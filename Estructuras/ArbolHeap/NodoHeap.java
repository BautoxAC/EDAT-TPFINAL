package Estructuras.ArbolHeap;

public class NodoHeap {
    
    private Comparable clave;
    private Object dato;

    public NodoHeap (Comparable clave, Object dato) {
        this.clave = clave;
        this.dato = dato;

    }

    public Comparable getClave() {
        return this.clave;
    }

    public Object getDato() {
        return this.dato;
    }

    public void setClave(Comparable clave) {
        this.clave = clave;
    }

    public void setDato(Object dato) {
        this.dato = dato;
    }

}
