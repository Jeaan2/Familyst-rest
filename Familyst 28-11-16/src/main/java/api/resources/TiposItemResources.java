/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.resources;

import api.model.TipoItem;
import database.DAO.TipoItemDAO;
import interfaces.api.resources.ITiposItemResources;
import java.util.List;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
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
@Path("tiposItem")
public class TiposItemResources implements ITiposItemResources{

    /**
     * Creates a new instance of FamiliasResources
     */
    public TiposItemResources() {
    }

    @GET
    @Path("{idTipoItem}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public Response buscarTipoItem(@PathParam("idTipoItem") int idTipoItem) {
        try {            
            TipoItem tipoItem = new TipoItemDAO().buscarTipoItem(idTipoItem);           
            return Response.status(Response.Status.OK).entity(tipoItem).build();               
        } catch (Exception e) {
            switch (e.getMessage())
            {
                //CODE 404
                case "TipoItem nao encontrado.":
                    return Response.status(Response.Status.NOT_FOUND).build();
                //CODE 500
                default:
                    return Response.serverError().entity(e.getMessage()).build();
            }
        }
    }  
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public Response listarTiposItem() {
        try {
            List<TipoItem> tiposItem = new TipoItemDAO().listarTiposItem();
            GenericEntity<List<TipoItem>> tiposItemResponse = new GenericEntity<List<TipoItem>>(tiposItem){};
            return Response.status(Response.Status.OK).entity(tiposItemResponse).build();    
        } catch (Exception e) {
            //CODE 500
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
