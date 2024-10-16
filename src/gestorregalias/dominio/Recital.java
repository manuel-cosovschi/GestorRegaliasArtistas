package gestorregalias.dominio;

import java.util.Date;

public class Recital {
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
}
