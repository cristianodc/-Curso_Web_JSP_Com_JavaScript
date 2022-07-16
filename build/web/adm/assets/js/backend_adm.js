/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
function login()
    {
        var ema = document.getElementById("ema").value;
        var sen =document.getElementById("sen").value;
        var auth= btoa(ema + ":" + sen);
        
        const URL='http://localhost:8080/Projeto_loja_js/login-exec';
        
        fetch(URL,{
            method: 'POST',
            headers:{
                'Content-Type':'application/x-www-form-urlencoded',
                'Authorization': 'Basic' + auth
            },
            
            body: 'foo=123&abc=456'
        })
                .then(res => res.json())
                .then(obj => {
                    console.log(obj);
                    if(obj.token !== '' && obj.token !== 'failure')
                        {
                            localStorage.setItem('token',obj.token);
                            localStorage.setItem('outro',obj.abc);
                            window.location = 'http://localhost:8080/Projeto_loja_js/adm/dashboard.html';
                        }else{
                            localStorage.clear();
                        }
                })
                        .catch(err => console.log(err));
    }
    
    function logout()
        {
           const URL = 'http://localhost:8080/Projeto_loja_js/login-exec';
           
           fetch(URL,{
               
               method:'GET'
           })
                   .then(res => res.json())
                   .then(obj => {
                       localStorage.clear();
                       window.location = 'http://localhost:8080/Projeto_loja_js/adm/login.html';
                   })
                           .catch(err => console.log(err))
            
        }
        
        function verificaAcesso()
            {
                var token = localStorage.getItem('token');
                if(token !== 'd3uc3rto')
                    {
                        localStorage.clear();
                        window.location = 'http://localhost:8080/Projeto_loja_js/adm/login.html';
                    }
            }
            
      function listProtudos(div,param ='',met='GET')
    {
        var met  = met.toUpperCase();
        var bod  = '';
        
        if((param !== null || param !== '') && met === 'GET')
            {
                param = param = "?"+param;
            }else if(met === 'POST')
            {
                bod = 'body: '+param;
            }
            var query = location.search.slice(1);
            var partes = query.split('&');
            var param = param+'&'+partes[0];
        const URL='http://localhost:8080/Projeto_loja_js/prodControler'+param;
        
        console.log(URL);
        
        fetch(URL,{
            methodo:met,
        })
                .then(res => res.json())
                .then(obj => {
                    console.log(obj)
             let prod = '';
                    for(i=0; i<obj.length; i++)
                        {
                          prod += '<tr>';
                          prod += '<td>'+obj[i].idprodutos+'</td>';
                          prod += '<td>'+obj[i].nome+'</td>';
                          prod += '<td>'+obj[i].valor+'</td>';
                          prod += '<td>'+obj[i].peso+'</td>';
                          prod += '<td>'+obj[i].destaque+'</td>';
                          prod += '<td>'+obj[i].ativo+'</td>';
                          prod += '<td> <a href="produto_update02.html?idP='+obj[i].idprodutos+'" class="btn btn-warning">[AA]</a> </td>';
                         
                          prod += '</tr>';
                        }
                        document.getElementById(div).innerHTML = prod;
                })
                        .catch(err => console.log(err))
    }
    function detalheCat(form)
    {
        var query = location.search.slice(1);
        var partes= query.split('&');
        var param = '?act=cat&'+partes[0];
        const URL='http://localhost:8080/Projeto_loja_js/catController'+param;
        
        fetch(URL,{
            method:'GET'
        })
                .then(res => res.json())
                .then(lstCat =>{
                    console.log(lstCat)
                    form.id.  value = lstCat[0].idcategorias;
                    form.nome.value = lstCat[0].nome;
                    form.desc.value = lstCat[0].descricao;
                    form.atv.value  = lstCat[0].ativo;
                    
                })
                        .catch(err => console.log(err));
    }
    
    function detalhesProd(div)
    {
        var query  =location.search.slice(1);
        var partes = query.split('&');
        
        var param = '?act=prod&'+partes[0];
         const URL='http://localhost:8080/Projeto_loja_js/prodControler'+param;
         
         fetch(URL,{
             method: 'GET'
         })
                 .then(res => res.json())
                 .then(obj => {
                     
                     let divPro='';
             
                     for(i=0; i<obj.length; i++)
                        {
                           divPro +='<form  method="post" onsubmit="return false">';
                           
                           divPro += ' <div class="row mb-3"> <label for="inputEmail3" class="col-sm-2 col-form-label">ID</label><div class="col-sm-10"> <input type="text" class="form-control" name="id" value="'+obj[0].idprodutos+'"> </div> </div>'  ;
                           divPro += ' <div class="row mb-3"> <label for="inputEmail3" class="col-sm-2 col-form-label">Nome</label><div class="col-sm-10"> <input type="text" class="form-control" name="nome" value="'+obj[0].nome+'"> </div> </div>'  ;
                           divPro += ' <div class="row mb-3"> <label for="inputEmail3" class="col-sm-2 col-form-label">Descricao</label><div class="col-sm-10"> <input type="text" class="form-control" name="nome" value="'+obj[0].descricao+'"> </div> </div>' ;
                            divPro += ' <div class="row mb-3"> <label for="inputEmail3" class="col-sm-2 col-form-label">Mais Informações</label><div class="col-sm-10"> <textarea type="text" class="form-control" name="maisinfo" >'+obj[0].maisinfo+'" </textarea></div> </div>'  ;
                           divPro += ' <div class="row mb-3"> <label for="inputEmail3" class="col-sm-2 col-form-label">Valor</label><div class="col-sm-10"> <input type="text" class="form-control" name="id" value="'+obj[0].valor+'"> </div> </div>'  ;
                           divPro += ' <div class="row mb-3"> <label for="inputEmail3" class="col-sm-2 col-form-label">Peso</label><div class="col-sm-10"> <input type="text" class="form-control" name="id" value="'+obj[0].peso+'"> </div> </div>'  ;
                           
                           if(obj[0].destaque !== 's')
                            {
                                 divPro += ' <div class="row mb-3"> <label for="inputEmail3" class="col-sm-2 col-form-label">Destaque</label><div class="col-sm-10">\n\
                                             <input type="radio"  name="dtq" value="s" > Sim <br>  <input type="radio"  name="dtq" value="s" checked= checked> Não <br>\n\
                                             </div> </div>'  ;
                            }else{
                                divPro += ' <div class="row mb-3"> <label for="inputEmail3" class="col-sm-2 col-form-label">Destaque</label><div class="col-sm-10">\n\
                                             <input type="radio"  name="dtq" value="s" checked= checked> Sim <br>  <input type="radio"  name="dtq" value="n" > Não <br>\n\
                                             </div> </div>'  ;
                            }
                           
                             divPro += ' <div class="row mb-3"> <label for="inputEmail3" class="col-sm-2 col-form-label">Id Cat</label><div class="col-sm-10"> <input type="text" class="form-control" name="idCat" value="'+obj[0].categorias_idcategorias+'"> </div> </div>'  ;
                              divPro += ' <div class="row mb-3"> <label for="inputEmail3" class="col-sm-2 col-form-label">Categoria</label><div class="col-sm-10"> <input type="text" class="form-control" name="nomeC" value="'+obj[0].nomeCat+'"> </div> </div>'  ;
                           divPro += '<button type="button" class="btn btn-primary" onclick="insertProduto(this.form);>Alterar</button>';
                           divPro +='</form>';
                            
                        }
                        
                        document.getElementById(div).innerHTML = divPro;
                     
                 })
                         .catch(err => console.log(err))
                 
    }
    
    function selectCategorias(div , param='')
        {
            const URL='http://localhost:8080/Projeto_loja_js/catController';
            
            fetch(URL,{
                method: 'GET'
            })
                    .then(res => res.json())
                    .then(catVet => {
                        console.log(catVet);
                        var opc = document.getElementById(div).innerHTML;
                
                        for(i=0; i<catVet.length; i++)
                            {
                                opc +=' <option value="'+catVet[i].idcategorias+'">'+catVet[i].nome+'</option>';
                            }
                            
                            document.getElementById(div).innerHTML = opc;
                    })
                            .catch(err => console.log(err));
            
        }
        
        function insertProduto(form)
        {
            var nome  = form.nome.value;
            var desc  = form.descr.value;
            var maisI = form.maisinfo.value;
            var peso  = form.peso.value;
            var valor = form.val.value;
            var idCat = form.idc.value;
            var dtq   = form.dtq.value;
            var msg = document.getElementById('msg');
             const URL='http://localhost:8080/Projeto_loja_js/prodControler';
             
             fetch(URL,{
                 
                 method: 'POST',
                 headers:{
                    'Content-Type':'application/x-www-form-urlencoded'
                 },
                 body:'act=inserir&idc='+idCat+'&nome='+nome+'&descr='+desc+'&maisinfo='+maisI+'&peso='+peso+'&valor='+valor+'&dtq='+dtq
             })
                     .then(res => res.json())
                     .then(obj =>{
                         if(obj.nome !== '')
                            {
                                msg.innerHTML = 'Cadastro realizado com Sucesso';
                                form.reset();
                            }else{
                                
                                 form.reset();
                            }
                     })
                             .catch(err => console.log(err));
        }
        function deleteCat(idc)
            {
                var ok = confirm('Tem certeza que deseja realizar a exclusão desse registro ?');
                
                if(ok)
                    {
                         var msg = document.getElementById('msg');
                        const URL='http://localhost:8080/Projeto_loja_js/catController';
                        fetch(URL,{
                            method:'POST',
                            headers:{
                                'Content-Type':'application/x-www-form-urlencoded'
                            },
                            body:'act=delete&idc='+idc
                        })
                                .then(res => res.json())
                                .then(obj =>{
                                    console.log(obj);
                                    if(obj.nome !== ''){
                                       location.reload();
                                        msg.innerHTML = 'Realizado com sucesso';
                                    }else{
                                        msg.innerHTML = 'Não';
                                    }
                                })
                                       .catch(err => console.log(err));
                    }
            }
        function updateCat(form)
            {
                var id= form.id.value;
                var nome  = form.nome.value;
                var desc  = form.desc.value;
                var atv = form.atv.value;
                var msg = document.getElementById('msg');
                
                 const URL='http://localhost:8080/Projeto_loja_js/catController';
                 
                 fetch(URL,{
                     method:'POST',
                     headers:{
                         'Content-Type':'application/x-www-form-urlencoded'
                     },
                     body:'act=update&idc='+id+'&nome='+nome+'&desc='+desc+'&atv='+atv
                 })
                         .then(res => res.json())
                         .then(obj =>{
                             if(obj.nome !== '')
                                {
                                     msg.innerHTML = 'Atualização realizado com Sucesso';
                                     form.reset();
                                }else{
                                     msg.innerHTML = 'Problemas ao realizar a atualização';
                                }
                         })
                                 .catch(err => console.log(err));
            }
        function insertCat(form)
            {
                var nome  = form.nome.value;
                var desc  = form.desc.value;
              
                var msg = document.getElementById('msg');
                const URL='http://localhost:8080/Projeto_loja_js/catController';
               
                fetch(URL,{
                  method:'POST',
                  headers:{
                      'Content-Type':'application/x-www-form-urlencoded'
                  },
                  body:'act=inserir&nome='+nome+'&desc='+desc
                }) 
                     .then(res => res.json())
                     .then(obj =>{
                         if(obj.nome !== '')
                            {
                                msg.innerHTML = 'Cadastro realizado com Sucesso';
                                form.reset();
                            }else{
                                
                                 form.reset();
                            }
                     })
                             .catch(err => console.log(err));
            }
        
 function selectCategorias02(div , param='')
        {
            var query = location.search.slice(1);
            var partes = query.split('&');
            var catFk=0;
            
            if(partes.length > 0)
                {
                    var param = '?act=prod&'+partes[0];
                    const URLp='http://localhost:8080/Projeto_loja_js/prodControler'+param;
                    fetch(URLp,{
                        method:'GET'
                    })
                            .then(res => res.json())
                            .then(obj =>{catFk = obj[0].categorias_idcategorias;
                                          
                                            console.log('DENTRO DO ESCOPO :'+catFk);
                                   })
                                    .catch(err => console.log(err));
                }
            
             console.log('FORA DO ESCOPO '+catFk);
            const URL='http://localhost:8080/Projeto_loja_js/catController';
            
            fetch(URL,{
                method: 'GET'
            })
                    .then(res => res.json())
                    .then(catVet => {
                        console.log(catVet);
                        console.log(catFk);
                        var opc = document.getElementById(div).innerHTML;
                        let selected='';
                        for(i=0; i< catVet.length; i++)
                            {
                                if(catVet[i].idcategorias === catFk)
                                    {
                                        selected  =' selected="selected"';
                                    }else{
                                        selected='';
                                    }
                                  opc +=' <option'+ selected +' value="'+catVet[i].idcategorias+'">'+catVet[i].nome+'</option>';
                                 
                            }
                            
                            document.getElementById(div).innerHTML = opc;
                    })
                            .catch(err => console.log(err));
            
        }
        
        function motraTodasCat(div)
            {
                 var param = '?act=all';
                 const URL='http://localhost:8080/Projeto_loja_js/catController'+param;
                 
                 fetch(URL,{
                     method: 'GET'
                 })
                         .then(res => res.json())
                         .then(catVet => {
                          var table = document.getElementById(div).innerHTML; 
                             for(i=0; i < catVet.length; i++)
                                {
                                    table +='<tr>';
                                        table += '<td>'+catVet[i].idcategorias+'</td>';
                                        table += '<td>'+catVet[i].nome+'</td>';
                                        table += '<td>'+catVet[i].descricao+'</td>'
                                        table += '<td>'+catVet[i].ativo+'</td>'
                                        table +=  '<td> <a href="categoria_update.html?idc='+catVet[i].idcategorias+'" class="btn btn-warning">[A]</a> </td>';
                                         table +=  '<td> <a href="javascript:deleteCat('+catVet[i].idcategorias+')" class="btn btn-warning">[x]</a> </td>';
                                    table +='</tr>';
                                }
                                
                                document.getElementById(div).innerHTML = table;
                             
                         })
                                 .catch(err => console.log(err));
            }
            
        
        function selectProduto(form)
        {
            var query  =location.search.slice(1);
            var partes = query.split('&');
        
          var param = '?act=prod&'+partes[0];
          const URL='http://localhost:8080/Projeto_loja_js/prodControler'+param;
            
            fetch(URL,{
                
                method:'GET'
            })
                    .then(res => res.json())
                    .then(obj => {
                        
                         console.log(obj);
                         form.id.value =       obj[0].idprodutos;
                         form.nome.value     = obj[0].nome;
                         form.descr.value    = obj[0].descricao;
                         form.maisinfo.value = obj[0].maisinfo;
                         form.peso.value     = obj[0].peso;
                         form.val.value      = obj[0].valor;
                         form.dtq.value      = obj[0].destaque;
                    })
                            .catch(err => console.log(err));
        }
        
        function selecionaTProdutos(div,param ='',met='GET')
            {
                var met  = met.toUpperCase();
        var bod  = '';
        
        if((param !== null || param !== '') && met === 'GET')
            {
                param = param = "?"+param;
            }else if(met === 'POST')
            {
                bod = 'body: '+param;
            }
            var query = location.search.slice(1);
            var partes = query.split('&');
            var param = param+'&'+partes[0];
        const URL='http://localhost:8080/Projeto_loja_js/prodControler'+param;
        
        console.log(URL);
        
        fetch(URL,{
            methodo:met,
        })
                .then(res => res.json())
                .then(obj => {
                    console.log(obj)
             let prod = '';
                    for(i=0; i<obj.length; i++)
                        {
                          prod += '<tr>';
                          prod += '<td>'+obj[i].idprodutos+'</td>';
                          prod += '<td>'+obj[i].nome+'</td>';
                          prod += '<td>'+obj[i].valor+'</td>';
                          prod += '<td>'+obj[i].peso+'</td>';
                          prod += '<td>'+obj[i].destaque+'</td>';
                          prod += '<td>'+obj[i].ativo+'</td>';
                          prod += '<td> <a href="produto_update02.html?idP='+obj[i].idprodutos+'" class="btn btn-warning">[A]</a> </td>';
                         
                          prod += '</tr>';
                        }
                        document.getElementById(div).innerHTML = prod;
                })
                        .catch(err => console.log(err))
            }
            
