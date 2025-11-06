package test;  // Paquete para pruebas

// Importaciones para testing
import modelo.Bodega;
import vista.Interfaz;
import controlador.ControladorInventario;
import org.junit.Before;    // Anotación para método que se ejecuta antes de cada test
import org.junit.Test;      // Anotación para marcar métodos de test
import static org.junit.Assert.*;  // Importa métodos de aserción

public class BodegaTest {  // Clase de pruebas para Bodega
    
    private Bodega bodega;  // Instancia de Bodega para testing
    
    @Before  // Este método se ejecuta antes de cada test
    public void setUp() {
        bodega = new Bodega();  // Crea nueva instancia de Bodega para cada test
    }
    
    @Test  // Marca este método como test
    public void pruebaAgregarStock() {  // Test para agregar stock
        // Prueba: 7 + 5 = 12
        bodega.agregarProducto("Producto Test", 7);  // Agrega producto con stock 7
        boolean resultado = bodega.agregarStock("Producto Test", 5);  // Agrega 5 al stock
        assertTrue("Debería agregar stock exitosamente", resultado);  // Verifica que la operación fue exitosa
        assertEquals("La suma de stock debería ser 12", 12, bodega.buscarProducto("Producto Test").getStock());  // Verifica stock final
    }
    
    @Test
    public void pruebaRestarStockExito() {  // Test para restar stock exitoso
        // Prueba: 10 - 3 = 7
        bodega.agregarProducto("Producto Test", 10);  // Agrega producto con stock 10
        boolean resultado = bodega.restarStock("Producto Test", 3);  // Resta 3 al stock
        assertTrue("Debería restar stock exitosamente", resultado);  // Verifica que la operación fue exitosa
        assertEquals("La resta de stock debería ser 7", 7, bodega.buscarProducto("Producto Test").getStock());  // Verifica stock final
    }
    
    @Test
    public void pruebaRestarStockSinNegativos() {  // Test para evitar stock negativo
        // Prueba: 10 - 11 = NO PERMITIDO (debe mantener stock en 10)
        bodega.agregarProducto("Producto Test", 10);  // Agrega producto con stock 10
        boolean resultado = bodega.restarStock("Producto Test", 11);  // Intenta restar 11 (más del stock disponible)
        assertFalse("No debería permitir restar más stock del disponible", resultado);  // Verifica que la operación falló
        assertEquals("El stock debería mantenerse en 10", 10, bodega.buscarProducto("Producto Test").getStock());  // Verifica que el stock no cambió
    }
}
