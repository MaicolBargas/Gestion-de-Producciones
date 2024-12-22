package fabrica.gestiondeproducciones.persistencia;

import fabrica.gestiondeproducciones.dominio.Silo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class PersistenciaSilo {
    Conexion conexion = new Conexion();
    Connection con;
    PreparedStatement consulta;
    
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
   
}
