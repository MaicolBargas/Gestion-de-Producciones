package fabrica.gestiondeproducciones.persistencia;

import fabrica.gestiondeproducciones.dominio.Silo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class PersistenciaSilo {
    Conexion conexion = new Conexion();
    Connection con;
    PreparedStatement consulta;
    ResultSet resultado;
    public boolean altaSilo(Silo silo){
        String sql = "INSERT INTO silos (codigoSilo, capacidad) VALUES (?,?)";
        
        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            consulta.setInt(1, silo.getCodigoInterno());
            consulta.setInt(2, silo.getCapacidad());
            consulta.execute();
            return true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }finally{
            try{
                con.close();
            }catch(SQLException e){
            
            }
        }
    }
   
    public List listarSilos(){
        List<Silo> lista = new ArrayList();
        String sql = "SELECT * FROM silos WHERE activo = '1'";
        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            resultado = consulta.executeQuery();
            while(resultado.next()){
                Silo silo = new Silo();
                silo.setId(resultado.getInt("idSilo"));
                silo.setCodigoInterno(resultado.getInt("codigoSilo"));
                silo.setCapacidad(resultado.getInt("capacidad"));
                lista.add(silo);
            }
        }catch(SQLException e){
            System.out.println(e.toString());
            return null;
        }
        return lista;
    }
    
    
}
