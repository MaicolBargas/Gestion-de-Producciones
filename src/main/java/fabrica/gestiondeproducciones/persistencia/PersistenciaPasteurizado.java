
package fabrica.gestiondeproducciones.persistencia;

import fabrica.gestiondeproducciones.dominio.IngresoLeche;
import fabrica.gestiondeproducciones.dominio.LechePasteurizada;
import fabrica.gestiondeproducciones.utilidades.Excepciones;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class PersistenciaPasteurizado {
    Conexion conexion = new Conexion();
    Connection con;
    PreparedStatement consulta;
    ResultSet resultado;
    String nombreTabla = "pasteurizadas";
    PersistenciaIngresoLeche persIngreso = new PersistenciaIngresoLeche();
    
    public boolean altaPasteurizado(LechePasteurizada lecheP){
        String sql = "INSERT INTO "+ nombreTabla +"(temperatura, litros, idIngreso, descremado, cremaObtenida,cremaDisponible) VALUES (?,?,?,?,?,?)";
        
        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            consulta.setFloat(1, lecheP.getTemperatura());
            consulta.setInt(2, lecheP.getLitros());
            if(lecheP.getIngreso() instanceof IngresoLeche){
                consulta.setInt(3, lecheP.getIngreso().getIdIngreso());
            }else{
                throw new SQLException("El ingreso de leche seleccionado no existe, pruebe revisar el listado completo.");
            } 
            consulta.setBoolean(4, lecheP.getDescremado());                   
            consulta.setInt(5, lecheP.getCrema());
            consulta.setInt(6, lecheP.getCremaDisponible());
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
    
    
    public List listarPasteurizados(){
        List<LechePasteurizada> lista = new ArrayList();
        String sql = "SELECT * FROM "+ nombreTabla +" WHERE activo = '1'";
        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            resultado = consulta.executeQuery();
            while(resultado.next()){
                LechePasteurizada lecheP = new LechePasteurizada();
                lecheP.setId(resultado.getInt("idLecheP"));
                lecheP.setTemperatura(resultado.getFloat("temperatura"));
                lecheP.setLitros(resultado.getInt("litros"));
                IngresoLeche ingreso = persIngreso.buscarIngreso(resultado.getInt("idIngreso"));
                if(ingreso instanceof IngresoLeche){
                    lecheP.setIngreso(ingreso);
                }              
                lecheP.setDescremado(resultado.getBoolean("descremado"));
                lecheP.setCrema(resultado.getInt("cremaObtenida"));
                lecheP.setCremaDisponible(resultado.getInt("cremaDisponible"));
                lista.add(lecheP);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            return null;
        }
        return lista;
    }
    
     public List listarPasteurizadosProducidos(){
        List<LechePasteurizada> lista = new ArrayList();
        String sql = "SELECT * FROM " + nombreTabla + " WHERE activo = '1' AND idLecheP NOT IN ("
           + "SELECT idLechePast FROM produccion p WHERE p.idProduccion NOT IN ("
           + "SELECT pm.idProduccion FROM produccion_manteca pm));";



        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            resultado = consulta.executeQuery();
            while(resultado.next()){
                LechePasteurizada lecheP = new LechePasteurizada();
                lecheP.setId(resultado.getInt("idLecheP"));
                lecheP.setTemperatura(resultado.getFloat("temperatura"));
                lecheP.setLitros(resultado.getInt("litros"));
                IngresoLeche ingreso = persIngreso.buscarIngreso(resultado.getInt("idIngreso"));
                if(ingreso instanceof IngresoLeche){
                    lecheP.setIngreso(ingreso);
                }              
                lecheP.setDescremado(resultado.getBoolean("descremado"));
                lecheP.setCrema(resultado.getInt("cremaObtenida"));
                lecheP.setCremaDisponible(resultado.getInt("cremaDisponible"));
                lista.add(lecheP);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            return null;
        }
        return lista;
    }
    
    
        public List listarPasteurizadosPendientesAnalizar(){
        List<LechePasteurizada> lista = new ArrayList();
        String sql = "SELECT p.* FROM pasteurizadas p LEFT JOIN analisis a ON p.idLecheP = a.idPasteurizada WHERE p.activo = '1' AND a.idPasteurizada IS NULL";

        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            resultado = consulta.executeQuery();
            while(resultado.next()){
                LechePasteurizada lecheP = new LechePasteurizada();
                lecheP.setId(resultado.getInt("idLecheP"));
                lecheP.setTemperatura(resultado.getFloat("temperatura"));
                lecheP.setLitros(resultado.getInt("litros"));
                IngresoLeche ingreso = persIngreso.buscarIngreso(resultado.getInt("idIngreso"));
                if(ingreso instanceof IngresoLeche){
                    lecheP.setIngreso(ingreso);
                }              
                lecheP.setDescremado(resultado.getBoolean("descremado"));
                lecheP.setCrema(resultado.getInt("cremaObtenida"));
                lecheP.setCrema(resultado.getInt("cremaDisponible"));
                lista.add(lecheP);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            return null;
        }
        return lista;
    }
    
    public boolean bajaPasteurizado(int id){
        String sql = "UPDATE "+ nombreTabla +" SET activo = 0 WHERE idLecheP = ?";
       
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
    
     public boolean modificarPasteurizado(LechePasteurizada lecheP){
        String sql = "UPDATE "+ nombreTabla +" SET temperatura = ?, litros = ?, idIngreso = ?, descremado = ?, cremaObtenida = ?, cremaDisponible=? WHERE idLecheP = ?";
        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            consulta.setFloat(1, lecheP.getTemperatura());
            consulta.setInt(2, lecheP.getLitros());
            consulta.setInt(3, lecheP.getIngreso().getIdIngreso());
            consulta.setBoolean(4, lecheP.getDescremado());
            consulta.setInt(5, lecheP.getCrema());
            consulta.setInt(6, lecheP.getCremaDisponible());
            consulta.setInt(7, lecheP.getId());
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
     
     public LechePasteurizada buscarPasteurizado(int id){
        String sql = "SELECT * FROM "+ nombreTabla +" WHERE idLecheP =?";
        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            consulta.setInt(1, id);
            resultado = consulta.executeQuery();     
            if(resultado.next()){
               LechePasteurizada lecheP = new LechePasteurizada();
                lecheP.setId(resultado.getInt("idLecheP"));
                lecheP.setTemperatura(resultado.getFloat("temperatura"));
                lecheP.setLitros(resultado.getInt("litros"));
                IngresoLeche ingreso = persIngreso.buscarIngreso(resultado.getInt("idIngreso"));
                if(ingreso instanceof IngresoLeche){
                    lecheP.setIngreso(ingreso);
                }              
                lecheP.setDescremado(resultado.getBoolean("descremado"));
                lecheP.setCrema(resultado.getInt("cremaObtenida"));
                lecheP.setCremaDisponible(resultado.getInt("cremaDisponible"));
                return lecheP;
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
