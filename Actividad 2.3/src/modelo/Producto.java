package modelo;  // Paquete del modelo

public class Producto {  // Clase que representa un producto
    private String nombre;  // Nombre del producto
    private int stock;      // Cantidad en stock
    
    public Producto(String nombre, int stockInicial) {  // Constructor
        this.nombre = nombre;          // Asigna nombre
        this.stock = stockInicial;     // Asigna stock inicial
    }
    
    // Getters y Setters
    public String getNombre() {  // Obtiene nombre
        return nombre;
    }
    
    public void setNombre(String nombre) {  // Establece nombre
        this.nombre = nombre;
    }
    
    public int getStock() {  // Obtiene stock
        return stock;
    }
    
    public void setStock(int stock) {  // Establece stock
        this.stock = stock;
    }
    
    @Override
    public String toString() {  // Representación string del producto
        return "Producto: " + nombre + " | Stock: " + stock;  // Formato: "Producto: nombre | Stock: cantidad"
    }
}