/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import model.*;

/**
 *
 * @author Renan
 */

//Classe principal de teste de manipulação de conteúdo no banco de dados
public class BlobTester {
    public static void main(String[] args) {
        //DatabaseCreator db = new DatabaseCreator();
        PartidaDAO p1 = new PartidaDAO();
        p1.setPartida(4000, 300, "admin");
    }
}
