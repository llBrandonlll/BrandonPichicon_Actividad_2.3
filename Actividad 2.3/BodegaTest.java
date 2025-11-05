package test;

import modelo.Bodega;
import modelo.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BodegaTest {
    private Bodega bodega;
    
    @BeforeEach
    void setUp() {
        bodega = new Bodega();
    }
    
    @Test
    void testAgregarStockSumaCorrectamente() {
        // Arrange
        bodega.agregarProducto("Laptop", 7);
        
        // Act
        boolean resultado = bodega.agregarStock("Laptop", 5);
        
        // Assert
        assertTrue(resultado, "Debería agregar stock correctamente");
        Producto producto = bodega.buscarProducto("Laptop");
        assertNotNull(producto, "El producto debería existir");
        assertEquals(12, producto.getStock(), "7 + 5 debería ser 12");
    }
    
    @Test
    void testRestarStockRestaCorrectamente() {
        // Arrange
        bodega.agregarProducto("Mouse", 10);
        
        // Act
        boolean resultado = bodega.restarStock("Mouse", 3);
        
        // Assert
        assertTrue(resultado, "Debería restar stock correctamente");
        Producto producto = bodega.buscarProducto("Mouse");
        assertNotNull(producto, "El producto debería existir");
        assertEquals(7, producto.getStock(), "10 - 3 debería ser 7");
    }
    
    @Test
    void testRestarStockNoPermiteNegativos() {
        // Arrange
        bodega.agregarProducto("Teclado", 10);
        
        // Act
        boolean resultado = bodega.restarStock("Teclado", 11);
        
        // Assert
        assertFalse(resultado, "No debería permitir stock negativo");
        Producto producto = bodega.buscarProducto("Teclado");
        assertNotNull(producto, "El producto debería existir");
        assertEquals(10, producto.getStock(), "El stock debería permanecer en 10");
    }
    
    @Test
    void testAgregarProducto() {
        // Act
        bodega.agregarProducto("Monitor", 5);
        
        // Assert
        Producto producto = bodega.buscarProducto("Monitor");
        assertNotNull(producto, "El producto debería existir");
        assertEquals("Monitor", producto.getNombre());
        assertEquals(5, producto.getStock());
    }
    
    @Test
    void testListarProductos() {
        // Arrange
        bodega.agregarProducto("Producto1", 1);
        bodega.agregarProducto("Producto2", 2);
        
        // Act & Assert
        assertEquals(2, bodega.listarProductos().size());
    }
}
