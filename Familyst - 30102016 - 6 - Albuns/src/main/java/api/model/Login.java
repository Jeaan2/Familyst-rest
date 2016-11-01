/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.model;

/**
 *
 * @author jdfid
 */
public class Login {
    private String usuario;
    private String senha;

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }
    
    public Login(String usuario, String senha)
    {
        this.usuario = usuario;
        this.senha = senha;
    }
}
