package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Model.ModelAvion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import BBDD.ConexionBBDD;

/**
 * Clase que gestiona las operaciones de acceso a datos para la entidad Avión.
 */
public class DaoAvion {
    private static Connection conection;

    /**
     * Carga una lista de aviones asociados a un aeropuerto específico.
     *
     * @param id El ID del aeropuerto cuyos aviones se quieren cargar.
     * @return Una lista observable de ModelAvion.
     */
    public static ObservableList<ModelAvion> listaAviones(int id) {
        conection = ConexionBBDD.getConnection();
        String select = "SELECT modelo, numero_asientos, velocidad_maxima, activado FROM aviones WHERE id_aeropuerto=?";
        ObservableList<ModelAvion> lst = FXCollections.observableArrayList();
        try {
            PreparedStatement pstmt = conection.prepareStatement(select);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ModelAvion avion = new ModelAvion(rs.getString("modelo"),
                        rs.getInt("numero_asientos"),
                        rs.getInt("velocidad_maxima"),
                        rs.getInt("activado") == 1,
                        id);
                lst.add(avion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lst;
    }

    /**
     * Consigue una lista de todos los aviones en la base de datos.
     *
     * @return Una lista de todos los ModelAvion.
     */
    public static ArrayList<ModelAvion> conseguirListaTodos() {
        ArrayList<ModelAvion> lst = new ArrayList<ModelAvion>();
        conection = ConexionBBDD.getConnection();
        String select = "SELECT modelo, numero_asientos, velocidad_maxima, activado, id_aeropuerto FROM aviones";
        try {
            PreparedStatement pstmt = conection.prepareStatement(select);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ModelAvion avion = new ModelAvion(rs.getString("modelo"),
                        rs.getInt("numero_asientos"),
                        rs.getInt("velocidad_maxima"),
                        rs.getInt("activado") == 1,
                        rs.getInt("id_aeropuerto"));
                lst.add(avion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lst;
    }

    /**
     * Añade un nuevo avión a la base de datos.
     *
     * @param modelo      El modelo del avión.
     * @param numAsientos El número de asientos del avión.
     * @param velMax     La velocidad máxima del avión.
     * @param activado   Indica si el avión está activado.
     * @param idAeropuerto El ID del aeropuerto asociado al avión.
     */
    public static void aniadir(String modelo, int numAsientos, int velMax, boolean activado, int idAeropuerto) {
        conection = ConexionBBDD.getConnection();
        String insert = "INSERT INTO aviones (modelo, numero_asientos, velocidad_maxima, activado, id_aeropuerto) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement pstmt = conection.prepareStatement(insert);
            pstmt.setString(1, modelo);
            pstmt.setInt(2, numAsientos);
            pstmt.setInt(3, velMax);
            pstmt.setInt(4, activado ? 1 : 0);
            pstmt.setInt(5, idAeropuerto);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Actualiza el estado de un avión (activado/desactivado) en la base de datos.
     *
     * @param modelo      El modelo del avión a actualizar.
     * @param idAeropuerto El ID del aeropuerto al que pertenece el avión.
     * @param estado      El nuevo estado del avión (activado o desactivado).
     */
    public static void update(String modelo, int idAeropuerto, boolean estado) {
        conection = ConexionBBDD.getConnection();
        String update = "UPDATE aviones SET activado=? WHERE modelo=? AND id_aeropuerto=?";
        try {
            PreparedStatement pstmt = conection.prepareStatement(update);
            pstmt.setInt(1, estado ? 1 : 0);
            pstmt.setString(2, modelo);
            pstmt.setInt(3, idAeropuerto);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Elimina un avión de la base de datos dado su modelo y el ID del aeropuerto.
     *
     * @param modelo      El modelo del avión a eliminar.
     * @param idAeropuerto El ID del aeropuerto al que pertenece el avión.
     */
    public static void delete(String modelo, int idAeropuerto) {
        conection = ConexionBBDD.getConnection();
        String delete = "DELETE FROM aviones WHERE modelo=? AND id_aeropuerto=?";
        try {
            PreparedStatement pstmt = conection.prepareStatement(delete);
            pstmt.setString(1, modelo);
            pstmt.setInt(2, idAeropuerto);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
