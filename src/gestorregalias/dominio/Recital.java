package gestorregalias.dominio;

import java.io.Serializable;
import java.time.LocalDate;

public class Recital implements Serializable {
    private static final long serialVersionUID = 1L; // Agregar serialVersionUID para la serialización
    private LocalDate fecha;
    private double recaudacion;
    private double costosProduccion;

    public Recital(LocalDate fecha, double recaudacion, double costosProduccion) {
        this.fecha = fecha;
        this.recaudacion = recaudacion;
        this.costosProduccion = costosProduccion;
    }

    public double getNeto() {
        return recaudacion - costosProduccion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return "\nFecha: " + fecha + "\nRecaudacion: " + recaudacion + "\nCosto de Produccion: " + costosProduccion;
    }
}
