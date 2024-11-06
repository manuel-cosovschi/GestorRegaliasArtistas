package gestorregalias.dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Disco implements Serializable {
    private static final long serialVersionUID = 1L; // Agregar serialVersionUID para la serialización
    private String nombre;
    private int unidadesVendidas;
    private List<Cancion> canciones;

    public Disco(String nombre, int unidadesVendidas) {
        this.nombre = nombre;
        this.unidadesVendidas = unidadesVendidas;
        this.canciones = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public int getUnidadesVendidas() {
        return unidadesVendidas;
    }

    public List<Cancion> getCanciones() {
        return canciones;
    }    

    @Override
    public String toString() {
        return "\nNombre: " + nombre + "\nUnidadesVendidas: " + unidadesVendidas + muestraCanciones();
    }

    private String muestraCanciones(){
        String resultado = "";
        for (Cancion cancion : canciones){
            resultado = resultado + cancion.toString();
        }
        return resultado;
    }
}
