package fabrica.gestiondeproducciones.persistencia;

import fabrica.gestiondeproducciones.dominio.Silo;
import fabrica.gestiondeproducciones.utilidades.Excepciones;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class PersistenciaSilo {

    Conexion conexion = new Conexion();
    Connection con;
    PreparedStatement consulta;
    ResultSet resultado;
    String nombreTabla = "silos";

    public boolean altaSilo(Silo silo) {
        String sql = "INSERT INTO " + nombreTabla + "(codigoSilo, capacidad) VALUES (?,?)";

        try {
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            consulta.setInt(1, silo.getCodigoInterno());
            consulta.setInt(2, silo.getCapacidad());
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

    public List listarSilos() {
        List<Silo> lista = new ArrayList();
        String sql = "SELECT * FROM " + nombreTabla + " WHERE activo = '1'";
        try {
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            resultado = consulta.executeQuery();
            while (resultado.next()) {
                Silo silo = new Silo();
                silo.setId(resultado.getInt("idSilo"));
                silo.setCodigoInterno(resultado.getInt("codigoSilo"));
                silo.setCapacidad(resultado.getInt("capacidad"));
                lista.add(silo);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            return null;
        }
        return lista;
    }

    public boolean bajaSilo(int id) {
        String sql = "UPDATE " + nombreTabla + " SET activo = 0 WHERE idSilo = ?";

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

    public boolean modificarSilo(Silo silo) {
        String sql = "UPDATE " + nombreTabla + " SET codigoSilo = ?, capacidad = ? WHERE idSilo = ?";
        try {
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            consulta.setInt(1, silo.getCodigoInterno());
            consulta.setInt(2, silo.getCapacidad());
            consulta.setInt(3, silo.getId());
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

    public Silo buscarSilo(int id) {
        String sql = "SELECT * FROM " + nombreTabla + " WHERE idSilo =?";
        try {
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            consulta.setInt(1, id);
            resultado = consulta.executeQuery();
            if (resultado.next()) {
                Silo silo = new Silo();
                silo.setId(resultado.getInt("idSilo"));
                silo.setCodigoInterno(resultado.getInt("codigoSilo"));
                silo.setCapacidad(resultado.getInt("capacidad"));
                return silo;
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

    public Silo buscarSiloXCodigo(int codigo) {
        String sql = "SELECT * FROM " + nombreTabla + " WHERE codigoSilo =?";
        try {
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            consulta.setInt(1, codigo);
            resultado = consulta.executeQuery();
            if (resultado.next()) {
                Silo silo = new Silo();
                silo.setId(resultado.getInt("idSilo"));
                silo.setCodigoInterno(resultado.getInt("codigoSilo"));
                silo.setCapacidad(resultado.getInt("capacidad"));
                return silo;
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
