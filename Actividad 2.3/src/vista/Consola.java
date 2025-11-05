package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Consola extends JFrame {
    private JButton btnAgregarProducto;
    private JButton btnAgregarStock;
    private JButton btnRestarStock;
    private JButton btnReporteInventario;
    private JButton btnSalir;
    
    public Consola() {
        inicializarComponentes();
        configurarVentana();
    }
    
    private void inicializarComponentes() {
        // Crear botones
        btnAgregarProducto = new JButton("1. Agregar Producto");
        btnAgregarStock = new JButton("2. Agregar Stock");
        btnRestarStock = new JButton("3. Restar Stock");
        btnReporteInventario = new JButton("4. Reporte Inventario");
        btnSalir = new JButton("5. Salir");
        
        // Hacer los botones más grandes
        Font fontBotones = new Font("Arial", Font.BOLD, 14);
        btnAgregarProducto.setFont(fontBotones);
        btnAgregarStock.setFont(fontBotones);
        btnRestarStock.setFont(fontBotones);
        btnReporteInventario.setFont(fontBotones);
        btnSalir.setFont(fontBotones);
        
        // Panel para botones
        JPanel panelBotones = new JPanel(new GridLayout(5, 1, 10, 10));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        panelBotones.add(btnAgregarProducto);
        panelBotones.add(btnAgregarStock);
        panelBotones.add(btnRestarStock);
        panelBotones.add(btnReporteInventario);
        panelBotones.add(btnSalir);
        
        // Configurar layout principal
        setLayout(new BorderLayout());
        add(panelBotones, BorderLayout.CENTER);
        
        // Agregar título
        JLabel titulo = new JLabel("SISTEMA DE GESTIÓN DE INVENTARIO", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(titulo, BorderLayout.NORTH);
    }
    
    private void configurarVentana() {
        setTitle("Sistema de Gestión de Inventario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 450);
        setLocationRelativeTo(null);
        setResizable(false);
    }
    
    // Métodos para agregar listeners a los botones
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
    
    // Métodos para mostrar diálogos de entrada
    public String mostrarInputDialog(String mensaje) {
        return JOptionPane.showInputDialog(this, mensaje);
    }
    
    public int mostrarInputDialogEntero(String mensaje) {
        try {
            String input = JOptionPane.showInputDialog(this, mensaje);
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    public void mostrarMensajeDialog(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}