/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.autenticacao.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jdfid
 */
@XmlRootElement
public class AccessTokenRequest {

    public AccessTokenRequest(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }
    
     public AccessTokenRequest() {
        this.usuario = null;
        this.senha = null;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }
    
    @XmlElement
    private String usuario;
    @XmlElement
    private String senha;
}
