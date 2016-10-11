/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Resources;

import DAO.FamiliaDAO;
import Model.Familia;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author jdfid
 */
@Path("familias")
public class FamiliasResources {

    @Context
    private UriInfo context; //verificar se precisa

    /**
     * Creates a new instance of FamiliasResources
     */
    public FamiliasResources() {
    }

    @RolesAllowed("ADMIN")
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Familia> findAll() {
        return new FamiliaDAO().listarFamilias();
    }

    @GET
    @Path("{idFamilia}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Familia buscarFamilia(@PathParam("idFamilia") Integer idFamilia) {
        return new FamiliaDAO().buscarFamilia(idFamilia);
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response inserirFamilia(Familia familia) throws URISyntaxException {
        URI insertUri = new URI("inserirFamilia");
        if (new FamiliaDAO().inserirFamilia(familia))
            return Response.created(insertUri).build();
        else
            return Response.noContent().build();
    }
    
    @PUT
    @Path("{idFamilia}")
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public void editarFamilia(@PathParam("idFamilia") Integer idFamilia, Familia familia) {     
        new FamiliaDAO().editarFamilia(idFamilia, familia);
    }
        
    @DELETE
    @Path("{idFamilia}")
    public void removerFamilia(@PathParam("idFamilia") Integer idFamilia) {
        FamiliaDAO familiaDAO = new FamiliaDAO();
        familiaDAO.removerFamilia(familiaDAO.buscarFamilia(idFamilia));
    }
    
}
