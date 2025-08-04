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
        assertFalse(grafo.insertarVertice("A"));
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
        assertFalse(grafo.insertarArco("A", "B", 20));
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
        assertFalse(grafo.existeArco("B", "A")); // Verificacion teniendo en cuenta que es un grafo dirigido
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
        assertEquals(3, camino.longitud()); // Tiene que ser A->B->C (max 10) en lugar de A->C (20)
    }

    @Test
    void testCaminoMinimoMaxEtiqueta1() {
        grafo.insertarVertice("A");
        grafo.insertarVertice("B");
        grafo.insertarVertice("C");
        grafo.insertarVertice("D");
        grafo.insertarArco("A", "B", 10);
        grafo.insertarArco("B", "C", 10);
        grafo.insertarArco("A", "D", 20);
        grafo.insertarArco("D", "C", 1);

        Lista camino = grafo.caminoMinimoMaxEtiqueta("A", "C");

        assertEquals(camino.toString(),"[A,D,C]");
    }

    @Test
    void testCaminoMinimoMaxEtiqueta2() {
        grafo.insertarVertice("A");
        grafo.insertarVertice("B");
        grafo.insertarVertice("D");
        grafo.insertarVertice("E");
        grafo.insertarVertice("F");
        grafo.insertarVertice("G");
        grafo.insertarVertice("H");
        grafo.insertarArco("A", "D", 40);
        grafo.insertarArco("A", "E", 100);
        grafo.insertarArco("A", "F", 20);
        grafo.insertarArco("A", "B", 60);
        grafo.insertarArco("E", "H", 10);
        grafo.insertarArco("H", "G", 40);
        grafo.insertarArco("F", "G", 70);

        Lista camino = grafo.caminoMinimoMaxEtiqueta("A", "G");

        assertEquals(camino.toString(),"[A,E,H,G]");
    }

    @Test
    void testCaminoMinimoMaxEtiqueta3() {
        grafo.insertarVertice("A");
        grafo.insertarVertice("B");
        grafo.insertarVertice("C");
        grafo.insertarVertice("D");
        grafo.insertarVertice("E");
        grafo.insertarArco("A", "D", 1);
        grafo.insertarArco("A", "C", 1);
        grafo.insertarArco("A", "B", 60);
        
        grafo.insertarArco("D", "E", 70);
        grafo.insertarArco("B", "E", 1);
        grafo.insertarArco("C", "E", 40);

        Lista camino = grafo.caminoMinimoMaxEtiqueta("A", "E");
        System.out.println(camino.toString());
        assertEquals(camino.longitud(),3);
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

        assertEquals(2, camino.longitud()); // Tiene que ser A->C directamente
    }

    @Test
    void testObtenerCaminoMasCorto1() {
        grafo.insertarVertice("A");
        grafo.insertarVertice("B");
        grafo.insertarVertice("D");
        grafo.insertarVertice("E");
        grafo.insertarVertice("F");
        grafo.insertarVertice("G");
        grafo.insertarVertice("H");
        grafo.insertarArco("A", "D", 40);
        grafo.insertarArco("A", "E", 100);
        grafo.insertarArco("A", "F", 20);
        grafo.insertarArco("A", "B", 60);
        grafo.insertarArco("E", "H", 10);
        grafo.insertarArco("H", "G", 40);
        grafo.insertarArco("F", "G", 70);

        Lista camino = grafo.obtenerCaminoMasCorto("A", "G");

        assertEquals(camino.toString(),"[A,F,G]");
    }

    @Test
    void testObtenerCaminoMasCorto2() {
        grafo.insertarVertice("A");
        grafo.insertarVertice("B");
        grafo.insertarVertice("D");
        grafo.insertarVertice("E");
        grafo.insertarVertice("F");
        grafo.insertarVertice("G");
        grafo.insertarArco("A", "D", 40);
        grafo.insertarArco("A", "E", 100);
        grafo.insertarArco("A", "F", 20);
        grafo.insertarArco("A", "B", 60);
        grafo.insertarArco("E", "G", 40);
        grafo.insertarArco("F", "G", 70);

        Lista camino = grafo.obtenerCaminoMasCorto("A", "G");

        assertEquals(camino.longitud(),3);
    }

    @Test
    void testClone() {
        grafo.insertarVertice("A");
        grafo.insertarVertice("B");
        grafo.insertarArco("A", "B", 10);

        Grafo clon = grafo.clone();
        assertTrue(clon.existeVertice("A"));
        assertTrue(clon.existeVertice("B"));
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