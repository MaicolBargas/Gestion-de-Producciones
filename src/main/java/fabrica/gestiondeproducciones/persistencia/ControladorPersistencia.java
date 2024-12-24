
package fabrica.gestiondeproducciones.persistencia;

import fabrica.gestiondeproducciones.dominio.Silo;

import fabrica.gestiondeproducciones.dominio.Seccion;

import fabrica.gestiondeproducciones.dominio.Tambo;

import java.util.List;

public class ControladorPersistencia {
    PersistenciaSilo persistenciaSilo = new PersistenciaSilo();
    PersistenciaSeccion persistenciaSeccion = new PersistenciaSeccion();
    PersistenciaTambo persistenciaTambo = new PersistenciaTambo();
   
    public boolean altaSilo(Silo silo){
       return persistenciaSilo.altaSilo(silo);
    }
    
    public List listarSilos(){
        return persistenciaSilo.listarSilos();
    }
    
    public boolean bajaSilo(int id){
        return persistenciaSilo.bajaSilo(id);
    }
    
    public boolean modificarSilo(Silo silo){
       return persistenciaSilo.modificarSilo(silo);
    }
    
    public Silo buscarSilo(int id){
        return persistenciaSilo.buscarSilo(id);
    }
    

     public boolean altaSeccion(Seccion seccion){
       return persistenciaSeccion.altaSeccion(seccion);
    }
    
    public List listarSecciones(){
        return persistenciaSeccion.listarSecciones();
    }
    
    public boolean bajaSeccion(int id){
        return persistenciaSeccion.bajaSeccion(id);
    }
    
    public boolean modificarSeccion(Seccion seccion){
       return persistenciaSeccion.modificarSeccion(seccion);
    }
    
    public Seccion buscarSeccion(int id) {
        return persistenciaSeccion.buscarSeccion(id);
}
    
    
     
    
public boolean altaTambo(Tambo tambo){
       return persistenciaTambo.altaTambo(tambo);
    }
    
    public List listarTambos(){
        return persistenciaTambo.listarTambos();
    }
    
    public boolean bajaTambo(int id){
        return persistenciaTambo.bajaTambo(id);
    }
    
    public boolean modificarTambo(Tambo tambo){
       return persistenciaTambo.modificarTambo(tambo);
    }
    
    public Tambo buscarTambo(int id){
        return persistenciaTambo.buscarTambo(id);

    }
}
