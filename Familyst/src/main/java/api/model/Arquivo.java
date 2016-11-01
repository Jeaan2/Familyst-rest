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
public class Arquivo {

    public Arquivo(int idArquivo, Date dataCriacao) {
        this.idArquivo = idArquivo;
        this.dataCriacao = dataCriacao;
    }
    
    public Arquivo(){
        this.idArquivo = 0;
        this.dataCriacao = null;
    }  
    
    @XmlElement
    private final int idArquivo;
    
    
    @XmlElement
    private final Date dataCriacao;
    
      
    public int getIdArquivo() {
        return idArquivo;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }    
}
