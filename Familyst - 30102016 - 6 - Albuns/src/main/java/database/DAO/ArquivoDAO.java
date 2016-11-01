/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.DAO;

import api.model.Arquivo;
import interfaces.database.DAO.IArquivoDAO;
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
public class ArquivoDAO implements IArquivoDAO{

    /**
     * Nomes de colunas e PreparedStatements para execucao de querys
     */
    private final String colid = "idArquivo";
    private final String coldataCriacao = "dataCriacao";
    private final String stmtBuscarArquivo = "SELECT * FROM ARQUIVO WHERE " + colid + " = ?";
    private final String stmtInserirArquivo = "INSERT INTO ARQUIVO (" + coldataCriacao + ") VALUES (NOW())";
    
    
    /**
     * Classes utilizadas
     */
    Connection con;
    PreparedStatement stmt;
    
    
    @Override
    public Arquivo buscarArquivo(int idArquivo) throws Exception {
         try {         
            
            Arquivo arquivo = null;
             
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtBuscarArquivo);
            stmt.setInt(1, idArquivo);
            stmt.execute();
            
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                Timestamp timestamp = rs.getTimestamp(coldataCriacao);
                java.util.Date dataCriacao = new java.util.Date(timestamp.getTime()); 
                arquivo = new Arquivo(rs.getInt(colid), dataCriacao);               
            }        
            
            if (arquivo == null)
                throw new Exception("Arquivo nao encontrado.");
            
            return arquivo;
        } catch (Exception e) {
            Logger.getLogger(ArquivoDAO.class.getName()).log(Level.SEVERE, null, e);
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
    public int inserirArquivo() throws Exception {
        try {        
            
            //insere entidade
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtInserirArquivo, Statement.RETURN_GENERATED_KEYS);
            stmt.executeUpdate();
            ResultSet keys = stmt.getGeneratedKeys();
            keys.next();
            return keys.getInt(1);
            
        } catch (Exception e) {
            Logger.getLogger(ArquivoDAO.class.getName()).log(Level.SEVERE, null, e);
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
