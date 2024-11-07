package gestorregalias.dominio;

public class ArtistaEmergente extends Artista {

    /**
     * Se define el constructor del artista emergente. Setea los atributos con lo indicado en los parametros.
     * @param identificador
     * @param nombre
     * @param cantidadIntegrantes
     * @param generoMusical
     */
    public ArtistaEmergente(String identificador, String nombre, int cantidadIntegrantes, String generoMusical) {
        super(identificador, nombre, cantidadIntegrantes, generoMusical,5,5,5);
    }

    /**
     * Llama al toString de la clase padre. Muestra todos los atributos del artista.
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Calcula el importe por regalia del artista emergente.
     */
    @Override
    public Float calculaImportePorRegalia(float importe, String actividad) {
        float importeRegalia = 0;
        switch (actividad){
            case "disco":
                importeRegalia = importe * (getPorcentajeRegaliaDisco() / 100);
                break;
            case "cancion":
            importeRegalia = importe * (getPorcentajeRegaliaCancion() / 100);
                break;
            case "recital":
                importeRegalia = importe * (getPorcentajeRegaliaRecital() / 100);
                break;
            default:
        }
        return importeRegalia;
    }
}



