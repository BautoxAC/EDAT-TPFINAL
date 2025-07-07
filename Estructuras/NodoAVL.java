package Estructuras;

public class NodoAVL {
    
    private Object elem;
    private int altura;
    private NodoAVL izquierdo;
    private NodoAVL derecho;

    public NodoAVL (Object elem, NodoAVL izquierdo, NodoAVL derecho) {

        this.elem = elem;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
        this.altura = 0;

    }

    public NodoAVL (Object elem) {

        this.elem = elem;
        this.izquierdo = null;
        this.derecho = null;

    }

    public void recalcularAltura() {
        
        int alturaHI = -1;
        int alturaHD = -1;

        if (this.izquierdo != null) {
            alturaHI = this.izquierdo.getAltura();
        }
        if (this.derecho != null) {
            alturaHD = this.derecho.getAltura();
        }

        this.altura = Math.max(alturaHI,alturaHD) + 1;

        System.out.println("nodo con elem "+elem+" tiene altura "+this.altura);

    }

    public Object getElem() {
        return this.elem;
    }

    public int getAltura() {
        return this.altura;
    }

    public NodoAVL getIzquierdo() {
        return this.izquierdo;
    }

    public NodoAVL getDerecho() {
        return this.derecho;
    }

    public void setElem(Object elem) {
        this.elem = elem;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void setIzquierdo(NodoAVL izquierdo) {
        this.izquierdo = izquierdo;
    }

    public void setDerecho(NodoAVL derecho) {
        this.derecho = derecho;
    }



}
