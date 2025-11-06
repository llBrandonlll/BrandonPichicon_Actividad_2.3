package vista;  // Paquete de la vista (interfaz gráfica)

// Importaciones de Swing para interfaz gráfica
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Consola extends JFrame {  // Clase que representa la ventana principal (hereda de JFrame)
    // Declaración de botones
    private JButton btnAgregarProducto;
    private JButton btnAgregarStock;
    private JButton btnRestarStock;
    private JButton btnReporteInventario;
    private JButton btnSalir;
    
    public Consola() {  // Constructor
        inicializarComponentes();  // Crea y configura los componentes
        configurarVentana();      // Configura propiedades de la ventana
    }
    
    private void inicializarComponentes() {  // Método para crear componentes de la interfaz
        // Crear botones con texto descriptivo
        btnAgregarProducto = new JButton("1. Agregar Producto");
        btnAgregarStock = new JButton("2. Agregar Stock");
        btnRestarStock = new JButton("3. Restar Stock");
        btnReporteInventario = new JButton("4. Reporte Inventario");
        btnSalir = new JButton("5. Salir");
        
        // Hacer los botones más grandes
        Font fontBotones = new Font("Arial", Font.BOLD, 14);  // Crea fuente en negrita tamaño 14
        btnAgregarProducto.setFont(fontBotones);    // Aplica fuente al botón
        btnAgregarStock.setFont(fontBotones);       // Aplica fuente al botón
        btnRestarStock.setFont(fontBotones);        // Aplica fuente al botón
        btnReporteInventario.setFont(fontBotones);  // Aplica fuente al botón
        btnSalir.setFont(fontBotones);              // Aplica fuente al botón
        
        // Panel para botones
        JPanel panelBotones = new JPanel(new GridLayout(5, 1, 10, 10));  // Panel con grid de 5 filas, 1 columna, espacios de 10px
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));  // Borde vacío de 20px
        
        // Agrega botones al panel
        panelBotones.add(btnAgregarProducto);
        panelBotones.add(btnAgregarStock);
        panelBotones.add(btnRestarStock);
        panelBotones.add(btnReporteInventario);
        panelBotones.add(btnSalir);
        
        // Configurar layout principal
        setLayout(new BorderLayout());           // Establece layout de la ventana
        add(panelBotones, BorderLayout.CENTER);  // Agrega panel de botones al centro
        
        // Agregar título
        JLabel titulo = new JLabel("SISTEMA DE GESTIÓN DE INVENTARIO", JLabel.CENTER);  // Crea etiqueta de título centrada
        titulo.setFont(new Font("Arial", Font.BOLD, 18));  // Fuente más grande para título
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  // Borde alrededor del título
        add(titulo, BorderLayout.NORTH);  // Agrega título en la parte superior
    }
    
    private void configurarVentana() {  // Configura propiedades de la ventana
        setTitle("Sistema de Gestión de Inventario");  // Título de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Cierra aplicación al cerrar ventana
        setSize(400, 450);      // Tamaño de la ventana (ancho x alto)
        setLocationRelativeTo(null);  // Centra la ventana en la pantalla
        setResizable(false);    // Evita que usuario redimensione ventana
    }
    
    // Métodos para agregar listeners a los botones (conexión con controlador)
    public void setAgregarProductoListener(ActionListener listener) {
        btnAgregarProducto.addActionListener(listener);  // Asigna listener al botón
    }
    
    public void setAgregarStockListener(ActionListener listener) {
        btnAgregarStock.addActionListener(listener);  // Asigna listener al botón
    }
    
    public void setRestarStockListener(ActionListener listener) {
        btnRestarStock.addActionListener(listener);  // Asigna listener al botón
    }
    
    public void setReporteInventarioListener(ActionListener listener) {
        btnReporteInventario.addActionListener(listener);  // Asigna listener al botón
    }
    
    public void setSalirListener(ActionListener listener) {
        btnSalir.addActionListener(listener);  // Asigna listener al botón
    }
    
    // Métodos para mostrar diálogos de entrada
    public String mostrarInputDialog(String mensaje) {  // Muestra diálogo para entrada de texto
        return JOptionPane.showInputDialog(this, mensaje);  // Retorna texto ingresado
    }
    
    public int mostrarInputDialogEntero(String mensaje) {  // Muestra diálogo para entrada de número
        try {
            String input = JOptionPane.showInputDialog(this, mensaje);  // Obtiene input como string
            return Integer.parseInt(input);  // Convierte a entero
        } catch (NumberFormatException e) {  // Si no es un número válido
            return -1;  // Retorna -1 indicando error
        }
    }
    
    public void mostrarMensajeDialog(String mensaje) {  // Muestra diálogo de mensaje
        JOptionPane.showMessageDialog(this, mensaje);  // Muestra mensaje en diálogo
    }
}