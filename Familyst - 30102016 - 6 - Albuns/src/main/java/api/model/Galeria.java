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
public class Galeria {

    public Galeria(int idGaleria, Date dataCriacao) {
        this.idGaleria = idGaleria;
        this.dataCriacao = dataCriacao;
    }
    
    public Galeria(){
        this.idGaleria = 0;
        this.dataCriacao = null;
    }  
    
    @XmlElement
    private final int idGaleria;
    
    
    @XmlElement
    private final Date dataCriacao;
    
      
    public int getIdGaleria() {
        return idGaleria;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }    
}
