package br.com.nava.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nava.entities.UsuarioEntity;
import br.com.nava.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<UsuarioEntity> getAll(){
		return usuarioRepository.findAll();
	}
	
	public UsuarioEntity getOne(int id) {
		return usuarioRepository.findById(id).orElse( new UsuarioEntity() );
	}
	
	public UsuarioEntity save(UsuarioEntity usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public UsuarioEntity update(int id, UsuarioEntity usuario) {
		Optional<UsuarioEntity> optional = usuarioRepository.findById(id);
		
		if (optional.isPresent()) {
			UsuarioEntity usuarioBD = optional.get();
			usuarioBD.setEmail( usuario.getEmail() );
			usuarioBD.setNome( usuario.getNome() );
			
			return usuarioRepository.save(usuarioBD);
		}
		else {
			return new UsuarioEntity();
		}
	}
	
	public void delete(int id) {
		usuarioRepository.deleteById(id);
	}	
}
