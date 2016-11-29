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
public class Noticia {
    
    @XmlElement
    private final int idNoticia;
        
    @XmlElement
    private final String descricao;
    
    @XmlElement
    private final Date dataCriacao;
    
    @XmlElement
    private final int idFamilia;
    
    @XmlElement
    private final int idUsuario;

    public Noticia(int idNoticia, String descricao, Date dataCriacao, int idFamilia, int idUsuario) {
        this.idNoticia = idNoticia;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.idFamilia = idFamilia;
        this.idUsuario = idUsuario;
    }

    public Noticia() {
        this.idNoticia = 0;
        this.descricao = null;
        this.dataCriacao = null;
        this.idFamilia = 0;
        this.idUsuario = 0;
    }

    public int getIdNoticia() {
        return idNoticia;
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

    public int getIdUsuario() {
        return idUsuario;
    }
    
    
}
