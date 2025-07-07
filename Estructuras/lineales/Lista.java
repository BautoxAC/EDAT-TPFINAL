package TrabajoFinal.Estructuras.lineales;

public class Lista {
    private Nodo cabecera;

    public Lista() {
        this.cabecera = null;
    }

    public boolean insertar(Object nuevoElem, int pos) {
        boolean exito = true;
        if (pos < 1 || pos > this.longitud() + 1) {
            exito = false;
        } else {
            if (pos == 1) {
                this.cabecera = new Nodo(nuevoElem, this.cabecera);
            } else {
                Nodo aux = this.cabecera;
                int i = 1;
                while (i < pos - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                Nodo nuevo = new Nodo(nuevoElem, aux.getEnlace());
                aux.setEnlace(nuevo);
            }
        }
        return exito;
    }

    public boolean eliminar(int pos) {
        boolean exito = false;
        int aux = 1;
        Nodo nodoAux = this.cabecera;
        if (!esVacia()) {
            if (pos > 1 && pos <= this.longitud()) {
                while (aux < pos - 1) {
                    nodoAux = nodoAux.getEnlace();
                    aux++;
                }
                nodoAux.setEnlace(nodoAux.getEnlace().getEnlace());
                exito = true;

            } else if (pos == 1) {
                this.cabecera = this.cabecera.getEnlace();
                exito = true;
            }
        }
        return exito;
    }

    public Object recuperar(int pos) {
        int aux = 1;
        Object elem = null;
        Nodo nodoAux = this.cabecera;
        if (pos >= 1 && pos <= this.longitud()) {
            while (aux <= pos) {
                elem = nodoAux.getElem();
                nodoAux = nodoAux.getEnlace();
                aux++;
            }
        }
        return elem;
    }

    public int localizar(Object elemento) {
        int posicion = -1;
        Nodo nodoAux = this.cabecera;
        int cuenta = 1;
        while (posicion == -1 && nodoAux != null) {
            if (nodoAux.getElem().equals(elemento)) {
                posicion = cuenta;
            } else {
                nodoAux = nodoAux.getEnlace();
                cuenta++;
            }
        }
        return posicion;
    }

    public int longitud() {
        int pos = 0;
        Nodo aux = cabecera;
        while (aux != null) {
            pos++;
            aux = aux.getEnlace();
        }
        return pos;
    }

    public boolean esVacia() {
        return this.cabecera == null;
    }

    public void vaciar() {
        this.cabecera = null;
    }

    public Lista clone() {
        Lista listaClon = new Lista();
        listaClon.cabecera = clonarLista(this.cabecera);
        return listaClon;
    }

    private Nodo clonarLista(Nodo nodoActual) {
        Nodo retorno = null;
        if (nodoActual != null) {
            retorno = new Nodo(nodoActual.getElem(), clonarLista(nodoActual.getEnlace()));
        }
        return retorno;
    }

    public String toString() {
        String texto = "[";
        Nodo aux = cabecera;
        while (aux != null) {
            texto += aux.getElem();
            aux = aux.getEnlace();
            if (aux != null)
                texto += ",";
        }
        texto += "]";
        return texto;
    }

    public void eliminarAparicionesV2(Object x) {

        Nodo nodoAnterior = this.cabecera;
        Nodo nodoActual = this.cabecera;

        while (nodoActual != null) {
            if (nodoActual.getElem().equals(x)) {
                if (nodoAnterior != nodoActual) {
                    nodoAnterior.setEnlace(nodoActual.getEnlace());
                    nodoActual.setEnlace(null);
                } else {
                    this.cabecera = nodoActual.getEnlace();
                }
            } else {
                nodoAnterior = nodoActual;
            }
            nodoActual = nodoAnterior.getEnlace();
        }

    }

    public boolean moverAAnteultimaPosicionV2(int pos) {

        boolean movido = false;
        int cuenta = 1;
        int longitud = longitud();
        Nodo nodoActual = this.cabecera;
        Nodo guardado = null;

        if (longitud > 0 && pos <= longitud) {

            while (nodoActual != null && cuenta <= longitud) {
                
                if (cuenta == pos - 1) {
                    guardado = nodoActual.getEnlace();
                    nodoActual.setEnlace(guardado.getEnlace());
                }
                if (cuenta == longitud - 2) {
                    guardado.setEnlace(nodoActual.getEnlace());
                    nodoActual.setEnlace(guardado);
                    movido = true;
                }
                nodoActual = nodoActual.getEnlace();

                cuenta++;

            }

        }

        return movido;

    }

    public Lista obtenerMultiplosV2(int num) {

        Lista nuevaLista = new Lista();
        Nodo nodoActual = this.cabecera;
        int contador = 1;

        for (int i = 1; i <= longitud(); i++) {
            if (i % num == 0) {
                nuevaLista.insertar(nodoActual.getElem(), contador);
                contador++;
            }
            nodoActual = nodoActual.getEnlace();
        }

        return nuevaLista;

    }

    public Lista obtenerMultiplos(int num) {

        Lista lista = new Lista();
        int longitud = longitud();
        Nodo nodoActual = this.cabecera;
        int contador = 1;
        for (int i = 1; i <= longitud; i++) {
            if (i % num == 0) {
                insertarEnLista(lista, nodoActual.getElem(), contador);
                contador++;
            }
            nodoActual = nodoActual.getEnlace();
        }

        return lista;

    }

    public static void insertarEnLista(Lista lista, Object elem, int posicion) {

        Nodo nuevoNodo = new Nodo(elem,null);
        Nodo nodoActual = lista.cabecera;
        boolean encontrado = false;

        if (posicion == 1) {

            lista.cabecera = nuevoNodo;

        } else {

            while (!encontrado && nodoActual != null) {

                if (nodoActual.getEnlace() == null) {
                    nodoActual.setEnlace(nuevoNodo);
                    encontrado = true;
                } else {
                    nodoActual = nodoActual.getEnlace();
                }
                
            }

        }

    }

    public void eliminarApariciones(Object x) {

        Nodo nodoAnterior = this.cabecera;
        Nodo nodoActual = this.cabecera;
        
        while (nodoActual != null) {

            if (nodoActual.getElem().equals(x)) {

                if (nodoActual == this.cabecera) {
                    this.cabecera = nodoActual.getEnlace();
                } else {
                    nodoAnterior.setEnlace(nodoActual.getEnlace());
                }

            } else {

                nodoAnterior = nodoActual;

            }

            nodoActual = nodoAnterior.getEnlace();
            
        }

    }

    public boolean moverAAnteultimaPosicionV3(int pos) {

        boolean semovio = false;

        Nodo nodoGuardado = null;
        Nodo nodoActual = this.cabecera;
        int cuenta = 1;
        int longitud = longitud();

        if (longitud > 0 && pos <= longitud) {
            while (nodoActual != null) {

                if (cuenta == pos - 1) {
                    nodoGuardado = nodoActual.getEnlace();
                    nodoActual.setEnlace(nodoGuardado.getEnlace());
                }
                if (cuenta == longitud - 2) {
                    nodoGuardado.setEnlace(nodoActual.getEnlace());
                    nodoActual.setEnlace(nodoGuardado);
                    semovio = true;
                }

                nodoActual = nodoActual.getEnlace();
                cuenta++;
    
            }
        }


        return semovio;

    }

    public void agregarElem(Object nuevo, int x) {

        Nodo nodoActual = this.cabecera;
        int contador = 1;
        Nodo nodoAux = null;

        if (nodoActual == null) {
            this.cabecera = new Nodo(nuevo,null);
        } else {
            nodoAux = new Nodo(nuevo,null);
            nodoAux.setEnlace(nodoActual);
            this.cabecera = nodoAux;
            
            while (nodoActual != null) {
                if (contador == x) {
                    nodoAux = new Nodo(nuevo,null);
                    nodoAux.setEnlace(nodoActual.getEnlace());
                    nodoActual.setEnlace(nodoAux);
                    contador = -1;
                }
                contador++;
                nodoActual = nodoActual.getEnlace();
            }
        }

    }

    public boolean moverAAnteultimaPosicion(int pos) {

        boolean pudo = false;

        Nodo nodoAux = null;
        Nodo nodoActual = this.cabecera;
        Nodo nodoAnterior = this.cabecera;

        int contador = 1;
        int longitud = longitud();

        if (pos <= longitud && pos != longitud - 1 && pos > 0) {

            if (pos != longitud && pos != 1) {
                while (nodoActual != null) {
                    if (contador == pos) {
                        nodoAux = nodoActual;
                        nodoAnterior.setEnlace(nodoActual.getEnlace());
                    }
                    if (contador == longitud) {
                        nodoAux.setEnlace(nodoAnterior.getEnlace());
                        nodoAnterior.setEnlace(nodoAux);
                    }
                    contador++;
                    nodoAnterior = nodoActual;
                    nodoActual = nodoActual.getEnlace();
                }
            } else {
                if (pos == 1) {
                    nodoAux = this.cabecera;
                    this.cabecera = nodoAux.getEnlace();
                }
                
                while (nodoActual != null) {

                    if (pos == longitud) {
                        if (contador == longitud - 1) {
                            nodoAux = nodoActual.getEnlace();
                            nodoActual.setEnlace(null);
                            nodoAux.setEnlace(nodoActual);
                            nodoAnterior.setEnlace(nodoAux);
                            pudo = true;
                        }
                    } else {
                        if (contador == longitud) {
                            nodoAux.setEnlace(nodoActual);
                            nodoAnterior.setEnlace(nodoAux);
                            pudo = true;
                        }
                    }

                    contador++;
                    nodoAnterior = nodoActual;
                    nodoActual = nodoActual.getEnlace();
                }
            }

        }

        return pudo;

    }

}