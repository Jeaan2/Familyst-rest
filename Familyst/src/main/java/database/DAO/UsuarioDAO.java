/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.DAO;

import api.model.Usuario;
import interfaces.database.DAO.IUsuarioDAO;
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
public class UsuarioDAO implements IUsuarioDAO{

    /**
     * Nomes de colunas e PreparedStatements para execucao de querys
     */
    private final String colid = "idUsuario";
    private final String colnome = "nome";
    private final String colsenha = "senha";
    private final String colidFacebook = "idFacebook";
    private final String colemail = "email";
    private final String collocal = "local";
    private final String coldataCriacao = "dataCriacao";
    private final String stmtAutenticarUsuario = "SELECT * FROM USUARIO WHERE " + colemail + " = ? AND " + colsenha + " = ?";
    private final String stmtBuscarUsuario = "SELECT * FROM USUARIO WHERE " + colid + " = ?";
    private final String stmtBuscarUsuarioExistente = "SELECT * FROM USUARIO WHERE " + colemail + " = ?";
    private final String stmtInserirUsuario = "INSERT INTO USUARIO (" + colnome + "," + colsenha + "," + colemail + "," + collocal + "," + coldataCriacao + ") VALUES (?,?,?,?,NOW())";
    private final String stmtEditarUsuario = "UPDATE USUARIO SET " + colnome + " = ?," + collocal + " = ? WHERE " + colid + " = ?";
    private final String stmtRemoverUsuario = "DELETE FROM USUARIO WHERE " + colid + " = ?";
    
    /**
     * Classes utilizadas
     */
    Connection con;
    PreparedStatement stmt;
    
    @Override
    public int autenticarUsuario(String email, String senha) throws Exception
    {
        try {         
            
            Usuario usuario = null;
             
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtAutenticarUsuario);
            stmt.setString(1, email);
            stmt.setString(2, senha);
            stmt.execute();
            
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                usuario = new Usuario(rs.getInt(colid), rs.getString(colnome), rs.getString(colsenha), rs.getString(colidFacebook), rs.getString(collocal), rs.getString(colemail), rs.getDate(coldataCriacao));               
            }        
            if (usuario == null)
                throw new Exception("Usuario nao encontrado.");
            
            return usuario.getIdUsuario();
            
        } catch (Exception e) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
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
    public Usuario buscarUsuario(int idUsuario) throws Exception
    {
        try {         
            
            Usuario usuario = null;
             
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtBuscarUsuario);
            stmt.setInt(1, idUsuario);
            stmt.execute();
            
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                Timestamp timestamp = rs.getTimestamp(coldataCriacao);
                java.util.Date dataCriacao = new java.util.Date(timestamp.getTime());   
                usuario = new Usuario(rs.getInt(colid), rs.getString(colnome), rs.getString(colsenha), rs.getString(colidFacebook), rs.getString(collocal), rs.getString(colemail), dataCriacao);               
            }        
            
            if (usuario == null)
                throw new Exception("Usuario nao encontrado.");
            
            return usuario;
            
        } catch (Exception e) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
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
    public int inserirUsuario(Usuario usuario) throws Exception {
        try {        
            
            //verifica se ja nao existe usuario com este email
            if (buscarUsuarioExistente(usuario.getEmail()) != null)
                throw new Exception("Recurso já existente na base.");           
            
            //insere entidade
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtInserirUsuario, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSenha());         
            stmt.setString(3, usuario.getEmail());          
            stmt.setString(4, usuario.getLocal());
            
            stmt.executeUpdate();
            ResultSet keys = stmt.getGeneratedKeys();
            keys.next();
            return keys.getInt(1);
            
        } catch (Exception e) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
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
    
    //metodo interno
    private Usuario buscarUsuarioExistente(String email) {
         try {         
            
            Usuario usuario = null;
             
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtBuscarUsuarioExistente);
            stmt.setString(1, email);
            stmt.execute();
            
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                usuario = new Usuario(rs.getInt(colid), rs.getString(colnome), rs.getString(colsenha), rs.getString(colidFacebook), rs.getString(collocal), rs.getString(colemail),rs.getDate(coldataCriacao));               
            }           
            
            return usuario;
        } catch (Exception e) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
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
    public void editarUsuario(int idUsuario, Usuario usuario) throws Exception{
        try {        
            
            //verifica se usuario existe
            Usuario usuarioExistente = buscarUsuario(idUsuario);
            if ( usuarioExistente == null)
                throw new Exception("Usuario nao encontrado.");
            
            //verifica se usuario teve algum dado alterado
            if (usuarioExistente.getNome().equals(usuario.getNome()) && 
                    usuarioExistente.getLocal().equals(usuario.getLocal()))
                throw new Exception("Usuario nao alterado.");
            
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtEditarUsuario);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getLocal());
            stmt.setInt(3, idUsuario);
            stmt.executeUpdate();
            
        } catch (Exception e) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
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
    public void removerUsuario(int idUsuario) throws Exception {
        try {     
            
            //verifica se usuario existe
            Usuario usuarioExistente = buscarUsuario(idUsuario);
            if ( usuarioExistente == null)
                throw new Exception("Usuario nao encontrado.");
            
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtRemoverUsuario);
            stmt.setInt(1, idUsuario);
            stmt.executeUpdate();
            
        } catch (Exception e) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
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
