package Estructuras.ArbolAVL;

import Estructuras.lineales.Lista;

public class ArbolAVL {
    private NodoAVL raiz;

    public ArbolAVL() {
        this.raiz = null;
    }

    public boolean insertar(Comparable elem) {

        boolean[] exito = { false };

        if (this.raiz == null) {
            this.raiz = new NodoAVL(elem);
            exito[0] = true;
        } else {
            this.raiz = insertarRec(this.raiz, elem, exito);
        }
        return exito[0];
    }

    private NodoAVL insertarRec(NodoAVL n, Comparable elem, boolean[] exito) {
        int comparar = elem.compareTo(n.getElem());
        if (n != null) {
            if (comparar != 0) {
                if (comparar < 0) {
                    if (n.getIzquierdo() != null) {
                        n.setIzquierdo(insertarRec(n.getIzquierdo(), elem, exito));
                    } else {
                        n.setIzquierdo(new NodoAVL(elem));
                        exito[0] = true;
                    }
                } else {
                    if (n.getDerecho() != null) {
                        n.setDerecho(insertarRec(n.getDerecho(), elem, exito));
                    } else {
                        n.setDerecho(new NodoAVL(elem));
                        exito[0] = true;
                    }
                }
            }
        }
        if (exito[0]) {
            n.recalcularAltura();
            int balance;
            balance = calcularBalance(n);
            System.out.println(n.getElem() + " balance: " + balance + " elemIn: " + elem + " altura: " + n.getAltura());
            if (balance > 1) {
                // desabalaceado por izq entonces balanceo por izq
                // rotacion a der

                int balanceHijo = calcularBalance(n.getIzquierdo());
                if (balanceHijo < 0) {
                    n.setIzquierdo(balancearDer(n.getIzquierdo()));

                }
                n = balancearIzq(n);

            }
            if (balance < -1) {
                // desabalaceado por der entonces balanceo por der
                // roto a IZQ
                int balanceHijo = calcularBalance(n.getDerecho());
                System.out.println(balanceHijo);
                if (balanceHijo > 0) {
                    n.setDerecho(balancearIzq(n.getDerecho()));
                }
                n = balancearDer(n);
            }
        }
        return n;
    }

    private NodoAVL balancearIzq(NodoAVL r) {
        // rotacion a der
        NodoAVL h;
        // rotacion simple
        h = r.getIzquierdo();
        NodoAVL temp = h.getDerecho();
        h.setDerecho(r);
        r.setIzquierdo(temp);
        r.recalcularAltura();
        h.recalcularAltura();
        return h;
    }

    private NodoAVL balancearDer(NodoAVL r) {
        // rotacion a izq
        NodoAVL h;
        // rotacion simple
        h = r.getDerecho();
        NodoAVL temp = h.getIzquierdo();
        h.setIzquierdo(r);
        r.setDerecho(temp);
        r.recalcularAltura();
        h.recalcularAltura();
        return h;
    }

    private int calcularBalance(NodoAVL n) {
        int altDer = (n.getDerecho() != null) ? n.getDerecho().getAltura() : -1;
        int altIzq = (n.getIzquierdo() != null) ? n.getIzquierdo().getAltura() : -1;
        int balance = altIzq - altDer;
        return balance;
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

    private void recorrerPreOrden(Lista lista, NodoAVL padre) {
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

    private NodoAVL eliminarRec(NodoAVL n, Comparable elem, boolean[] exito) {
        NodoAVL eliminado = n;
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
        if (exito[0] && eliminado != null) {
            eliminado.recalcularAltura();
            int balance = calcularBalance(eliminado);

            if (balance > 1) {
                // desabalaceado por izq entonces balanceo por izq
                // rotacion a der
                int balanceHijo = calcularBalance(eliminado.getIzquierdo());
                if (balanceHijo < 0) {
                    eliminado.setIzquierdo(balancearDer(eliminado.getIzquierdo()));
                }
                eliminado = balancearIzq(eliminado);

            }
            if (balance < -1) {
                // desabalaceado por der entonces balanceo por der

                int balanceHijo = calcularBalance(n.getDerecho());
                if (balanceHijo > 0) {
                    eliminado.setDerecho(balancearIzq(n.getDerecho()));
                }
                eliminado = balancearDer(eliminado);
            }
        }
        return eliminado;
    }

    public Object buscarMayor(NodoAVL n) {
        Object mayor = null;
        if (n != null) {
            mayor = n.getElem();
            if (n.getDerecho() != null) {
                mayor = buscarMayor(n.getDerecho());
            }
        }

        return mayor;
    }

    private Object minimoRecursivo(NodoAVL nodoActual) {

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

    public String toString() {

        return toStringAux(this.raiz);

    }

    private String toStringAux(NodoAVL nodoActual) {
        String texto = "";

        if (nodoActual != null) {
            texto += nodoActual.getElem().toString() + " -> ";
            texto += "HI: ";
            System.out.println(nodoActual.getElem() + " " + nodoActual.getAltura());
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

    private Object maximoRecursivo(NodoAVL nodoActual) {

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

    private void listarRangoRec(Lista l, NodoAVL n, Comparable min, Comparable max) {
        if (n != null) {
            Comparable compararElem = (Comparable) n.getElem();
            int comparacionMin = compararElem.compareTo(min);
            int comparacionMax = compararElem.compareTo(max);
            if (comparacionMin >= 0 && comparacionMax <= 0) {
                l.insertar(compararElem, l.longitud() + 1);
            }
            if (comparacionMin > 0) {
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

    private boolean perteneceRecursivo(NodoAVL nodoActual, Comparable elem) {

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

}
