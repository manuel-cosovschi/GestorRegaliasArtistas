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
}



