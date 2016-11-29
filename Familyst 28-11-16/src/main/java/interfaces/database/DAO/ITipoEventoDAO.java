/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.database.DAO;

import api.model.TipoEvento;
import java.util.List;

/**
 *
 * @author jdfid
 */
public interface ITipoEventoDAO {
        
    public List<TipoEvento> listarTiposEvento() throws Exception;
    public TipoEvento buscarTipoEvento(int idTipoEvento) throws Exception;
}
