/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.model;

import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jdfid
 */
@XmlRootElement
public class Evento {
    
    @XmlElement
    private final int idEvento;
    
    @XmlElement
    private final String nome;
    
    @XmlElement
    private final String descricao;
    
    @XmlElement
    private final Date dataCriacao;
    
    @XmlElement
    private final String local;
    
    @XmlElement
    private final int idFamilia;
    
    @XmlElement
    private final int idTipoEvento;
    
    @XmlElement
    private final int idUsuario;

    public Evento(int idEvento, String nome, String descricao, Date dataCriacao, String local, int idFamilia, int idTipoEvento, int idUsuario) {
        this.idEvento = idEvento;
        this.nome = nome;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.local = local;
        this.idFamilia = idFamilia;
        this.idTipoEvento = idTipoEvento;
        this.idUsuario = idUsuario;
    }

    public Evento() {
        this.idEvento = 0;
        this.nome = null;
        this.descricao = null;
        this.dataCriacao = null;
        this.local = null;
        this.idFamilia = 0;
        this.idTipoEvento = 0;
        this.idUsuario = 0;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public String getLocal() {
        return local;
    }

    public int getIdFamilia() {
        return idFamilia;
    }

    public int getIdTipoEvento() {
        return idTipoEvento;
    }

    public int getIdUsuario() {
        return idUsuario;
    }
    
    
}
