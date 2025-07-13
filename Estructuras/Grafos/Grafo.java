package Estructuras.Grafos;

import Estructuras.lineales.*;

import java.util.PriorityQueue;

import Estructuras.Especificas.ColaPrioridad.*;

public class Grafo {
    private NodoVert inicio;

    public Grafo() {
        this.inicio = null;
    }

    public boolean insertarVertice(Object elem) {
        boolean hecho = false;
        boolean encontrado = (ubicarVertice(elem) != null);
        if (!encontrado) {
            NodoVert nuevo = new NodoVert(elem, this.inicio, null);
            this.inicio = nuevo;
        }
        return hecho;
    }

    public boolean insertarArco(Object origen, Object destino, Object etiqueta) {
        boolean hecho = false;
        NodoVert origenVer = ubicarVertice(origen);
        NodoVert destinoVer = ubicarVertice(destino);
        boolean encontrado = ubicarArcoDirigido(origenVer, destino);
        if (origenVer != null && destinoVer != null && !encontrado && !origen.equals(destino)) {
            NodoAdy nuevo = new NodoAdy(destinoVer, origenVer.getPrimerAdy(), etiqueta);
            origenVer.setPrimerAdy(nuevo);
        }
        return hecho;
    }

    public boolean existeArco(Object origen, Object destino) {
        boolean encontrado = false;
        NodoVert origenVer = ubicarVertice(origen);
        if (origenVer != null) {
            encontrado = ubicarArcoDirigido(origenVer, destino);
        }
        return encontrado;
    }

    private boolean ubicarArcoDirigido(NodoVert origenVer, Object destino) {
        boolean encontrado = false;
        NodoAdy aux = origenVer.getPrimerAdy();
        while (aux != null) {
            if (aux.getVertice().getElem().equals(destino)) {
                encontrado = true;
            }
            aux = aux.getSigAdyacente();

        }
        return encontrado;
    }

    private NodoVert ubicarVertice(Object elem) {
        NodoVert encontrado = null;
        NodoVert aux = this.inicio;
        while (aux != null && encontrado == null) {
            if (aux.getElem().equals(elem)) {
                encontrado = aux;
            }
            aux = aux.getSigVertice();
        }
        return encontrado;
    }

    public boolean existeVertice(Object elem) {
        return (ubicarVertice(elem) != null);
    }

    public boolean eliminarVertice(Object elem) {
        NodoVert actual = this.inicio;
        boolean eliminado = false;
        if (actual != null && actual.getElem().equals(elem)) {
            this.inicio = actual.getSigVertice();
        } else {
            NodoVert ant = actual;
            NodoAdy actualEnlace = null;
            actual = actual.getSigVertice();
            while (actual != null && !eliminado) {
                if (actual.getElem().equals(elem)) {
                    actualEnlace = actual.getPrimerAdy();
                    ant.setSigVertice(actual.getSigVertice());
                    eliminado = true;
                    while (actualEnlace != null) {
                        eliminarArcoAdyacentes(actualEnlace.getVertice(), actual.getElem());
                        actualEnlace = actualEnlace.getSigAdyacente();
                    }
                } else {
                    ant = actual;
                    actual = actual.getSigVertice();
                }
            }

        }

        return eliminado;
    }

    private boolean eliminarArcoAdyacentes(NodoVert n, Object elem) {
        boolean hecho = false;
        if (n != null) {

            NodoAdy nodoRevisar = n.getPrimerAdy();
            if (nodoRevisar.getVertice().getElem().equals(elem)) {
                n.setPrimerAdy(nodoRevisar.getSigAdyacente());
                hecho = true;
            } else {
                NodoAdy ant = nodoRevisar;
                nodoRevisar = nodoRevisar.getSigAdyacente();
                while (nodoRevisar != null && !hecho) {
                    if (nodoRevisar.getVertice().getElem().equals(elem)) {
                        ant.setSigAdyacente(nodoRevisar.getSigAdyacente());
                        hecho = true;
                    } else {
                        ant = nodoRevisar;
                        nodoRevisar = nodoRevisar.getSigAdyacente();
                    }
                }
            }
        }
        return hecho;
    }

