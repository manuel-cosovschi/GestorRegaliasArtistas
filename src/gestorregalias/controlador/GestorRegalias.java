package gestorregalias.controlador;

import gestorregalias.dominio.*;
import java.io.*;
import java.util.*;

public class GestorRegalias {
    private TreeSet<Artista> artistas;

    public GestorRegalias() {
        this.artistas = new TreeSet<>(Comparator.comparing(Artista::getIdentificador));
    }

    // Método para cargar datos desde un archivo (XML, JSON, CSV)
    public void cargarDatos(String archivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (validarDatos(datos)) {
                    String identificador = datos[0];
                    String nombre = datos[1];
                    int cantidadIntegrantes = Integer.parseInt(datos[2]);
                    String generoMusical = datos[3];
                    String tipo = datos[4];

                    Artista artista;
                    if ("emergente".equalsIgnoreCase(tipo)) {
                        artista = new ArtistaEmergente(identificador, nombre, cantidadIntegrantes, generoMusical);
                    } else {
                        artista = new ArtistaConsagrado(identificador, nombre, cantidadIntegrantes, generoMusical);
                    }
                    artistas.add(artista);
                } else {
                    System.out.println("Error en la línea: " + linea + ". Datos inválidos.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar los datos: " + e.getMessage());
        }
    }

    // Método para validar los datos cargados
    private boolean validarDatos(String[] datos) {
        if (datos.length != 5) {
            return false;
        }
        if (datos[0] == null || datos[0].isEmpty()) { // Verifica identificador
            return false;
        }
        if (datos[1] == null || datos[1].isEmpty()) { // Verifica nombre
            return false;
        }
        try {
            int cantidadIntegrantes = Integer.parseInt(datos[2]);
            if (cantidadIntegrantes < 1) { // Debe haber al menos un integrante
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        if (datos[3] == null || datos[3].isEmpty()) { // Verifica género musical
            return false;
        }
        if (!"emergente".equalsIgnoreCase(datos[4]) && !"consagrado".equalsIgnoreCase(datos[4])) { // Verifica tipo
            return false;
        }
        return true;
    }

    // Método para listar los artistas
    public void listarArtistas() {
        artistas.forEach(artista -> System.out.println(artista.getIdentificador() + " - " + artista.getNombre()));
    }

    // Método para generar la liquidación mensual de un artista
    public void generarLiquidacion(String identificadorArtista) {
        Artista artista = artistas.stream()
                .filter(a -> a.getIdentificador().equals(identificadorArtista))
                .findFirst()
                .orElse(null);

        if (artista == null) {
            System.out.println("Artista con identificador " + identificadorArtista + " no encontrado.");
            return;
        }

        System.out.println("Liquidación mensual para el artista: " + artista.getNombre());
        double totalLiquidacion = 0;

        // Liquidación por discos vendidos
        for (Disco disco : artista.getDiscos()) {
            double ingresosPorDisco = disco.getUnidadesVendidas() * 10; // Supongamos 10 unidades monetarias por disco
            totalLiquidacion += ingresosPorDisco;
            System.out.println("Ingresos por disco '" + disco.getNombre() + "': " + ingresosPorDisco);
        }

        // Liquidación por recitales
        for (Recital recital : artista.getRecitales()) {
            double ingresosNetos = recital.getNeto();
            totalLiquidacion += ingresosNetos;
            System.out.println("Ingresos netos del recital del " + recital.getFecha() + ": " + ingresosNetos);
        }

        // Mostrar total de la liquidación
        System.out.println("Total de la liquidación: " + totalLiquidacion);
    }

    //Consulta de los datos completos de los artitas
    public void mostrarDatosArtista (int cantidadIntegrantes, String generoMusical){
        TreeSet<Artista> artistasFiltrados = new TreeSet<Artista>();

        if (cantidadIntegrantes <= 0 && generoMusical.equals("")){
            System.out.println ("No se ingreso ningún filtro");
            return;
        }

        if (cantidadIntegrantes > 0 && !generoMusical.equals("")){
            for (Artista artista : artistas){
                if (artista.getCantidadIntegrantes() == cantidadIntegrantes && artista.getGeneroMusical().equals(generoMusical)){
                    artistasFiltrados.add(artista);
                }
            }
        }
        else{
            if (cantidadIntegrantes > 0){
                for (Artista artista : artistas){
                    if (artista.getCantidadIntegrantes() == cantidadIntegrantes){
                        artistasFiltrados.add(artista);
                    }
                }
            }
            else{
                for (Artista artista : artistas){
                    if (artista.getGeneroMusical().equals(generoMusical)){
                        artistasFiltrados.add(artista);
                    }
                }
            }
        }

        for (Artista artista : artistasFiltrados){
            System.out.println(artista.toString());
        }
                    
    }

    public void eliminarArtista (String identificador){
        if (identificador.equals("")){
            System.out.println ("No se ingreso ningún identificador");
            return;
        }

        Iterator<Artista> ita = artistas.iterator();
        Artista artista;
        while (ita.hasNext()){
            artista = ita.next();
            if (artista.getIdentificador().equals(identificador)){
                ita.remove();
                System.out.println ("\n Artista " + identificador + " eliminado correctamente.");
                return;
            }
        }

    }
}
