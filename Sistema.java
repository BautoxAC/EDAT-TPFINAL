package TrabajoFinal;
import TrabajoFinal.Estructuras.*;
import TrabajoFinal.Estructuras.Grafos.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Sistema {

    private Grafo mapaCiudades;
    private ArbolAVL ciudades;
    private FileWriter logger;

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
                    // agregarCiudad(scanner);
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

    private void agregarCiudad(Scanner scanner) {
        System.out.println("\n--- NUEVA CIUDAD ---");
        
        Ciudad nuevaCiudad;
        String nombre;
        boolean pertenece = false;

        System.out.println("Ingrese el nombre de la ciudad");

        nombre = scanner.nextLine();

        if (ciudades.pertenece(nombre)) {

            System.out.println("Error, ya existe esa ciudad");
            pertenece = true;

        }

        if (!pertenece) {

            System.out.println("Ingrese la nomenclatura");

            

        }
        

        //logger.registrar("Ciudad agregada: " + ciudad.getNombre());
    }

}
