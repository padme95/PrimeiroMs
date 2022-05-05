package br.com.nava.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nava.dtos.EnderecoDTO;
import br.com.nava.entities.EnderecoEntity;
import br.com.nava.services.EnderecoService;

@RestController
@RequestMapping(value = "enderecos")
public class EnderecoController {
	
	@Autowired
	private EnderecoService enderecoService;
	
	@GetMapping
	public ResponseEntity< List<EnderecoDTO> > getAll(){
		//System.out.println("aqui");
//		return ResponseEntity.status(HttpStatus.OK)
//				.body( enderecoService.getAll() );
		
		return ResponseEntity.ok()
				.body( enderecoService.getAll() );
	} 
	
	@GetMapping("{id}")
	public ResponseEntity< EnderecoDTO> getOne(@PathVariable int id) {
		return ResponseEntity.ok().body( enderecoService.getOne(id) );
	}
	
	@PostMapping
	public ResponseEntity< EnderecoDTO> save(@RequestBody EnderecoDTO endereco) {
		//return ResponseEntity.ok().body ( enderecoService.save( endereco.toEntity() ).toDTO() );
		
		EnderecoEntity enderecoEntity = endereco.toEntity();
		EnderecoDTO enderecoDTOSalvo = enderecoService.save( enderecoEntity );
		
		return ResponseEntity.ok().body ( enderecoDTOSalvo );
	}
	
	@PatchMapping(value = "{id}")
	public ResponseEntity< EnderecoDTO > update(@PathVariable int id, @RequestBody EnderecoDTO endereco) {
		return ResponseEntity.ok().body( enderecoService.update(id, endereco.toEntity()) );		
	}
	
	@DeleteMapping(value = "{id}")
	public void delete(@PathVariable int id) {
		enderecoService.delete(id);
	}
	
}
