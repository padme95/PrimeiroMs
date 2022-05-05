package br.com.nava.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nava.dtos.EnderecoDTO;
import br.com.nava.entities.EnderecoEntity;
import br.com.nava.repositories.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public List<EnderecoDTO> getAll(){
		
		List<EnderecoEntity> entities = this.enderecoRepository.findAll();
		
		List<EnderecoDTO> dtos = new ArrayList<EnderecoDTO>();
		
		for (EnderecoEntity enderecoEntity : entities) {
			dtos.add( enderecoEntity.toDTO() );
		}
		
		//return this.enderecoRepository.findAll();
		return dtos;
	}
	
	public EnderecoDTO getOne(int id) {
		return this.enderecoRepository.findById(id)
					.orElse( new EnderecoEntity() ).toDTO();
	}
	
	public EnderecoDTO save(EnderecoEntity endereco) {		
		return enderecoRepository.save(endereco).toDTO();
	}
	
	public EnderecoDTO update(int id, EnderecoEntity novoEndereco) {
		
		//verificar se o registro existe
		
		Optional<EnderecoEntity> enderecoBD = enderecoRepository.findById(id);
		
		// se o registro existir
		if (enderecoBD.isPresent()) {
			EnderecoEntity obj = enderecoBD.get();
			
			obj.setCep( novoEndereco.getCep() );
			obj.setCidade( novoEndereco.getCidade() );
			obj.setEstado(novoEndereco.getEstado());
			obj.setNumero(novoEndereco.getNumero());
			obj.setRua( novoEndereco.getRua());
			obj.setUsuario( novoEndereco.getUsuario()); 
			
			return enderecoRepository.save(obj).toDTO();						
		}
		else {
			return new EnderecoEntity().toDTO();
		}
	}
	
	public void delete(int id) {
		enderecoRepository.deleteById(id);
	}
}
