/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Renan
 */
@Entity
@Table(name="P_Partida")
public class Partida {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long codPartida;
    private double tempo;
    private double pontos;
    private boolean resultado;
    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name="id_usuario")
    private Usuario usuario = new Usuario();

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the codPartida
     */
    public long getCodPartida() {
        return codPartida;
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
     * @return the resultado
     */
    public boolean isResultado() {
        return resultado;
    }

    /**
     * @param resultado the resultado to set
     */
    public void setResultado(boolean resultado) {
        this.resultado = resultado;
    }

    /**
     * @return the Usuarios
     */
    
    
}
