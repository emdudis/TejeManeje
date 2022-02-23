<%-- 
    Document   : actualizarProducto
    Created on : 09-dic-2021, 11:32:21
    Author     : Marivi
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Modelo.*" %>
<%@page import="java.util.*" %>
<%@page session="true" %>
<%
    String usu="";
    String nom="";
    HttpSession sesionOK=request.getSession();
    if(sesionOK.getAttribute("perfil")!=null)
        nom=(String)sesionOK.getAttribute("nom")+" "+(String)sesionOK.getAttribute("ape");
%>

<%

    Producto p = ProductoDAO.obtenerProducto(Integer.parseInt(request.getParameter("id")));
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
        <h2 align="center">Actualizar producto</h2>
        <form method="post" action="ServletControlador">
            <table border="0" width="400" align="center">
                <tr>
                    <td>Código: </td>
                    <td><input type="text" name="txtCodigo" value="<%=p.getCodigoProducto()%>" readonly="true"></td>
                </tr><tr>
                    <td>Nombre: </td>
                    <td><input type="text" name="txtNombre" value="<%=p.getNombre()%>"></td>
                </tr><tr>
                    <td>Precio: </td>
                    <td><input type="text" name="txtPrecio" value="<%=p.getPrecio()%>"></td>
                </tr><tr>
                    <td>Autora: </td>
                    <td><input type="text" name="txtAutora" value="<%=p.getAutora()%>"></td>
                </tr><tr>
                    <td>Talla: </td>
                    <td><input type="text" name="txtTalla" value="<%=p.getTalla()%>"></td>
                </tr><tr>
                    <td colspan="3"><input type="submit" name="btnActualizar" value="Actualizar"></td>
                </tr>
                <input type="hidden" name="accion" value="ModificarProducto"/>
            </table>
        </form>
    </body>
</html>
