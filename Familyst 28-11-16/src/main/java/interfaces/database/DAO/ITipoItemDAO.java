/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.database.DAO;

import api.model.TipoItem;
import java.util.List;

/**
 *
 * @author jdfid
 */
public interface ITipoItemDAO {
        
    public List<TipoItem> listarTiposItem() throws Exception;
    public TipoItem buscarTipoItem(int idTipoItem) throws Exception;
}
