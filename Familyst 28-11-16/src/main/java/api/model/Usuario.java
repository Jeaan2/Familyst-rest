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
public class Usuario {

    public Usuario(int idUsuario, String nome, String senha, String idFacebook, String local, String email, Date dataCriacao) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.senha = senha;
        this.idFacebook = idFacebook;
        this.local = local;
        this.email = email;
        this.dataCriacao = dataCriacao;
    }
    
    public Usuario(){
        this.idUsuario = 0;
        this.nome = null;
        this.senha = null;
        this.idFacebook = null;
        this.local = null;
        this.email = null;
        this.dataCriacao = null;
    }    

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public String getIdFacebook() {
        return idFacebook;
    }

    public String getLocal() {
        return local;
    }

    public String getEmail() {
        return email;
    }
    
    public Date getDataCriacao() {
        return dataCriacao;
    }
    
    @XmlElement
    private final int idUsuario;
    
    @XmlElement
    private final String nome;
    
    @XmlElement
    private final String senha;
    
    @XmlElement
    private final String idFacebook;
    
    @XmlElement
    private final String local;
    
    @XmlElement
    private final String email;        
    
    @XmlElement
    private final Date dataCriacao;
}
