public class NodoVer {
    private Object elem;
    private NodoVer sigVertice;
    private NodoAdy primerAdy;

    public NodoVer(Object el, NodoVer sig, NodoAdy ady) {
        this.elem = el;
        this.sigVertice = sig;
        this.primerAdy = ady;
    }

    public Object getElem() {
        return this.elem;
    }
    public NodoVer getSigVertice() {
        return this.sigVertice;
    }
    public NodoAdy getPrimerAdy() {
        return this.primerAdy;
    }

    public void setElem(Object el){
        this.elem = el;
    }
    public void setSigVertice(NodoVer sig){
        this.sigVertice = sig;
    }
    public void setPrimerAdy( NodoAdy ady){
        this.primerAdy = ady;
    }

}
