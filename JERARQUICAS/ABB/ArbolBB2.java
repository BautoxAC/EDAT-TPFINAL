package JERARQUICAS.ABB;



import lineales.dinamicas.*;

public class ArbolBB2 {

    private NodoABB raiz;

    public ArbolBB2() {
        this.raiz = null;
    }

    public boolean insertar(Object elem) {

        boolean exito = true;

        if (this.raiz == null) {
            this.raiz = new NodoABB(elem);
        } else {
            exito = insertarAux(this.raiz, (Comparable) elem);
        }

        return exito;

    }

    private boolean insertarAux(NodoABB nodoActual, Comparable elem) {

        boolean exito = true;

        int comparacion = elem.compareTo(nodoActual.getElem());

        if (comparacion == 0) {

            // si lo encuentra entonces no se puede insertar

            exito = false;

        } else if (comparacion < 0) {

            // si la comparacion da menor entonces se verifica el hijo izquierdo, si no es
            // nulo sigue buscando
            // y si lo es entonces el hijo izquierdo del nodo sera un nuevo nodo con el
            // elemento

            if (nodoActual.getIzquierdo() != null) {
                exito = insertarAux(nodoActual.getIzquierdo(), elem);
            } else {
                exito = true;
                nodoActual.setIzquierdo(new NodoABB(elem));
            }

        } else {

            // si la comparacion da mayor entonces se verifica el hijo derecho, si no es
            // nulo sigue buscando
            // y si lo es entonces el hijo derecho del nodo sera un nuevo nodo con el
            // elemento

            if (nodoActual.getDerecho() != null) {
                exito = insertarAux(nodoActual.getDerecho(), elem);
            } else {
                exito = true;
                nodoActual.setDerecho(new NodoABB(elem));
            }

        }

        return exito;

    }

    public boolean eliminar(Object elem) {

        boolean[] eliminado = { false };

        NodoABB buscarNodoEliminar = eliminarAux(this.raiz, (Comparable) elem, eliminado);

        // en el caso de que el metodo devuelva un nodo distinto a la raiz, quiere decir
        // que terminamos eliminado la raiz
        // por lo tanto, la nueva raiz sera el nodo devuelto

        if (buscarNodoEliminar != this.raiz) {
            this.raiz = buscarNodoEliminar;
        }

        return eliminado[0];

    }

    private NodoABB eliminarAux(NodoABB nodoActual, Comparable elem, boolean[] eliminado) {

        NodoABB nodoAux = null;
        int comparacion;
        Object elemMayor;

        // las variables que guardo son, nodoAux que me sirve para la recursividad
        // comparacion es para abreviar el compareTo
        // elemMayor para obtener el mayor valor en el caso de que tenga 2 hijos, es
        // decir, el mayor elemento
        // del subarbol izquierdo

        if (nodoActual != null) {

            comparacion = elem.compareTo(nodoActual.getElem());

            if (comparacion == 0) {

                if (nodoActual.getIzquierdo() == null && nodoActual.getDerecho() == null) {
                    nodoAux = null;
                    eliminado[0] = true;
                } else {
                    if (nodoActual.getIzquierdo() != null && nodoActual.getDerecho() != null) {

                        elemMayor = obtenerMayorElemIzq(nodoActual.getIzquierdo());

                        nodoActual.setIzquierdo(eliminarAux(nodoActual.getIzquierdo(), (Comparable) elemMayor, eliminado));

                        nodoActual.setElem(elemMayor);
                        nodoAux = nodoActual;

                        eliminado[0] = true;

                    } else {
                        if (nodoActual.getIzquierdo() != null) {
                            nodoAux = nodoActual.getIzquierdo();
                        } else {
                            nodoAux = nodoActual.getDerecho();
                        }
                        eliminado[0] = true;
                    }

                }

                // si la comparacion da que es menor, entonces se obtiene el nodoAux
                // recursivamente y se le inserta al nodoActual como HI
                // como izquierdo, pasaria lo contrario para el otro caso, si es mayor se
                // obtiene nodoAux y se inserta como HD

            } else if (comparacion < 0) {

                if (nodoActual.getIzquierdo() != null) {
                    nodoAux = eliminarAux(nodoActual.getIzquierdo(), elem, eliminado);
                    nodoActual.setIzquierdo(nodoAux);
                    nodoAux = nodoActual;
                }

            } else {

                if (nodoActual.getDerecho() != null) {
                    nodoAux = eliminarAux(nodoActual.getDerecho(), elem, eliminado);
                    nodoActual.setDerecho(nodoAux);
                    nodoAux = nodoActual;
                }

            }

        }

        return nodoAux;

    }

    private Object obtenerMayorElemIzq(NodoABB nodoActual) {

        Object mayor = null;

        if (nodoActual != null) {

            if (nodoActual.getDerecho() != null) {
                mayor = obtenerMayorElemIzq(nodoActual.getDerecho());
            } else {
                mayor = nodoActual.getElem();
            }

        }

        return mayor;

    }

    public boolean pertenece(Object elem) {

        return perteneceRecursivo(this.raiz, (Comparable) elem);

    }

