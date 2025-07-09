public class NodoAdy {
    private NodoVer vertice;
    private NodoAdy sigAdyacente;
    private Object etiqueta;

    public NodoAdy(NodoVer verti, NodoAdy sig, Object eti) {
        this.vertice = verti;
        this.sigAdyacente = sig;
        this.etiqueta = eti;
    }

    public NodoVer getVertice() {
        return this.vertice;
    }

    public NodoAdy getSigAdyacente() {
        return this.sigAdyacente;
    }

    public Object getEtiqueta() {
        return this.etiqueta;
    }

    public void setVertice(NodoVer verti) {
        this.vertice = verti;
    }

    public void setSigAdyacente(NodoAdy sig) {
        this.sigAdyacente = sig;
    }

    public void setEtiqueta(Object eti) {
        this.etiqueta = eti;
    }
}
