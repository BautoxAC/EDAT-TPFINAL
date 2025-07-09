
public class Ciudad {
    // variables
    private String nombre;
    // es una matriz de enteros sobre la cantidad de habitantes
    private int[][] cantHabitantes;
    private String nomenclatura;
    private int superficie;
    private int cantConsumo;
    // constructor
    public Ciudad(String nombre, int[][] cantHabitantes, String nomenclatura, int superficie, int cantConsumo) {
        this.nombre = nombre;
        this.cantHabitantes = cantHabitantes;
        this.nomenclatura = nomenclatura;
        this.superficie = superficie;
        this.cantConsumo = cantConsumo;
    }
    // visualizadores
    public String getNombre(){
        return this.nombre;
    }
    public String getNomenclatura(){
        return this.nomenclatura;
    }
    public int getSuperficie(){
        return this.superficie;
    }
    public int getCantConsumo(){
        return this.cantConsumo;
    }

    public int getHabitantesAnio(int anio){
        int cantidadHab = -1;
        int i = 0;
        while () {
            
        }
        return cantidadHab;
    }

    // modificadores

    public void setNombre(String nombre){
         this.nombre = nombre ;
    }
    public void setNomenclatura(String nomenclatura){
         this.nomenclatura = nomenclatura ;
    }
    public void setSuperficie(int superficie){
         this.superficie = superficie ;
    }
    public void setCantConsumo(int cantConsumo){
         this.cantConsumo = cantConsumo ;
    }


}
