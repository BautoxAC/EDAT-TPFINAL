

class CiudadConsumo {
    String nombreCiudad;
    int consumoAnual;

    public CiudadConsumo(String nombreCiudad, int consumoAnual) {
        this.nombreCiudad = nombreCiudad;
        this.consumoAnual = consumoAnual;
    }
    public int getConsumoAnual(){
        return this.consumoAnual;
    }
    public String getNombreCiudad(){
        return this.nombreCiudad;
    }
}