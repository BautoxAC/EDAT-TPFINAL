package constructistas.ArbolHeap;

public class ArbolHeap {
    int TAMANIO = 20;
    Comparable[] heap;
    int ultimo;

    public ArbolHeap() {
        this.heap = new Comparable[TAMANIO];
        this.ultimo = 0;
    }

    public boolean insertar(Comparable elem) {
        boolean hecho = false;
        if (this.ultimo == 0) {
            this.heap[ultimo] = elem;
            this.ultimo++;
            hecho = true;
        } else {
            if (this.ultimo != TAMANIO) {
                int numUltimo = ultimo;
                this.heap[numUltimo] = elem;
                while (!hecho) {
                    Comparable padre = this.heap[((numUltimo + 1) / 2) - 1];
                    int comparacionPadre = elem.compareTo(padre);
                    if (comparacionPadre < 0) {
                        this.heap[numUltimo] = padre;
                        this.heap[((numUltimo + 1) / 2) - 1] = elem;
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
            heapString += heap[i];
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
            int cantIt = 1;
            Comparable cimaMomentanea = this.heap[0];
            if (this.esVacio()) {
                hecho = true;
            }
            while (!hecho && cantIt < 5 && i * 2 < 20) {
                Comparable hijoMenor = cimaMomentanea;
                Comparable hijoIZQ = this.heap[(2 * i) - 1];
                Comparable hijoDER = this.heap[(2 * i)];
                if (hijoIZQ != null && hijoDER != null) {
                    hijoMenor = (hijoIZQ.compareTo(hijoDER) < 0) ? (hijoIZQ)
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
                int comparacionHijoMenor = cimaMomentanea.compareTo(hijoMenor);
                if (comparacionHijoMenor > 0) {
                    this.heap[i - 1] = hijoMenor;
                    this.heap[posMenor] = cimaMomentanea;
                    i = posMenor + 1;
                } else {
                    hecho = true;
                }
                cantIt++;
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
}
