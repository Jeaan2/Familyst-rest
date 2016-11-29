/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.DAO;

import interfaces.database.DAO.IFamiliaDAO;
import api.model.Familia;
import api.model.Usuario;
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
public class FamiliaDAO implements IFamiliaDAO{

    /**
     * Nomes de colunas e PreparedStatements para execucao de querys
     */
    private final String colid = "idFamilia";
    private final String colidusuario = "usuario_idUsuario";
    private final String colidfamilia = "familia_idFamilia";
    private final String colnome = "nome";
    private final String coldescricao = "descricao";
    private final String coldataCriacao = "dataCriacao";
    private final String collocal = "local";
    private final String colidgaleria = "galeria_idgaleria";
    private final String colidarquivo = "arquivo_idarquivo";
    private final String stmtListarFamilia = "SELECT * FROM FAMILIA";
    private final String stmtListarFamiliasUsuario = "SELECT * FROM USUARIO_HAS_FAMILIA WHERE " + colidusuario + " = ?";  
    private final String stmtListarUsuariosFamilia = "SELECT * FROM USUARIO_HAS_FAMILIA WHERE " + colidfamilia + " = ?";     
    private final String stmtBuscarFamilia = "SELECT * FROM FAMILIA WHERE " + colid + " = ?";
    private final String stmtBuscarFamiliaExistente = "SELECT * FROM FAMILIA WHERE " + colnome + " = ? AND " + coldescricao + " = ? AND " + collocal + " = ?";
    private final String stmtInserirFamilia = "INSERT INTO FAMILIA (" + colnome + "," + coldescricao + "," + coldataCriacao + "," + collocal + "," + colidgaleria + "," + colidarquivo + ") VALUES (?,?,NOW(),?,?,?)";
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
     * @throws java.lang.Exception 
     */    
    @Override
    public List<Familia> listarFamilias() throws Exception {
        try {
            
            List<Familia> familias = new ArrayList<>();            
            
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtListarFamilia);
            stmt.execute();
            
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                Timestamp timestamp = rs.getTimestamp(coldataCriacao);
                java.util.Date dataCriacao = new java.util.Date(timestamp.getTime());
                Familia familia = new Familia(rs.getInt(colid), rs.getString(colnome), rs.getString(coldescricao), dataCriacao, rs.getString(collocal), rs.getInt(colidgaleria), rs.getInt(colidarquivo));
                familias.add(familia);
            }            
            
            return familias;
        } catch (Exception e) {
            Logger.getLogger(FamiliaDAO.class.getName()).log(Level.SEVERE, null, e);
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
    public Familia buscarFamilia(int idFamilia) throws Exception {
         try {         
            
            Familia familia = null;
             
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtBuscarFamilia);
            stmt.setInt(1, idFamilia);
            stmt.execute();
            
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) { 
                Timestamp timestamp = rs.getTimestamp(coldataCriacao);
                java.util.Date dataCriacao = new java.util.Date(timestamp.getTime());                
                familia = new Familia(rs.getInt(colid), rs.getString(colnome), rs.getString(coldescricao), dataCriacao, rs.getString(collocal), rs.getInt(colidgaleria), rs.getInt(colidarquivo));               
            }        
            
            if (familia == null)
                throw new Exception("Familia nao encontrada.");
            
            return familia;
        } catch (Exception e) {
            Logger.getLogger(FamiliaDAO.class.getName()).log(Level.SEVERE, null, e);
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
    private Familia buscarFamiliaExistente(String nome, String descricao, String local) {
         try {         
            
            Familia familia = null;
             
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtBuscarFamiliaExistente);
            stmt.setString(1, nome);
            stmt.setString(2, descricao);
            stmt.setString(3, local);
            stmt.execute();
            
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                familia = new Familia(rs.getInt(colid), rs.getString(colnome), rs.getString(coldescricao), rs.getDate(coldataCriacao), rs.getString(collocal), rs.getInt(colidgaleria), rs.getInt(colidarquivo));               
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
    public int inserirFamilia(Familia familia) throws Exception {
        try {        
            
            //verifica se ja nao existe
            if (buscarFamiliaExistente(familia.getNome(), familia.getDescricao(), familia.getLocal()) != null)
                throw new Exception("Recurso já existente na base.");           
            
            //insere galeria da familia
            int idGaleria = new GaleriaDAO().inserirGaleria();
            
            //insere arquivo da familia
            int idArquivo = new ArquivoDAO().inserirArquivo();
            
            //insere familia
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtInserirFamilia, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, familia.getNome());
            stmt.setString(2, familia.getDescricao());            
            stmt.setString(3, familia.getLocal());         
            stmt.setInt(4, idGaleria);     
            stmt.setInt(5, idArquivo);
            
            stmt.executeUpdate();
            ResultSet keys = stmt.getGeneratedKeys();
            keys.next();
            return keys.getInt(1);
            
        } catch (Exception e) {
            Logger.getLogger(FamiliaDAO.class.getName()).log(Level.SEVERE, null, e);
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
    public void editarFamilia(int idFamilia, Familia familia) throws Exception{
        try {        
            
            //verifica se familia existe
            Familia familiaExistente = buscarFamilia(idFamilia);
            if ( familiaExistente == null)
                throw new Exception("Familia nao encontrada.");
            
            //verifica se familia teve algum dado alterado
            if (familiaExistente.getNome().equals(familia.getNome()) && 
                    familiaExistente.getDescricao().equals(familia.getDescricao()) &&
                    familiaExistente.getLocal().equals(familia.getLocal()))
                throw new Exception("Familia nao alterada.");
            
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtEditarFamilia);
            stmt.setString(1, familia.getNome());
            stmt.setString(2, familia.getDescricao());
            stmt.setString(3, familia.getLocal());
            stmt.setInt(4, idFamilia);
            stmt.executeUpdate();
            
        } catch (Exception e) {
            Logger.getLogger(FamiliaDAO.class.getName()).log(Level.SEVERE, null, e);
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
    public void removerFamilia(int idFamilia) throws Exception {
        try {     
            
            //verifica se familia existe
            Familia familiaExistente = buscarFamilia(idFamilia);
            if ( familiaExistente == null)
                throw new Exception("Familia nao encontrada.");
            
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtRemoverFamilia);
            stmt.setInt(1, idFamilia);
            stmt.executeUpdate();
            
        } catch (Exception e) {
            Logger.getLogger(FamiliaDAO.class.getName()).log(Level.SEVERE, null, e);
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
    public List<Familia> listarFamiliasUsuario(int idUsuario) throws Exception {
        try {            
            List<Familia> familias = new ArrayList<>();            
            
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtListarFamiliasUsuario);
            stmt.setInt(1, idUsuario);
            stmt.execute();
            
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                Familia familia = buscarFamilia(rs.getInt(colidfamilia));
                familias.add(familia);
            }            
            
            return familias;
        } catch (Exception e) {
            Logger.getLogger(FamiliaDAO.class.getName()).log(Level.SEVERE, null, e);
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
    public List<Usuario> listarUsuariosFamilia(int idFamilia) throws Exception {
        try {            
            List<Usuario> usuarios = new ArrayList<>();            
            
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtListarUsuariosFamilia);
            stmt.setInt(1, idFamilia);
            stmt.execute();
            
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                Usuario usuario = new UsuarioDAO().buscarUsuario(rs.getInt(colidusuario));
                usuarios.add(usuario);
            }            
            
            return usuarios;
        } catch (Exception e) {
            Logger.getLogger(FamiliaDAO.class.getName()).log(Level.SEVERE, null, e);
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
