/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.autenticacao.resources;

import static api.autenticacao.AutenticadorRest.AUTHENTICATION_KEY;
import api.autenticacao.model.AccessTokenRequest;
import api.autenticacao.model.AccessTokenResponse;
import com.sun.jersey.core.util.Base64;
import database.DAO.UsuarioDAO;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author jdfid
 */
@Path("accesstokens")
public class AccessTokensResource {
    
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response buscarAccessToken(AccessTokenRequest accessTokenRequest)
    {
        try {           
            
            //pega email e senha vindos do post
            String emailInput = accessTokenRequest.getEmail();
            String senhaInput = accessTokenRequest.getSenha();
            
            //verifica se usuario existe no banco
            int idUsuario = new UsuarioDAO().autenticarUsuario(emailInput, senhaInput);  
            
            //monta accesstoken
            String accessToken = AUTHENTICATION_KEY + ":" + emailInput + ":" + senhaInput;
            byte[] bytesEncoded = Base64.encode(accessToken.getBytes());
            accessToken = new String(bytesEncoded);            
            
            //retorna access token e id do usuario
            AccessTokenResponse accessTokenResponse = new AccessTokenResponse(idUsuario, accessToken);
            return Response.status(Response.Status.OK).entity(accessTokenResponse).build();      
        } catch (Exception e) {
            switch (e.getMessage())
            {
                //CODE 403
                case "Usuario nao encontrado.":
                    return Response.status(Response.Status.FORBIDDEN).build();
                //CODE 500
                default:
                    return Response.serverError().entity(e.getMessage()).build();
            }
        }
    }     
}
