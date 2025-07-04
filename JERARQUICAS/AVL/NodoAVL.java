package constructistas.AVL;

public class NodoAVL {
    private Object elem;
    private NodoAVL izquierdo;
    private NodoAVL derecho;
    private int altura;

    public NodoAVL(Object elem, NodoAVL izquierdo, NodoAVL derecho) {
        this.elem = elem;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
        this.altura = 0;
    }

    public NodoAVL(Object elem) {
        this.elem = elem;
        this.izquierdo = null;
        this.derecho = null;
        this.altura = 0;
    }

    public Object getElem() {
        return this.elem;
    }

    public NodoAVL getIzquierdo() {
        return this.izquierdo;
    }

    public NodoAVL getDerecho() {
        return this.derecho;
    }

    public int getAltura() {
        return this.altura;
    }

    public void setElem(Object elem) {
        this.elem = elem;
    }

    public void setIzquierdo(NodoAVL izquierdo) {
        this.izquierdo = izquierdo;
    }

    public void setDerecho(NodoAVL derecho) {
        this.derecho = derecho;
    }

    public void recalcularAltura() {
        int HI = (this.getIzquierdo() != null) ? this.getIzquierdo().getAltura() : -1;
        int HD = (this.getDerecho() != null) ? this.getDerecho().getAltura() : -1;
        this.altura = Math.max(HI, HD) + 1;
    }

}
