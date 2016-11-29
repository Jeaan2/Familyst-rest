/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.database.DAO;

import api.model.Item;
import java.util.List;

/**
 *
 * @author jdfid
 */
public interface IItemDAO {
        
    public List<Item> listarItensEvento(int idEvento) throws Exception;
    
    public int inserirItem(Item item) throws Exception;
        
    public void editarItem(int idItem, Item item) throws Exception;
    
    public void removerItem(int idItem) throws Exception;
}
