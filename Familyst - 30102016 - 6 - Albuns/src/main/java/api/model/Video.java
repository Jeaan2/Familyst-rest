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
public class Video {

    public Video(int idVideo, String descricao, Date dataCriacao, String link, int idGaleria) {
        this.idVideo = idVideo;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.link = link;
        this.idGaleria = idGaleria;
    }

    public Video () {
        this.idVideo = 0;
        this.descricao = null;
        this.dataCriacao = null;
        this.link = null;
        this.idGaleria = 0;
    }
    
    @XmlElement
    private final int idVideo;   
    
    @XmlElement
    private final String descricao;
    
    @XmlElement
    private final Date dataCriacao;
    
    @XmlElement
    private final String link;
    
    @XmlElement
    private final int idGaleria;
    
    public int getIdVideo() {
        return idVideo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public String getLink() {
        return link;
    }

    public int getIdGaleria() {
        return idGaleria;
    }  
}
