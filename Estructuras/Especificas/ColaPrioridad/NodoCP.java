package Estructuras.Especificas.ColaPrioridad;

import Estructuras.lineales.*;

public class NodoCP {

    int prioridad;
    Cola items;
    NodoCP enlace;

    public NodoCP (int prioridad, Cola items, NodoCP enlace) {

        this.prioridad = prioridad;
        this.items = items;
        this.enlace = enlace;

    }

    public int getPrioridad() {
        return this.prioridad;
    }

    public Cola getItems() {
        return this.items;
    }

    public NodoCP getEnlace() {
        return this.enlace;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public void setItems(Cola items) {
        this.items = items;
    }

    public void setEnlace(NodoCP enlace) {
        this.enlace = enlace;
    }

}
