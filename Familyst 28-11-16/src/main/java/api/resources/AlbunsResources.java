/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.resources;

import api.model.Album;
import database.DAO.AlbumDAO;
import interfaces.api.resources.IAlbunsResources;
import java.net.URI;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author jdfid
 */
@Path("albuns")
public class AlbunsResources implements IAlbunsResources{

    /**
     * Creates a new instance of AlbunsResources
     */
    public AlbunsResources() {
    } 
    
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public Response inserirAlbum(Album album){
        try {
            int idAlbum = new AlbumDAO().inserirAlbum(album);
            URI insertUri = new URI("/" + idAlbum);
            
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
    @Path("{idAlbum}")
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Override
    public Response editarAlbum(@PathParam("idAlbum") Integer idAlbum, Album album) {     
        try {
            new AlbumDAO().editarAlbum(idAlbum, album);    
            return Response.status(Response.Status.OK).build();        
        } catch (Exception e) {
            switch (e.getMessage())
            {
                //CODE 404
                case "Album nao encontrado.":
                    return Response.status(Response.Status.NOT_FOUND).build();
                //CODE 204
                case "Album nao alterado.":
                    return Response.status(Response.Status.NO_CONTENT).build();
                //CODE 500
                default:
                    return Response.serverError().entity(e.getMessage()).build();
            }
        }
    }
        
    @DELETE
    @Path("{idAlbum}")
    @Override
    public Response removerAlbum(@PathParam("idAlbum") Integer idAlbum) {
        AlbumDAO albumDAO = new AlbumDAO();
        try {
            albumDAO.removerAlbum(idAlbum);     
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
             switch (e.getMessage())
            {
                //CODE 404
                case "Album nao encontrado.":
                    return Response.status(Response.Status.NOT_FOUND).build();
                //CODE 500
                default:
                    return Response.serverError().entity(e.getMessage()).build();
            }
        }
    }
    
}
