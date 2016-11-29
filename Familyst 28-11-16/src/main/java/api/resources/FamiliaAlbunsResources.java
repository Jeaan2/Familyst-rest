/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.resources;

import api.model.Album;
import database.DAO.AlbumDAO;
import interfaces.api.resources.IFamiliaAlbunsResources;
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
@Path("familias/{idFamilia}/albuns")
public class FamiliaAlbunsResources implements IFamiliaAlbunsResources {
 
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON}) 
    @Override
    public Response buscarAlbunsFamilia(@PathParam("idFamilia") Integer idFamilia) {
        try {            
            List<Album> albuns = new AlbumDAO().listarAlbunsFamilia(idFamilia);
            GenericEntity<List<Album>> albunsResponse = new GenericEntity<List<Album>>(albuns){};
            return Response.status(Response.Status.OK).entity(albunsResponse).build();                
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
