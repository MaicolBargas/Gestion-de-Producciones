
package fabrica.gestiondeproducciones;

import fabrica.gestiondeproducciones.presentacion.Principal;
import java.io.IOException;
import java.net.Socket;

public class GestionDeProducciones {

    public static void main(String[] args) {
        iniciarServicios();
        Principal principal = new Principal();
        principal.show();
    }
    
        public static void iniciarServicios() {
        try {
            // Ruta donde está instalado XAMPP
            String xamppPath = "C:\\xampp"; // Cambia según tu instalación
            
            // Iniciar Apache
            if(!isApacheRunning()){
                ProcessBuilder apache = new ProcessBuilder("cmd.exe", "/c", xamppPath + "\\apache_start.bat");
                apache.start();
            }else{
                System.out.println("Apache ya esta corriendo");
            }

            
            // Iniciar MySQL
            if(!isMySQLRunning()){
                ProcessBuilder mysql = new ProcessBuilder("cmd.exe", "/c", xamppPath + "\\mysql_start.bat");
                mysql.start();
            }else{
                System.out.println("MySQL ya esta corriendo");
            }

            
        } catch (IOException e) {
            
        }
    }
        
    public static boolean puertoEnUso(String host, int port) {
        try (Socket socket = new Socket(host, port)) {
            return true; // Si se puede conectar, el servicio está corriendo
        } catch (IOException e) {
            return false; // Si no se puede conectar, el servicio no está corriendo
        }
    }

    public static boolean isApacheRunning() {
        return puertoEnUso("localhost", 80) || puertoEnUso("localhost", 443);
    }

    public static boolean isMySQLRunning() {
        return puertoEnUso("localhost", 3306);
    }
        
}
