/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.DAO;

import api.model.Evento;
import interfaces.database.DAO.IEventoDAO;
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
public class EventoDAO implements IEventoDAO{
 
    /**
     * Nomes de colunas e PreparedStatements para execucao de querys
     */
    private final String colid = "idEvento";
    private final String colnome = "nome";
    private final String coldescricao = "descricao";
    private final String coldataCriacao = "dataCriacao";
    private final String collocal = "local";
    private final String colidfamilia = "familia_idFamilia";
    private final String colidtipoevento = "tipoEvento_idtipoEvento";
    private final String colidusuario = "usuario_idUsuario";
    private final String stmtBuscarEvento = "SELECT * FROM EVENTO WHERE " + colid + " = ?";
    private final String stmtListarEventosFamilia = "SELECT * FROM EVENTO WHERE " + colidfamilia + " = ?";
    private final String stmtInserirEvento = "INSERT INTO EVENTO (" + colnome + ","+ coldescricao + "," + coldataCriacao + "," + collocal + "," + colidfamilia+ "," + colidtipoevento+ "," + colidusuario + ") VALUES (?,?,NOW(),?,?,?,?)";
    private final String stmtEditarEvento = "UPDATE EVENTO SET " + colnome + " = ?," + coldescricao + " = ?," + collocal + " = ?," + colidtipoevento + " = ? WHERE " + colid + " = ?";
    private final String stmtRemoverEvento = "DELETE FROM EVENTO WHERE " + colid + " = ?";
    
    /**
     * Classes utilizadas
     */
    Connection con;
    PreparedStatement stmt;
    
    @Override
    public List<Evento> listarEventosFamilia(int idFamilia) throws Exception {
        try {
            
            List<Evento> eventos = new ArrayList<>();            
            
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtListarEventosFamilia);
            stmt.setInt(1, idFamilia);
            stmt.execute();
            
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                Timestamp timestamp = rs.getTimestamp(coldataCriacao);
                java.util.Date dataCriacao = new java.util.Date(timestamp.getTime());
                Evento evento = new Evento(rs.getInt(colid), rs.getString(colnome), rs.getString(coldescricao), dataCriacao, rs.getString(collocal), rs.getInt(colidfamilia), rs.getInt(colidtipoevento), rs.getInt(colidusuario));
                eventos.add(evento);
            }            
            
            return eventos;
        } catch (Exception e) {
            Logger.getLogger(EventoDAO.class.getName()).log(Level.SEVERE, null, e);
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
    public int inserirEvento(Evento evento) throws Exception {
        try {        
            
            //insere evento
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtInserirEvento, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, evento.getNome());        
            stmt.setString(2, evento.getDescricao());            
            stmt.setString(3, evento.getLocal());         
            stmt.setInt(4, evento.getIdFamilia());         
            stmt.setInt(5, evento.getIdTipoEvento());        
            stmt.setInt(6, evento.getIdUsuario());    
            
            stmt.executeUpdate();
            ResultSet keys = stmt.getGeneratedKeys();
            keys.next();
            return keys.getInt(1);
            
        } catch (Exception e) {
            Logger.getLogger(EventoDAO.class.getName()).log(Level.SEVERE, null, e);
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
    public void editarEvento(int idEvento, Evento evento) throws Exception {
        try {        
            
            //verifica se evento existe
            Evento eventoExistente = buscarEvento(idEvento);
            if ( eventoExistente == null)
                throw new Exception("Evento nao encontrado.");
            
            //verifica se evento teve algum dado alterado
            if (eventoExistente.getDescricao().equals(evento.getDescricao()) && 
                    eventoExistente.getNome().equals(evento.getNome()) && 
                    eventoExistente.getIdTipoEvento() == (evento.getIdTipoEvento()) && 
                    eventoExistente.getLocal().equals(evento.getLocal()))
                throw new Exception("Evento nao alterado.");
            
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtEditarEvento);
            stmt.setString(1, evento.getNome());
            stmt.setString(2, evento.getDescricao());
            stmt.setString(3, evento.getLocal());
            stmt.setInt(4, evento.getIdTipoEvento());
            stmt.setInt(5, idEvento);
            stmt.executeUpdate();
            
        } catch (Exception e) {
            Logger.getLogger(EventoDAO.class.getName()).log(Level.SEVERE, null, e);
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
    
    private Evento buscarEvento(int idEvento) throws Exception {
         try {         
            
            Evento evento = null;
             
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtBuscarEvento);
            stmt.setInt(1, idEvento);
            stmt.execute();
            
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) { 
                Timestamp timestamp = rs.getTimestamp(coldataCriacao);
                java.util.Date dataCriacao = new java.util.Date(timestamp.getTime());                
                evento = new Evento(rs.getInt(colid), rs.getString(colnome), rs.getString(coldescricao), dataCriacao, rs.getString(collocal), rs.getInt(colidfamilia), rs.getInt(colidtipoevento), rs.getInt(colidusuario));
            }        
            
            if (evento == null)
                throw new Exception("Evento nao encontrado.");
            
            return evento;
        } catch (Exception e) {
            Logger.getLogger(EventoDAO.class.getName()).log(Level.SEVERE, null, e);
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
    public void removerEvento(int idEvento) throws Exception {
        try {     
            
            //verifica se video existe
            Evento eventoExistente = buscarEvento(idEvento);
            if ( eventoExistente == null)
                throw new Exception("Evento nao encontrado.");
            
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtRemoverEvento);
            stmt.setInt(1, idEvento);
            stmt.executeUpdate();
            
        } catch (Exception e) {
            Logger.getLogger(EventoDAO.class.getName()).log(Level.SEVERE, null, e);
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
