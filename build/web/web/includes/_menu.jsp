 <nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <div class="container">
      <div class="collapse navbar-collapse">
        <ul class="navbar-nav">
            <jsp:useBean class="br.com.projetoloja.repository.CategoriaDAO" id="lista" />
            <c:forEach items="${lista.categoria}" var="cat">
                <li class="nav-item">
                   <a class="nav-link" href="produtos.jsp?idc=${cat.idcategorias}">${cat.nome}</a>
               </li>
            </c:forEach>
          
         
        </ul>
      </div>
    </div>
  </nav>