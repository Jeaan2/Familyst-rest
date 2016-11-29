/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.api.resources;

import api.model.Item;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

public interface IItensResources {

    public Response inserirItem(Item evento);
    public Response editarItem(@PathParam("idItem") Integer idItem, Item item);
    public Response removerItem(@PathParam("idItem") Integer idItem);
    
}
