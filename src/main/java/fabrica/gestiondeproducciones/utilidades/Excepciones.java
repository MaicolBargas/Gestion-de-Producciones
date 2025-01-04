
package fabrica.gestiondeproducciones.utilidades;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Excepciones {
    
    private static final Map<String,String> excepcionesMsj = new HashMap<>();
    
    static{
        excepcionesMsj.put("DB001", "Error al conectarse a la Base de Datos, vuelva a intentarlo.");
        excepcionesMsj.put("1062", "Ya existe un registro con este codigo");
            
    }
    
    public static String excepcionBDD(Exception e){
      return excepcionesMsj.getOrDefault(((SQLException) e).getErrorCode(),"Error al conectarse a la Base de Datos, vuelva a intentarlo.");
    }
    
    public static String excepcionGeneral(Exception e){  
      return excepcionesMsj.getOrDefault(((SQLException) e).getErrorCode(),"Error al conectarse a la Base de Datos, vuelva a intentarlo.");
    }
    
    public static String controlaExepciones(Exception e){
        if(e instanceof java.sql.SQLException){
            return excepcionBDD(e);
        }else{
            return excepcionGeneral(e);
        }
    }
    
}
