package JERARQUICAS.TEST;


import JERARQUICAS.ARBOLBIN.ArbolBin;
import lineales.dinamicas.Lista;

public class TestArbolBin {
    public static void main(String[] args) {
        ArbolBin arbol = new ArbolBin();
        arbol.insertar(1, 1,'I');
        arbol.insertar(2, 1,'I');
        arbol.insertar(3, 1,'D');
        arbol.insertar(4, 2,'I');
        arbol.insertar(5, 3,'I');
        arbol.insertar(7, 2,'D');
        arbol.insertar(7, 5,'D');
        
        /* System.out.println(arbol.toString()); */
        arbol.altura();
        ArbolBin clon = arbol.clone();
        arbol.insertarPorPosicion(7, 6, 'I');
        System.out.println(arbol.equals(clon));
        System.out.println(arbol.toString());
        /* System.out.println(arbol.altura());
        System.err.println(arbol.nivel(60) + " nivel real ");
        System.out.println(arbol.padre(8) + " padre"); */
        /* arbol.vaciar();
        System.out.println(arbol.toString()); */
        Lista nivel = arbol.listarPreorden();
/*         System.out.println(arbol.estaRepetido(3));
 */    }

}
