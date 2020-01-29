package br.com.purplefire.crm.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.purplefire.crm.domain.Categoria;
import br.com.purplefire.crm.domain.Cidade;
import br.com.purplefire.crm.domain.Cliente;
import br.com.purplefire.crm.domain.Endereco;
import br.com.purplefire.crm.domain.Estado;
import br.com.purplefire.crm.domain.ItemPedido;
import br.com.purplefire.crm.domain.Pagamento;
import br.com.purplefire.crm.domain.PagamentoComBoleto;
import br.com.purplefire.crm.domain.PagamentoComCartao;
import br.com.purplefire.crm.domain.Pedido;
import br.com.purplefire.crm.domain.Produto;
import br.com.purplefire.crm.domain.enums.EstadoPagamento;
import br.com.purplefire.crm.domain.enums.Perfil;
import br.com.purplefire.crm.domain.enums.TipoCliente;
import br.com.purplefire.crm.repositories.CategoriaRepository;
import br.com.purplefire.crm.repositories.CidadeRepository;
import br.com.purplefire.crm.repositories.ClienteRepository;
import br.com.purplefire.crm.repositories.EnderecoRepository;
import br.com.purplefire.crm.repositories.EstadoRepository;
import br.com.purplefire.crm.repositories.ItemPedidoRepository;
import br.com.purplefire.crm.repositories.PagamentoRepository;
import br.com.purplefire.crm.repositories.PedidoRepository;
import br.com.purplefire.crm.repositories.ProdutoRepository;

@Service
public class DBService {

