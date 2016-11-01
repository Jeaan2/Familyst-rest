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
public class AccessTokenResponse {
    
    @XmlElement
    private int idUsuario;
    @XmlElement
    private String accessToken;

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public AccessTokenResponse(int idUsuario, String accessToken) {
        this.idUsuario = idUsuario;
        this.accessToken = accessToken;
    }
        
    public AccessTokenResponse() {
        this.idUsuario = 0;
        this.accessToken = null;
    }
}
