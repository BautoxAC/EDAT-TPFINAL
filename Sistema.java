import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Estructuras.*;
import Estructuras.Especificas.Diccionario.*;
import Estructuras.Grafos.*;

public class Sistema {

    private Grafo mapaCiudades;
    private Diccionario ciudades;
    private FileWriter logger;
    private Map<ParNomen, Tuberia> hashMapCiudadTuberia = new HashMap<>();
    private Auxiliares aux;

    public Sistema() {

        mapaCiudades = new Grafo();
        ciudades = new Diccionario();

        try {
            // escribe lineas
            FileWriter writer = new FileWriter("sistema.log", false); // true = append y no borra lo anteriormente
                                                                      // escrito
            writer.write("Este texto se agrega al final.\n");
            writer.write("Este texto se agrega al final.\n");
            writer.close();
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
                    menuTuberias(scanner);
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

    private void menuTuberias(Scanner scanner) {
        String opcion;

        do {

            System.out.println("\n--- GESTIÓN DE TUBERIAS ---");
            System.out.println("1. Agregar tuberia");
            System.out.println("2. Eliminar tuberia");
            System.out.println("3. Modificar tuberia");
            System.out.println("4. Volver");
            System.out.print("Opcion elegida: ");

            opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    ingresarTuberia(scanner);
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

    private void ingresarTuberia(Scanner scanner) {
        System.out.println("\n--- NUEVA TUBERIA ---");

        String ciuNombreSalida;
        String ciuNombreEntrada;

        String tuberiaNomen;
        int caudalMinimo;
        int caudalMaximo;
        int diametroTuberia;
        String estado;

        System.out.println("Ingrese el nombre de la ciudad de salida del agua: ");

        ciuNombreSalida = scanner.nextLine();

        if (!ciudades.existeClave(ciuNombreSalida)) {

            System.out.println("Ingrese el nombre de la ciudad de entrada del agua: ");

            ciuNombreEntrada = scanner.nextLine();

            if (!ciudades.existeClave(ciuNombreEntrada)) {

                tuberiaNomen = ciuNombreSalida + "-" + ciuNombreEntrada;

                if (!hashMapCiudadTuberia.containsValue(tuberiaNomen)) {

                    System.out.println("Ingrese el caudal minimo de la tuberia: ");

                    caudalMinimo = scanner.nextInt();

                    System.out.println("Ingrese el caudal maximo de la tuberia: ");

                    caudalMaximo = scanner.nextInt();

                    System.out.println("Ingrese el diametro de la tuberia de la tuberia: ");

                    diametroTuberia = scanner.nextInt();

                    System.out.println("Ingrese el Estado en el que se encuntra la tuberia: ");

                    estado = scanner.nextLine();

                    Tuberia newTuberia = new Tuberia(tuberiaNomen, caudalMaximo, caudalMinimo, diametroTuberia, estado);
                    ParNomen parNomeclatura = new ParNomen(ciuNombreSalida, ciuNombreEntrada);

                    hashMapCiudadTuberia.put(parNomeclatura, newTuberia);

                    if (hashMapCiudadTuberia.containsValue(newTuberia)) {
                        System.out.println("Se agrego con exito la tuberia: " + tuberiaNomen);
                    } else {
                        System.out.println("NO SE AGREGO LA tuberia: " + tuberiaNomen);
                    }

                } else {
                    System.out.println("Ya existe la tuberia: " + tuberiaNomen);
                }

            } else {
                System.out.println("No existe esta ciudad: " + ciuNombreEntrada);
            }

        } else {
            System.out.println("No existe esta ciudad: " + ciuNombreSalida);
        }

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
                    eliminarCiudad(scanner);
                    break;
                case "3":
                    modificarCiudad(scanner);
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

        String nombre;
        String nomenclatura;
        String superficie;
        String cantMetrosCubicos;
        boolean pertenece = false;
        boolean valido = false;
        int[][] matriz;
        int j;
        String valorActual;

        System.out.println("Ingrese el nombre de la ciudad");

        nombre = scanner.nextLine();

        if (ciudades.pertenece(nombre)) {

            System.out.println("Error, ya existe esa ciudad");
            pertenece = true;

        }

        if (!pertenece) {

            do {

                System.out.println("Ingrese la nomenclatura");
                System.out.println("0 - Para salir");

                // VERIFICA QUE LA NOMENCLATURA SEA IGUAL A LOS ESPACIOS DEL NOMBRE DE LA
                // CIUDAD O LAS DOS PRIMERAS LETRAS DE LA CIUDAD

                nomenclatura = scanner.nextLine();

                if (esNomenclaturaValida(nomenclatura) && verificarNombreNomenclatura(nombre, nomenclatura)) {
                    valido = true;
                }

            } while (!valido && !nomenclatura.equals("0"));

            if (valido) {

                valido = false;

                System.out.println("Ingrese la superficie");
                System.out.println("0 - Para salir");

                do {

                    superficie = scanner.nextLine();

                    if (aux.esNumero(superficie) && !superficie.equals("0")) {
                        valido = true;
                    }

                    System.out.println("AA");

                } while (!valido && !superficie.equals("0"));

                if (valido) {

                    valido = false;

                    System.out.println("Ingrese la cantidad de metros cubicos promedio");
                    System.out.println("0 - Para salir");

                    do {

                        cantMetrosCubicos = scanner.nextLine();

                        if (aux.esNumero(cantMetrosCubicos) && !cantMetrosCubicos.equals("0")) {
                            valido = true;
                        }

                    } while (!valido && !cantMetrosCubicos.equals("0"));

                    if (valido) {

                        matriz = new int[10][12];
                        j = 0;

                        for (int i = 0; i < matriz.length; i++) {

                            while (j < matriz[0].length) {

                                System.out.println("Ingrese un valor para los habitantes del año " + (2015 + i)
                                        + " del mes " + aux.numeroAMes(j));

                                valorActual = scanner.nextLine();

                                if (aux.esNumero(valorActual)) {
                                    matriz[i][j] = Integer.parseInt(valorActual);
                                    j++;
                                } else {

                                    System.out.println("Porfavor ingrese un numero valido");

                                }

                            }

                            // j = 0;

                        }

                        agregarCiudad(nombre, matriz, nomenclatura, Integer.parseInt(superficie),
                                Integer.parseInt(cantMetrosCubicos));

                    }

                }

            }

        }

        // logger.registrar("Ciudad agregada: " + ciudad.getNombre());
    }

    private void eliminarCiudad(Scanner scanner) {
        System.out.println("\n--- ELIMINAR CIUDAD ---");

        boolean ciudadEliminada;
        String nombre;
        String log;

        System.out.println("Ingrese el nombre de la ciudad");

        nombre = scanner.nextLine();

        ciudadEliminada = ciudades.eliminar(nombre);

        if (ciudadEliminada) {

            log = "Ciudad " + nombre + " fue eliminada con exito";
            System.out.println(log);

        } else {

            log = "Error, la ciudad " + nombre + " no existe";
            System.out.println(log);

        }

    }

    private void modificarCiudad(Scanner scanner) {

        String opcion;
        Ciudad ciudadElegida = null;

        do {

            System.out.println("\n--- MODIFICAR CIUDADES ---");
            System.out.println("1. Elegir ciudad");
            System.out.println("2. Modificar cantidad habitantes en un mes");
            System.out.println("3. Modificar cantidad habitantes en un año");
            System.out.println("4. Modificar superficie");
            System.out.println("5. Modificar cantidad de consumo");
            System.out.println("6. Salir");
            if (ciudadElegida != null) {
                System.out.println("Ciudad elegida: " + ciudadElegida.getNombre());
            } else {
                System.out.println("No hay una ciudad seleccionada");
            }
            System.out.print("Opcion elegida: ");

            opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    ciudadElegida = elegirCiudad(scanner);
                    break;
                case "2":
                    modificarCantHabitantesMes(scanner, ciudadElegida);
                    break;
                case "3":
                    modificarCantHabitantesAnio(scanner, ciudadElegida);
                    break;
                case "4":
                    modificarSuperficie(scanner, ciudadElegida);
                    break;
                case "5":
                    modificarCantConsumo(scanner, ciudadElegida);
                    break;
                case "6":
                    System.out.println("Volviendo al menu anterior...");
                    break;
                default:
                    System.out.println("ERROR");
                    break;
            }

        } while (!opcion.equals("6"));

    }

    private Ciudad elegirCiudad(Scanner scanner) {
        System.out.println("\n--- ELEGIR CIUDAD ---");

        Ciudad ciudadElegida = null;
        String log;
        String nombre;

        System.out.println("Ingrese el nombre de la ciudad a elegir");

        nombre = scanner.nextLine();
        ciudadElegida = (Ciudad) ciudades.obtenerDato(nombre);

        if (ciudadElegida != null) {

            log = "Ciudad " + nombre + " fue seleccionada";
            System.out.println(log);

        } else {

            log = "Error, la ciudad " + nombre + " no existe";
            System.out.println(log);

        }

        return ciudadElegida;

    }

    private void modificarCantHabitantesMes(Scanner scanner, Ciudad ciudad) {

        String log;
        String cant;
        String mes;
        int anio;

        if (ciudad != null) {

            System.out.println("Ingrese el año del mes que quiera cambiar");

            anio = scanner.nextInt();
            scanner.nextLine();

            if (traducirAnio(anio) != -1) {

                System.out.println("Ingrese el mes que quiere cambiar");

                mes = scanner.nextLine();

                if (aux.mesANumero(mes) != -1) {
                    System.out.println("Ingrese la nueva cantidad de habitantes");

                    cant = scanner.nextLine();

                    if (aux.esNumero(cant)) {

                        log = "Cantidad actualizada con exito";
                        System.out.println(log);

                    } else {

                        log = "Error, no se pudo completar la operacion, cantidad no valida";
                        System.out.println(log);

                    }
                } else {
                    log = "Error, no se pudo completar la operacion, ";
                    System.out.println(log);
                }

            } else {
                log = "Error, no se pudo completar la operacion, año no valido, debe ser entre 2015-2024";
                System.out.println(log);
            }

        } else {
            System.out.println("ERROR: No hay una ciudad seleccionada");
        }

    }

    private void modificarSuperficie(Scanner scanner, Ciudad ciudad) {

        String log;
        String cant;

        if (ciudad != null) {

            System.out.println("Ingrese la nueva superficie");

            cant = scanner.nextLine();

            if (aux.esNumero(cant)) {

                log = "Superficie actualizada con exito";
                System.out.println(log);

                ciudad.setSuperficie(Integer.parseInt(cant));

            } else {

                log = "Error, no se pudo completar la operacion, numero no valido";
                System.out.println(log);

            }

        } else {
            System.out.println("ERROR: No hay una ciudad seleccionada");
        }

    }

    private void modificarCantConsumo(Scanner scanner, Ciudad ciudad) {

        String log;
        String cant;

        if (ciudad != null) {

            System.out.println("Ingrese la nueva cantidad de consumo");

            cant = scanner.nextLine();

            if (aux.esNumero(cant)) {

                log = "Consumo actualizado con exito";
                System.out.println(log);

                ciudad.setCantConsumo(Integer.parseInt(cant));

            } else {

                log = "Error, no se pudo completar la operacion, numero no valido";
                System.out.println(log);

            }

        } else {
            System.out.println("ERROR: No hay una ciudad seleccionada");
        }

    }

    private void agregarCiudad(String nombre, int[][] matriz, String nomenclatura, int superficie,
            int cantMetrosCubicos) {

        // Ciudad ciudad = new Ciudad(nombre, matriz, nomenclatura, superficie,
        // cantMetrosCubicos);

        Ciudad ciudad = new Ciudad(nombre, nomenclatura, superficie, cantMetrosCubicos);

        Object[] par = { ciudad.getNombre(), ciudad };

        ciudades.insertar(par);

    }

    private boolean esNomenclaturaValida(String nomenclatura) {

        boolean valido = true;
        int i = 0;
        String numeroAux = "";
        int num;

        if (nomenclatura.length() == 6) {

            while (valido && i < nomenclatura.length()) {

                if (i <= 1) {
                    if (!aux.esMayuscula(nomenclatura.charAt(i))) {
                        valido = false;
                    }
                } else if (i > 1) {
                    if (!aux.esNumero(nomenclatura.charAt(i))) {
                        valido = false;
                    } else {
                        numeroAux = numeroAux + nomenclatura.charAt(i);
                    }
                }

                i++;

            }

        } else {
            valido = false;
        }

        if (valido) {
            num = Integer.parseInt(numeroAux);
            if (num < 3000 || num > 4000) {
                valido = false;
            }
        }

        return valido;

    }

    private boolean verificarNombreNomenclatura(String nombre, String nomenclatura) {

        String[] palabras = nombre.trim().split("\\s+");
        String nomenEsperada;

        if (palabras.length < 2) {

            nomenEsperada = palabras[0].substring(0, 2).toUpperCase();

        } else {
            nomenEsperada = (palabras[0].charAt(0) + "" + palabras[1].charAt(0)).toUpperCase();

        }

        return ((nomenEsperada.charAt(0) == nomenclatura.charAt(0))
                && (nomenEsperada.charAt(1) == nomenclatura.charAt(1)));

    }

    private int traducirAnio(int anio) {
        int anioRet = -1;
        if (anio >= 2015 && anio <= 2025) {
            anioRet = anio - 2015;
        }
        return anioRet;

    }

}
