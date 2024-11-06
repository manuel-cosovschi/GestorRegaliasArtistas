package gestorregalias.dominio;

import java.io.Serializable;
import java.util.Date;

public class Recital implements Serializable {
    private static final long serialVersionUID = 1L; // Agregar serialVersionUID para la serialización
    private Date fecha;
    private double recaudacion;
    private double costosProduccion;

    public Recital(Date fecha, double recaudacion, double costosProduccion) {
        this.fecha = fecha;
        this.recaudacion = recaudacion;
        this.costosProduccion = costosProduccion;
    }

    public double getNeto() {
        return recaudacion - costosProduccion;
    }

    public Date getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return "\nFecha: " + fecha + "\nRecaudacion: " + recaudacion + "\nCosto de Produccion: " + costosProduccion;
    }
}
