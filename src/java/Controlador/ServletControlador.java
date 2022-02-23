package Controlador;

import Modelo.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebServlet(name = "ServletControlador", urlPatterns = {"/ServletControlador"})
public class ServletControlador extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        String accion=request.getParameter("accion");
        
        if(accion.equals("AnadirCarrito")){
            this.anadirCarrito(request, response);
        }else if(accion.equals("ModificarProducto")){
            this.actualizarProducto(request, response);
        }else if(accion.equals("RegistrarProducto")){
            this.registrarProducto(request, response);
        }else if(accion.equals("RegistrarVenta")){
            this.registrarVenta(request, response);
        }else if(accion.equals("MostrarVentas")){
            this.mostrarVentas(request, response);
        }else if(accion.equals("registrarNuevoUsuario")){
            this.registrarNuevoUsuario(request, response);
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void anadirCarrito(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        //Primero traemos la sesión activa:
        HttpSession sesion=request.getSession();
        
        //Segundo, creo la variable de tipo arraylist para ir almacenando los productos:
        ArrayList<DetalleCarrito> carrito;
        
        //Si la sesión es null quiere decir que la lista aun está vacía, si no, es que ya
        //agregué algun producto:        
        if(sesion.getAttribute("carrito")==null){
            carrito = new ArrayList<DetalleCarrito>();
        }else{
            carrito = (ArrayList<DetalleCarrito>)sesion.getAttribute("carrito");
        }
       
        //Ahora necesito traer los datos del producto seleccionado por el comprador, y para ello
        //utilizo un método que ya tengo y al que le tengo que pasar como parametro
        //el codigo que me envían desde la página anadirCarrito:
        Producto p=ProductoDAO.obtenerProducto(Integer.parseInt(request.getParameter("txtCodigo")));
        System.out.println("estamos aquiiiiiiiiiiiiiiii, mostrando el txtCodigo "+p.getCodigoProducto());
        //A continuación necesito un objeto de tipo DetalleCarrito, para cumplimentarlo
        //con los datos nuevos que me está enviando el usuario de la aplicación:
        DetalleCarrito d=new DetalleCarrito();
        d.setCodigoProducto(Integer.parseInt(request.getParameter("txtCodigo")));
        d.setProducto(p);
        d.setCantidad(Double.parseDouble(request.getParameter("txtCantidad")));
        
        //Calculo el subtotal:
        double subTotal=p.getPrecio()*d.getCantidad();
        
        //Controlo que el producto que estoy añadiendo a la cesta NO estuviera ya antes en dicha cesta
        //para evitar duplicados:
        int indice=-1;
        
        for(int i=0; i<carrito.size(); i++){
            DetalleCarrito car=carrito.get(i);
            //si el producto que te han traido para que metas en el carrito, ya lo tienes, NO
            //lo vas a volver a meter, harás otras cosas con él.....pero NO meterlo como
            //otro producto:
            if(car.getCodigoProducto()== p.getCodigoProducto()){
                indice=i;
            }  
        }
        //aquí digo, si el producto no se ha encontrado en el carrito, lo añado:
        if(indice==-1){
            carrito.add(d);
        }
        //y este carrito lo vuelvo a meter en la sesión, para que haya persistencia de datos cuando
        //navegue de unas páginas a otras:
        sesion.setAttribute("carrito", carrito);
        //Todo esto lo redirecciono a la siguiente vista que es donde va a mostrar
        //la descripción de la venta que se ha hecho a este usuario (y que es una pagina
        //jsp que es lo siguiente que nos toca crear):
        response.sendRedirect("registrarVenta.jsp");
        
    }

    private void actualizarProducto(HttpServletRequest request, HttpServletResponse response) 
         throws ServletException, IOException {
        
        Producto p=new Producto(Integer.parseInt(request.getParameter("txtCodigo")), 
                                request.getParameter(("txtNombre").toString()), 
                                Double.parseDouble(request.getParameter("txtPrecio")), 
                                request.getParameter(("txtAutora").toString()),
                                request.getParameter(("txtTalla").toString()));
        boolean modificado=ProductoDAO.actualizarProducto(p);
        if(modificado){
            response.sendRedirect("mensaje.jsp?men=Se actualizo el producto de manera correcta");
        }else{
            response.sendRedirect("mensaje.jsp?men=No se actualizo el producto");
        }
        
    }

    private void registrarProducto(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException{
    
        String pro=request.getParameter("txtNombre");
        double pre=Double.parseDouble(request.getParameter("txtPrecio"));
        String img=request.getParameter("txtImagen");
        String aut=request.getParameter("txtAutora");
        String tal=request.getParameter("txtTalla");
        
        Producto p=new Producto(pro, pre, img, aut, tal);
        boolean rspt=ProductoDAO.insertarProducto(p);
        if(rspt){
            response.sendRedirect("mensaje.jsp?men=Se registro el nuevo item de manera correcta");
        }else{
            response.sendRedirect("mensaje.jsp?men=NO se ha podido registrar el nuevo producto en la base de datos.");
        }
    
    
    }

    private void registrarVenta(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException{
        
        //Los datos del carrito de compra se encuentran almacenados en variables
        //de sesión, por eso la tengo que declarar también aquí:
        HttpSession sesion=request.getSession();
        
        //creo un objeto de tipo Venta vacío:
        Venta v= new Venta();        
       
        //Y aquí estoy recibiendo el cliente desde la cajita de texto que
        //incorporamos temporalmente al formulario del carrito de compra:
        v.setCliente(request.getParameter("txtCliente").toUpperCase());
        
        System.out.println("El comprador esta vez se llama: "+v.getCliente());
        
        //Necesitamos la colección con los productos vendidos, porque es un parámetro
        //que nos va a pedir el método insertarVenta de la clase VentaDAO;
        // y ese detalle de la venta lo tenemos guardado en la sesión "carrito":
        ArrayList<DetalleCarrito> detalle = (ArrayList<DetalleCarrito>)sesion.getAttribute("carrito");        
        boolean rpta=VentaDAO.insertarVenta(v, detalle);
        
        
        //ESTO ES POSTERIOR A QUE EL DETALLE DE CARRITO SE HAYA REGISTRADO EN LA BASE DE DATOS
        //Y ES aquí y ahora que procedo a recibir el monto total de la compra:
        double total=Double.parseDouble(request.getParameter("total"));
        //PARA A RENGLON SEGUIDO REDIRECCIONAR ESE MONTO AL FORMULARIO DE PAGO
        //QUE SE LO VA A ENVIAR A LA PAGINA PAYPAL para pagar:
        //
        //
        if(rpta){
            //si ya se logra grabar el carrito, se elimina la sesión, para que 
            //que no se quede el carrito ahí grabado con las cosas para las
            //siguientes compras:
            //request.getSession().removeAttribute("carrito");
            response.sendRedirect("FormularioPago.jsp?total="+total);
            //response.sendRedirect("mensaje.jsp?men=El detalle de la venta se ha registrado correctamente");
        }else{
            response.sendRedirect("mensaje.jsp?men=No se han podido registrar los detalles de la venta");
        }
        
        
    }

    private void mostrarVentas(HttpServletRequest request, HttpServletResponse response) 
       throws ServletException, IOException{
        
        ArrayList<Venta> lista=new ArrayList<>();
        lista=VentaDAO.obtenerVentas();
        for(int i=0; i<lista.size(); i++){
            System.out.println("AQUI RECIBIMOS DEL METODO DAO EL CODIGO VENTA: "+lista.get(i).getCodigoVenta());
            System.out.println("AQUI RECIBIMOS DEL METDO DAO EL CODIGO CLIENTE: "+lista.get(i).getCliente());
            System.out.println("AQUI RECIBIMOS DEL METODO DAO LA FECHA, QUE PUEDE SER NULL: "+lista.get(i).getFecha());
        }
        request.setAttribute("lista", lista);
        request.getRequestDispatcher("consultarVentas.jsp").forward(request, response);        
        
    }

    private void registrarNuevoUsuario(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        
        Usuario u=new Usuario(request.getParameter("txtNombre"), 
                              request.getParameter("txtApellido1"),
                              request.getParameter("txtApellido2"),
                              request.getParameter("txtDireccion"),
                              request.getParameter("txtPoblacion"),
                              request.getParameter("txtProvincia"),
                              request.getParameter("txtCodigoPostal"),
                              request.getParameter("txtEmail"),
                              request.getParameter("txtClave"),
                              request.getParameter("txtMovil"));
        
        boolean rspt=UsuarioDAO.insertarUsuario(u);
        if(rspt){
            response.sendRedirect("mensaje.jsp?men=Se ha registrado correctamente");
        }else{
            response.sendRedirect("mensaje.jsp?men=Error en el registro, vuelva a intentarlo por favor");
        }
        
        
        
    }
    

}