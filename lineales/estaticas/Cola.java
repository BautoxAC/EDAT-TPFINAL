/************* Autores ***********
Tomas Ismael Cárdenas Maldonado - FAI-4884
Bautista Tosi: Juan Bautista Tosi Griedassov- FAI-5162
Santiago Fuentes: Santiago Emanuel Fuentes FAI  - [5383]
Santiago Fuentes: PASCAL RIOS, IVO JAVIER, FAI-5200
*/
package lineales.estaticas;

public class Cola {
    // Variables
    private Object[] cola;
    private int frente;
    private int fin;
    private static final int TAMANIO = 10;

    public Cola() {
        frente = 0;
        fin = 0;
        cola = new Object[TAMANIO];
    }

    public boolean poner(Object nuevoEle) {
        boolean hecho = false;
        if ((fin + 1) % TAMANIO != frente) {
            cola[fin] = nuevoEle;
            fin = (fin + 1) % TAMANIO;
            hecho = true;
        }
        return hecho;
    }

    public boolean sacar() {
        boolean hecho = false;
        if (!this.esVacia()) {
            cola[frente] = null;
            frente = (frente + 1) % TAMANIO;
            hecho = true;
        }
        return hecho;
    }

    public Object obtenerFrente() {
        Object enfrente;
        enfrente = cola[frente];
        return enfrente;
    }

    public boolean esVacia() {
        boolean vacia = false;
        if (frente == fin) {
            vacia = true;
        }
        return vacia;
    }

    public void vaciar() {
        for (int i = 0; i < TAMANIO; i++) {
            cola[i] = null;
        }
        frente = 0;
        fin = 0;
    }

    public Cola clone() {
        Cola colaClone = new Cola();
        colaClone.frente = this.frente;
        colaClone.fin = this.fin;
        colaClone.cola = this.cola.clone();
        return colaClone;
    }

    public String toString() {
        String colaString = "[";
        for (int i = frente; i != this.fin; i = (i + 1) % TAMANIO) {
            colaString += cola[i];
            if (i != this.fin-1) {
                colaString += ",";
            }
        }
        colaString += "]";
        return colaString;
    }
}