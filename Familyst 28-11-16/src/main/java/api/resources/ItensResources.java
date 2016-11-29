/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.resources;

import api.model.Item;
import database.DAO.ItemDAO;
import interfaces.api.resources.IItensResources;
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
@Path("itens")
public class ItensResources implements IItensResources{

    /**
     * Creates a new instance of ItensResources
     */
    public ItensResources() {
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public Response inserirItem(Item item){
        try {
            int idItem = new ItemDAO().inserirItem(item);
            URI insertUri = new URI("/" + idItem);
            
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
    @Path("{idItem}")
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Override
    public Response editarItem(@PathParam("idItem") Integer idItem, Item item) {     
        try {
            new ItemDAO().editarItem(idItem, item);    
            return Response.status(Response.Status.OK).build();        
        } catch (Exception e) {
            switch (e.getMessage())
            {
                //CODE 404
                case "Item nao encontrado.":
                    return Response.status(Response.Status.NOT_FOUND).build();
                //CODE 204
                case "Item nao alterado.":
                    return Response.status(Response.Status.NO_CONTENT).build();
                //CODE 500
                default:
                    return Response.serverError().entity(e.getMessage()).build();
            }
        }
    }
        
    @DELETE
    @Path("{idItem}")
    @Override
    public Response removerItem(@PathParam("idItem") Integer idItem) {
        ItemDAO itemDAO = new ItemDAO();
        try {
            itemDAO.removerItem(idItem);     
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
             switch (e.getMessage())
            {
                //CODE 404
                case "Item nao encontrado.":
                    return Response.status(Response.Status.NOT_FOUND).build();
                //CODE 500
                default:
                    return Response.serverError().entity(e.getMessage()).build();
            }
        }
    }
    
}
