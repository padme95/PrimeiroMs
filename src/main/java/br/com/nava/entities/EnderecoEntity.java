package br.com.nava.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.nava.dtos.EnderecoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ENDERECOS")
public class EnderecoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String rua;
	private int numero;
	private String cep;
	private String cidade;
	private String estado;
	
	/* nome do atributo da classe java que mapeia o endereço (Usuário)
	 * na classe UsuarioEntity*/
	@JsonIgnore
	@OneToOne(mappedBy = "endereco")
	@ToString.Exclude
	private UsuarioEntity usuario;
	
	public EnderecoDTO toDTO() {
		ModelMapper mapper = new ModelMapper();
		
		return mapper.map(this, EnderecoDTO.class);
	}
	
}
