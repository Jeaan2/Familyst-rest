/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webclient.doc;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author jdfid
 */
@Path("/")
public class DocApp {
    
    //tags html
    private final String iniLinha = "<tr>";
    private final String fimLinha = "</tr>";
    private final String iniColuna = "<td>";
    private final String fimColuna = "</td>";
    private final String iniTabela = "<table>";
    private final String fimTabela = "</table>";
    private final String line_tab = "&nbsp;&nbsp;&nbsp;&nbsp;";
    private final String line_break = "<br>";  
        
    @GET
    public String SwaggerApp()
    {
        //Criacao tabelas
        String title_familia = "Familias:" + line_tab + " Authorization: Return 401 - Unauthorized ";
        String tabelaFamilias = criarTabelaFamilias();      
        String title_accesstokens = "AccessTokens:";
        String tabelaAccessTokens = criarTabelaAccessTokens();
        String title_usuarios = "Usuarios:" + line_tab + " Authorization: Return 401 - Unauthorized ";
        String tabelaUsuarios = criarTabelaUsuarios();       
        String title_galerias = "Galerias:" + line_tab + " Authorization: Return 401 - Unauthorized ";
        String tabelaGalerias = criarTabelaGalerias();        
        String title_arquivos = "Arquivos:" + line_tab + " Authorization: Return 401 - Unauthorized ";
        String tabelaArquivos = criarTabelaArquivos();  
        String title_videos = "Videos:" + line_tab + " Authorization: Return 401 - Unauthorized ";
        String tabelaVideos = criarTabelaVideos();
        String title_tiposEvento = "TiposEvento:" + line_tab + " Authorization: Return 401 - Unauthorized ";
        String tabelaTiposEvento = criarTabelaTiposEvento();
        String title_tiposItem = "TiposItem:" + line_tab + " Authorization: Return 401 - Unauthorized ";
        String tabelaTiposItem = criarTabelaTiposItem();
        String title_eventos = "Eventos:" + line_tab + " Authorization: Return 401 - Unauthorized ";
        String tabelaEventos = criarTabelaEventos();
        String titleItens = "Itens:" + line_tab + " Authorization: Return 401 - Unauthorized ";
        String tabelaItens = criarTabelaItens();
        String titleAlbuns = "Albuns:" + line_tab + " Authorization: Return 401 - Unauthorized ";
        String tabelaAlbuns = criarTabelaAlbuns();
        
        //definicao css
        String css =    "<style>\n" +
                        "table {\n" +
                        "    font-family: arial, sans-serif;\n" +
                        "    border-collapse: collapse;\n" +
                        "    width: 100%;\n" +
                        "}\n" +
                        "\n" +
                        "td, th {\n" +
                        "    border: 1px solid #dddddd;\n" +
                        "    text-align: left;\n" +
                        "    padding: 8px;\n" +
                        "}\n" +
                        "\n" +
                        "tr:nth-child(even) {\n" +
                        "    background-color: #dddddd;\n" +
                        "}\n" +
                        "</style>\n";
        
        String page = "<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        css +
                        "</head>\n" +
                        "<body>\n" +
                        "\n" +
                        line_break + "<b>" + title_familia + "</b>" +
                        tabelaFamilias +
                        line_break + "<b>" + title_accesstokens + "</b>" +
                        tabelaAccessTokens +
                        line_break + "<b>" + title_usuarios + "</b>" +
                        tabelaUsuarios +
                        line_break + "<b>" + title_galerias + "</b>" +
                        tabelaGalerias +
                        line_break + "<b>" + title_arquivos + "</b>" +
                        tabelaArquivos +
                        line_break + "<b>" + title_videos + "</b>" +
                        tabelaVideos +
                        line_break + "<b>" + title_tiposEvento + "</b>" +
                        tabelaTiposEvento +
                        line_break + "<b>" + title_tiposItem + "</b>" +
                        tabelaTiposItem +
                        line_break + "<b>" + title_eventos + "</b>" +
                        tabelaEventos +
                        line_break + "<b>" + titleItens + "</b>" +
                        tabelaItens +
                        line_break + "<b>" + titleAlbuns + "</b>" +
                        tabelaAlbuns +
                        "\n" +
                        "</body>\n" +
                        "</html>";
        
        return page;
    }