function atualizarProd(form)
    {
            var idP   = form.id.value;
            var nome  = form.nome.value;
            var desc  = form.descr.value;
            var maisI = form.maisinfo.value;
            var peso  = form.peso.value;
            var valor = form.val.value;
            var idCat = document.getElementById('catId').value;
            var dtq   = form.dtq.value;
            var msg = document.getElementById('msg');
             const URL='http://localhost:8080/Projeto_loja_js/prodControler';
             
              fetch(URL,{
                 
                 method: 'POST',
                 headers:{
                    'Content-Type':'application/x-www-form-urlencoded'
                 },
                 body:'act=update&idc='+idCat+'&nome='+nome+'&descr='+desc+'&maisinfo='+maisI+'&peso='+peso+'&valor='+valor+'&dtq='+dtq+'&idP='+idP
             })
                     .then(res => res.json())
                     .then(obj =>{
                         if(obj.nome !== '')
                            {
                                msg.innerHTML = 'Atualização realizado com Sucesso';
                                form.reset();
                            }else{
                                
                                 form.reset();
                            }
                     })
                             .catch(err => console.log(err));
        }
        
        function deleteProd(idP)
        {
            var ok = confirm('Tem certeza que deseja realizar a exclusão desse registro ?');
                
                if(ok)
                    {
                         var msg = document.getElementById('msg');
                         const URL='http://localhost:8080/Projeto_loja_js/prodControler';
                        fetch(URL,{
                            method:'POST',
                            headers:{
                                'Content-Type':'application/x-www-form-urlencoded'
                            },
                            body:'act=delete&idP='+idP
                        })
                                .then(res => res.json())
                                .then(obj =>{
                                    console.log(obj);
                                    if(obj.nome !== ''){
                                       location.reload();
                                        msg.innerHTML = 'Realizado com sucesso';
                                    }else{
                                        msg.innerHTML = 'Não';
                                    }
                                })
                                       .catch(err => console.log(err));
                    }
        }
                   
