package TrabajoFinal.Estructuras.lineales;

public class Pila {

    private Nodo tope;

    public Pila() {
        this.tope = null;
    }

    public boolean apilar(Object nuevoElem) {
        Nodo nuevo = new Nodo(nuevoElem, this.tope);

        this.tope = nuevo;

        return true;
    }

    public boolean desapilar() {
        boolean exito = false;

        if (tope != null) {
            tope = tope.getEnlace();
            exito = true;
        }

        return exito;

    }

    public Object obtenerTope() {
        Object objeto = null;

        if (tope != null) {
            objeto = tope.getElem();
        }

        return objeto;

    }

    public boolean esVacia() {
        return this.tope == null;
    }

    public void vaciar() {
        this.tope = null;
    }

    public Pila clone() {
        Pila clonPila = new Pila();
        clonPila.tope = clonarPila(this.tope);
        return clonPila;

    }

    private Nodo clonarPila(Nodo principal) {
        Nodo nodoAux = null;
        if (principal != null) {
            nodoAux = new Nodo(principal.getElem(), clonarPila(principal.getEnlace()));
        }
        return nodoAux;

    }

    public String toString() {
        String texto = "[";
        Nodo aux = this.tope;
        while (aux != null) {
            texto += aux.getElem();
            aux = aux.getEnlace();
            if (aux != null) texto += ",";
        }
        texto += "]";
        return texto;
    }

}