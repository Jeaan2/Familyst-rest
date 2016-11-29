/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.api.resources;

import api.model.Album;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

public interface IAlbunsResources {

    public Response inserirAlbum(Album album);
    public Response editarAlbum(@PathParam("idAlbum") Integer idAlbum, Album album);
    public Response removerAlbum(@PathParam("idAlbum") Integer idAlbum);
    
}
