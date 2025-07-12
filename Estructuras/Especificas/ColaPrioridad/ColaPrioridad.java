package Estructuras.Especificas.ColaPrioridad;

import Estructuras.lineales.*;

public class ColaPrioridad {

    private NodoCP inicio;

    public ColaPrioridad() {

        this.inicio = null;

    }

    public boolean insertar(Object elem, int prioridad) {

        boolean insertado = false;
        Cola cola;
        NodoCP aux;
        NodoCP anterior;

        if (this.inicio == null) {
            cola = new Cola();
            cola.poner(elem);
            inicio = new NodoCP(prioridad, cola, null);
            insertado = true;
        } else {
            if (inicio.getPrioridad() == prioridad) {
                cola = inicio.getItems();
                cola.poner(elem);
                insertado = true;
            } else {
                if (prioridad < inicio.getPrioridad()) {
                    cola = new Cola();
                    aux = new NodoCP(prioridad, cola, inicio);
                    inicio = aux;
                    cola.poner(elem);
                    insertado = true;
                } else {
                    anterior = inicio;
                    aux = inicio.getEnlace();
                    while (aux != null && !insertado) {
                        if (aux.getPrioridad() == prioridad) {
                            cola = aux.getItems();
                            cola.poner(elem);
                            insertado = true;
                        }
                        anterior = aux;
                        aux = aux.getEnlace();
                    }
                    if (!insertado) {
                        cola = new Cola();
                        aux = new NodoCP(prioridad, cola, null);
                        anterior.setEnlace(aux);
                        cola.poner(elem);
                        insertado = true;
                    }
                }
            }
        }

        return insertado;
    }

    public boolean eliminarFrente() {

        boolean eliminado = false;
        Cola cola;

        if (this.inicio != null) {

            cola = this.inicio.getItems();
            cola.sacar();
            eliminado = true;

            if (cola.esVacia()) {
                this.inicio = this.inicio.getEnlace();
            }

        }

        return eliminado;
    }

    public Object recuperarFrente() {

        Object frente = null;
        Cola cola;

        if (this.inicio != null) {

            cola = this.inicio.getItems();
            frente = cola.obtenerFrente();

        }

        return frente;
    }

    public boolean esVacia () {
        return this.inicio == null;
    }
    
    public void vaciar () {
        this.inicio = null;
    } 

    public ColaPrioridad clone() {
        ColaPrioridad clon = new ColaPrioridad();
        clon.inicio = clonarCola(this.inicio);
        return clon;
    }

    private NodoCP clonarCola(NodoCP frenteActual) {
        NodoCP nodoAux = null;
        if (frenteActual != null) {
            nodoAux = new NodoCP(frenteActual.getPrioridad(), frenteActual.getItems().clone(), clonarCola(frenteActual.getEnlace()));
        }
        return nodoAux;
    }

    public String toString () {
        String texto = "["; 
        NodoCP base = this.inicio;
        while (base != null) {
            texto += base.getPrioridad();
            texto += ",("+base.getItems().toString()+")";
            base = base.getEnlace();
            if(base != null)
                texto+= ",";
            
        }
        texto += "]";
        return texto;
    }
    

}
