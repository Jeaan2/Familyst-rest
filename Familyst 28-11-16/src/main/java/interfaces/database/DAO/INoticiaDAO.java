/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.database.DAO;

import api.model.Noticia;
import java.util.List;

/**
 *
 * @author jdfid
 */
public interface INoticiaDAO {
        
    public List<Noticia> listarNoticiasFamilia(int idFamilia) throws Exception;
    
    public int inserirNoticia(Noticia noticia) throws Exception;
        
    public void editarNoticia(int idNoticia, Noticia noticia) throws Exception;
    
    public void removerNoticia(int idNoticia) throws Exception;
}
