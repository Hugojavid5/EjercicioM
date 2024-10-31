package Dao;

import java.sql.*;
import BBDD.ConexionBBDD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Model.ModelAeropuerto;

/**
 * Clase que gestiona las operaciones de acceso a datos para la entidad Aeropuerto.
 */
public class DaoAeropuerto {
    private static Connection connection;

    /**
     * Constructor de la clase DaoAeropuerto.
     *
     * @throws SQLException Si ocurre un error al inicializar la conexión.
     */
    public DaoAeropuerto() throws SQLException {
    }

    /**
     * Inserta un nuevo aeropuerto en la base de datos.
     *
     * @param nombre             El nombre del aeropuerto.
     * @param direccionId        El ID de la dirección asociada.
     * @param anioInauguracion   El año de inauguración del aeropuerto.
     * @param capacidad          La capacidad del aeropuerto.
     * @param trabajadores       El número de trabajadores del aeropuerto.
     * @param financiacion       La cantidad de financiación del aeropuerto.
     * @return El ID del aeropuerto insertado.
     * @throws SQLException Si ocurre un error al insertar el aeropuerto.
     */
    public static int insertarAeropuerto(String nombre, int direccionId, int anioInauguracion, int capacidad, int trabajadores, double financiacion) throws SQLException {
        String sql = "INSERT INTO aeropuertos (nombre, direccion_id, anio_inauguracion, capacidad, num_trabajadores, financiacion) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBBDD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, nombre);
            pstmt.setInt(2, direccionId);
            pstmt.setInt(3, anioInauguracion);
            pstmt.setInt(4, capacidad);
            pstmt.setInt(5, trabajadores);
            pstmt.setDouble(6, financiacion);
            pstmt.executeUpdate();
            // Obtener el ID generado
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // Devuelve el ID del aeropuerto
                } else {
                    throw new SQLException("No se pudo obtener el ID del aeropuerto.");
                }
            }
        }
    }

    /**
     * Obtiene una lista de todos los aeropuertos, tanto públicos como privados.
     *
     * @return Una lista observable de todos los aeropuertos.
     */
    public static ObservableList<ModelAeropuerto> listaTodas() {
        ObservableList<ModelAeropuerto> lst = FXCollections.observableArrayList();
        lst.addAll(DaoAeropuertoPrivado.cargarListaAeropuertosPrivados());
        lst.addAll(DaoAeropuertoPublico.cargarListaAeropuertosPublicos());
        return lst;
    }

    /**
     * Añade un nuevo aeropuerto a la base de datos.
     *
     * @param nombre             El nombre del aeropuerto.
     * @param anioInauguracion   El año de inauguración del aeropuerto.
     * @param capacidad          La capacidad del aeropuerto.
     * @param idDireccion        El ID de la dirección asociada.
     * @param imagen             La imagen del aeropuerto en forma de Blob.
     */
    public static void aniadir(String nombre, int anioInauguracion, int capacidad, int idDireccion, Blob imagen) {
        connection = ConexionBBDD.getConnection();
        String insert = "INSERT INTO aeropuertos (nombre, anio_inauguracion, capacidad, id_direccion, imagen) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement pstmt;
            pstmt = connection.prepareStatement(insert);
            pstmt.setString(1, nombre);
            pstmt.setInt(2, anioInauguracion);
            pstmt.setInt(3, capacidad);
            pstmt.setInt(4, idDireccion);
            pstmt.setBlob(5, imagen);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Elimina un aeropuerto de la base de datos dado su ID.
     *
     * @param id El ID del aeropuerto a eliminar.
     */
    public static void eliminar(int id) {
        connection = ConexionBBDD.getConnection();
        String delete = "DELETE FROM aeropuertos WHERE id=?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(delete);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Consigue el ID de un aeropuerto basado en sus atributos.
     *
     * @param nombre             El nombre del aeropuerto.
     * @param anioInauguracion   El año de inauguración del aeropuerto.
     * @param capacidad          La capacidad del aeropuerto.
     * @param idDireccion        El ID de la dirección asociada.
     * @param imagen             La imagen del aeropuerto en forma de Blob.
     * @return El ID del aeropuerto encontrado, o null si no se encuentra.
     */
    public static Integer conseguirID(String nombre, int anioInauguracion, int capacidad, int idDireccion, Blob imagen) {
        connection = ConexionBBDD.getConnection();
        String select = "SELECT id FROM aeropuertos WHERE nombre=? AND anio_inauguracion=? AND capacidad=? AND id_direccion=?";
        try {
            PreparedStatement pstmt;
            pstmt = connection.prepareStatement(select);
            pstmt.setString(1, nombre);
            pstmt.setInt(2, anioInauguracion);
            pstmt.setInt(3, capacidad);
            pstmt.setInt(4, idDireccion);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Modifica un aeropuerto existente en la base de datos.
     *
     * @param id                 El ID del aeropuerto a modificar.
     * @param nombre             El nuevo nombre del aeropuerto.
     * @param anioInauguracion   El nuevo año de inauguración del aeropuerto.
     * @param capacidad          La nueva capacidad del aeropuerto.
     * @param idDireccion        El nuevo ID de la dirección asociada.
     * @param imagen             La nueva imagen del aeropuerto en forma de Blob.
     */
    public static void modificarPorId(int id, String nombre, int anioInauguracion, int capacidad, int idDireccion, Blob imagen) {
        connection = ConexionBBDD.getConnection();
        String update = "UPDATE aeropuertos SET nombre=?, anio_inauguracion=?, capacidad=?, id_direccion=?, imagen=? WHERE id=?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(update);
            pstmt.setString(1, nombre);
            pstmt.setInt(2, anioInauguracion);
            pstmt.setInt(3, capacidad);
            pstmt.setInt(4, idDireccion);
            pstmt.setBlob(5, imagen);
            pstmt.setInt(6, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
