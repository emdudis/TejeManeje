package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class UsuarioDAO {
    
    
     public static boolean insertarUsuario(Usuario u){
        boolean rspt=false;
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
            String sql="INSERT INTO usuarios VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pst=conexion.prepareStatement(sql);
            pst.setInt(1, 0);
            pst.setString(2, u.getNomUsu());
            pst.setString(3, u.getApellido1());
            pst.setString(4, u.getApellido2());
            pst.setString(5, u.getDomicilio());
            pst.setString(6, u.getPoblacion());
            pst.setString(7, u.getProvincia());
            pst.setString(8, u.getCodigoPostal());
            pst.setString(9, "Cliente");
            pst.setString(10, u.getCorreo());
            pst.setString(11, u.getClaveUsu());
            pst.setString(12, u.getTelefono());
            int i=pst.executeUpdate();
            if(i==1){
                rspt=true;
            }else{
                rspt=false;
            }
            pst.close();
            conexion.close();
        }catch(Exception e){
            System.out.println(e);
        }
        return rspt;
    }
    
    
    
    
    
    
    
    
    
    
    
}
