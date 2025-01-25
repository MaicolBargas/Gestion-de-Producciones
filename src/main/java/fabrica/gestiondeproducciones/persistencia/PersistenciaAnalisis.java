
package fabrica.gestiondeproducciones.persistencia;

import fabrica.gestiondeproducciones.dominio.Analisis;
import fabrica.gestiondeproducciones.dominio.AnalisisIngreso;
import fabrica.gestiondeproducciones.dominio.AnalisisLechePasteurizada;
import fabrica.gestiondeproducciones.dominio.AnalisisManteca;
import fabrica.gestiondeproducciones.dominio.Empleado;
import fabrica.gestiondeproducciones.dominio.IngresoLeche;
import fabrica.gestiondeproducciones.dominio.LechePasteurizada;
import fabrica.gestiondeproducciones.dominio.ProduccionManteca;
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
    PersistenciaPasteurizado persLechePast=new PersistenciaPasteurizado();
    PersistenciaProduccionManteca persManteca = new PersistenciaProduccionManteca();
    
    // <editor-fold defaultstate="collapsed" desc="Persistencia Analisis de Ingreso">  
    public boolean altaAnalisisIngreso(AnalisisIngreso analisis){
        String sql = "INSERT INTO "+ nombreTabla +"(tipo, empleado,fecha,levadura,mos,poliformosTotales,poliformosFecales,grasa,proteina,agua,idIngreso) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        
        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);          
            consulta.setString(1, analisis.getTipo());
            consulta.setInt(2, analisis.getEncargado().getId());
            consulta.setString(3, analisis.getFecha());
            consulta.setInt(4, analisis.getLevadura());
            consulta.setInt(5, analisis.getMos());
            consulta.setInt(6, analisis.getPoliformosTotales());
            consulta.setInt(7, analisis.getPoliformosFecales());           
            consulta.setInt(8, analisis.getGrasa());
            consulta.setInt(9, analisis.getProteina());
            consulta.setInt(10, analisis.getAgua());
            consulta.setInt(11, analisis.getIngreso().getIdIngreso());
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
        String sql = "UPDATE "+ nombreTabla +" SET tipo = ?, empleado = ?, fecha = ?, levadura = ?, mos = ?, poliformosTotales = ?, poliformosFecales = ?, grasa = ? ,proteina = ?, agua = ?, idIngreso = ? WHERE idAnalisis = ?";
        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            consulta.setString(1, analisis.getTipo());
            consulta.setInt(2, analisis.getEncargado().getId());
            consulta.setString(3, analisis.getFecha());
            consulta.setInt(4, analisis.getLevadura());
            consulta.setInt(5, analisis.getMos());
            consulta.setInt(6, analisis.getPoliformosTotales());
            consulta.setInt(7, analisis.getPoliformosFecales());           
            consulta.setInt(8, analisis.getGrasa());
            consulta.setInt(9, analisis.getProteina());
            consulta.setInt(10, analisis.getAgua());
            consulta.setInt(11, analisis.getIngreso().getIdIngreso());
            consulta.setInt(12, analisis.getId());

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
    
    // <editor-fold defaultstate="collapsed" desc="Persistencia Analisis de Leche Pasteurizada">  
    public boolean altaAnalisisLechePast(AnalisisLechePasteurizada analisisLechePast){
        String sql = "INSERT INTO "+ nombreTabla +"(tipo, empleado,fecha,levadura,mos,poliformosTotales,poliformosFecales,grasa,proteina,agua,idPasteurizada) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        
        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);          
            consulta.setString(1, analisisLechePast.getTipo());
            consulta.setInt(2, analisisLechePast.getEncargado().getId());
            consulta.setString(3, analisisLechePast.getFecha());
            consulta.setInt(4, analisisLechePast.getLevadura());
            consulta.setInt(5, analisisLechePast.getMos());
            consulta.setInt(6, analisisLechePast.getPoliformosTotales());
            consulta.setInt(7, analisisLechePast.getPoliformosFecales());           
            consulta.setInt(8, analisisLechePast.getGrasa());
            consulta.setInt(9, analisisLechePast.getProteina());
            consulta.setInt(10, analisisLechePast.getAgua());
            consulta.setInt(11, analisisLechePast.getLechePast().getId());
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
    
    public boolean modificarAnalisisLechePast(AnalisisLechePasteurizada analisisLechePast){
        String sql = "UPDATE "+ nombreTabla +" SET tipo = ?, empleado = ?, fecha = ?, levadura = ?, mos = ?, poliformosTotales = ?, poliformosFecales = ?, grasa = ? ,proteina = ?, agua = ?, idPasteurizada = ? WHERE idAnalisis = ?";
        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            consulta.setString(1, analisisLechePast.getTipo());
            consulta.setInt(2, analisisLechePast.getEncargado().getId());
            consulta.setString(3, analisisLechePast.getFecha());
            consulta.setInt(4, analisisLechePast.getLevadura());
            consulta.setInt(5, analisisLechePast.getMos());
            consulta.setInt(6, analisisLechePast.getPoliformosTotales());
            consulta.setInt(7, analisisLechePast.getPoliformosFecales());           
            consulta.setInt(8, analisisLechePast.getGrasa());
            consulta.setInt(9, analisisLechePast.getProteina());
            consulta.setInt(10, analisisLechePast.getAgua());
            consulta.setInt(11, analisisLechePast.getLechePast().getId());
            consulta.setInt(12, analisisLechePast.getId());
           

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
    
    public List listarAnalisisLechePast() {
        List<AnalisisLechePasteurizada> lista = new ArrayList();
        String sql = "SELECT * FROM "+ nombreTabla +" WHERE tipo = 'pasteurizada' AND activo = '1'";
        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            resultado = consulta.executeQuery();
            while(resultado.next()){
                AnalisisLechePasteurizada analisisLechePast = new AnalisisLechePasteurizada();
                analisisLechePast.setId(resultado.getInt("idAnalisis"));
                analisisLechePast.setTipo(resultado.getString("tipo"));
                
                Empleado encargado = persEmpleado.buscarEmpleado(resultado.getInt("empleado"));
                if(encargado instanceof Empleado){
                    analisisLechePast.setEncargado(encargado);
                } 
                
               
                analisisLechePast.setFecha(resultado.getString("fecha"));
                analisisLechePast.setLevadura(resultado.getInt("levadura"));
                analisisLechePast.setMos(resultado.getInt("mos"));
                analisisLechePast.setPoliformosTotales(resultado.getInt("poliformosTotales"));
                analisisLechePast.setPoliformosFecales(resultado.getInt("poliformosFecales"));
                analisisLechePast.setGrasa(resultado.getInt("grasa"));
                analisisLechePast.setProteina(resultado.getInt("proteina"));
                analisisLechePast.setAgua(resultado.getInt("agua"));

                LechePasteurizada LechePast = persLechePast.buscarPasteurizado(resultado.getInt("idPasteurizada"));
                if(LechePast instanceof LechePasteurizada){
                    analisisLechePast.setLechePast(LechePast);
                }   
                
                lista.add(analisisLechePast);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            return null;
        }
        return lista;
    }
    
    public AnalisisLechePasteurizada buscarAnalisisLechePast(int id){
        String sql = "SELECT * FROM "+ nombreTabla +" WHERE idAnalisis =?";
        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            consulta.setInt(1, id);
            resultado = consulta.executeQuery();     
            if(resultado.next()){
               AnalisisLechePasteurizada analisisLechePast = new AnalisisLechePasteurizada();
                analisisLechePast.setId(resultado.getInt("idAnalisis"));
                analisisLechePast.setTipo(resultado.getString("tipo"));
                
                Empleado encargado = persEmpleado.buscarEmpleado(resultado.getInt("empleado"));
                if(encargado instanceof Empleado){
                    analisisLechePast.setEncargado(encargado);
                } 
                
                analisisLechePast.setFecha(resultado.getString("fecha"));
                analisisLechePast.setLevadura(resultado.getInt("levadura"));
                analisisLechePast.setMos(resultado.getInt("mos"));
                analisisLechePast.setPoliformosTotales(resultado.getInt("poliformosTotales"));
                analisisLechePast.setPoliformosFecales(resultado.getInt("poliformosFecales"));
                analisisLechePast.setGrasa(resultado.getInt("grasa"));
                analisisLechePast.setProteina(resultado.getInt("proteina"));
                analisisLechePast.setAgua(resultado.getInt("agua"));

                LechePasteurizada LechePast = persLechePast.buscarPasteurizado(resultado.getInt("idPasteurizada"));
                if(LechePast instanceof LechePasteurizada){
                    analisisLechePast.setLechePast(LechePast);
                }   
                return analisisLechePast;
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
      
    // <editor-fold defaultstate="collapsed" desc="Analisis de Manteca">  
    public boolean altaAnalisisManteca(AnalisisManteca analisis){
        String sql = "INSERT INTO "+ nombreTabla +"(tipo, empleado,fecha,levadura,mos,poliformosTotales,poliformosFecales,grasa,acidez,humedad,idProduccion) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        
        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);          
            consulta.setString(1, analisis.getTipo());
            consulta.setInt(2, analisis.getEncargado().getId());
            consulta.setString(3, analisis.getFecha());
            consulta.setInt(4, analisis.getLevadura());
            consulta.setInt(5, analisis.getMos());
            consulta.setInt(6, analisis.getPoliformosTotales());
            consulta.setInt(7, analisis.getPoliformosFecales());           
            consulta.setInt(8, analisis.getGrasa());
            consulta.setInt(9, analisis.getAcidez());
            consulta.setInt(10, analisis.getHumedad());
            consulta.setInt(11, analisis.getProduccion().getIdProduccion());
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
    
    public boolean modificarAnalisisManteca(AnalisisManteca analisis){
        String sql = "UPDATE "+ nombreTabla +" SET tipo = ?, empleado = ?, fecha = ?, levadura = ?, mos = ?, poliformosTotales = ?, poliformosFecales = ?, grasa = ? ,acidez = ?, humedad = ?, idProduccion = ? WHERE idAnalisis = ?";
        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            consulta.setString(1, analisis.getTipo());
            consulta.setInt(2, analisis.getEncargado().getId());
            consulta.setString(3, analisis.getFecha());
            consulta.setInt(4, analisis.getLevadura());
            consulta.setInt(5, analisis.getMos());
            consulta.setInt(6, analisis.getPoliformosTotales());
            consulta.setInt(7, analisis.getPoliformosFecales());           
            consulta.setInt(8, analisis.getGrasa());
            consulta.setInt(9, analisis.getAcidez());
            consulta.setInt(10, analisis.getHumedad());
            consulta.setInt(11, analisis.getProduccion().getIdProduccion());
            consulta.setInt(12, analisis.getId());

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
    
    public List listarAnalisisManteca() {
        List<AnalisisManteca> lista = new ArrayList();
        String sql = "SELECT * FROM "+ nombreTabla +" WHERE tipo = 'manteca' AND activo = '1'";
        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            resultado = consulta.executeQuery();
            while(resultado.next()){
                AnalisisManteca analisis = new AnalisisManteca();
                analisis.setId(resultado.getInt("idAnalisis"));
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
                analisis.setAcidez(resultado.getInt("acidez"));
                analisis.setHumedad(resultado.getInt("humedad"));

                ProduccionManteca produccion = persManteca.buscarProduccionManteca(resultado.getInt("idProduccion"));
                if(produccion instanceof ProduccionManteca){
                    analisis.setProduccion(produccion);
                }   
                
                lista.add(analisis);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            return null;
        }
        return lista;
    }
    
    public AnalisisManteca buscarAnalisisManteca(int id){
        String sql = "SELECT * FROM "+ nombreTabla +" WHERE idAnalisis =?";
        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            consulta.setInt(1, id);
            resultado = consulta.executeQuery();     
            if(resultado.next()){
               AnalisisManteca analisis = new AnalisisManteca();
                analisis.setId(resultado.getInt("idAnalisis"));
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
                analisis.setAcidez(resultado.getInt("acidez"));
                analisis.setHumedad(resultado.getInt("humedad"));

                ProduccionManteca produccion = persManteca.buscarProduccionManteca(resultado.getInt("idProduccion"));
                if(produccion instanceof ProduccionManteca){
                    analisis.setProduccion(produccion);
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
