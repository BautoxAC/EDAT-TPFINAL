package Estructuras.Grafos;

import Estructuras.lineales.*;

import java.util.PriorityQueue;

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
        ColaPrioridad cola;
        Lista caminoInicial;
        Lista mejorCamino = new Lista();

        NodoVert verticeOrigen = ubicarVertice(origen);

        int menorMaximoEncontrado;

        Object[] frente;
        NodoVert nodoActual;
        int maxActual;
        Lista caminoActual;

        NodoAdy ady;

        Object ciudadDestino;
        int nuevoMax;
        Lista nuevoCamino;

        boolean encontrado;

        if (verticeOrigen != null) {

            cola = new ColaPrioridad();
            caminoInicial = new Lista();

            menorMaximoEncontrado = Integer.MAX_VALUE;

            caminoInicial.insertar(verticeOrigen.getElem(), 1);
            cola.insertar(new Object[] { verticeOrigen, 0, caminoInicial }, 0);

            encontrado = false;

            while (!cola.esVacia() && !encontrado) {

                frente = (Object[]) cola.recuperarFrente();
                cola.eliminarFrente();

                nodoActual = (NodoVert) frente[0];
                maxActual = (int) frente[1];
                caminoActual = (Lista) frente[2];

                if (nodoActual.getElem().equals(destino)) {
                     if (maxActual < menorMaximoEncontrado) {
                     menorMaximoEncontrado = maxActual;
                    mejorCamino = caminoActual;
                 }
/*                     encontrado = true;
 */                } else {
                    ady = nodoActual.getPrimerAdy();
                    while (ady != null) {
                        ciudadDestino = ady.getVertice().getElem();

                        if (caminoActual.localizar(ciudadDestino) < 0) {
                            nuevoMax = Math.max(maxActual, (int) ady.getEtiqueta());
                            nuevoCamino = caminoActual.clone();
                            nuevoCamino.insertar(ciudadDestino, nuevoCamino.longitud() + 1);

                            cola.insertar(new Object[] { ady.getVertice(), nuevoMax, nuevoCamino }, nuevoMax);
                        }

                        ady = ady.getSigAdyacente();
                    }
                }

            }

        }

        return mejorCamino;
    }

    public Lista backupMinimoMaxEtiqueta(Object origen, Object destino) {
        ColaPrioridad cola = new ColaPrioridad();
        Lista lista = new Lista();
        Lista mejorCamino = null;

        NodoVert verticeOrigen = ubicarVertice(origen);

        lista.insertar(verticeOrigen.getElem(), 1);
        cola.insertar(new Object[] { verticeOrigen, Integer.MAX_VALUE, lista }, Integer.MAX_VALUE);

        int minTotal = Integer.MAX_VALUE;
        int minActual;

        NodoAdy ady;

        Object[] frente;

        NodoVert nodoActual;
        Lista caminoActual;
        Lista nuevoCamino;

        int nuevoMin;

        while (!cola.esVacia()) {
            frente = (Object[]) cola.recuperarFrente();
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

                    if (caminoActual.localizar(ady.getVertice().getElem()) < 0) {
                        nuevoCamino = caminoActual.clone();
                        nuevoCamino.insertar(ady.getVertice().getElem(), nuevoCamino.longitud() + 1);
                        cola.insertar(new Object[] { ady.getVertice(), nuevoMin, nuevoCamino }, nuevoMin);

                    }

                    ady = ady.getSigAdyacente();
                }

            }

        }
        return mejorCamino;
    }

    public Lista obtenerCaminoMasCorto(Object origen, Object destino) {

        Lista camino = new Lista();
        Cola cola;
        Diccionario padres;

        NodoVert nodoOrigen = ubicarVertice(origen);

        NodoVert verticeActual;
        NodoAdy ady;
        boolean encontrado;
        NodoVert verticeAdy;

        if (nodoOrigen != null) {

            cola = new Cola();
            padres = new Diccionario();

            encontrado = false;

            padres.insertar(new Object[] { origen, null });
            cola.poner(nodoOrigen);

            while (!cola.esVacia() && !encontrado) {

                verticeActual = (NodoVert) cola.obtenerFrente();
                cola.sacar();

                if (verticeActual.getElem().equals(destino)) {
                    encontrado = true;
                    crearCamino(camino, padres, destino);
                } else {
                    ady = verticeActual.getPrimerAdy();
                    while (ady != null) {
                        verticeAdy = ady.getVertice();
                        if (!padres.existeClave(verticeAdy.getElem())) {
                            padres.insertar(new Object[] { verticeAdy.getElem(), verticeActual.getElem() });
                            cola.poner(verticeAdy);
                        }
                        ady = ady.getSigAdyacente();
                    }
                }

            }

        }

        return camino;

    }

    private void crearCamino(Lista camino, Diccionario padres, Object elem) {

        if (elem != null) {
            camino.insertar(elem, 1);
            crearCamino(camino, padres, padres.obtenerDato(elem));
        }

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