<%-- 
    Document   : obtenerDetalleVenta
    Created on : 11-dic-2021, 18:35:46
    Author     : Marivi
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Modelo.DetalleCarrito"%>
<%@page import="Modelo.DetalleCarritoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <table border="1" width="600" align="center">
            <tr bgcolor="skyblue">
                <th>CodPro</th>
                <th>Cantidad</th>
            </tr>
            <%
               ArrayList<DetalleCarrito> lista=DetalleCarritoDAO.obtenerDetalleVenta(Integer.parseInt(request.getParameter("cod")));
               for(int i=0; i<lista.size(); i++){
                    DetalleCarrito d=lista.get(i);
            
            %>  
                    <tr>
                        <td><%=d.getCodigoProducto() %></td>
                        <td><%=d.getCantidad() %></td>
                    </tr>
            <%
               }
            %>
        </table>
    </body>
</html>
