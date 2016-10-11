/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Interfaces;

import Model.Familia;
import java.util.List;

/**
 *
 * @author jdfid
 */
public interface IFamiliaDAO {
        
    public List<Familia> listarFamilias();
    
    public Familia buscarFamilia(int idFamilia);
    
    public boolean inserirFamilia(Familia familia);
        
    public void editarFamilia(int idFamilia, Familia familia);
    
    public void removerFamilia(Familia familia);
}
