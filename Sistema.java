import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Estructuras.*;
import Estructuras.ArbolAVL.ArbolAVL;
import Estructuras.Grafos.*;

public class Sistema {

    private Grafo mapaCiudades;
    private ArbolAVL ciudades;
    private FileWriter logger;
    private Map<ParNomen,Tuberia> ciudadTuberia = new HashMap<>();

    public Sistema() {

        mapaCiudades = new Grafo();
        ciudades = new ArbolAVL();

        try {
            logger = new FileWriter("sistema.log", true); // append mode
            // log("Sistema inicializado");
        } catch (IOException e) {
            System.err.println("Error al abrir el archivo de log: " + e.getMessage());
        }

    }

    public void menuPrincipal() {
        Scanner scanner = new Scanner(System.in);
        String opcion;
        do {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Gestionar ciudades");
            System.out.println("2. Gestionar tuberías");
            System.out.println("3. Alta de transmisión de agua");
            System.out.println("4. Consultas sobre ciudades");
            System.out.println("5. Consultas sobre transporte de agua");
            System.out.println("6. Listado de consumo anual por ciudades");
            System.out.println("7. Mostrar sistema (debugging)");
            System.out.println("8. Salir");
            System.out.print("Opcion elegida: ");
            opcion = scanner.nextLine();
            switch (opcion) {
                case "1":
                    menuCiudades(scanner);
                    break;
                case "2":
                    // menuTuberias(scanner);
                    break;
                case "3":
                    // altaTransmision(scanner);
                    break;
                case "4":
                    // menuConsultasCiudades(scanner);
                    break;
                case "5":
                    // menuConsultasTransporte(scanner);
                    break;
                case "6":
                    // listadoConsumoAnual(scanner);
                    break;
                case "7":
                    // mostrarSistema();
                    break;
                case "8":
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (!opcion.equals("8"));
        // Al salir, cerrar el logger
        try {
            if (logger != null) {
                logger.close();
            }
        } catch (IOException e) {
            System.err.println("Error al cerrar el logger: " + e.getMessage());
        }
        scanner.close();
    }

    private void menuCiudades(Scanner scanner) {

        String opcion;

        do {

            System.out.println("\n--- GESTIÓN DE CIUDADES ---");
            System.out.println("1. Agregar ciudad");
            System.out.println("2. Eliminar ciudad");
            System.out.println("3. Modificar ciudad");
            System.out.println("4. Volver");
            System.out.print("Opcion elegida: ");

            opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    ingresarCiudad(scanner);
                    break;
                case "2":
                    // eliminarCiudad(scanner);
                    break;
                case "3":
                    // modificarCiudad(scanner);
                    break;
                case "4":
                    System.out.println("Volviendo al menu anterior...");
                    break;
                default:
                    System.out.println("ERROR");
                    break;
            }

        } while (!opcion.equals("4"));

    }

    private void ingresarCiudad(Scanner scanner) {
        System.out.println("\n--- NUEVA CIUDAD ---");

        Ciudad nuevaCiudad;
        String nombre;
        String nomenclatura;
        String superficie;
        String cantMetrosCubicos;
        boolean valido = false;
        Ciudad ciudad;
        int[][] matriz;
        int j;
        String valorActual;

        System.out.println("Ingrese el nombre de la ciudad");

        nombre = scanner.nextLine();


        if (!ciudades.pertenece(nombre)) {

            do {

                System.out.println("Ingrese la nomenclatura");
                System.out.println("0 - Para salir");

                // VERIFICAR QUE LA NOMENCLATURA SEA IGUAL A LOS ESPACIOS DEL NOMBRE DE LA CIUDAD O LAS DOS PRIMERAS LETRAS DE LA CIUDAD

                nomenclatura = scanner.nextLine();

                if (esNomenclaturaValida(nomenclatura)) {
                    valido = true;
                }

            } while (!valido && !nomenclatura.equals("0"));

            if (valido) {

                valido = false;

                System.out.println("Ingrese la superficie");
                System.out.println("0 - Para salir");

                do {

                    superficie = scanner.nextLine();

                    if (esNumero(superficie) && !superficie.equals("0")) {
                        valido = true;
                    }

                } while (!valido && !superficie.equals("0"));

                if (valido) {

                    valido = false;

                    System.out.println("Ingrese la cantidad de metros cubicos promedio");
                    System.out.println("0 - Para salir");

                    do {

                        cantMetrosCubicos = scanner.nextLine();

                        if (esNumero(cantMetrosCubicos) && !cantMetrosCubicos.equals("0")) {
                            valido = true;
                        }

                    } while (!valido && !cantMetrosCubicos.equals("0"));

                    if (valido) {

                        matriz = new int[10][12];
                        j = 0;

                        for (int i = 0; i < matriz.length; i++) {

                            while (j < matriz[0].length) {

                                System.out.println("Ingrese un valor para los habitantes del año " + (2015 + i)
                                        + " del mes " + numeroAMes(j));

                                valorActual = scanner.nextLine();

                                if (esNumero(valorActual)) {
                                    matriz[i][j] = Integer.parseInt(valorActual);
                                    j++;
                                } else {

                                    System.out.println("Porfavor ingrese un numero valido");

                                }

                            }

                        }

                        agregarCiudad(nombre, matriz, nomenclatura, Integer.parseInt(superficie),
                                Integer.parseInt(cantMetrosCubicos));

                    }

                }

            }

        }else{
            System.out.println("Error, ya existe esa ciudad");
        }

        // logger.registrar("Ciudad agregada: " + ciudad.getNombre());
    }







    
    private void agregarCiudad(String nombre, int[][] matriz, String nomenclatura, int superficie,
            int cantMetrosCubicos) {

    }






    //extras
    private boolean esNomenclaturaValida(String nomenclatura) {

        boolean valido = true;
        int i = 0;

        if (nomenclatura.length() == 6) {

            while (valido && i < nomenclatura.length()) {

                if (i <= 1) {
                    if (!esMayuscula(nomenclatura.charAt(i))) {
                        valido = false;
                    }
                } else if (i > 1) {
                    if (!esNumero(nomenclatura.charAt(i))) {
                        valido = false;
                    }
                }

                i++;

            }

        } else {
            valido = false;
        }

        return valido;

    }

    private boolean esMayuscula(char letra) {

        return letra >= 65 && letra <= 90;

    }

    private boolean esNumero(char letra) {

        return letra >= 48 && letra <= 57;

    }

    private boolean esNumero(String palabra) {

        boolean valido = true;
        int i = 0;

        while (valido && i < palabra.length()) {

            if (!esNumero(palabra.charAt(i))) {
                valido = false;
            }

        }

        return valido;

    }

    private String numeroAMes(int numero) {

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

}
