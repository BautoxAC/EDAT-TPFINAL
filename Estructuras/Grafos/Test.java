package TrabajoFinal.Estructuras.Grafos;

public class Test {
    
    public static void main(String[] args) {
        
        test1();

    }

    public static void test1() {

        Grafo grafo = new Grafo();

        grafo.insertarVertice(10);
        grafo.insertarVertice(11);
        grafo.insertarArco(10, 11, "hola");

        System.out.println(grafo.toString());

        System.out.println(grafo.eliminarArco(11, 11));
        System.out.println(grafo.toString());

        Grafo grafo2 = grafo.clone();

        System.out.println(grafo2.toString());

    }

}
