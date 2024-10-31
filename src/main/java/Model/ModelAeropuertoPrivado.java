package Model;

import java.sql.Blob;
import java.util.Objects;

/**
 * Clase que representa un aeropuerto privado, que extiende la clase ModelAeropuerto.
 */
public class ModelAeropuertoPrivado extends ModelAeropuerto {

    private int numSocios;

    /**
     * Constructor de la clase ModelAeropuertoPrivado.
     *
     * @param nombre             El nombre del aeropuerto privado.
     * @param anioInauguracion   El año de inauguración del aeropuerto privado.
     * @param capacidad          La capacidad del aeropuerto privado.
     * @param direccion          La dirección del aeropuerto privado.
     * @param imagen             La imagen del aeropuerto privado como un objeto Blob.
     * @param numSocios         El número de socios del aeropuerto privado.
     */
    public ModelAeropuertoPrivado(String nombre, int anioInauguracion, int capacidad, ModelDireccion direccion, Blob imagen, int numSocios) {
        super(nombre, anioInauguracion, capacidad, direccion, imagen);
        this.numSocios = numSocios;
    }

    /**
     * Obtiene el número de socios del aeropuerto privado.
     *
     * @return El número de socios.
     */
    public int getNumSocios() {
        return numSocios;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Objects.hash(numSocios);
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
        ModelAeropuertoPrivado other = (ModelAeropuertoPrivado) obj;
        return numSocios == other.numSocios; // Compara numSocios además de los atributos de ModelAeropuerto
    }
}
