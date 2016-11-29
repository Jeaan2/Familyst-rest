/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.resources;

import api.model.Evento;
import database.DAO.EventoDAO;
import interfaces.api.resources.IEventosResources;
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
@Path("eventos")
public class EventosResources implements IEventosResources{

    /**
     * Creates a new instance of EventosResources
     */
    public EventosResources() {
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public Response inserirEvento(Evento evento){
        try {
            int idEvento = new EventoDAO().inserirEvento(evento);
            URI insertUri = new URI("/" + idEvento);
            
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
    @Path("{idEvento}")
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Override
    public Response editarEvento(@PathParam("idEvento") Integer idEvento, Evento evento) {     
        try {
            new EventoDAO().editarEvento(idEvento, evento);    
            return Response.status(Response.Status.OK).build();        
        } catch (Exception e) {
            switch (e.getMessage())
            {
                //CODE 404
                case "Evento nao encontrado.":
                    return Response.status(Response.Status.NOT_FOUND).build();
                //CODE 204
                case "Evento nao alterado.":
                    return Response.status(Response.Status.NO_CONTENT).build();
                //CODE 500
                default:
                    return Response.serverError().entity(e.getMessage()).build();
            }
        }
    }
        
    @DELETE
    @Path("{idEvento}")
    @Override
    public Response removerEvento(@PathParam("idEvento") Integer idEvento) {
        EventoDAO eventoDAO = new EventoDAO();
        try {
            eventoDAO.removerEvento(idEvento);     
            return Response.status(Response.Status.OK).build();
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
