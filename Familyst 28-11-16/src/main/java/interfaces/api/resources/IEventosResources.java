/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.api.resources;

import api.model.Evento;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

public interface IEventosResources {

    public Response inserirEvento(Evento evento);
    public Response editarEvento(@PathParam("idEvento") Integer idEvento, Evento evento);
    public Response removerEvento(@PathParam("idEvento") Integer idEvento);
    
}