    private String criarTabelaFamilias() {
        String linha_GET_familia = 
                iniLinha + 
                iniColuna + "GET /familias" + fimColuna + 
                iniColuna + "200 - OK " + line_tab + " {List<familias>}" + line_break +  "500 - Internal Server Error" + fimColuna + 
                fimLinha;
        
        String linha_GET_familia_id = 
                iniLinha + 
                iniColuna + "GET /familias" + "{idFamilia}" + fimColuna + 
                iniColuna + "200 - OK "+  line_tab +" {familia}"+line_break + "404 - Not Found"+line_break + "500 - Internal Server Error" + fimColuna + 
                fimLinha;
        
        String linha_POST_familia = 
                iniLinha + 
                iniColuna + "POST /familias" + fimColuna + 
                iniColuna + "201 - Created "+  line_tab +"HEADER {resourcebasepath}/idFamilia"+line_break + "409 - Conflict "+line_break + "500 - Internal Server Error" + fimColuna + 
                iniColuna + "Body (JSON - XML) "+line_break + "{ \"familia\": { \"nome\": ,\"descricao\": ,\"local\": }} " + fimColuna + 
                fimLinha;
        
        String linha_PUT_familia = 
                iniLinha + 
                iniColuna + "PUT /familias" + "{idFamilia}" + fimColuna + 
                iniColuna + "200 - OK " + line_break + "404 - Not Found "+line_break + "204 - No Content"+line_break + "500 - Internal Server Error" + fimColuna + 
                iniColuna + "Body (JSON - XML) "+line_break + "{ \"familia\": { \"nome\": ,\"descricao\": ,\"local\": }} " + fimColuna + 
                fimLinha;
        
        String linha_DELETE_familia = 
                iniLinha + 
                iniColuna + "DELETE /familias" + "{idFamilia}" + fimColuna + 
                iniColuna + "200 - OK "+line_break + "404 - Not Found"+line_break + "500 - Internal Server Error" + fimColuna + 
                fimLinha;

        String tabelaFamilias = iniTabela +
                linha_GET_familia +
                linha_GET_familia_id +
                linha_POST_familia +
                linha_PUT_familia + 
                linha_DELETE_familia +
                fimTabela;
        
        return tabelaFamilias;
    }

    private String criarTabelaAccessTokens() {
        
        String linha_POST_accesstoken = 
                iniLinha + 
                iniColuna + "POST /accesstokens" + fimColuna + 
                iniColuna + "200 - OK "+  line_tab +" { idUsuario, accesstoken}"+line_break + "404 - Not Found"+line_break + "500 - Internal Server Error" + fimColuna + 
                iniColuna + "Body (JSON - XML) "+line_break + "{ \"accesstokenrequest\": { \"nome\": ,\"senha\":}} " + fimColuna + 
                fimLinha;        

        String tabelaAccesstoken = iniTabela +
                linha_POST_accesstoken +
                fimTabela;
        
        return tabelaAccesstoken;
    }

    private String criarTabelaUsuarios() {
        
        String linha_GET_usuario_id = 
                iniLinha + 
                iniColuna + "GET /usuario" + "{idUsuario}" + fimColuna + 
                iniColuna + "200 - OK "+  line_tab +" {usuario}"+line_break + "404 - Not Found"+line_break + "500 - Internal Server Error" + fimColuna + 
                fimLinha;
        
        String linha_POST_usuario = 
                iniLinha + 
                iniColuna + "POST /usuarios" + fimColuna + 
                iniColuna + "201 - Created "+  line_tab +"HEADER {resourcebasepath}/idUsuario"+line_break + "409 - Conflict "+line_break + "500 - Internal Server Error" + fimColuna + 
                iniColuna + "Body (JSON - XML) "+line_break + "{ \"usuario\": { \"nome\": ,\"senha\": ,\"email\": ,\"local\":}} " + fimColuna + 
                fimLinha;
        
        String linha_PUT_usuario = 
                iniLinha + 
                iniColuna + "PUT /usuarios" + "{idUsuario}" + fimColuna + 
                iniColuna + "200 - OK " + line_break + "404 - Not Found "+line_break + "204 - No Content"+line_break + "500 - Internal Server Error" + fimColuna + 
                iniColuna + "Body (JSON - XML) "+line_break + "{ \"usuario\": { \"nome\": ,\"local\": }} " + fimColuna + 
                fimLinha;
        
        String linha_DELETE_usuario = 
                iniLinha + 
                iniColuna + "DELETE /usuarios" + "{idUsuario}" + fimColuna + 
                iniColuna + "200 - OK "+line_break + "404 - Not Found"+line_break + "500 - Internal Server Error" + fimColuna + 
                fimLinha;

        String tabelaUsuarios = iniTabela +
                linha_GET_usuario_id +
                linha_POST_usuario +
                linha_PUT_usuario + 
                linha_DELETE_usuario +
                fimTabela;
        
        return tabelaUsuarios;
    }
    
    private String criarTabelaGalerias() {
        
        String linha_GET_galeria_id = 
                iniLinha + 
                iniColuna + "GET /galerias" + "{idGaleria}" + fimColuna + 
                iniColuna + "200 - OK "+  line_tab +" {galeria}"+line_break + "404 - Not Found"+line_break + "500 - Internal Server Error" + fimColuna + 
                fimLinha; 
       
        String tabelaGalerias = iniTabela +
                linha_GET_galeria_id +
                fimTabela;
        
        return tabelaGalerias;
    }
    
