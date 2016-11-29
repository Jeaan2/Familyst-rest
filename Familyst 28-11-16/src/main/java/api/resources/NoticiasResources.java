/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.resources;

import api.model.Noticia;
import database.DAO.NoticiaDAO;
import interfaces.api.resources.INoticiasResources;
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
@Path("noticias")
public class NoticiasResources implements INoticiasResources{

    /**
     * Creates a new instance of EventosResources
     */
    public NoticiasResources() {
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public Response inserirNoticia(Noticia noticia){
        try {
            int idNoticia = new NoticiaDAO().inserirNoticia(noticia);
            URI insertUri = new URI("/" + idNoticia);
            
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
    @Path("{idNoticia}")
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Override
    public Response editarNoticia(@PathParam("idNoticia") Integer idNoticia, Noticia noticia) {     
        try {
            new NoticiaDAO().editarNoticia(idNoticia, noticia);    
            return Response.status(Response.Status.OK).build();        
        } catch (Exception e) {
            switch (e.getMessage())
            {
                //CODE 404
                case "Noticia nao encontrada.":
                    return Response.status(Response.Status.NOT_FOUND).build();
                //CODE 204
                case "Noticia nao alterada.":
                    return Response.status(Response.Status.NO_CONTENT).build();
                //CODE 500
                default:
                    return Response.serverError().entity(e.getMessage()).build();
            }
        }
    }
        
    @DELETE
    @Path("{idNoticia}")
    @Override
    public Response removerNoticia(@PathParam("idNoticia") Integer idNoticia) {
        NoticiaDAO noticiaDAO = new NoticiaDAO();
        try {
            noticiaDAO.removerNoticia(idNoticia);     
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
             switch (e.getMessage())
            {
                //CODE 404
                case "Noticia nao encontrada.":
                    return Response.status(Response.Status.NOT_FOUND).build();
                //CODE 500
                default:
                    return Response.serverError().entity(e.getMessage()).build();
            }
        }
    }
    
}
