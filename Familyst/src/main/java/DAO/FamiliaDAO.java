/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.Interfaces.IFamiliaDAO;
import Model.Familia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jdfid
 */
public class FamiliaDAO implements IFamiliaDAO{

    /**
     * Nomes de colunas e PreparedStatements para execucao de querys
     */
    private final String colid = "idFamilia";
    private final String colnome = "nome";
    private final String coldescricao = "descricao";
    private final String coldataCriacao = "dataCriacao";
    private final String collocal = "local";
    private final String stmtListarFamilia = "SELECT * FROM FAMILIA";
    private final String stmtBuscarFamilia = "SELECT * FROM FAMILIA WHERE " + colid + " = ?";
    private final String stmtInserirFamilia = "INSERT INTO FAMILIA (" + colid + "," + colnome + "," + coldescricao + "," + coldataCriacao + "," + collocal + ") VALUES (?,?,?,NOW(),?)";
    private final String stmtEditarFamilia = "UPDATE FAMILIA SET " + colnome + " = ?," + coldescricao + " = ?," + collocal + " = ? WHERE " + colid + " = ?";
    private final String stmtRemoverFamilia = "DELETE FROM FAMILIA WHERE " + colid + " = ?";
    
    
    /**
     * Classes utilizadas
     */
    Connection con;
    PreparedStatement stmt;
    
    /**
     * 
     * @return 
     */    
    @Override
    public List<Familia> listarFamilias() {
        try {
            
            List<Familia> familias = new ArrayList<>();            
            
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtListarFamilia);
            stmt.execute();
            
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                Timestamp timestamp = rs.getTimestamp(coldataCriacao);
                java.util.Date dataCriacao = new java.util.Date(timestamp.getTime());
                Familia familia = new Familia(rs.getInt(colid), rs.getString(colnome), rs.getString(coldescricao), dataCriacao, rs.getString(collocal));
                familias.add(familia);
            }            
            
            return familias;
        } catch (Exception e) {
            Logger.getLogger(FamiliaDAO.class.getName()).log(Level.SEVERE, null, e);
            return null;
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
    public Familia buscarFamilia(int idFamilia) {
         try {         
            
            Familia familia = null;
             
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtBuscarFamilia);
            stmt.setInt(1, idFamilia);
            stmt.execute();
            
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                familia = new Familia(rs.getInt(colid), rs.getString(colnome), rs.getString(coldescricao), rs.getDate(coldataCriacao), rs.getString(collocal));               
            }           
            
            return familia;
        } catch (Exception e) {
            Logger.getLogger(FamiliaDAO.class.getName()).log(Level.SEVERE, null, e);
            return null;
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
    public boolean inserirFamilia(Familia familia) {
        try {        
            
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtInserirFamilia);
            stmt.setInt(1, familia.getIdFamilia());
            stmt.setString(2, familia.getNome());
            stmt.setString(3, familia.getDescricao());            
            stmt.setString(4, familia.getLocal());
            
            stmt.executeUpdate();
            
            return true;
        } catch (Exception e) {
            Logger.getLogger(FamiliaDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
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
    public void editarFamilia(int idFamilia, Familia familia) {
        try {        
            
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtEditarFamilia);
            stmt.setString(1, familia.getNome());
            stmt.setString(2, familia.getDescricao());
            stmt.setString(3, familia.getLocal());
            stmt.setInt(4, idFamilia);
            stmt.executeUpdate();
            
        } catch (Exception e) {
            Logger.getLogger(FamiliaDAO.class.getName()).log(Level.SEVERE, null, e);
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
    public void removerFamilia(Familia familia) {
        try {        
            
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtRemoverFamilia);
            stmt.setInt(1, familia.getIdFamilia());
            stmt.executeUpdate();
            
        } catch (Exception e) {
            Logger.getLogger(FamiliaDAO.class.getName()).log(Level.SEVERE, null, e);
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