    public boolean eliminarArco(Object origen, Object destino) {
        boolean eliminado = false;
        NodoVert origenVer = ubicarVertice(origen);
        if (origenVer != null) {
            eliminado = eliminarArcoAdyacentes(origenVer, destino);
        }
        return eliminado;
    }

    public void vaciar() {
        this.inicio = null;
    }

    public boolean esVacio() {
        return this.inicio == null;
    }

    public String toString() {

        String string = "";

        NodoVert verticeAux = this.inicio;
        NodoAdy nodoAdyacente = null;

        while (verticeAux != null) {

            string = string + "Vertice: " + verticeAux.getElem() + " [";

            nodoAdyacente = verticeAux.getPrimerAdy();

            while (nodoAdyacente != null) {

                string += nodoAdyacente.getVertice().getElem() + "," + nodoAdyacente.getEtiqueta() + ";";

                nodoAdyacente = nodoAdyacente.getSigAdyacente();

            }

            string += "]" + "\n";

            verticeAux = verticeAux.getSigVertice();

        }

        return string;

    }

    public Lista listarEnProfunidad() {

        Lista visitados = new Lista();

        NodoVert aux = this.inicio;

        while (aux != null) {

            if (visitados.localizar(aux.getElem()) < 0) {

                listarEnProfunidadAux(aux, visitados);

            }

            aux = aux.getSigVertice();

        }

        return visitados;

    }

    private void listarEnProfunidadAux(NodoVert nodoActual, Lista visitados) {

        if (nodoActual != null) {

            visitados.insertar(nodoActual.getElem(), visitados.longitud() + 1);

            NodoAdy adyacente = nodoActual.getPrimerAdy();

            while (adyacente != null) {

                if (visitados.localizar(adyacente.getVertice().getElem()) < 0) {

                    listarEnProfunidadAux(adyacente.getVertice(), visitados);

                }

                adyacente = adyacente.getSigAdyacente();

            }

        }

    }

    public Lista listarEnAnchura() {

        Lista visitados = new Lista();
        NodoVert aux = this.inicio;

        while (aux != null) {
            if (visitados.localizar(aux.getElem()) < 0) {
                listarEnAnchuraAux(aux, visitados);
            }
            aux = aux.getSigVertice();
        }

        return visitados;

    }

    private void listarEnAnchuraAux(NodoVert verticeInicial, Lista lista) {

        Cola cola = new Cola();

        lista.insertar(verticeInicial.getElem(), lista.longitud() + 1);
        cola.poner(verticeInicial);

        NodoVert frente;
        Lista adyacentes;
        NodoVert ady;
        while (!cola.esVacia()) {
            frente = (NodoVert) cola.obtenerFrente();
            cola.sacar();

            adyacentes = obtenerAdyacentes(frente);

            while (!adyacentes.esVacia()) {
                ady = (NodoVert) adyacentes.recuperar(adyacentes.longitud());
                if (lista.localizar(ady.getElem()) == -1) {
                    lista.insertar(ady.getElem(), lista.longitud() + 1);
                    cola.poner(ady);
                }
            }

        }

    }

    public Lista caminoMinimoMaxEtiqueta() {

        Lista visitados = new Lista();
        NodoVert aux = this.inicio;

        while (aux != null) {
            if (visitados.localizar(aux.getElem()) < 0) {
                caminoMinimoMaxEtiquetaAux(aux, visitados);
            }
            aux = aux.getSigVertice();
        }

        return visitados;

    }

