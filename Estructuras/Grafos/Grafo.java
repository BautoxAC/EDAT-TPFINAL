package Estructuras.Grafos;

import Estructuras.lineales.*;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import Estructuras.Especificas.ColaPrioridad.*;
import Estructuras.Especificas.Diccionario.*;

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
        NodoAdy aux;
        if (origenVer != null) {
            aux = origenVer.getPrimerAdy();
            while (aux != null) {
                if (aux.getVertice().getElem().equals(destino)) {
                    encontrado = true;
                }
                aux = aux.getSigAdyacente();

            }
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
        if (actual != null) {
            if (actual.getElem().equals(elem)) {
                this.inicio = actual.getSigVertice();
            } else {
                this.eliminarArcoAdyacente(elem, actual);
                NodoVert ant = actual;
                actual = actual.getSigVertice();
                while (actual != null && !eliminado) {
                    if (actual.getElem().equals(elem)) {
                        ant.setSigVertice(actual.getSigVertice());
                        eliminado = true;
                    } else {
                        this.eliminarArcoAdyacente(elem, actual);
                        ant = actual;
                        actual = actual.getSigVertice();
                    }
                }

            }
        }

        return eliminado;
    }

    private boolean eliminarArcoAdyacente(Object elem, NodoVert n) {
        NodoAdy actual;
        NodoAdy ant;
        boolean encontrado = false;
        if (n != null) {
            actual = n.getPrimerAdy();
            if (actual != null) {
                if (actual.getVertice().getElem().equals(elem)) {
                    n.setPrimerAdy(actual.getSigAdyacente());
                    encontrado = true;
                } else {
                    ant = actual;
                    actual = actual.getSigAdyacente();
                    while (actual != null && !encontrado) {
                        if (actual.getVertice().getElem().equals(elem)) {
                            ant.setSigAdyacente(actual.getSigAdyacente());
                            encontrado = true;
                        } else {
                            ant = actual;
                            actual = actual.getSigAdyacente();
                        }
                    }
                }
            }

        }
        return encontrado;
    }

    public boolean cambiarEtiqueta(Object origen, Object destino, int etiqueta) {
        boolean exito = false;

        NodoVert nodoOrigen = ubicarVertice(origen);
        NodoAdy ady;

        if (nodoOrigen != null) {

            ady = nodoOrigen.getPrimerAdy();
            while (ady != null && !exito) {

                if (ady.getVertice().getElem().equals(destino)) {
                    ady.setEtiqueta(etiqueta);
                    exito = true;
                }
                ady = ady.getSigAdyacente();

            }

        }
        return exito;
    }

    public boolean eliminarArco(Object origen, Object destino) {
        boolean eliminado = false;
        NodoVert origenVer = ubicarVertice(origen);
        if (origenVer != null) {
            eliminado = this.eliminarArcoAdyacente(destino, origenVer);
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

                string += nodoAdyacente.getVertice().getElem() + "," + nodoAdyacente.getEtiqueta();

                nodoAdyacente = nodoAdyacente.getSigAdyacente();
                if (nodoAdyacente != null) {
                    string += ";";
                }
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
        NodoAdy ady;
        while (!cola.esVacia()) {
            frente = (NodoVert) cola.obtenerFrente();
            cola.sacar();

            ady = frente.getPrimerAdy();

            while (ady != null) {
                if (lista.localizar(ady.getVertice().getElem()) < 0) {
                    lista.insertar(ady.getVertice().getElem(), lista.longitud() + 1);
                    cola.poner(ady.getVertice());
                }
                ady = ady.getSigAdyacente();
            }

        }

    }

    public Lista caminoMinimoMaxEtiqueta(Object origen, Object destino) {
        Lista caminoActual = new Lista();
        Lista mejorCamino = new Lista();
        Lista visitados = new Lista();
        int[] minMax = { Integer.MAX_VALUE };

        NodoVert verticeOrigen = ubicarVertice(origen);
        NodoVert verticeDestino = ubicarVertice(destino);

        // metodo recursivo que usa DFS (recorrido en profunidad) para encontrar el
        // camino con la tuberia que tenga menor caudal maximo.

        if (verticeOrigen != null && verticeDestino != null) {
            dfsMinimoMaxEtiqueta(verticeOrigen, destino, caminoActual, mejorCamino, visitados, Integer.MAX_VALUE, minMax);
        }

        return mejorCamino;
    }

    private void dfsMinimoMaxEtiqueta(NodoVert actual, Object destino, Lista caminoActual, Lista mejorCamino,
            Lista visitados, int minCaudalActual, int[] minMax) {

        NodoAdy ady;
        Object vecino;
        int nuevoMin;

        visitados.insertar(actual.getElem(), visitados.longitud() + 1);
        caminoActual.insertar(actual.getElem(), caminoActual.longitud() + 1);

        if (actual.getElem().equals(destino)) {
            if (minCaudalActual < minMax[0]) {
                minMax[0] = minCaudalActual;
                // mejorCamino = caminoActual.clone(); // No usamos clone por la referencia recursiva.
                mejorCamino.vaciar();
                for (int i = 1; i <= caminoActual.longitud(); i++) {
                    mejorCamino.insertar(caminoActual.recuperar(i), i);
                }
            }
        } else {
            ady = actual.getPrimerAdy();
            while (ady != null) {
                vecino = ady.getVertice().getElem();
                if (visitados.localizar(vecino) < 0) {
                    nuevoMin = Math.min(minCaudalActual, (int) ady.getEtiqueta());
                    dfsMinimoMaxEtiqueta(ady.getVertice(), destino, caminoActual, mejorCamino, visitados, nuevoMin,
                            minMax);
                }
                ady = ady.getSigAdyacente();
            }
        }

        visitados.eliminar(visitados.localizar(actual.getElem()));
        caminoActual.eliminar(caminoActual.longitud());
    }

    public Lista obtenerCaminoMasCorto(Object origen, Object destino) {

        // BFS (recorrido por anchura) para encontrar el camino mas corto

        NodoVert verticeOrigen = ubicarVertice(origen);
        NodoVert verticeDestino = ubicarVertice(destino);
        Lista resultado = new Lista();
        boolean encontrado = false;

        Cola cola;
        Lista visitados;
        Lista caminoInicial;

        Object[] par;
        NodoVert actual;
        Lista camino;

        NodoAdy ady;
        Object vecino;

        Lista nuevoCamino;

        if (verticeOrigen != null && verticeDestino != null) {
            cola = new Cola();
            visitados = new Lista();
            caminoInicial = new Lista();

            caminoInicial.insertar(origen, 1);

            cola.poner(new Object[] { verticeOrigen, caminoInicial });
            visitados.insertar(origen, 1);

            while (!cola.esVacia() && !encontrado) {
                par = (Object[]) cola.obtenerFrente();
                cola.sacar();
                actual = (NodoVert) par[0];
                camino = (Lista) par[1];

                if (actual.getElem().equals(destino)) {
                    resultado = camino;
                    encontrado = true;
                } else {
                    ady = actual.getPrimerAdy();
                    while (ady != null) {
                        vecino = ady.getVertice().getElem();
                        if (visitados.localizar(vecino) < 0) {
                            nuevoCamino = camino.clone();
                            nuevoCamino.insertar(vecino, nuevoCamino.longitud() + 1);
                            cola.poner(new Object[] { ady.getVertice(), nuevoCamino });
                            visitados.insertar(vecino, visitados.longitud() + 1);
                        }
                        ady = ady.getSigAdyacente();
                    }

                }

            }
        }

        return resultado;
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