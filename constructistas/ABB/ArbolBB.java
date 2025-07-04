package constructistas.ABB;

import javax.smartcardio.CommandAPDU;

import JERARQUICAS.NODOARBOL.NodoArbol;
import constructistas.ABB.NodoABB;
import lineales.dinamicas.Lista;

public class ArbolBB {
    private NodoABB raiz;

    public ArbolBB() {
        this.raiz = null;
    }

    public boolean insertar(Comparable elem) {

        boolean exito = false;

        if (this.raiz == null) {
            this.raiz = new NodoABB(elem);
            exito = true;
        } else {
            exito = insertarRec(this.raiz, elem);
        }
        return exito;
    }

    private boolean insertarRec(NodoABB n, Comparable elem) {
        boolean exito = false;
        int comparar = elem.compareTo(n.getElem());
        if (n != null) {
            if (comparar != 0) {
                if (comparar < 0) {
                    if (n.getIzquierdo() != null) {
                        exito = insertarRec(n.getIzquierdo(), elem);
                    } else {
                        n.setIzquierdo(new NodoABB(elem));
                        exito = true;
                    }
                } else {
                    if (n.getDerecho() != null) {
                        exito = insertarRec(n.getDerecho(), elem);
                    } else {
                        n.setDerecho(new NodoABB(elem));
                        exito = true;
                    }
                }
            }
        }
        return exito;
    }

    public boolean vacio() {
        boolean esVacio = false;
        if (this.raiz == null) {
            esVacio = true;
        }
        return esVacio;
    }

    public Lista listar() {
        Lista arbolPreOrden = new Lista();
        recorrerPreOrden(arbolPreOrden, this.raiz);
        return arbolPreOrden;
    }

    private void recorrerPreOrden(Lista lista, NodoABB padre) {
        if (padre != null) {
            lista.insertar(padre.getElem(), lista.longitud() + 1);
            recorrerPreOrden(lista, padre.getIzquierdo());
            recorrerPreOrden(lista, padre.getDerecho());
        }
    }

    public boolean eliminar(Comparable elem) {
        boolean[] exito = { false };
        if (!this.vacio()) {
            this.raiz = eliminarRec(this.raiz, elem, exito);
        }
        return exito[0];
    }

    private NodoABB eliminarRec(NodoABB n, Comparable elem, boolean[] exito) {
        NodoABB eliminado = n;
        int comparar = elem.compareTo(n.getElem());
        if (n != null) {
            if (comparar == 0) {
                if (n.getDerecho() == null && n.getIzquierdo() == null) {
                    eliminado = null;
                }
                if (n.getIzquierdo() != null && n.getDerecho() == null) {
                    eliminado = n.getIzquierdo();
                }
                if (n.getIzquierdo() == null && n.getDerecho() != null) {
                    eliminado = n.getDerecho();

                }
                if (n.getIzquierdo() != null && n.getDerecho() != null) {
                    Object mayor = buscarMayor(n.getIzquierdo());
                    n.setIzquierdo(eliminarRec(n.getIzquierdo(), (Comparable) mayor, exito));
                    n.setElem(mayor);
                }
                exito[0] = true;
            } else {
                if (comparar < 0) {
                    n.setIzquierdo(eliminarRec(n.getIzquierdo(), elem, exito));
                } else {
                    n.setDerecho(eliminarRec(n.getDerecho(), elem, exito));
                }
            }

        }
        return eliminado;
    }

