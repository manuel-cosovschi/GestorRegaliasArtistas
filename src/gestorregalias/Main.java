package gestorregalias;

import gestorregalias.controlador.GestorRegalias;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GestorRegalias gestor = new GestorRegalias();
        System.out.println("Bienvenido al sistema de gestión de regalías de artistas.");
        // Cargar los datos desde el archivo CSV
        gestor.cargarDatos("datos_artistas.csv");
        // Listar los artistas cargados
        gestor.listarArtistas();

        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\nSeleccione una opción:");
            System.out.println("1. Listar artistas");
            System.out.println("2. Consultar artista por filtro");
            System.out.println("3. Generar liquidación de un artista");
            System.out.println("4. Dar de baja un artista");
            System.out.println("5. Salir");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // consumir el salto de línea

            switch (opcion) {
                case 1:
                    gestor.listarArtistas();
                    break;
                case 2:
                    System.out.print("Seleccione la cantidad de integrantes y/o genero musical: ");
                    System.out.print("\nCantidad Integrantes: ");
                    int cantidadIntegrantes = scanner.nextInt();
                    scanner.nextLine(); // consumir el salto de línea
                    System.out.print("\nGenero Musical: ");
                    String generoMusical = scanner.nextLine();
                    System.out.print("\n");
                    gestor.mostrarDatosArtista(cantidadIntegrantes, generoMusical);
                    break;
                case 3:
                    System.out.print("Ingrese el identificador del artista: ");
                    String identificador = scanner.nextLine();
                    gestor.generarLiquidacion(identificador);
                    break;
                case 4:
                    gestor.listarArtistas();
                    System.out.print("Ingrese el identificador del artista que quisiera dar de baja: ");
                    String identificadorBaja = scanner.nextLine();
                    gestor.eliminarArtista(identificadorBaja);
                    break;
                case 5:
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


