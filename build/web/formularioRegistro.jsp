<%-- 
    Document   : formularioRegistro
    Created on : 12-dic-2021, 12:13:49
    Author     : Marivi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page session="true" %>
<%
    String usu="";
    String nom="";
    HttpSession sesionOK=request.getSession();
    if(sesionOK.getAttribute("perfil")!=null)
        nom=(String)sesionOK.getAttribute("nom")+" "+(String)sesionOK.getAttribute("ape");
    
%>
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
        <h2 align="center">Formulario de Registro</h2>    
        <table border="0" width="500" align="center">
            <form action="ServletControlador" method="post">
            <input type="hidden" name="accion" value="registrarNuevoUsuario"/>
                <tr>
                    <td>Nombre: </td>
                    <td><input type="text" name="txtNombre"></td>
                </tr>
                <tr>
                    <td>Primer apellido: </td>
                    <td><input type="text" name="txtApellido1"></td>
                </tr>
                <tr>
                    <td>Segundo apellido: </td>
                    <td><input type="text" name="txtApellido2"></td>
                </tr>
                <tr>
                    <td>Dirección: </td>
                    <td><input type="text" name="txtDireccion"></td>
                </tr>
                <tr>
                    <td>Población: </td>
                    <td><input type="text" name="txtPoblacion"></td>
                </tr>
                <tr>
                    <td>Provincia: </td>
                    <td><input type="text" name="txtProvincia"></td>
                </tr>
                <tr>
                    <td>Código postal: </td>
                    <td><input type="text" name="txtCodigoPostal"></td>
                </tr>
                <tr>
                    <td>Email: </td>
                    <td><input type="text" name="txtEmail"></td>
                </tr>
                <tr>
                    <td>Clave de usuario: </td>
                    <td><input type="text" name="txtClave"></td>
                </tr>
                <tr>
                    <td>Móvil: </td>
                    <td><input type="text" name="txtMovil"></td>
                </tr><tr>
                    <th colspan="2">
                        <input type="submit" name="btnRegistro" value="Registrarse">
                    </th>
                </tr>
            </form>
        </table>
    </body>
</html>
