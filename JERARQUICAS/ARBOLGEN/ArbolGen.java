package JERARQUICAS.ARBOLGEN;

import JERARQUICAS.NODOGEN.NodoGen;
import lineales.dinamicas.Cola;
import lineales.dinamicas.Lista;

public class ArbolGen {
    private NodoGen raiz;

    public ArbolGen() {
        this.raiz = null;
    }

    public boolean insertar(Object elemNuevo, Object elemPadre) {

        boolean insertado = false;

        NodoGen nodoNuevo = new NodoGen(elemNuevo, null, null);
        if (this.raiz == null) {
            this.raiz = nodoNuevo;
            insertado = true;
        } else {
            insertado = insertarRecursivo(raiz, elemNuevo, elemPadre);
        }

        return insertado;

    }

    private boolean insertarRecursivo(NodoGen nodoActual, Object elem, Object elemPadre) {

        boolean insertado = false;
        NodoGen nodoNuevo;
        if (nodoActual != null) {
            NodoGen aux = nodoActual.getHijoIzquierdo();
            if (nodoActual.getElem().equals(elemPadre)) {
                nodoNuevo = new NodoGen(elem, null, null);
                nodoActual.setHijoIzquierdo(nodoNuevo);
                nodoNuevo.setHermanoDerecho(aux);
                insertado = true;
            }
            while (aux != null && !insertado) {
                insertado = insertarRecursivo(aux, elem, elemPadre);
                aux = aux.getHermanoDerecho();
            }

        }
        return insertado;

    }

    public String toString() {
        String arbolGenString = recorridoString(this.raiz);
        return arbolGenString;
    }

    private String recorridoString(NodoGen nodoActual) {
        String arbolGenString = "\n";
        if (nodoActual != null) {
            arbolGenString += nodoActual.getElem().toString() + "->";
            NodoGen nodoAuxHijo = nodoActual.getHijoIzquierdo();
            while (nodoAuxHijo != null) {
                arbolGenString += nodoAuxHijo.getElem().toString();
                nodoAuxHijo = nodoAuxHijo.getHermanoDerecho();
                if (nodoAuxHijo != null) {
                    arbolGenString += ",";
                }
            }
            nodoAuxHijo = nodoActual.getHijoIzquierdo();
            while (nodoAuxHijo != null) {
                arbolGenString += recorridoString(nodoAuxHijo);
                nodoAuxHijo = nodoAuxHijo.getHermanoDerecho();
            }
        }
        return arbolGenString;

    }

    public boolean insertarPorPosicion(Object elemNuevo, int posPadre) {
        int[] pos = { 1 };
        boolean insertado;
        NodoGen nodoNuevo = new NodoGen(elemNuevo, null, null);
        if (this.raiz == null) {
            this.raiz = nodoNuevo;
            insertado = true;
        } else {
            insertado = insertarPosRec(elemNuevo, posPadre, pos, this.raiz);
        }

        return insertado;
    }

    private boolean insertarPosRec(Object elemNuevo, int posPadre, int[] pos, NodoGen n) {
        boolean hecho = false;
        if (n != null) {
            NodoGen nodoNuevo = new NodoGen(elemNuevo, null, null);
            if (pos[0] == posPadre) {
                nodoNuevo.setHermanoDerecho(n.getHijoIzquierdo());
                n.setHijoIzquierdo(nodoNuevo);
                hecho = true;
            }
            if (!hecho) {
                NodoGen hijo = n.getHijoIzquierdo();
                pos[0]++;
                while (!hecho && hijo != null) {
                    hecho = insertarPosRec(elemNuevo, posPadre, pos, hijo);
                    hijo = hijo.getHermanoDerecho();
                }
            }

        }
        return hecho;
    }

    public boolean pertenece(Object elem) {
        boolean pertenece = perteneceRec(this.raiz, elem);
        return pertenece;
    }

