/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.DAO;

import api.model.Familia;
import api.model.Video;
import interfaces.database.DAO.IVideoDAO;
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
public class VideoDAO implements IVideoDAO{
 
    /**
     * Nomes de colunas e PreparedStatements para execucao de querys
     */
    private final String colid = "idVideo";
    private final String coldescricao = "descricao";
    private final String coldataCriacao = "dataCriacao";
    private final String collink = "link";
    private final String colidgaleria = "galeria_idgaleria";
    private final String stmtBuscarVideo = "SELECT * FROM VIDEO WHERE " + colid + " = ?";
    private final String stmtListarVideosGaleria = "SELECT * FROM VIDEO WHERE " + colidgaleria + " = ?";
    private final String stmtInserirVideo = "INSERT INTO VIDEO (" + coldescricao + "," + coldataCriacao + "," + collink + "," + colidgaleria + ") VALUES (?,NOW(),?,?)";
    private final String stmtEditarVideo = "UPDATE VIDEO SET " + coldescricao + " = ?," + collink + " = ? WHERE " + colid + " = ?";
    private final String stmtRemoverVideo = "DELETE FROM VIDEO WHERE " + colid + " = ?";
    
    /**
     * Classes utilizadas
     */
    Connection con;
    PreparedStatement stmt;
    
    @Override
    public List<Video> listarVideosGaleria(int idGaleria) throws Exception {
        try {
            
            List<Video> videos = new ArrayList<>();            
            
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtListarVideosGaleria);
            stmt.setInt(1, idGaleria);
            stmt.execute();
            
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                Timestamp timestamp = rs.getTimestamp(coldataCriacao);
                java.util.Date dataCriacao = new java.util.Date(timestamp.getTime());
                Video video = new Video(rs.getInt(colid), rs.getString(coldescricao), dataCriacao, rs.getString(collink), rs.getInt(colidgaleria));
                videos.add(video);
            }            
            
            return videos;
        } catch (Exception e) {
            Logger.getLogger(VideoDAO.class.getName()).log(Level.SEVERE, null, e);
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
    public int inserirVideo(Video video) throws Exception {
        try {        
            
            //insere video
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtInserirVideo, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, video.getDescricao());            
            stmt.setString(2, video.getLink());         
            stmt.setInt(3, video.getIdGaleria());     
            
            stmt.executeUpdate();
            ResultSet keys = stmt.getGeneratedKeys();
            keys.next();
            return keys.getInt(1);
            
        } catch (Exception e) {
            Logger.getLogger(VideoDAO.class.getName()).log(Level.SEVERE, null, e);
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
    public void editarVideo(int idVideo, Video video) throws Exception {
        try {        
            
            //verifica se video existe
            Video videoExistente = buscarVideo(idVideo);
            if ( videoExistente == null)
                throw new Exception("Video nao encontrado.");
            
            //verifica se video teve algum dado alterado
            if (videoExistente.getDescricao().equals(video.getDescricao()) && 
                    videoExistente.getLink().equals(video.getLink()))
                throw new Exception("Video nao alterado.");
            
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtEditarVideo);
            stmt.setString(1, video.getDescricao());
            stmt.setString(2, video.getLink());
            stmt.setInt(3, idVideo);
            stmt.executeUpdate();
            
        } catch (Exception e) {
            Logger.getLogger(VideoDAO.class.getName()).log(Level.SEVERE, null, e);
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
    
    private Video buscarVideo(int idVideo) throws Exception {
         try {         
            
            Video video = null;
             
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtBuscarVideo);
            stmt.setInt(1, idVideo);
            stmt.execute();
            
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) { 
                Timestamp timestamp = rs.getTimestamp(coldataCriacao);
                java.util.Date dataCriacao = new java.util.Date(timestamp.getTime());                
                video = new Video(rs.getInt(colid), rs.getString(coldescricao), dataCriacao, rs.getString(collink), rs.getInt(colidgaleria));               
            }        
            
            if (video == null)
                throw new Exception("Video nao encontrado.");
            
            return video;
        } catch (Exception e) {
            Logger.getLogger(VideoDAO.class.getName()).log(Level.SEVERE, null, e);
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
    public void removerVideo(int idVideo) throws Exception {
        try {     
            
            //verifica se video existe
            Video videoExistente = buscarVideo(idVideo);
            if ( videoExistente == null)
                throw new Exception("Video nao encontrado.");
            
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtRemoverVideo);
            stmt.setInt(1, idVideo);
            stmt.executeUpdate();
            
        } catch (Exception e) {
            Logger.getLogger(VideoDAO.class.getName()).log(Level.SEVERE, null, e);
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
