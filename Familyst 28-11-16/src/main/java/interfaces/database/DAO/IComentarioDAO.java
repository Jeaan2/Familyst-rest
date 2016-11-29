/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.database.DAO;

import api.model.Comentario;
import java.util.List;

/**
 *
 * @author jdfid
 */
public interface IComentarioDAO {
        
    public List<Comentario> listarComentariosEvento(int idEvento) throws Exception;
    
    public int inserirComentario(Comentario comentario) throws Exception;
        
    public void editarComentario(int idComentario, Comentario comentario) throws Exception;
    
    public void removerComentario(int idComentario) throws Exception;
}