	@Autowired
	private BCryptPasswordEncoder pe;
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public void instantiateDatabase() throws ParseException {
		Categoria cat1 = new Categoria(null, "Isqueiros");
		Categoria cat2 = new Categoria(null, "Acessórios");

		/*
		 * Isqueiros
		 */

		Produto p1 = new Produto(null, "Isqueiro Purplefire - Clássico", 70.00);

		Produto p2 = new Produto(null, "Isqueiro Purplefire - Aromático Morango", 70.00);
		Produto p3 = new Produto(null, "Isqueiro Purplefire - Aromático Chocolate", 70.00);
		Produto p4 = new Produto(null, "Isqueiro Purplefire - Aromático Baunilha", 70.00);
		Produto p5 = new Produto(null, "Isqueiro Purplefire - Aromático Uva", 70.00);

		Produto p6 = new Produto(null, "Isqueiro Purplefire - Aromático California Grape Kush", 75.00);
		Produto p7 = new Produto(null, "Isqueiro Purplefire - Aromático California Vanilla Kush", 75.00);
		Produto p8 = new Produto(null, "Isqueiro Purplefire - Aromático California Strawberry Kush", 75.00);
		Produto p9 = new Produto(null, "Isqueiro Purplefire - Aromático California Blackberry Kush", 75.00);
		Produto p10 = new Produto(null, "Isqueiro Purplefire - Aromático California Lemon Kush", 75.00);
		Produto p11 = new Produto(null, "Isqueiro Purplefire - Aromático California Cherry Og", 75.00);

		Produto p12 = new Produto(null, "Isqueiro Purplefire - Space Dust Spaced Out (Foguete)", 75.00);
		Produto p13 = new Produto(null, "Isqueiro Purplefire - Space Dust E-Tripping (ET)", 75.00);
		Produto p14 = new Produto(null, "Isqueiro Purplefire - Space Dust Octopuff (Monstro)", 75.00);
		Produto p15 = new Produto(null, "Isqueiro Purplefire - Space Dust Aqualazy (Monstro)", 75.00);
		Produto p16 = new Produto(null, "Isqueiro Purplefire - Space Dust Electric Saturn (Planeta)", 75.00);
		Produto p17 = new Produto(null, "Isqueiro Purplefire - Space Dust Astrohigh (Astronauta)", 75.00);

		Produto p18 = new Produto(null, "Isqueiro Purplefire - Skull Gorilla", 75.00);
		Produto p19 = new Produto(null, "Isqueiro Purplefire - Skull Lion", 75.00);
		Produto p20 = new Produto(null, "Isqueiro Purplefire - Skull T-Rex", 75.00);

		Produto p21 = new Produto(null, "Isqueiro Purplefire - Halloween Slasher Chucky", 75.00);
		Produto p22 = new Produto(null, "Isqueiro Purplefire - Halloween Slasher Freddy", 75.00);
		Produto p23 = new Produto(null, "Isqueiro Purplefire - Halloween Slasher Hannibal", 75.00);
		Produto p24 = new Produto(null, "Isqueiro Purplefire - Halloween Slasher It", 75.00);
		Produto p25 = new Produto(null, "Isqueiro Purplefire - Halloween Slasher Jason", 75.00);
		Produto p26 = new Produto(null, "Isqueiro Purplefire - Halloween Slasher Myers", 75.00);
		Produto p27 = new Produto(null, "Isqueiro Purplefire - Halloween Grim Reaper", 75.00);
		Produto p28 = new Produto(null, "Isqueiro Purplefire - Halloween Werewolf", 75.00);
		Produto p29 = new Produto(null, "Isqueiro Purplefire - Halloween Dracula", 75.00);
		Produto p30 = new Produto(null, "Isqueiro Purplefire - Halloween Pumpkin", 75.00);
		Produto p31 = new Produto(null, "Isqueiro Purplefire - Halloween Frankestein", 75.00);
		Produto p32 = new Produto(null, "Isqueiro Purplefire - Halloween Cult Clown", 75.00);
		Produto p33 = new Produto(null, "Isqueiro Purplefire - Halloween Nosferatu", 75.00);

		Produto p34 = new Produto(null, "Isqueiro Purplefire - LDRV Adele", 75.00);
		Produto p35 = new Produto(null, "Isqueiro Purplefire - LDRV Bey", 75.00);
		Produto p36 = new Produto(null, "Isqueiro Purplefire - LDRV Dua", 75.00);
		Produto p37 = new Produto(null, "Isqueiro Purplefire - LDVR Gaga", 75.00);
		Produto p38 = new Produto(null, "Isqueiro Purplefire - LDVR Little Monster", 75.00);
		Produto p39 = new Produto(null, "Isqueiro Purplefire - LDVR Katy", 75.00);
		Produto p40 = new Produto(null, "Isqueiro Purplefire - LDVR Riri", 75.00);

		Produto p41 = new Produto(null, "Isqueiro Purplefire - Collab Filipe Ret", 75.00);
		Produto p42 = new Produto(null, "Isqueiro Purplefire - Collab Cone Crew Diretoria", 75.00);
		Produto p43 = new Produto(null, "Isqueiro Purplefire - Collab Costa Gold", 75.00);
		Produto p44 = new Produto(null, "Isqueiro Purplefire - Collab Costa Gold Predella", 75.00);
		Produto p45 = new Produto(null, "Isqueiro Purplefire - Collab Costa Gold Nog", 75.00);
		Produto p46 = new Produto(null, "Isqueiro Purplefire - Collab Costa Gold Cidy", 75.00);

		/*
		 * Acessórios
		 */

		Produto p47 = new Produto(null, "Case", 30.00);
		Produto p48 = new Produto(null, "Caixa de Seda com Piteira (24 unidades)", 0.00); // Verificar preço.
		Produto p49 = new Produto(null, "Seda Avulsa com Piteira (33 folhas cartela magnética)", 6.00);
		Produto p50 = new Produto(null, "Mousepad Weedy", 8.00);
		Produto p51 = new Produto(null, "Mousepad Becksket", 8.00);
		Produto p52 = new Produto(null, "Mousepad Venom", 8.00);
		Produto p53 = new Produto(null, "Mousepad Purpledrink", 8.00);
		Produto p54 = new Produto(null, "Piteira de Vidro", 10.00);
		Produto p55 = new Produto(null, "Purple Munchies Gomas Ursinhos Ácidos", 7.00);
		Produto p56 = new Produto(null, "Purple Munchies Gomas Smoke Rings Morango", 7.00);
		Produto p57 = new Produto(null, "Purple Munchies Chocolate Green Sour Kush", 14.00);
		Produto p58 = new Produto(null, "Purple Munchies Chocolate Purple Kush", 14.00);

		cat1.getProdutos()
				.addAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18,
						p19, p20, p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p33, p34, p35, p36, p37,
						p38, p39, p40, p41, p42, p43, p44, p45, p46));

		cat2.getProdutos().addAll(Arrays.asList(p47, p48, p49, p50, p51, p52, p53, p54, p55, p56, p57, p58));

		p1.getCategorias().add(cat1);
		p2.getCategorias().add(cat1);
		p3.getCategorias().add(cat1);
		p4.getCategorias().add(cat1);
		p5.getCategorias().add(cat1);
		p6.getCategorias().add(cat1);
		p7.getCategorias().add(cat1);
		p8.getCategorias().add(cat1);
		p9.getCategorias().add(cat1);
		p10.getCategorias().add(cat1);
		p11.getCategorias().add(cat1);
		p12.getCategorias().add(cat1);
		p13.getCategorias().add(cat1);
		p14.getCategorias().add(cat1);
		p15.getCategorias().add(cat1);
		p16.getCategorias().add(cat1);
		p17.getCategorias().add(cat1);
		p18.getCategorias().add(cat1);
		p19.getCategorias().add(cat1);
		p20.getCategorias().add(cat1);
		p21.getCategorias().add(cat1);
		p22.getCategorias().add(cat1);
		p23.getCategorias().add(cat1);
		p24.getCategorias().add(cat1);
		p25.getCategorias().add(cat1);
		p26.getCategorias().add(cat1);
		p27.getCategorias().add(cat1);
		p28.getCategorias().add(cat1);
		p29.getCategorias().add(cat1);
		p30.getCategorias().add(cat1);
		p31.getCategorias().add(cat1);
		p32.getCategorias().add(cat1);
		p33.getCategorias().add(cat1);
		p34.getCategorias().add(cat1);
		p35.getCategorias().add(cat1);
		p36.getCategorias().add(cat1);
		p37.getCategorias().add(cat1);
		p38.getCategorias().add(cat1);
		p39.getCategorias().add(cat1);
		p40.getCategorias().add(cat1);
		p41.getCategorias().add(cat1);
		p42.getCategorias().add(cat1);
		p43.getCategorias().add(cat1);
		p44.getCategorias().add(cat1);
		p45.getCategorias().add(cat1);
		p46.getCategorias().add(cat1);

		p47.getCategorias().add(cat2);
		p48.getCategorias().add(cat2);
		p49.getCategorias().add(cat2);
		p50.getCategorias().add(cat2);
		p51.getCategorias().add(cat2);
		p52.getCategorias().add(cat2);
		p53.getCategorias().add(cat2);
		p54.getCategorias().add(cat2);
		p55.getCategorias().add(cat2);
		p56.getCategorias().add(cat2);
		p57.getCategorias().add(cat2);
		p58.getCategorias().add(cat2);

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16,
				p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38,
				p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50, p51, p52, p53, p54, p55, p56, p57, p58));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA,
				pe.encode("123"), "Tabacaria do Osvaldo", "Representante Comercial", Perfil.USUARIO);

		Cliente cli2 = new Cliente(null, "Ana Costa", "ana@gmail.com", "38087993071", TipoCliente.PESSOAFISICA,
				pe.encode("123"), "Tabacaria Oliveira", "Responsavel Comercial", Perfil.USUARIO);

		Cliente cli3 = new Cliente(null, "Amanda Pogere", "amanda@gmail.com", "38087993071", TipoCliente.PESSOAFISICA,
				pe.encode("123"), "Tabacaria Oliveira", "Responsavel Comercial", Perfil.ADMINISTRADOR);

		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		cli2.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		Endereco e3 = new Endereco(null, "Rua Santa Fé", "105", null, "Boa Vista", "80050020", cli2, c2);
		Endereco e4 = new Endereco(null, "Rua Nove de Março", "105", null, "Centro", "80050020", cli3, c2);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		cli2.getEnderecos().addAll(Arrays.asList(e3));
		cli3.getEnderecos().addAll(Arrays.asList(e4));

		clienteRepository.saveAll(Arrays.asList(cli1, cli2, cli3));
		enderecoRepository.saveAll(Arrays.asList(e1, e2, e3, e4));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"),
				null);
		ped2.setPagamento(pagto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}

}
