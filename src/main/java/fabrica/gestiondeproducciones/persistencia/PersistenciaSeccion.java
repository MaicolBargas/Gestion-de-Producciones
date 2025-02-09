package fabrica.gestiondeproducciones.persistencia;

import fabrica.gestiondeproducciones.dominio.Seccion;
import fabrica.gestiondeproducciones.utilidades.Excepciones;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class PersistenciaSeccion {

    Conexion conexion = new Conexion();
    Connection con;
    PreparedStatement consulta;
    ResultSet resultado;
    String nombreTabla = "secciones";

    public boolean altaSeccion(Seccion seccion) {
        String sql = "INSERT INTO " + nombreTabla + "(nombre, descripcion) VALUES (?,?)";

        try {
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            consulta.setString(1, seccion.getNombre());
            consulta.setString(2, seccion.getDescripcion());
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

    public List listarSecciones() {
        List<Seccion> lista = new ArrayList();
        String sql = "SELECT * FROM " + nombreTabla + " WHERE activo = '1'";
        try {
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            resultado = consulta.executeQuery();
            while (resultado.next()) {
                Seccion seccion = new Seccion();
                seccion.setId(resultado.getInt("idSeccion"));
                seccion.setNombre(resultado.getString("nombre"));
                seccion.setDescripcion(resultado.getString("descripcion"));
                lista.add(seccion);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
            return null;
        }
        return lista;
    }

    public boolean bajaSeccion(int id) {
        String sql = "UPDATE " + nombreTabla + " SET activo = 0 WHERE idSeccion = ?";

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

    public boolean modificarSeccion(Seccion seccion) {
        String sql = "UPDATE " + nombreTabla + " SET nombre = ?, descripcion = ? WHERE idSeccion = ?";
        try {
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            consulta.setString(1, seccion.getNombre());
            consulta.setString(2, seccion.getDescripcion());
            consulta.setInt(3, seccion.getId());
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

    public Seccion buscarSeccion(int id) {
        String sql = "SELECT * FROM " + nombreTabla + " WHERE idSeccion =?";
        try {
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            consulta.setInt(1, id);
            resultado = consulta.executeQuery();
            if (resultado.next()) {
                Seccion seccion = new Seccion();
                seccion.setId(resultado.getInt("idSeccion"));
                seccion.setNombre(resultado.getString("nombre"));
                seccion.setDescripcion(resultado.getString("descripcion"));
                return seccion;
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
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
