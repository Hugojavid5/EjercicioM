package Model;

import java.sql.Blob;
import java.util.Objects;

/**
 * Clase que representa un aeropuerto público, que extiende la clase ModelAeropuerto.
 */
public class ModelAeropuertoPublico extends ModelAeropuerto {
    private float financiacion;
    private int numTrabajadores;

    /**
     * Constructor de la clase ModelAeropuertoPublico.
     *
     * @param nombre             El nombre del aeropuerto público.
     * @param anioInauguracion   El año de inauguración del aeropuerto público.
     * @param capacidad          La capacidad del aeropuerto público.
     * @param direccion          La dirección del aeropuerto público.
     * @param imagen             La imagen del aeropuerto público como un objeto Blob.
     * @param financiacion       La financiación del aeropuerto público.
     * @param numTrabajadores    El número de trabajadores del aeropuerto público.
     */
    public ModelAeropuertoPublico(String nombre, int anioInauguracion, int capacidad, ModelDireccion direccion, Blob imagen, float financiacion, int numTrabajadores) {
        super(nombre, anioInauguracion, capacidad, direccion, imagen);
        this.financiacion = financiacion;
        this.numTrabajadores = numTrabajadores;
    }

    /**
     * Obtiene la financiación del aeropuerto público.
     *
     * @return La financiación.
     */
    public float getFinanciacion() {
        return financiacion;
    }

    /**
     * Obtiene el número de trabajadores del aeropuerto público.
     *
     * @return El número de trabajadores.
     */
    public int getNumTrabajadores() {
        return numTrabajadores;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Objects.hash(financiacion, numTrabajadores);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        ModelAeropuertoPublico other = (ModelAeropuertoPublico) obj;
        // Compara financiacion y numTrabajadores además de los atributos de ModelAeropuerto
        return Float.floatToIntBits(financiacion) == Float.floatToIntBits(other.financiacion)
                && numTrabajadores == other.numTrabajadores;
    }
}
