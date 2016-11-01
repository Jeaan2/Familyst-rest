/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.resources;

import api.model.TipoEvento;
import database.DAO.TipoEventoDAO;
import interfaces.api.resources.ITiposEventoResources;
import java.util.List;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author jdfid
 */
@Path("tiposEvento")
public class TiposEventoResources implements ITiposEventoResources{

    /**
     * Creates a new instance of FamiliasResources
     */
    public TiposEventoResources() {
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public Response listarTiposEvento() {
        try {
            List<TipoEvento> tiposEvento = new TipoEventoDAO().listarTiposEvento();
            GenericEntity<List<TipoEvento>> tiposEventoResponse = new GenericEntity<List<TipoEvento>>(tiposEvento){};
            return Response.status(Response.Status.OK).entity(tiposEventoResponse).build();    
        } catch (Exception e) {
            //CODE 500
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