    private boolean perteneceRecursivo(NodoABB nodoActual, Comparable elem) {

        boolean pertenece = false;

        int comparacion;

        // para todos los nodos verifico cuando coincida el elemento, si no lo hace, voy
        // al menor o al mayor
        // cuando corresponda

        if (nodoActual != null) {

            comparacion = elem.compareTo(nodoActual.getElem());

            if (comparacion == 0) {
                pertenece = true;
            } else {
                if (comparacion < 0) {
                    pertenece = perteneceRecursivo(nodoActual.getIzquierdo(), elem);
                } else {
                    pertenece = perteneceRecursivo(nodoActual.getDerecho(), elem);
                }

            }
        }

        return pertenece;

    }

    public boolean esVacio() {

        return this.raiz == null;

    }

    public Lista listar() {
        Lista nuevaLista = new Lista();

        insertarListaPreorden(this.raiz, nuevaLista);

        return nuevaLista;
    }

    private void insertarListaPreorden(NodoABB nodoActual, Lista lista) {
        if (nodoActual != null) {
            lista.insertar(nodoActual.getElem(), lista.longitud() + 1);
            insertarListaPreorden(nodoActual.getIzquierdo(), lista);
            insertarListaPreorden(nodoActual.getDerecho(), lista);
        }
    }

    public Lista listarRango(Object elemMinimo, Object elemMaximo) {

        Lista lista = new Lista();

        listarRangoRecursivo(this.raiz, elemMinimo, elemMaximo, lista);

        return lista;

    }

    private void listarRangoRecursivo(NodoABB nodoActual, Object elemMinimo, Object elemMaximo, Lista lista) {

        Comparable elem = null;
        int comparacionMin;
        int comparacionMax;
        
        if (nodoActual != null) {

            elem = (Comparable) nodoActual.getElem();
            comparacionMin = elem.compareTo(elemMinimo);
            comparacionMax = elem.compareTo(elemMaximo);

            if (comparacionMin > 0) {
                listarRangoRecursivo(nodoActual.getIzquierdo(), elemMinimo, elemMaximo, lista);
            }

            if (comparacionMin >= 0 && comparacionMax <= 0) {
                lista.insertar(nodoActual.getElem(), lista.longitud() + 1);
            }

            if (comparacionMax < 0) {
                listarRangoRecursivo(nodoActual.getDerecho(), elemMinimo, elemMaximo, lista);
            }

        }

    }

    public Object minimoElem() {

        return minimoRecursivo(this.raiz);

    }

    private Object minimoRecursivo(NodoABB nodoActual) {

        Object minimo = null;

        if (nodoActual != null) {

            if (nodoActual.getIzquierdo() == null) {
                minimo = nodoActual.getElem();
            } else {
                minimo = minimoRecursivo(nodoActual.getIzquierdo());
            }

        }

        return minimo;

    }

    public Object maximoElem() {

        return maximoRecursivo(this.raiz);

    }

    private Object maximoRecursivo(NodoABB nodoActual) {

        Object maximo = null;

        if (nodoActual != null) {

            if (nodoActual.getDerecho() == null) {
                maximo = nodoActual.getElem();
            } else {
                maximo = maximoRecursivo(nodoActual.getDerecho());
            }

        }

        return maximo;

    }

    public Lista listarNiveles() {
        Lista nuevaLista = new Lista();

        NodoABB nodoActual;
        int contador = 0;

        Cola cola = new Cola();
        cola.poner(this.raiz);
        while (!cola.esVacia()) {
            nodoActual = (NodoABB) cola.obtenerFrente();
            cola.sacar();
            contador++;
            nuevaLista.insertar(nodoActual.getElem(), contador);
            if (nodoActual.getIzquierdo() != null) {
                cola.poner(nodoActual.getIzquierdo());
            }
            if (nodoActual.getDerecho() != null) {
                cola.poner(nodoActual.getDerecho());
            }
        }

        return nuevaLista;
    }

    public void vaciar() {
        this.raiz = null;
    }

    public ArbolBB2 clone() {
        ArbolBB2 arbolClon = new ArbolBB2();

        arbolClon.raiz = clonarArbol(this.raiz);

        return arbolClon;
    }

    private NodoABB clonarArbol(NodoABB nodoActual) {
        NodoABB nodoAux = null;
        if (nodoActual != null) {
            nodoAux = new NodoABB(nodoActual.getElem(), clonarArbol(nodoActual.getIzquierdo()),
                    clonarArbol(nodoActual.getDerecho()));
        }
        return nodoAux;
    }

    public String toString() {

        return toStringAux(this.raiz);

    }

    private String toStringAux(NodoABB nodoActual) {
        String texto = "";

        if (nodoActual != null) {

            texto += nodoActual.getElem().toString() + " -> ";
            texto += "HI: ";

            if (nodoActual.getIzquierdo() != null) {
                texto += nodoActual.getIzquierdo().getElem();
            } else {
                texto += "N/A";
            }

            texto += " HD: ";

            if (nodoActual.getDerecho() != null) {
                texto += nodoActual.getDerecho().getElem();
            } else {
                texto += "N/A";
            }

            texto += "\n";

            texto += toStringAux(nodoActual.getIzquierdo());
            texto += toStringAux(nodoActual.getDerecho());

        }

        return texto;
    }

}