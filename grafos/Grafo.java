

public class Grafo {
    private NodoVer inicio;

    public Grafo() {
        this.inicio = null;
    }

    public boolean insertarVertice(Object elem) {
        boolean hecho = false;
        boolean encontrado = (ubicarVertice(elem) != null);
        if (!encontrado) {
            NodoVer nuevo = new NodoVer(elem, this.inicio, null);
            this.inicio = nuevo;
        }
        return hecho;
    }

    public boolean insertarArco(Object origen, Object destino, Object etiqueta) {
        boolean hecho = false;
        NodoVer origenVer = ubicarVertice(origen);
        NodoVer destinoVer = ubicarVertice(destino);
        boolean encontrado = ubicarArcoDirigido(origenVer, destino);
        if (origenVer != null && destinoVer != null && !encontrado) {
            NodoAdy nuevo = new NodoAdy(destinoVer, origenVer.getPrimerAdy(), etiqueta);
            origenVer.setPrimerAdy(nuevo);
        }
        return hecho;
    }

    public boolean existeArco(Object origen, Object destino) {
        boolean encontrado = false;
        NodoVer origenVer = ubicarVertice(origen);
        if (origenVer != null) {
            encontrado = ubicarArcoDirigido(origenVer, destino);
        }
        return encontrado;
    }

    private boolean ubicarArcoDirigido(NodoVer origenVer, Object destino) {
        boolean encontrado = false;
        NodoAdy aux = origenVer.getPrimerAdy();
        while (aux != null) {
            if (aux.getVertice().getElem().equals(destino)) {
                encontrado = true;
            } else {
                aux = aux.getSigAdyacente();
            }
        }
        return encontrado;
    }

    private NodoVer ubicarVertice(Object elem) {
        NodoVer encontrado = null;
        NodoVer aux = this.inicio;
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
        NodoVer actual = this.inicio;
        boolean eliminado = false;
        if (actual != null && actual.getElem().equals(elem)) {
            this.inicio = actual.getSigVertice();
        } else {
            NodoVer ant = actual;
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

    private boolean eliminarArcoAdyacentes(NodoVer n, Object elem) {
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
        NodoVer origenVer = ubicarVertice(origen);
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

        NodoVer verticeAux = this.inicio;
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