/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.api.resources;

import api.model.Familia;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

public interface IFamiliasResources {

    public Response findAll();
    public Response buscarFamilia(@PathParam("idFamilia") Integer idFamilia);
    public Response inserirFamilia(Familia familia);
    public Response editarFamilia(@PathParam("idFamilia") Integer idFamilia, Familia familia);
    public Response removerFamilia(@PathParam("idFamilia") Integer idFamilia);
    
}
