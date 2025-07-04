/************* Autores ***********
Tomas Ismael Cárdenas Maldonado - FAI-4884
Bautista Tosi: Juan Bautista Tosi Griedassov- FAI-5162
Santiago Fuentes: Santiago Emanuel Fuentes FAI  - [5383]
Santiago Fuentes: PASCAL RIOS, IVO JAVIER, FAI-5200
*/
package lineales.dinamicas;

import lineales.dinamicas.NODO.Nodo;

public class Lista {
    private Nodo cabecera;

    public Lista() {
        this.cabecera = null;
    }

    public boolean insertar(Object elem, int indice) {
        boolean logrado = false;
        int longitud = this.longitud();
        if (indice > 0 && indice <= longitud + 1) {
            Nodo nuevo = new Nodo(elem, null);
            if (indice == 1) {
                nuevo.setEnlace(cabecera);
                this.cabecera = nuevo;
            } else {
                Nodo actual = this.cabecera;
                for (int i = 1; i < indice - 1; i++) {
                    actual = actual.getEnlace();
                }
                nuevo.setEnlace(actual.getEnlace());
                actual.setEnlace(nuevo);

            }
            logrado = true;
        }
        return logrado;
    }

    public boolean eliminar(int indice) {
        boolean hecho = true;
        int longitud = this.longitud();
        if (indice > 0 && indice <= longitud) {
            if (indice == 1) {
                this.cabecera = this.cabecera.getEnlace();
            } else {
                Nodo actual = this.cabecera;
                for (int i = 1; i < indice - 1; i++) {
                    actual = actual.getEnlace();
                }
                actual.setEnlace(actual.getEnlace().getEnlace());
            }
        } else {
            hecho = false;
        }
        return hecho;
    }

    public String toString() {
        Nodo actual = this.cabecera;
        String txt = "";
        txt += "[";
        int longitud = this.longitud();
        for (int i = 1; i < longitud + 1; i++) {
            txt += actual.getElem();
            if (actual.getEnlace() != null) {
                txt += ",";
            }
            actual = actual.getEnlace();
        }
        txt += "]";
        return txt;
    }

    public Object recuperar(int indice) {

        Nodo nodo = this.cabecera;
        Object elem = null;
        int longitud = this.longitud();

        if (indice >= 1 && indice <= longitud) {

            for (int i = 1; i < indice; i++) {

                nodo = nodo.getEnlace();

            }

            elem = nodo.getElem();

        }

        return elem;
    }

    public int localizar(Object elem) {
        Nodo nodo = this.cabecera;
        int i = 1;
        int longitud = this.longitud();
        if (elem != null) {
            while (i <= longitud && !elem.equals(nodo.getElem())) {
                nodo = nodo.getEnlace();
                i++;
            }
        } else {
            i = -1;
        }
        if (i == longitud + 1) {
            i = -1;
        }
        return i;
    }

    public int longitud() {
        Nodo nodo = this.cabecera;
        int i = 0;
        while (nodo != null) {
            nodo = nodo.getEnlace();
            i++;
        }
        return i;
    }

    public boolean esVacia() {
        boolean siEs = true;
        if (this.cabecera != null) {
            siEs = false;
        }
        return siEs;
    }

    public void vaciar() {
        this.cabecera = null;
    }

    public Lista clone() {
        Lista listaClon = new Lista();
        listaClon.cabecera = recorrerLista(this.cabecera);
        return listaClon;
    }

    private Nodo recorrerLista(Nodo nodoRecorrer) {
        Nodo nuevo = null;
        if (nodoRecorrer != null) {
            nuevo = new Nodo(nodoRecorrer.getElem(), recorrerLista(nodoRecorrer.getEnlace()));
        }
        return nuevo;
    }

    public Lista obtenerMultiplos(int num) {
        Lista listaMulti = new Lista();
        int longi = this.longitud();
        Nodo nodoActualOrig = this.cabecera;
        Nodo actualCopia = listaMulti.cabecera;
        int cont = 1;
        for (int i = 1; i <= longi; i++) {
            if (i % num == 0) {
                Nodo nuevo = new Nodo(nodoActualOrig.getElem(), null);
                if (cont == 1) {
                    actualCopia = nuevo;
                    listaMulti.cabecera = nuevo;
                } else {
                    actualCopia.setEnlace(nuevo);
                    actualCopia = nuevo;
                }
                cont++;
            }
            nodoActualOrig = nodoActualOrig.getEnlace();
        }
        return listaMulti;
    }

