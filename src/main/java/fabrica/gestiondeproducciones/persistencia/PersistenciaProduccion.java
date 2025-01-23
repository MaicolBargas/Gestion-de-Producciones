
package fabrica.gestiondeproducciones.persistencia;

import fabrica.gestiondeproducciones.dominio.Controlador;
import fabrica.gestiondeproducciones.dominio.Empleado;
import fabrica.gestiondeproducciones.dominio.Insumo;
import fabrica.gestiondeproducciones.dominio.LineaInsumo;
import fabrica.gestiondeproducciones.dominio.Produccion;
import fabrica.gestiondeproducciones.dominio.Silo;
import fabrica.gestiondeproducciones.presentacion.GestionProduccionManteca;
import fabrica.gestiondeproducciones.utilidades.Excepciones;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class PersistenciaProduccion {
    
    Conexion conexion = new Conexion();
    Connection con;
    PreparedStatement consulta;
    ResultSet resultado;
    String nombreTabla = "produccion";
    PersistenciaInsumo persInsumo = new PersistenciaInsumo();
    PersistenciaEmpleado persEmpleado = new PersistenciaEmpleado();
    PersistenciaProducto persProducto = new PersistenciaProducto();
    
    int idGenerado;
    
    public int getIdGenerado(){
        return idGenerado;
    }
    
    public boolean altaProduccion(Produccion produccion) {
    String sql = "INSERT INTO " + nombreTabla + 
                 " (codInterno, idLechePast, idProducto, rendimiento, kgLtsObt, fecha, encargadoId, horaInicio, horaFin, tiempoTrabajado, nroTacho) " +             
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try {
        // Prepara la conexión y solicita las claves generadas
        con = conexion.obtenerConexion();
        consulta = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        // Configura los valores de la consulta
        consulta.setString(1, produccion.getCodInterno());
        consulta.setInt(2, produccion.getLechep().getId());
        consulta.setInt(3, produccion.getProducto().getId());
        consulta.setFloat(4, produccion.getRendimiento());
        consulta.setInt(5, produccion.getKgLtsObt());
        consulta.setString(6, produccion.getFecha());
        consulta.setInt(7, produccion.getEncargado().getId());
        consulta.setString(8, produccion.getHoraInicio());
        consulta.setString(9, produccion.getHoraFin());
        consulta.setString(10, produccion.getTiempoTrabajado());
        consulta.setInt(11, produccion.getNroTacho());

        
        int filasAfectadas = consulta.executeUpdate();

        if (filasAfectadas > 0) {
           
            ResultSet rs = consulta.getGeneratedKeys();
            if (rs.next()) {
                 idGenerado = rs.getInt(1); // Lee el ID generado
                 System.out.println(idGenerado);
                 
                 
                
            } else {
                throw new SQLException("No se pudo obtener el ID generado.");
            }
        }

        return true;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
        return false;
    } finally {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
        }
    }
}

    
    public void agregarEmpleado(int idProd,int idEmpleado){
        String sql = "INSERT INTO produccion_empleados" +"(idProduccion,idEmpleado) VALUES (?,?)";
        
        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            consulta.setInt(1, idProd);
            consulta.setInt(2, idEmpleado);
            consulta.execute();
        }catch(SQLException e){            
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
        }finally{
            try{
                con.close();
            }catch(SQLException e){
               JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            }
        }
    }
    
    public void agregarInsumos(int idProd,int idInsumo,int cantidad){
        String sql = "INSERT INTO linea_insumos" +"(idProduccion,idInsumo,cantidad) VALUES (?,?,?)";
        
        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            consulta.setInt(1, idProd);
            consulta.setInt(2, idInsumo);
            consulta.setInt(3,cantidad);
            consulta.execute();
        }catch(SQLException e){            
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
        }finally{
            try{
                con.close();
            }catch(SQLException e){
               JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            }
        }
    }
    
    public boolean bajaProduccion(int id, String tabla) throws Exception{
        String sql = "UPDATE produccion SET activo = 0 WHERE idProduccion = ?";       
        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            consulta.setInt(1, id);
            consulta.execute();
            bajaProduccionEspecifica(id,"produccion_empleados");     
            bajaProduccionEspecifica(id,"linea_insumos");     
            bajaProduccionEspecifica(id,tabla);     
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
    
    private boolean bajaProduccionEspecifica(int id, String tabla) throws Exception{        
        String sql = "DELETE FROM "+tabla+" WHERE idProduccion = ?";
       
        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            consulta.setInt(1, id);
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
    
    public List listarEmpleadosXProduccion(int idProduccion){
        List<Empleado> lista = new ArrayList();
        String sql = "SELECT * FROM produccion_empleados WHERE idProduccion = ?";
        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            consulta.setInt(1, idProduccion);
            resultado = consulta.executeQuery();
            while(resultado.next()){
                Empleado empleado = persEmpleado.buscarEmpleado(resultado.getInt("idEmpleado"));
               
                if(empleado instanceof Empleado){
                    lista.add(empleado);
                }              
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            return null;
        }
        return lista;
    }
    
    public List listarInsumoXProduccion(int idProduccion){
        List<LineaInsumo> lista = new ArrayList();
        String sql = "SELECT * FROM linea_insumos WHERE idProduccion = ?";
        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            consulta.setInt(1, idProduccion);
            resultado = consulta.executeQuery();
            while(resultado.next()){
                Insumo insumo = persInsumo.buscarInsumo(resultado.getInt("idInsumo"));
                if( insumo instanceof Insumo){
                    int id = resultado.getInt("idLinea");
                    int cantidad = resultado.getInt("cantidad");
                    LineaInsumo linea = new LineaInsumo(id,insumo,cantidad);
                    lista.add(linea);
                }
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            return null;
        }
        return lista;
    }
    
    public void actualizarEmpleadosxProduccion(int idProduccion, List<Empleado> empleados){
        limpiarEmpleadosProduccion(idProduccion);
        try{
            for(Empleado empleado : empleados){                
                agregarEmpleado(idProduccion, empleado.getId());                
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
        }
    }
    
    private void limpiarEmpleadosProduccion(int idProduccion){
        String sql = "DELETE FROM produccion_empleados WHERE idProduccion = ?";
        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            consulta.setInt(1, idProduccion);
            consulta.execute();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
        }finally{
            try{
                con.close();
            }catch(SQLException e){
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            }
        }
    }
    
    public void actualizarInsumosxProduccion(int idProduccion, List<LineaInsumo> insumos){
        limpiarInsumosProduccion(idProduccion);
        try{
            for(LineaInsumo insumo : insumos){                
                agregarInsumos(idProduccion, insumo.getInsumo().getId(), insumo.getCantidad());                
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
        }
    }
    
    private void limpiarInsumosProduccion(int idProduccion){
        String sql = "DELETE FROM linea_insumos WHERE idProduccion = ?";
        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            consulta.setInt(1, idProduccion);
            consulta.execute();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
        }finally{
            try{
                con.close();
            }catch(SQLException e){
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            }
        }
    }
    
}
