import Estructuras.Especificas.Diccionario.Diccionario;
import Estructuras.lineales.Lista;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DiccionarioTest {
    private Diccionario diccionario;

    @BeforeEach
    void setUp() {
        diccionario = new Diccionario();
    }

    @Test
    void testDiccionarioVacioInicialmente() {
        assertTrue(diccionario.vacio());
    }

    @Test
    void testInsertarElemento() {
        Object[] par = {"clave1", "valor1"};
        assertTrue(diccionario.insertar(par));
        assertFalse(diccionario.vacio());
    }

    @Test
    void testInsertarElementoDuplicado() {
        Object[] par1 = {"clave1", "valor1"};
        Object[] par2 = {"clave1", "valor2"};
        
        assertTrue(diccionario.insertar(par1));
        assertFalse(diccionario.insertar(par2));
    }

    @Test
    void testEliminarElementoExistente() {
        Object[] par = {"clave1", "valor1"};
        diccionario.insertar(par);
        
        assertTrue(diccionario.eliminar("clave1"));
        assertTrue(diccionario.vacio());
    }

    @Test
    void testEliminarElementoNoExistente() {
        assertFalse(diccionario.eliminar("claveInexistente"));
    }

    @Test
    void testPerteneceElementoExistente() {
        Object[] par = {"clave1", "valor1"};
        diccionario.insertar(par);
        
        assertTrue(diccionario.pertenece("clave1"));
    }

    @Test
    void testPerteneceElementoNoExistente() {
        assertFalse(diccionario.pertenece("claveInexistente"));
    }

    @Test
    void testObtenerDatoExistente() {
        Object[] par = {"clave1", "valor1"};
        diccionario.insertar(par);
        
        assertEquals("valor1", diccionario.obtenerDato("clave1"));
    }

    @Test
    void testObtenerDatoNoExistente() {
        assertNull(diccionario.obtenerDato("claveInexistente"));
    }

    @Test
    void testListarRango() {
        Object[] par1 = {"a", "valorA"};
        Object[] par2 = {"b", "valorB"};
        Object[] par3 = {"c", "valorC"};
        
        diccionario.insertar(par1);
        diccionario.insertar(par2);
        diccionario.insertar(par3);
        
        Lista rango = diccionario.listarRango("a", "b");
        assertEquals(2, rango.longitud());
    }

    @Test
    void testListarClaves() {
        Object[] par1 = {"a", "valorA"};
        Object[] par2 = {"b", "valorB"};
        
        diccionario.insertar(par1);
        diccionario.insertar(par2);
        
        Lista claves = diccionario.listarClaves();
        assertEquals(2, claves.longitud());
    }

    @Test
    void testListarDatos() {
        Object[] par1 = {"a", "valorA"};
        Object[] par2 = {"b", "valorB"};
        
        diccionario.insertar(par1);
        diccionario.insertar(par2);
        
        Lista datos = diccionario.listarDatos();
        assertEquals(2, datos.longitud());
    }

    @Test
    void testEjemploDesIzqHijoDesDer() {
        Object[][] pares = {{30,"dato1"},{15,"dato1"},{40,"dato1"},{10,"dato1"},{25,"dato1"},{35,"dato1"},{50,"dato1"},{5,"dato1"},{17,"dato1"},{27,"dato1"},{37,"dato1"},{18,"dato1"}};
        
        for (int i = 0; i < pares.length; i++) {
            diccionario.insertar(pares[i]);
        }
        
        assertTrue(diccionario.eliminar(30));

        Lista claves = diccionario.listarClaves();

        assertEquals("[27,15,10,5,18,17,25,40,35,37,50]", claves.toString());
    }

    @Test
    void testEjemploDesDerHijoDesIzq() {
        Object[][] pares = {{30,"dato1"},{15,"dato1"},{40,"dato1"},{10,"dato1"},{25,"dato1"},{35,"dato1"},{50,"dato1"},{5,"dato1"},{17,"dato1"},{27,"dato1"},{37,"dato1"},{26,"dato1"}};
        
        for (int i = 0; i < pares.length; i++) {
            diccionario.insertar(pares[i]);
        }
        
        assertTrue(diccionario.eliminar(17));

        Lista claves = diccionario.listarClaves();

        assertEquals("[30,15,10,5,26,25,27,40,35,37,50]", claves.toString());

    }

}