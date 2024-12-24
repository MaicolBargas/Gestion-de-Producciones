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
    
    
    public boolean altaTambo(Tambo tambo){
        return persistencia.altaTambo(tambo);
    }
    
    public List listarTambo(){
        return persistencia.listarTambos();
    }
    
    public boolean bajaTambo(int id){
        return persistencia.bajaTambo(id);
    }
    
    public boolean modificarTambo(Tambo tambo){
       return persistencia.modificarTambo(tambo);
    }
    
    public Tambo buscarTambo(int id){
        return persistencia.buscarTambo(id);
    }
    
    
}
