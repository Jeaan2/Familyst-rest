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
public class Familia {

    public Familia(int idFamilia, String nome, String descricao, Date dataCriacao, String local, int idGaleria, int idArquivo) {
        this.idFamilia = idFamilia;
        this.nome = nome;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.local = local;
        this.idGaleria = idGaleria;
        this.idArquivo = idArquivo;
    }
    
    public Familia(){
        this.idFamilia = 0;
        this.nome = null;
        this.descricao = null;
        this.dataCriacao = null;
        this.local = null;
        this.idGaleria = 0;
        this.idArquivo = 0;
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
    
    @XmlElement
    private final int idGaleria;

    @XmlElement
    private final int idArquivo;
    
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
    
    public int getIdGaleria() {
        return idGaleria;
    }

    public int getIdArquivo() {
        return idArquivo;
    }
}
