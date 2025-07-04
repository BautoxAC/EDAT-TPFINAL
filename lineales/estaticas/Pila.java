/************* Autores ***********
Tomas Ismael Cárdenas Maldonado - FAI-4884
Bautista Tosi: Juan Bautista Tosi Griedassov- FAI-5162
Santiago Fuentes: Santiago Emanuel Fuentes FAI  - [5383]
Santiago Fuentes: PASCAL RIOS, IVO JAVIER, FAI-5200
*/
package lineales.estaticas;

public class Pila {
    /* cambiar toString */
    private Object[] arreglo;
    private int tope;
    private static final int TAMANIO = 10;

    public Pila() {
        this.arreglo = new Object[TAMANIO];
        this.tope = -1;
    }

    public boolean apilar(Object elem) {
        boolean exito;
        if (this.tope + 1 >= this.TAMANIO) {
            exito = false;
        } else {
            this.tope++;
            this.arreglo[tope] = elem;
            exito = true;
        }
        return exito;
    }

    public boolean desapilar() {
        boolean exito;
        if (this.tope == -1) {
            exito = false;
        } else {
            this.arreglo[tope] = null;
            this.tope--;
            exito = true;
        }
        return exito;
    }

    public Object obtenerTope() {
        Object topeEle;

        if (this.tope == -1) {
            topeEle = null;
        } else {
            topeEle = arreglo[this.tope];
        }

        return topeEle;

    }

    public boolean esVacia() {
        boolean siVacio = false;
        if (this.tope == -1) {
            siVacio = true;
        }
        return siVacio;
    }

    public void vaciar() {
        for (int i = 0; i < arreglo.length; i++) {
            this.arreglo[i] = null;
        }
        this.tope = -1;
    }

    public Pila clone() {
        Pila pilaClon = new Pila();
        pilaClon.arreglo = this.arreglo.clone();
        pilaClon.tope = this.tope;
        return pilaClon;
    }

    public String toString(){
        String pilaString = "[";
        for (int i = tope; i>=0; i--) {
            pilaString += arreglo[i];
            if (i!=0) {
                pilaString += ",";
            }
        }
        pilaString +="]";
        return pilaString;
    }
}