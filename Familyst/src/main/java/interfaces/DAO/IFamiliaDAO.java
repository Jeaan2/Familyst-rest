/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.DAO;

import api.model.Familia;
import java.util.List;

/**
 *
 * @author jdfid
 */
public interface IFamiliaDAO {
        
    public List<Familia> listarFamilias() throws Exception;
    
    public Familia buscarFamilia(int idFamilia) throws Exception;
    
    public int inserirFamilia(Familia familia) throws Exception;
        
    public void editarFamilia(int idFamilia, Familia familia) throws Exception;
    
    public void removerFamilia(int idFamilia) throws Exception;
}
