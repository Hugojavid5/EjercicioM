package Model;

/**
 * Clase que representa una dirección.
 */
public class ModelDireccion {
    private String pais;
    private String ciudad;
    private String calle;
    private int numero;

    /**
     * Constructor de la clase ModelDireccion.
     *
     * @param pais   El país de la dirección.
     * @param ciudad La ciudad de la dirección.
     * @param calle  La calle de la dirección.
     * @param numero El número de la dirección.
     */
    public ModelDireccion(String pais, String ciudad, String calle, int numero) {
        super();
        this.pais = pais;
        this.ciudad = ciudad;
        this.calle = calle;
        this.numero = numero;
    }

    /**
     * Obtiene el país de la dirección.
     *
     * @return El país de la dirección.
     */
    public String getPais() {
        return pais;
    }

    /**
     * Obtiene la ciudad de la dirección.
     *
     * @return La ciudad de la dirección.
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Obtiene la calle de la dirección.
     *
     * @return La calle de la dirección.
     */
    public String getCalle() {
        return calle;
    }

    /**
     * Obtiene el número de la dirección.
     *
     * @return El número de la dirección.
     */
    public int getNumero() {
        return numero;
    }
}
