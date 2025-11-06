package app;  // Declara que esta clase pertenece al paquete 'app'

// Importaciones de clases necesarias
import modelo.Bodega;           // Importa la clase Bodega del paquete modelo
import vista.Interfaz;           // Importa la clase Consola del paquete vista  
import controlador.ControladorInventario;  // Importa el controlador
import javax.swing.SwingUtilities;  // Importa utilidades de Swing para hilos

public class Main {  // Clase principal que contiene el método main
    public static void main(String[] args) {  // Método principal, punto de entrada
        System.out.println("Iniciando Sistema de Gestión de Inventario...");  // Mensaje en consola
        System.out.println("Abriendo interfaz gráfica...\n");  // Otro mensaje
        
        // Ejecutar en el hilo de eventos de Swing (para interfaces gráficas)
        SwingUtilities.invokeLater(new Runnable() {  // Crea un Runnable para ejecutar en el hilo de Swing
            @Override
            public void run() {  // Método que se ejecutará en el hilo de Swing
                // Crear modelo, vista y controlador (MVC)
                Bodega bodega = new Bodega();  // Instancia del modelo que maneja los datos
                Interfaz vista = new Interfaz();  // Instancia de la vista (UI))
                ControladorInventario controlador = new ControladorInventario(bodega, vista);  // Controlador que conecta modelo y vista
                
                // Mostrar la ventana
                vista.setVisible(true);  // Hace visible la ventana de la interfaz gráfica
            }
        });
    }
}