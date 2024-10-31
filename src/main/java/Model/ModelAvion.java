package Model;

/**
 * Clase que representa un avión.
 */
public class ModelAvion {
    private String modelo;
    private int numeroAsientos;
    private int velocidadMaxima;
    private boolean activado; // Cambiado a int para representar 1 (activo) o 0 (inactivo)
    private int idAeropuerto;

    /**
     * Constructor de la clase ModelAvion.
     *
     * @param modelo          El modelo del avión.
     * @param numeroAsientos  El número de asientos del avión.
     * @param velocidadMaxima La velocidad máxima del avión.
     * @param activado       El estado del avión (true si está activado, false si no).
     * @param idAeropuerto    El ID del aeropuerto al que está asignado el avión.
     */
    public ModelAvion(String modelo, int numeroAsientos, int velocidadMaxima, Boolean activado, int idAeropuerto) {
        super();
        this.modelo = modelo;
        this.numeroAsientos = numeroAsientos;
        this.velocidadMaxima = velocidadMaxima;
        this.activado = activado;
        this.idAeropuerto = idAeropuerto;
    }

    @Override
    public String toString() {
        return this.modelo;
    }

    /**
     * Obtiene el modelo del avión.
     *
     * @return El modelo del avión.
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Obtiene el número de asientos del avión.
     *
     * @return El número de asientos.
     */
    public int getNumeroAsientos() {
        return numeroAsientos;
    }

    /**
     * Obtiene la velocidad máxima del avión.
     *
     * @return La velocidad máxima.
     */
    public int getVelocidadMaxima() {
        return velocidadMaxima;
    }

    /**
     * Verifica si el avión está activado.
     *
     * @return true si el avión está activado, false en caso contrario.
     */
    public boolean isActivado() {
        return activado;
    }

    /**
     * Obtiene el ID del aeropuerto al que está asignado el avión.
     *
     * @return El ID del aeropuerto.
     */
    public int getIdAeropuerto() {
        return idAeropuerto;
    }
}
