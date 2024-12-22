
package fabrica.gestiondeproducciones.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    Connection con;
    
    public Connection obtenerConexion(){
        try{
            String bdd = "jdbc:mysql://localhost:3306/magnolia_db?serverTimezone=UTC";
            con = DriverManager.getConnection(bdd,"root","");
            return con;
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return null;
    }
    
}
