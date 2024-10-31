package Dao;

import java.sql.*;
import BBDD.ConexionBBDD;
import Model.ModelDireccion;

/**
 * Clase que gestiona las operaciones de acceso a datos para la entidad Dirección.
 */
public class DaoDireccion {

    private static Connection conection;

    /**
     * Crea un modelo de dirección a partir de un ID.
     *
     * @param id El ID de la dirección que se desea recuperar.
     * @return Un objeto ModelDireccion correspondiente a la dirección encontrada, o null si no se encuentra.
     */
    public static ModelDireccion crearModeloDireccionPorID(int id) {
        conection = ConexionBBDD.getConnection();
        String select = "SELECT pais, ciudad, calle, numero FROM direcciones WHERE id=?";
        try {
            PreparedStatement pstmt = conection.prepareStatement(select);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new ModelDireccion(rs.getString("pais"),
                        rs.getString("ciudad"),
                        rs.getString("calle"),
                        rs.getInt("numero"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Inserta una nueva dirección en la base de datos y devuelve su ID generado.
     *
     * @param direccion La dirección a insertar.
     * @return El ID de la dirección insertada.
     * @throws SQLException Si ocurre un error al ejecutar la inserción.
     */
    public static int insertarDireccion(ModelDireccion direccion) throws SQLException {
        String sql = "INSERT INTO direcciones (calle, ciudad, pais, numero) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionBBDD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, direccion.getCalle());
            pstmt.setString(2, direccion.getCiudad());
            pstmt.setString(3, direccion.getPais());
            pstmt.setInt(4, direccion.getNumero());
            pstmt.executeUpdate();
            // Obtener el ID generado
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // Devuelve el ID de la dirección
                } else {
                    throw new SQLException("No se pudo obtener el ID de la dirección.");
                }
            }
        }
    }

    /**
     * Añade una nueva dirección a la base de datos.
     *
     * @param pais   El país de la dirección.
     * @param ciudad La ciudad de la dirección.
     * @param calle  La calle de la dirección.
     * @param numero El número de la dirección.
     */
    public static void aniadir(String pais, String ciudad, String calle, int numero) {
        conection = ConexionBBDD.getConnection();
        String insert = "INSERT INTO direcciones (pais, ciudad, calle, numero) VALUES (?,?,?,?)";
        try {
            PreparedStatement pstmt = conection.prepareStatement(insert);
            pstmt.setString(1, pais);
            pstmt.setString(2, ciudad);
            pstmt.setString(3, calle);
            pstmt.setInt(4, numero);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Consigue el ID de una dirección dada su información.
     *
     * @param pais   El país de la dirección.
     * @param ciudad La ciudad de la dirección.
     * @param calle  La calle de la dirección.
     * @param numero El número de la dirección.
     * @return El ID de la dirección encontrada, o null si no se encuentra.
     */
    public static Integer conseguirID(String pais, String ciudad, String calle, int numero) {
        conection = ConexionBBDD.getConnection();
        String select = "SELECT id FROM direcciones WHERE pais=? AND ciudad=? AND calle=? AND numero=?";
        try {
            PreparedStatement pstmt = conection.prepareStatement(select);
            pstmt.setString(1, pais);
            pstmt.setString(2, ciudad);
            pstmt.setString(3, calle);
            pstmt.setInt(4, numero);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
