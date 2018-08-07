/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.LinkedList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import view.UserLoginView;

/**
 *
 * @author Renan
 */
@ManagedBean
@SessionScoped
public class PartidaDAO {
    
    private final SessionFactory sessionFactory;

    public PartidaDAO() {
        Configuration conf = new AnnotationConfiguration();
        conf.configure();
        sessionFactory = conf.buildSessionFactory();
    }

    public List<Partida> getPartida() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Partida> result = session.createQuery("from Partida order by tempo asc").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public List<Partida> getUserPartida() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        UserLoginView userview = new UserLoginView();
        List<Partida> result = session.createQuery("from Partida p where p.usuario.nome=:name").setString("name", userview.requestUser()).list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public void setPartida(double pontos, double tempo, String user) {
        Session session = sessionFactory.openSession();
        Transaction trans = session.beginTransaction();
//cria um novo objeto da classe Partida
        UsuarioDAO usuDao = new UsuarioDAO();
        Usuario usuinsert = new Usuario();
        List<Usuario> usulist = new LinkedList<>();
        usulist = usuDao.getUmUsuarioLogin(user);
        System.out.println("teste: " + user);
        usuinsert = usulist.get(0);
        Partida partida = new Partida();
        partida.setPontos(pontos);
        partida.setTempo(tempo);
        partida.setResultado(true);
        partida.setUsuario(usuinsert);
        session.save(partida);
        /*salva o objeto na sessão do Hibernate
se for usado o comando saveOrUpdate(), o Hibernate irá inserir no banco de dados caso seja um novo objeto (new Usuario())
ou alterar se for um objeto já existente*/
        trans.commit(); //confirma a transação salvando o objeto no banco de dados
        session.close();
    }
}
