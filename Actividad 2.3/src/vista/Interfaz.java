package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Interfaz extends JFrame {
    private JButton btnAgregarProducto;
    private JButton btnAgregarStock;
    private JButton btnRestarStock;
    private JButton btnReporteInventario;
    private JButton btnSalir;
    
    private final Color COLOR_FONDO = new Color(240, 248, 255);
    private final Color COLOR_BOTON_NORMAL = new Color(173, 216, 230);
    private final Color COLOR_BOTON_HOVER = new Color(135, 206, 250);
    private final Color COLOR_BOTON_ACCION = new Color(152, 251, 152);
    private final Color COLOR_BOTON_PELIGRO = new Color(255, 182, 193);
    private final Color COLOR_TITULO = new Color(70, 130, 180);
    
    public Interfaz() {
        inicializarComponentes();
        configurarVentana();
    }
    
    private void inicializarComponentes() {
        getContentPane().setBackground(COLOR_FONDO);
        setLayout(new BorderLayout(15, 15));
        
        JPanel panelTitulo = crearPanelTitulo();
        add(panelTitulo, BorderLayout.NORTH);
        
        JPanel panelBotones = crearPanelBotones();
        add(panelBotones, BorderLayout.CENTER);
        
        JPanel panelInferior = crearPanelInferior();
        add(panelInferior, BorderLayout.SOUTH);
    }
    
    private JPanel crearPanelTitulo() {
        JPanel panelTitulo = new JPanel(new BorderLayout());
        panelTitulo.setBackground(COLOR_FONDO);
        panelTitulo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel titulo = new JLabel("SISTEMA DE GESTIÓN DE INVENTARIO", JLabel.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setForeground(COLOR_TITULO);
        
        JLabel subtitulo = new JLabel("Control de Stock y Productos - Use la CONSOLA para ingresar datos", JLabel.CENTER);
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitulo.setForeground(new Color(100, 100, 100));
        
        panelTitulo.add(titulo, BorderLayout.CENTER);
        panelTitulo.add(subtitulo, BorderLayout.SOUTH);
        
        return panelTitulo;
    }
    
    private JPanel crearPanelBotones() {
        JPanel panelBotones = new JPanel(new GridLayout(2, 3, 15, 15));
        panelBotones.setBackground(COLOR_FONDO);
        panelBotones.setBorder(BorderFactory.createEmptyBorder(0, 30, 30, 30));
        
        btnAgregarProducto = crearBoton("Agregar Producto", COLOR_BOTON_ACCION);
        btnAgregarStock = crearBoton("Agregar Stock", COLOR_BOTON_NORMAL);
        btnRestarStock = crearBoton("Restar Stock", COLOR_BOTON_NORMAL);
        btnReporteInventario = crearBoton("Reporte Inventario", COLOR_BOTON_NORMAL);
        btnSalir = crearBoton("Salir", COLOR_BOTON_PELIGRO);
        
        panelBotones.add(btnAgregarProducto);
        panelBotones.add(btnAgregarStock);
        panelBotones.add(btnRestarStock);
        panelBotones.add(btnReporteInventario);
        panelBotones.add(btnSalir);
        panelBotones.add(new JLabel());
        
        return panelBotones;
    }
    
    private JPanel crearPanelInferior() {
        JPanel panelInferior = new JPanel(new FlowLayout());
        panelInferior.setBackground(COLOR_FONDO);
        panelInferior.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        
        JLabel info = new JLabel("Ingrese los datos solicitados en la TERMINAL/CONSOLA");
        info.setFont(new Font("Segoe UI", Font.BOLD, 12));
        info.setForeground(new Color(200, 0, 0));
        
        panelInferior.add(info);
        return panelInferior;
    }
    
    private JButton crearBoton(String texto, Color colorBase) {
        JButton boton = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                if (getModel().isPressed()) {
                    g2.setColor(colorBase.darker());
                } else if (getModel().isRollover()) {
                    g2.setColor(COLOR_BOTON_HOVER);
                } else {
                    g2.setColor(colorBase);
                }
                
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.dispose();
                
                super.paintComponent(g);
            }
            
            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(colorBase.darker().darker());
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
                g2.dispose();
            }
        };
        
        boton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        boton.setForeground(Color.BLACK);
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setContentAreaFilled(false);
        boton.setOpaque(false);
        boton.setPreferredSize(new Dimension(150, 60));
        
        if (texto.contains("Agregar Producto")) {
            boton.setIcon(crearIcono("➕"));
        } else if (texto.contains("Agregar Stock")) {
            boton.setIcon(crearIcono("📈"));
        } else if (texto.contains("Restar Stock")) {
            boton.setIcon(crearIcono("📉"));
        } else if (texto.contains("Reporte")) {
            boton.setIcon(crearIcono("📊"));
        } else if (texto.contains("Salir")) {
            boton.setIcon(crearIcono("🚪"));
        }
        
        return boton;
    }
    
    private Icon crearIcono(String emoji) {
        JLabel label = new JLabel(emoji);
        label.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));
        label.setPreferredSize(new Dimension(20, 20));
        
        return new Icon() {
            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                label.setBounds(x, y, getIconWidth(), getIconHeight());
                label.paint(g);
            }
            
            @Override
            public int getIconWidth() {
                return 20;
            }
            
            @Override
            public int getIconHeight() {
                return 20;
            }
        };
    }
    
    private void configurarVentana() {
        setTitle("Sistema de Gestión de Inventario - Modo Consola");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        
        try {
            setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icono.png")));
        } catch (Exception e) {
            // Si no hay icono disponible, continúa sin él
        }
    }
    
    // ========== MÉTODOS PARA AGREGAR LISTENERS ==========
    
    public void setAgregarProductoListener(ActionListener listener) {
        btnAgregarProducto.addActionListener(listener);
    }
    
    public void setAgregarStockListener(ActionListener listener) {
        btnAgregarStock.addActionListener(listener);
    }
    
    public void setRestarStockListener(ActionListener listener) {
        btnRestarStock.addActionListener(listener);
    }
    
    public void setReporteInventarioListener(ActionListener listener) {
        btnReporteInventario.addActionListener(listener);
    }
    
    public void setSalirListener(ActionListener listener) {
        btnSalir.addActionListener(listener);
    }
}
