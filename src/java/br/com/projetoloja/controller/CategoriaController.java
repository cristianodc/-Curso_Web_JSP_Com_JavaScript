/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.com.projetoloja.controller;

import br.com.projetoloja.model.Categoria;
import br.com.projetoloja.repository.CategoriaDAO;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Cristiano
 */
public class CategoriaController extends HttpServlet {

    public Categoria inserir(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
    
        int res=0;
          String nome = request.getParameter("nome");
          String descricao=  request.getParameter("desc");
        //  String ativ=  request.getParameter("atv");
          CategoriaDAO daoCat = new CategoriaDAO();
          Categoria cat = new Categoria();
          cat.setNome(nome);
          cat.setDescricao(descricao);
          cat.setAtivo("s");
         res= daoCat.cadastrar(cat);
         if(res > 0)
            {
                return  cat;
            }
         return null;
    }
    public Categoria update(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
    
          String      nome = request.getParameter("nome");
          String  descricao=  request.getParameter("desc");
          String       idc = request.getParameter("idc");
          String       ati = request.getParameter("atv");
          int res =0; 
               
          CategoriaDAO daoCat = new CategoriaDAO();
          Categoria cat = new Categoria();
          cat.setIdcategorias(Integer.parseInt(idc));
          cat.setNome(nome);
          cat.setDescricao(descricao);
          cat.setAtivo(ati);
          
         res= daoCat.updateCat(cat);
         if(res > 0)
         {
            return cat; 
         }
         return null;
    }
    public int delete(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
       CategoriaDAO dao = new CategoriaDAO();
       String       idc = request.getParameter("idc");
       int id = Integer.parseInt(idc);
       
       int result =  dao.deleteCat(id);
       return result;
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     
        List<Categoria> lstCat= new ArrayList<Categoria>();
        Categoria cat = null;
        Gson gson = new Gson();
        String json = "";
        String act = request.getParameter("act");
        /*Retornta para o navegador*/
        
        if(act.equalsIgnoreCase("cat"))
            {
                String id = request.getParameter("idc");
                int idCat = Integer.parseInt(id);
                cat = buscarCatId(idCat);
                lstCat.add(cat);
                json = gson.toJson(lstCat);
            }
        if(act.equalsIgnoreCase("all"))
            {
               lstCat = listaAll();
                json = gson.toJson(lstCat);
            }
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
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
        
          Categoria cat=null;
          PrintWriter out  = response.getWriter();
          Gson gson = new Gson();
          String json ="";
          String act = request.getParameter("act");
        
        
        if(act.equalsIgnoreCase("inserir"))
            {
               cat= inserir(request, response);
               json= gson.toJson(cat);
            }
        
        if(act.equalsIgnoreCase("update"))
            {
              cat=  update(request, response);
               json= gson.toJson(cat);
                
            }
        if(act.equalsIgnoreCase("delete"))
            {
               int result =  delete(request, response);
              cat = new Categoria();
               if(result > 0)
                    {
                        cat.setNome("ok");
                        
                    }else{
                   
                   cat.setNome("");
                  
               }
               
               json= gson.toJson(cat);
            }
       
       
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
    
    public List<Categoria> listaAll ()
        {
            CategoriaDAO dao = new CategoriaDAO();
            List<Categoria> lstCat= dao.listar();
            return lstCat;
        }
    
    public Categoria buscarCatId(int idCat)
        {
            CategoriaDAO dao  = new CategoriaDAO();
            Categoria cat = dao.buscarCatId(idCat);
            return cat;
        }

}
