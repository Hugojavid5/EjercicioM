package Model;

/**
 * Clase que representa un usuario con un nombre de usuario y una contraseña.
 */
public class ModelUsuario {
    private String usuario;
    private String password;

    /**
     * Constructor de la clase ModelUsuario.
     *
     * @param usuario  El nombre de usuario.
     * @param password La contraseña del usuario.
     */
    public ModelUsuario(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    /**
     * Obtiene el nombre de usuario.
     *
     * @return El nombre de usuario.
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Establece el nombre de usuario.
     *
     * @param usuario El nuevo nombre de usuario.
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return La contraseña del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param password La nueva contraseña del usuario.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
