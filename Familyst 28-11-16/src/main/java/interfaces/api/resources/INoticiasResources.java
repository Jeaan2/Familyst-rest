/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.api.resources;

import api.model.Noticia;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

public interface INoticiasResources {

    public Response inserirNoticia(Noticia noticia);
    public Response editarNoticia(@PathParam("idNoticia") Integer idNoticia, Noticia noticia);
    public Response removerNoticia(@PathParam("idNoticia") Integer idNoticia);
    
}