    public void eliminarApariciones(Object x) {

        Nodo nodoActual = this.cabecera;
        Nodo nodoAnterior = this.cabecera;

        while (nodoActual != null) {
            if (nodoActual.getElem().equals(x) && nodoActual == this.cabecera) {
                this.cabecera = nodoActual.getEnlace();
            }
            if (nodoActual.getElem().equals(x)) {
                nodoAnterior.setEnlace(nodoActual.getEnlace());
            } else {
                nodoAnterior = nodoActual;
            }

            nodoActual = nodoActual.getEnlace();
        }

    }

    public void eliminarApariciones2(Object x) {
        Nodo actual = this.cabecera;
        // Caso especial, se elimina la cabecera

        // Si no, recorremos y vamos verificando
        while (actual != null && actual.getEnlace() != null) {
            if (actual.getElem().equals(x) && (actual == this.cabecera)) {
                this.cabecera = cabecera.getEnlace();
                actual = cabecera;
            } else {
                if (actual.getEnlace().getElem().equals(x)) {
                    actual.setEnlace(actual.getEnlace().getEnlace());
                } else {
                    actual = actual.getEnlace();
                }
            }
        }
    }

    public boolean moverAAnteultimaPosicion(int pos) {
        boolean hecho = false;
        int longi = this.longitud();
        int anteUltPos = 1;
        if (longi > 2) {
            anteUltPos = longi - 1;
        }
        Nodo nodoActual = this.cabecera;
        Nodo nodoMover = this.cabecera;
        if (pos >= 1 && pos <= longi && anteUltPos != pos) {
            if (pos == 1) {
                nodoMover = nodoActual;
                this.cabecera = this.cabecera.getEnlace();
            } else {
                for (int i = 2; i < pos; i++) {
                    nodoActual = nodoActual.getEnlace();
                }
                nodoMover = nodoActual.getEnlace();
                nodoActual.setEnlace(nodoActual.getEnlace().getEnlace());
            }
            nodoActual = this.cabecera;
            if (anteUltPos == 1) {
                nodoMover.setEnlace(this.cabecera);
                this.cabecera = nodoMover;
            } else {
                for (int i = 2; i < anteUltPos; i++) {
                    nodoActual = nodoActual.getEnlace();
                }
                nodoMover.setEnlace(nodoActual.getEnlace());
                nodoActual.setEnlace(nodoMover);
            }
            hecho = true;
        }

        return hecho;
    }

    public boolean moverAAnteultimaPosicion2(int pos) {
        boolean hecho = false;
        int longi = this.longitud();
        int anteUltPos = 1;
        if (longi > 2) {
            anteUltPos = longi - 1;
        }
        Nodo nodoActual = this.cabecera;
        Nodo nodoMover = this.cabecera;
        int cont = 1;
        int cont2 = 1;
        if (pos >= 1 && pos <= longi && anteUltPos != pos) {
            while (!hecho) {
                if (cont != pos + 1) {
                    if (cont < pos - 1) {
                        nodoActual = nodoActual.getEnlace();
                    }
                    if (pos == 1) {
                        nodoMover = this.cabecera;
                        this.cabecera = this.cabecera.getEnlace();
                    } else if (cont == pos) {
                        nodoMover = nodoActual.getEnlace();
                        nodoActual.setEnlace(nodoActual.getEnlace().getEnlace());
                        nodoActual = this.cabecera;
                    }
                    cont++;
                } else {
                    if (anteUltPos == 1) {
                        nodoMover.setEnlace(this.cabecera);
                        this.cabecera = nodoMover;
                        hecho = true;
                    } else {
                        if (cont2 < anteUltPos - 1) {
                            nodoActual = nodoActual.getEnlace();
                        } else {
                            nodoMover.setEnlace(nodoActual.getEnlace());
                            nodoActual.setEnlace(nodoMover);
                            hecho = true;
                        }
                    }
                    cont2++;
                }

            }
        }

        return hecho;
    }

}
