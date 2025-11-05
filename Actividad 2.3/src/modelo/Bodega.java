package modelo;

import java.util.ArrayList;
import java.util.Iterator;

public class Bodega {
    private ArrayList<Producto> inventario;
    
    public Bodega() {
        this.inventario = new ArrayList<>();
    }
    
    public void agregarProducto(String nombre, int stockInicial) {
        Producto nuevoProducto = new Producto(nombre, stockInicial);
        inventario.add(nuevoProducto);
    }
    
    public ArrayList<Producto> listarProductos() {
        return new ArrayList<>(inventario);
    }
    
    public boolean agregarStock(String nombreProducto, int cantidad) {
        for (Producto producto : inventario) {
            if (producto.getNombre().equalsIgnoreCase(nombreProducto)) {
                producto.setStock(producto.getStock() + cantidad);
                return true;
            }
        }
        return false;
    }
    
    public boolean restarStock(String nombreProducto, int cantidad) {
        for (Producto producto : inventario) {
            if (producto.getNombre().equalsIgnoreCase(nombreProducto)) {
                if (producto.getStock() >= cantidad) {
                    producto.setStock(producto.getStock() - cantidad);
                    return true;
                }
                return false;
            }
        }
        return false;
    }
    
    public String generarReporteInventario() {
        StringBuilder reporte = new StringBuilder();
        reporte.append("=== REPORTE DE INVENTARIO TOTAL ===\n");
        
        Iterator<Producto> iterator = inventario.iterator();
        while (iterator.hasNext()) {
            Producto producto = iterator.next();
            reporte.append(producto.toString()).append("\n");
        }
        
        return reporte.toString();
    }
    
    public String generarReporteStockCritico(int stockMinimo) {
        StringBuilder reporte = new StringBuilder();
        reporte.append("=== REPORTE DE STOCK CRÍTICO (Stock < " + stockMinimo + ") ===\n");
        
        for (Producto producto : inventario) {
            if (producto.getStock() < stockMinimo) {
                reporte.append(producto.toString()).append("\n");
            }
        }
        
        return reporte.toString();
    }
    
    public Producto buscarProducto(String nombre) {
        for (Producto producto : inventario) {
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                return producto;
            }
        }
        return null;
    }
}