    private boolean perteneceRec(NodoGen n, Object elem) {
        boolean pertenece = false;
        if (n != null) {
            if (n.getElem().equals(elem)) {
                pertenece = true;
            }
            if (!pertenece) {
                NodoGen hijo = n.getHijoIzquierdo();
                while (hijo != null) {
                    perteneceRec(hijo, elem);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
        return pertenece;
    }

    public Lista ancestros(Object elem) {
        Lista ancestros = new Lista();
        int[] largoLis = { 0 };
        recorrerAncestros(ancestros, this.raiz, elem, largoLis);
        return ancestros;
    }

    private boolean recorrerAncestros(Lista ancestros, NodoGen n, Object elem, int[] largoLis) {
        boolean encontrado = false;
        if (n != null) {
            if (n.getElem().equals(elem)) {
                encontrado = true;
            }
            if (!encontrado) {
                largoLis[0]++;
                ancestros.insertar(n.getElem(), largoLis[0]);
                NodoGen hijo = n.getHijoIzquierdo();
                while (hijo != null && !encontrado) {
                    encontrado = recorrerAncestros(ancestros, hijo, elem, largoLis);
                    hijo = hijo.getHermanoDerecho();
                }
                if (!encontrado) {
                    ancestros.eliminar(largoLis[0]);
                    largoLis[0]--;
                }
            }
        }
        return encontrado;
    }

    public boolean esVacio() {
        boolean vacio = false;
        if (this.raiz == null) {
            vacio = true;
        }
        return vacio;
    }

    public void vaciar() {
        this.raiz = null;
    }

    public int altura() {
        int altura = recAltura(this.raiz, -1);
        return altura;

    }

    private int recAltura(NodoGen n, int altura) {
        if (n != null) {
            NodoGen hijo = n.getHijoIzquierdo();
            altura++;
            int alturaMayor = altura;
            while (hijo != null) {
                int alturaLado = recAltura(hijo, altura);
                hijo = hijo.getHermanoDerecho();
                if (alturaMayor < alturaLado) {
                    alturaMayor = alturaLado;
                }
            }
            if (altura < alturaMayor) {
                altura = alturaMayor;
            }
        }
        return altura;
    }

    public int nivel(Object elem) {
        int nivel = recNivel(this.raiz, elem, 0);
        return nivel;
    }

    private int recNivel(NodoGen n, Object elem, int nivel) {
        int nivelElem = -1;
        if (n != null) {
            if (n.getElem().equals(elem)) {
                nivelElem = nivel;
            } else {
                NodoGen hijo = n.getHijoIzquierdo();
                nivel++;
                while (hijo != null && nivelElem == -1) {
                    nivelElem = recNivel(hijo, elem, nivel);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
        return nivelElem;
    }

    public Object padre(Object elem) {
        Object padre = padreRec(this.raiz, elem);
        return padre;
    }

    private Object padreRec(NodoGen n, Object elem) {
        Object padre = null;
        if (n != null) {
            NodoGen hijo = n.getHijoIzquierdo();
            while (hijo != null && padre == null) {
                if (hijo.getElem().equals(elem)) {
                    padre = n.getElem();
                } else {
                    padre = padreRec(hijo, elem);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
        return padre;
    }

    public Lista listarPreorden() {
        Lista preOrden = new Lista();
        int[] largoLis = { 0 };
        recListarPre(preOrden, this.raiz, largoLis);
        return preOrden;
    }

    private void recListarPre(Lista preOrden, NodoGen n, int[] largoLis) {
        if (n != null) {
            largoLis[0]++;
            preOrden.insertar(n.getElem(), largoLis[0]);
            NodoGen hijo = n.getHijoIzquierdo();
            while (hijo != null) {
                recListarPre(preOrden, hijo, largoLis);
                hijo = hijo.getHermanoDerecho();
            }
        }
    }

    public Lista listarPosorden() {
        Lista posOrden = new Lista();
        int[] largoLis = { 0 };
        recListarPosorden(posOrden, this.raiz, largoLis);
        return posOrden;
    }

    private void recListarPosorden(Lista posOrden, NodoGen n, int[] largoLis) {
        if (n != null) {
            NodoGen hijo = n.getHijoIzquierdo();
            while (hijo != null) {
                recListarPosorden(posOrden, hijo, largoLis);
                hijo = hijo.getHermanoDerecho();
            }
            largoLis[0]++;
            posOrden.insertar(n.getElem(), largoLis[0]);
        }
    }

    public Lista listarInorden() {
        Lista inOrden = new Lista();
        int[] largoLis = { 0 };
        recListarInorden(inOrden, this.raiz, largoLis);
        return inOrden;
    }

    private void recListarInorden(Lista inOrden, NodoGen n, int[] largoLis) {
        if (n != null) {
            NodoGen hijo = n.getHijoIzquierdo();
            recListarInorden(inOrden, hijo, largoLis);
            largoLis[0]++;
            inOrden.insertar(n.getElem(), largoLis[0]);
            while (hijo != null) {
                hijo = hijo.getHermanoDerecho();
                recListarInorden(inOrden, hijo, largoLis);
            }
        }
    }

    public ArbolGen clone() {
        ArbolGen clon = new ArbolGen();
        clon.raiz = cloneAux(this.raiz);
        return clon;
    }

    public NodoGen cloneAux(NodoGen n) {
        NodoGen nuevo = null;
        if (n != null) {
            nuevo = new NodoGen(n.getElem(), cloneAux(n.getHijoIzquierdo()), n.getHermanoDerecho());
        }
        return nuevo;

    }

    public Lista listarNiveles() {
        Lista lista = new Lista();
        Cola cola = new Cola();
        NodoGen nodoAux;

        cola.poner(this.raiz);
        int i = 0;
        while (!cola.esVacia()) {
            System.out.println(i);
            i++;
            nodoAux = (NodoGen) cola.obtenerFrente();

            cola.sacar();
            lista.insertar(nodoAux.getElem(), lista.longitud() + 1);

            NodoGen hijo = nodoAux.getHijoIzquierdo();

            while (hijo != null) {
                cola.poner(hijo);
                hijo = hijo.getHermanoDerecho();
            }

        }

        return lista;
    }

    public boolean verificarCamino(Lista camino) {
        int longi = camino.longitud();
        boolean esUnCamino = verificarCaminoRec(this.raiz, camino, 1, longi);
        return esUnCamino;
    }

    private boolean verificarCaminoRec(NodoGen n, Lista camino, int i, int longi) {
        boolean esUnCamino = false;
        if (n != null) {
            if (camino.recuperar(i).equals(n.getElem())) {
                NodoGen hijo = n.getHijoIzquierdo();
                if (i == longi) {
                    esUnCamino = true;
                }
                i++;
                while (!esUnCamino && hijo != null) {
                    esUnCamino = verificarCaminoRec(hijo, camino, i, longi);
                    hijo = hijo.getHermanoDerecho();
                }
            }

        }
        return esUnCamino;
    }

    public Lista listarEntreNiveles(int niv1, int niv2) {
        Lista listaNiveles = new Lista();
        // Cola colaAux = new Cola();
        // primer nivel de un arbol es 0
        int nivelActual = 0;
        int nivAux = niv2;
        if (niv1 > niv2) {
            niv2 = niv1;
            niv1 = nivAux;
        }

        listarEntreNivelesRec(niv1, niv2, this.raiz, listaNiveles, 0);

        return listaNiveles;
    }

    private void listarEntreNivelesRec(int niv1, int niv2, NodoGen n, Lista listaNiveles, int i) {
        if (n != null && i <= niv2) {
            if (i >= niv1) {
                listaNiveles.insertar(n.getElem(), listaNiveles.longitud() + 1);
            }
            NodoGen hijo = n.getHijoIzquierdo();
            i++;
            while (hijo != null) {
                listarEntreNivelesRec(niv1, niv2, hijo, listaNiveles, i);
                hijo = hijo.getHermanoDerecho();
            }

        }
    }
    /*
     * public Lista listarEntreNiveles(int niv1, int niv2) {
     * Lista listaNiveles = new Lista();
     * //Cola colaAux = new Cola();
     * // primer nivel de un arbol es 0
     * int nivelActual = 0;
     * int nivAux = niv2;
     * if (niv1 > niv2) {
     * niv2 = niv1;
     * niv1 = nivAux;
     * }
     * if (niv1==0) {
     * listaNiveles.insertar(this.raiz.getElem(), listaNiveles.longitud()+1);
     * }
     * listarEntreNivelesRec(niv1, niv2, this.raiz, listaNiveles, 0);
     * 
     * 
     * 
     * return listaNiveles;
     * }
     * 
     * private void listarEntreNivelesRec(int niv1, int niv2, NodoGen n, Lista
     * listaNiveles, int i) {
     * if (n != null) {
     * NodoGen hijo = n.getHijoIzquierdo();
     * if (hijo!=null) {
     * i++;
     * }
     * 
     * while (hijo != null && i<=niv2) {
     * if (i>=niv1 && i<=niv2) {
     * listaNiveles.insertar(hijo.getElem(), listaNiveles.longitud()+1);
     * }
     * listarEntreNivelesRec(niv1, niv2, hijo, listaNiveles, i);
     * hijo = hijo.getHermanoDerecho();
     * }
     * 
     * }
     * }
     * /*
     * 
     * 
     *
     * 
     * EL EJEMPLO (pensar otros casos)
     * Implemente el método eliminar que permita quitar un elemento del árbol, junto
     * a todos sus descendientes.
     * 
     */

    public boolean eliminar(Object elem) {
        boolean eliminado = eliminarRec(elem, this.raiz);
        return eliminado;
    }

    private boolean eliminarRec(Object elem, NodoGen n) {
        boolean eliminado = false;
        if (n != null) {
            NodoGen hijo = n.getHijoIzquierdo();
            if (hijo != null && hijo.getElem().equals(elem)) {
                n.setHijoIzquierdo(hijo.getHermanoDerecho());
                eliminado = true;
            }
            if (!eliminado && n.getHermanoDerecho() != null && n.getHermanoDerecho().equals(elem)) {
                n.setHermanoDerecho(n.getHermanoDerecho().getHermanoDerecho());
                eliminado = true;
            }
            while (hijo != null && !eliminado) {
                eliminado = eliminarRec(elem, hijo);
                hijo = hijo.getHermanoDerecho();
            }
        }
        return eliminado;
    }

    /*
     * EJERCICIO 1
     * En el TDA árbol genérico, implementar la operación listarHastaNivel(niv) que
     * recibe por parámetro el
     * nivel niv y devuelve una lista con todos los elementos de los nodos que se
     * encuentran entre los niveles [0,
     * niv]. El método debe recorrer el árbol en preorden y sólo visitar los nodos a
     * listar, es decir que no debe
     * recorrer nodos de más. Utilizar la representación dinámica de árbol genérico
     * HEI-HD
     */
    public Lista listarHastaNivel(int niv) {
        Lista lista = new Lista();
        if (niv >= 0) {
            listarHastaNivelRec(niv, this.raiz, 0, lista);
        }
        return lista;
    }

    private void listarHastaNivelRec(int niv, NodoGen n, int i, Lista lista) {
        if (n != null && i <= niv) {
            lista.insertar(n.getElem(), lista.longitud() + 1);
            i++;
            NodoGen hijo = n.getHijoIzquierdo();
            while (hijo != null && i <= niv) {
                listarHastaNivelRec(niv, hijo, i, lista);
                hijo = hijo.getHermanoDerecho();
            }
        }
    }

    public boolean jerarquizar(Object elem) {
        boolean hecho = false;
        hecho = jerarquizarRec(elem, this.raiz);
        return hecho;
    }

    private boolean jerarquizarRec(Object elem, NodoGen n) {
        boolean hecho = false;
        if (n != null) {
            NodoGen hijo = n.getHijoIzquierdo();
            NodoGen nHermadoDerecho = n.getHermanoDerecho();
            NodoGen hijoAnterior = null;
            System.out.println(n.getElem());
            if (n != this.raiz && hijo != null && hijo.getElem().equals(elem)) {
                n.setHijoIzquierdo(hijo.getHijoIzquierdo());
                hijo.setHermanoDerecho(nHermadoDerecho);
                n.setHermanoDerecho(hijo);
                hecho = true;
            } else {
                while (hijo != null && !hecho) {
                    hijoAnterior = hijo;
                    hijo = hijo.getHermanoDerecho();
                    if (n != this.raiz && !hecho && hijo != null && hijo.getElem().equals(elem)) {
                        hijoAnterior.setHermanoDerecho(hijo.getHermanoDerecho());
                        hijo.setHermanoDerecho(nHermadoDerecho);
                        n.setHermanoDerecho(hijo);
                        hecho = true;
                    }
                    if (!hecho) {
                        hecho = jerarquizarRec(elem, hijoAnterior);
                    }

                }
            }

        }
        return hecho;
    }

}
