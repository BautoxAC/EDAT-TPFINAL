/************* Autores ***********
Tomas Ismael Cárdenas Maldonado - FAI-4884
Bautista Tosi: Juan Bautista Tosi Griedassov- FAI-5162
Santiago Fuentes: Santiago Emanuel Fuentes FAI  - [5383]
Santiago Fuentes: PASCAL RIOS, IVO JAVIER, FAI-5200
*/
package lineales.dinamicas;

import lineales.dinamicas.NODO.Nodo;

public class Pila {
    private Nodo tope;

    // contructores
    public Pila() {
        this.tope = null;
    }

    // Observadores
    public Object obtenerTope() {
        Object top = null;
        if (!this.esVacia()) {
            top = this.tope.getElem();
        }
        return top;
    }

    // Propios del tipo
    public boolean apilar(Object nuevoElem) {
        Nodo nuevo = new Nodo(nuevoElem, this.tope);
        this.tope = nuevo;
        return true;
    }

    public boolean desapilar() {
        boolean hecho = false;
        if (!this.esVacia()) {
            this.tope = this.tope.getEnlace();
            hecho = true;
        }
        return hecho;
    }

    public boolean esVacia() {
        boolean siEsVacia = false;
        if (this.tope == null) {
            siEsVacia = true;
        }
        return siEsVacia;
    }

    public void vaciar() {
        this.tope = null;
    }

    public Pila clone() {
        Pila pilaClon = new Pila();
        pilaClon.tope = recorrerPila(this.tope);
        return pilaClon;
    }

    private Nodo recorrerPila(Nodo nodoEnlace) {
        Nodo nuevo = null;
        if (nodoEnlace != null) {
            nuevo = new Nodo(nodoEnlace.getElem(), recorrerPila(nodoEnlace.getEnlace()));
        }
        return nuevo;
    }

    public String toString() {
        Nodo aux = this.tope;
        String txt = "[";
        while (aux != null) {
            txt += aux.getElem().toString();
            aux = aux.getEnlace();
            if (aux != null) {
                txt += ",";
            }
        }
        txt += "]";
        return txt;
    }
}