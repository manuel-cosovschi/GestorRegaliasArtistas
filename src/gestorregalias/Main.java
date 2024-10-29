package gestorregalias;

import gestorregalias.controlador.GestorRegalias;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GestorRegalias gestor = new GestorRegalias();
        System.out.println("Bienvenido al sistema de gestión de regalías de artistas.");
        gestor.cargarDatos("datos_artistas.csv");
        gestor.listarArtistas();

        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\nSeleccione una opción:");
            System.out.println("1. Listar artistas");
            System.out.println("2. Generar liquidación de un artista");
            System.out.println("3. Consultar artista por filtro");
            System.out.println("4. Consultar top 10 canciones por género musical");
            System.out.println("5. Mostrar detalle de unidades vendidas por disco para un artista");
            System.out.println("6. Dar de baja un artista");
            System.out.println("7. Salir");
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


