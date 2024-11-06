package gestorregalias;

import gestorregalias.controlador.GestorRegalias;
import gestorregalias.dominio.Cancion;
import gestorregalias.dominio.ArtistaConsagrado;
import gestorregalias.dominio.ArtistaEmergente;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GestorRegalias gestor = new GestorRegalias();
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        System.out.println("¿Desea cargar el estado desde un archivo serializado? (s/n)");
        String respuesta = scanner.nextLine();
        if (respuesta.equalsIgnoreCase("s")) {
            gestor.cargarEstado("estado_artistas.ser");
        } else {
            gestor.cargarDatos("datos_artistas.csv");
        }

        while (continuar) {
            System.out.println("\nSeleccione una opción:");
            System.out.println("1. Listar artistas");
            System.out.println("2. Generar liquidación de un artista");
            System.out.println("3. Consultar artista por filtro");
            System.out.println("4. Consultar top 10 canciones por género musical");
            System.out.println("5. Mostrar detalle de unidades vendidas por disco para un artista");
            System.out.println("6. Dar de baja un artista");
            System.out.println("7. Guardar estado del sistema");
            System.out.println("8. Registrar un nuevo artista");
            System.out.println("9. Agregar un recital a un artista");
            System.out.println("10. Agregar una canción a un disco");
            System.out.println("11. Salir");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    gestor.listarArtistas();
                    break;
                case 2:
                    System.out.print("Ingrese el identificador del artista: ");
                    String identificador = scanner.nextLine();
                    gestor.generarLiquidacion(identificador);
                    break;
                case 3:
                    System.out.print("Ingrese la cantidad de integrantes (0 para omitir): ");
                    int cantidadIntegrantes = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Ingrese el género musical (dejar vacío para omitir): ");
                    String generoMusical = scanner.nextLine();
                    gestor.mostrarDatosArtista(cantidadIntegrantes, generoMusical);
                    break;
                case 4:
                    System.out.print("Ingrese el género musical: ");
                    String genero = scanner.nextLine();
                    gestor.mostrarTop10CancionesPorGenero(genero);
                    break;
                case 5:
                    System.out.print("Ingrese el identificador del artista: ");
                    String idArtista = scanner.nextLine();
                    gestor.mostrarDetalleUnidadesVendidasPorDisco(idArtista);
                    break;
                case 6:
                    System.out.print("Ingrese el identificador del artista que desea dar de baja: ");
                    String identificadorBaja = scanner.nextLine();
                    gestor.eliminarArtista(identificadorBaja);
                    break;
                case 7:
                    gestor.guardarEstado("estado_artistas.ser");
                    break;
                case 8:
                    System.out.print("Ingrese el identificador del artista (6 caracteres): ");
                    String nuevoId = scanner.nextLine();
                    System.out.print("Ingrese el nombre del artista: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese la cantidad de integrantes: ");
                    int integrantes = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Ingrese el género musical: ");
                    String generoNuevo = scanner.nextLine();
                    System.out.print("¿El artista es emergente o consagrado? (e/c): ");
                    String tipoArtista = scanner.nextLine();

                    if (tipoArtista.equalsIgnoreCase("e")) {
                        gestor.agregarArtista(new ArtistaEmergente(nuevoId, nombre, integrantes, generoNuevo));
                    } else if (tipoArtista.equalsIgnoreCase("c")) {
                        gestor.agregarArtista(new ArtistaConsagrado(nuevoId, nombre, integrantes, generoNuevo));
                    } else {
                        System.out.println("Tipo de artista no válido.");
                    }
                    break;
                case 9:
                    System.out.print("Ingrese el identificador del artista: ");
                    String idRecitalArtista = scanner.nextLine();
                    System.out.print("Ingrese la fecha del recital (yyyy-mm-dd): ");
                    String fechaStr = scanner.nextLine();
                    System.out.print("Ingrese la recaudación del recital: ");
                    double recaudacion = scanner.nextDouble();
                    System.out.print("Ingrese los costos de producción del recital: ");
                    double costos = scanner.nextDouble();
                    scanner.nextLine();
                    gestor.agregarRecital(idRecitalArtista, fechaStr, recaudacion, costos);
                    break;
                case 10:
                    System.out.print("Ingrese el identificador del artista: ");
                    String idArtistaDisco = scanner.nextLine();
                    System.out.print("Ingrese el nombre del disco: ");
                    String nombreDisco = scanner.nextLine();
                    System.out.print("Ingrese el nombre de la canción: ");
                    String nombreCancion = scanner.nextLine();
                    System.out.print("Ingrese la duración de la canción en minutos: ");
                    int durMinutos = scanner.nextInt();
                    System.out.print("Ingrese la duración de la canción en segundos: ");
                    int durSegundos = scanner.nextInt();
                    System.out.print("Ingrese la cantidad de reproducciones: ");
                    int reproducciones = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("¿Es sencillo? (s/n): ");
                    String esSencillo = scanner.nextLine();
                    boolean sencillo = esSencillo.equalsIgnoreCase("s");
                    gestor.agregarCancionADisco(idArtistaDisco, nombreDisco, new Cancion(nombreCancion, durMinutos, durSegundos, reproducciones, sencillo));
                    break;
                case 11:
                    continuar = false;
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
        scanner.close();
    }
}




