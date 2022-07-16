/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function listCategorias(div, param='')
{
    if(param !== null || param !== '')
        {
            param = "?"+param;
        }
    const URL='http://localhost:8080/Projeto_loja_js/catController'+param;
    
    fetch(URL,{
        
        methodo: 'GET'
    })
            .then(res => res.json())
            .then(obj =>{
                console.log(obj);
        let menu = document.getElementById(div).innerHTML;
        for(i=0; i<obj.length; i++)
            {
                menu = menu +'<li class="nav-item">';
                menu = menu +'<a class="nav-link" href="produtos.html?idc='+obj[i].idcategorias+'">'+obj[i].nome+'</a>';
                menu = menu + '</li>';
            }
            document.getElementById(div).innerHTML = menu;
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
                            divPro += ' <div class="col">';
                                 divPro += '<div class="card mb-4 rounded-3 shadow-sm">';
                                    divPro += ' <div class="card-header py-3">';
                                        divPro += '  <h4 class="my-0 fw-normal">'+obj[0].nome+'</h4>'
                                    divPro += '<\div>';
                                 
                                    divPro += ' <div class="card-body">';
                                        divPro += ' <h1 class="card-title pricing-card-title"> Valor ='+obj[0].valor+'</h1>';
                                        divPro += '<p>Descrição</p>';
                                        divPro += '<p> Descricão '+obj[0].descricao+'</p>';
                                    divPro += ' </div>';
                                 divPro += ' </div>';
                             divPro += ' </div>';
                            
                        }
                        
                        document.getElementById(div).innerHTML = divPro;
                     
                 })
                         .catch(err => console.log(err))
                 
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
             let divPro = '';
                    for(i=0; i<obj.length; i++)
                        {
                          divPro = divPro + ' <div class="col">';
                            divPro = divPro + ' <div class="card card-cover h-100 overflow-hidden text-white bg-dark rounded-5 shadow-lg">';
                               divPro = divPro +' <div class="card card-cover h-100 overflow-hidden text-white bg-dark rounded-5 shadow-lg  style="background-image: url(https://picsum.photos/400/200?random= 1);">'
                                divPro = divPro +'<div class="d-flex flex-column h-100 p-3 pb-3 text-white text-shadow-1">';
                                    divPro  = divPro +'<h3 class="pt-5 mt-5 mb-4 display-6 lh-1 fw-bold">'+obj[i].nome+'</h3>';
                                        
                                        divPro = divPro +' <ul class="d-flex list-unstyled mt-auto">';
                                            divPro = divPro +' <li class="me-auto">Valor = '+obj[i].valor+'</li>';
                                              divPro = divPro +' <li class="me-auto"><a href="detalhes.html?idP='+obj[i].idprodutos+'" class="w-100 btn btn-lg btn-outline-primary">Detalhes</a></li>';
                                            
                                            divPro = divPro+ '<li class="d-flex align-items-center me-3"> <small>'+obj[i].categorias_idcategorias+'</small></li>';
                                        divPro = divPro +'</ul>'
                                
                                divPro = divPro + '</div>'
                             divPro = divPro + '</div>'
                            divPro = divPro + '</div>'
                          divPro = divPro + '</div>'
                        }
                        document.getElementById(div).innerHTML = divPro;
                })
                        .catch(err => console.log(err))
    }


