package Estructuras.Especificas.Diccionario;

import java.util.ArrayList;
import java.util.List;
import Estructuras.lineales.*;

// clase hecha por maxi con el codigo de bauti

public class Diccionario {

    private NodoAVLDicc raiz;

    public Diccionario() {

        this.raiz = null;

    }

    public boolean insertar(Object[] par) {

        boolean[] exito = { false };

        if (this.raiz == null) {
            this.raiz = new NodoAVLDicc(par[0], par[1]);
            exito[0] = true;
        } else {
            this.raiz = insertarRec(this.raiz, par, exito);
        }
        return exito[0];
    }

    private NodoAVLDicc insertarRec(NodoAVLDicc n, Object[] par, boolean[] exito) {
        Comparable elem = (Comparable) par[0];
        int comparar;
        if (n != null) {
            comparar = elem.compareTo(n.getClave());
            if (comparar != 0) {
                if (comparar < 0) {
                    if (n.getHijoIzquierdo() != null) {
                        n.setHijoIzquierdo(insertarRec(n.getHijoIzquierdo(), par, exito));
                    } else {
                        n.setHijoIzquierdo(new NodoAVLDicc(par[0], par[1]));
                        exito[0] = true;
                    }
                } else {
                    if (n.getHijoDerecho() != null) {
                        n.setHijoDerecho(insertarRec(n.getHijoDerecho(), par, exito));
                    } else {
                        n.setHijoDerecho(new NodoAVLDicc(par[0], par[1]));
                        exito[0] = true;
                    }
                }
            }
        }
        if (exito[0]) {
            n.recalcularAltura();
            int balance;
            balance = calcularBalance(n);
            System.out
                    .println(n.getClave() + " balance: " + balance + " elemIn: " + elem + " altura: " + n.getAltura());
            if (balance > 1) {
                // desabalaceado por izq entonces balanceo por izq
                // rotacion a der

                int balanceHijo = calcularBalance(n.getHijoIzquierdo());
                if (balanceHijo < 0) {
                    n.setHijoIzquierdo(balancearDer(n.getHijoIzquierdo()));

                }
                n = balancearIzq(n);

            }
            if (balance < -1) {
                // desabalaceado por der entonces balanceo por der
                // roto a IZQ
                int balanceHijo = calcularBalance(n.getHijoDerecho());
                System.out.println(balanceHijo);
                if (balanceHijo > 0) {
                    n.setHijoDerecho(balancearIzq(n.getHijoDerecho()));
                }
                n = balancearDer(n);
            }
        }
        return n;
    }

    private NodoAVLDicc balancearIzq(NodoAVLDicc r) {
        // rotacion a der
        NodoAVLDicc h;
        // rotacion simple
        h = r.getHijoIzquierdo();
        NodoAVLDicc temp = h.getHijoDerecho();
        h.setHijoDerecho(r);
        r.setHijoIzquierdo(temp);
        r.recalcularAltura();
        h.recalcularAltura();
        return h;
    }

    private NodoAVLDicc balancearDer(NodoAVLDicc r) {
        // rotacion a izq
        NodoAVLDicc h;
        // rotacion simple
        h = r.getHijoDerecho();
        NodoAVLDicc temp = h.getHijoIzquierdo();
        h.setHijoIzquierdo(r);
        r.setHijoDerecho(temp);
        r.recalcularAltura();
        h.recalcularAltura();
        return h;
    }

