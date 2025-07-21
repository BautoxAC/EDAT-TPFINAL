import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;

import Estructuras.ArbolHeap.ArbolHeap;
import Estructuras.Especificas.Diccionario.*;
import Estructuras.Grafos.*;
import Estructuras.lineales.*;

public class Sistema {

    private Grafo mapaCiudades;
    private Diccionario ciudades;
    private Map<ParNomen, Tuberia> hashMapCiudadTuberia = new HashMap<>();

    public Sistema() {
        vaciarLog();
        mapaCiudades = new Grafo();
        ciudades = new Diccionario();
        leerArchivos("Ciudades");
        leerArchivos("Tuberias");
        escribirLog(mapaCiudades.toString());
        escribirLog(ciudades.toString());
        escribirLog(this.mapToString());

    }

    public void menuPrincipal() {

        Scanner scanner = new Scanner(System.in);
        String opcion;
        do {
            System.out.println("---\n MENÚ PRINCIPAL ---");
            System.out.println("1. Gestionar ciudades");
            System.out.println("2. Gestionar tuberías");
            System.out.println("3. Consultas sobre ciudades");
            System.out.println("4. Consultas sobre transporte de agua");
            System.out.println("5. Salir");
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
                    mostrarCiudad(scanner);
                    break;
                case "4":
                    menuConsultasTransporte(scanner);
                    break;
                case "5":
                    System.out.println("Saliendo del sistema...\n");
                    escribirLog("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (!opcion.equals("5"));
        escribirLog(mapaCiudades.toString());
        escribirLog(ciudades.toString());
        escribirLog(this.mapToString());
        scanner.close();
    }

    private void menuTuberias(Scanner scanner) {
        String opcion;

        do {

            System.out.println("\n GESTIÓN DE TUBERIAS ");
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
                    eliminarTuberia(scanner);
                    break;
                case "3":
                    modificarTuberia(scanner);
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

    private void eliminarTuberia(Scanner scanner) {

        String ciuNombreSalida;
        String ciuNombreLlegada;
        ParNomen parNomen;
        String nomenSalida;
        String nomenLlegada;
        /*
         * System.out.println("Ingrese la nomeclatura de la truberia a eliminar");
         * 
         * String nomenBuscada;
         * nomenBuscada = scanner.nextLine();
         * 
         * 
         * hashMapCiudadTuberia.entrySet().removeIf(entry >
         * entry.getValue().getNomenclatura().equals(nomenBuscada)
         * );
         */

        System.out.println("Ingrese el nombre de la ciudad de salida del agua: ");

        ciuNombreSalida = scanner.nextLine();

        if (ciudades.existeClave(ciuNombreSalida)) {

            System.out.println("Ingrese el nombre de la ciudad de llegada del agua: ");

            ciuNombreLlegada = scanner.nextLine();

            if (ciudades.existeClave(ciuNombreLlegada)) {
                nomenSalida = ((Ciudad) ciudades.obtenerDato(ciuNombreSalida)).getNomenclatura();
                nomenLlegada = ((Ciudad) ciudades.obtenerDato(ciuNombreLlegada)).getNomenclatura();

                parNomen = new ParNomen(nomenSalida, nomenLlegada);
                mapaCiudades.eliminarArco(nomenSalida, nomenLlegada);

                hashMapCiudadTuberia.remove(parNomen);
                escribirLog("Tuberia eliminada: "
                        + nomenSalida + "-"
                        + nomenLlegada);

            } else {
                escribirLog("No existe esta ciudad: " + ciuNombreLlegada);
            }

        } else {
            escribirLog("No existe esta ciudad: " + ciuNombreSalida);
        }
    }

    public String mapToString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Relaciones ParNomen => Tubería:\n");

        for (Map.Entry<ParNomen, Tuberia> entry : hashMapCiudadTuberia.entrySet()) {
            sb.append(entry.getKey().toString())
                    .append(" => ")
                    .append(entry.getValue().toString())
                    .append("\n");
        }

        return sb.toString();
    }

    private void ingresarTuberia(Scanner scanner) {
        System.out.println("\n NUEVA TUBERIA ");

        String ciuNombreSalida;
        String ciuNombreLlegada;

        String caudalMinimo;
        String caudalMaximo;
        String tuberiaSalidaNomen;
        String tuberiaLlegadaNomen;
        String diametroTuberia;
        String estado;
        Tuberia newTuberia = new Tuberia();
        String log = "";
        System.out.println("Ingrese el nombre de la ciudad de salida del agua: ");

        ciuNombreSalida = scanner.nextLine();

        if (ciudades.existeClave(ciuNombreSalida)) {

            System.out.println("Ingrese el nombre de la ciudad de llegada del agua: ");

            ciuNombreLlegada = scanner.nextLine();

            if (ciudades.existeClave(ciuNombreLlegada)) {
                if (!ciuNombreLlegada.equals(ciuNombreSalida)) {

                    tuberiaSalidaNomen = ((Ciudad) ciudades.obtenerDato(ciuNombreSalida))
                            .getNomenclatura();
                    tuberiaLlegadaNomen = ((Ciudad) ciudades.obtenerDato(ciuNombreLlegada))
                            .getNomenclatura();
                    do {

                        System.out.println("Ingrese el caudal minimo de la tuberia: ");
                        caudalMinimo = scanner.nextLine();

                    } while (!Auxiliares.esNumero(caudalMinimo));

                    do {
                        System.out.println("Ingrese el caudal maximo de la tuberia: ");

                        caudalMaximo = scanner.nextLine();

                    } while (!Auxiliares.esNumero(caudalMaximo));

                    do {
                        System.out.println("Ingrese el diametro de la tuberia de la tuberia: ");

                        diametroTuberia = scanner.nextLine();

                    } while (!Auxiliares.esNumero(diametroTuberia));
                    do {
                        System.out.println(
                                "Ingrese el Estado en el que se encuentra la tuberia: \n 0: ACTIVO \n 1: EN REPARACION \n 2: EN DISEÑO \n 3: INACTIVO");

                        estado = scanner.nextLine();

                        System.out.println(estado);
                        estado = numeroAEstado(estado);
                        System.out.println(estado);
                    } while (estado.equals("INCORRECTO"));

                    newTuberia = agregarTuberia(Integer.parseInt(caudalMaximo),
                            Integer.parseInt(caudalMinimo),
                            Integer.parseInt(diametroTuberia), estado, tuberiaSalidaNomen,
                            tuberiaLlegadaNomen);

                } else {
                    log = "No se puede ingresar una tuberia que va de una ciudad a si misma";
                }
            } else {
                log = "No existe esta ciudad: " + ciuNombreLlegada;
            }
        } else {
            log = "No existe esta ciudad: " + ciuNombreSalida;
        }
        escribirLog(log);

    }

    private Tuberia agregarTuberia(int caudalMaximo, int caudalMinimo, int diametroTuberia,
            String estado, String tuberiaSalidaNomen, String tuberiaLlegadaNomen) {

        Tuberia newTuberia = new Tuberia();
        ParNomen parNomeclatura = new ParNomen();

        String nomen = tuberiaSalidaNomen + "-" + tuberiaLlegadaNomen;
        newTuberia = new Tuberia(nomen, caudalMaximo, caudalMinimo,
                diametroTuberia, estado);

        String log = "";

        parNomeclatura = new ParNomen(tuberiaSalidaNomen, tuberiaLlegadaNomen);
        if (!hashMapCiudadTuberia.containsKey(parNomeclatura)) {

            mapaCiudades.insertarArco(tuberiaSalidaNomen, tuberiaLlegadaNomen, caudalMaximo);
            hashMapCiudadTuberia.put(parNomeclatura, newTuberia);

            log = "Se agrego con exito la tuberia: " + nomen;

        } else {
            log = "Ya existe la tuberia: " + nomen;

        }
        escribirLog(log);

        return newTuberia;
    }

    private void modificarTuberia(Scanner scanner) {

        String opcion;
        Ciudad ciudadElegida = null;
        Tuberia tuberiaElegida = new Tuberia();

        do {

            System.out.println("\n MODIFICAR TUBERIAS ");
            System.out.println("1. Elegir tuberia");
            System.out.println("2. Modificar caudal minimo");
            System.out.println("3. Modificar caudal maximo");
            System.out.println("4. Modificar diametro de la tuberia");
            System.out.println("5. Modificar el estado");
            System.out.println("6. Salir");
            if (ciudadElegida != null) {
                System.out.println("Tuberia elegida: " + ciudadElegida.getNombre());
            } else {
                System.out.println("No hay una tuberia seleccionada");
            }
            System.out.print("Opcion elegida: ");

            opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    tuberiaElegida = elegirTuberia(scanner);
                    break;
                case "2":
                    modificarCaudalMinimo(scanner, tuberiaElegida);
                    break;
                case "3":
                    modificarCaudalMaximo(scanner, tuberiaElegida);
                    break;
                case "4":
                    modificarDiametroTuberia(scanner, tuberiaElegida);
                    break;
                case "5":
                    modificarEstado(scanner, tuberiaElegida);
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

    private void modificarCaudalMinimo(Scanner scanner, Tuberia tuberiaElegida) {

        String log;
        String cant;

        if (tuberiaElegida != null) {

            System.out.println("Ingrese el nuevo caudal minimo");

            cant = scanner.nextLine();

            if (Auxiliares.esNumero(cant)) {
                log = "Caudal minimo actualizado con exito";
                tuberiaElegida.setCaudalMinimo(Integer.parseInt(cant));
            } else {
                log = "Error, no se pudo completar la operacion, numero no valido";
            }
        } else {
            log = "ERROR: No hay una tuberia seleccionada";
        }
        escribirLog(log);

    }

    private void modificarCaudalMaximo(Scanner scanner, Tuberia tuberiaElegida) {

        String log;
        String cant;

        if (tuberiaElegida != null) {

            System.out.println("Ingrese el nuevo caudal maximo");

            cant = scanner.nextLine();

            if (Auxiliares.esNumero(cant)) {
                log = "Caudal maximo actualizada con exito";
                tuberiaElegida.setCaudalMaximo(Integer.parseInt(cant));

                mapaCiudades.cambiarEtiqueta(tuberiaElegida.getCiudadOrigen(), tuberiaElegida.getCiudadDestino(),
                        Integer.parseInt(cant));

            } else {

                log = "Error, no se pudo completar la operacion, numero no valido";

            }

        } else {
            log = "ERROR: No hay una tuberia seleccionada";
        }

        escribirLog(log);
    }

    private void modificarDiametroTuberia(Scanner scanner, Tuberia tuberiaElegida) {

        String log;
        String cant;

        if (tuberiaElegida != null) {

            System.out.println("Ingrese el nuevo diametro de la tuberia");

            cant = scanner.nextLine();

            if (Auxiliares.esNumero(cant)) {

                log = "Diametro de la tuberia actualizada con exito";

                tuberiaElegida.setDiametroTuberia(Integer.parseInt(cant));

            } else {

                log = "Error, no se pudo completar la operacion, numero no valido";

            }

        } else {
            log = "ERROR: No hay una tuberia seleccionada";

        }
        escribirLog(log);

    }

    private void modificarEstado(Scanner scanner, Tuberia tuberiaElegida) {

        String log;
        String estado;

        if (tuberiaElegida != null) {

            System.out.println(
                    "Ingrese el Estado en el que se encuntra la tuberia: \n 0: ACTIVO \n 1: EN REPARACION \n 2: EN DISEÑO \n 3: INACTIVO");
            estado = scanner.nextLine();

            estado = numeroAEstado(estado);

            if (!estado.equals("INCORRECTO")) {

                log = "Estado de la tuberia actualizada con exito";

                tuberiaElegida.setEstado(estado);

            } else {

                log = "Error, no se pudo completar la operacion, estado no valido";

            }

        } else {

            log = "ERROR: No hay una tuberia seleccionada";

        }
        escribirLog(log);

    }

    private Tuberia elegirTuberia(Scanner scanner) {
        System.out.println("\n ELEGIR TUBERIA ");
        String ciuNombreSalida;
        String ciuNombreLlegada;
        String log = "";
        Tuberia tuberiaElegida = null;
        System.out.println("Ingrese el nombre de la ciudad de salida del agua: ");
        ciuNombreSalida = scanner.nextLine();

        if (!ciudades.existeClave(ciuNombreSalida)) {
            System.out.println("Ingrese el nombre de la ciudad de llegada del agua: ");

            ciuNombreLlegada = scanner.nextLine();

            if (!ciudades.existeClave(ciuNombreLlegada)) {

                tuberiaElegida = hashMapCiudadTuberia
                        .get(new ParNomen(((Ciudad) ciudades.obtenerDato(ciuNombreSalida)).getNomenclatura(),
                                ((Ciudad) ciudades.obtenerDato(ciuNombreLlegada)).getNomenclatura()));
                log = "tuberia seleccionada";

            } else {
                log = "No existe esta ciudad: " + ciuNombreLlegada;

            }

        } else {
            log = "No existe esta ciudad: " + ciuNombreSalida;

        }
        escribirLog(log);

        return tuberiaElegida;
    }

    private void menuCiudades(Scanner scanner) {

        String opcion;

        do {

            System.out.println("\n GESTIÓN DE CIUDADES ");
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

    private void mostrarCiudad(Scanner scanner) {

        String opcion;
        Ciudad ciudadElegida = null;

        do {

            System.out.println("1. Elegir ciudad");
            System.out.println("2. Mostrar habitantes");
            System.out.println("3. Mostrar volumen del agua");
            System.out.println("4. Mostrar el rango por nombre y volumen");
            System.out.println("5. Mostrar las ciudades ordenadas por consumo");
            System.out.println("6. Volver");
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
                    cantHabitates(scanner, ciudadElegida);
                    break;
                case "3":
                    volumenAgua(scanner, ciudadElegida);
                    break;
                case "4":
                    rangoNombreVolumen(scanner);
                    break;
                case "5":
                    ciudadesOrdenadasConsumo(scanner);
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

    private void ciudadesOrdenadasConsumo(Scanner scanner) {
        String anio;
        int anioInt;
        System.out.println("Ingrese el año desde 2015 al 2024 inclusive");
        do {
            anio = scanner.nextLine();
            anioInt = traducirAnio(Integer.parseInt(anio));
        } while (anioInt == -1);

        String log = "";
        if (anioInt != -1) {
            Lista listaConsumos = ciudades.listarDatos();
            int largo = listaConsumos.longitud();
            ArbolHeap heap = new ArbolHeap(largo);
            for (int i = 1; i <= largo; i++) {
                Ciudad CiudadX = (Ciudad) listaConsumos.recuperar(i);
                int consumo = CiudadX.getConsumoAnual(anioInt);
                heap.insertar(consumo, new CiudadConsumo(CiudadX.getNombre(), consumo));
            }
            Object[] arbolOrdenado = heap.ordenarArreglo();
            for (int i = 0; i < arbolOrdenado.length; i++) {
                CiudadConsumo ciudad = (CiudadConsumo) arbolOrdenado[i];
                log += "ciudad: " + ciudad.getNombreCiudad() + " con consumo: " + ciudad.getConsumoAnual() + "\n";
            }
        } else {
            log = "ERROR:  AÑO INGRESADO INCORRECTAMENTE DEBE SER ENTRE 2015 Y 2024";
        }
        escribirLog(log);
    }

    private void rangoNombreVolumen(Scanner scanner) {
        String minNomb, maxNomb;
        int minVol, maxVol;
        Lista ciudLista;
        Ciudad ciudadActual;

        int[] mesAnioInt = new int[2];

        int volumenAgua;
        String log = "";
        boolean encontrado;

        mesYAnio(scanner, mesAnioInt);

        System.out.println("Ingrese minNomb");
        minNomb = scanner.nextLine();

        System.out.println("Ingrese maxNomb");
        maxNomb = scanner.nextLine();
        // revisar numero y orden
        System.out.println("Ingrese minVol");
        minVol = Integer.parseInt(scanner.nextLine());

        System.out.println("Ingrese maxVol");
        maxVol = Integer.parseInt(scanner.nextLine());

        ciudLista = ciudades.listarRango(minNomb, maxNomb);

        if (!ciudLista.esVacia()) {
            log = "[";
            do {
                ciudadActual = (Ciudad) ciudLista.recuperar(1);
                encontrado = false;

                volumenAgua = ciudadActual.getHabitantesAnioMes(mesAnioInt[1], mesAnioInt[0])
                        * ciudadActual.getCantConsumo();
                if (volumenAgua < maxVol && volumenAgua > minVol) {
                    log += ciudadActual.getNombre();
                    encontrado = true;
                }
                ciudLista.eliminar(1);
                if (!ciudLista.esVacia() && encontrado) {
                        log += ", ";
                    }
            } while (!ciudLista.esVacia());
            log += "]";
        } else {
            log = "no se encotro ninguna ciudad entre " + minNomb + " y " + maxNomb;
        }
        escribirLog(log);

    }

    private void volumenAgua(Scanner scanner, Ciudad ciudadElegida) {

        int volumenAgua;
        int[] mesAnioInt = new int[2];

        if (ciudadElegida != null) {

            mesYAnio(scanner, mesAnioInt);

            volumenAgua = ciudadElegida.getHabitantesAnioMes(mesAnioInt[1], mesAnioInt[0])
                    * ciudadElegida.getCantConsumo();
            int habitantes = ciudadElegida.getHabitantesAnioMes(mesAnioInt[1], mesAnioInt[0]);
            escribirLog(
                    "La cantidad de volumen de agua distribuida de " + Auxiliares.numeroAMes(mesAnioInt[0]) + " del "
                            + (mesAnioInt[1] + 2015)
                            + " ES: " + volumenAgua + " para los habitantes: " + habitantes);
        } else {
            System.out.println("No hay una ciudad seleccionada");
        }

    }

    private void cantHabitates(Scanner scanner, Ciudad ciudadElegida) {

        int[] mesAnioInt = new int[2];

        if (ciudadElegida != null) {
            mesYAnio(scanner, mesAnioInt);

            escribirLog("La cantidad de habitantes de " + Auxiliares.numeroAMes(mesAnioInt[0]) + " del "
                    + (mesAnioInt[1] + 2015)
                    + " ES: " + ciudadElegida.getHabitantesAnioMes(mesAnioInt[1], mesAnioInt[0]));
        } else {
            System.out.println("No hay una ciudad seleccionada");
        }

    }

    private void mesYAnio(Scanner scanner, int[] mesAnioInt) {
        String mes;
        int mesInt;
        String anio;
        int anioInt;
        // Modularizable
        System.out.println("Ingrese el mes");
        do {
            mes = scanner.nextLine();
            mesInt = Auxiliares.mesANumero(mes);
            if (mesInt == -1) {
                System.out.println("debe ingresar un mes valio");
            }
        } while (mesInt == -1);

        mesAnioInt[0] = mesInt;

        System.out.println("Ingrese el año desde 2015 al 2024 inclusive");
        do {
            anio = scanner.nextLine();
            anioInt = traducirAnio(Integer.parseInt(anio));
        } while (anioInt == -1);

        mesAnioInt[1] = anioInt;

    }

    private void ingresarCiudad(Scanner scanner) {
        System.out.println("\n NUEVA CIUDAD ");

        String nombre;
        String nomenclatura;
        String superficie;
        String cantMetrosCubicos;
        boolean pertenece = false;
        boolean valido = false;
        int[][] matriz;
        int j;
        String log;
        String valorActual;

        System.out.println("Ingrese el nombre de la ciudad");

        nombre = scanner.nextLine();

        if (ciudades.pertenece(nombre)) {

            log = "Error, ya existe esa ciudad";
            escribirLog(log);

            pertenece = true;

        }

        if (!pertenece && nombre.length() > 1) {

            do {

                System.out.println("Ingrese la nomenclatura");
                System.out.println("0  Para salir");

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
                System.out.println("0  Para salir");

                do {
                    valido = false;
                    superficie = scanner.nextLine();
                    if (Auxiliares.esNumero(superficie) && !superficie.equals("0")) {
                        valido = true;
                    }

                } while (!valido);

                if (valido) {

                    valido = false;

                    System.out.println("Ingrese la cantidad de metros cubicos promedio");
                    System.out.println("0  Para salir");

                    do {

                        cantMetrosCubicos = scanner.nextLine();

                        if (Auxiliares.esNumero(cantMetrosCubicos) && !cantMetrosCubicos.equals("0")) {
                            valido = true;
                        }

                    } while (!valido && !cantMetrosCubicos.equals("0"));

                    if (valido) {

                        matriz = new int[10][12];
                        j = 0;

                        for (int i = 0; i < matriz.length; i++) {

                            while (j < matriz[0].length) {

                                System.out.println("Ingrese un valor para los habitantes del año " + (2015 + i)
                                        + " del mes " + Auxiliares.numeroAMes(j));

                                valorActual = scanner.nextLine();

                                if (Auxiliares.esNumero(valorActual)) {
                                    matriz[i][j] = Integer.parseInt(valorActual);
                                    j++;
                                } else {

                                    System.out.println("Porfavor ingrese un numero valido");

                                }

                            }
                            j = 0;

                        }

                        agregarCiudad(nombre, matriz, nomenclatura, Integer.parseInt(superficie),
                                Integer.parseInt(cantMetrosCubicos));

                    }

                }

            }

        }

    }

    private void eliminarCiudad(Scanner scanner) {
        System.out.println("\n ELIMINAR CIUDAD ");

        boolean ciudadEliminada;
        String nombre;
        String log;

        System.out.println("Ingrese el nombre de la ciudad");

        nombre = scanner.nextLine();
        String ciudadNomen = ((Ciudad) ciudades.obtenerDato(nombre)).getNomenclatura();
        ciudadEliminada = ciudades.eliminar(nombre);

        if (ciudadEliminada) {
            log = "Ciudad " + nombre + " fue eliminada con exito";
            mapaCiudades.eliminarVertice(ciudadNomen);
        } else {
            log = "Error, la ciudad " + nombre + " no existe";
        }
        escribirLog(log);

    }

    private void modificarCiudad(Scanner scanner) {

        String opcion;
        Ciudad ciudadElegida = null;

        do {

            System.out.println("\n MODIFICAR CIUDADES ");
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
        System.out.println("\n ELEGIR CIUDAD ");

        Ciudad ciudadElegida = null;
        String log;
        String nombre;

        System.out.println("Ingrese el nombre de la ciudad a elegir");

        nombre = scanner.nextLine();
        ciudadElegida = (Ciudad) ciudades.obtenerDato(nombre);

        if (ciudadElegida != null) {
            log = "Ciudad " + nombre + " fue seleccionada";
        } else {
            log = "Error, la ciudad " + nombre + " no existe";
        }
        escribirLog(log);
        return ciudadElegida;

    }

    private void modificarCantHabitantesAnio(Scanner scanner, Ciudad ciudad) {

        String log;
        String cant;
        String anio;
        int anioInt;
        int i;

        if (ciudad != null) {

            System.out.println("Ingrese el año que quiera cambiar");

            anio = scanner.nextLine();

            if (Auxiliares.esNumero(anio)) {
                anioInt = traducirAnio(Integer.parseInt(anio));
                i = 0;
                while (i < 12) {

                    System.out.println("Ingrese la nueva cantidad de habitantes del mes " + Auxiliares.numeroAMes(i));

                    cant = scanner.nextLine();

                    if (Auxiliares.esNumero(cant)) {

                        log = "Cantidad actualizada con exito";
                        ciudad.setHabitantesAnioMes(anioInt, i, Integer.parseInt(cant));
                        i++;

                    } else {

                        log = "Error, no se pudo completar la operacion, cantidad no valida debe ser positiva";

                    }
                    escribirLog(log);
                }
            }

        } else {
            log = "ERROR: No hay una ciudad seleccionada";
            escribirLog(log);
        }
    }

    private void modificarCantHabitantesMes(Scanner scanner, Ciudad ciudad) {

        String log;
        String cant;
        String mes;
        int mesInt;
        String anio;
        int anioInt;

        if (ciudad != null) {

            System.out.println("Ingrese el año del mes que quiera cambiar");

            anio = scanner.nextLine();

            if (Auxiliares.esNumero(anio)) {

                anioInt = traducirAnio(Integer.parseInt(anio));

                System.out.println("Ingrese el mes que quiere cambiar");

                mes = scanner.nextLine();

                mesInt = Auxiliares.mesANumero(mes);

                if (mesInt != -1) {
                    System.out.println("Ingrese la nueva cantidad de habitantes");

                    cant = scanner.nextLine();
                    if (Auxiliares.esNumero(cant)) {

                        log = "Cantidad actualizada con exito";

                        ciudad.setHabitantesAnioMes(anioInt, mesInt, Integer.parseInt(cant));

                    } else {

                        log = "Error, no se pudo completar la operacion, cantidad no valida";
                    }
                } else {
                    log = "Error, no se pudo completar la operacion, el mes debe ser alguno de los siguientes: enero, febrero, marzo, abril, mayo, junio, julio, agosto, septiembre, octubre, noviembre y diciembre";
                }

            } else {
                log = "Error, no se pudo completar la operacion, año no valido, debe ser entre 2015-2024";
            }

        } else {
            log = "ERROR: No hay una ciudad seleccionada";
        }
        escribirLog(log);

    }

    private void modificarSuperficie(Scanner scanner, Ciudad ciudad) {

        String log;
        String cant;

        if (ciudad != null) {

            System.out.println("Ingrese la nueva superficie");

            cant = scanner.nextLine();

            if (Auxiliares.esNumero(cant)) {

                log = "Superficie actualizada con exito";

                ciudad.setSuperficie(Integer.parseInt(cant));

            } else {

                log = "Error, no se pudo completar la operacion, numero no valido";

            }

        } else {
            log = "ERROR: No hay una ciudad seleccionada";
        }
        escribirLog(log);
    }

    private void modificarCantConsumo(Scanner scanner, Ciudad ciudad) {

        String log;
        String cant;

        if (ciudad != null) {

            System.out.println("Ingrese la nueva cantidad de consumo");

            cant = scanner.nextLine();

            if (Auxiliares.esNumero(cant)) {

                log = "Consumo actualizado con exito";

                ciudad.setCantConsumo(Integer.parseInt(cant));

            } else {

                log = "Error, no se pudo completar la operacion, numero no valido";

            }

        } else {
            log = "ERROR: No hay una ciudad seleccionada";
        }
        escribirLog(log);

    }

    private Ciudad agregarCiudad(String nombre, int[][] matriz, String nomenclatura, int superficie,
            int cantMetrosCubicos) {

        Ciudad ciudad = new Ciudad(nombre, nomenclatura, superficie, cantMetrosCubicos);
        ciudad.setHabitantesMatriz(matriz);
        Object[] par = { nombre, ciudad };

        ciudades.insertar(par);

        mapaCiudades.insertarVertice(nomenclatura);
        escribirLog("Ciudad agregada " + nombre);
        return ciudad;
    }

    private void menuConsultasTransporte(Scanner scanner) {

        String opcion;
        Ciudad ciudadA = null;
        Ciudad ciudadB = null;

        do {

            System.out.println("\n--- GESTIÓN DE CONSULTA DE TRANSPORTE ---");
            System.out.println("1. Elegir Ciudad A");
            System.out.println("2. Elegir Ciudad B");
            System.out.println("3. Obtener camino con el minimo caudal maximo y su estado (AB)");
            System.out.println("4. Obtener el camino que pasa por la minima cantidad de ciudades (AB)");
            System.out.println("5. Volver");
            if (ciudadA == null) {
                System.out.println("Ciudad A no seleccionada");
            } else {
                System.out.println("Ciudad A seleccionada" + ciudadA.getNombre());
            }
            if (ciudadB == null) {
                System.out.println("Ciudad B no seleccionada");
            } else {
                System.out.println("Ciudad B seleccionada" + ciudadB.getNombre());
            }
            System.out.print("Opcion elegida: ");

            opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    ciudadA = elegirCiudad(scanner);
                    break;
                case "2":
                    ciudadB = elegirCiudad(scanner);
                    break;
                case "3":
                    obtenerCaminoYEstado(ciudadA, ciudadB);
                    break;
                case "4":
                    obtenerMinimoCamino(ciudadA, ciudadB);
                    break;
                case "5":
                    System.out.println("Volviendo al menu anterior...");
                    break;
                default:
                    System.out.println("ERROR");
                    break;
            }

        } while (!opcion.equals("5"));

    }

    private void obtenerCaminoYEstado(Ciudad ciudadA, Ciudad ciudadB) {
        String log = "";
        Lista camino = mapaCiudades.caminoMinimoMaxEtiqueta(ciudadA.getNomenclatura(), ciudadB.getNomenclatura());
        String estadoCamino;
        if (!camino.esVacia()) {
            estadoCamino = obtenerEstadoCamino(camino);

            log += "El camino es el siguiente: \n";
            log += camino.toString() + " \n";
            log += "Y su estado es: " + estadoCamino + "\n";
        } else {
            log += "No existe camino entre ellos";
        }
        escribirLog(log);
    }

    private void obtenerMinimoCamino(Ciudad ciudadA, Ciudad ciudadB) {
        String log = "";
        Lista camino = mapaCiudades.obtenerCaminoMasCorto(ciudadA.getNomenclatura(), ciudadB.getNomenclatura());
        String estadoCamino;
        if (!camino.esVacia()) {
            estadoCamino = obtenerEstadoCamino(camino);
            log += "El camino es el siguiente:\n";
            log += camino.toString() + " \n";
            log += "Y esta: " + estadoCamino + " \n";
        } else {
            log += "No existe camino entre ellos";
        }

        escribirLog(log);
    }

    private String obtenerEstadoCamino(Lista camino) {

        String estadoFinal = "ACTIVO";
        Object ciudadOrigen;
        Object ciudadDestino;
        String estado;
        boolean estaEnDis = false;
        boolean estaEnRep = false;
        boolean estaInactiv = false;
        int longitud = camino.longitud();
        int i = 1;

        while (i < longitud && !estaEnDis) {
            ciudadOrigen = camino.recuperar(i);
            ciudadDestino = camino.recuperar(i + 1);

            Tuberia tuberia = hashMapCiudadTuberia.get(new ParNomen((String) ciudadOrigen, (String) ciudadDestino));

            if (tuberia != null) {
                estado = tuberia.getEstado();
                estaEnDis = estado.equals("EN DISEÑO");
                estaEnRep = estaEnRep || estado.equals("EN REPARACIÓN");
                estaInactiv = estaInactiv || estado.equals("INACTIVO");
            }

            estadoFinal = estaEnDis ? "EN DISEÑO" : estaEnRep ? "EN REPARACIÓN" : estaInactiv ? "INACTIVO" : "ACTIVO";

            i++;

        }
        return estadoFinal;
    }

    private boolean esNomenclaturaValida(String nomenclatura) {

        boolean valido = true;
        int i = 0;
        String numeroAux = "";
        int num;

        if (nomenclatura.length() == 6) {

            while (valido && i < nomenclatura.length()) {

                if (i <= 1) {
                    if (!Auxiliares.esMayuscula(nomenclatura.charAt(i))) {
                        valido = false;
                    }
                } else if (i > 1) {
                    if (!Auxiliares.esNumero(nomenclatura.charAt(i))) {
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
        if (anio >= 2015 && anio <= 2024) {
            anioRet = anio - 2015;
        }
        return anioRet;

    }

    private String numeroAEstado(String num) {
        String estado = "";
        switch (num) {
            case "0":
                estado = "ACTIVO";
                break;
            case "1":
                estado = "EN REPARACION";
                break;
            case "2":
                estado = "EN DISEÑO";

                break;
            case "3":
                estado = "INACTIVO";
                break;
            default:
                estado = "INCORRECTO";
                break;
        }
        return estado;
    }

    // METODOS DE CARGA

    public String obtenerRutaArchivos() {
        Path rutaCarpeta = Paths.get("");

        return rutaCarpeta.toAbsolutePath() + "";
    }

    public void leerArchivos(String camino) {

        String nombreArchivoEntrada = obtenerRutaArchivos() + "\\ArchivosCargas\\" + camino + ".txt";
        String linea = null;

        try {
            FileReader lectorArchivo = new FileReader(nombreArchivoEntrada);
            BufferedReader bufferLectura = new BufferedReader(lectorArchivo);

            while ((linea = bufferLectura.readLine()) != null) {

                String[] split = linea.split(";");
                switch (camino) {
                    /*
                     * - Ciudades: tiene un formato de Nombre;Nomenclatura;Superficie;CantConsumo.
                     * - Tuberias: tiene un formato de
                     * NomenclaturaCiudadOrigen-CiudadDestino;CaudalMáximo;CaudalMínimo;Diámetro;
                     * Estado.
                     * - habitantes: es una tabla tal que fila es año y columna es mes.
                     */
                    case "Ciudades":
                        Ciudad nuevaCiudad = this.agregarCiudad(split[0], new int[10][12], split[1],
                                Integer.parseInt(split[2]),
                                Integer.parseInt(split[3]));
                        try {
                            String nombreArchivoEntradaHab = obtenerRutaArchivos()
                                    + "\\ArchivosCargas\\habitantes\\habitantes_" + split[0] + ".txt";
                            String lineaHab = null;
                            FileReader lectorArchivoHab = new FileReader(nombreArchivoEntradaHab);
                            BufferedReader bufferLecturaHab = new BufferedReader(lectorArchivoHab);
                            int i = 0;
                            while ((lineaHab = bufferLecturaHab.readLine()) != null) {
                                String[] splitHab = lineaHab.split(";");
                                for (int index = 0; index < splitHab.length; index++) {
                                    nuevaCiudad.setHabitantesAnioMes(i, index, Integer.parseInt(splitHab[index]));
                                }
                                i++;
                            }
                            bufferLecturaHab.close();
                        } catch (FileNotFoundException ex) {
                            System.err.println(ex.getMessage() + "No existe el archivo de habitantes. ");
                        } catch (IOException ex) {
                            System.err.println("Error leyendo o escribiendo en algun archivo de habitantes.");
                        }

                        break;
                    case "Tuberias":
                        String[] splitNomen = split[0].split("-");
                        this.agregarTuberia(Integer.parseInt(split[1]), Integer.parseInt(split[2]),
                                Integer.parseInt(split[3]), split[4], splitNomen[0], splitNomen[1]);
                        break;
                    default:
                        break;
                }

            }
            bufferLectura.close();
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage() + "No existe el archivo.");
        } catch (IOException ex) {
            System.err.println("Error leyendo o escribiendo en algun archivo.");
        }

    }

    private void escribirLog(String texto) {
        if (!texto.equals("")) {
            try {
                FileWriter logger = new FileWriter("sistema.log", true);
                System.out.println(texto);
                logger.write(texto + " \n");
                logger.close();
            } catch (FileNotFoundException ex) {
                System.err.println(ex.getMessage() + "No existe el archivo." + " \n");
            } catch (IOException ex) {
                System.err.println("Error leyendo o escribiendo en algun archivo." + " \n");
            }
        }
    }

    private void vaciarLog() {
        try {
            FileWriter logger = new FileWriter("sistema.log", false);
            logger.write("");
            logger.close();
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage() + "No existe el archivo." + " \n");
        } catch (IOException ex) {
            System.err.println("Error leyendo o escribiendo en algun archivo." + " \n");
        }
    }
}
