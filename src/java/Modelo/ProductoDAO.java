package Modelo;

import Utils.Conexion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



public class ProductoDAO {
 
    
    public static ArrayList<Producto> obtenerProductos(){
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
        
        //Statement stmt=Conexion.getConexion().createStatement();
        PreparedStatement pst=null;
        Producto p=null;
        ArrayList<Producto> lista=new ArrayList();
        try{
           String sql="SELECT * FROM producto";
           pst=conexion.prepareStatement(sql);
           ResultSet rs=pst.executeQuery();
           while(rs.next()){
               p = new Producto(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4),
                rs.getString(5), rs.getString(6));
                lista.add(p);
           }
            /*for(int i=0; i<lista.size(); i++){
                
                System.out.println(p.getCodigoProducto());
                System.out.println(p.getNombre());
                System.out.println(p.getPrecio());
                System.out.println(p.getImagen());
                System.out.println(p.getAutora());
                System.out.println(p.getTalla());
            }
            pst.close();
            conexion.close();*/
        }catch(Exception e){
            System.out.println(e);
        }  
        return lista;
    }
    
    public static Producto obtenerProducto(int codigo){
        
        Producto p=null;
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
        
        //Statement stmt=Conexion.getConexion().createStatement();
        PreparedStatement pst=null;
        try{
           String sql="SELECT * FROM producto WHERE codigoProducto="+codigo;
           pst=conexion.prepareStatement(sql);
           ResultSet rs=pst.executeQuery();
           while(rs.next()){
               p = new Producto(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4),
                rs.getString(5), rs.getString(6));
           }
           //System.out.println(p.getCodigoProducto() + p.getNombre());
           pst.close();
           conexion.close();
        }catch(Exception e){
            System.out.println(e);
        } 
        return p;
    }
    
    public static boolean actualizarProducto(Producto varproducto){
        
        boolean modificado=false;
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
            String sql="update producto set codigoProducto=?, nombre=?, precio=?, autora=?, talla=? where codigoProducto=?";
            pst=conexion.prepareStatement(sql);
            pst.setInt(1, varproducto.getCodigoProducto());
            pst.setString(2, varproducto.getNombre());
            pst.setDouble(3, varproducto.getPrecio());
            pst.setString(4, varproducto.getAutora());
            pst.setString(5, varproducto.getTalla());
            pst.setInt(6, varproducto.getCodigoProducto());
            //pst.execute();
            int i=pst.executeUpdate();
            if(i==1){
                modificado=true;
            }else{
                modificado=false;
            }
        }catch(SQLException exc){
            exc.printStackTrace();
            modificado=false;
        } 
        System.out.println("Se ha actualizado el producto?= "+modificado);
        return modificado;
        
    }
    
    public static boolean insertarProducto(Producto p){
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
        
        PreparedStatement pst1=null;
        int ultCodProd=0;
        PreparedStatement pst2=null;
        try{
            String sql1="SELECT MAX(codigoProducto) FROM producto";
            pst1=conexion.prepareStatement(sql1);
            ResultSet rs=pst1.executeQuery();
            while(rs.next()){
               ultCodProd = rs.getInt(1);
            }
           System.out.println("EL ULTIMO CODIGO DE PRODUCTO QUE TENEMOS EN LA TABLA PRODUCTO ES EL: "+ultCodProd);
            String sql2="INSERT INTO producto VALUES(?, ?, ?, ?, ?, ?)";
            pst2=conexion.prepareStatement(sql2);
            pst2.setInt(1, ultCodProd+1);
            pst2.setString(2, p.getNombre());
            pst2.setDouble(3, p.getPrecio());
            pst2.setString(4, p.getImagen());
            pst2.setString(5, p.getAutora());
            pst2.setString(6, p.getTalla());
            int i=pst2.executeUpdate();
            if(i==1){
                rspt=true;
            }else{
                rspt=false;
            }
            pst1.close();
            pst2.close();
            conexion.close();
        }catch(Exception e){
            System.out.println(e);
        }
        return rspt;
    }
    
}