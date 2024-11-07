package gestorregalias.dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Artista implements Comparable<Artista>, Serializable {
    private static final long serialVersionUID = 1L; // Agregar serialVersionUID para la serialización
    private String identificador;
    private String nombre;
    private int cantidadIntegrantes;
    private String generoMusical;
    private List<Disco> discos;
    private List<Recital> recitales;
    private float porcentajeRegaliaDisco;
    private float porcentajeRegaliaCancion;
    private float porcentajeRegaliaRecital;
    
    /**
     * Se define el constructor del artista. Setea los atributos con lo indicado en los parametros e inicializa las colecciones.
     * @param identificador
     * @param nombre
     * @param cantidadIntegrantes
     * @param generoMusical
     * @param porcentajeRegaliaCancion
     * @param porcentajeRegaliaDisco
     * @param porcentajeRegaliaRecital
     */
    public Artista(String identificador, String nombre, int cantidadIntegrantes, String generoMusical, float porcentajeRegaliaCancion, float porcentajeRegaliaDisco, float porcentajeRegaliaRecital) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.cantidadIntegrantes = cantidadIntegrantes;
        this.generoMusical = generoMusical;
        this.discos = new ArrayList<>();
        this.recitales = new ArrayList<>();
        this.porcentajeRegaliaCancion = porcentajeRegaliaCancion;
        this.porcentajeRegaliaDisco = porcentajeRegaliaDisco;
        this.porcentajeRegaliaRecital = porcentajeRegaliaRecital;
    }

    // Métodos getter

    /**
     * Devuelve el identificador del artista (es unico).
     * @return
     */
    public String getIdentificador() {
        return identificador;
    }

    /**
     * Devuelve el nombre del artista.
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve la cantidad de integrantes del artista.
     * @return
     */
    public int getCantidadIntegrantes() {
        return cantidadIntegrantes;
    }

    /**
     * Devuelve el genero musical que tiene el artista.
     * @return
     */
    public String getGeneroMusical() {
        return generoMusical;
    }

    /**
     * Devuelve la coleccion de discos del artista.
     * @return
     */
    public List<Disco> getDiscos() {
        return discos;
    }

    /**
     * Devuelve la coleccion de recitales del artista.
     * @return
     */
    public List<Recital> getRecitales() {
        return recitales;
    }

    /**
     * Devuelve el porcentaje de regalia del disco.
     * @return
     */
    public float getPorcentajeRegaliaDisco(){
        return porcentajeRegaliaDisco;
    }

    /**
     * Devuelve el porcentaje de regalia del cancion.
     * @return
     */
    public float getPorcentajeRegaliaCancion(){
        return porcentajeRegaliaCancion;
    }

    /**
     * Devuelve el porcentaje de regalia del recital.
     * @return
     */
    public float getPorcentajeRegaliaRecital(){
        return porcentajeRegaliaRecital;
    }

    /**
     * Redefinicion dle metodo compareto para comparar por el identificador del artista.
     */
    @Override
    public int compareTo(Artista o) {
        return this.identificador.compareTo(o.identificador);
    }

    /**
     * Redefinicion del toString para que muestre los datos del artista.
     */
    @Override
    public String toString() {
        return "Identificador: " + identificador + "\nNombre: " + nombre + "\nCantidad de Integrantes: " + cantidadIntegrantes + "\nGenero Musical: " + generoMusical + muestraDiscos() + muestraRecitales();
    }

    /**
     * Redefinicion del metodo equals para comparar por el identificador del artista.
     */
    @Override
    public boolean equals(Object obj) {
        Artista p = (Artista) obj;
        return this.identificador.equals(p.identificador);
    }

    /**
     * Muestra los diferentes discos de la lista de discos.
     * @return
     */
    private String muestraDiscos(){
        String resultado = "";
        for (Disco disco : discos){
            resultado = resultado + disco.toString();
        }
        return resultado;
    }

    /**
     * Muestra los recitales que estan en la lista de recitales.
     * @return
     */
    private String muestraRecitales(){
        String resultado = "";
        for (Recital recital : recitales){
            resultado = resultado + recital.toString();
        }
        return resultado;
    }

    /**
     * Agrega un disco a la lista de discos.
     * @param disco
     */
    public void agregarDisco(Disco disco){
        discos.add(disco);
    }

    /**
     * Agrega un recital a la lista de recitales.
     * @param recital
     */
    public void agregarRecital(Recital recital){
        recitales.add(recital);
    }

    /**
     * Funcion abstracta que calcula el importe por regalia.
     * @param importe
     * @param actividad
     * @return
     */
    public abstract Float calculaImportePorRegalia(float importe, String actividad);
}
