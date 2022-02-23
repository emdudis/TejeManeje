package Utils;

import java.sql.Connection;
import java.sql.DriverManager;


public class Conexion {

       
    public static Connection getConexion(){
    
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
        
        return conexion;
    }
    
    public static void main(String[] args) {
        System.out.println("intentando conectar con ");
        Conexion.getConexion();
        
          
    }
    
}
