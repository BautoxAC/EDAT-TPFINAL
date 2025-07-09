package Estructuras.Especificas.Diccionario;

public class NodoAVLDicc {
    
    private Object clave;
    private Object dato;
    private int altura;
    private NodoAVLDicc hijoIzquierdo;
    private NodoAVLDicc hijoDerecho;

    public NodoAVLDicc (Object clave, Object dato) {

        this.clave = clave;
        this.dato = dato;
        this.altura = 0;
        this.hijoIzquierdo = null;
        this.hijoDerecho = null;

    }

    public Object getClave() {
        return this.clave;
    }

    public Object getDato() {
        return this.dato;
    }

    public int getAltura() {
        return this.altura;
    }

    public NodoAVLDicc getHijoIzquierdo() {
        return this.hijoIzquierdo;
    }

    public NodoAVLDicc getHijoDerecho() {
        return this.hijoDerecho;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void setHijoIzquierdo(NodoAVLDicc hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

    public void setHijoDerecho(NodoAVLDicc hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }

    public void recalcularAltura() {
        
        int alturaHI = -1;
        int alturaHD = -1;

        if (this.hijoIzquierdo != null) {
            alturaHI = this.hijoIzquierdo.getAltura();
        }
        if (this.hijoDerecho != null) {
            alturaHD = this.hijoDerecho.getAltura();
        }

        this.altura = Math.max(alturaHI,alturaHD) + 1;

        //System.out.println("nodo con elem "+elem+" tiene altura "+this.altura);

    }





}
