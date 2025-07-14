//Esta clase existe para tener metodos generales de uso para toda la aplicacion

public class Auxiliares {

    public static boolean esNumero(String palabra) {

        boolean valido = true;
        int i = 0;

        if (palabra.length() > 0) {
            while (valido && i < palabra.length()) {

                if (palabra.charAt(i) == '0' && palabra.length() > 1) {
                    valido = false;
                } else if (!esNumero(palabra.charAt(i))) {
                    valido = false;
                }
                i++;
            }
        } else {
            valido = false;
        }

        return valido;

    }

    public static String numeroAMes(int numero) {

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

    public static boolean esNumero(char letra) {

        return letra >= 48 && letra <= 57;

    }

    public static boolean esMayuscula(char letra) {

        return letra >= 65 && letra <= 90;

    }

    public static int mesANumero(String mes) {
        int elegido;
        mes = mes.toLowerCase();
        switch (mes) {
            case "enero":
                elegido = 0;
                break;
            case "febrero":
                elegido = 1;
                break;
            case "marzo":
                elegido = 2;
                break;
            case "abril":
                elegido = 3;
                break;
            case "mayo":
                elegido = 4;
                break;
            case "junio":
                elegido = 5;
                break;
            case "julio":
                elegido = 6;
                break;
            case "agosto":
                elegido = 7;
                break;
            case "septiembre":
                elegido = 8;
                break;
            case "octubre":
                elegido = 9;
                break;
            case "noviembre":
                elegido = 10;
                break;
            case "diciembre":
                elegido = 11;
                break;
            default:
                elegido = -1;
                break;
        }

        return elegido;

    }

    public static String devolverNumeroValido(String palabra) {

        String numero = "";
        int i;
        boolean numEncontrado;

        if (palabra.charAt(0) == '0' && palabra.length() > 1) {
            i = 1;
            numEncontrado = false;
            while (i < palabra.length()) {
                if (palabra.charAt(i) != '0' && !numEncontrado) {
                    numEncontrado = true;
                }
                if (numEncontrado) {
                    numero += palabra.charAt(i);
                }
                i++;
            }
            if (numero.length() == 0) {
                numero = "0";
            }
        } else {
            numero = palabra;
        }

        return numero;

    }

}
