/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author Renan
 */
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.*;

import org.primefaces.context.RequestContext;

//Classe de controle e visão do login

@ManagedBean
public class UserLoginView {

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //método que verifica o login, consultando o banco de dados
    public void login(ActionEvent event) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        FacesMessage message = null;
        boolean loggedIn = false;
        byte[] bytestoMessage = password.getBytes("UTF-8");
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digest = md.digest(bytestoMessage);
        String senhastr = new String(digest, StandardCharsets.UTF_8);
        try {
            List<Usuario> user = new LinkedList<>();
            Usuario userBean = new Usuario();
            UsuarioDAO usuario = new UsuarioDAO();
            user = usuario.getUmUsuario(username, senhastr);
            userBean = user.get(0);
            if (usuario.getUmUsuario(username, senhastr).size()>0) {
                loggedIn = true;
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bem vindo!", username);
            } else {
                loggedIn = false;
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro", "Credenciais inválidas!");
            }

            FacesContext.getCurrentInstance().addMessage(null, message);
            session.setAttribute("ID_USER", userBean);
            context.addCallbackParam("loggedIn", loggedIn);
        } catch (IndexOutOfBoundsException ie) {
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Usuario ou senha não encontrados!", "Digite corretamente ou crie uma nova conta!");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    //Método que retorna o nome do usuário gravado em uma sessão
    public String requestUser() {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = (HttpSession) request.getSession();
        Usuario userReq;
        userReq = (Usuario) session.getAttribute("ID_USER");
        return userReq.getNome();
    }

    public Usuario request() {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = (HttpSession) request.getSession();
        Usuario userReq;
        userReq = (Usuario) session.getAttribute("ID_USER");
        return userReq;
    }
    
    //Método que destroi a sessão
    public void endSession() {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = (HttpSession) request.getSession();
        session.invalidate();
    }
}
