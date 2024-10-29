package gestorregalias.dominio;

public class Cancion {
    private String nombre;
    private int duracionMinutos;
    private int duracionSegundos;
    private int reproducciones;
    private boolean esSencillo;

    public Cancion(String nombre, int duracionMinutos, int duracionSegundos, int reproducciones, boolean esSencillo) {
        this.nombre = nombre;
        this.duracionMinutos = duracionMinutos;
        this.duracionSegundos = duracionSegundos;
        this.reproducciones = reproducciones;
        this.esSencillo = esSencillo;
    }

    public int getReproducciones() {
        return reproducciones;
    }    

    public double getReproduccionesParaLiquidacion() {
        return esSencillo ? reproducciones * 1.5 : reproducciones;
    }

    @Override
    public String toString() {
        return "\nNombre: " + nombre + "\nDuracion Minutos: " + duracionMinutos + "\nDuracion Segundos: " + duracionSegundos + "\nCantidad Reproducciones: " + reproducciones + "\nEs Sencillo? " + esSencillo;
    }
}
