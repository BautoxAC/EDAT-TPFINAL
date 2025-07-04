package constructistas.ABB;

public class NodoABB {
    private Object elem;
    private NodoABB izquierdo;
    private NodoABB derecho;

    public NodoABB(Object elem, NodoABB izquierdo, NodoABB derecho) {
        this.elem = elem;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
    }
    public NodoABB(Object elem) {
        this.elem = elem;
        this.izquierdo = null;
        this.derecho = null;
    }

    public Object getElem() {
        return this.elem;
    }

    public NodoABB getIzquierdo() {
        return this.izquierdo;
    }

    public NodoABB getDerecho() {
        return this.derecho;
    }

    public void setElem(Object elem) {
        this.elem = elem;
    }

    public void setIzquierdo(NodoABB izquierdo) {
        this.izquierdo = izquierdo;
    }

    public void setDerecho(NodoABB derecho) {
        this.derecho = derecho;
    }
}
