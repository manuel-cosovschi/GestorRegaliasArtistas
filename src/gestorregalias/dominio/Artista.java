package gestorregalias.dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Artista implements Comparable<Artista>, Serializable {
    private static final long serialVersionUID = 1L; // Agregar serialVersionUID para la serialización
    private String identificador;
    private String nombre;
    private int cantidadIntegrantes;
    private String generoMusical;
    private List<Disco> discos;
    private List<Recital> recitales;

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

    public int getCantidadIntegrantes() {
        return cantidadIntegrantes;
    }

    public String getGeneroMusical() {
        return generoMusical;
    }

    public List<Disco> getDiscos() {
        return discos;
    }

    public List<Recital> getRecitales() {
        return recitales;
    }

    @Override
    public int compareTo(Artista o) {
        return this.identificador.compareTo(o.identificador);
    }

    @Override
    public String toString() {
        return "Identificador: " + identificador + "\nNombre: " + nombre + "\nCantidad de Integrantes: " + cantidadIntegrantes + "\nGenero Musical: " + generoMusical + muestraDiscos() + muestraRecitales();
    }

    @Override
    public boolean equals(Object obj) {
        Artista p = (Artista) obj;
        return this.identificador.equals(p.identificador);
    }

    private String muestraDiscos(){
        String resultado = "";
        for (Disco disco : discos){
            resultado = resultado + disco.toString();
        }
        return resultado;
    }

    private String muestraRecitales(){
        String resultado = "";
        for (Recital recital : recitales){
            resultado = resultado + recital.toString();
        }
        return resultado;
    }
}
