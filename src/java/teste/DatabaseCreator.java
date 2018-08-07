/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import java.util.LinkedList;
import java.util.List;
import model.Usuario;
import model.UsuarioDAO;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 *
 * @author Renan
 */

//Classe geradora do banco de dados
public class DatabaseCreator {
    public DatabaseCreator(){
        List<Usuario> userlist = new LinkedList<>();
        Usuario userbean = new Usuario();
        UsuarioDAO u1 = new UsuarioDAO();
        Configuration conf = new AnnotationConfiguration();
        conf.configure();
        SchemaExport se = new SchemaExport(conf);
        se.create(true, true);
    }
}
