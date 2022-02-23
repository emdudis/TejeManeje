<%-- 
    Document   : anadirCarrito
    Created on : 07-dic-2021, 18:13:04
    Author     : Marivi
--%>

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
        
        if(sesionOK.getAttribute("perfil") == null){
%>
            <jsp:forward page="login.jsp"/>
<%
        }else{
            usu = (String)sesionOK.getAttribute("perfil");
        }
    
%>    


<!DOCTYPE html>
<%
    Producto p = ProductoDAO.obtenerProducto(Integer.parseInt(request.getParameter("id")));
    
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
        <h2 align="center">Añadir producto a Carrito</h2>
        <table border="0" width="500" align="center">
            <form method="post" action="ServletControlador">
                <tr>
                    <th rowspan="7"><img src="img/<%= p.getImagen() %>" width="170" height="170"></th>
                    <th>Código</th>
                    <th><input type="text" name="txtCodigo" value="<%= p.getCodigoProducto()%>" readonly/></th>
                    </tr><tr>
                    <th>Nombre</th>
                    <th><input type="text" name="txtNombre" value="<%= p.getNombre()%>" readonly/></th>
                    </tr><tr>
                    <th>Talla</th>
                    <th><input type="text" name="txtTalla" value="<%= p.getTalla()%>" readonly/></th>
                    </tr><tr>
                    <th>Autora</th>
                    <th><input type="text" name="txtAutora" value="<%= p.getAutora() %>" readonly/></th>
                    </tr><tr>
                    <th>Precio</th>
                    <th><input type="text" name="txtPrecio" value="<%= p.getPrecio() %>" readonly/></th>
                    </tr><tr>
                    <th>Cantidad</th>
                    <th><input type="number" value="0" min="0" name="txtCantidad" value="0"/></th>
                    </tr><tr>
                    <th colspan="3"><input type="submit" value="Añadir" name="btnAnadir"></th>    
                    </tr>
                    <input type="hidden" name="accion" value="AnadirCarrito"/>
            </form>
        </table>
    </body>
</html>
