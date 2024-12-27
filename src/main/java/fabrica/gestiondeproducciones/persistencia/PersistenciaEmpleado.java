
package fabrica.gestiondeproducciones.persistencia;

import fabrica.gestiondeproducciones.dominio.Empleado;
import fabrica.gestiondeproducciones.dominio.Seccion;
import fabrica.gestiondeproducciones.utilidades.Excepciones;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class PersistenciaEmpleado {
    
    Conexion conexion = new Conexion();
    Connection con;
    PreparedStatement consulta;
    ResultSet resultado;
    String nombreTabla = "empleado";
    PersistenciaSeccion persSeccion = new PersistenciaSeccion();
     	 	 
    public boolean altaEmpleado(Empleado empleado){
        String sql = "INSERT INTO "+ nombreTabla +"(ci, nombre, apellido, idSeccion, telefono, mail ) VALUES (?,?,?,?,?,?)";
        
        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            consulta.setInt(1, empleado.getCi());
            consulta.setString(2, empleado.getNombre());
            consulta.setString(3, empleado.getApellido());           
            if(empleado.getSeccion() instanceof Seccion){
                consulta.setInt(4, empleado.getSeccion().getId());
            }else{
                throw new SQLException("La seccion seleccionada no existe, prueba darla de alta primero.");
            }           
            consulta.setString(5, empleado.getTelefono());
            consulta.setString(6, empleado.getMail());

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
   
    public List listarEmpleados(){
        List<Empleado> lista = new ArrayList();
        String sql = "SELECT * FROM "+ nombreTabla +" WHERE activo = '1'";
        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            resultado = consulta.executeQuery();
            while(resultado.next()){
                Empleado empleado = new Empleado();
                empleado.setId(resultado.getInt("idEmpleado"));
                empleado.setCi(resultado.getInt("ci"));
                empleado.setNombre(resultado.getString("nombre"));
                empleado.setApellido(resultado.getString("apellido"));
                Seccion seccion = persSeccion.buscarSeccion(resultado.getInt("idSeccion"));
                if(seccion instanceof Seccion){
                    empleado.setSeccion(seccion);
                }              
                empleado.setTelefono(resultado.getString("telefono"));
                empleado.setMail(resultado.getString("mail"));
                lista.add(empleado);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            return null;
        }
        return lista;
    }
    
    public boolean bajaEmpleado(int id){
        String sql = "UPDATE "+ nombreTabla +" SET activo = 0 WHERE idEmpleado = ?";
       
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
    
    public boolean modificarEmpleado(Empleado empleado){
        String sql = "UPDATE "+ nombreTabla +" SET ci = ?, nombre = ?, apellido = ?, idSeccion = ?, telefono = ?, mail = ? WHERE idEmpleado = ?";
        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            consulta.setInt(1, empleado.getCi());
            consulta.setString(2, empleado.getNombre());
            consulta.setString(3, empleado.getApellido());
            consulta.setInt(4, empleado.getSeccion().getId());
            consulta.setString(5, empleado.getTelefono());
            consulta.setString(6, empleado.getMail());
            consulta.setInt(7, empleado.getId());
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
        
    public Empleado buscarEmpleado(int id){
        String sql = "SELECT * FROM "+ nombreTabla +" WHERE idEmpleado =?";
        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            consulta.setInt(1, id);
            resultado = consulta.executeQuery();     
            if(resultado.next()){
                Empleado empleado = new Empleado();
                empleado.setId(resultado.getInt("idEmpleado"));
                empleado.setCi(resultado.getInt("ci"));
                empleado.setNombre(resultado.getString("nombre"));
                empleado.setApellido(resultado.getString("apellido"));
                Seccion seccion = persSeccion.buscarSeccion(resultado.getInt("idSeccion"));
                if(seccion instanceof Seccion){
                    empleado.setSeccion(seccion);
                }              
                empleado.setTelefono(resultado.getString("telefono"));
                empleado.setMail(resultado.getString("mail"));
                return empleado;
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            return null;
        }finally{
            try{
                con.close();
            }catch(SQLException e){
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            }
        }
        return null;
    }
    
    public Empleado buscarEmpleadoXCi(int ci){
        String sql = "SELECT * FROM "+ nombreTabla +" WHERE ci = ?";
        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            consulta.setInt(1, ci);
            resultado = consulta.executeQuery();     
            if(resultado.next()){
                Empleado empleado = new Empleado();
                empleado.setId(resultado.getInt("idEmpleado"));
                empleado.setCi(resultado.getInt("ci"));
                empleado.setNombre(resultado.getString("nombre"));
                empleado.setApellido(resultado.getString("apellido"));
                Seccion seccion = persSeccion.buscarSeccion(resultado.getInt("idSeccion"));
                if(seccion instanceof Seccion){
                    empleado.setSeccion(seccion);
                }              
                empleado.setTelefono(resultado.getString("telefono"));
                empleado.setMail(resultado.getString("mail"));
                return empleado;
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            return null;
        }finally{
            try{
                con.close();
            }catch(SQLException e){
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            }
        }
        return null;
    }
    
}