     private String criarTabelaArquivos() {
        
        String linha_GET_arquivo_id = 
                iniLinha + 
                iniColuna + "GET /arquivos" + "{idArquivos}" + fimColuna + 
                iniColuna + "200 - OK "+  line_tab +" {arquivo}"+line_break + "404 - Not Found"+line_break + "500 - Internal Server Error" + fimColuna + 
                fimLinha; 
       
        String tabelaArquivos = iniTabela +
                linha_GET_arquivo_id +
                fimTabela;
        
        return tabelaArquivos;
    }
     
     private String criarTabelaVideos() {

         String linha_GET_video_id = 
                iniLinha + 
                iniColuna + "GET /videos" + "{idGaleria}" + fimColuna + 
                iniColuna + "200 - OK "+  line_tab +" {videos da galeria}"+line_break + "404 - Not Found"+line_break + "500 - Internal Server Error" + fimColuna + 
                fimLinha;
        
        String linha_POST_video = 
                iniLinha + 
                iniColuna + "POST /videos" + fimColuna + 
                iniColuna + "201 - Created "+  line_tab +"HEADER {resourcebasepath}/idVideo"+line_break + "500 - Internal Server Error" + fimColuna + 
                iniColuna + "Body (JSON - XML) "+line_break + "{ \"video\": { \"descricao\": ,\"link\": ,\"idGaleria\": }} " + fimColuna + 
                fimLinha;
        
        String linha_PUT_video = 
                iniLinha + 
                iniColuna + "PUT /videos" + "{idVideo}" + fimColuna + 
                iniColuna + "200 - OK " + line_break + "404 - Not Found "+line_break + "204 - No Content"+line_break + "500 - Internal Server Error" + fimColuna + 
                iniColuna + "Body (JSON - XML) "+line_break + "{ \"video\": { \"descricao\": ,\"link\": }} " + fimColuna + 
                fimLinha;
        
        String linha_DELETE_video = 
                iniLinha + 
                iniColuna + "DELETE /videos" + "{idVideo}" + fimColuna + 
                iniColuna + "200 - OK "+line_break + "404 - Not Found"+line_break + "500 - Internal Server Error" + fimColuna + 
                fimLinha;

        String tabelaVideos = iniTabela +
                linha_GET_video_id +
                linha_POST_video +
                linha_PUT_video + 
                linha_DELETE_video +
                fimTabela;
        
        return tabelaVideos;
    }
     
    private String criarTabelaTiposEvento() {
        
        String linha_GET_tipoEvento = 
                iniLinha + 
                iniColuna + "GET /tiposEvento" + fimColuna + 
                iniColuna + "200 - OK "+  line_tab +" {tiposEvento}"+line_break + "500 - Internal Server Error" + fimColuna + 
                fimLinha; 
       
        String tabelaTiposEvento = iniTabela +
                linha_GET_tipoEvento +
                fimTabela;
        
        return tabelaTiposEvento;
    }
    
    private String criarTabelaTiposItem() {
        
        String linha_GET_tipoItem = 
                iniLinha + 
                iniColuna + "GET /tiposItem" + fimColuna + 
                iniColuna + "200 - OK "+  line_tab +" {tiposItem}"+line_break + "500 - Internal Server Error" + fimColuna + 
                fimLinha; 
       
        String tabelaTiposItem = iniTabela +
                linha_GET_tipoItem +
                fimTabela;
        
        return tabelaTiposItem;
    }
    
    private String criarTabelaEventos() {
        
        String linha_GET_eventos_idFamilia = 
                iniLinha + 
                iniColuna + "GET /eventos" + "{idFamilia}" + fimColuna + 
                iniColuna + "200 - OK "+  line_tab +" {eventos}"+line_break + "404 - Not Found"+line_break + "500 - Internal Server Error" + fimColuna + 
                fimLinha;
        
        String linha_POST_evento = 
                iniLinha + 
                iniColuna + "POST /eventos" + fimColuna + 
                iniColuna + "201 - Created "+  line_tab +"HEADER {resourcebasepath}/idEvento"+line_break + "500 - Internal Server Error" + fimColuna + 
                iniColuna + "Body (JSON - XML) "+line_break + "{ \"evento\": { \"nome\": ,\"descricao\": ,\"local\": ,\"idFamilia\": ,\"idTipoEvento\": ,\"idUsuario\":}} " + fimColuna + 
                fimLinha;
        
        String linha_PUT_evento = 
                iniLinha + 
                iniColuna + "PUT /eventos" + "{idEvento}" + fimColuna + 
                iniColuna + "200 - OK " + line_break + "404 - Not Found "+line_break + "204 - No Content"+line_break + "500 - Internal Server Error" + fimColuna + 
                iniColuna + "Body (JSON - XML) "+line_break + "{ \"evento\": { \"nome\": ,\"descricao\": ,\"local\": ,\"idTipoEvento\": }} " + fimColuna + 
                fimLinha;
        
        String linha_DELETE_evento = 
                iniLinha + 
                iniColuna + "DELETE /eventos" + "{idEvento}" + fimColuna + 
                iniColuna + "200 - OK "+line_break + "404 - Not Found"+line_break + "500 - Internal Server Error" + fimColuna + 
                fimLinha;

        String tabelaEventos = iniTabela +
                linha_GET_eventos_idFamilia +
                linha_POST_evento +
                linha_PUT_evento + 
                linha_DELETE_evento +
                fimTabela;
        
        return tabelaEventos;
    }
    
