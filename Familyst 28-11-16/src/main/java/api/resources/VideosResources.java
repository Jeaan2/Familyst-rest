/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.resources;

import api.model.Video;
import database.DAO.VideoDAO;
import interfaces.api.resources.IVideosResources;
import java.net.URI;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author jdfid
 */
@Path("videos")
public class VideosResources implements IVideosResources{

    /**
     * Creates a new instance of VideosResources
     */
    public VideosResources() {
    }

    @GET
    @Path("{idGaleria}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public Response listarVideosGaleria(@PathParam("idGaleria") Integer idGaleria) {
        try {            
            List<Video> videos = new VideoDAO().listarVideosGaleria(idGaleria);
            GenericEntity<List<Video>> videosResponse = new GenericEntity<List<Video>>(videos){};
            return Response.status(Response.Status.OK).entity(videosResponse).build();    
        } catch (Exception e) {
            switch (e.getMessage())
            {
                //CODE 404
                case "Galeria nao encontrada.":
                    return Response.status(Response.Status.NOT_FOUND).build();
                //CODE 500
                default:
                    return Response.serverError().entity(e.getMessage()).build();
            }
        }
    }    
    
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public Response inserirVideo(Video video){
        try {
            int idVideo = new VideoDAO().inserirVideo(video);
            URI insertUri = new URI("/" + idVideo);
            
            //CODE 201
            return Response.created(insertUri).build();
        } catch (Exception e) {
            switch (e.getMessage())
            {
                //CODE 500
                default:
                    return Response.serverError().entity(e.getMessage()).build();
            }
        }        
    }
    
    @PUT
    @Path("{idVideo}")
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Override
    public Response editarVideo(@PathParam("idVideo") Integer idVideo, Video video) {     
        try {
            new VideoDAO().editarVideo(idVideo, video);    
            return Response.status(Response.Status.OK).build();        
        } catch (Exception e) {
            switch (e.getMessage())
            {
                //CODE 404
                case "Video nao encontrado.":
                    return Response.status(Response.Status.NOT_FOUND).build();
                //CODE 204
                case "Video nao alterado.":
                    return Response.status(Response.Status.NO_CONTENT).build();
                //CODE 500
                default:
                    return Response.serverError().entity(e.getMessage()).build();
            }
        }
    }
        
    @DELETE
    @Path("{idVideo}")
    @Override
    public Response removerVideo(@PathParam("idVideo") Integer idVideo) {
        VideoDAO videoDAO = new VideoDAO();
        try {
            videoDAO.removerVideo(idVideo);     
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
             switch (e.getMessage())
            {
                //CODE 404
                case "Video nao encontrado.":
                    return Response.status(Response.Status.NOT_FOUND).build();
                //CODE 500
                default:
                    return Response.serverError().entity(e.getMessage()).build();
            }
        }
    }
    
}
