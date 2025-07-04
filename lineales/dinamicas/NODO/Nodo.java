/************* Autores ***********
Tomas Ismael Cárdenas Maldonado - FAI-4884
Bautista Tosi: Juan Bautista Tosi Griedassov- FAI-5162
Santiago Fuentes: Santiago Emanuel Fuentes FAI  - [5383]
Santiago Fuentes: PASCAL RIOS, IVO JAVIER, FAI-5200
*/

package lineales.dinamicas.NODO;
public class Nodo {
    private Object elem;
    private Nodo enlace;
    // constructor
    public Nodo(Object elem, Nodo enlace){
        this.elem = elem;
        this.enlace = enlace;
    }
    // modificadores
    public void setElem(Object elem){
        this.elem = elem;
    }

    public void setEnlace(Nodo enlace){
        this.enlace = enlace;
    }

    // Observadores
    public Object getElem(){
        return this.elem;
    }
    public Nodo getEnlace(){
        return this.enlace;
    }

}
