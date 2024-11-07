package gestorregalias.dominio;

import java.time.LocalDate;

public class ArtistaConsagrado extends Artista {
    private LocalDate fechaDeConsagracion;

    /**
     * Se define el constructor del artista Consagrado. Setea los atributos con lo indicado en los parametros.
     * @param identificador
     * @param nombre
     * @param cantidadIntegrantes
     * @param generoMusical
     */
    public ArtistaConsagrado(String identificador, String nombre, int cantidadIntegrantes, String generoMusical) {
        super(identificador, nombre, cantidadIntegrantes, generoMusical,10,20,20);
        
        fechaDeConsagracion = java.time.LocalDate.now();
    }
    
    /**
     * Se redefine el toString para que también muestre los datos del artista consagrado (en este caso, fecha de consagracion).
     */
    @Override
    public String toString() {
        return super.toString() + "\n Fecha de consagracion: " + fechaDeConsagracion;
    }

    /**
     * Calcula el importe por regalia del artista consagrado. Incluye una bonificacion del 5% para cada regalia por ser consagrado.
     */
    @Override
    public Float calculaImportePorRegalia(float importe, String actividad) {
        float importeRegalia = 0;
        switch (actividad){
            case "disco":
                importeRegalia = importe * ((getPorcentajeRegaliaDisco() + 5) / 100);
                break;
            case "cancion":
            importeRegalia = importe * ((getPorcentajeRegaliaCancion() + 5) / 100);
                break;
            case "recital":
                importeRegalia = importe * ((getPorcentajeRegaliaRecital() + 5) / 100);
                break;
            default:
        }
        return importeRegalia;
    }
}