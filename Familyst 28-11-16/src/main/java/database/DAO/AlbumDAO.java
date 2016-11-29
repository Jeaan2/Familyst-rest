/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.DAO;

import api.model.Album;
import interfaces.database.DAO.IAlbumDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jdfid
 */
public class AlbumDAO implements IAlbumDAO{
 
    /**
     * Nomes de colunas e PreparedStatements para execucao de querys
     */
    private final String colid = "idAlbum";
    private final String colnome = "nome";
    private final String coldescricao = "descricao";
    private final String coldataCriacao = "dataCriacao";
    private final String colidfamilia = "familia_idFamilia";
    private final String stmtBuscarAlbum = "SELECT * FROM ALBUM WHERE " + colid + " = ?";
    private final String stmtListarAlbunsFamilia = "SELECT * FROM ALBUM WHERE " + colidfamilia + " = ?";
    private final String stmtInserirAlbum = "INSERT INTO ALBUM (" + colnome + ","+ coldescricao + "," + coldataCriacao + "," + colidfamilia + ") VALUES (?,?,NOW(),?)";
    private final String stmtEditarAlbum = "UPDATE ALBUM SET " + colnome + " = ?," + coldescricao + " = ? WHERE " + colid + " = ?";
    private final String stmtRemoverAlbum = "DELETE FROM ALBUM WHERE " + colid + " = ?";
    
    /**
     * Classes utilizadas
     */
    Connection con;
    PreparedStatement stmt;
    
    @Override
    public List<Album> listarAlbunsFamilia(int idFamilia) throws Exception {
        try {
            
            List<Album> albuns = new ArrayList<>();            
            
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtListarAlbunsFamilia);
            stmt.setInt(1, idFamilia);
            stmt.execute();
            
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                Timestamp timestamp = rs.getTimestamp(coldataCriacao);
                java.util.Date dataCriacao = new java.util.Date(timestamp.getTime());
                Album album = new Album(rs.getInt(colid), rs.getString(colnome), rs.getString(coldescricao), dataCriacao, rs.getInt(colidfamilia));
                albuns.add(album);
            }            
            
            return albuns;
        } catch (Exception e) {
            Logger.getLogger(AlbumDAO.class.getName()).log(Level.SEVERE, null, e);
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
    public int inserirAlbum(Album album) throws Exception {
        try {        
            
            //insere evento
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtInserirAlbum, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, album.getNome());        
            stmt.setString(2, album.getDescricao());            
            stmt.setInt(3, album.getIdFamilia());         
            
            stmt.executeUpdate();
            ResultSet keys = stmt.getGeneratedKeys();
            keys.next();
            return keys.getInt(1);
            
        } catch (Exception e) {
            Logger.getLogger(AlbumDAO.class.getName()).log(Level.SEVERE, null, e);
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
    public void editarAlbum(int idAlbum, Album album) throws Exception {
        try {        
            
            //verifica se album existe
            Album albumExistente = buscarAlbum(idAlbum);
            if ( albumExistente == null)
                throw new Exception("Album nao encontrado.");
            
            //verifica se album teve algum dado alterado
            if (albumExistente.getDescricao().equals(album.getDescricao()) && 
                    albumExistente.getNome().equals(album.getNome()))
                throw new Exception("Album nao alterado.");
            
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtEditarAlbum);
            stmt.setString(1, album.getNome());
            stmt.setString(2, album.getDescricao());
            stmt.setInt(3, idAlbum);
            stmt.executeUpdate();
            
        } catch (Exception e) {
            Logger.getLogger(AlbumDAO.class.getName()).log(Level.SEVERE, null, e);
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
    
    private Album buscarAlbum(int idAlbum) throws Exception {
         try {         
            
            Album album = null;
             
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtBuscarAlbum);
            stmt.setInt(1, idAlbum);
            stmt.execute();
            
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) { 
                Timestamp timestamp = rs.getTimestamp(coldataCriacao);
                java.util.Date dataCriacao = new java.util.Date(timestamp.getTime());                
                album = new Album(rs.getInt(colid), rs.getString(colnome), rs.getString(coldescricao), dataCriacao, rs.getInt(colidfamilia));
            }        
            
            if (album == null)
                throw new Exception("Album nao encontrado.");
            
            return album;
        } catch (Exception e) {
            Logger.getLogger(AlbumDAO.class.getName()).log(Level.SEVERE, null, e);
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
    public void removerAlbum(int idAlbum) throws Exception {
        try {     
            
            //verifica se album existe
            Album albumExistente = buscarAlbum(idAlbum);
            if ( albumExistente == null)
                throw new Exception("Album nao encontrado.");
            
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtRemoverAlbum);
            stmt.setInt(1, idAlbum);
            stmt.executeUpdate();
            
        } catch (Exception e) {
            Logger.getLogger(AlbumDAO.class.getName()).log(Level.SEVERE, null, e);
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
