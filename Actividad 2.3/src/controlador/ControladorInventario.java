package controlador;  // Paquete del controlador

// Importaciones
import modelo.Bodega;        // Modelo de datos
import modelo.Producto;      // Clase Producto
import vista.Consola;        // Vista (interfaz)
import java.awt.event.ActionEvent;    // Evento de acción
import java.awt.event.ActionListener; // Interfaz para escuchar eventos
import java.util.ArrayList;           // Lista dinámica

public class ControladorInventario {  // Clase controlador que maneja la lógica
    private Bodega bodega;    // Referencia al modelo
    private Consola vista;    // Referencia a la vista
    
    // Constructor que recibe modelo y vista
    public ControladorInventario(Bodega bodega, Consola vista) {
        this.bodega = bodega;    // Asigna el modelo recibido
        this.vista = vista;      // Asigna la vista recibida
        
        configurarListeners();   // Configura los escuchadores de eventos
        System.out.println("=== SISTEMA DE GESTIÓN DE INVENTARIO INICIADO ===");  // Mensaje de inicio
        System.out.println("Los resultados se mostrarán en esta terminal/consola\n");  // Información al usuario
    }
    
    private void configurarListeners() {  // Configura los eventos de los botones
        vista.setAgregarProductoListener(new ActionListener() {  // Listener para botón "Agregar Producto"
            @Override
            public void actionPerformed(ActionEvent e) {  // Se ejecuta al hacer clic
                agregarProducto();  // Llama al método para agregar producto
            }
        });
        
        // Similar para los otros botones...
        vista.setAgregarStockListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarStock();  // Llama al método para agregar stock
            }
        });
        
        vista.setRestarStockListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restarStock();  // Llama al método para restar stock
            }
        });
        
        vista.setReporteInventarioListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarReporteInventario();  // Llama al método para generar reporte
            }
        });
        
        vista.setSalirListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("=== SISTEMA CERRADO ===");  // Mensaje de cierre
                System.exit(0);  // Termina la aplicación
            }
        });
    }
    
    private void agregarProducto() {  // Método para agregar nuevo producto
        System.out.println("\n--- AGREGAR PRODUCTO ---");  // Encabezado en consola
        String nombre = vista.mostrarInputDialog("Ingrese el nombre del producto:");  // Dialog para ingresar nombre
        if (nombre != null && !nombre.trim().isEmpty()) {  // Valida que el nombre no esté vacío
            int stock = vista.mostrarInputDialogEntero("Ingrese el stock inicial:");  // Dialog para stock
            if (stock >= 0) {  // Valida que stock no sea negativo
                // Verificar si el producto ya existe
                Producto productoExistente = bodega.buscarProducto(nombre);  // Busca producto en bodega
                if (productoExistente != null) {  // Si el producto ya existe
                    // Si existe, agregar al stock existente
                    int stockAnterior = productoExistente.getStock();  // Obtiene stock actual
                    bodega.agregarStock(nombre, stock);  // Agrega stock al producto existente
                    int stockNuevo = productoExistente.getStock();  // Obtiene nuevo stock
                    System.out.println("✅ Producto '" + nombre + "' ya existía. Stock actualizado:");  // Mensaje informativo
                    System.out.println("📦 Stock anterior: " + stockAnterior);  // Muestra stock anterior
                    System.out.println("📦 Stock agregado: " + stock);  // Muestra stock agregado
                    System.out.println("📦 Stock nuevo: " + stockNuevo);  // Muestra stock nuevo
                } else {  // Si el producto no existe
                    // Si no existe, crear nuevo producto
                    bodega.agregarProducto(nombre, stock);  // Crea nuevo producto en bodega
                    System.out.println("✅ Nuevo producto '" + nombre + "' agregado con stock inicial: " + stock);  // Mensaje de éxito
                }
            } else {
                System.out.println("❌ Error: El stock no puede ser negativo");  // Mensaje de error
            }
        } else {
            System.out.println("❌ Error: El nombre del producto no puede estar vacío");  // Mensaje de error
        }
    }
    
    private void agregarStock() {  // Método para agregar stock a producto existente
        System.out.println("\n--- AGREGAR STOCK ---");  // Encabezado
        String nombre = vista.mostrarInputDialog("Ingrese el nombre del producto:");  // Pide nombre del producto
        if (nombre != null && !nombre.trim().isEmpty()) {  // Valida nombre
            int cantidad = vista.mostrarInputDialogEntero("Ingrese la cantidad a agregar:");  // Pide cantidad
            if (cantidad > 0) {  // Valida cantidad positiva
                boolean exito = bodega.agregarStock(nombre, cantidad);  // Intenta agregar stock
                if (exito) {  // Si tuvo éxito
                    System.out.println("✅ Stock agregado: " + cantidad + " unidades a '" + nombre + "'");  // Mensaje éxito
                    Producto producto = bodega.buscarProducto(nombre);  // Obtiene producto actualizado
                    if (producto != null) {
                        System.out.println("📦 Stock actual: " + producto.getStock());  // Muestra stock actual
                    }
                } else {
                    System.out.println("❌ Error: Producto '" + nombre + "' no encontrado");  // Mensaje error
                }
            } else {
                System.out.println("❌ Error: La cantidad debe ser mayor a 0");  // Mensaje error
            }
        }
    }
    
    private void restarStock() {  // Método para restar stock
        System.out.println("\n--- RESTAR STOCK ---");  // Encabezado
        String nombre = vista.mostrarInputDialog("Ingrese el nombre del producto:");  // Pide nombre
        if (nombre != null && !nombre.trim().isEmpty()) {  // Valida nombre
            int cantidad = vista.mostrarInputDialogEntero("Ingrese la cantidad a restar:");  // Pide cantidad a restar
            if (cantidad > 0) {  // Valida cantidad positiva
                boolean exito = bodega.restarStock(nombre, cantidad);  // Intenta restar stock
                if (exito) {  // Si tuvo éxito
                    System.out.println("✅ Stock restado: " + cantidad + " unidades de '" + nombre + "'");  // Mensaje éxito
                    Producto producto = bodega.buscarProducto(nombre);  // Obtiene producto actualizado
                    if (producto != null) {
                        System.out.println("📦 Stock actual: " + producto.getStock());  // Muestra stock actual
                    }
                } else {
                    System.out.println("❌ Error: No se pudo restar stock. Verifique:");  // Mensaje error
                    System.out.println("   - Que el producto exista");  // Posible causa 1
                    System.out.println("   - Que haya suficiente stock disponible");  // Posible causa 2
                }
            } else {
                System.out.println("❌ Error: La cantidad debe ser mayor a 0");  // Mensaje error
            }
        }
    }
    
    private void generarReporteInventario() {  // Método para generar reporte
        System.out.println("\n" + bodega.generarReporteInventario());  // Obtiene y muestra reporte de bodega
    }
}