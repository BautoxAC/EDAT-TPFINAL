package JERARQUICAS.ARBOLBIN;

import JERARQUICAS.NODOARBOL.NodoArbol;
import lineales.dinamicas.Cola;
import lineales.dinamicas.Lista;

/* mostrar la posición en preorden del cada elemento en el árbol.
Mostrar la posición en preorden de un elemento x en el arbol
Mostrar la posición en preorden de todos los elementos vistados hasta encontrar el elemento x en el arbol */
public class ArbolBin {
    private NodoArbol raiz;

    public ArbolBin() {
        this.raiz = null;
    }

    public boolean insertar(Object elemNuevo, Object elemPadre, char posHijo) {
        boolean hecho = true;
        if (this.raiz == null) {
            this.raiz = new NodoArbol(elemNuevo, raiz, raiz);
        } else {
            NodoArbol nPadre = obtenerNodo(this.raiz, elemPadre);
            if (nPadre != null) {
                if (nPadre.getIzquierdo() == null && posHijo == 'I') {
                    nPadre.setIzquierdo(new NodoArbol(elemNuevo, null, null));
                } else if (nPadre.getDerecho() == null && posHijo == 'D') {
                    nPadre.setDerecho(new NodoArbol(elemNuevo, null, null));
                } else {
                    hecho = false;
                }
            } else {
                hecho = false;
            }
        }

        return hecho;
    }

    private NodoArbol obtenerNodo(NodoArbol n, Object buscado) {
        NodoArbol buscadoEncontrado = null;
        if (n != null) {
            if (n.getElem().equals(buscado)) {
                buscadoEncontrado = n;
            } else {
                buscadoEncontrado = obtenerNodo(n.getIzquierdo(), buscado);
                if (buscadoEncontrado == null) {
                    buscadoEncontrado = obtenerNodo(n.getDerecho(), buscado);
                }
            }
        }

        return buscadoEncontrado;
    }

    public Lista listarPreorden() {
        Lista arbolPreOrden = new Lista();
        recorrerPreOrden(arbolPreOrden, this.raiz);
        return arbolPreOrden;
    }

    private void recorrerPreOrden(Lista lista, NodoArbol padre) {
        if (padre != null) {
            lista.insertar(padre.getElem(), lista.longitud() + 1);
            recorrerPreOrden(lista, padre.getIzquierdo());
            recorrerPreOrden(lista, padre.getDerecho());
        }
    }

    public Lista listarInorden() {
        Lista arbolInOrden = new Lista();
        recorrerInOrden(arbolInOrden, this.raiz);
        return arbolInOrden;
    }

    private void recorrerInOrden(Lista lista, NodoArbol padre) {
        if (padre != null) {
            recorrerInOrden(lista, padre.getIzquierdo());
            lista.insertar(padre.getElem(), lista.longitud() + 1);
            recorrerInOrden(lista, padre.getDerecho());
        }
    }

    public Lista listarPosorden() {
        Lista arbolPosOrden = new Lista();
        recorrerPosorden(arbolPosOrden, this.raiz);
        return arbolPosOrden;
    }

    private void recorrerPosorden(Lista lista, NodoArbol padre) {
        if (padre != null) {
            recorrerPosorden(lista, padre.getIzquierdo());
            recorrerPosorden(lista, padre.getDerecho());
            lista.insertar(padre.getElem(), lista.longitud() + 1);
        }
    }

    public boolean insertarPorPosicion(Object elemNuevo, int posPadre, char posHijo) {
        boolean hecho = true;
        if (this.raiz == null) {
            this.raiz = new NodoArbol(elemNuevo, raiz, raiz);
        } else {
            int[] arrPos = { 1 };
            NodoArbol nPadre = obtenerNodoPos(posPadre, arrPos, raiz);
            if (nPadre != null) {
                if (nPadre.getIzquierdo() == null && posHijo == 'I') {
                    nPadre.setIzquierdo(new NodoArbol(elemNuevo, null, null));
                } else if (nPadre.getDerecho() == null && posHijo == 'D') {
                    nPadre.setDerecho(new NodoArbol(elemNuevo, null, null));
                } else {
                    hecho = false;
                }
            } else {
                hecho = false;
            }
        }

        return hecho;
    }

