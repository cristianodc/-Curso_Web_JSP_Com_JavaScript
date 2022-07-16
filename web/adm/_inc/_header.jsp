<!DOCTYPE html>
<html>
    <head>
        <title>Alfashop - ADM</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        
        <!-- Bootstrap core CSS -->
        <link href="./assets/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        
        <header class="container navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
            <a class="navbar-brand col-md-3 col-lg-2 me-0 px-3" href="#">Aflashop ADM</a>
            <div class="text-white">
              <%= usu.getNome() %>
            </div>
            <div class="navbar-nav">
              <div class="nav-item text-nowrap">
                  <a class="nav-link px-3" href="../login-exec">Logout</a>
              </div>
            </div>
        </header>