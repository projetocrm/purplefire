package br.com.purplefire.crm.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.purplefire.crm.domain.Cidade;
import br.com.purplefire.crm.domain.Cliente;
import br.com.purplefire.crm.domain.Endereco;
import br.com.purplefire.crm.domain.enums.Perfil;
import br.com.purplefire.crm.domain.enums.TipoCliente;
import br.com.purplefire.crm.dto.ClienteDTO;
import br.com.purplefire.crm.dto.ClienteNewDTO;
import br.com.purplefire.crm.repositories.ClienteRepository;
import br.com.purplefire.crm.repositories.EnderecoRepository;
import br.com.purplefire.crm.security.UserSpringSecurity;
import br.com.purplefire.crm.services.exceptions.AuthorizationException;
import br.com.purplefire.crm.services.exceptions.DataIntegrityException;
import br.com.purplefire.crm.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private ClienteRepository repo;

	@Autowired
	private EnderecoRepository enderecoRepository;

	public Cliente find(Integer id) {
		UserSpringSecurity user = UserService.authenticated();
		if (user == null || user.hasRole(Perfil.ADMINISTRADOR) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado.");
		}
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}

	public Cliente findByEmail(String email) {
		UserSpringSecurity user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMINISTRADOR) && !email.equals(user.getUsername())) {
			throw new AuthorizationException("Acesso negado.");
		}

		Cliente obj = repo.findByEmail(email);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado, id: " + user.getId() + ", tipo: " + Cliente.class.getName());
		}
		return obj;
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		@SuppressWarnings("deprecation")
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		// enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um cliente que possui pedidos.");
		}
	}

	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null, null, null, null, null);
	}

	public Cliente fromDTO(ClienteNewDTO objDto) {
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(),
				TipoCliente.toEnum(objDto.getTipo()), pe.encode(objDto.getSenha()), objDto.getEmpresa(),
				objDto.getCargo(), Perfil.USUARIO);
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(),
				objDto.getBairro(), objDto.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		if (objDto.getTelefone2() != null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3() != null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		return cli;
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
