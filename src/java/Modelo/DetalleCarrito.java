package Modelo;


public class DetalleCarrito {
  
    private int codigoVenta;
    private int codigoProducto;
    private double cantidad;
    //private double envio;
    private Producto producto;
    private Venta venta;

    
    
    
    public DetalleCarrito(int codigoVenta, int codigoProducto, double cantidad, /*double envio,*/ Producto producto, Venta venta) {
        this.codigoVenta = codigoVenta;
        this.codigoProducto = codigoProducto;
        this.cantidad = cantidad;
        //this.envio = envio;
        this.producto = producto;
        this.venta = venta;
    }

    public DetalleCarrito() {
       
    }

    public DetalleCarrito(int codigoVenta, int codigoProducto, double cantidad){
        this.codigoVenta=codigoVenta;
        this.codigoProducto=codigoProducto;
        this.cantidad=cantidad;
    }
    
    public int getCodigoVenta() {
        return codigoVenta;
    }

    public void setCodigoVenta(int codigoVenta) {
        this.codigoVenta = codigoVenta;
    }

    public int getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }
    /*
    public double getEnvio() {
        return envio;
    }

    public void setEnvio(double envio) {
        this.envio = envio;
    }
    */
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }
    
    
}