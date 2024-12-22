package fabrica.gestiondeproducciones.dominio;

import fabrica.gestiondeproducciones.persistencia.ControladorPersistencia;


public class Controlador {
    ControladorPersistencia persistencia = new ControladorPersistencia();
    
    /* Gestion de Silos */
    public boolean altaSilo(Silo silo){
        return persistencia.altaSilo(silo);
    }
}
