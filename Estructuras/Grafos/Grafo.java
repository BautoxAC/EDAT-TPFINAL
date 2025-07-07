package Estructuras.Grafos;

import lineales.dinamicas.*;

public class Grafo {

    private NodoVert inicio;

    public Grafo() {

        this.inicio = null;

    }

    public boolean insertarVertice(Object vertice) {

        boolean exito = false;

        NodoVert aux = this.ubicarVertice(vertice);

        if (aux == null) {

            this.inicio = new NodoVert(vertice, this.inicio);
            exito = true;

        }

        return exito;

    }

    private NodoVert ubicarVertice(Object buscado) {

        NodoVert aux = this.inicio;

        while (aux != null && !aux.getElem().equals(buscado)) {

            aux = aux.getSigVertice();

        }

        return aux;

    }

    public boolean eliminarVertice(Object vertice) {

        boolean exito = false;

        NodoVert aux = this.ubicarVertice(vertice);

        if (aux != null) {

            // LOGICA DE BORRAR ACA, hay que borrar todos los arcos que apuntan a el

            eliminarArcosDeDestinoVertice(vertice);

            exito = true;

        }

        return exito;

    }

    private void eliminarArcosDeDestinoVertice(Object vertice) {

        NodoVert aux = this.inicio;
        NodoAdy nodoAdyacente = null;
        NodoAdy nodoAdyAnterior = null;

        while (aux != null) {

            nodoAdyacente = aux.getPrimerAdy();
            nodoAdyAnterior = nodoAdyacente;

            while (nodoAdyacente != null) {

                if (nodoAdyacente.getVertice().getElem().equals(vertice)) {

                    nodoAdyAnterior.setSigAdyacente(nodoAdyacente.getSigAdyacente());

                }

                nodoAdyAnterior = nodoAdyacente;
                nodoAdyacente = nodoAdyacente.getSigAdyacente();

            }

            aux = aux.getSigVertice();

        }

    }

    public boolean existeVertice(Object vertice) {

        return ubicarVertice(vertice) != null;

    }

    public boolean insertarArco(Object verticeOrigen, Object verticeDestino, Object etiqueta) {

        boolean exito = false;
        boolean encontrado = false;

        NodoVert verOrigen = ubicarVertice(verticeOrigen);
        NodoVert verDestino;

        if (verOrigen != null) {

            encontrado = (ubicarArco(verOrigen, verticeDestino) != null);

            if (!encontrado) {

                verDestino = ubicarVertice(verticeDestino);

                verOrigen.setPrimerAdy(new NodoAdy(verDestino, verOrigen.getPrimerAdy(), etiqueta));
                exito = true;

            }

        }

        return exito;

    }

    public boolean eliminarArco(Object verticeOrigen, Object veriticeDestino) {

        boolean exito = false;

        NodoVert vertice = ubicarVertice(verticeOrigen);
        NodoAdy nodoAux = null;

        if (vertice != null) {

            if (ubicarArco(vertice, veriticeDestino) != null) {

                nodoAux = vertice.getPrimerAdy();

                if (nodoAux.getVertice().getElem().equals(veriticeDestino)) {
                    vertice.setPrimerAdy(null);
                    exito = true;
                } else {

                    while (nodoAux != null && !exito) {

                        if (nodoAux.getSigAdyacente().getVertice().getElem().equals(veriticeDestino)) {
                            nodoAux.setSigAdyacente(nodoAux.getSigAdyacente().getSigAdyacente());
                            exito = true;
                        }

                    }

                }

            }

        }

        return exito;

    }

    private NodoAdy ubicarArco(Object origen, Object destino) {

        // Ubicar arco por objetos

        NodoVert verOrigen = ubicarVertice(origen);
        NodoAdy aux = null;

        if (verOrigen != null) {

            aux = verOrigen.getPrimerAdy();

            while (aux != null && !aux.getVertice().getElem().equals(destino)) {

                aux = aux.getSigAdyacente();

            }

        }

        return aux;

    }

    private NodoAdy ubicarArco(NodoVert nodoOrigen, Object destino) {

        // Ubicar arco por nodo por parametro

        NodoVert verOrigen = nodoOrigen;
        NodoAdy aux = null;

        if (verOrigen != null) {

            aux = verOrigen.getPrimerAdy();

            while (aux != null && !aux.getVertice().getElem().equals(destino)) {

                aux = aux.getSigAdyacente();

            }

        }

        return aux;

    }

    public boolean existeArco(Object origen, Object destino) {

        return ubicarArco(origen, destino) != null;

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

    public void vaciar() {
        this.inicio = null;
    }

    public boolean esVacio() {
        return this.inicio == null;
    }

    public Grafo clone() {

        Grafo clon = new Grafo();

        clon.inicio = clonarVert(this.inicio);

        return clon;

    }

    public NodoVert clonarVert(NodoVert nodoActual) {

        NodoVert nodoVertice = null;

        if (nodoActual != null) {

            nodoVertice = new NodoVert(nodoActual.getElem(), clonarVert(nodoActual.getSigVertice()));
            nodoVertice.setPrimerAdy(clonarAdy(nodoActual.getPrimerAdy(), nodoVertice));

        }

        return nodoVertice;

    }

    public NodoAdy clonarAdy(NodoAdy nodoActual, NodoVert nodoVertice) {

        NodoAdy nodo = null;

        if (nodoActual != null) {

            nodo = new NodoAdy(nodoVertice, clonarAdy(nodoActual.getSigAdyacente(), nodoVertice), nodoActual.getEtiqueta());

        }

        return nodo;

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

}
