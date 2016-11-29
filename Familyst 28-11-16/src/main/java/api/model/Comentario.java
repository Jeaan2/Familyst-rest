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
public class Comentario {
    
     @XmlElement
    private final int idComentario;
    
    @XmlElement
    private final String descricao;
    
    @XmlElement
    private final Date dataCriacao;
    
    @XmlElement
    private final int idNoticia;
    
    @XmlElement
    private final int idEvento;
    
    @XmlElement
    private final int idUsuario;

    public Comentario(int idComentario, String descricao, Date dataCriacao, int idNoticia, int idEvento, int idUsuario) {
        this.idComentario = idComentario;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.idNoticia = idNoticia;
        this.idEvento = idEvento;
        this.idUsuario = idUsuario;
    }

    public Comentario() {
        this.idComentario = 0;
        this.descricao = null;
        this.dataCriacao = null;
        this.idNoticia = 0;
        this.idEvento = 0;
        this.idUsuario = 0;
    }

    public int getIdEvento() {
        return idComentario;
    }

    public String getDescricao() {
        return descricao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public int getIdFamilia() {
        return idNoticia;
    }

    public int getIdTipoEvento() {
        return idEvento;
    }

    public int getIdUsuario() {
        return idUsuario;
    }
}
