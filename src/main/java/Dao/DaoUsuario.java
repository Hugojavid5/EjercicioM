package Dao;

import BBDD.ConexionBBDD;
import Model.ModelUsuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase que gestiona las operaciones de acceso a datos para la entidad Usuario.
 */
public class DaoUsuario {

    /**
     * Obtiene un usuario de la base de datos a partir de su nombre de usuario.
     *
     * @param nombreUsuario El nombre del usuario que se desea recuperar.
     * @return Un objeto ModelUsuario correspondiente al usuario encontrado, o null si no se encuentra.
     */
    public static ModelUsuario getUsuario(String nombreUsuario) {
        Connection connection = null;
        ModelUsuario usuario = null;
        String sql = "SELECT * FROM usuarios WHERE usuario = ?";
        try {
            // Establecer la conexión a la base de datos
            ConexionBBDD cBD = new ConexionBBDD();
            connection = ConexionBBDD.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, nombreUsuario);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String usuarioNombre = rs.getString("usuario");
                String password = rs.getString("password");
                usuario = new ModelUsuario(usuarioNombre, password);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el usuario: " + e.getMessage());
        } finally {
            // Cerrar la conexión después de usarla
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
            return usuario;
        }
    }
}
