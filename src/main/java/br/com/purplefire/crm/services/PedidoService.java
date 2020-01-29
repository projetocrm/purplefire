package br.com.purplefire.crm.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.purplefire.crm.domain.Cliente;
import br.com.purplefire.crm.domain.ItemPedido;
import br.com.purplefire.crm.domain.PagamentoComBoleto;
import br.com.purplefire.crm.domain.Pedido;
import br.com.purplefire.crm.domain.Produto;
import br.com.purplefire.crm.domain.enums.EstadoPagamento;
import br.com.purplefire.crm.domain.enums.Perfil;
import br.com.purplefire.crm.repositories.ItemPedidoRepository;
import br.com.purplefire.crm.repositories.PagamentoRepository;
import br.com.purplefire.crm.repositories.PedidoRepository;
import br.com.purplefire.crm.repositories.ProdutoRepository;
import br.com.purplefire.crm.security.UserSpringSecurity;
import br.com.purplefire.crm.services.exceptions.AuthorizationException;
import br.com.purplefire.crm.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;

	@Autowired
	private BoletoService boletoService;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	// @Autowired
	// private ClienteRepository clienteRepository;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private EmailService emailService;

	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		UserSpringSecurity user = UserService.authenticated();
		Cliente cliente = clienteService.find(user.getId());
		if (!user.hasRole(Perfil.ADMINISTRADOR) && obj.isPresent()
				&& !cliente.getId().equals(obj.get().getCliente().getId())) {
			throw new AuthorizationException("Acesso negado.");
		}
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

	public Pedido insert(Pedido obj) {
		/*
		 * # O código abaixo é para buscar o ID especificado no POST (em caso de
		 * administradores precisarem efetuar um pedido em nome de um cliente) -
		 * Observação: Necessário definir clienteRepository
		 * 
		 * Optional<Cliente> cliente =
		 * clienteRepository.findById(obj.getCliente().getId());
		 */
		UserSpringSecurity user = UserService.authenticated();
		Cliente cliente = clienteService.find(user.getId());

		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(cliente);
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens()) {
			Optional<Produto> produto = produtoRepository.findById(ip.getProduto().getId());
			if (produto.isPresent()) {
				ip.setDesconto(0.0);
				ip.setProduto(produto.get());
				ip.setPreco(ip.getProduto().getPreco());
				ip.setPedido(obj);
			}
		}
		itemPedidoRepository.saveAll(obj.getItens());
		emailService.sendOrderConfirmationEmail(obj);

		return obj;
	}

	public Page<Pedido> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		UserSpringSecurity user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado.");
		}
		@SuppressWarnings("deprecation")
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Cliente cliente = clienteService.find(user.getId());
		return repo.findByCliente(cliente, pageRequest);
	}
}
