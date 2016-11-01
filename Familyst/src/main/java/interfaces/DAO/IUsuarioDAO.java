/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.DAO;

/**
 *
 * @author jdfid
 */
public interface IUsuarioDAO {
        
    public boolean autenticarUsuario(String nome, String senha);
}
