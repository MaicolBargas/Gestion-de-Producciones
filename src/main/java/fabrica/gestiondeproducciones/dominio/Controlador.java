package fabrica.gestiondeproducciones.dominio;

import fabrica.gestiondeproducciones.persistencia.ControladorPersistencia;
import java.util.List;


public class Controlador {
    ControladorPersistencia persistencia = new ControladorPersistencia();
    
    public boolean altaSilo(Silo silo){
        return persistencia.altaSilo(silo);
    }
    
    public List listarSilos(){
        return persistencia.listarSilos();
    }
    
    public boolean bajaSilo(int id){
        return persistencia.bajaSilo(id);
    }
    
    public boolean modificarSilo(Silo silo){
       return persistencia.modificarSilo(silo);
    }
    
    public Silo buscarSilo(int id){
        return persistencia.buscarSilo(id);
    }
    
    public boolean altaSeccion(Seccion seccion){
        return persistencia.altaSeccion(seccion);
    }
    
    public List listarSecciones(){
        return persistencia.listarSecciones();
    }
    
    public boolean bajaSeccion(int id){
        return persistencia.bajaSeccion(id);
    }
    
    public boolean modificarSeccion(Seccion seccion){
       return persistencia.modificarSeccion(seccion);
    }
    
    public Seccion buscarSeccion(int id){
        return persistencia.buscarSeccion(id);
    }
    
    
    
}
