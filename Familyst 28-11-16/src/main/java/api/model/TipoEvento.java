/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jdfid
 */
@XmlRootElement
public class TipoEvento {
    
    @XmlElement
    final private int idTipoEvento;
    
    @XmlElement
    final private String nome;

    public TipoEvento(int idTipoEvento, String nome) {
        this.idTipoEvento = idTipoEvento;
        this.nome = nome;
    }
    
    public TipoEvento () {
        this.idTipoEvento = 0;
        this.nome = null;
    }

    public int getIdTipoEvento() {
        return idTipoEvento;
    }

    public String getNome() {
        return nome;
    }
    
}
