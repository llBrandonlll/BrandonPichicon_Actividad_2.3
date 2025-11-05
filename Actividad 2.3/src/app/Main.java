package app;

import modelo.Bodega;
import vista.Consola;
import controlador.ControladorInventario;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando Sistema de Gestión de Inventario...");
        System.out.println("Abriendo interfaz gráfica...\n");
        
        // Ejecutar en el hilo de eventos de Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Crear modelo, vista y controlador
                Bodega bodega = new Bodega();
                Consola vista = new Consola();
                ControladorInventario controlador = new ControladorInventario(bodega, vista);
                
                // Mostrar la ventana
                vista.setVisible(true);
            }
        });
    }
}