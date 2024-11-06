package gestorregalias.dominio;

import java.time.LocalDate;

public class ArtistaConsagrado extends Artista {
    private LocalDate fechaDeConsagracion;

    public ArtistaConsagrado(String identificador, String nombre, int cantidadIntegrantes, String generoMusical) {
        super(identificador, nombre, cantidadIntegrantes, generoMusical,10,20,20);
        
        fechaDeConsagracion = java.time.LocalDate.now();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}