    private int calcularBalance(NodoAVLDicc n) {
        int altDer = (n.getHijoDerecho() != null) ? n.getHijoDerecho().getAltura() : -1;
        int altIzq = (n.getHijoIzquierdo() != null) ? n.getHijoIzquierdo().getAltura() : -1;
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

    public boolean eliminar(Comparable elem) {
        boolean[] exito = { false };
        if (!this.vacio()) {
            this.raiz = eliminarRec(this.raiz, elem, exito);
        }
        return exito[0];
    }

    private NodoAVLDicc eliminarRec(NodoAVLDicc n, Comparable elem, boolean[] exito) {
        NodoAVLDicc eliminado = n;
        int comparar;
        if (n != null) {
            comparar = elem.compareTo(n.getClave());
            if (comparar == 0) {
                if (n.getHijoDerecho() == null && n.getHijoIzquierdo() == null) {
                    eliminado = null;
                }
                if (n.getHijoIzquierdo() != null && n.getHijoDerecho() == null) {
                    eliminado = n.getHijoIzquierdo();
                }
                if (n.getHijoIzquierdo() == null && n.getHijoDerecho() != null) {
                    eliminado = n.getHijoDerecho();

                }
                if (n.getHijoIzquierdo() != null && n.getHijoDerecho() != null) {
                    Object[] parMayor = buscarMayor(n.getHijoIzquierdo());
                    n.setHijoIzquierdo(eliminarRec(n.getHijoIzquierdo(), (Comparable) parMayor[1], exito));
                    n.setClave(parMayor[0]);
                    n.setDato(parMayor[1]);
                }
                exito[0] = true;
            } else {
                if (comparar < 0) {
                    n.setHijoIzquierdo(eliminarRec(n.getHijoIzquierdo(), elem, exito));
                } else {
                    n.setHijoDerecho(eliminarRec(n.getHijoDerecho(), elem, exito));
                }
            }

        }
        if (exito[0] && eliminado != null) {
            eliminado.recalcularAltura();
            int balance = calcularBalance(eliminado);

            if (balance > 1) {
                // desabalaceado por izq entonces balanceo por izq
                // rotacion a der
                int balanceHijo = calcularBalance(eliminado.getHijoIzquierdo());
                if (balanceHijo < 0) {
                    eliminado.setHijoIzquierdo(balancearDer(eliminado.getHijoIzquierdo()));
                }
                eliminado = balancearIzq(eliminado);

            }
            if (balance < -1) {
                // desabalaceado por der entonces balanceo por der

                int balanceHijo = calcularBalance(n.getHijoDerecho());
                if (balanceHijo > 0) {
                    eliminado.setHijoDerecho(balancearIzq(n.getHijoDerecho()));
                }
                eliminado = balancearDer(eliminado);
            }
        }
        return eliminado;
    }

    public Object[] buscarMayor(NodoAVLDicc n) {
        Object[] parMayor = null;
        if (n != null) {
            parMayor = new Object[2];
            parMayor[0] = n.getClave();
            parMayor[1] = n.getDato();
            if (n.getHijoDerecho() != null) {
                parMayor = buscarMayor(n.getHijoDerecho());
            }
        }

        return parMayor;
    }

    private Object[] minimoRecursivo(NodoAVLDicc nodoActual) {

        Object[] parMinimo = null;

        if (nodoActual != null) {

            parMinimo = new Object[2];

            if (nodoActual.getHijoIzquierdo() == null) {
                parMinimo[0] = nodoActual.getClave();
                parMinimo[1] = nodoActual.getDato();
            } else {
                parMinimo = minimoRecursivo(nodoActual.getHijoIzquierdo());
            }

        }

        return parMinimo;

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

    private String toStringAux(NodoAVLDicc nodoActual) {
        String texto = "";

        if (nodoActual != null) {
            texto += nodoActual.getClave().toString() + " -> ";
            texto += "HI: ";
            System.out.println(nodoActual.getClass() + " " + nodoActual.getAltura());
            if (nodoActual.getHijoIzquierdo() != null) {
                texto += nodoActual.getHijoIzquierdo().getClave();
            } else {
                texto += "N/A";
            }

            texto += " HD: ";

            if (nodoActual.getHijoDerecho() != null) {
                texto += nodoActual.getHijoDerecho().getClave();
            } else {
                texto += "N/A";
            }

            texto += "\n";

            texto += toStringAux(nodoActual.getHijoIzquierdo());
            texto += toStringAux(nodoActual.getHijoDerecho());

        }

        return texto;
    }

    private Object[] maximoRecursivo(NodoAVLDicc nodoActual) {

        Object[] parMaximo = null;

        if (nodoActual != null) {

            if (nodoActual.getHijoDerecho() == null) {
                parMaximo = new Object[2];
                parMaximo[0] = nodoActual.getClave();
                parMaximo[1] = nodoActual.getDato();
            } else {
                parMaximo = maximoRecursivo(nodoActual.getHijoDerecho());
            }

        }

        return parMaximo;

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

    private void listarRangoRec(Lista l, NodoAVLDicc n, Comparable min, Comparable max) {
        if (n != null) {
            Comparable compararElem = (Comparable) n.getClave();
            int comparacionMin = compararElem.compareTo(min);
            int comparacionMax = compararElem.compareTo(max);
            if (comparacionMin >= 0 && comparacionMax <= 0) {
                l.insertar(compararElem, l.longitud() + 1);
            }
            if (comparacionMin > 0) {
                listarRangoRec(l, n.getHijoIzquierdo(), min, max);
            }
            if (comparacionMax < 0) {
                listarRangoRec(l, n.getHijoDerecho(), min, max);
            }
        }
    }

    public boolean pertenece(Object elem) {

        return perteneceRecursivo(this.raiz, (Comparable) elem);

    }

    private boolean perteneceRecursivo(NodoAVLDicc nodoActual, Comparable elem) {

        boolean pertenece = false;

        int comparacion;

        // para todos los nodos verifico cuando coincida el elemento, si no lo hace, voy
        // al menor o al mayor
        // cuando corresponda

        if (nodoActual != null) {

            comparacion = elem.compareTo(nodoActual.getClave());

            if (comparacion == 0) {
                pertenece = true;
            } else {
                if (comparacion < 0) {
                    pertenece = perteneceRecursivo(nodoActual.getHijoIzquierdo(), elem);
                } else {
                    pertenece = perteneceRecursivo(nodoActual.getHijoDerecho(), elem);
                }

            }
        }

        return pertenece;

    }

    public Object obtenerDato(Object clave) {

        return obtenerDatoAux(this.raiz, (Comparable) clave);

    }

    private Object obtenerDatoAux(NodoAVLDicc nodoActual, Comparable clave) {

        Object dato = null;

        int comparacion;

        if (nodoActual != null) {

            comparacion = clave.compareTo(nodoActual.getClave());

            if (comparacion == 0) {
                dato = nodoActual.getDato();
            } else {
                if (comparacion < 0) {
                    dato = obtenerDatoAux(nodoActual.getHijoIzquierdo(), clave);
                } else {
                    dato = obtenerDatoAux(nodoActual.getHijoDerecho(), clave);
                }

            }
        }

        System.out.println("Dato es: ");
        System.out.println(dato);

        return dato;

    }

    public boolean existeClave(Object clave) {

        return existeClaveAux(this.raiz, (Comparable) clave);

    }

    private boolean existeClaveAux(NodoAVLDicc nodoActual, Comparable clave) {

        boolean existe = false;

        int comparacion;

        if (nodoActual != null) {

            comparacion = clave.compareTo(nodoActual.getClave());

            if (comparacion == 0) {
                existe = true;
            } else {
                if (comparacion < 0) {
                    existe = existeClaveAux(nodoActual.getHijoIzquierdo(), clave);
                } else {
                    existe = existeClaveAux(nodoActual.getHijoDerecho(), clave);
                }

            }
        }

        return existe;

    }

    public Lista listarClaves() {
        Lista arbolPreOrden = new Lista();
        listarClavesAux(arbolPreOrden, this.raiz);
        return arbolPreOrden;
    }

    private void listarClavesAux(Lista lista, NodoAVLDicc padre) {
        if (padre != null) {
            lista.insertar(padre.getClave(), lista.longitud() + 1);
            listarClavesAux(lista, padre.getHijoIzquierdo());
            listarClavesAux(lista, padre.getHijoDerecho());
        }
    }

    public Lista listarDatos() {
        Lista arbolPreOrden = new Lista();
        listarDatosAux(arbolPreOrden, this.raiz);
        return arbolPreOrden;
    }

    private void listarDatosAux(Lista lista, NodoAVLDicc padre) {
        if (padre != null) {
            lista.insertar(padre.getDato(), lista.longitud() + 1);
            listarDatosAux(lista, padre.getHijoIzquierdo());
            listarDatosAux(lista, padre.getHijoDerecho());
        }
    }

}
