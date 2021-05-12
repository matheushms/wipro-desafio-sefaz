package com.wipro.desafiosefaz.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table
public class Usuario{
	
	@NotNull(message="Login deve ser informada.")
	@NotEmpty(message="Login deve ser informada.")
	@Id
	private String login;
	
	
	@NotNull(message="Nome deve ser informado.")
	@NotEmpty(message="Nome deve ser informado.")
	@Column
	private String nome;
	
	
	@NotNull(message="Senha deve ser informada.")
	@NotEmpty(message="Senha deve ser informada.")
	@Column
	private String senha;
	
	@NotNull(message="Email deve ser informado.")
	@NotEmpty(message="Email deve ser informado.")
	@Column
	private String email;
	
	@OneToMany(fetch = FetchType.EAGER)
	private List<Telefone> telefones;

	

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [login=" + login + ", nome=" + nome + ", senha=" + senha + ", email=" + email + ", telefones="
				+ telefones + "]";
	}

	

	
	
	
	

}
