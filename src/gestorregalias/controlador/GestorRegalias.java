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
                if (datos.length == 5) { // suposición: 5 datos por artista
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
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar los datos: " + e.getMessage());
        }
    }

    // Método para listar los artistas
    public void listarArtistas() {
        artistas.forEach(artista -> System.out.println(artista.getIdentificador() + " - " + artista.getNombre()));
    }
}
