/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.com.projetoloja.controller;

import br.com.projetoloja.model.Produto;
import br.com.projetoloja.repository.ProdutoDAO;
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
public class ProdutoControler extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        
            List<Produto> lista  = new ArrayList<Produto>();
            Produto obj;
            String act = request.getParameter("act");
            
            /*pagina.jsp?atc=dtg*/
            
            if(act.equalsIgnoreCase("dtq"))
                {
                    lista = listaDestaques();
                }
            if(act.equalsIgnoreCase("cat"))
                {
                    String id = request.getParameter("idc");
                    
                    lista = prodListCat(id);
                }
             if(act.equalsIgnoreCase("prod"))
                {
                    String id = request.getParameter("idP");
                    obj = buscaProdId(id);
                    lista.add(obj);
                }
              if(act.equalsIgnoreCase("all"))
                {
                    
                   lista = prodListAll();
                }
             
             Gson gson = new Gson();
             String json = gson.toJson(lista);
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
        Produto pro;
        PrintWriter out  = response.getWriter();
        String json="";
        Gson gson = new Gson();
        String act = request.getParameter("act");
        
        
        if(act.equalsIgnoreCase("inserir"))
            {
             pro =   inserirProduto(request,response);
             json=gson.toJson(pro);
            }
        if(act.equalsIgnoreCase("update"))
            {
              pro =   updateProduto(request,response);
              json=gson.toJson(pro);
            }
         if(act.equalsIgnoreCase("delete"))
            {
                deletePro(request,response);
                
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

    protected Produto inserirProduto(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        
        String nome  = request.getParameter("nome");
        String desc  = request.getParameter("descr");
        String pes   = request.getParameter("peso");
        String maisi = request.getParameter("maisinfo");
        String val   = request.getParameter("valor");
        String detaque = request.getParameter("dtq");
        String cid     = request.getParameter("idc");
        
        Produto prod  = new Produto();
        prod.setNome(nome);
        prod.setDescricao(desc);
        prod.setMaisinfo(maisi);
        prod.setPeso(Double.parseDouble(pes));
        prod.setValor(Double.parseDouble(val));
        prod.setDestaque(detaque);
        prod.setCategorias_idcategorias(Integer.parseInt(cid));
        prod.setAtivo("s");
        
        ProdutoDAO dao = new ProdutoDAO();
        int res =  dao.insertProduto(prod);
        
        if(res >0){
            
            return prod;
        }
        
        return null;
    }
     protected Produto updateProduto(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        
         String id = request.getParameter("idP");
        String nome  = request.getParameter("nome");
        String desc  = request.getParameter("descr");
        String pes   = request.getParameter("peso");
        String maisi = request.getParameter("maisinfo");
        String val   = request.getParameter("valor");
        String detaque = "s";
        String cid     = request.getParameter("idc");
        
        Produto prod  = new Produto();
        prod.setIdprodutos(Integer.parseInt(id));
        prod.setNome(nome);
        prod.setDescricao(desc);
        prod.setMaisinfo(maisi);
        prod.setPeso(Double.parseDouble(pes));
        prod.setValor(Double.parseDouble(val));
        prod.setDestaque(detaque);
        prod.setCategorias_idcategorias(Integer.parseInt(cid));
        prod.setAtivo("s");
        
        ProdutoDAO dao = new ProdutoDAO();
        dao.uptadeProduto(prod);
        
        
        return prod;
    }

    private void deletePro(HttpServletRequest request, HttpServletResponse response) {
       
        String id= request.getParameter("idP");
        ProdutoDAO dao = new ProdutoDAO();
        
        dao.deleteProd(Integer.parseInt(id));
    }

    public List<Produto> listaDestaques()
        {
            List<Produto> listaD = new ArrayList<Produto>();
            ProdutoDAO dao = new ProdutoDAO();
            
            return listaD = dao.getDestaques();
        }
    public List<Produto> prodListCat( String catId)
        {
            List<Produto> listaPCat = new ArrayList<Produto>();
            ProdutoDAO dao = new ProdutoDAO();
            
            return listaPCat = dao.listaPorCat(catId);
        }
    
    public Produto buscaProdId( String id)
        {
            Produto prod = new Produto();
            ProdutoDAO dao = new ProdutoDAO();
            
            return prod = dao.buscarId(id);
        }
    public List<Produto> prodListAll( )
        {
            List<Produto> lista = new ArrayList<Produto>();
            ProdutoDAO dao = new ProdutoDAO();
            
            return lista = dao.lista();
        }
}
