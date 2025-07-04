/************* Autores ***********
Tomas Ismael Cárdenas Maldonado - FAI-4884
Bautista Tosi: Juan Bautista Tosi Griedassov- FAI-5162
Santiago Fuentes: Santiago Emanuel Fuentes FAI  - [5383]
Santiago Fuentes: PASCAL RIOS, IVO JAVIER, FAI-5200
*/
package lineales.dinamicas;

import lineales.dinamicas.NODO.Nodo;

public class Cola {
    private Nodo frente;
    private Nodo fin;

    public Cola() {
        this.frente = null;
        this.fin = null;
    }

    public boolean esVacia() {
        boolean vacia = true;
        if (this.frente != null && this.fin != null) {
            vacia = false;
        }
        return vacia;
    }

    public boolean poner(Object elem) {
        Nodo nodo = new Nodo(elem, null);
        if (this.esVacia()) {
            this.frente = nodo;
        } else {
            this.fin.setEnlace(nodo);
        }
        this.fin = nodo;
        return true;
    }

    public boolean sacar() {
        boolean hecho = true;
        if (this.esVacia()) {
            hecho = false;
        } else {
            this.frente = this.frente.getEnlace();
            if (this.frente == null) {
                this.fin = null;
            }
        }
        return hecho;
    }

    public Object obtenerFrente() {
        Object frente;
        if (this.esVacia()) {
            frente = null;
        } else {
            frente = this.frente.getElem();
        }
        return frente;
    }

    public void vaciar() {
        this.fin = null;
        this.frente = null;
    }

    public String toString() {
        String txt = "[";
        Nodo nodo = this.frente;
        while (nodo != null) {
            txt += nodo.getElem();
            if (nodo.getEnlace() != null) {
                txt += ",";
            }
            nodo = nodo.getEnlace();
        }
        txt += "]";
        return txt;
    }

    private Nodo recorrerCola(Nodo nodo, Cola colaClon) {
        Nodo nuevo = null;
        if (nodo != null) {
            nuevo = new Nodo(nodo.getElem(), recorrerCola(nodo.getEnlace(), colaClon));
            if (nodo.getEnlace() == null) {
                colaClon.fin = nodo;
            }
        }
        return nuevo;
    }

    public Cola clone() {
        Cola colaClon = new Cola();
        if (!this.esVacia()) {
            colaClon.frente = recorrerCola(this.frente, colaClon);
        }
        return colaClon;
    }
}
