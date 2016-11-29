/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.resources;

import api.model.Noticia;
import database.DAO.NoticiaDAO;
import interfaces.api.resources.IFamiliaNoticiasResources;
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
@Path("familias/{idFamilia}/noticias")
public class FamiliaNoticiasResources implements IFamiliaNoticiasResources {
 
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON}) 
    @Override
    public Response buscarNoticiasFamilia(@PathParam("idFamilia") Integer idFamilia) {
        try {            
            List<Noticia> noticias = new NoticiaDAO().listarNoticiasFamilia(idFamilia);
            GenericEntity<List<Noticia>> noticiasResponse = new GenericEntity<List<Noticia>>(noticias){};
            return Response.status(Response.Status.OK).entity(noticiasResponse).build();                
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
