/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.database.DAO;

import api.model.Album;
import java.util.List;

/**
 *
 * @author jdfid
 */
public interface IAlbumDAO {
        
    public List<Album> listarAlbunsFamilia(int idFamilia) throws Exception;
    
    public int inserirAlbum(Album album) throws Exception;
        
    public void editarAlbum(int idAlbum, Album album) throws Exception;
    
    public void removerAlbum(int idAlbum) throws Exception;
}
