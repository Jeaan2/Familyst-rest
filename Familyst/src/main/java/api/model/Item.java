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
public class Item {
    
    @XmlElement
    private final int idItem;
    
    @XmlElement
    private final int quantidade;
    
    @XmlElement
    private final int idTipoItem;
    
    @XmlElement
    private final int idEvento;
    
    @XmlElement
    private final int idUsuario;

    public Item(int idItem, int quantidade, int idTipoItem, int idEvento, int idUsuario) {
        this.idItem = idItem;
        this.quantidade = quantidade;
        this.idTipoItem = idTipoItem;
        this.idEvento = idEvento;
        this.idUsuario = idUsuario;
    }

    public Item() {
        this.idItem = 0;
        this.quantidade = 0;
        this.idTipoItem = 0;
        this.idEvento = 0;
        this.idUsuario = 0;
    }

    public int getIdItem() {
        return idItem;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public int getIdTipoItem() {
        return idTipoItem;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public int getIdUsuario() {
        return idUsuario;
    }
    
    
}
