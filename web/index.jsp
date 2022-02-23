<%-- 
    Document   : index
    Created on : 06-dic-2021, 19:28:40
    Author     : Marivi
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Modelo.Producto"%>
<%@page import="Modelo.ProductoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<%
    String usu="";
    String nom="";
    HttpSession sesionOK=request.getSession();
    if(sesionOK.getAttribute("perfil")!=null)
        nom=(String)sesionOK.getAttribute("nom")+" "+(String)sesionOK.getAttribute("ape");
    
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Carrito tienda online</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/estilos.css">
        <link rel="stylesheet" href="css/owl.carousel.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="css/flaticon.css">  
    </head>
    <body>
        <nav class="navbar navbar-default navbar-fixed-top">
            <div class="container">
              <div class="navbar-header">
                <a class="navbar-brand" href="#">TeJEMANEJE</a>
              </div>
              <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                  <li><a href="index.jsp">Catálogo</a></li>
                      <% 
                          if(sesionOK.getAttribute("perfil")!=null && sesionOK.getAttribute("perfil").equals("admin")){
                      %> 
                              <li><a href="registrarProducto.jsp">Registrar Producto</a></li>
                      <%
                          }
                          if(sesionOK.getAttribute("perfil")!=null ){  
                      %>
                              <li><a href="registrarVenta.jsp">Registrar ventas</a></li>
                      <%
                          }
                          if(sesionOK.getAttribute("perfil")!=null && sesionOK.getAttribute("perfil").equals("admin")){
                      %>
                              <li><a href="ServletControlador?accion=MostrarVentas">Consultar Ventas</a></li>
                      <%
                          }
                          if(sesionOK.getAttribute("perfil")!=null){
                      %>    
                              <li><a href="ServletLogueo?accion=cerrar">Cerrar Sesión</a></li>
                      <%
                          }        
                      %>
                </ul>      
                <ul class="nav navbar-nav navbar-right">
                    <%
                       if(sesionOK.getAttribute("perfil")!=null){
                    %>
                            <li><a href="#">Bienvenid@ <%= nom%></a></li>
                    <%
                       }
                       if(sesionOK.getAttribute("perfil")==null){
                    %>
                            <li><a href="login.jsp">Iniciar Sesión</a></li>
                    <%
                        }
                    %> 
                </ul>
              </div>
            </div>
        </nav>
        <!-- START SLIDER -->
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <div class="jumbotron">
                       <h1>Labores</h1>
                       <p>Gema & Marta</p>
                       <p>TeJEMANEJE</p>
                     </div>
                </div>
            </div>
        </div>
        <!-- END SLIDER -->
        <!-- START PRODUCTO -->
        <section id="producto">
           <div class="container">
               <div class="row">
                   <div class="col-md-12">
                       <div class="titulo">
                           <h2>Nuestras Labores</h2>
                           <div class="hr"></div>
                       </div>
                   </div>
                   <%
                        ArrayList<Producto> lista = ProductoDAO.obtenerProductos();
                        int salto=0;
                        for(Producto p: lista){
                   %>
                            <div class="col-sm-4 col-lg-4 col-md-4">
                                 <div class="thumbnail text-center">                            
                                     <img src="img/<%=p.getImagen()%>" class="img-responsive" alt="">                                                                                         
                                     <div class="caption">
                                         <h4 class="pull-right precio"><%=p.getPrecio()%></h4>
                                         <h4><a href="#"><%=p.getNombre()%></a>
                                         </h4>
                                         <% 
                                             if(sesionOK.getAttribute("perfil")!=null && sesionOK.getAttribute("perfil").equals("admin")){
                                         %> 
                                             <a class="btn btn-primary" href="actualizarProducto.jsp?id=<%=p.getCodigoProducto()%>">Modificar</a>
                                         <%
                                             }
                                         %>     
                                             <a class="btn btn-primary" href="anadirCarrito.jsp?id=<%= p.getCodigoProducto()%>">Detalle</a>
                                     </div>                                
                                 </div>
                             </div>
                    <%
                        }
                    %>
                </div>
           </div>
        </section>
        <script src="js/jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/owl.carousel.js"></script>
        <script src="js/code.js"></script>
    </body>
</html>