/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.resources;

import api.model.Usuario;
import database.DAO.UsuarioDAO;
import interfaces.api.resources.IUsuariosResources;
import java.net.URI;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author jdfid
 */
@Path("usuarios")
public class UsuariosResources implements IUsuariosResources {
    
    @GET
    @Path("{idUsuario}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public Response buscarUsuario(@PathParam("idUsuario") int idUsuario) {
        try {            
            Usuario usuario = new UsuarioDAO().buscarUsuario(idUsuario);           
            return Response.status(Response.Status.OK).entity(usuario).build();               
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
    
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public Response inserirUsuario(Usuario usuario){
        try {
            int idUsuario = new UsuarioDAO().inserirUsuario(usuario);
            URI insertUri = new URI("/" + idUsuario);
            
            //CODE 201
            return Response.created(insertUri).build();
        } catch (Exception e) {
            switch (e.getMessage())
            {
                //CODE 409
                case "Recurso já existente na base.":
                    return Response.status(Response.Status.CONFLICT).entity("Email já cadastrado.").build();
                //CODE 500
                default:
                    return Response.serverError().entity(e.getMessage()).build();
            }
        }       
    }
    
    @PUT
    @Path("{idUsuario}")
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Override
    public Response editarUsuario(@PathParam("idUsuario") Integer idUsuario, Usuario usuario) {     
        try {
            new UsuarioDAO().editarUsuario(idUsuario, usuario);    
            return Response.status(Response.Status.OK).build();        
        } catch (Exception e) {
            switch (e.getMessage())
            {
                //CODE 404
                case "Usuario nao encontrado.":
                    return Response.status(Response.Status.NOT_FOUND).build();
                //CODE 204
                case "Usuario nao alterado.":
                    return Response.status(Response.Status.NO_CONTENT).build();
                //CODE 500
                default:
                    return Response.serverError().entity(e.getMessage()).build();
            }
        }
    }
        
    @DELETE
    @Path("{idUsuario}")
    @Override
    public Response removerUsuario(@PathParam("idUsuario") Integer idUsuario) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        try {
            usuarioDAO.removerUsuario(idUsuario);     
            return Response.status(Response.Status.OK).build();
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
