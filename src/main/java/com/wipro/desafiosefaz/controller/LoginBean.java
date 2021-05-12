package com.wipro.desafiosefaz.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.wipro.desafiosefaz.dao.UsuarioDAO;
import com.wipro.desafiosefaz.model.Telefone;
import com.wipro.desafiosefaz.model.Usuario;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

	private Usuario usuario = new Usuario();
	private UsuarioDAO usuarioDAO = new UsuarioDAO();

	
	private String mensagem="";
	
	public String logar(){
		
		//System.out.println(pessoa.getLogin() + "  " + pessoa.getSenha());
		
		Usuario pessoaUser = usuarioDAO.consultarUsuario(usuario.getLogin(), usuario.getSenha());
		FacesContext context = FacesContext.getCurrentInstance(); 
		ExternalContext externalContext = context.getExternalContext();
		
		if(pessoaUser != null){//achou usuario
			//adicionar o usuário na sessão usuarioLogado
			
			externalContext.getSessionMap().put("usuarioLogado", pessoaUser); 
			
			
			return "sessao.xhtml";
		}
		
		context.addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Login ou senha incorreta.", "Login ou senha incorreta"));
		return "index.xhtml";
	}

	
	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



	
	
}
