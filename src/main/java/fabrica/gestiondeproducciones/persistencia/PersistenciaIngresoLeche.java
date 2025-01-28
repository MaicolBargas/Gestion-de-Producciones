
package fabrica.gestiondeproducciones.persistencia;

import fabrica.gestiondeproducciones.dominio.IngresoLeche;
import fabrica.gestiondeproducciones.dominio.Silo;
import fabrica.gestiondeproducciones.dominio.Tambo;
import fabrica.gestiondeproducciones.utilidades.Excepciones;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class PersistenciaIngresoLeche {
    Conexion conexion = new Conexion();
    Connection con;
    PreparedStatement consulta;
    ResultSet resultado;
    String nombreTabla = "ingresos";
    PersistenciaTambo persTambo = new PersistenciaTambo();
    PersistenciaSilo persSilo = new PersistenciaSilo();

    public boolean altaIngreso(IngresoLeche ingreso){
        String sql = "INSERT INTO "+ nombreTabla +"(idTambo, litros, litrosDisponibles, idSilo, fecha) VALUES (?,?,?,?,?)";
        
        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            if(ingreso.getTambo() instanceof Tambo){
                consulta.setInt(1, ingreso.getTambo().getId());
            }else{
                throw new SQLException("El tambo seleccionado no existe, prueba darlo de alta primero.");
            } 
            
            consulta.setInt(2, ingreso.getLitros());    
            consulta.setInt(3, ingreso.getLitrosDisponibles());                     

            if(ingreso.getSilo() instanceof Silo){
                consulta.setInt(4, ingreso.getSilo().getId());
            }else{
                throw new SQLException("El silo seleccionado no existe, prueba darlo de alta primero.");
            }           
            consulta.setString(5, ingreso.getFecha());         
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
    
    public List listarIngresos(){
        List<IngresoLeche> lista = new ArrayList();
        String sql = "SELECT * FROM "+ nombreTabla +" WHERE activo = '1'";
        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            resultado = consulta.executeQuery();
            while(resultado.next()){
                IngresoLeche ingreso = new IngresoLeche();
                ingreso.setIdIngreso(resultado.getInt("idIngreso"));
                
                Tambo tambo = persTambo.buscarTambo(resultado.getInt("idTambo"));
                if(tambo instanceof Tambo){
                    ingreso.setTambo(tambo);
                }
                            
                ingreso.setLitros(resultado.getInt("litros"));
                ingreso.setLitrosDisponibles(resultado.getInt("litrosDisponibles"));

                Silo silo = persSilo.buscarSilo(resultado.getInt("idSilo"));
                if(silo instanceof Silo){
                    ingreso.setSilo(silo);
                }              
                
                ingreso.setFecha(resultado.getString("fecha"));
                lista.add(ingreso);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            return null;
        }
        return lista;
    }
    
    public boolean bajaIngreso(int id){
        String sql = "UPDATE "+ nombreTabla +" SET activo = 0 WHERE idIngreso = ?";
       
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
    
    public boolean modificarIngreso(IngresoLeche ingreso){
        String sql = "UPDATE "+ nombreTabla +" SET idTambo = ?, litros = ?, litrosDisponibles = ?, idSilo = ?, fecha = ? WHERE idIngreso = ?";
        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            consulta.setInt(1, ingreso.getTambo().getId());
            consulta.setInt(2, ingreso.getLitros());
            consulta.setInt(3, ingreso.getLitrosDisponibles());
            consulta.setInt(4, ingreso.getSilo().getId());
            consulta.setString(5, ingreso.getFecha());
            consulta.setInt(6, ingreso.getIdIngreso());
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
    
    public IngresoLeche buscarIngreso(int id){
        String sql = "SELECT * FROM "+ nombreTabla +" WHERE idIngreso =?";
        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            consulta.setInt(1, id);
            resultado = consulta.executeQuery();     
            if(resultado.next()){
                IngresoLeche ingreso = new IngresoLeche();
                ingreso.setIdIngreso(resultado.getInt("idIngreso"));
                
                Tambo tambo = persTambo.buscarTambo(resultado.getInt("idTambo"));
                if(tambo instanceof Tambo){
                    ingreso.setTambo(tambo);
                }
                            
                ingreso.setLitros(resultado.getInt("litros"));
                ingreso.setLitrosDisponibles(resultado.getInt("litrosDisponibles"));

                Silo silo = persSilo.buscarSilo(resultado.getInt("idSilo"));
                if(silo instanceof Silo){
                    ingreso.setSilo(silo);
                }              
                
                ingreso.setFecha(resultado.getString("fecha"));
                return ingreso;
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
    
    public List listarIngresosPendientesAnalizar(){
        List<IngresoLeche> lista = new ArrayList();
        String sql = "SELECT i.* FROM ingresos i LEFT JOIN analisis a ON i.idIngreso = a.idIngreso WHERE i.activo = '1' AND a.idIngreso IS NULL";

        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            resultado = consulta.executeQuery();
            while(resultado.next()){
                IngresoLeche ingreso = new IngresoLeche();
                ingreso.setIdIngreso(resultado.getInt("idIngreso"));
                
                Tambo tambo = persTambo.buscarTambo(resultado.getInt("idTambo"));
                if(tambo instanceof Tambo){
                    ingreso.setTambo(tambo);
                }
                            
                ingreso.setLitros(resultado.getInt("litros"));
                ingreso.setLitrosDisponibles(resultado.getInt("litrosDisponibles"));

                Silo silo = persSilo.buscarSilo(resultado.getInt("idSilo"));
                if(silo instanceof Silo){
                    ingreso.setSilo(silo);
                }              
                
                ingreso.setFecha(resultado.getString("fecha"));
                lista.add(ingreso);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            return null;
        }
        return lista;
    }
    
    
}
