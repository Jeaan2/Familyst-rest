/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.DAO;

import api.model.Item;
import interfaces.database.DAO.IItemDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jdfid
 */
public class ItemDAO implements IItemDAO{
 
    /**
     * Nomes de colunas e PreparedStatements para execucao de querys
     */
    private final String colid = "idItem";
    private final String colquantidade = "quantidade";
    private final String colidtipoitem = "tipoItem_idtipoItem";
    private final String colidevento = "evento_idEvento";
    private final String colidusuario = "usuario_idUsuario";
    private final String stmtBuscarItem = "SELECT * FROM ITEM WHERE " + colid + " = ?";
    private final String stmtListarItensEvento = "SELECT * FROM ITEM WHERE " + colidevento + " = ?";
    private final String stmtInserirItem = "INSERT INTO ITEM (" + colquantidade + ","+ colidtipoitem + "," + colidevento + "," + colidusuario + ") VALUES (?,?,?,?)";
    private final String stmtEditarItem = "UPDATE ITEM SET " + colquantidade + " = ?," + colidtipoitem + " = ?," + colidusuario + " = ? WHERE " + colid + " = ?";
    private final String stmtRemoverItem = "DELETE FROM ITEM WHERE " + colid + " = ?";
    
    /**
     * Classes utilizadas
     */
    Connection con;
    PreparedStatement stmt;
    
    @Override
    public List<Item> listarItensEvento(int idEvento) throws Exception {
        try {
            
            List<Item> itens = new ArrayList<>();            
            
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtListarItensEvento);
            stmt.setInt(1, idEvento);
            stmt.execute();
            
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                Item item = new Item(rs.getInt(colid), rs.getInt(colquantidade), rs.getInt(colidtipoitem), rs.getInt(colidevento), rs.getInt(colidusuario));
                itens.add(item);
            }            
            
            return itens;
        } catch (Exception e) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, e);
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
    public int inserirItem(Item item) throws Exception {
        try {        
            
            //insere entidade
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtInserirItem, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, item.getQuantidade());        
            stmt.setInt(2, item.getIdTipoItem());            
            stmt.setInt(3, item.getIdEvento());         
            stmt.setInt(4, item.getIdUsuario());    
            
            stmt.executeUpdate();
            ResultSet keys = stmt.getGeneratedKeys();
            keys.next();
            return keys.getInt(1);
            
        } catch (Exception e) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, e);
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
    public void editarItem(int idItem, Item item) throws Exception {
        try {        
            
            //verifica se item existe
            Item itemExistente = buscarItem(idItem);
            if ( itemExistente == null)
                throw new Exception("Item nao encontrado.");
            
            //verifica se item teve algum dado alterado
            if (itemExistente.getQuantidade() == (item.getQuantidade()) && 
                    itemExistente.getIdTipoItem()==(item.getIdTipoItem()) && 
                    itemExistente.getIdUsuario()==(item.getIdUsuario()))
                throw new Exception("Item nao alterado.");
            
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtEditarItem);
            stmt.setInt(1, item.getQuantidade());
            stmt.setInt(2, item.getIdTipoItem());
            stmt.setInt(3, item.getIdUsuario());
            stmt.setInt(4, idItem);
            stmt.executeUpdate();
            
        } catch (Exception e) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, e);
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
    
    private Item buscarItem(int idItem) throws Exception {
         try {         
            
            Item item = null;
             
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtBuscarItem);
            stmt.setInt(1, idItem);
            stmt.execute();
            
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) { 
                item = new Item(rs.getInt(colid), rs.getInt(colquantidade), rs.getInt(colidtipoitem), rs.getInt(colidevento), rs.getInt(colidusuario));
            }        
            
            if (item == null)
                throw new Exception("Item nao encontrado.");
            
            return item;
        } catch (Exception e) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, e);
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
    public void removerItem(int idItem) throws Exception {
        try {     
            
            //verifica se item existe
            Item itemExistente = buscarItem(idItem);
            if ( itemExistente == null)
                throw new Exception("Item nao encontrado.");
            
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtRemoverItem);
            stmt.setInt(1, idItem);
            stmt.executeUpdate();
            
        } catch (Exception e) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, e);
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
