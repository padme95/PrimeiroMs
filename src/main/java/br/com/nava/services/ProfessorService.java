package br.com.nava.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nava.dtos.ProfessorDTO;
import br.com.nava.entities.ProfessorEntity;
import br.com.nava.repositories.ProfessorRepository;

@Service
public class ProfessorService {
	
	@Autowired
	private ProfessorRepository professorRepository;

//	public void mostrar() {
//		System.out.println("mostrar");
//	}
	
	public List<ProfessorDTO> getAll(){
		List<ProfessorEntity> lista =  professorRepository.findAll();
		
		List<ProfessorDTO> listaDTO = new ArrayList<>();
		
		/*
		 * for (int i = 0; i < lista.size(); i++) {
			ProfessorEntity professorEntity = lista.get(i);
		}*/
		// foreach
		// 1-) Tipo da variável de cada elemento da lista
		// 2-) nome local da variável 
		// - 3 lista com elementos a ser percorrido
		
		for (ProfessorEntity professorEntity : lista) {						
			
//			ProfessorDTO dto = new ProfessorDTO();
//			
//			dto.setId( professorEntity.getId() );
//			dto.setCep( professorEntity.getCep() );
//			//dto.setCpf( professorEntity.getCpf() );
//			dto.setNome( professorEntity.getNome() );
//			dto.setNumero( professorEntity.getNumero() );
//			dto.setRua( professorEntity.getRua() );
			
//			listaDTO.add(dto);
			listaDTO.add( professorEntity.toDTO() );
		}
		
		return listaDTO;		
	}
	
//	public ProfessorEntity getOne(int id, ArrayList<ProfessorEntity> listaProfessor) {
//		
//		int indice = findIndex(id, listaProfessor);
//		return (indice >= 0 ? listaProfessor.get(indice) : null);
//	}
	
	public ProfessorDTO getOne(int id) {
		
		Optional<ProfessorEntity> optional = professorRepository.findById(id);
		
		ProfessorEntity professor = optional.orElse( new ProfessorEntity() );
		
		return professor.toDTO();
	}
	
	public ProfessorDTO save(ProfessorEntity professor) {
		return professorRepository.save(professor).toDTO();
	}
	
//	public int findIndex(int id, ArrayList<ProfessorEntity> listaProfessor) {
//		
//		for (int i = 0; i < listaProfessor.size(); i++) {
//			if (listaProfessor.get(i).getId() == id) {
//				return i;
//			}
//		}
//		
//		return -1;
//	}
	 // variável professor contém os dados vindo da requisição  REST
//	public ProfessorEntity update(int id, ProfessorEntity professor, 
//				ArrayList<ProfessorEntity> listaProfessor ) {
//		
//		int indice = findIndex(id, listaProfessor);
//		
//		if (indice >= 0) {
//			
//			listaProfessor.get(indice).setNome( professor.getNome() );
//			listaProfessor.get(indice).setCpf( professor.getCpf() );
//			listaProfessor.get(indice).setRua( professor.getRua() );
//			listaProfessor.get(indice).setNumero( professor.getNumero() );
//			listaProfessor.get(indice).setCep( professor.getCep() );
//			
//			return listaProfessor.get(indice);
//		}
//		
//		return null;
//	}
	
	public ProfessorDTO update(int id, ProfessorEntity professor) {
	
		// primeiro passo é verificar se o registro existe no banco de dados
		
		Optional<ProfessorEntity> optional = professorRepository.findById(id);
		// se existe no banco de dados
		if (optional.isPresent() == true) {
			// atualiza o objeto existente
			ProfessorEntity professorBD = optional.get();
			professorBD.setNome( professor.getNome() );
			professorBD.setCep( professor.getCep() );
			professorBD.setCpf( professor.getCpf() );
			professorBD.setNumero( professor.getNumero() );
			professorBD.setRua( professor.getRua() );
			
			return professorRepository.save(professorBD).toDTO();
		}
		// caso contrário, retorna um objeto vazio
		else {
			return new ProfessorEntity().toDTO();
		}
	}
	
//	public void delete(int id, ArrayList<ProfessorEntity> listaProfessor) {
//		
//		int indice = findIndex(id, listaProfessor);
//		
//		if (indice >= 0) listaProfessor.remove(indice);
//	}
	
	public void delete(int id) {
		
		professorRepository.deleteById(id);
		
//		try {
//			professorRepository.deleteById(id);
//		}
//		catch (Exception e) {
//			System.out.println("Registro não existe");			
//		}
		
	}
	
	public List<ProfessorDTO> searchByName(String nome){

		//List<ProfessorEntity> lista =  professorRepository.findByNomeContains(nome);
		//List<ProfessorEntity> lista =  professorRepository.searchByNome2(nome);
		List<ProfessorEntity> lista =  professorRepository.searchByNomeNativeSQL(nome);
		
				
		List<ProfessorDTO> dtos = new ArrayList<>();
		
		for (ProfessorEntity professorEntity : lista) {
			dtos.add( professorEntity.toDTO() );
		}
		
		return dtos;
	}
}
