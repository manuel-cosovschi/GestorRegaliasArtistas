package gestorregalias.dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Disco implements Serializable {
    private static final long serialVersionUID = 1L; // Agregar serialVersionUID para la serialización
    private String nombre;
    private int unidadesVendidas;
    private float precioVentaXUnidad;
    private List<Cancion> canciones;

    /**
     * Se define el constructor del disco. Setea los atributos con lo indicado en los parametros.
     * @param nombre
     * @param unidadesVendidas
     * @param precioVentaXUnidad
     */
    public Disco(String nombre, int unidadesVendidas, float precioVentaXUnidad) {
        this.nombre = nombre;
        this.unidadesVendidas = unidadesVendidas;
        this.precioVentaXUnidad = precioVentaXUnidad;
        this.canciones = new ArrayList<>();
    }

    /**
     * Devuelve el nombre del disco.
     * @return
     */
    public String getNombre() {
        return nombre;
    }

        /**
         * Devuelve la cantidad de discos vendidos (unidades vendidas).
         * @return
         */
    public int getUnidadesVendidas() {
        return unidadesVendidas;
    }

    /**
     * Devuelve la lista de canciones que tiene el disco.
     * @return
     */
    public List<Cancion> getCanciones() {
        return canciones;
    } 

    /**
     * Devuelve el precio de venta por unidad de disco.
     * @return
     */
    public float getprecioXVentaUnidad(){
        return precioVentaXUnidad;
    }

    /**
     * Se redefine el metoodo toString para mostrar todos los datos del disco.
     */
    @Override
    public String toString() {
        return "\nNombre: " + nombre + "\nUnidadesVendidas: " +  unidadesVendidas + "\nPrecio de venta por unidad: " + precioVentaXUnidad + muestraCanciones();
    }

    /**
     * Muestra todas las canciones que tiene el disco.
     * @return
     */
    private String muestraCanciones(){
        String resultado = "";
        for (Cancion cancion : canciones){
            resultado = resultado + cancion.toString();
        }
        return resultado;
    }

    /**
     * Metodo para agregar una cancion a la lista de canciones del disco.
     * @param cancion
     */
    public void agregarCancion(Cancion cancion){
        canciones.add(cancion);
    }
}