    private String criarTabelaItens() {
        
        String linha_GET_itens_idEvento = 
                iniLinha + 
                iniColuna + "GET /itens" + "{idEvento}" + fimColuna + 
                iniColuna + "200 - OK "+  line_tab +" {itens}"+line_break + "404 - Not Found"+line_break + "500 - Internal Server Error" + fimColuna + 
                fimLinha;
        
        String linha_POST_itens = 
                iniLinha + 
                iniColuna + "POST /itens" + fimColuna + 
                iniColuna + "201 - Created "+  line_tab +"HEADER {resourcebasepath}/idItem"+line_break + "500 - Internal Server Error" + fimColuna + 
                iniColuna + "Body (JSON - XML) "+line_break + "{ \"item\": { \"quantidade\": ,\"idTipoItem\": ,\"idEvento\": ,\"idUsuario\":}} " + fimColuna + 
                fimLinha;
        
        String linha_PUT_item = 
                iniLinha + 
                iniColuna + "PUT /itens" + "{idItem}" + fimColuna + 
                iniColuna + "200 - OK " + line_break + "404 - Not Found "+line_break + "204 - No Content"+line_break + "500 - Internal Server Error" + fimColuna + 
                iniColuna + "Body (JSON - XML) "+line_break + "{ \"item\": { \"quantidade\": ,\"idTipoItem\": ,\"idUsuario\":}} " + fimColuna + 
                fimLinha;
        
        String linha_DELETE_item = 
                iniLinha + 
                iniColuna + "DELETE /itens" + "{idItem}" + fimColuna + 
                iniColuna + "200 - OK "+line_break + "404 - Not Found"+line_break + "500 - Internal Server Error" + fimColuna + 
                fimLinha;

        String tabelaItens = iniTabela +
                linha_GET_itens_idEvento +
                linha_POST_itens +
                linha_PUT_item + 
                linha_DELETE_item +
                fimTabela;
        
        return tabelaItens;
    }
    
    private String criarTabelaAlbuns() {
        
        String linha_GET_albuns_idFamilia = 
                iniLinha + 
                iniColuna + "GET /albuns" + "{idFamilia}" + fimColuna + 
                iniColuna + "200 - OK "+  line_tab +" {albuns}"+line_break + "404 - Not Found"+line_break + "500 - Internal Server Error" + fimColuna + 
                fimLinha;
        
        String linha_POST_album = 
                iniLinha + 
                iniColuna + "POST /albuns" + fimColuna + 
                iniColuna + "201 - Created "+  line_tab +"HEADER {resourcebasepath}/idItem"+line_break + "500 - Internal Server Error" + fimColuna + 
                iniColuna + "Body (JSON - XML) "+line_break + "{ \"album\": { \"nome\": ,\"descricao\": ,\"idFamilia\": }} " + fimColuna + 
                fimLinha;
        
        String linha_PUT_album = 
                iniLinha + 
                iniColuna + "PUT /albuns" + "{idAlbum}" + fimColuna + 
                iniColuna + "200 - OK " + line_break + "404 - Not Found "+line_break + "204 - No Content"+line_break + "500 - Internal Server Error" + fimColuna + 
                iniColuna + "Body (JSON - XML) "+line_break + "{ \"album\": { \"nome\": ,\"descricao\": }} " + fimColuna + 
                fimLinha;
        
        String linha_DELETE_album = 
                iniLinha + 
                iniColuna + "DELETE /albuns" + "{idAlbum}" + fimColuna + 
                iniColuna + "200 - OK "+line_break + "404 - Not Found"+line_break + "500 - Internal Server Error" + fimColuna + 
                fimLinha;

        String tabelaAlbuns = iniTabela +
                linha_GET_albuns_idFamilia +
                linha_POST_album +
                linha_PUT_album + 
                linha_DELETE_album +
                fimTabela;
        
        return tabelaAlbuns;
    }
}
