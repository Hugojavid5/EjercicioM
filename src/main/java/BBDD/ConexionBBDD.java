package BBDD;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Clase que gestiona la conexión a la base de datos.
 */
public class ConexionBBDD {
    private static Connection connection;

    /**
     * Constructor de la clase ConexionBBDD que inicializa la conexión a la base de datos.
     *
     * @throws SQLException Si ocurre un error al establecer la conexión.
     */
    public ConexionBBDD() throws SQLException {
        Properties connConfig = loadProperties();
        String url = connConfig.getProperty("dburl");
        connection = DriverManager.getConnection(url, connConfig);
        connection.setAutoCommit(true);
    }

    /**
     * Obtiene la conexión a la base de datos.
     *
     * @return La conexión a la base de datos.
     */
    public static Connection getConnection() {
        return connection;
    }

    /**
     * Cierra la conexión a la base de datos.
     *
     * @return La conexión cerrada.
     * @throws SQLException Si ocurre un error al cerrar la conexión.
     */
    public Connection CloseConexion() throws SQLException {
        connection.close(); // Cierra la conexión
        return connection;
    }

    /**
     * Carga las propiedades de configuración de la base de datos desde un archivo.
     *
     * @return Un objeto Properties que contiene las configuraciones de conexión.
     */
    public static Properties loadProperties() {
        try (FileInputStream fs = new FileInputStream("configuration.properties")) {
            Properties props = new Properties();
            props.load(fs);
            return props;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
