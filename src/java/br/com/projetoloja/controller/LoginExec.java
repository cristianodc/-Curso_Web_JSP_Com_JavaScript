/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.com.projetoloja.controller;

import br.com.projetoloja.model.Usuario;
import br.com.projetoloja.repository.UsuarioDAO;
import br.com.projetoloja.util.Util;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Cristiano
 */
public class LoginExec extends HttpServlet {

   
    protected void validarLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*Pgar dados do form
           Montar filtro
            
        */
        String ema= request.getParameter("ema");
        String senha= request.getParameter("sen");
        HttpSession session = request.getSession();
        String destino="";
        String msg = "";
       //consultr dados usando DAO
        UsuarioDAO daoUso = new UsuarioDAO();
        
        List<Usuario> lista  = new ArrayList<Usuario>();
        
        senha  = Util.Sha01(senha);
        lista = daoUso.buscar(ema,senha);
        
        if(lista.size() > 0)
            {
                Usuario uso = lista.get(0);
                session.setAttribute("usuario", uso);
                destino = "./adm/dashboard.html";
            }else
                {
                    msg = "err";
                    destino = "./adm/?msg="+msg;
                }
        response.sendRedirect(destino);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         PrintWriter out = response.getWriter();
         Usuario objUso = new Usuario();
         objUso.setToken("");
        
           Gson gson  = new Gson();
           String json= gson.toJson(objUso);
           
           //retorna para o navegador
           response.setContentType("application/json");
           response.setCharacterEncoding("utf-8");
           out.print(json);
           out.flush();
        
       
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
           PrintWriter out = response.getWriter();
           String token= "failure";
           
           /*dados vindo pelo body*/
           String foo= request.getParameter("foo");
           String abc = request.getParameter("abc");
           Usuario objUso = null;
           
           final String authorization;
               authorization = request.getHeader("Authorization");
           if(authorization != null && authorization.toLowerCase().startsWith("basic"))
            {
                String base64Credencials= authorization.substring("Basic".length()).trim();
                byte[] creDecode = Base64.getDecoder().decode(base64Credencials);
                String credencials = new String(creDecode,StandardCharsets.UTF_8);
                
                final String[] values = credencials.split(":",2);
                
                UsuarioDAO dao  = new UsuarioDAO();
                
                String ema = values[0];
                String sen = Util.Sha01(values[1]);
                
                objUso = dao.validarLgin(ema, sen);
                if(objUso != null)
                    {
                        HttpSession session = request.getSession();
                        session.setAttribute("login", "d3uc3rto");
                        session.setAttribute("user", objUso.getNome());
                      
                        token  = "d3uc3rto";
                        objUso.setToken(token);
                    }else{
                    
                    objUso = new Usuario();
                    objUso.setToken("");
                }
                
            }
           
           
           Gson gson  = new Gson();
           String json= gson.toJson(objUso);
           
           //retorna para o navegador
           response.setContentType("application/json");
           response.setCharacterEncoding("utf-8");
           out.print(json);
           out.flush();
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
