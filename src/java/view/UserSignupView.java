/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import model.UsuarioDAO;
/**
 *
 * @author Renan
 */
@ManagedBean
public class UserSignupView {

    private String username;

    private String email;

    private String password;

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public void cadastrar(ActionEvent event) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        FacesMessage message = null;
        try {
            UsuarioDAO usuario = new UsuarioDAO();
            System.out.println(usuario.getUmUsuarioLogin(username));
            if (usuario.getUmUsuarioLogin(username).isEmpty()){
                usuario.setUsuario(username, password, email);
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastrado com sucesso!", "login já pode ser efetuado!");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro!", "O usuário já existe!");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }            
        } catch (InternalError ie) {
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro interno!", "Contate o administrador!");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
}
