package fabrica.gestiondeproducciones.persistencia;

import fabrica.gestiondeproducciones.dominio.Tambo;
import fabrica.gestiondeproducciones.utilidades.Excepciones;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class PersistenciaTambo {

    Conexion conexion = new Conexion();
    Connection con;
    PreparedStatement consulta;
    ResultSet resultado;
    String nombreTabla = "tambo";

    public boolean altaTambo(Tambo tambo) {
        String sql = "INSERT INTO " + nombreTabla + "(nombrePropietario, contacto, direccion) VALUES (?,?,?)";

        try {
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            consulta.setString(1, tambo.getPropietario());
            consulta.setString(2, tambo.getContacto());
            consulta.setString(3, tambo.getDireccion());
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

    public List listarTambos() {
        List<Tambo> lista = new ArrayList();
        String sql = "SELECT * FROM " + nombreTabla + " WHERE activo = '1'";
        try {
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            resultado = consulta.executeQuery();
            while (resultado.next()) {
                Tambo tambo = new Tambo();
                tambo.setId(resultado.getInt("idTambo"));
                tambo.setPropietario(resultado.getString("nombrePropietario"));
                tambo.setContacto(resultado.getString("contacto"));
                tambo.setDireccion(resultado.getString("direccion"));
                lista.add(tambo);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            return null;
        }
        return lista;
    }

    public boolean bajaTambo(int id) {
        String sql = "UPDATE " + nombreTabla + " SET activo = 0 WHERE idTambo = ?";

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

    public boolean modificarTambo(Tambo tambo) {
        String sql = "UPDATE " + nombreTabla + " SET nombrePropietario = ?, contacto = ?, direccion = ? WHERE idTambo = ?";
        try {
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            consulta.setString(1, tambo.getPropietario());
            consulta.setString(2, tambo.getContacto());
            consulta.setString(3, tambo.getDireccion());
            consulta.setInt(4, tambo.getId());
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

    public Tambo buscarTambo(int id) {
        String sql = "SELECT * FROM " + nombreTabla + " WHERE idTambo =?";
        try {
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            consulta.setInt(1, id);
            resultado = consulta.executeQuery();
            if (resultado.next()) {
                Tambo tambo = new Tambo();
                tambo.setId(resultado.getInt("idTambo"));
                tambo.setPropietario(resultado.getString("nombrePropietario"));
                tambo.setContacto(resultado.getString("contacto"));
                tambo.setDireccion(resultado.getString("direccion"));
                return tambo;
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
