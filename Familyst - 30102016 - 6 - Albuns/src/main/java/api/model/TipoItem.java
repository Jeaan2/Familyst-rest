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
public class TipoItem {
    
    @XmlElement
    private final int idTipoItem;
    
    @XmlElement
    private final String nome;

    public TipoItem(int idTipoItem, String nome) {
        this.idTipoItem = idTipoItem;
        this.nome = nome;
    }

    public TipoItem() {
        this.idTipoItem = 0;
        this.nome = null;
    }

    public int getIdTipoItem() {
        return idTipoItem;
    }

    public String getNome() {
        return nome;
    }        
    
}
