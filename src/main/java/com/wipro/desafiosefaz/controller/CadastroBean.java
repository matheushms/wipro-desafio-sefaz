package com.wipro.desafiosefaz.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import com.wipro.desafiosefaz.dao.UsuarioDAO;
import com.wipro.desafiosefaz.model.Telefone;
import com.wipro.desafiosefaz.model.Usuario;

@ViewScoped
@ManagedBean(name = "cadastroBean")
public class CadastroBean implements Serializable {


	private Usuario usuario = new Usuario();
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private List<Usuario> usuarios = new ArrayList<Usuario>();
		
	private List<Telefone> telefones = new ArrayList<Telefone>();
	private Telefone telefone = new Telefone();

	
	
	@PostConstruct 
	public void carregarPessoas(){
		usuarios = usuarioDAO.getListEntity(Usuario.class);
	}
	
	public void addNumber() {
		telefones.add(telefone);
		telefone = new Telefone();
	}
	
	public String removeTelefone(){
		telefones.remove(telefone);
		telefone = new Telefone();
		mostrarMsg("Removido com sucesso!");
		return "";
	}
	
	
	
	public String deslogar(){
		
		FacesContext context = FacesContext.getCurrentInstance(); //para pegar alguma informação do ambiente de execução do jsf
		ExternalContext externalContext = context.getExternalContext();
		externalContext.getSessionMap().remove("usuarioLogado"); 
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) context.getExternalContext().getRequest();
		
		httpServletRequest.getSession().invalidate();
		
		
		return "index";
	}

	public String salvar(){
		usuario = usuarioDAO.merge(usuario);
		carregarPessoas();
		System.out.println(usuarios);
		mostrarMsg("Cadastrado com sucesso!");
		usuario = new Usuario();
		return "";
	}
	
	public String remove(){
		usuarioDAO.deletePorLogin(usuario);
		usuario = new Usuario();
		carregarPessoas();
		mostrarMsg("Removido com sucesso!");
		return "";
	}
	
	private void mostrarMsg(String msg) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(msg);
		context.addMessage(null, message);
		
	}
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}



	
	
	
}
