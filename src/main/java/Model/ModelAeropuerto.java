package Model;

import java.sql.Blob;
import java.util.Objects;

/**
 * Clase que representa un aeropuerto.
 */
public class ModelAeropuerto {
    private int id;
    private String nombre;
    private int anioInauguracion;
    private int capacidad;
    private ModelDireccion direccion;
    private Blob imagen;

    /**
     * Constructor de la clase ModelAeropuerto.
     *
     * @param nombre             El nombre del aeropuerto.
     * @param anioInauguracion   El año de inauguración del aeropuerto.
     * @param capacidad          La capacidad del aeropuerto.
     * @param direccion          La dirección del aeropuerto.
     * @param imagen             La imagen del aeropuerto como un objeto Blob.
     */
    public ModelAeropuerto(String nombre, int anioInauguracion, int capacidad, ModelDireccion direccion, Blob imagen) {
        super();
        this.nombre = nombre;
        this.anioInauguracion = anioInauguracion;
        this.capacidad = capacidad;
        this.direccion = direccion;
        this.imagen = imagen;
    }
    public void setImagen(Blob imagen) {
        this.imagen = imagen;
    }

    /**
     * Obtiene el ID del aeropuerto.
     *
     * @return El ID del aeropuerto.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID del aeropuerto.
     *
     * @param id El ID a establecer.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del aeropuerto.
     *
     * @return El nombre del aeropuerto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el año de inauguración del aeropuerto.
     *
     * @return El año de inauguración.
     */
    public int getAnioInauguracion() {
        return anioInauguracion;
    }

    /**
     * Obtiene la capacidad del aeropuerto.
     *
     * @return La capacidad del aeropuerto.
     */
    public int getCapacidad() {
        return capacidad;
    }

    /**
     * Obtiene la dirección del aeropuerto.
     *
     * @return Un objeto ModelDireccion que representa la dirección del aeropuerto.
     */
    public ModelDireccion getDireccion() {
        return direccion;
    }

    /**
     * Obtiene la imagen del aeropuerto.
     *
     * @return Un objeto Blob que representa la imagen del aeropuerto.
     */
    public Blob getImagen() {
        return imagen;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ModelAeropuerto other = (ModelAeropuerto) obj;
        return anioInauguracion == other.anioInauguracion && capacidad == other.capacidad
                && Objects.equals(direccion, other.direccion) && id == other.id
                && Objects.equals(imagen, other.imagen) && Objects.equals(nombre, other.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(anioInauguracion, capacidad, direccion, id, imagen, nombre);
    }

    @Override
    public String toString() {
        return this.nombre;
    }
}
