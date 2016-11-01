/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.database.DAO;

import api.model.Galeria;

/**
 *
 * @author jdfid
 */
public interface IGaleriaDAO {
       
    public Galeria buscarGaleria(int idGaleria) throws Exception;    
    public int inserirGaleria() throws Exception;
    
}
