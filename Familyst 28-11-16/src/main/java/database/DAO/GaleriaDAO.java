/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.DAO;

import api.model.Galeria;
import interfaces.database.DAO.IGaleriaDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jdfid
 */
public class GaleriaDAO implements IGaleriaDAO{

    /**
     * Nomes de colunas e PreparedStatements para execucao de querys
     */
    private final String colid = "idGaleria";
    private final String coldataCriacao = "dataCriacao";
    private final String stmtBuscarGaleria = "SELECT * FROM GALERIA WHERE " + colid + " = ?";
    private final String stmtInserirGaleria = "INSERT INTO GALERIA (" + coldataCriacao + ") VALUES (NOW())";
    
    
    /**
     * Classes utilizadas
     */
    Connection con;
    PreparedStatement stmt;
    
    
    @Override
    public Galeria buscarGaleria(int idGaleria) throws Exception {
         try {         
            
            Galeria galeria = null;
             
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtBuscarGaleria);
            stmt.setInt(1, idGaleria);
            stmt.execute();
            
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                Timestamp timestamp = rs.getTimestamp(coldataCriacao);
                java.util.Date dataCriacao = new java.util.Date(timestamp.getTime()); 
                galeria = new Galeria(rs.getInt(colid), dataCriacao);               
            }        
            
            if (galeria == null)
                throw new Exception("Galeria nao encontrada.");
            
            return galeria;
        } catch (Exception e) {
            Logger.getLogger(GaleriaDAO.class.getName()).log(Level.SEVERE, null, e);
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
    
    @Override
    public int inserirGaleria() throws Exception {
        try {        
            
            //insere entidade
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtInserirGaleria, Statement.RETURN_GENERATED_KEYS);
            stmt.executeUpdate();
            ResultSet keys = stmt.getGeneratedKeys();
            keys.next();
            return keys.getInt(1);
            
        } catch (Exception e) {
            Logger.getLogger(GaleriaDAO.class.getName()).log(Level.SEVERE, null, e);
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
