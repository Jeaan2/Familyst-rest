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

    public AccessTokenRequest(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }
    
     public AccessTokenRequest() {
        this.email = null;
        this.senha = null;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
    
    @XmlElement
    private String email;
    @XmlElement
    private String senha;
}
