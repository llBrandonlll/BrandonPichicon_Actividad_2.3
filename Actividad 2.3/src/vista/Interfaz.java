package vista;  // Declara que esta clase pertenece al paquete 'vista'

// Importaciones de Swing para interfaz gráfica
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Interfaz extends JFrame {  // Clase que representa la ventana principal (hereda de JFrame)
    // Declaración de botones como variables de instancia
    private JButton btnAgregarProducto;
    private JButton btnAgregarStock;
    private JButton btnRestarStock;
    private JButton btnReporteInventario;
    private JButton btnSalir;
    
    // Definición de colores pastel como constantes
    private final Color COLOR_FONDO = new Color(240, 248, 255); // Azul pastel muy claro (Alice Blue)
    private final Color COLOR_BOTON_NORMAL = new Color(173, 216, 230); // Azul pastel (Light Blue)
    private final Color COLOR_BOTON_HOVER = new Color(135, 206, 250); // Azul pastel más intenso (Light Sky Blue)
    private final Color COLOR_BOTON_ACCION = new Color(152, 251, 152); // Verde pastel (Pale Green)
    private final Color COLOR_BOTON_PELIGRO = new Color(255, 182, 193); // Rosa pastel para salir (Light Pink)
    private final Color COLOR_TITULO = new Color(70, 130, 180); // Azul acero (Steel Blue)
    
    public Interfaz() {  // Constructor de la clase
        inicializarComponentes();  // Llama al método que crea y configura los componentes visuales
        configurarVentana();      // Llama al método que configura las propiedades de la ventana
    }
    
    private void inicializarComponentes() {  // Método para crear y configurar todos los componentes de la interfaz
        // Configurar el fondo principal del contenido de la ventana
        getContentPane().setBackground(COLOR_FONDO);
        // Establece un layout de tipo BorderLayout con espacios de 15px entre componentes
        setLayout(new BorderLayout(15, 15));
        
        // Crear panel de título con gradiente y agregarlo en la parte superior
        JPanel panelTitulo = crearPanelTitulo();
        add(panelTitulo, BorderLayout.NORTH);
        
        // Crear panel principal de botones y agregarlo en el centro
        JPanel panelBotones = crearPanelBotones();
        add(panelBotones, BorderLayout.CENTER);
        
        // Crear panel inferior con información y agregarlo en la parte inferior
        JPanel panelInferior = crearPanelInferior();
        add(panelInferior, BorderLayout.SOUTH);
    }
    
    private JPanel crearPanelTitulo() {  // Método para crear el panel del título
        // Crea un panel con layout BorderLayout
        JPanel panelTitulo = new JPanel(new BorderLayout());
        panelTitulo.setBackground(COLOR_FONDO);  // Establece color de fondo
        panelTitulo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));  // Agrega márgenes internos
        
        // Crea etiqueta para el título principal centrado
        JLabel titulo = new JLabel("SISTEMA DE GESTIÓN DE INVENTARIO", JLabel.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));  // Fuente negrita tamaño 24
        titulo.setForeground(COLOR_TITULO);  // Color del texto
        
        // Crea etiqueta para el subtítulo centrado
        JLabel subtitulo = new JLabel("Control de Stock y Productos", JLabel.CENTER);
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));  // Fuente normal tamaño 14
        subtitulo.setForeground(new Color(100, 100, 100));  // Color gris para el subtítulo
        
        // Agrega los componentes al panel
        panelTitulo.add(titulo, BorderLayout.CENTER);  // Título en el centro
        panelTitulo.add(subtitulo, BorderLayout.SOUTH);  // Subtítulo en la parte inferior
        
        return panelTitulo;  // Retorna el panel completo
    }
    
    private JPanel crearPanelBotones() {  // Método para crear el panel de botones
        // Crea un panel con grid de 2 filas y 3 columnas, con espacios de 15px
        JPanel panelBotones = new JPanel(new GridLayout(2, 3, 15, 15));
        panelBotones.setBackground(COLOR_FONDO);  // Establece color de fondo
        panelBotones.setBorder(BorderFactory.createEmptyBorder(0, 30, 30, 30));  // Márgenes: 0 arriba, 30 laterales, 30 abajo
        
        // Crear botones con estilos personalizados y colores específicos
        btnAgregarProducto = crearBoton("Agregar Producto", COLOR_BOTON_ACCION);
        btnAgregarStock = crearBoton("Agregar Stock", COLOR_BOTON_NORMAL);
        btnRestarStock = crearBoton("Restar Stock", COLOR_BOTON_NORMAL);
        btnReporteInventario = crearBoton("Reporte Inventario", COLOR_BOTON_NORMAL);
        btnSalir = crearBoton("Salir", COLOR_BOTON_PELIGRO);
        
        // Agregar botones al panel en disposición 2x3
        panelBotones.add(btnAgregarProducto);
        panelBotones.add(btnAgregarStock);
        panelBotones.add(btnRestarStock);
        panelBotones.add(btnReporteInventario);
        panelBotones.add(btnSalir);
        
        // Espacio vacío para completar la grid 2x3 (6 espacios totales)
        panelBotones.add(new JLabel()); 
        
        return panelBotones;  // Retorna el panel con botones
    }
    
    private JPanel crearPanelInferior() {  // Método para crear el panel inferior informativo
        JPanel panelInferior = new JPanel(new FlowLayout());  // Panel con layout FlowLayout (centrado)
        panelInferior.setBackground(COLOR_FONDO);  // Color de fondo
        panelInferior.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));  // Márgenes internos
        
        // Crea etiqueta con mensaje informativo
        JLabel info = new JLabel("Los resultados se mostrarán en la terminal/consola");
        info.setFont(new Font("Segoe UI", Font.ITALIC, 12));  // Fuente itálica tamaño 12
        info.setForeground(new Color(120, 120, 120));  // Color gris
        
        panelInferior.add(info);  // Agrega la etiqueta al panel
        return panelInferior;  // Retorna el panel completo
    }
    
    private JButton crearBoton(String texto, Color colorBase) {  // Método factory para crear botones personalizados
        // Crea un JButton anónimo con personalización del método paintComponent
        JButton boton = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {  // Sobrescribe cómo se pinta el botón
                Graphics2D g2 = (Graphics2D) g.create();  // Crea copia del contexto gráfico
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);  // Suaviza bordes
                
                // Cambia color según el estado del botón
                if (getModel().isPressed()) {  // Si está siendo presionado
                    g2.setColor(colorBase.darker());  // Color más oscuro
                } else if (getModel().isRollover()) {  // Si el mouse está sobre el botón
                    g2.setColor(COLOR_BOTON_HOVER);  // Color de hover
                } else {  // Estado normal
                    g2.setColor(colorBase);  // Color base
                }
                
                // Dibuja rectángulo redondeado de fondo
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.dispose();  // Libera recursos gráficos
                
                super.paintComponent(g);  // Llama al método original para pintar el texto
            }
            
            @Override
            protected void paintBorder(Graphics g) {  // Sobrescribe cómo se pinta el borde
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(colorBase.darker().darker());  // Color del borde (más oscuro)
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);  // Dibuja borde redondeado
                g2.dispose();
            }
        };
        
        // Configura propiedades básicas del botón
        boton.setFont(new Font("Segoe UI", Font.BOLD, 14));  // Fuente del texto
        boton.setForeground(Color.BLACK);  // Color del texto
        boton.setFocusPainted(false);  // Elimina el efecto de foco por defecto
        boton.setBorderPainted(false);  // Elimina el borde por defecto
        boton.setContentAreaFilled(false);  // Desactiva el relleno por defecto
        boton.setOpaque(false);  // Hace el botón transparente
        boton.setPreferredSize(new Dimension(150, 60));  // Tamaño preferido del botón
        
        // Agregar iconos según la función del botón
        if (texto.contains("Agregar Producto")) {
            boton.setIcon(crearIcono("➕"));  // Icono de agregar
        } else if (texto.contains("Agregar Stock")) {
            boton.setIcon(crearIcono("📈"));  // Icono de subir
        } else if (texto.contains("Restar Stock")) {
            boton.setIcon(crearIcono("📉"));  // Icono de bajar
        } else if (texto.contains("Reporte")) {
            boton.setIcon(crearIcono("📊"));  // Icono de gráfica
        } else if (texto.contains("Salir")) {
            boton.setIcon(crearIcono("🚪"));  // Icono de puerta
        }
        
        return boton;  // Retorna el botón completamente configurado
    }
    
    private Icon crearIcono(String emoji) {  // Método para crear iconos a partir de emojis
        JLabel label = new JLabel(emoji);  // Crea etiqueta con el emoji
        label.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));  // Fuente específica para emojis
        label.setPreferredSize(new Dimension(20, 20));  // Tamaño del icono
        
        // Retorna un Icon anónimo que pinta la etiqueta
        return new Icon() {
            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                label.setBounds(x, y, getIconWidth(), getIconHeight());  // Establece posición y tamaño
                label.paint(g);  // Pinta la etiqueta
            }
            
            @Override
            public int getIconWidth() {  // Ancho del icono
                return 20;
            }
            
            @Override
            public int getIconHeight() {  // Alto del icono
                return 20;
            }
        };
    }
    
    private void configurarVentana() {  // Método para configurar propiedades de la ventana
        setTitle("Sistema de Gestión de Inventario");  // Título de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Cierra la aplicación al cerrar ventana
        setSize(700, 500); // Ventana más ancha para acomodar botones horizontales
        setLocationRelativeTo(null);  // Centra la ventana en la pantalla
        setResizable(false);  // Evita que el usuario redimensione la ventana
        
        // Intenta cargar un icono para la ventana
        try {
            setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icono.png")));
        } catch (Exception e) {
            // Si no hay icono disponible, continúa sin él
        }
    }
    
    // ========== MÉTODOS PARA AGREGAR LISTENERS ==========
    
    public void setAgregarProductoListener(ActionListener listener) {
        btnAgregarProducto.addActionListener(listener);  // Asigna listener al botón Agregar Producto
    }
    
    public void setAgregarStockListener(ActionListener listener) {
        btnAgregarStock.addActionListener(listener);  // Asigna listener al botón Agregar Stock
    }
    
    public void setRestarStockListener(ActionListener listener) {
        btnRestarStock.addActionListener(listener);  // Asigna listener al botón Restar Stock
    }
    
    public void setReporteInventarioListener(ActionListener listener) {
        btnReporteInventario.addActionListener(listener);  // Asigna listener al botón Reporte Inventario
    }
    
    public void setSalirListener(ActionListener listener) {
        btnSalir.addActionListener(listener);  // Asigna listener al botón Salir
    }
    
    // ========== MÉTODOS PARA MOSTRAR DIÁLOGOS ==========
    
    public String mostrarInputDialog(String mensaje) {  // Muestra diálogo para entrada de texto
        return JOptionPane.showInputDialog(this, mensaje);  // Retorna texto ingresado por usuario
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
        JOptionPane.showMessageDialog(this, mensaje);  // Muestra mensaje en diálogo modal
    }
}
