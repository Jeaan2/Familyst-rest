/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.resources;

import api.model.Comentario;
import database.DAO.ComentarioDAO;
import interfaces.api.resources.IComentariosResources;
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
@Path("comentarios")
public class ComentariosResources implements IComentariosResources{

    /**
     * Creates a new instance of ComentariosResources
     */
    public ComentariosResources() {
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public Response inserirComentario(Comentario comentario){
        try {
            int idComentario = new ComentarioDAO().inserirComentario(comentario);
            URI insertUri = new URI("/" + idComentario);
            
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
    @Path("{idComentario}")
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Override
    public Response editarComentario(@PathParam("idComentario") Integer idComentario, Comentario comentario) {     
        try {
            new ComentarioDAO().editarComentario(idComentario, comentario);    
            return Response.status(Response.Status.OK).build();        
        } catch (Exception e) {
            switch (e.getMessage())
            {
                //CODE 404
                case "Comentario nao encontrado.":
                    return Response.status(Response.Status.NOT_FOUND).build();
                //CODE 204
                case "Comentario nao alterado.":
                    return Response.status(Response.Status.NO_CONTENT).build();
                //CODE 500
                default:
                    return Response.serverError().entity(e.getMessage()).build();
            }
        }
    }
        
    @DELETE
    @Path("{idComentario}")
    @Override
    public Response removerComentario(@PathParam("idComentario") Integer idComentario) {
        ComentarioDAO comentarioDAO = new ComentarioDAO();
        try {
            comentarioDAO.removerComentario(idComentario);     
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
             switch (e.getMessage())
            {
                //CODE 404
                case "Comentario nao encontrado.":
                    return Response.status(Response.Status.NOT_FOUND).build();
                //CODE 500
                default:
                    return Response.serverError().entity(e.getMessage()).build();
            }
        }
    }
    
}
