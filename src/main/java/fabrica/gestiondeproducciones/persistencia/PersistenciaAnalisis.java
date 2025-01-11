
package fabrica.gestiondeproducciones.persistencia;

import fabrica.gestiondeproducciones.dominio.Analisis;
import fabrica.gestiondeproducciones.dominio.AnalisisIngreso;
import fabrica.gestiondeproducciones.dominio.Empleado;
import fabrica.gestiondeproducciones.dominio.IngresoLeche;
import fabrica.gestiondeproducciones.utilidades.Excepciones;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class PersistenciaAnalisis {
    Conexion conexion = new Conexion();
    Connection con;
    PreparedStatement consulta;
    ResultSet resultado;
    String nombreTabla = "analisis";
    PersistenciaIngresoLeche persIngreso = new PersistenciaIngresoLeche();
    PersistenciaEmpleado persEmpleado = new PersistenciaEmpleado();

    // <editor-fold defaultstate="collapsed" desc="Persistencia Analisis de Ingreso">  
    public boolean altaAnalisisIngreso(AnalisisIngreso analisis){
        String sql = "INSERT INTO "+ nombreTabla +"(codigo,tipo, empleado,fecha,levadura,mos,poliformosTotales,poliformosFecales,grasa,proteina,agua,idIngreso) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        
        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);          
            consulta.setInt(1, analisis.getCodigo());
            consulta.setString(2, analisis.getTipo());
            consulta.setInt(3, analisis.getEncargado().getId());
            consulta.setString(4, analisis.getFecha());
            consulta.setInt(5, analisis.getLevadura());
            consulta.setInt(6, analisis.getMos());
            consulta.setInt(7, analisis.getPoliformosTotales());
            consulta.setInt(8, analisis.getPoliformosFecales());           
            consulta.setInt(9, analisis.getGrasa());
            consulta.setInt(10, analisis.getProteina());
            consulta.setInt(11, analisis.getAgua());
            consulta.setInt(12, analisis.getIngreso().getIdIngreso());
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
    
    public boolean modificarAnalisisIngreso(AnalisisIngreso analisis){
        String sql = "UPDATE "+ nombreTabla +" SET codigo = ?, tipo = ?, empleado = ?, fecha = ?, levadura = ?, mos = ?, poliformosTotales = ?, poliformosFecales = ?, grasa = ? ,proteina = ?, agua = ?, idIngreso = ? WHERE idAnalisis = ?";
        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            consulta.setInt(1, analisis.getCodigo());
            consulta.setString(2, analisis.getTipo());
            consulta.setInt(3, analisis.getEncargado().getId());
            consulta.setString(4, analisis.getFecha());
            consulta.setInt(5, analisis.getLevadura());
            consulta.setInt(6, analisis.getMos());
            consulta.setInt(7, analisis.getPoliformosTotales());
            consulta.setInt(8, analisis.getPoliformosFecales());           
            consulta.setInt(9, analisis.getGrasa());
            consulta.setInt(10, analisis.getProteina());
            consulta.setInt(11, analisis.getAgua());
            consulta.setInt(12, analisis.getIngreso().getIdIngreso());
            consulta.setInt(13, analisis.getId());

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
    
    public List listarAnalisisIngreso() {
        List<AnalisisIngreso> lista = new ArrayList();
        String sql = "SELECT * FROM "+ nombreTabla +" WHERE tipo = 'ingreso' AND activo = '1'";
        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            resultado = consulta.executeQuery();
            while(resultado.next()){
                AnalisisIngreso analisis = new AnalisisIngreso();
                analisis.setId(resultado.getInt("idAnalisis"));
                analisis.setCodigo(resultado.getInt("codigo"));
                analisis.setTipo(resultado.getString("tipo"));
                
                Empleado encargado = persEmpleado.buscarEmpleado(resultado.getInt("empleado"));
                if(encargado instanceof Empleado){
                    analisis.setEncargado(encargado);
                } 
                
                analisis.setFecha(resultado.getString("fecha"));
                analisis.setLevadura(resultado.getInt("levadura"));
                analisis.setMos(resultado.getInt("mos"));
                analisis.setPoliformosTotales(resultado.getInt("poliformosTotales"));
                analisis.setPoliformosFecales(resultado.getInt("poliformosFecales"));
                analisis.setGrasa(resultado.getInt("grasa"));
                analisis.setProteina(resultado.getInt("proteina"));
                analisis.setAgua(resultado.getInt("agua"));

                IngresoLeche ingreso = persIngreso.buscarIngreso(resultado.getInt("idIngreso"));
                if(ingreso instanceof IngresoLeche){
                    analisis.setIngreso(ingreso);
                }   
                
                lista.add(analisis);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            return null;
        }
        return lista;
    }
    
    public AnalisisIngreso buscarAnalisisIngreso(int id){
        String sql = "SELECT * FROM "+ nombreTabla +" WHERE idAnalisis =?";
        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            consulta.setInt(1, id);
            resultado = consulta.executeQuery();     
            if(resultado.next()){
               AnalisisIngreso analisis = new AnalisisIngreso();
                analisis.setId(resultado.getInt("idAnalisis"));
                analisis.setCodigo(resultado.getInt("codigo"));
                analisis.setTipo(resultado.getString("tipo"));
                
                Empleado encargado = persEmpleado.buscarEmpleado(resultado.getInt("empleado"));
                if(encargado instanceof Empleado){
                    analisis.setEncargado(encargado);
                } 
                
                analisis.setFecha(resultado.getString("fecha"));
                analisis.setLevadura(resultado.getInt("levadura"));
                analisis.setMos(resultado.getInt("mos"));
                analisis.setPoliformosTotales(resultado.getInt("poliformosTotales"));
                analisis.setPoliformosFecales(resultado.getInt("poliformosFecales"));
                analisis.setGrasa(resultado.getInt("grasa"));
                analisis.setProteina(resultado.getInt("proteina"));
                analisis.setAgua(resultado.getInt("agua"));

                IngresoLeche ingreso = persIngreso.buscarIngreso(resultado.getInt("idIngreso"));
                if(ingreso instanceof IngresoLeche){
                    analisis.setIngreso(ingreso);
                }   
                return analisis;
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
    
    // </editor-fold>  
    
    private void altaAnalisisProduccion(Analisis analisis, PreparedStatement consulta){
            
    }
    
    
    
    public boolean bajaAnalisis(int id){
        String sql = "UPDATE "+ nombreTabla +" SET activo = 0 WHERE idAnalisis = ?";
       
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
    
    
    
}