    public Object buscarMayor(NodoABB n) {
        Object mayor = null;
        if (n != null) {
            mayor = n.getElem();
            if (n.getDerecho() != null) {
                mayor = buscarMayor(n.getDerecho());
            }
        }

        return mayor;
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

    public Object minimoElem() {

        return minimoRecursivo(this.raiz);

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

    /*
     * listarRango (elemMinimo, elemMaximo) : Lista (de elemento)
     * // recorre parte del árbol (sólo lo necesario) y devuelve una lista ordenada
     * con los elementos que
     * se encuentran en el intervalo [elemMinimo, elemMaximo].
     */
    public Lista listarRango(Object elemMinimo, Object elemMaximo) {
        Lista listaRango = new Lista();
        int comparacion = ((Comparable) elemMinimo).compareTo((Comparable) elemMaximo);
        if (comparacion < 0) {
            listarRangoRec(listaRango, this.raiz, (Comparable) elemMinimo, (Comparable) elemMaximo);
        }
        return listaRango;
    }

    private void listarRangoRec(Lista l, NodoABB n, Comparable min, Comparable max) {
        if (n != null) {
            Comparable compararElem = (Comparable) n.getElem();
            int comparacionMin = compararElem.compareTo(min);
            int comparacionMax = compararElem.compareTo(max);
            if (comparacionMin >= 0 && comparacionMax <= 0) {
                l.insertar(compararElem, l.longitud() + 1);
            }
            if (comparacionMin >= 0) {
                listarRangoRec(l, n.getIzquierdo(), min, max);
            }
            if (comparacionMax < 0) {
                listarRangoRec(l, n.getDerecho(), min, max);
            }
        }
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

    public boolean eliminarMenor() {
        boolean eliminado = false;
        if (!this.vacio()) {
            this.raiz = eliminarMenorRec(this.raiz);
            eliminado = true;
        }
        return eliminado;
    }

    private NodoABB eliminarMenorRec(NodoABB n) {
        if (n.getIzquierdo() == null) {
            n = n.getDerecho();
        } else {
            n.setIzquierdo(eliminarMenorRec(n.getIzquierdo()));
        }
        return n;
    }

    /*
     * Implementar el método clonarParteInvertida(elem) que devuelve un nuevo árbol
     * que es una copia del subárbol original, cuya raíz es el elemento dado y cada
     * hijo está cambiado de lugar . Si el elemento no existe, el árbol que devuelve
     * es vacío. Ejemplo: si tiene el árbol A de la derecha y elem=13, el resultado
     * debe ser el árbol B
     * REALIZAR LA TRAZA CON EL ARBOL A DEL EJEMPLO. PROPONGA AL MENOS OTRO CASO DE
     * PRUEBA QUE TENGA RESULTADO DISTINTO
     */
    public ArbolBB clonarParteInvertida(Comparable elem) {
        ArbolBB arbol = new ArbolBB();
        NodoABB nodoClonar = perteneceAlArbol(this.raiz, elem);
        arbol.raiz = clonarInvertido(nodoClonar);
        return arbol;
    }

    private NodoABB perteneceAlArbol(NodoABB n, Comparable elem) {
        boolean encontrado = false;
        NodoABB nodoReturn = null;
        if (n != null) {
            int comparacion = elem.compareTo(n.getElem());
            if (comparacion == 0) {
                encontrado = true;
                nodoReturn = n;
            } else {
                if (comparacion < 0) {
                    nodoReturn = perteneceAlArbol(n.getIzquierdo(), elem);
                } else {
                    nodoReturn = perteneceAlArbol(n.getDerecho(), elem);
                }
            }

        }
        return nodoReturn;
    }

    private NodoABB clonarInvertido(NodoABB n) {
        NodoABB nodo = null;
        if (n != null) {
            nodo = new NodoABB(n.getElem(), clonarInvertido(n.getDerecho()), clonarInvertido(n.getIzquierdo()));
        }
        return nodo;
    }

    /*
     * EJERCICIO 3
     * En el TDA árbol binario de búsqueda, agregar el método listarMayorIgual(elem)
     * que dado un elemento
     * devuelve una lista con los elementos mayores o iguales que elem ordenados de
     * mayor a menor. El método
     * debe recorrer lo mínimo indispensable del árbol.
     */
    public Lista listarMayorIgual(Comparable elem) {
        Lista lista = new Lista();
        listarMayorIgualRec(this.raiz, lista, elem);
        return lista;
    }

    private void listarMayorIgualRec(NodoABB n, Lista lista, Comparable elem) {
        if (n != null) {
            System.out.println(n.getElem());
            int comparacion = ((Comparable) n.getElem()).compareTo(elem);
            if (comparacion >= 0) {
                listarMayorIgualRec(n.getDerecho(), lista, elem);
                lista.insertar(n.getElem(), lista.longitud() + 1);
                listarMayorIgualRec(n.getIzquierdo(), lista, elem);
            }
            if (comparacion < 0) {
                listarMayorIgualRec(n.getDerecho(), lista, elem);
            }
        }
    }
    /*
     * EJERCICIO 4
     * En el TDA árbol binario de búsqueda, agregar el método listarMenores(elem)
     * que dado un elemento
     * devuelve una lista con los elementos estrictamente menores que elem ordenados
     * de menor a mayor. El
     * método debe recorrer lo mínimo indispensable del árbol
     */
    public Lista listarMenores(Comparable elem){
        Lista lista = new Lista();
        listarMenoresRec(this.raiz, elem, lista);
        return lista;
    }
    private void listarMenoresRec(NodoABB n, Comparable elem, Lista lista){
        if (n!=null) {
            int comparacion = ((Comparable)n.getElem()).compareTo(elem);
            if (comparacion<0) {
                listarMenoresRec(n.getIzquierdo(), elem, lista);
                lista.insertar(n.getElem(), lista.longitud()+1);
                listarMenoresRec(n.getDerecho(), elem, lista);
            }
            if (comparacion>=0) {
                listarMenoresRec(n.getIzquierdo(), elem, lista);
            }
        }
    }
}
