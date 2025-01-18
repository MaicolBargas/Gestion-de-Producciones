
package fabrica.gestiondeproducciones.persistencia;

import fabrica.gestiondeproducciones.dominio.Empleado;
import fabrica.gestiondeproducciones.dominio.Produccion;
import fabrica.gestiondeproducciones.dominio.ProduccionManteca;
import fabrica.gestiondeproducciones.dominio.Silo;
import fabrica.gestiondeproducciones.utilidades.Excepciones;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;


public class PersistenciaProduccionManteca {
     Conexion conexion = new Conexion();
    Connection con;
    PreparedStatement consulta;
    PersistenciaProduccion p= new PersistenciaProduccion();
    
    public boolean altaProduccionManteca(ProduccionManteca produccion) {
    String sqlProduccion = "INSERT INTO produccion " + 
        "(codInterno, idLechePast, idProducto, rendimiento, kgLtsObt, fecha, encargadoId, horaInicio, horaFin, tiempoTrabajado, nroTacho) " + 
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String sqlProduccionManteca = "INSERT INTO produccionManteca " + 
        "(idProduccion, comienzoBatido, finBatido, totalBatido, ormas) " + 
        "VALUES (?, ?, ?, ?, ?)";
    String sqlAgregarEmpleados = "INSERT INTO produccion_empleados" +"(idProduccion,idEmpleado) VALUES (?,?)";
    String sqlAgregarInsumo = "INSERT INTO linea_insumos" +"(idProduccion,idInsumo,cantidad) VALUES (?,?,?)";

    try {
        con = conexion.obtenerConexion();
        
        // Preparar el primer insert
        consulta = con.prepareStatement(sqlProduccion, Statement.RETURN_GENERATED_KEYS);
        consulta.setString(1, produccion.getCodInterno());
        consulta.setInt(2, produccion.getLechep().getId());
        consulta.setInt(3, produccion.getProducto().getId());
        consulta.setInt(4, produccion.getRendimiento());
        consulta.setInt(5, produccion.getKgLtsObt());
        consulta.setString(6, produccion.getFecha());
        consulta.setInt(7, produccion.getEncargado().getId());
        consulta.setString(8, produccion.getHoraInicio());
        consulta.setString(9, produccion.getHoraFin());
        consulta.setString(10, produccion.getTiempoTrabajado());
        consulta.setInt(11, produccion.getNroTacho());
        
        // Ejecutar el primer insert
        consulta.executeUpdate();

        // Obtener el ID generado automáticamente
        ResultSet rs = consulta.getGeneratedKeys();
        int idProduccion = -1;
        if (rs.next()) {
            idProduccion = rs.getInt(1);
        }

        if (idProduccion == -1) {
            throw new SQLException("No se pudo obtener el ID de la tabla producciones.");
        }

        // Preparar el segundo insert
        consulta = con.prepareStatement(sqlProduccionManteca);
        consulta.setInt(1, idProduccion);
        consulta.setString(2, produccion.getHoraComienzoBatido());
        consulta.setString(3, produccion.getHoraFinBatido());
        consulta.setString(4, produccion.getTiempoTotalBatido());
        consulta.setInt(5, produccion.getCantidad());

        // Ejecutar el segundo insert
        consulta.executeUpdate();
        for(int i=0;i<produccion.getListaEmpleados().size();i++){
        consulta = con.prepareStatement(sqlAgregarEmpleados);
            consulta.setInt(1, idProduccion);
            consulta.setInt(2, produccion.getListaEmpleados().get(i).getId());
            consulta.executeUpdate();}
        
      
        consulta.executeUpdate();
        for(int i=0;i<produccion.getListaInsumos().size();i++){
        consulta = con.prepareStatement(sqlAgregarInsumo);
            consulta.setInt(1, idProduccion);
            consulta.setInt(2, produccion.getListaInsumos().get(i).getId());
            consulta.setInt(3, produccion.getListaInsumos().get(i).getCantidad());
            consulta.executeUpdate();}

        return true;

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
        return false;

    } finally {
        try {
            if (con != null) con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
        }
    }
}

    
 public boolean agregarEmpleado(int idProd,int idEmpleado){
        String sqlAgregarEmpleados = "INSERT INTO produccion_empleados" +"(idProduccion,idEmpleado) VALUES (?,?)";
        
        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sqlAgregarEmpleados);
            consulta.setInt(1, idProd);
            consulta.setInt(2, idEmpleado);
            consulta.execute();
            return true;
        }catch(SQLException e){            
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            return false;
        }finally{
            try{
                con.close();
            }catch(SQLException e){
               JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            }
        }
    }
    
    public boolean agregarInsumos(int idProd,int idInsumo,int cantidad){
        String sql = "INSERT INTO linea_insumos" +"(idProduccion,idInsumo,cantidad) VALUES (?,?,?)";
        
        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            consulta.setInt(1, idProd);
            consulta.setInt(2, idInsumo);
            consulta.setInt(3,cantidad);
            consulta.execute();
            return true;
        }catch(SQLException e){            
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            return false;
        }finally{
            try{
                con.close();
            }catch(SQLException e){
               JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            }
        }
    }
    
}
