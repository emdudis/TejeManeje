package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;



public class VentaDAO {
    
    public static boolean insertarVenta(Venta varventa, ArrayList<DetalleCarrito> d){
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
        int codVen=0;
        PreparedStatement pst2=null;
        PreparedStatement pst3=null;
        try{
            String sql1="SELECT MAX(codigoVenta) FROM venta";
            pst1=conexion.prepareStatement(sql1);
            ResultSet rs=pst1.executeQuery();
            while(rs.next()){
               codVen = rs.getInt(1);
            }
            if (codVen!=0){
                codVen++;
            }      
           System.out.println("EL SIGUIENTE CODIGO DE VENTA QUE TENEMOS QUE ADJUDICAR ES EL: "+codVen);
            String sql2="INSERT INTO venta VALUES(?, ?, null)";
            //String sql2="INSERT INTO venta VALUES(?, ?)";
            pst2=conexion.prepareStatement(sql2);
            pst2.setInt(1, codVen);
            pst2.setString(2, varventa.getCliente());
            //pst2.setDate(3, date.parseDate('2021-12-11'));
            int i=pst2.executeUpdate();
          
            int i2=0;
            String sql3="INSERT INTO detallecarrito VALUES(?, ?, ?)";
            pst3=conexion.prepareStatement(sql3);
            for(DetalleCarrito aux: d){
                pst3.setInt(1, codVen);
                pst3.setInt(2, aux.getCodigoProducto());
                pst3.setDouble(3, aux.getCantidad());
                i2=pst3.executeUpdate();
            }
          
            //le pregunto si el detalle ha sido insertado, porque habiéndose insertado 
            //el detalle entonces la venta también lo habrá sido:
            if(i==1){
                rspt=true;
            }else{
                rspt=false;
            }
            pst1.close();
            pst2.close();
            pst3.close();
            conexion.close();
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
        return rspt; 
        
    }
    
    public static ArrayList<Venta> obtenerVentas(){
        ArrayList<Venta> lista=new ArrayList<Venta>();
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
        
        String sql="";
        PreparedStatement pst=null;
        try{
            sql="SELECT * FROM venta";
            pst=conexion.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                Venta v= new Venta(rs.getInt(1),rs.getString(2),
                        rs.getDate(3));
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