    private void caminoMinimoMaxEtiquetaAux(NodoVert verticeInicial, Lista lista) {

        ColaPrioridad colaPrioridad = new ColaPrioridad();

        lista.insertar(verticeInicial.getElem(), lista.longitud() + 1);
        colaPrioridad.insertar(verticeInicial, 1);

        NodoVert frente;
        Lista adyacentes;
        NodoAdy ady;
        while (!colaPrioridad.esVacia()) {
            frente = (NodoVert) colaPrioridad.recuperarFrente();
            colaPrioridad.eliminarFrente();

            adyacentes = obtenerAdyacentes(frente);

            while (!adyacentes.esVacia()) {
                ady = (NodoAdy) adyacentes.recuperar(adyacentes.longitud());
                if (lista.localizar(ady.getVertice().getElem()) == -1) {
                    lista.insertar(ady.getVertice().getElem(), lista.longitud() + 1);
                    colaPrioridad.insertar(ady, (int) ady.getEtiqueta());
                }
            }

        }

    }

    public Lista caminoMinimoMaxEtiqueta(Object origen, Object destino) {
        ColaPrioridad cola = new ColaPrioridad();
        Lista lista = new Lista();
        Lista mejorCamino = null;

        NodoVert verticeOrigen = ubicarVertice(origen);

        lista.insertar(verticeOrigen.getElem(), 1);
        cola.insertar(new Object[] {verticeOrigen, Integer.MAX_VALUE, lista}, Integer.MAX_VALUE);

        int minTotal = Integer.MAX_VALUE;
        int minActual;

        NodoAdy ady;

        Object[] frente;

        NodoVert nodoActual;
        Lista caminoActual;
        Lista nuevoCamino;

        int nuevoMin;

        while (!cola.esVacia()) {
            frente = (Object []) cola.recuperarFrente();
            cola.eliminarFrente();

            nodoActual = (NodoVert) frente[0];
            minActual = (int) frente[1];
            caminoActual = (Lista) frente[2];

            if (nodoActual.getElem().equals(destino)) {
                if (minActual < minTotal) {
                    minTotal = minActual;
                    mejorCamino = caminoActual;
                }
            } else {
                ady = nodoActual.getPrimerAdy();
                while (ady != null) {
                    nuevoMin = Math.min(minActual, (int) ady.getEtiqueta());
                    nuevoCamino = caminoActual.clone();
                    nuevoCamino.insertar(ady.getVertice().getElem(), nuevoCamino.longitud()+1);

                    cola.insertar(new Object[] {ady.getVertice(), nuevoMin, nuevoCamino}, nuevoMin);

                    ady = ady.getSigAdyacente();
                }

            }

        }
        return mejorCamino;
    }

    public Grafo clone() {

        Grafo clon = new Grafo();

        clon.inicio = clonarVert(this.inicio);

        return clon;

    }

    private NodoVert clonarVert(NodoVert nodoActual) {

        NodoVert nodoVertice = null;

        if (nodoActual != null) {

            nodoVertice = new NodoVert(nodoActual.getElem(), clonarVert(nodoActual.getSigVertice()), null);
            nodoVertice.setPrimerAdy(clonarAdy(nodoActual.getPrimerAdy(), nodoVertice));

        }

        return nodoVertice;

    }

    private NodoAdy clonarAdy(NodoAdy nodoActual, NodoVert nodoVertice) {

        NodoAdy nodo = null;

        if (nodoActual != null) {

            nodo = new NodoAdy(nodoVertice, clonarAdy(nodoActual.getSigAdyacente(), nodoVertice),
                    nodoActual.getEtiqueta());

        }

        return nodo;

    }

    public Lista obtenerAdyacentes(Object vertice) {
        Lista adyacentes = new Lista();
        NodoVert nodo = ubicarVertice(vertice);
        if (nodo != null) {
            NodoAdy ady = nodo.getPrimerAdy();
            int pos = 1;
            while (ady != null) {
                adyacentes.insertar(ady.getVertice().getElem(), pos++);
                ady = ady.getSigAdyacente();
            }
        }
        return adyacentes;
    }

}