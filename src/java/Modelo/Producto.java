package Modelo;

import java.util.ArrayList;


public class Producto {
    
    private int codigoProducto;
    private String nombre;
    private double precio;
    private String imagen;
    private String autora;
    private String talla;

    
    
    public Producto(int codigoProducto, String nombre, double precio, String imagen, String autora, String talla) {
        this.codigoProducto = codigoProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.imagen = imagen;
        this.autora = autora;
        this.talla = talla;
    }

    public Producto(int codigoProducto, String nombre, double precio, String autora, String talla) {
        this.codigoProducto = codigoProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.autora = autora;
        this.talla = talla;
    }

    public Producto(String pro, double pre, String img, String aut, String tal) {
       this.nombre=pro;
       this.precio=pre;
       this.imagen=img;
       this.autora=aut;
       this.talla=tal;
    }

    
    
    
    public int getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getAutora() {
        return autora;
    }

    public void setAutora(String autora) {
        this.autora = autora;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }
    
    
}