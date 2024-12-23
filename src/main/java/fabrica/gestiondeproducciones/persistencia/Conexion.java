
package fabrica.gestiondeproducciones.persistencia;

import fabrica.gestiondeproducciones.utilidades.Excepciones;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {
    Connection con;
    
    public Connection obtenerConexion(){
        try{
            String bdd = "jdbc:mysql://localhost:3306/magnolia_db?serverTimezone=UTC";
            con = DriverManager.getConnection(bdd,"root","");
            return con;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
        }
        return null;
    }
    
}
