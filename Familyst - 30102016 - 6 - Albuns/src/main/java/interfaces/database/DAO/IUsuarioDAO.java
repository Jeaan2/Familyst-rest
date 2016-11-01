/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.database.DAO;

import api.model.Usuario;

/**
 *
 * @author jdfid
 */
public interface IUsuarioDAO {
        
    public int autenticarUsuario(String nome, String senha) throws Exception;
    public Usuario buscarUsuario(int idUsuario) throws Exception;
    public int inserirUsuario(Usuario usuario) throws Exception;
    public void editarUsuario(int idUsuario, Usuario usuario) throws Exception;
    public void removerUsuario(int idUsuario) throws Exception;
}
