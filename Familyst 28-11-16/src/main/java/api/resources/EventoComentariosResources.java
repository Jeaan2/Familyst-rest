/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.resources;

import api.model.Comentario;
import database.DAO.ComentarioDAO;
import interfaces.api.resources.IEventoComentariosResources;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author jdfid
 */
@Path("eventos/{idEvento}/comentarios")
public class EventoComentariosResources implements IEventoComentariosResources {
 
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON}) 
    @Override
    public Response buscarComentariosEvento(@PathParam("idEvento") Integer idEvento) {
        try {            
            List<Comentario> comentarios = new ComentarioDAO().listarComentariosEvento(idEvento);
            GenericEntity<List<Comentario>> comentariosResponse = new GenericEntity<List<Comentario>>(comentarios){};
            return Response.status(Response.Status.OK).entity(comentariosResponse).build();                
        } catch (Exception e) {
            switch (e.getMessage())
            {
                //CODE 404
                case "Evento nao encontrado.":
                    return Response.status(Response.Status.NOT_FOUND).build();
                //CODE 500
                default:
                    return Response.serverError().entity(e.getMessage()).build();
            }
        }
    }
}
