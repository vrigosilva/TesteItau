package br.com.itau.testeitau.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="JOGADOR")
@ApiModel(description = "Ator principal do sistema que podera ser cadastrado, consultado, alterado e removido.")
public class Jogador {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @ApiModelProperty(notes = "${jogador.id.notes}", example = "1", readOnly=true, position = 0)
	private Long id;
	
	@Column
	@NotEmpty @NotNull
	@Size(max=50)
	@ApiModelProperty(notes = "${jogador.nome.notes}", example = "Vinicius Rigo", required = true, position = 1)
	private String name;
	
	@Column
	@NotEmpty @NotNull
	@Email
	@Size(max=50)
	@ApiModelProperty(notes = "${jogador.email.notes}", example = "vinicius@email.com", required = true, position = 2)
	private String email;
	
	@Column
	@NotNull
	@ApiModelProperty(notes = "${jogador.origem.notes}", example = "VINGADORES" , required = true, position = 3)
	private ListaOrigemEnum origem;

	@Column
    @ApiModelProperty(notes = "${jogador.fone.notes}", example = "11 99999-9999", required = false, position = 4)
	private String fone;
	
	@Column
	@NotEmpty @NotNull
	@ApiModelProperty(notes = "${jogador.codinome.notes}", example = "Hulk", required = true, position = 5)
	private String codinome;

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFone() {
		return fone;
	}
	public void setFone(String fone) {
		this.fone = fone;
	}
	public ListaOrigemEnum getOrigem() {
		return origem;
	}
	public void setOrigem(ListaOrigemEnum origem) {
		this.origem = origem;
	}
	public String getCodinome() {
		return codinome;
	}
	public void setCodinome(String codinome) {
		this.codinome = codinome;
	}
	@Override
	public String toString() {
		return "Jogador [id=" + id + ", name=" + name + ", email=" + email + ", fone=" + fone + ", codinome=" + codinome
				+ ", origem=" + origem + "]";
	}
	
}
