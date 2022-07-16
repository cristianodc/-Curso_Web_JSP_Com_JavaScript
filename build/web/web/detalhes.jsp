
<%@include  file="./includes/_header.jsp" %>
<%@include file="./includes/_menu.jsp" %>
<%@page import="br.com.projetoloja.model.Produto"%>
<%@page import="java.util.List"%>
<%@page import="br.com.projetoloja.repository.ProdutoDAO" %>%>
  <%
      /*
        1) Pegar  o parametro "idc" que bem da URL (id da categoria)
        2) Criar um objeto DAO para consultar por ID de categoria
        3) Usar retorno lista para exibir os dados (aqui não usamos o useBean)
      */
      String filtro = "1 != 1";
       Long idP =0L;
      if(request.getParameter("idP") != null)
        {
                String id = request.getParameter("idP");
                   
            try {
                    idP =Long.parseLong(id);
                    filtro = "idprodutos ="+ idP;
                } catch (Exception e) {
               idP =0L;
            }
        }
       
      ProdutoDAO dao = new ProdutoDAO();
      List<Produto> listaP  = dao.buscar(filtro);
      pageContext.setAttribute("listaP", listaP);
  %>
   <c:forEach items="${listaP}" var="prod">
  <div class="container px-4 py-4" id="custom-cards">
    <h2 class="pb-2 border-bottom">${prod.nome}</h2>
    <div class="row row-cols-1 row-cols-lg-3 align-items-stretch g-4 py-5">
        
         ${prod.descricao}
         <br>
           ${prod.maisinfo}
         <br>
          ${prod.valor}

    </div>
  </div>
   </c:forEach>
    <%@include file="./includes/_footer.jsp" %>

</main>

<script src="./assets/js/bootstrap.bundle.min.js"></script>   
</body>
</html>
