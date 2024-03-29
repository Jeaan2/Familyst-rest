/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.DAO;

import api.model.TipoItem;
import interfaces.database.DAO.ITipoItemDAO;
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
public class TipoItemDAO implements ITipoItemDAO{

    /**
     * Nomes de colunas e PreparedStatements para execucao de querys
     */
    private final String colid = "idtipoItem";
    private final String colnome = "nome";
    private final String stmtListarTipoItem = "SELECT * FROM TIPOITEM";
        
    /**
     * Classes utilizadas
     */
    Connection con;
    PreparedStatement stmt;
    
    @Override
    public List<TipoItem> listarTiposItem() throws Exception {
        try {
            
            List<TipoItem> tiposItem = new ArrayList<>();            
            
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtListarTipoItem);
            stmt.execute();
            
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                TipoItem tipoItem = new TipoItem(rs.getInt(colid), rs.getString(colnome));
                tiposItem.add(tipoItem);
            }            
            
            return tiposItem;
        } catch (Exception e) {
            Logger.getLogger(TipoItemDAO.class.getName()).log(Level.SEVERE, null, e);
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
