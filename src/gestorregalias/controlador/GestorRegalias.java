package gestorregalias.controlador;

import gestorregalias.dominio.*;
import java.io.*;
import java.util.*;

public class GestorRegalias {
    private Set<Artista> artistas;

    public GestorRegalias() {
        this.artistas = new HashSet<>();
    }

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

    private boolean validarDatos(String[] datos) {
        if (datos.length != 5) {
            return false;
        }
        if (datos[0].isEmpty() || datos[1].isEmpty() || datos[3].isEmpty()) {
            return false;
        }
        try {
            int cantidadIntegrantes = Integer.parseInt(datos[2]);
            if (cantidadIntegrantes < 1) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return "emergente".equalsIgnoreCase(datos[4]) || "consagrado".equalsIgnoreCase(datos[4]);
    }

    public void listarArtistas() {
        for (Artista artista : artistas) {
            System.out.println(artista.getIdentificador() + " - " + artista.getNombre());
        }
    }

    public void generarLiquidacion(String identificadorArtista) {
        Artista artista = buscarArtista(identificadorArtista);

        if (artista == null) {
            System.out.println("Artista con identificador " + identificadorArtista + " no encontrado.");
            return;
        }

        double totalLiquidacion = 0;
        System.out.println("Liquidación mensual para el artista: " + artista.getNombre());

        for (Disco disco : artista.getDiscos()) {
            double ingresosPorDisco = disco.getUnidadesVendidas() * 10;
            totalLiquidacion += ingresosPorDisco;
            System.out.println("Ingresos por disco '" + disco.getNombre() + "': " + ingresosPorDisco);
        }

        for (Recital recital : artista.getRecitales()) {
            double ingresosNetos = recital.getNeto();
            totalLiquidacion += ingresosNetos;
            System.out.println("Ingresos netos del recital del " + recital.getFecha() + ": " + ingresosNetos);
        }

        System.out.println("Total de la liquidación: " + totalLiquidacion);
    }

    public void mostrarDatosArtista(int cantidadIntegrantes, String generoMusical) {
        boolean filtroAplicado = false;

        for (Artista artista : artistas) {
            boolean coincide = true;

            if (cantidadIntegrantes > 0 && artista.getCantidadIntegrantes() != cantidadIntegrantes) {
                coincide = false;
            }

            if (!generoMusical.isEmpty() && !artista.getGeneroMusical().equalsIgnoreCase(generoMusical)) {
                coincide = false;
            }

            if (coincide) {
                System.out.println(artista);
                filtroAplicado = true;
            }
        }

        if (!filtroAplicado) {
            System.out.println("No se encontraron artistas con los filtros especificados.");
        }
    }

    public void eliminarArtista(String identificador) {
        Artista artista = buscarArtista(identificador);
        if (artista != null) {
            artistas.remove(artista);
            System.out.println("Artista " + identificador + " eliminado correctamente.");
        } else {
            System.out.println("Artista con identificador " + identificador + " no encontrado.");
        }
    }

    private Artista buscarArtista(String identificador) {
        for (Artista artista : artistas) {
            if (artista.getIdentificador().equals(identificador)) {
                return artista;
            }
        }
        return null;
    }

    public void mostrarTop10CancionesPorGenero(String generoMusical) {
        List<Cancion> canciones = new ArrayList<>();

        for (Artista artista : artistas) {
            if (artista.getGeneroMusical().equalsIgnoreCase(generoMusical)) {
                for (Disco disco : artista.getDiscos()) {
                    canciones.addAll(disco.getCanciones());
                }
            }
        }

        canciones.sort((c1, c2) -> Integer.compare(c2.getReproducciones(), c1.getReproducciones()));

        System.out.println("Top 10 canciones del género: " + generoMusical);
        for (int i = 0; i < Math.min(10, canciones.size()); i++) {
            System.out.println(canciones.get(i));
        }
    }

    public void mostrarDetalleUnidadesVendidasPorDisco(String identificadorArtista) {
        Artista artista = buscarArtista(identificadorArtista);

        if (artista == null) {
            System.out.println("Artista con identificador " + identificadorArtista + " no encontrado.");
            return;
        }

        System.out.println("Detalle de unidades vendidas por disco para el artista: " + artista.getNombre());
        int totalUnidades = 0;

        for (Disco disco : artista.getDiscos()) {
            int unidadesVendidas = disco.getUnidadesVendidas();
            totalUnidades += unidadesVendidas;
            System.out.println("Disco '" + disco.getNombre() + "': " + unidadesVendidas + " unidades vendidas");
        }

        if (artista.getDiscos().size() > 0) {
            double promedioUnidades = (double) totalUnidades / artista.getDiscos().size();
            System.out.println("Promedio de unidades vendidas por disco: " + promedioUnidades);
        } else {
            System.out.println("No hay discos registrados para este artista.");
        }
    }
}
  
