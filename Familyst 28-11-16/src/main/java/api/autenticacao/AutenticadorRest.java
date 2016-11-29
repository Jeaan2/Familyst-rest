/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.autenticacao;

import com.sun.jersey.core.util.Base64;
import database.DAO.UsuarioDAO;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jdfid
 */
@WebFilter(filterName = "AutenticadorRest", urlPatterns = {"/*"})
public class AutenticadorRest implements Filter {
    
    public static final String AUTHENTICATION_HEADER = "Authorization";    
    public static final String AUTHENTICATION_KEY = "9S0hwJEl0ErzLvQO0INu";
    private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(
                                                            Arrays.asList("","/accesstokens"))); 
    
    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {    
        
        //recupera codigo de autenticacao entrado
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authCredentials = httpServletRequest.getHeader(AUTHENTICATION_HEADER);

        //verifica se a url entrada está na lista de urls que nao precisam de autenticação
        String requestPath = ((HttpServletRequest) request).getRequestURI();
        String contextPath = ((HttpServletRequest) request).getContextPath();               
        String partialPath = requestPath.substring(contextPath.length());
        String path;
        try {
            path = partialPath.substring(0,partialPath.indexOf("/",1)); //usar regex            
        } catch (Exception e) {
            path = partialPath;
        }
        boolean authenticationStatus = ALLOWED_PATHS.contains(path);
        
        //o cadastro de usuario não usa autenticação
        if (path.equals("/usuarios") && httpServletRequest.getMethod().equals("POST"))
            authenticationStatus = true;
        
        //se nao esta na lista de permitidos, tenta autenticar
        if (!authenticationStatus)
            authenticationStatus = authenticate(authCredentials);

        //retorno
        if (authenticationStatus) {
            chain.doFilter(request, response);
        } else {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
    
    private  boolean authenticate(String authCredentials) {
        try {
            //se nao enviou auth
            if (authCredentials == null) return false;

            //Remove "Basic"
            final String encodedAuthenticationSent = authCredentials.replaceFirst("Basic" + " ", "");

            //remove criptografia base64
            String authenticationSent = null;        
            try {
                byte[] decodedBytes = Base64.decode(encodedAuthenticationSent);
                authenticationSent = new String(decodedBytes, "UTF-8");
            } catch (IOException e) {
                Logger.getLogger(AutenticadorRest.class.getName()).log(Level.SEVERE, null, e);
            }

            //recupera parametros
            final StringTokenizer tokenizer = new StringTokenizer(
                            authenticationSent, ":");
            final String authKey = tokenizer.nextToken();
            final String usuario = tokenizer.nextToken();
            final String senha = tokenizer.nextToken();

            //se nao retornar id nenhum, nao autenticado
            int idUsuario = new UsuarioDAO().autenticarUsuario(usuario, senha);

            return ( idUsuario != -1 && authKey.equals(AUTHENTICATION_KEY));

        } catch (Exception e) {
            return false;
        }
    }  

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
