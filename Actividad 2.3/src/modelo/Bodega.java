package modelo;  // Paquete del modelo (manejo de datos)

// Importaciones
import java.util.ArrayList;  // Lista dinámica
import java.util.Iterator;   // Para iterar sobre colecciones

public class Bodega {  // Clase que representa el inventario
    private ArrayList<Producto> inventario;  // Lista que almacena todos los productos
    
    public Bodega() {  // Constructor
        this.inventario = new ArrayList<>();  // Inicializa la lista vacía
    }
    
    public void agregarProducto(String nombre, int stockInicial) {  // Agrega nuevo producto
        Producto nuevoProducto = new Producto(nombre, stockInicial);  // Crea objeto Producto
        inventario.add(nuevoProducto);  // Agrega a la lista
    }
    
    public ArrayList<Producto> listarProductos() {  // Retorna copia de la lista de productos
        return new ArrayList<>(inventario);  // Retorna nueva lista para evitar modificación directa
    }
    
    public boolean agregarStock(String nombreProducto, int cantidad) {  // Agrega stock a producto existente
        for (Producto producto : inventario) {  // Recorre todos los productos
            if (producto.getNombre().equalsIgnoreCase(nombreProducto)) {  // Si encuentra el producto (ignorando mayúsculas/minúsculas)
                producto.setStock(producto.getStock() + cantidad);  // Suma la cantidad al stock actual
                return true;  // Retorna éxito
            }
        }
        return false;  // Retorna fallo (producto no encontrado)
    }
    
    public boolean restarStock(String nombreProducto, int cantidad) {  // Resta stock de producto
        for (Producto producto : inventario) {  // Recorre productos
            if (producto.getNombre().equalsIgnoreCase(nombreProducto)) {  // Si encuentra el producto
                if (producto.getStock() >= cantidad) {  // Verifica que haya suficiente stock
                    producto.setStock(producto.getStock() - cantidad);  // Resta la cantidad
                    return true;  // Retorna éxito
                }
                return false;  // Retorna fallo (stock insuficiente)
            }
        }
        return false;  // Retorna fallo (producto no encontrado)
    }
    
    public String generarReporteInventario() {  // Genera reporte completo del inventario
        StringBuilder reporte = new StringBuilder();  // Para construir el string del reporte
        reporte.append("=== REPORTE DE INVENTARIO TOTAL ===\n");  // Encabezado
        
        Iterator<Producto> iterator = inventario.iterator();  // Crea iterador para la lista
        while (iterator.hasNext()) {  // Mientras haya elementos
            Producto producto = iterator.next();  // Obtiene siguiente producto
            reporte.append(producto.toString()).append("\n");  // Agrega representación string del producto
        }
        
        return reporte.toString();  // Retorna el reporte completo
    }
    
    public String generarReporteStockCritico(int stockMinimo) {  // Genera reporte de productos con stock bajo
        StringBuilder reporte = new StringBuilder();  // Para construir el string
        reporte.append("=== REPORTE DE STOCK CRÍTICO (Stock < " + stockMinimo + ") ===\n");  // Encabezado con stock mínimo
        
        for (Producto producto : inventario) {  // Recorre todos los productos
            if (producto.getStock() < stockMinimo) {  // Si el stock es menor al mínimo
                reporte.append(producto.toString()).append("\n");  // Agrega producto al reporte
            }
        }
        
        return reporte.toString();  // Retorna reporte
    }
    
    public Producto buscarProducto(String nombre) {  // Busca producto por nombre
        for (Producto producto : inventario) {  // Recorre productos
            if (producto.getNombre().equalsIgnoreCase(nombre)) {  // Si coincide el nombre (ignorando mayúsculas/minúsculas)
                return producto;  // Retorna el producto encontrado
            }
        }
        return null;  // Retorna null si no lo encuentra
    }
}