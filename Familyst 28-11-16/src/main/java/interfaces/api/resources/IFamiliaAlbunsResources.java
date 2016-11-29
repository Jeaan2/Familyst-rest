/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.api.resources;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

public interface IFamiliaAlbunsResources {
    public Response buscarAlbunsFamilia(@PathParam("idFamilia") Integer idFamilia);
}
