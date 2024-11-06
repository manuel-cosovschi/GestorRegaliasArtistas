package gestorregalias.dominio;

public class ArtistaEmergente extends Artista {

    public ArtistaEmergente(String identificador, String nombre, int cantidadIntegrantes, String generoMusical) {
        super(identificador, nombre, cantidadIntegrantes, generoMusical,5,5,5);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}



