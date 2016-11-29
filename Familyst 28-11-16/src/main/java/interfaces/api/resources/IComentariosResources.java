/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.api.resources;

import api.model.Comentario;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

public interface IComentariosResources {

    public Response inserirComentario(Comentario comentario);
    public Response editarComentario(@PathParam("idComentario") Integer idComentario, Comentario comentario);
    public Response removerComentario(@PathParam("idComentario") Integer idComentario);
    
}
