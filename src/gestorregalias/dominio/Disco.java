package gestorregalias.dominio;

import java.util.ArrayList;
import java.util.List;

public class Disco {
    private String nombre;
    private int unidadesVendidas;
    private List<Cancion> canciones;

    public Disco(String nombre, int unidadesVendidas) {
        this.nombre = nombre;
        this.unidadesVendidas = unidadesVendidas;
        this.canciones = new ArrayList<>();
    }

    // Métodos getter y setter, según sea necesario
    public String getNombre() {
        return nombre;
    }

    public int getUnidadesVendidas() {
        return unidadesVendidas;
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
