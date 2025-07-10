//Esta clase existe para tener metodos generales de uso para toda la aplicacion

public class Auxiliares {


    public Auxiliares(){
    }
    public boolean esNumero(String palabra) {

        boolean valido = true;
        int i = 0;

        while (valido && i < palabra.length()) {

            if (!esNumero(palabra.charAt(i))) {
                valido = false;
            }

        }

        return valido;

    }
    public String numeroAMes(int numero) {

        String elegido;

        switch (numero) {
            case 0:
                elegido = "Enero";
                break;
            case 1:
                elegido = "Febrero";
                break;
            case 2:
                elegido = "Marzo";
                break;
            case 3:
                elegido = "Abril";
                break;
            case 4:
                elegido = "Mayo";
                break;
            case 5:
                elegido = "Junio";
                break;
            case 6:
                elegido = "Julio";
                break;
            case 7:
                elegido = "Agosto";
                break;
            case 8:
                elegido = "Septiembre";
                break;
            case 9:
                elegido = "Octubre";
                break;
            case 10:
                elegido = "Noviembre";
                break;
            case 11:
                elegido = "Diciembre";
                break;
            default:
                elegido = "";
                break;
        }

        return elegido;

    }
    public boolean esNumero(char letra) {

        return letra >= 48 && letra <= 57;

    }
    public boolean esMayuscula(char letra) {

        return letra >= 65 && letra <= 90;

    }
}
