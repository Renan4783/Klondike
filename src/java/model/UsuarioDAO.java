package model;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.faces.bean.SessionScoped;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import javax.faces.bean.ManagedBean;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Renan
 */
@ManagedBean
@SessionScoped
public class UsuarioDAO {
    
    private final SessionFactory sessionFactory;
    
    
    public UsuarioDAO(){
        Configuration conf = new AnnotationConfiguration();
        conf.configure();
        sessionFactory = conf.buildSessionFactory();
    }
    

    public List<Usuario> getUmUsuario(String nome, String senha){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Usuario> result = session.createQuery("from Usuario where nome=:name and senha=:pass").setString("name", nome).setString("pass", senha).list();
        session.getTransaction().commit();
        session.close();
        return result;
    
    }
    
    public List<Usuario> getUmUsuarioLogin(String nome){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Usuario> result = session.createQuery("from Usuario where nome=:name").setString("name", nome).list();
        session.getTransaction().commit();
        session.close();
        return result;
        
    }
    
    public void setUsuario(String nome, String senha, String email) throws UnsupportedEncodingException, NoSuchAlgorithmException{
        Session session = sessionFactory.openSession();
        Transaction trans = session.beginTransaction();
        byte[] bytestoMessage = senha.getBytes("UTF-8");
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digest = md.digest(bytestoMessage);
        String senhastr = new String(digest, StandardCharsets.UTF_8);
//cria um novo objeto da classe Usuario
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setSenha(senhastr);
        usuario.setEmail(email);
        session.save(usuario);
        /*salva o objeto na sessão do Hibernate
se for usado o comando saveOrUpdate(), o Hibernate irá inserir no banco de dados caso seja um novo objeto (new Usuario())
ou alterar se for um objeto já existente*/
        trans.commit(); //confirma a transação salvando o objeto no banco de dados
        session.close();
    }
    
}

