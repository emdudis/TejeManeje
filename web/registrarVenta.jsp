<%-- 
    Document   : registrarVenta
    Created on : 08-dic-2021, 11:54:19
    Author     : Marivi
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Modelo.DetalleCarrito"%>
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
        <h2 align="center">Carrito de Compra</h2>
        <%-- Debajo de la barra del menú, que tenemos en todas las Vistas, desarrollo el
        códgio: necesito ahora obtener los datos de sesion y mostrar los productos que el
        usuario ha estado seleccionando; Y además se hace dentro de un formulario para luego
        poder enviar esa información al ServletControlador, identificando esta nueva accion con 
        un value, por ejemplo: value="RegistrarVenta":
        --%>
        <form method="post" action="ServletControlador">
            <input type="hidden" name="accion" value="RegistrarVenta"/>
            <table border="1" align="center" width="450">
               
                <%--temporalmente hacemos una cajita de texto para obtener el nombre del cliente
                pero solo de manera provisional hasta que tengamos la sesión del usuario--%>
               
                <input type="hidden" name="txtCliente" value="<%
                        if(sesionOK.getAttribute("perfil")!=null)
                        out.println(nom);%>" readonly="readonly">
                <tr style="background-color: skyblue; color: black; font-weight: bold">
                    <td with="180">Nombre</td>
                    <td>Precio</td>
                    <td>Cantidad</td>
                    <td>Sub. Total</td>
                </tr>
                <%-- Mostramos los productos que tenemos en la sesion que se llama carrito --%>
                <%
                 double total=0;
                 double envio=6;
                 ArrayList<DetalleCarrito> lista=(ArrayList<DetalleCarrito>)session.getAttribute("carrito");
                 if(lista!=null){
                    for(DetalleCarrito d: lista){
                %> 
                        
                    <tr>
                        <td><%= d.getProducto().getNombre()%></td>
                        <td><%= d.getProducto().getPrecio()%></td>
                        <td><%= d.getCantidad()%></td>
                        <td align="center"><%=String.format("%.2f",(d.getProducto().getPrecio()*d.getCantidad()))%></td>
                    </tr>
                    
                <%
                    total=total+(d.getProducto().getPrecio()*d.getCantidad());
                   }
                    if(total>=49){
                        envio=0;
                    }else{
                       total+=envio;
                    }
                 } 
                %>
                    <tr>
                        <th colspan="3" align="right">Envío</th>
                        <th><%=envio%></th>
                    </tr>
                    <tr>
                        <th colspan="3" align="right">Total</th>
                        <th><%=String.format("%.2f",total)%></th>
                    </tr>
                    <tr>
                        <th colspan="5">
                            <input type="submit" value="Registrar Venta" name="btnVenta"/></th>
                    </tr>
            </table>
            <input type="hidden" name="total" value="<%=total%>" />
        </form>
        <div>
            <h3 align="center"><a href="index.jsp">Seguir Comprando</a> ||
                <a href="ServletLogueo?accion=cancelar">Cancelar Compra</a>
            </h3>
        </div>
    </body>
</html>
