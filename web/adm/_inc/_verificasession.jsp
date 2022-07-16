<%-- 
    Document   : _verificasession
    Created on : 1 de jul de 2022, 15:17:35
    Author     : Cristiano
--%>

<%@page import="br.com.projetoloja.model.Usuario"%>
<%
    if(session.getAttribute("usuario") == null)
        {
            //response.sendRedirect("index.jsp");
            RequestDispatcher despachador = request.getRequestDispatcher("index.jsp");
            despachador.forward(request, response);
        }
   Usuario usu =(Usuario)  session.getAttribute("usuario");
    
%>