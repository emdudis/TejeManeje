<%-- 
    Document   : registrarProducto
    Created on : 09-dic-2021, 21:37:11
    Author     : Marivi
--%>

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
        <h2 align="center">Cargar Imagen</h2>
        <table border="0" width="800" align="center">
            <form action="subirImagen.jsp" enctype="multipart/form-data" method="post">
                <tr bgcolor="skyblue">
                    <th>Imagen</th>
                    <th><input type="file" name="file"/></th>
                </tr><tr>
                    <th colspan="2">
                        <input type="submit" value="Registrar" name="CargarImagen"/>
                    </th>
                </tr>
            </form>
        </table>
    </body>
</html>
