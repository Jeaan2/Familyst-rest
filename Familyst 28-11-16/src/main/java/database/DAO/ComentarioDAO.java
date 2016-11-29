/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.DAO;

import api.model.Comentario;
import interfaces.database.DAO.IComentarioDAO;
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
public class ComentarioDAO implements IComentarioDAO{
 
    /**
     * Nomes de colunas e PreparedStatements para execucao de querys
     */
    private final String colid = "idComentario";
    private final String coldados = "dados";
    private final String coldataCriacao = "dataCriacao";
    private final String colidNoticia = "noticia_idnoticia";
    private final String colidevento = "evento_idEvento";
    private final String colidusuario = "usuario_idUsuario";
    private final String stmtBuscarComentario = "SELECT * FROM COMENTARIO WHERE " + colid + " = ?";
    private final String stmtListarComentariosEvento = "SELECT * FROM COMENTARIO WHERE " + colidevento + " = ?";
    private final String stmtInserirComentario = "INSERT INTO COMENTARIO (" + coldados + "," + coldataCriacao + "," + colidNoticia+ "," + colidevento+ "," + colidusuario + ") VALUES (?,NOW(),?,?,?)";
    private final String stmtEditarComentario = "UPDATE COMENTARIO SET " + coldados + " = ? WHERE " + colid + " = ?";
    private final String stmtRemoverComentario = "DELETE FROM COMENTARIO WHERE " + colid + " = ?";
    
    /**
     * Classes utilizadas
     */
    Connection con;
    PreparedStatement stmt;
    
    @Override
    public List<Comentario> listarComentariosEvento(int idEvento) throws Exception {
        try {
            
            List<Comentario> comentarios = new ArrayList<>();            
            
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtListarComentariosEvento);
            stmt.setInt(1, idEvento);
            stmt.execute();
            
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                Timestamp timestamp = rs.getTimestamp(coldataCriacao);
                java.util.Date dataCriacao = new java.util.Date(timestamp.getTime());
                Comentario comentario = new Comentario(rs.getInt(colid), rs.getString(coldados), dataCriacao, rs.getInt(colidNoticia), rs.getInt(colidevento), rs.getInt(colidusuario));
                comentarios.add(comentario);
            }            
            
            return comentarios;
        } catch (Exception e) {
            Logger.getLogger(ComentarioDAO.class.getName()).log(Level.SEVERE, null, e);
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
    public int inserirComentario(Comentario comentario) throws Exception {
        try {        
            
            //insere evento
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtInserirComentario, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, comentario.getDescricao());            
            stmt.setInt(2, comentario.getIdFamilia());         
            stmt.setInt(3, comentario.getIdTipoEvento());        
            stmt.setInt(4, comentario.getIdUsuario());    
            
            stmt.executeUpdate();
            ResultSet keys = stmt.getGeneratedKeys();
            keys.next();
            return keys.getInt(1);
            
        } catch (Exception e) {
            Logger.getLogger(ComentarioDAO.class.getName()).log(Level.SEVERE, null, e);
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
    public void editarComentario(int idComentario, Comentario comentario) throws Exception {
        try {        
            
            //verifica se comentario existe
            Comentario comentarioExistente = buscarComentario(idComentario);
            if ( comentarioExistente == null)
                throw new Exception("Comentario nao encontrado.");
            
            //verifica se evento teve algum dado alterado
            if (comentarioExistente.getDescricao().equals(comentario.getDescricao()))
                throw new Exception("Comentario nao alterado.");
            
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtEditarComentario);
            stmt.setString(1, comentario.getDescricao());
            stmt.setInt(2, idComentario);
            stmt.executeUpdate();
            
        } catch (Exception e) {
            Logger.getLogger(ComentarioDAO.class.getName()).log(Level.SEVERE, null, e);
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
    
    private Comentario buscarComentario(int idComentario) throws Exception {
         try {         
            
            Comentario comentario = null;
             
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtBuscarComentario);
            stmt.setInt(1, idComentario);
            stmt.execute();
            
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) { 
                Timestamp timestamp = rs.getTimestamp(coldataCriacao);
                java.util.Date dataCriacao = new java.util.Date(timestamp.getTime());                
                comentario = new Comentario(rs.getInt(colid), rs.getString(coldados), dataCriacao, rs.getInt(colidNoticia), rs.getInt(colidevento), rs.getInt(colidusuario));
            }        
            
            if (comentario == null)
                throw new Exception("Comentario nao encontrado.");
            
            return comentario;
        } catch (Exception e) {
            Logger.getLogger(ComentarioDAO.class.getName()).log(Level.SEVERE, null, e);
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
    public void removerComentario(int idComentario) throws Exception {
        try {     
            
            //verifica se comentario existe
            Comentario comentarioExistente = buscarComentario(idComentario);
            if ( comentarioExistente == null)
                throw new Exception("Comentario nao encontrado.");
            
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtRemoverComentario);
            stmt.setInt(1, idComentario);
            stmt.executeUpdate();
            
        } catch (Exception e) {
            Logger.getLogger(ComentarioDAO.class.getName()).log(Level.SEVERE, null, e);
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
