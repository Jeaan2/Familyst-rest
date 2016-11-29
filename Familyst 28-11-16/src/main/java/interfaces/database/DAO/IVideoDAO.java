/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.database.DAO;

import api.model.Video;
import java.util.List;

/**
 *
 * @author jdfid
 */
public interface IVideoDAO {
        
    public List<Video> listarVideosGaleria(int idGaleria) throws Exception;
    
    public int inserirVideo(Video video) throws Exception;
        
    public void editarVideo(int idVideo, Video video) throws Exception;
    
    public void removerVideo(int idVideo) throws Exception;
}
