package TrabajoFinal;
/*
De las tuberías se guarda la siguiente información:
Nomenclatura
Formato: Nomenclatura ciudad desde-Nomenclaturaciudad hasta, por ejemplo: si las ciudades son Neufuén y Cupral-Có sería NE3001-CC3002
Caudal mínimo en metros cúbicos por hora
Caudal máximo en metros cúbicos por hora
Diámetro de la tubería en milímetros
Estado: ACTIVO, EN REPARACIÓN, EN DISEÑO, INACTIVO.
*/

public class Tuberia {

    private String nomenclatura;
    private int caudalMinimo;
    private int caudalMaximo;
    private int diametroTuberia;
    private String estado;
    
    public Tuberia(String nomen,int cMax,int cMin, int diamTub, String est){
        nomenclatura=nomen;
        caudalMaximo=cMax;
        caudalMinimo=cMin;
        diametroTuberia=diamTub;
        estado=est;
    }

    //Observadores
    public int getCaudalMaximo() {
        return caudalMaximo;
    }
    public int getCaudalMinimo() {
        return caudalMinimo;
    }
    public int getDiametroTuberia() {
        return diametroTuberia;
    }
    public String getEstado() {
        return estado;
    }
    public String getNomenclatura() {
        return nomenclatura;
    }

    //Modificadores
    public void setCaudalMinimo(int caudalMinimo) {
        this.caudalMinimo = caudalMinimo;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    //Formato: Nomenclatura ciudad desde-Nomenclaturaciudad hasta, por ejemplo: si las ciudades son Neufuén y Cupral-Có sería NE3001-CC3002

}