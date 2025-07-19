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

    // Formato: Nomenclatura ciudad desde-Nomenclaturaciudad hasta, por ejemplo: si
    // las ciudades son Neufuén y Cupral-Có sería NE3001-CC3002
    private String nomenclatura;
    private int caudalMinimo;
    private int caudalMaximo;
    private int diametroTuberia;
    private String estado;

    public Tuberia() {
    }

    public Tuberia(String nomen, int cMax, int cMin, int diamTub, String est) {
        this.nomenclatura = nomen;
        this.caudalMaximo = cMax;
        this.caudalMinimo = cMin;
        this.diametroTuberia = diamTub;
        this.estado = est;
    }

    // Observadores
    public int getCaudalMaximo() {
        return this.caudalMaximo;
    }

    public int getCaudalMinimo() {
        return this.caudalMinimo;
    }

    public int getDiametroTuberia() {
        return this.diametroTuberia;
    }

    public String getEstado() {
        return this.estado;
    }

    public String getNomenclatura() {
        return this.nomenclatura;
    }

    public String getCiudadOrigen() {

        String [] split = nomenclatura.split("-");
        
        return split[0];

    }

    public String getCiudadDestino() {

        String [] split = nomenclatura.split("-");
        
        return split[1];

    }

    // Modificadores
    public void setCaudalMinimo(int caudalMinimo) {
        this.caudalMinimo = caudalMinimo;
    }

    public void setCaudalMaximo(int caudalMaximo) {
        this.caudalMaximo = caudalMaximo;
    }

    public void setDiametroTuberia(int diametroTuberia) {
        this.diametroTuberia = diametroTuberia;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String toString() {
        return "Tubería [" +
                "Nomenclatura: " + this.nomenclatura +
                ", Caudal Mín: " + this.caudalMinimo + " m³/h" +
                ", Caudal Máx: " + this.caudalMaximo + " m³/h" +
                ", Diámetro: " + this.diametroTuberia + " mm" +
                ", Estado: " + this.estado +
                "]";
    }
}