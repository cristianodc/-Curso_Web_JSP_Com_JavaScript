<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>Alfashop</title>

  <!-- Bootstrap core CSS -->
  <link href="./assets/css/bootstrap.min.css" rel="stylesheet">
  
  <!-- Custom styles for this template -->
  <link href="./assets/css/style.css" rel="stylesheet">
</head>

<body>

<main>

  <nav class="navbar navbar-expand-md ">
    <div class="container">
      <div class="collapse navbar-collapse" id="navbarCollapse">
        <ul class="navbar-nav me-auto mb-2 mb-md-0">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="#">Alfashop</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="./">Destaques</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Contato</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Perguntas Frequentes</a>
          </li>
        </ul>
        <form class="d-flex" action="produtos.jsp">
            <input class="form-control me-2" type="search" placeholder="Pesquisar por nome" name="q">
          <button class="btn btn-outline-primary" type="submit">Pesquisar</button>
        </form>
      </div>
    </div>
  </nav>
    
