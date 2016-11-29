/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.resources;

import api.model.Arquivo;
import database.DAO.ArquivoDAO;
import interfaces.api.resources.IArquivosResources;
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
@Path("arquivos")
public class ArquivosResources implements IArquivosResources{

    /**
     * Creates a new instance of ArquivosResources
     */
    public ArquivosResources() {
    }

    @GET
    @Path("{idArquivo}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public Response buscarArquivo(@PathParam("idArquivo") Integer idArquivo) {
        try {
             Arquivo arquivo = new ArquivoDAO().buscarArquivo(idArquivo);
            return Response.status(Response.Status.OK).entity(arquivo).build();               
        } catch (Exception e) {
            switch (e.getMessage())
            {
                //CODE 404
                case "Arquivo nao encontrado.":
                    return Response.status(Response.Status.NOT_FOUND).build();
                //CODE 500
                default:
                    return Response.serverError().entity(e.getMessage()).build();
            }
        }
    }   
}
