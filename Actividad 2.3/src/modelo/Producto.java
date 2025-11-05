package modelo;

public class Producto {
    private String nombre;
    private int stock;
    
    public Producto(String nombre, int stockInicial) {
        this.nombre = nombre;
        this.stock = stockInicial;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public int getStock() {
        return stock;
    }
    
    public void setStock(int stock) {
        this.stock = stock;
    }
    
    @Override
    public String toString() {
        return "Producto: " + nombre + " | Stock: " + stock;
    }
}