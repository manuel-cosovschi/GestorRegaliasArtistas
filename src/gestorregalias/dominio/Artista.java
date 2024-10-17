package gestorregalias.dominio;

import java.util.ArrayList;
import java.util.List;

public abstract class Artista {
    protected String identificador; // Cadena de 6 caracteres
    protected String nombre;
    protected int cantidadIntegrantes;
    protected String generoMusical;
    protected List<Disco> discos;
    protected List<Recital> recitales;

    public Artista(String identificador, String nombre, int cantidadIntegrantes, String generoMusical) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.cantidadIntegrantes = cantidadIntegrantes;
        this.generoMusical = generoMusical;
        this.discos = new ArrayList<>();
        this.recitales = new ArrayList<>();
    }

    // Métodos getter
    public String getIdentificador() {
        return identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Disco> getDiscos() {
        return discos;
    }

    public List<Recital> getRecitales() {
        return recitales;
    }
}
