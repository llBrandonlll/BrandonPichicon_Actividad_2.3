package controlador;

import modelo.Bodega;
import modelo.Producto;
import vista.Interfaz;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class ControladorInventario {
    private Bodega bodega;
    private Interfaz vista;
    private Scanner scanner;
    
    public ControladorInventario(Bodega bodega, Interfaz vista) {
        this.bodega = bodega;
        this.vista = vista;
        this.scanner = new Scanner(System.in);
        
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
                scanner.close();
                System.exit(0);
            }
        });
    }
    
    private void agregarProducto() {
        System.out.println("\n--- AGREGAR PRODUCTO ---");
        System.out.print("Ingrese el nombre del producto: ");
        String nombre = scanner.nextLine();
        
        if (nombre != null && !nombre.trim().isEmpty()) {
            System.out.print("Ingrese el stock inicial: ");
            try {
                int stock = Integer.parseInt(scanner.nextLine());
                
                if (stock >= 0) {
                    Producto productoExistente = bodega.buscarProducto(nombre);
                    if (productoExistente != null) {
                        int stockAnterior = productoExistente.getStock();
                        bodega.agregarStock(nombre, stock);
                        int stockNuevo = productoExistente.getStock();
                        System.out.println("✅ Producto '" + nombre + "' ya existía. Stock actualizado:");
                        System.out.println("📦 Stock anterior: " + stockAnterior);
                        System.out.println("📦 Stock agregado: " + stock);
                        System.out.println("📦 Stock nuevo: " + stockNuevo);
                    } else {
                        bodega.agregarProducto(nombre, stock);
                        System.out.println("✅ Nuevo producto '" + nombre + "' agregado con stock inicial: " + stock);
                    }
                } else {
                    System.out.println("❌ Error: El stock no puede ser negativo");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Error: Debe ingresar un número válido para el stock");
            }
        } else {
            System.out.println("❌ Error: El nombre del producto no puede estar vacío");
        }
    }
    
    private void agregarStock() {
        System.out.println("\n--- AGREGAR STOCK ---");
        System.out.print("Ingrese el nombre del producto: ");
        String nombre = scanner.nextLine();
        
        if (nombre != null && !nombre.trim().isEmpty()) {
            System.out.print("Ingrese la cantidad a agregar: ");
            try {
                int cantidad = Integer.parseInt(scanner.nextLine());
                
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
            } catch (NumberFormatException e) {
                System.out.println("❌ Error: Debe ingresar un número válido para la cantidad");
            }
        }
    }
    
    private void restarStock() {
        System.out.println("\n--- RESTAR STOCK ---");
        System.out.print("Ingrese el nombre del producto: ");
        String nombre = scanner.nextLine();
        
        if (nombre != null && !nombre.trim().isEmpty()) {
            System.out.print("Ingrese la cantidad a restar: ");
            try {
                int cantidad = Integer.parseInt(scanner.nextLine());
                
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
                        System.out.println(" - Que el producto exista");
                        System.out.println(" - Que haya suficiente stock disponible");
                    }
                } else {
                    System.out.println("❌ Error: La cantidad debe ser mayor a 0");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Error: Debe ingresar un número válido para la cantidad");
            }
        }
    }
    
    private void generarReporteInventario() {
        System.out.println("\n" + bodega.generarReporteInventario());
    }
}
