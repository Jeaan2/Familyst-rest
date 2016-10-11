/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jdfid
 */
@XmlRootElement
public class Familia {

    public Familia(int idFamilia, String nome, String descricao, Date dataCriacao, String local) {
        this.idFamilia = idFamilia;
        this.nome = nome;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.local = local;
    }
    
    public Familia(){
        this.idFamilia = 0;
        this.nome = null;
        this.descricao = null;
        this.dataCriacao = null;
        this.local = null;
    }  
    
    @XmlElement
    private final int idFamilia;
    
    @XmlElement
    private final String nome;
    
    @XmlElement
    private final String descricao;
    
    @XmlElement
    private final Date dataCriacao;
    
    @XmlElement
    private final String local;
    
    public int getIdFamilia() {
        return idFamilia;
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
}
