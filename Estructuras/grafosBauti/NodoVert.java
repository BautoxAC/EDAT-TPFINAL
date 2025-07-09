package Estructuras.grafosBauti;
public class NodoVert {
    private Object elem;
    private NodoVert sigVertice;
    private NodoAdy primerAdy;

    public NodoVert(Object el, NodoVert sig, NodoAdy ady) {
        this.elem = el;
        this.sigVertice = sig;
        this.primerAdy = ady;
    }

    public Object getElem() {
        return this.elem;
    }
    public NodoVert getSigVertice() {
        return this.sigVertice;
    }
    public NodoAdy getPrimerAdy() {
        return this.primerAdy;
    }

    public void setElem(Object el){
        this.elem = el;
    }
    public void setSigVertice(NodoVert sig){
        this.sigVertice = sig;
    }
    public void setPrimerAdy( NodoAdy ady){
        this.primerAdy = ady;
    }

}
