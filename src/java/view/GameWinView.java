/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;
import model.PartidaDAO;
import model.Usuario;

/**
 *
 * @author Renan
 */
@ManagedBean
public class GameWinView {
    private double pontos;
    private double tempo;
    private String user;

    /**
     * @return the pontos
     */
    public double getPontos() {
        return pontos;
    }

    /**
     * @param pontos the pontos to set
     */
    public void setPontos(double pontos) {
        this.pontos = pontos;
    }

    /**
     * @return the tempo
     */
    public double getTempo() {
        return tempo;
    }

    /**
     * @param tempo the tempo to set
     */
    public void setTempo(double tempo) {
        this.tempo = tempo;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }
    
    public void insertCoin(ActionEvent event){
        PartidaDAO win = new PartidaDAO();
        UserLoginView sessionUser = new UserLoginView();
        win.setPartida(pontos, tempo, sessionUser.requestUser());
    }
}
