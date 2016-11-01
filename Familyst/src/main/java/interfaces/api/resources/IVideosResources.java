/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.api.resources;

import api.model.Video;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

public interface IVideosResources {

    public Response listarVideosGaleria(@PathParam("idGaleria") Integer idGaleria);
    public Response inserirVideo(Video video);
    public Response editarVideo(@PathParam("idVideo") Integer idVideo, Video video);
    public Response removerVideo(@PathParam("idVideo") Integer idVideo);
    
}
