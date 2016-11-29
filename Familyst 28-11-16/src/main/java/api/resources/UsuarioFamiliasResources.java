/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.resources;

import api.model.Familia;
import database.DAO.FamiliaDAO;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import interfaces.api.resources.IUsuarioFamiliasResources;

/**
 *
 * @author jdfid
 */
@Path("usuarios/{idUsuario}/familias")
public class UsuarioFamiliasResources implements IUsuarioFamiliasResources {
 
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON}) 
    @Override
    public Response buscarFamiliasUsuario(@PathParam("idUsuario") Integer idUsuario) {
        try {            
            List<Familia> familias = new FamiliaDAO().listarFamiliasUsuario(idUsuario);
            GenericEntity<List<Familia>> familiasResponse = new GenericEntity<List<Familia>>(familias){};
            return Response.status(Response.Status.OK).entity(familiasResponse).build();                
        } catch (Exception e) {
            switch (e.getMessage())
            {
                //CODE 404
                case "Usuario nao encontrado.":
                    return Response.status(Response.Status.NOT_FOUND).build();
                //CODE 500
                default:
                    return Response.serverError().entity(e.getMessage()).build();
            }
        }
    }
}
