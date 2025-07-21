
public class Ciudad {
    // variables
    private String nombre;
    // es una matriz de enteros sobre la cantidad de habitantes
    private int[][] cantHabitantes = new int[10][12];
    private String nomenclatura;
    private int superficie;
    private int cantConsumo;

    // constructor
    public Ciudad(String nombre, String nomenclatura, int superficie, int cantConsumo) {
        this.nombre = nombre;
        this.nomenclatura = nomenclatura;
        this.superficie = superficie;
        this.cantConsumo = cantConsumo;
    }

    // visualizadores
    public String getNombre() {
        return this.nombre;
    }

    public String getNomenclatura() {
        return this.nomenclatura;
    }

    public int getSuperficie() {
        return this.superficie;
    }

    public int getCantConsumo() {
        return this.cantConsumo;
    }

    public int getHabitantesAnio(int anio) {
        int cantidadHab = -1;
        if (anio != -1) {
            cantidadHab = 0;
            for (int j = 0; j < cantHabitantes[anio].length; j++) {
                if (cantHabitantes[anio][j] != 0) {
                    cantidadHab += cantHabitantes[anio][j];
                }
            }
        }

        return cantidadHab;
    }

    public int getHabitantesAnioMes(int anio, int mes) {
        int cantidadHab = -1;
        
        if (anio >= 0 && anio <= 9 && mes >= 0 && mes <= 11) {
            cantidadHab = cantHabitantes[anio][mes];
        }

        return cantidadHab;
    }

    public int getConsumoAnual(int anio) {
        int consumoAnual = 0;
        for (int i = 0; i < cantHabitantes[anio].length; i++) {
            consumoAnual += (cantHabitantes[anio][i] * this.cantConsumo);
        }
        return consumoAnual;
    }

    // modificadores

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNomenclatura(String nomenclatura) {
        this.nomenclatura = nomenclatura;
    }

    public void setSuperficie(int superficie) {
        this.superficie = superficie;
    }

    public void setCantConsumo(int cantConsumo) {
        this.cantConsumo = cantConsumo;
    }

    public boolean setHabitantesMatriz(int[][] hab) {
        boolean agregado = false;

        if (hab.length == 10 && hab[0].length == 12) {
            this.cantHabitantes = hab;
            agregado = true;
        }
        return agregado;
    }

    public boolean setHabitantesAnioMes(int anio, int mes, int cant) {
        boolean agregado = false;
        if (anio >= 0 && anio <= 9 && mes >= 0 && mes <= 11) {
            cantHabitantes[anio][mes] = cant;
        }
        return agregado;
    }
}
