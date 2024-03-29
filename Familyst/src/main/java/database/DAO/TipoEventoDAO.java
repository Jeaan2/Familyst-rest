/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.DAO;

import api.model.TipoEvento;
import interfaces.database.DAO.ITipoEventoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jdfid
 */
public class TipoEventoDAO implements ITipoEventoDAO{

    /**
     * Nomes de colunas e PreparedStatements para execucao de querys
     */
    private final String colid = "idtipoEvento";
    private final String colnome = "nome";
    private final String stmtListarTipoEvento = "SELECT * FROM TIPOEVENTO";
        
    /**
     * Classes utilizadas
     */
    Connection con;
    PreparedStatement stmt;
    
    @Override
    public List<TipoEvento> listarTiposEvento() throws Exception {
        try {
            
            List<TipoEvento> tiposEvento = new ArrayList<>();            
            
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtListarTipoEvento);
            stmt.execute();
            
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                TipoEvento tipoEvento = new TipoEvento(rs.getInt(colid), rs.getString(colnome));
                tiposEvento.add(tipoEvento);
            }            
            
            return tiposEvento;
        } catch (Exception e) {
            Logger.getLogger(TipoEventoDAO.class.getName()).log(Level.SEVERE, null, e);
           throw e;
        } finally {
            try {
                stmt.close();
            } catch (Exception ex) {
            }
            try {
                con.close();
            } catch (Exception ex) {
            }
        }
    }
    
}
