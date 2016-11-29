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
public class Album {
    
    @XmlElement
    private final int idAlbum;
    
    @XmlElement
    private final String nome;
    
    @XmlElement
    private final String descricao;
    
    @XmlElement
    private final Date dataCriacao;
    
    @XmlElement
    private final int idFamilia;

    public Album(int idAlbum, String nome, String descricao, Date dataCriacao, int idFamilia) {
        this.idAlbum = idAlbum;
        this.nome = nome;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.idFamilia = idFamilia;
    }

    public Album() {
        this.idAlbum = 0;
        this.nome = null;
        this.descricao = null;
        this.dataCriacao = null;
        this.idFamilia = 0;
    }

    public int getIdAlbum() {
        return idAlbum;
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

    public int getIdFamilia() {
        return idFamilia;
    }
    
    
}
