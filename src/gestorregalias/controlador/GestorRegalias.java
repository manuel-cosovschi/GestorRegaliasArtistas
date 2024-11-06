package gestorregalias.controlador;

import gestorregalias.dominio.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class GestorRegalias {
    private Set<Artista> artistas;

    public GestorRegalias() {
        this.artistas = new HashSet<>();
    }

    public void agregarArtista(Artista artista) {
        if (buscarArtista(artista.getIdentificador()) == null) {
            artistas.add(artista);
            System.out.println("Artista agregado exitosamente.");
        } else {
            System.out.println("Ya existe un artista con ese identificador.");
        }
    }

    public void agregarRecital(String identificadorArtista, String fecha, double recaudacion, double costos) {
        Artista artista = buscarArtista(identificadorArtista);
        if (artista != null) {
            try {
                Date fechaRecital = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
                Recital recital = new Recital(fechaRecital, recaudacion, costos);
                artista.getRecitales().add(recital);
                System.out.println("Recital agregado exitosamente.");
            } catch (ParseException e) {
                System.out.println("Fecha en formato incorrecto. Use yyyy-MM-dd.");
            }
        } else {
            System.out.println("Artista con identificador " + identificadorArtista + " no encontrado.");
        }
    }

    public void agregarCancionADisco(String identificadorArtista, String nombreDisco, Cancion cancion) {
        Artista artista = buscarArtista(identificadorArtista);
        if (artista != null) {
            Optional<Disco> discoOpt = artista.getDiscos().stream()
                    .filter(disco -> disco.getNombre().equalsIgnoreCase(nombreDisco))
                    .findFirst();
    
            if (discoOpt.isPresent()) {
                discoOpt.get().getCanciones().add(cancion);
                System.out.println("Canción agregada exitosamente al disco.");
            } else {
                System.out.println("Disco con nombre " + nombreDisco + " no encontrado.");
            }
        } else {
            System.out.println("Artista con identificador " + identificadorArtista + " no encontrado.");
        }
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

    public void guardarEstado(String archivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(artistas);
            System.out.println("Estado guardado exitosamente en " + archivo);
        } catch (IOException e) {
            System.out.println("Error al guardar el estado: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void cargarEstado(String archivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            artistas = (Set<Artista>) ois.readObject();
            System.out.println("Estado cargado exitosamente desde " + archivo);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar el estado: " + e.getMessage());
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

    public void mostrarTop10CancionesPorGenero(String generoMusical, String archivoSalida) {
        List<Cancion> canciones = new ArrayList<>();

        for (Artista artista : artistas) {
            if (artista.getGeneroMusical().equalsIgnoreCase(generoMusical)) {
                for (Disco disco : artista.getDiscos()) {
                    canciones.addAll(disco.getCanciones());
                }
            }
        }

        canciones.sort((c1, c2) -> Integer.compare(c2.getReproducciones(), c1.getReproducciones()));

        try (PrintWriter writer = new PrintWriter(new FileWriter(archivoSalida))) {
            System.out.println("Top 10 canciones del género: " + generoMusical);
            writer.println("Top 10 canciones del género: " + generoMusical);
            for (int i = 0; i < Math.min(10, canciones.size()); i++) {
                System.out.println(canciones.get(i));
                writer.println(canciones.get(i));
            }
            System.out.println("El reporte se ha guardado en " + archivoSalida);
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo: " + e.getMessage());
        }
    }

    public void mostrarDetalleUnidadesVendidasPorDisco(String identificadorArtista, String archivoSalida) {
        Artista artista = buscarArtista(identificadorArtista);

        if (artista == null) {
            System.out.println("Artista con identificador " + identificadorArtista + " no encontrado.");
            return;
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(archivoSalida))) {
            System.out.println("Detalle de unidades vendidas por disco para el artista: " + artista.getNombre());
            writer.println("Detalle de unidades vendidas por disco para el artista: " + artista.getNombre());
            int totalUnidades = 0;

            for (Disco disco : artista.getDiscos()) {
                int unidadesVendidas = disco.getUnidadesVendidas();
                totalUnidades += unidadesVendidas;
                System.out.println("Disco '" + disco.getNombre() + "': " + unidadesVendidas + " unidades vendidas");
                writer.println("Disco '" + disco.getNombre() + "': " + unidadesVendidas + " unidades vendidas");
            }

            if (artista.getDiscos().size() > 0) {
                double promedioUnidades = (double) totalUnidades / artista.getDiscos().size();
                System.out.println("Promedio de unidades vendidas por disco: " + promedioUnidades);
                writer.println("Promedio de unidades vendidas por disco: " + promedioUnidades);
            } else {
                System.out.println("No hay discos registrados para este artista.");
                writer.println("No hay discos registrados para este artista.");
            }
            System.out.println("El reporte se ha guardado en " + archivoSalida);
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo: " + e.getMessage());
        }
    }
}
  
