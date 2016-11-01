/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.database.DAO;

import api.model.Arquivo;

/**
 *
 * @author jdfid
 */
public interface IArquivoDAO {
       
    public Arquivo buscarArquivo(int idArquivo) throws Exception;    
    public int inserirArquivo() throws Exception;
    
}
