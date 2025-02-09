package fabrica.gestiondeproducciones.persistencia;

import fabrica.gestiondeproducciones.dominio.Insumo;
import fabrica.gestiondeproducciones.utilidades.Excepciones;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class PersistenciaInsumo {

    Conexion conexion = new Conexion();
    Connection con;
    PreparedStatement consulta;
    ResultSet resultado;
    String nombreTabla = "insumo";

    public boolean altaInsumo(Insumo insumo) {
        String sql = "INSERT INTO " + nombreTabla + "(nombre, descripcion, unidad) VALUES (?,?,?)";

        try {
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            consulta.setString(1, insumo.getNombre());
            consulta.setString(2, insumo.getDescripcion());
            consulta.setString(3, insumo.getUnidad());

            consulta.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            }
        }
    }

    public List listarInsumos() {
        List<Insumo> lista = new ArrayList();
        String sql = "SELECT * FROM " + nombreTabla + " WHERE activo = '1'";
        try {
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            resultado = consulta.executeQuery();
            while (resultado.next()) {
                Insumo insumo = new Insumo();
                insumo.setId(resultado.getInt("idInsumo"));
                insumo.setNombre(resultado.getString("nombre"));
                insumo.setDescripcion(resultado.getString("descripcion"));
                insumo.setUnidad(resultado.getString("unidad"));

                lista.add(insumo);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            return null;
        }
        return lista;
    }

    public boolean bajaInsumo(int id) {
        String sql = "UPDATE " + nombreTabla + " SET activo = 0 WHERE idInsumo = ?";

        try {
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            consulta.setInt(1, id);
            consulta.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            }
        }
    }

    public boolean modificarInsumo(Insumo insumo) {
        String sql = "UPDATE " + nombreTabla + " SET nombre = ?, descripcion = ?, unidad = ? WHERE idInsumo = ?";
        try {
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            consulta.setString(1, insumo.getNombre());
            consulta.setString(2, insumo.getDescripcion());
            consulta.setString(3, insumo.getUnidad());
            consulta.setInt(4, insumo.getId());
            consulta.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            }
        }

    }

    public Insumo buscarInsumo(int id) {
        String sql = "SELECT * FROM " + nombreTabla + " WHERE idInsumo =?";
        try {
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            consulta.setInt(1, id);
            resultado = consulta.executeQuery();
            if (resultado.next()) {
                Insumo insumo = new Insumo();
                insumo.setId(resultado.getInt("idInsumo"));
                insumo.setNombre(resultado.getString("nombre"));
                insumo.setDescripcion(resultado.getString("descripcion"));
                insumo.setUnidad(resultado.getString("unidad"));
                return insumo;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            return null;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            }
        }
        return null;
    }

}
