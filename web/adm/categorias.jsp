<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="_inc/_verificasession.jsp" %>
<%@include file="_inc/_header.jsp" %>

 <div class="container">
            <div class="row">
              
              <%@include file="_inc/_menu.jsp" %>

              <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
                
                <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                    <h2>Lista das Categorias</h2>
                    <a href="categoria_insert.jsp" class="btn btn-info">Cadastrar Categoria</a>
                </div>
                
                <div class="table-responsive">
                  <table class="table table-striped table-sm">
                    <thead>
                      <tr>
                        <th scope="col">Cod</th>
                        <th scope="col">Nome</th>
                        <th scope="col">Descricao</th>
                        <th scope="col">Ativo</th>
                        <th scope="col">Opçõeses</th>
                      </tr>
                    </thead>
                    <tbody>
                        <jsp:useBean class="br.com.projetoloja.repository.CategoriaDAO" id="lista"/>
                        <c:forEach items="${lista.categoria}" var="objCat">
                      <tr>
                        <td>${objCat.idcategorias}</td>
                        <td>${objCat.nome}</td>
                        <td>${objCat.descricao}</td>
                        <td>${objCat.ativo}</td>
                        <td>
                            <a href="categoria_update.jsp?idc=${objCat.idcategorias}" class="btn btn-warning">[A]</a>
                            
                            <form action="../catController" method="post" id="fdel${objCat.idcategorias}">
                                <input type="hidden" name="act" value="delete">
                                <input type="hidden" name="idc" value="${objCat.idcategorias}">
                                <button class="btn btn-danger" type="button" onclick="avisoDel('${objCat.nome}',${objCat.idcategorias});" >[ X ]</button>
                            </form>
                        </td>  
                      </tr>
                        </c:forEach>
                    </tbody>
                  </table>
                </div>
                
              </main>
            </div>
        </div>
              
        <script type="text/javascript">
            function avisoDel(nom, idc) {
                
               var ok = confirm("Deseja excluir a categoria "+nom+"  ???");
                if (ok) {
                    var div = document.getElementById("fdel"+idc).submit();
                    
                   
                }
            }
        </script>
   </body>
</html>
