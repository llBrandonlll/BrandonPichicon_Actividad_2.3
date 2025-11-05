package controlador;

import modelo.Bodega;
import modelo.Producto;
import vista.Consola;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ControladorInventario {
    private Bodega bodega;
    private Consola vista;
    
    public ControladorInventario(Bodega bodega, Consola vista) {
        this.bodega = bodega;
        this.vista = vista;
        
        configurarListeners();
        System.out.println("=== SISTEMA DE GESTIÓN DE INVENTARIO INICIADO ===");
        System.out.println("Los resultados se mostrarán en esta terminal/consola\n");
    }
    
    private void configurarListeners() {
        vista.setAgregarProductoListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarProducto();
            }
        });
        
        vista.setAgregarStockListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarStock();
            }
        });
        
        vista.setRestarStockListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restarStock();
            }
        });
        
        vista.setReporteInventarioListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarReporteInventario();
            }
        });
        
        vista.setSalirListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("=== SISTEMA CERRADO ===");
                System.exit(0);
            }
        });
    }
    
    private void agregarProducto() {
        System.out.println("\n--- AGREGAR PRODUCTO ---");
        String nombre = vista.mostrarInputDialog("Ingrese el nombre del producto:");
        if (nombre != null && !nombre.trim().isEmpty()) {
            int stock = vista.mostrarInputDialogEntero("Ingrese el stock inicial:");
            if (stock >= 0) {
                // Verificar si el producto ya existe
                Producto productoExistente = bodega.buscarProducto(nombre);
                if (productoExistente != null) {
                    // Si existe, agregar al stock existente
                    int stockAnterior = productoExistente.getStock();
                    bodega.agregarStock(nombre, stock);
                    int stockNuevo = productoExistente.getStock();
                    System.out.println("✅ Producto '" + nombre + "' ya existía. Stock actualizado:");
                    System.out.println("📦 Stock anterior: " + stockAnterior);
                    System.out.println("📦 Stock agregado: " + stock);
                    System.out.println("📦 Stock nuevo: " + stockNuevo);
                } else {
                    // Si no existe, crear nuevo producto
                    bodega.agregarProducto(nombre, stock);
                    System.out.println("✅ Nuevo producto '" + nombre + "' agregado con stock inicial: " + stock);
                }
            } else {
                System.out.println("❌ Error: El stock no puede ser negativo");
            }
        } else {
            System.out.println("❌ Error: El nombre del producto no puede estar vacío");
        }
    }
    
    private void agregarStock() {
        System.out.println("\n--- AGREGAR STOCK ---");
        String nombre = vista.mostrarInputDialog("Ingrese el nombre del producto:");
        if (nombre != null && !nombre.trim().isEmpty()) {
            int cantidad = vista.mostrarInputDialogEntero("Ingrese la cantidad a agregar:");
            if (cantidad > 0) {
                boolean exito = bodega.agregarStock(nombre, cantidad);
                if (exito) {
                    System.out.println("✅ Stock agregado: " + cantidad + " unidades a '" + nombre + "'");
                    Producto producto = bodega.buscarProducto(nombre);
                    if (producto != null) {
                        System.out.println("📦 Stock actual: " + producto.getStock());
                    }
                } else {
                    System.out.println("❌ Error: Producto '" + nombre + "' no encontrado");
                }
            } else {
                System.out.println("❌ Error: La cantidad debe ser mayor a 0");
            }
        }
    }
    
    private void restarStock() {
        System.out.println("\n--- RESTAR STOCK ---");
        String nombre = vista.mostrarInputDialog("Ingrese el nombre del producto:");
        if (nombre != null && !nombre.trim().isEmpty()) {
            int cantidad = vista.mostrarInputDialogEntero("Ingrese la cantidad a restar:");
            if (cantidad > 0) {
                boolean exito = bodega.restarStock(nombre, cantidad);
                if (exito) {
                    System.out.println("✅ Stock restado: " + cantidad + " unidades de '" + nombre + "'");
                    Producto producto = bodega.buscarProducto(nombre);
                    if (producto != null) {
                        System.out.println("📦 Stock actual: " + producto.getStock());
                    }
                } else {
                    System.out.println("❌ Error: No se pudo restar stock. Verifique:");
                    System.out.println("   - Que el producto exista");
                    System.out.println("   - Que haya suficiente stock disponible");
                }
            } else {
                System.out.println("❌ Error: La cantidad debe ser mayor a 0");
            }
        }
    }
    
    private void generarReporteInventario() {
        System.out.println("\n" + bodega.generarReporteInventario());
    }
}