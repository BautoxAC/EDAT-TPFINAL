package Estructuras.ArbolHeap;

public class ArbolHeap {
    int TAMANIO;
    NodoHeap[] heap;
    int ultimo;

    public ArbolHeap(int TAMANIO) {
        this.TAMANIO = TAMANIO;
        this.heap = new NodoHeap[TAMANIO];
        this.ultimo = 0;
    }

    public boolean insertar(Comparable clave, Object dato) {
        boolean hecho = false;
        NodoHeap nuevo = new NodoHeap(clave, dato);

        if (this.ultimo == 0) {
            this.heap[ultimo] = nuevo;
            this.ultimo++;
            hecho = true;
        } else {
            if (this.ultimo != TAMANIO) {
                int numUltimo = ultimo;
                this.heap[numUltimo] = nuevo;
                while (!hecho) {
                    NodoHeap padre = this.heap[((numUltimo + 1) / 2) - 1];
                    int comparacionPadre = clave.compareTo(padre.getClave());
                    if (comparacionPadre < 0) {
                        this.heap[numUltimo] = padre;
                        this.heap[((numUltimo + 1) / 2) - 1] = nuevo;
                        numUltimo = ((numUltimo + 1) / 2) - 1;
                    }
                    if (comparacionPadre >= 0 || numUltimo == 0) {
                        hecho = true;
                        this.ultimo++;
                    }
                }
            }
        }

        return hecho;
    }

    public String toString() {
        String heapString = "[";
        for (int i = 0; i < ultimo; i++) {
            heapString += heap[i].getClave();
            if (i != (ultimo - 1)) {
                heapString += ",";
            }
        }
        heapString += "]";
        return heapString;
    }

    public boolean eliminarCima() {
        boolean hecho = false;
        if (!this.esVacio()) {
            this.heap[0] = this.heap[ultimo - 1];
            this.heap[ultimo - 1] = null;
            this.ultimo--;
            int i = 1;
            NodoHeap cimaMomentanea = this.heap[0];
            if (this.esVacio()) {
                hecho = true;
            }
            while (!hecho) {
                NodoHeap hijoMenor = cimaMomentanea;
                NodoHeap hijoIZQ = this.heap[(2 * i) - 1];
                NodoHeap hijoDER = this.heap[(2 * i)];
                if (hijoIZQ != null && hijoDER != null) {
                    hijoMenor = (hijoIZQ.getClave().compareTo(hijoDER.getClave()) < 0) ? (hijoIZQ)
                            : (hijoDER);
                }
                if (hijoDER == null) {
                    hijoMenor = hijoIZQ;
                }
                if (hijoIZQ == null) {
                    hijoMenor = hijoDER;
                }
                if (hijoDER == null && hijoIZQ == null) {
                    hecho = true;
                    hijoMenor = cimaMomentanea;

                }
                int posMenor = (hijoIZQ == hijoMenor) ? (2 * i) - 1 : (2 * i);
                int comparacionHijoMenor = cimaMomentanea.getClave().compareTo(hijoMenor.getClave());
                if (comparacionHijoMenor > 0 && !hecho) {
                    this.heap[i - 1] = hijoMenor;
                    this.heap[posMenor] = cimaMomentanea;
                    i = posMenor + 1;
                } else {
                    hecho = true;
                }
            }
        }

        return hecho;
    }

    public boolean esVacio() {
        boolean es = false;
        if (this.ultimo == 0) {
            es = true;
        }
        return es;
    }

    public Object[] ordenarArreglo() {
        boolean hecho = false;
        int ult = ultimo - 1;
        Object[] arr = new Object[ultimo];
        for (int j = 0; j <= ult; j++) {
            hecho = false;
            if (!this.esVacio()) {
                arr[ultimo - 1] = this.heap[0].getDato();
                this.heap[0] = this.heap[ultimo - 1];
                this.ultimo--;
                int i = 1;
                NodoHeap cimaMomentanea = this.heap[0];
                if (this.esVacio()) {
                    hecho = true;
                }
                while (!hecho) {
                    int posMenor = 0;
                    NodoHeap hijoMenor = cimaMomentanea;
                    int posHijoIZQ = (2 * i) - 1;
                    int posHijoDER = (2 * i);
                    if (posHijoIZQ < this.ultimo
                            && this.heap[posHijoIZQ].getClave().compareTo(this.heap[posHijoDER].getClave()) < 0) {
                        posMenor = posHijoIZQ;
                    }
                    if (posHijoDER < this.ultimo
                            && this.heap[posHijoIZQ].getClave().compareTo(this.heap[posHijoDER].getClave()) > 0) {
                        posMenor = posHijoDER;
                    }
                    if (posMenor != 0) {
                        hijoMenor = this.heap[posMenor];
                        int comparacionHijoMenor = cimaMomentanea.getClave().compareTo(hijoMenor.getClave());
                        if (comparacionHijoMenor > 0) {
                            this.heap[i - 1] = hijoMenor;
                            this.heap[posMenor] = cimaMomentanea;
                            i = posMenor + 1;
                        } else {
                            hecho = true;
                        }
                    } else {
                        hecho = true;
                    }
                }
            }
        }
        this.ultimo = ult + 1;
        return arr;
    }
}
