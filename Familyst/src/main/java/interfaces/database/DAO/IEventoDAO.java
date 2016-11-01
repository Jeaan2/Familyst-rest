/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.database.DAO;

import api.model.Evento;
import java.util.List;

/**
 *
 * @author jdfid
 */
public interface IEventoDAO {
        
    public List<Evento> listarEventosFamilia(int idFamilia) throws Exception;
    
    public int inserirEvento(Evento evento) throws Exception;
        
    public void editarEvento(int idEvento, Evento evento) throws Exception;
    
    public void removerEvento(int idEvento) throws Exception;
}
