package Controlador;

import Modelo.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ServletLogueo extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion=request.getParameter("accion");
        if(accion.equals("loguin")){
            String usu=request.getParameter("txtUsu");
            String pas=request.getParameter("txtPas");
            Connection conexion=null;
            try{
                Class.forName("com.mysql.jdbc.Driver");
            }catch(Exception e){
                e.printStackTrace();
            } 

            String usuario="root";
            String password="mysql";
            String bd="carrito";
            String url="jdbc:mysql:///"+bd;

            try{
                conexion=DriverManager.getConnection(url, usuario, password);
            }catch(Exception e){
                System.out.println("Error al establecer la conexion con "+url);
            }

            if(conexion!=null){
                 System.out.println("Conexion establecida con bd= "+bd);
            }
            PreparedStatement pst=null;
            try{
               String sql="select * from usuarios where nomUsu=? and claveUsu=?";
               pst=conexion.prepareStatement(sql);
               pst.setString(1, usu);
               pst.setString(2, pas);
               ResultSet rs=pst.executeQuery();
               
            
               if(rs.next()){
                  HttpSession sesionOK = request.getSession();
                  sesionOK.setAttribute("perfil", (String)rs.getString(9));
                  sesionOK.setAttribute("nom", (String)rs.getString(2));
                  sesionOK.setAttribute("ape", (String)rs.getString(3));
                  request.getRequestDispatcher("index.jsp").forward(request, response);
               }else{
                  request.setAttribute("msg", "Usuario o Contraseña incorrectos");
                  request.getRequestDispatcher("login.jsp").forward(request, response);
               }
               
               pst.close();
               conexion.close();
            }catch(Exception e){
                System.out.println(e);
            } 
        }else if(accion.equals("cerrar")){
            HttpSession sesionOK = request.getSession();
            request.getSession().removeAttribute("perfil");
            request.getSession().removeAttribute("nom");
            request.getSession().removeAttribute("ape");
            sesionOK.invalidate();
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }else if(accion.equals("cancelar")){
            HttpSession sesion=request.getSession();
            request.getSession().removeAttribute("carrito");
            request.getRequestDispatcher("index.jsp").forward(request, response);
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

}
