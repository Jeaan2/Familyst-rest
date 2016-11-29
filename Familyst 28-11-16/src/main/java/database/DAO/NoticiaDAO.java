/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.DAO;

import api.model.Noticia;
import interfaces.database.DAO.INoticiaDAO;
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
public class NoticiaDAO implements INoticiaDAO{
 
    /**
     * Nomes de colunas e PreparedStatements para execucao de querys
     */
    private final String colid = "idNoticia";
    private final String coldescricao = "descricao";
    private final String coldataCriacao = "dataCriacao";
    private final String colidfamilia = "familia_idFamilia";
    private final String colidusuario = "usuario_idUsuario";
    private final String stmtBuscarNoticia = "SELECT * FROM NOTICIA WHERE " + colid + " = ?";
    private final String stmtListarNoticiasFamilia = "SELECT * FROM NOTICIA WHERE " + colidfamilia + " = ?";
    private final String stmtInserirNoticia = "INSERT INTO NOTICIA (" + coldescricao + "," + coldataCriacao + "," + colidfamilia+ "," + colidusuario + ") VALUES (?,NOW(),?,?)";
    private final String stmtEditarNoticia = "UPDATE NOTICIA SET " + coldescricao + " = ? WHERE " + colid + " = ?";
    private final String stmtRemoverNoticia = "DELETE FROM NOTICIA WHERE " + colid + " = ?";
    
    /**
     * Classes utilizadas
     */
    Connection con;
    PreparedStatement stmt;
    
    @Override
    public List<Noticia> listarNoticiasFamilia(int idFamilia) throws Exception {
        try {
            
            List<Noticia> noticias = new ArrayList<>();            
            
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtListarNoticiasFamilia);
            stmt.setInt(1, idFamilia);
            stmt.execute();
            
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                Timestamp timestamp = rs.getTimestamp(coldataCriacao);
                java.util.Date dataCriacao = new java.util.Date(timestamp.getTime());
                Noticia noticia = new Noticia(rs.getInt(colid), rs.getString(coldescricao), dataCriacao, rs.getInt(colidfamilia), rs.getInt(colidusuario));
                noticias.add(noticia);
            }            
            
            return noticias;
        } catch (Exception e) {
            Logger.getLogger(NoticiaDAO.class.getName()).log(Level.SEVERE, null, e);
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
    public int inserirNoticia(Noticia noticia) throws Exception {
        try {        
            
            //insere evento
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtInserirNoticia, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, noticia.getDescricao());            
            stmt.setInt(2, noticia.getIdFamilia());         
            stmt.setInt(3, noticia.getIdUsuario());    
            
            stmt.executeUpdate();
            ResultSet keys = stmt.getGeneratedKeys();
            keys.next();
            return keys.getInt(1);
            
        } catch (Exception e) {
            Logger.getLogger(NoticiaDAO.class.getName()).log(Level.SEVERE, null, e);
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
    public void editarNoticia(int idNoticia, Noticia noticia) throws Exception {
        try {        
            
            //verifica se noticia existe
            Noticia noticiaExistente = buscarNoticia(idNoticia);
            if ( noticiaExistente == null)
                throw new Exception("Noticia nao encontrada.");
            
            //verifica se evento teve algum dado alterado
            if (noticiaExistente.getDescricao().equals(noticia.getDescricao()))
                throw new Exception("Noticia nao alterada.");
            
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtEditarNoticia);
            stmt.setString(1, noticia.getDescricao());
            stmt.setInt(2, idNoticia);
            stmt.executeUpdate();
            
        } catch (Exception e) {
            Logger.getLogger(NoticiaDAO.class.getName()).log(Level.SEVERE, null, e);
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
    
    private Noticia buscarNoticia(int idNoticia) throws Exception {
         try {         
            
            Noticia noticia = null;
             
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtBuscarNoticia);
            stmt.setInt(1, idNoticia);
            stmt.execute();
            
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) { 
                Timestamp timestamp = rs.getTimestamp(coldataCriacao);
                java.util.Date dataCriacao = new java.util.Date(timestamp.getTime());                
                noticia = new Noticia(rs.getInt(colid), rs.getString(coldescricao), dataCriacao, rs.getInt(colidfamilia), rs.getInt(colidusuario));
            }        
            
            if (noticia == null)
                throw new Exception("Noticia nao encontrada.");
            
            return noticia;
        } catch (Exception e) {
            Logger.getLogger(NoticiaDAO.class.getName()).log(Level.SEVERE, null, e);
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
    public void removerNoticia(int idNoticia) throws Exception {
        try {     
            
            //verifica se noticia existe
            Noticia noticiaExistente = buscarNoticia(idNoticia);
            if ( noticiaExistente == null)
                throw new Exception("Noticia nao encontrada.");
            
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtRemoverNoticia);
            stmt.setInt(1, idNoticia);
            stmt.executeUpdate();
            
        } catch (Exception e) {
            Logger.getLogger(NoticiaDAO.class.getName()).log(Level.SEVERE, null, e);
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
