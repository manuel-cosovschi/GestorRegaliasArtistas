package gestorregalias.dominio;

public class Cancion {
    private String nombre;
    private int duracionMinutos;
    private int duracionSegundos;
    private int reproducciones;
    private boolean esSencillo;
    private float precioXReproduccion;

    /**
     * Se define el constructor de la cancion. Setea los atributos con lo indicado en los parametros.
     * @param nombre
     * @param duracionMinutos
     * @param duracionSegundos
     * @param reproducciones
     * @param esSencillo
     * @param precioXReproduccion
     */
    public Cancion(String nombre, int duracionMinutos, int duracionSegundos, int reproducciones, boolean esSencillo, float precioXReproduccion) {
        this.nombre = nombre;
        this.duracionMinutos = duracionMinutos;
        this.duracionSegundos = duracionSegundos;
        this.reproducciones = reproducciones;
        this.esSencillo = esSencillo;
        this.precioXReproduccion = precioXReproduccion;
    }

    /**
     * Devuelve la cantidad de reproducciones de la cancion.
     * @return
     */
    public int getReproducciones() {
        return reproducciones;
    }    

    /**
     * Devuelve el nombre de la cancion.
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve la cantidad de reproducciones para calcular la liquidacion. Si es sencillo se multiplica por 1.5.
     * @return
     */
    public double getReproduccionesParaLiquidacion() {
        return esSencillo ? reproducciones * 1.5 : reproducciones;
    }

    /**
     * Devuelve el precio por reproduccion por cancion.
     * @return
     */
    public float getPrecioXReproduccion() {
        return precioXReproduccion;
    }

    /**
     * Se redefine el metodo toString para mostrar todos los atributos de la cancion.
     */
    @Override
    public String toString() {
        return "\nNombre: " + nombre + "\nDuracion Minutos: " + duracionMinutos + "\nDuracion Segundos: " + duracionSegundos + "\nCantidad Reproducciones: " + reproducciones + "\nEs Sencillo? " + esSencillo + "\n Precio por reproduccion: " + precioXReproduccion;
    }
}
