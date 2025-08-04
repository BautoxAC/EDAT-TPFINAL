import Estructuras.Grafos.Grafo;
import Estructuras.lineales.Lista;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GrafoTest {
    private Grafo grafo;

    @BeforeEach
    void setUp() {
        grafo = new Grafo();
    }

    @Test
    void testGrafoVacioInicialmente() {
        assertTrue(grafo.esVacio());
    }

    @Test
    void testInsertarVertice() {
        assertTrue(grafo.insertarVertice("A"));
        assertFalse(grafo.esVacio());
    }

    @Test
    void testInsertarVerticeDuplicado() {
        grafo.insertarVertice("A");
        assertFalse(grafo.insertarVertice("A")); // No debería permitir duplicados
    }

    @Test
    void testInsertarArco() {
        grafo.insertarVertice("A");
        grafo.insertarVertice("B");
        
        assertTrue(grafo.insertarArco("A", "B", 10));
    }

    @Test
    void testInsertarArcoConVerticesInexistentes() {
        assertFalse(grafo.insertarArco("A", "B", 10));
    }

    @Test
    void testInsertarArcoDuplicado() {
        grafo.insertarVertice("A");
        grafo.insertarVertice("B");
        
        grafo.insertarArco("A", "B", 10);
        assertFalse(grafo.insertarArco("A", "B", 20)); // No debería permitir duplicados
    }

    @Test
    void testExisteVertice() {
        grafo.insertarVertice("A");
        assertTrue(grafo.existeVertice("A"));
        assertFalse(grafo.existeVertice("B"));
    }

    @Test
    void testExisteArco() {
        grafo.insertarVertice("A");
        grafo.insertarVertice("B");
        grafo.insertarArco("A", "B", 10);
        
        assertTrue(grafo.existeArco("A", "B"));
        assertFalse(grafo.existeArco("B", "A")); // Grafo dirigido
    }

    @Test
    void testEliminarVertice() {
        grafo.insertarVertice("A");
        assertTrue(grafo.eliminarVertice("A"));
        assertTrue(grafo.esVacio());
    }

    @Test
    void testEliminarVerticeConArcos() {
        grafo.insertarVertice("A");
        grafo.insertarVertice("B");
        grafo.insertarArco("A", "B", 10);
        
        assertTrue(grafo.eliminarVertice("A"));
        assertFalse(grafo.existeArco("A", "B"));
    }

    @Test
    void testEliminarArco() {
        grafo.insertarVertice("A");
        grafo.insertarVertice("B");
        grafo.insertarArco("A", "B", 10);
        
        assertTrue(grafo.eliminarArco("A", "B"));
        assertFalse(grafo.existeArco("A", "B"));
    }

    @Test
    void testCambiarEtiquetaArco() {
        grafo.insertarVertice("A");
        grafo.insertarVertice("B");
        grafo.insertarArco("A", "B", 10);
        
        assertTrue(grafo.cambiarEtiqueta("A", "B", 20));
    }

    @Test
    void testListarEnProfundidad() {
        grafo.insertarVertice("A");
        grafo.insertarVertice("B");
        grafo.insertarVertice("C");
        grafo.insertarArco("A", "B", 10);
        grafo.insertarArco("B", "C", 20);
        
        Lista recorrido = grafo.listarEnProfunidad();
        assertEquals(3, recorrido.longitud());
    }

    @Test
    void testListarEnAnchura() {
        grafo.insertarVertice("A");
        grafo.insertarVertice("B");
        grafo.insertarVertice("C");
        grafo.insertarArco("A", "B", 10);
        grafo.insertarArco("A", "C", 20);
        
        Lista recorrido = grafo.listarEnAnchura();
        assertEquals(3, recorrido.longitud());
    }

    @Test
    void testCaminoMinimoMaxEtiqueta() {
        grafo.insertarVertice("A");
        grafo.insertarVertice("B");
        grafo.insertarVertice("C");
        grafo.insertarArco("A", "B", 10);
        grafo.insertarArco("B", "C", 5);
        grafo.insertarArco("A", "C", 20);
        
        Lista camino = grafo.caminoMinimoMaxEtiqueta("A", "C");
        assertEquals(3, camino.longitud()); // Debería ser A->B->C (max 10) en lugar de A->C (20)
    }

    @Test
    void testObtenerCaminoMasCorto() {
        grafo.insertarVertice("A");
        grafo.insertarVertice("B");
        grafo.insertarVertice("C");
        grafo.insertarArco("A", "B", 10);
        grafo.insertarArco("B", "C", 20);
        grafo.insertarArco("A", "C", 30);
        
        Lista camino = grafo.obtenerCaminoMasCorto("A", "C");
        assertEquals(2, camino.longitud()); // Debería ser A->C directamente
    }

    @Test
    void testClone() {
        grafo.insertarVertice("A");
        grafo.insertarVertice("B");
        grafo.insertarArco("A", "B", 10);
        System.out.println(grafo.toString());

        Grafo clon = grafo.clone();
        assertTrue(clon.existeVertice("A"));
        assertTrue(clon.existeVertice("B"));
        System.out.println(clon.toString());
        System.out.println("HOLA");
        assertTrue(clon.existeArco("A", "B"));
    }

    @Test
    void testObtenerAdyacentes() {
        grafo.insertarVertice("A");
        grafo.insertarVertice("B");
        grafo.insertarVertice("C");
        grafo.insertarArco("A", "B", 10);
        grafo.insertarArco("A", "C", 20);
        
        Lista adyacentes = grafo.obtenerAdyacentes("A");
        assertEquals(2, adyacentes.longitud());
    }
}