/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.config;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author jdfid
 */
@javax.ws.rs.ApplicationPath("/")
public class FamilystApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);        
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(api.autenticacao.resources.AccessTokensResource.class);
        resources.add(api.resources.AlbunsResources.class);
        resources.add(api.resources.ArquivosResources.class);
        resources.add(api.resources.EventosResources.class);
        resources.add(api.resources.FamiliasResources.class);
        resources.add(api.resources.GaleriasResources.class);
        resources.add(api.resources.ItensResources.class);
        resources.add(api.resources.TiposEventoResources.class);
        resources.add(api.resources.TiposItemResources.class);
        resources.add(api.resources.UsuariosFamiliasResources.class);
        resources.add(api.resources.UsuariosResources.class);
        resources.add(api.resources.VideosResources.class);
        resources.add(webclient.doc.DocApp.class);
    }  
    
}
