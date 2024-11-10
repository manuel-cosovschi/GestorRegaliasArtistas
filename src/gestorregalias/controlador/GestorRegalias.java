package gestorregalias.controlador;

import gestorregalias.dominio.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class GestorRegalias {
    private TreeSet<Artista> artistas;

    public GestorRegalias() {
        this.artistas = new TreeSet<>();
    }

    /**
     * Permite agregar un artista a la lista de artistas.
     * @param artista
     */
    public void agregarArtista(Artista artista) {
        if (buscarArtista(artista.getIdentificador()) == null) {
            artistas.add(artista);

            String mensaje = "Artista agregado exitosamente.";
            int longitud = mensaje.length();
            String borde = "*".repeat(longitud + 4);

            System.out.println("\n" + borde);
            System.out.println("* " + mensaje + " *");
            System.out.println(borde + "\n");
        } else {
            String mensaje = "Ya existe un artista con ese identificador.";
            int longitud = mensaje.length();
            String borde = "*".repeat(longitud + 4);

            System.out.println("\n" + borde);
            System.out.println("* " + mensaje + " *");
            System.out.println(borde + "\n");
        }
    }

    /**
     * Permite agregar un recital a la lista de recitales
     * @param identificadorArtista
     * @param fecha
     * @param recaudacion
     * @param costos
     */
    public void agregarRecital(String identificadorArtista, String fecha, double recaudacion, double costos) {
        Artista artista = buscarArtista(identificadorArtista);
        if (artista != null) {
            try {
                LocalDate fechaRecital = LocalDate.parse(fecha);
                Recital recital = new Recital(fechaRecital, recaudacion, costos);
                artista.getRecitales().add(recital);
                String mensaje = "Recital agregado exitosamente.";
                int longitud = mensaje.length();
                String borde = "*".repeat(longitud + 4);

                System.out.println("\n" + borde);
                System.out.println("* " + mensaje + " *");
                System.out.println(borde + "\n");
            } catch (Exception e) {
                String mensaje = "Fecha en formato incorrecto. Use yyyy-MM-dd.";
                int longitud = mensaje.length();
                String borde = "*".repeat(longitud + 4);

                System.out.println("\n" + borde);
                System.out.println("* " + mensaje + " *");
                System.out.println(borde + "\n");

            }
        } else {
            String mensaje = "Artista con identificador " + identificadorArtista + " no encontrado.";
            int longitud = mensaje.length();
            String borde = "*".repeat(longitud + 4);

            System.out.println("\n" + borde);
            System.out.println("* " + mensaje + " *");
            System.out.println(borde + "\n");
        }
    }

    /**
     * Permite agregar una cancion al disco.
     * @param identificadorArtista
     * @param nombreDisco
     * @param cancion
     */
    public void agregarCancionADisco(String identificadorArtista, String nombreDisco, Cancion cancion) {
        Artista artista = buscarArtista(identificadorArtista);
        if (artista != null) {
            Optional<Disco> discoOpt = artista.getDiscos().stream()
                    .filter(disco -> disco.getNombre().equalsIgnoreCase(nombreDisco))
                    .findFirst();
    
            if (discoOpt.isPresent()) {
                discoOpt.get().getCanciones().add(cancion);

                String mensaje = "Canción agregada exitosamente al disco.";
                int longitud = mensaje.length();
                String borde = "*".repeat(longitud + 4);

                System.out.println("\n" + borde);
                System.out.println("* " + mensaje + " *");
                System.out.println(borde + "\n");
            } else {
                String mensaje = "Disco con nombre " + nombreDisco + " no encontrado.";
                int longitud = mensaje.length();
                String borde = "*".repeat(longitud + 4);

                System.out.println("\n" + borde);
                System.out.println("* " + mensaje + " *");
                System.out.println(borde + "\n");
            }
        } else {
            String mensaje = "Artista con identificador " + identificadorArtista + " no encontrado.";
            int longitud = mensaje.length();
            String borde = "*".repeat(longitud + 4);

            System.out.println("\n" + borde);
            System.out.println("* " + mensaje + " *");
            System.out.println(borde + "\n");
        }
    }
    
    /**
     * Carga los datos del artista, disco, cancion y recital del archivo datos_artistas.csv.
     * @param archivo
     */
    public void cargarDatos(String archivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");

                String tipoEntidad = datos[0];
                switch (tipoEntidad) {
                    case "Artista":
                        cargarArtista(datos, linea);
                        break;
                    case "Disco":
                        cargaDisco(datos);
                        break;
                    case "Cancion":
                        cargaCancion(datos);
                        break;
                    case "Recital":
                        cargaRecital(datos);
                        break;
                    default:

                }
            }
        } catch (IOException e) {
            String mensaje = "Error al cargar los datos: " + e.getMessage();
            int longitud = mensaje.length();
            String borde = "*".repeat(longitud + 4);

            System.out.println("\n" + borde);
            System.out.println("* " + mensaje + " *");
            System.out.println(borde + "\n");

        }
    }

    /**
     * Carga el artista
     * @param datos, linea
     */
    private void cargarArtista(String[] datos, String linea) {
        if (validarDatos(datos)) {
            String identificador = datos[1];
            String nombre = datos[2];
            int cantidadIntegrantes = Integer.parseInt(datos[3]);
            String generoMusical = datos[4];
            String tipo = datos[5];

            Artista artista;
            if ("emergente".equalsIgnoreCase(tipo)) {
                artista = new ArtistaEmergente(identificador, nombre, cantidadIntegrantes, generoMusical);
            } else {
                artista = new ArtistaConsagrado(identificador, nombre, cantidadIntegrantes, generoMusical);
            }
            artistas.add(artista);
        } else {
            String mensaje = "Error en la línea: " + linea + ". Datos inválidos.";
            int longitud = mensaje.length();
            String borde = "*".repeat(longitud + 4);

            System.out.println("\n" + borde);
            System.out.println("* " + mensaje + " *");
            System.out.println(borde + "\n");

            System.out.println();
        }
    }

    /**
     * Carga los disco
     * @param datos
     */
    private void cargaDisco(String[] datos) {
        try {
            String nombreDisco = datos[1];
            int unidadesVendidas = Integer.parseInt(datos[2]);
            float precioVentaXUnidad = Float.parseFloat(datos[3]);
            String nombreArtista = datos[4];

            Artista artista = buscarArtista(nombreArtista);
            if(artista != null) {
                Disco disco = new Disco(nombreDisco, unidadesVendidas, precioVentaXUnidad);
                artista.agregarDisco(disco);
            } else {
                String mensaje = "Artista no encontrado.\"";
                int longitud = mensaje.length();
                String borde = "*".repeat(longitud + 4);

                System.out.println("\n" + borde);
                System.out.println("* " + mensaje + " *");
                System.out.println(borde + "\n");

            }
        } catch (NumberFormatException e) {
            String mensaje = "Error de formato en los datos.";
            int longitud = mensaje.length();
            String borde = "*".repeat(longitud + 4);

            System.out.println("\n" + borde);
            System.out.println("* " + mensaje + " *");
            System.out.println(borde + "\n");
        }
    }

    /**
     * Carga los cancion
     * @param datos
     */
    private  void cargaCancion(String[] datos) {
        try {
            String nombreCancion = datos[1];
            int duracionMinutos = Integer.parseInt(datos[2]);
            int duracionSegundos = Integer.parseInt(datos[3]);
            int reproducciones = Integer.parseInt(datos[4]);
            Boolean esSencillo = Boolean.parseBoolean(datos[5]);
            float precioXReproduccion = Float.parseFloat(datos[6]);
            String nombreDiscoArtista = datos[7];

            Disco disco = buscarDisco(nombreDiscoArtista);
            if(disco != null) {
                Cancion cancion = new Cancion(nombreCancion, duracionMinutos, duracionSegundos, reproducciones, esSencillo, precioXReproduccion);
                disco.agregarCancion(cancion);
            } else {
                String mensaje = "Disco no encontrado para canción: " + nombreCancion;
                int longitud = mensaje.length();
                String borde = "*".repeat(longitud + 4);

                System.out.println("\n" + borde);
                System.out.println("* " + mensaje + " *");
                System.out.println(borde + "\n");
            }
        } catch (NumberFormatException e) {
            String mensaje = "Error de formato en los datos de la canción.";
            int longitud = mensaje.length();
            String borde = "*".repeat(longitud + 4);

            System.out.println("\n" + borde);
            System.out.println("* " + mensaje + " *");
            System.out.println(borde + "\n");
        }
    }

    /**
     * Carga los recital
     * @param datos
     */
    private  void cargaRecital(String[] datos) {
        try {
            LocalDate fecha = LocalDate.parse(datos[1]);
            float recaudacion = Float.parseFloat(datos[2]);
            float costReproduccion = Float.parseFloat(datos[3]);
            String nombreRecitalArtista = datos[4];

            Artista artista = buscarArtista(nombreRecitalArtista);
            if (artista != null) {
                Recital recital = new Recital(fecha, recaudacion, costReproduccion);
                artista.agregarRecital(recital);
            } else {
                String mensaje = "Artista no encontrado para recital.";
                int longitud = mensaje.length();
                String borde = "*".repeat(longitud + 4);

                System.out.println("\n" + borde);
                System.out.println("* " + mensaje + " *");
                System.out.println(borde + "\n");
            }
        } catch (NumberFormatException | DateTimeParseException e) {
            String mensaje = "Error de formato en los datos del recital.";
            int longitud = mensaje.length();
            String borde = "*".repeat(longitud + 4);

            System.out.println("\n" + borde);
            System.out.println("* " + mensaje + " *");
            System.out.println(borde + "\n");
        }
    }

    /**
     * Guarda el estado en el que se encuentra el sistema en un archivo .ser.
     * @param archivo
     */
    public void guardarEstado(String archivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(artistas);
            String mensaje = "Estado guardado exitosamente en " + archivo;
            int longitud = mensaje.length();
            String borde = "*".repeat(longitud + 4);

            System.out.println("\n" + borde);
            System.out.println("* " + mensaje + " *");
            System.out.println(borde + "\n");

        } catch (IOException e) {
            String mensaje = "Error al guardar el estado: " + e.getMessage();
            int longitud = mensaje.length();
            String borde = "*".repeat(longitud + 4);

            System.out.println("\n" + borde);
            System.out.println("* " + mensaje + " *");
            System.out.println(borde + "\n");

            System.out.println();
        }
    }

    /**
     * Carga el archivo .ser. Devuelve el sistema al estado en el que se encontraba antes de cerrarse.
     * @param archivo
     */
    @SuppressWarnings("unchecked")
    public void cargarEstado(String archivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            artistas = (TreeSet<Artista>) ois.readObject();
            String mensaje = "Estado cargado exitosamente desde " + archivo;
            int longitud = mensaje.length();
            String borde = "*".repeat(longitud + 4);

            System.out.println("\n" + borde);
            System.out.println("* " + mensaje + " *");
            System.out.println(borde + "\n");

        } catch (IOException | ClassNotFoundException e) {
            String mensaje = "Error al cargar el estado: " + e.getMessage();
            int longitud = mensaje.length();
            String borde = "*".repeat(longitud + 4);

            System.out.println("\n" + borde);
            System.out.println("* " + mensaje + " *");
            System.out.println(borde + "\n");
        }
    }

    /**
     * Valida que los datos del artista cuando se cargan son correctos.
     * @param datos
     * @return
     */
    private boolean validarDatos(String[] datos) {
        if (datos.length != 6) {
            return false;
        }
        if (datos[1].isEmpty() || datos[2].isEmpty() || datos[4].isEmpty()) {
            return false;
        }
        try {
            int cantidadIntegrantes = Integer.parseInt(datos[3]);
            if (cantidadIntegrantes < 1) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return "emergente".equalsIgnoreCase(datos[5]) || "consagrado".equalsIgnoreCase(datos[5]);
    }

    /**
     * Lista los diferentes artistas que tiene la coleccion artistas.
     */
    public void listarArtistas() {
        for (Artista artista : artistas) {
            System.out.println(artista.getIdentificador() + " - " + artista.getNombre());
        }
    }

    /**
     * Funcion que genera la liquidacion de cada artista.
     * @param identificadorArtista
     */
    public void generarLiquidacion(String identificadorArtista) {
        Artista artista = buscarArtista(identificadorArtista);

        if (artista == null) {
            String mensaje = "Artista con identificador " + identificadorArtista + " no encontrado.";
            int longitud = mensaje.length();
            String borde = "*".repeat(longitud + 4);

            System.out.println("\n" + borde);
            System.out.println("* " + mensaje + " *");
            System.out.println(borde + "\n");
            return;
        }

        int totalUnidades = 0;
        float importeTotalRegalias = 0;
        double totalLiquidacion = 0;
        double totalRegalias = 0;

        System.out.println("Liquidación mensual para el artista: " + artista.getNombre());
        System.out.println("\nDetalle de conceptos: ");
        System.out.println("\n\tPorcentaje de regalias por disco: " + artista.getPorcentajeRegaliaDisco() + " %");
        System.out.println("\n\tPorcentaje por regalias por cancion: " + artista.getPorcentajeRegaliaCancion() + " %");
        System.out.println("\n\tPorcentaje por regalias por recital: " + artista.getPorcentajeRegaliaRecital() + " %\n");

        for (Disco disco : artista.getDiscos()) {
            totalUnidades = disco.getUnidadesVendidas();
            System.out.println("\n\t Disco: " + disco.getNombre());
            System.out.println("\n\t Precio por unidad: $" + disco.getprecioXVentaUnidad());
            System.out.println("\n\t\t Total de unidades vendidas: " + totalUnidades);
            double ingresosPorDisco = (totalUnidades * disco.getprecioXVentaUnidad());
            System.out.println("\n\t\t Total ingresos por Disco: $" + (float)ingresosPorDisco);
            importeTotalRegalias = artista.calculaImportePorRegalia(disco.getprecioXVentaUnidad(),"disco") * totalUnidades;
            System.out.println("\n\t\t Total precio por porcentaje de regalias: $" + importeTotalRegalias);
            
            totalLiquidacion += ingresosPorDisco;
            totalRegalias += importeTotalRegalias;

            System.out.println("\n\tCanciones: ");
            double ingresoPorCancion = 0;
            for (Cancion cancion : disco.getCanciones()) {
                System.out.println("\n\t\t Nombre: " + cancion.getNombre());
                System.out.println("\n\t\t Cantidad de reproducciones: " + cancion.getReproducciones());
                ingresoPorCancion = cancion.getReproduccionesParaLiquidacion() * cancion.getPrecioXReproduccion();
                System.out.println("\n\t\t\t Total ingreso por reproduccion: $" + (float)ingresoPorCancion);
                importeTotalRegalias = artista.calculaImportePorRegalia(cancion.getPrecioXReproduccion(), "cancion");
                System.out.println("\n\t\t\t Total precio por porcentaje de regalias: $" + importeTotalRegalias);

                totalLiquidacion += ingresoPorCancion;
                totalRegalias += importeTotalRegalias;
            }
        }

        System.out.println("\n\tRecitales: ");
        for (Recital recital : artista.getRecitales()) {
            System.out.println("\n\t\t Fecha del recital: " + recital.getFecha());
            System.out.println("\n\t\t\t Total recaudacion: $" + recital.getRecaudacion());
            System.out.println("\n\t\t\t Total costo de Produccion: $" + recital.getCostosProduccion());
            float ingresosNetos = (float)(recital.getNeto());
            System.out.println("\n\t\t\t Total Neto: $" + ingresosNetos);
            importeTotalRegalias = artista.calculaImportePorRegalia(ingresosNetos, "recital");
            System.out.println("\n\t\t\t Total precio por porcentaje de regalias: $" + importeTotalRegalias);

            totalLiquidacion += ingresosNetos;
            totalRegalias += importeTotalRegalias;
        }

        System.out.println("\n\nTotal de la liquidación: $" + (float)totalLiquidacion);
        System.out.println("\nTotal de las regalias: $" + (float)totalRegalias);
    }

    /**
     * Muestra todos los datos que tiene el artista.
     * @param cantidadIntegrantes
     * @param generoMusical
     */
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
            String mensaje = "No se encontraron artistas con los filtros especificados.";
            int longitud = mensaje.length();
            String borde = "*".repeat(longitud + 4);

            System.out.println("\n" + borde);
            System.out.println("* " + mensaje + " *");
            System.out.println(borde + "\n");
        }
    }

    /**
     * Elimina el artista de la coleccion de artistas basandose en su identificador.
     * @param identificador
     */
    public void eliminarArtista(String identificador) {
        Artista artista = buscarArtista(identificador);
        if (artista != null) {
            artistas.remove(artista);
            String mensaje = "Artista " + identificador + " eliminado correctamente.";
            int longitud = mensaje.length();
            String borde = "*".repeat(longitud + 4);

            System.out.println("\n" + borde);
            System.out.println("* " + mensaje + " *");
            System.out.println(borde + "\n");

        } else {
            String mensaje = "Artista con identificador " + identificador + " no encontrado.";
            int longitud = mensaje.length();
            String borde = "*".repeat(longitud + 4);

            System.out.println("\n" + borde);
            System.out.println("* " + mensaje + " *");
            System.out.println(borde + "\n");
        }
    }

    /**
     * Busca el artista segun su identificador.
     * @param identificador
     * @return
     */
    private Artista buscarArtista(String identificador) {
        for (Artista artista : artistas) {
            if (artista.getIdentificador().equals(identificador)) {
                return artista;
            }
        }
        return null;
    }

    /**
     * Busca en cada artista un disco que se le pasa por parametro.
     * @param identificador
     * @return
     */
    private Disco buscarDisco(String identificador) {
        List<Disco> discosArtista;
        for (Artista artista : artistas) {
            discosArtista = artista.getDiscos();
            for (Disco disco : discosArtista) {
                if (disco.getNombre().equals(identificador)) {
                    return disco;
                }
            }
        }
        return null;
    }

    /**
     * Muestra el top 10 de canciones por genero musical.
     * @param generoMusical
     * @param archivoSalida
     */
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
            String mensaje = "Error al escribir el archivo: " + e.getMessage();
            int longitud = mensaje.length();
            String borde = "*".repeat(longitud + 4);

            System.out.println("\n" + borde);
            System.out.println("* " + mensaje + " *");
            System.out.println(borde + "\n");
        }
    }

    /**
     * Muestra el reporte de las unidades vendidas por el artista.
     * @param identificadorArtista
     * @param archivoSalida
     */
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
            String mensaje = "Error al escribir el archivo: " + e.getMessage();
            int longitud = mensaje.length();
            String borde = "*".repeat(longitud + 4);

            System.out.println("\n" + borde);
            System.out.println("* " + mensaje + " *");
            System.out.println(borde + "\n");
        }
    }
}
  