    private NodoArbol obtenerNodoPos(int pos, int[] posActual, NodoArbol nodoPasado) {

        NodoArbol nodoEncontrado = null;

        if (nodoPasado != null) {
            if (pos == posActual[0]) {
                nodoEncontrado = nodoPasado;
            } else {
                posActual[0]++;
                nodoEncontrado = obtenerNodoPos(pos, posActual, nodoPasado.getIzquierdo());
                if (nodoEncontrado == null) {
                    nodoEncontrado = obtenerNodoPos(pos, posActual, nodoPasado.getDerecho());
                }
            }
        }
        return nodoEncontrado;
    }

    public Object padre(Object ele) {
        Object padre = obtenerPadre(this.raiz, ele);
        return padre;
    }

    private Object obtenerPadre(NodoArbol n, Object buscado) {
        Object padreEncontrado = null;
        if (n != null) {
            if ((n.getIzquierdo() != null && n.getIzquierdo().getElem().equals(buscado))
                    || (n.getDerecho() != null && n.getDerecho().getElem().equals(buscado))) {
                padreEncontrado = n.getElem();
            } else {
                padreEncontrado = obtenerPadre(n.getIzquierdo(), buscado);
                if (padreEncontrado == null) {
                    padreEncontrado = obtenerPadre(n.getDerecho(), buscado);
                }
            }
        }
        return padreEncontrado;
    }

    public String toString() {
        String ArbolPreOrden = this.listarPreorden().toString();
        return ArbolPreOrden;
    }

    public int altura() {
        int altura = -1;
        altura = calcularAltura(this.raiz, altura);
        return altura;
    }

    public boolean esVacio() {
        boolean esVacia = true;
        if (this.raiz == null) {
            esVacia = false;
        }
        return esVacia;
    }

    public int nivel(Object elemento) {
        int nivel = -1;
        nivel = calcularNivelNodo(elemento, nivel, this.raiz);
        return nivel;
    }

    private int calcularNivelNodo(Object elemento, int nivel, NodoArbol nodoPasado) {
        int nivelReal = nivel;
        int nivelEncontrado = -1;
        if (nodoPasado != null) {
            nivelReal++;
            if (nodoPasado.getElem().equals(elemento)) {
                nivelEncontrado = nivelReal;
            } else {
                nivelEncontrado = calcularNivelNodo(elemento, nivelReal, nodoPasado.getIzquierdo());
                if (nivelEncontrado == -1) {
                    nivelEncontrado = calcularNivelNodo(elemento, nivelReal, nodoPasado.getDerecho());
                }
            }
        }
        return nivelEncontrado;
    }

    private int calcularAltura(NodoArbol padre, int alturaActual) {
        int izq, der, alturaReal;
        alturaReal = alturaActual;
        if (padre != null) {
            alturaReal++;
            izq = calcularAltura(padre.getIzquierdo(), alturaReal);
            der = calcularAltura(padre.getDerecho(), alturaReal);
            if (izq > alturaReal) {
                alturaReal = izq;
            }
            if (der > alturaReal) {
                alturaReal = der;
            }
        }
        return alturaReal;
    }

    public void vaciar() {
        this.raiz = null;
    }

    public Lista listarNiveles() {
        Lista arbolNivel = new Lista();
        Cola cola = new Cola();
        int contador = 1;
        NodoArbol nodoActual;
        cola.poner(this.raiz);
        while (!cola.esVacia()) {
            nodoActual = (NodoArbol) cola.obtenerFrente();
            arbolNivel.insertar(nodoActual.getElem(), contador);
            contador++;
            cola.sacar();
            if (nodoActual.getIzquierdo() != null) {
                cola.poner(nodoActual.getIzquierdo());
            }
            if (nodoActual.getDerecho() != null) {
                cola.poner(nodoActual.getDerecho());
            }
        }
        return arbolNivel;
    }

