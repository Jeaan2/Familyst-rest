/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.resources;

import database.DAO.FamiliaDAO;
import api.model.Familia;
import api.model.Galeria;
import database.DAO.GaleriaDAO;
import interfaces.api.resources.IGaleriasResources;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author jdfid
 */
@Path("galerias")
public class GaleriasResources implements IGaleriasResources{

    /**
     * Creates a new instance of GaleriasResources
     */
    public GaleriasResources() {
    }

    @GET
    @Path("{idGaleria}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public Response buscarGaleria(@PathParam("idGaleria") Integer idGaleria) {
        try {
             Galeria galeria = new GaleriaDAO().buscarGaleria(idGaleria);
            return Response.status(Response.Status.OK).entity(galeria).build();               
        } catch (Exception e) {
            switch (e.getMessage())
            {
                //CODE 404
                case "Galeria nao encontrada.":
                    return Response.status(Response.Status.NOT_FOUND).build();
                //CODE 500
                default:
                    return Response.serverError().entity(e.getMessage()).build();
            }
        }
    }   
}
