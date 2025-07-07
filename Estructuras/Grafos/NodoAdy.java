package TrabajoFinal.Estructuras.Grafos;

public class NodoAdy {
    
    NodoVert vertice;
    NodoAdy sigAdyacente;
    Object etiqueta;

    public NodoAdy(NodoVert verticie, NodoAdy sigAdyacente, Object etiqueta) {
        this.vertice = verticie;
        this.sigAdyacente = sigAdyacente;
        this.etiqueta = etiqueta;
    }

    public NodoVert getVertice() {
        return this.vertice;
    }

    public NodoAdy getSigAdyacente() {
        return this.sigAdyacente;
    }

    public Object getEtiqueta() {
        return this.etiqueta;
    }

    public void setVertice(NodoVert vertice) {
        this.vertice = vertice;
    }

    public void setSigAdyacente(NodoAdy sigAdyacente) {
        this.sigAdyacente = sigAdyacente;
    }

    public void setEtiqueta(Object etiqueta) {
        this.etiqueta = etiqueta;
    }

}
