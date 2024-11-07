package gestorregalias.dominio;

import java.io.Serializable;
import java.time.LocalDate;

public class Recital implements Serializable {
    private static final long serialVersionUID = 1L; // Agregar serialVersionUID para la serialización
    private LocalDate fecha;
    private double recaudacion;
    private double costosProduccion;

    /**
     * Se define el constructor del Recital. Setea los atributos con lo indicado en los parametros.
     * @param fecha
     * @param recaudacion
     * @param costosProduccion
     */
    public Recital(LocalDate fecha, double recaudacion, double costosProduccion) {
        this.fecha = fecha;
        this.recaudacion = recaudacion;
        this.costosProduccion = costosProduccion;
    }

    /**
     * Devuelve el Neto ganado del recital. La cuenta es recaudacion - los costos de produccion.
     * @return
     */
    public double getNeto() {
        return recaudacion - costosProduccion;
    }

    /**
     * Devuelve la fecha del recital.
     * @return
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Devuelve el total recaudado del recital.
     * @return
     */
    public double getRecaudacion() {
        return recaudacion;
    }

    /**
     * Devuelve el costo de la produccion del recital.
     * @return
     */
    public double getCostosProduccion() {
        return costosProduccion;
    }

    /**
     * Se redefine el toString para mostrar los datos del recital.
     */
    @Override
    public String toString() {
        return "\nFecha: " + fecha + "\nRecaudacion: " + recaudacion + "\nCosto de Produccion: " + costosProduccion;
    }
}
