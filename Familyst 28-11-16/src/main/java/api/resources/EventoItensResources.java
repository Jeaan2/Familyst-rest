/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.resources;

import api.model.Item;
import database.DAO.ItemDAO;
import interfaces.api.resources.IEventoItensResources;
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
@Path("eventos/{idEvento}/itens")
public class EventoItensResources implements IEventoItensResources {
 
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON}) 
    @Override
    public Response buscarItensEvento(@PathParam("idEvento") Integer idEvento) {
        try {            
            List<Item> itens = new ItemDAO().listarItensEvento(idEvento);
            GenericEntity<List<Item>> itensResponse = new GenericEntity<List<Item>>(itens){};
            return Response.status(Response.Status.OK).entity(itensResponse).build();                
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
