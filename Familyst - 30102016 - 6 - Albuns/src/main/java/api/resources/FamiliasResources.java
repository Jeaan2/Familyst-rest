/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.resources;

import database.DAO.FamiliaDAO;
import api.model.Familia;
import interfaces.api.resources.IFamiliasResources;
import java.net.URI;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
@Path("familias")
public class FamiliasResources implements IFamiliasResources{

    /**
     * Creates a new instance of FamiliasResources
     */
    public FamiliasResources() {
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public Response findAll() {
        try {
            List<Familia> familias = new FamiliaDAO().listarFamilias();
            GenericEntity<List<Familia>> familiasResponse = new GenericEntity<List<Familia>>(familias){};
            return Response.status(Response.Status.OK).entity(familiasResponse).build();    
        } catch (Exception e) {
            //CODE 500
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("{idFamilia}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public Response buscarFamilia(@PathParam("idFamilia") Integer idFamilia) {
        try {
             Familia familia = new FamiliaDAO().buscarFamilia(idFamilia);
            return Response.status(Response.Status.OK).entity(familia).build();               
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
    
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public Response inserirFamilia(Familia familia){
        try {
            int idFamilia = new FamiliaDAO().inserirFamilia(familia);
            URI insertUri = new URI("/" + idFamilia);
            
            //CODE 201
            return Response.created(insertUri).build();
        } catch (Exception e) {
            switch (e.getMessage())
            {
                //CODE 409
                case "Recurso já existente na base.":
                    return Response.status(Response.Status.CONFLICT).build();
                //CODE 500
                default:
                    return Response.serverError().entity(e.getMessage()).build();
            }
        }        
    }
    
    @PUT
    @Path("{idFamilia}")
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Override
    public Response editarFamilia(@PathParam("idFamilia") Integer idFamilia, Familia familia) {     
        try {
            new FamiliaDAO().editarFamilia(idFamilia, familia);    
            return Response.status(Response.Status.OK).build();        
        } catch (Exception e) {
            switch (e.getMessage())
            {
                //CODE 404
                case "Familia nao encontrada.":
                    return Response.status(Response.Status.NOT_FOUND).build();
                //CODE 204
                case "Familia nao alterada.":
                    return Response.status(Response.Status.NO_CONTENT).build();
                //CODE 500
                default:
                    return Response.serverError().entity(e.getMessage()).build();
            }
        }
    }
        
    @DELETE
    @Path("{idFamilia}")
    @Override
    public Response removerFamilia(@PathParam("idFamilia") Integer idFamilia) {
        FamiliaDAO familiaDAO = new FamiliaDAO();
        try {
            familiaDAO.removerFamilia(idFamilia);     
            return Response.status(Response.Status.OK).build();
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
