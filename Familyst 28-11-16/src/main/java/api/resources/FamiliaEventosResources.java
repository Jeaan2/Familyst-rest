/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.resources;

import api.model.Evento;
import database.DAO.EventoDAO;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import interfaces.api.resources.IFamiliaEventosResources;

/**
 *
 * @author jdfid
 */
@Path("familias/{idFamilia}/eventos")
public class FamiliaEventosResources implements IFamiliaEventosResources {
 
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON}) 
    @Override
    public Response buscarEventosFamilia(@PathParam("idFamilia") Integer idFamilia) {
        try {            
            List<Evento> eventos = new EventoDAO().listarEventosFamilia(idFamilia);
            GenericEntity<List<Evento>> eventosResponse = new GenericEntity<List<Evento>>(eventos){};
            return Response.status(Response.Status.OK).entity(eventosResponse).build();                
        } catch (Exception e) {
            switch (e.getMessage())
            {
                //CODE 404
                case "Familia nao encontrada.":
                    return Response.status(Response.Status.NOT_FOUND).build();
                //CODE 500
                default:
                    return Response.serverError().entity(e.getMessage()).build();
            }
        }
    }
}
