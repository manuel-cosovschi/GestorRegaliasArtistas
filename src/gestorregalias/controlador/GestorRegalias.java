package gestorregalias.controlador;

import gestorregalias.dominio.*;
import java.util.Comparator;
import java.util.TreeSet;

public class GestorRegalias {
    private TreeSet<Artista> artistas;

    public GestorRegalias() {
        this.artistas = new TreeSet<>(Comparator.comparing(Artista::getIdentificador));
    }

    
    public void cargarDatos(String archivo) {
        
    }

    
    public void listarArtistas() {
        artistas.forEach(artista -> System.out.println(artista.getIdentificador() + " - " + artista.getNombre()));
    }
}