    public ArbolBin clone() {
        ArbolBin clon = new ArbolBin();
        clon.raiz = clon.recorrerClon(this.raiz);
        return clon;
    }

    public NodoArbol recorrerClon(NodoArbol nodoActual) {
        NodoArbol nodo = null;
        if (nodoActual != null) {
            nodo = new NodoArbol(nodoActual.getElem(), recorrerClon(nodoActual.getIzquierdo()), recorrerClon(nodoActual.getDerecho()));
        }
        return nodo;
    }

    public Lista frontera() {
        Lista listaFron = new Lista();
        recorrerHojas(listaFron, this.raiz);
        return listaFron;
    }

    private void recorrerHojas(Lista listaFron, NodoArbol n) {
        if (n != null) {
            if (n.getIzquierdo() == null &&
                    n.getDerecho() == null) {
                listaFron.insertar(n.getElem(), listaFron.longitud() + 1);
            } else {
                recorrerHojas(listaFron, n.getIzquierdo());
                recorrerHojas(listaFron, n.getDerecho());
            }
        }
    }

    /*
     * e) Implementar la operación boolean verificarPatron(Lista patron), que recibe
     * por parámetro una lista patron
     * y determine si coincide exactamente con al menos un camino del árbol que
     * comience en la raíz y termine en
     * una hoja. El método debe ser eficiente, es decir, recorrer el árbol lo
     * estrictamente necesario.
     */
    public boolean verificarPatron(Lista patron) {
        int[] contador = { 1 };
        boolean coincide = recorrerVerificar(patron, this.raiz, contador);
        return coincide;
    }

    private boolean recorrerVerificar(Lista patron, NodoArbol n, int[] contador) {
        boolean coincide = false;
        if (n != null) {
            if (n.getElem().equals(patron.recuperar(contador[0]))) {
                contador[0]++;
                coincide = recorrerVerificar(patron, n.getIzquierdo(), contador);
                if (!coincide) {
                    coincide = recorrerVerificar(patron, n.getDerecho(), contador);
                }
            }
        } else {
            if (contador[0] == patron.longitud()) {
                coincide = true;
            }
        }
        return coincide;
    }

    public boolean estaRepetido(Object elem) {
        int[] cont = { 0 };
        boolean repetido = recorrerRep(this.raiz, cont, elem);
        return repetido;
    }

    private boolean recorrerRep(NodoArbol n, int[] cont, Object elem) {
        boolean repetido = false;
        if (n != null) {
            if (n.getElem().equals(elem)) {
                cont[0]++;
                if (cont[0] == 2) {
                    repetido = true;
                }
            }
            if (!repetido) {
                repetido = recorrerRep(n.getIzquierdo(), cont, elem);
                if (!repetido) {
                    repetido = recorrerRep(n.getDerecho(), cont, elem);
                }
            }
        }
        return repetido;
    }

  public boolean equals(ArbolBin otro) {

        return sonIguales(this.raiz,otro.raiz);

    }

    private boolean sonIguales(NodoArbol nodoActual, NodoArbol otroActual) {

        boolean igual = true;

        if (nodoActual != null) {
            if (otroActual == null) {
                igual = false;
            } else {
                if (nodoActual.getElem() != otroActual.getElem()) {
                    igual = false;
                } else {
                    igual = sonIguales(nodoActual.getIzquierdo(), otroActual.getIzquierdo());
                    if (igual) {
                        igual = sonIguales(nodoActual.getDerecho(), otroActual.getDerecho());
                    }
                }
            }

        } else if (nodoActual == null && otroActual != null) {
            igual = false;
        }

        return igual;

    }


}