
package fabrica.gestiondeproducciones.persistencia;

import fabrica.gestiondeproducciones.dominio.EnvasesDulce;
import fabrica.gestiondeproducciones.dominio.Tambo;
import fabrica.gestiondeproducciones.utilidades.Excepciones;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class PersistenciaEnvases {
    
    Conexion conexion = new Conexion();
    Connection con;
    PreparedStatement consulta;
    ResultSet resultado;
    String nombreTabla = "envases";
    
    public boolean altaEnvase(EnvasesDulce envase){
        String sql = "INSERT INTO "+ nombreTabla +"(descripcion,capacidad) VALUES (?,?)";
        
        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            consulta.setString(1,envase.getDescripcion());
            consulta.setFloat(2,envase.getCapacidad());
            
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
   
    public List listarEnvases(){
        List<EnvasesDulce> lista = new ArrayList();
        String sql = "SELECT * FROM "+ nombreTabla +" WHERE activo = '1'";
        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            resultado = consulta.executeQuery();
            while(resultado.next()){
                EnvasesDulce  envase = new EnvasesDulce();
                envase.setId(resultado.getInt("idEnvase"));
                envase.setDescripcion(resultado.getString("descripcion"));
                envase.setCapacidad(resultado.getFloat("capacidad"));
                
                lista.add(envase);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            return null;
        }
        return lista;
    }
    
    public boolean bajaEnvase(int id){
        String sql = "UPDATE "+ nombreTabla +" SET activo = 0 WHERE idEnvase= ?";
       
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
    public boolean modificarEnvase(EnvasesDulce envase){
        String sql = "UPDATE "+ nombreTabla +" SET descripcion = ?, capacidad= ? WHERE idEnvase = ?";
        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            consulta.setString(1, envase.getDescripcion());
            consulta.setFloat(2,envase.getCapacidad());
            
            consulta.setInt(3, envase.getId());
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
        
    public EnvasesDulce buscarEnvase(int id){
        String sql = "SELECT * FROM "+ nombreTabla +" WHERE idEnvase =?";
        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            consulta.setInt(1, id);
            resultado = consulta.executeQuery();     
            if(resultado.next()){           
                EnvasesDulce envase = new EnvasesDulce();
                envase.setId(resultado.getInt("idEnvase"));
                envase.setDescripcion(resultado.getString("descripcion"));
                envase.setCapacidad(resultado.getFloat("capacidad"));
                
                return envase;
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
