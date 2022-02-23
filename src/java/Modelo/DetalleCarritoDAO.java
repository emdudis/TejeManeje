package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class DetalleCarritoDAO {

    public static ArrayList<DetalleCarrito> obtenerDetalleVenta(int num){
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
        
        ArrayList<DetalleCarrito> lista=new ArrayList<DetalleCarrito>();
        String sql="";
        PreparedStatement pst=null;
        try{
           sql="select * from detallecarrito where codigoVenta="+num;
           pst=conexion.prepareStatement(sql);
           ResultSet rs=pst.executeQuery();
           while(rs.next()){
               DetalleCarrito v = new DetalleCarrito(rs.getInt(1), rs.getInt(2), rs.getDouble(3));
               lista.add(v);
           }
           
           pst.close();
           conexion.close();
        }catch(Exception e){
            System.out.println(e);
        }
        return lista;
    }
    
    
}