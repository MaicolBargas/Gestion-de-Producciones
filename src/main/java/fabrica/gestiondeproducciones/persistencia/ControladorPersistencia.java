
package fabrica.gestiondeproducciones.persistencia;

import fabrica.gestiondeproducciones.dominio.*;
import fabrica.gestiondeproducciones.dominio.Seccion;
import fabrica.gestiondeproducciones.dominio.Tambo;
import java.util.List;

public class ControladorPersistencia {
    PersistenciaSilo persistenciaSilo = new PersistenciaSilo();
    PersistenciaSeccion persistenciaSeccion = new PersistenciaSeccion();
    PersistenciaTambo persistenciaTambo = new PersistenciaTambo();
    PersistenciaProducto persistenciaProducto= new PersistenciaProducto();
   
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
    
    
    
    
     public boolean altaProducto(Producto producto){
       return persistenciaProducto.altaProducto(producto);
    }
    
    public List listarProductos(){
        return persistenciaProducto.listarProductos();
    }
    
    public boolean bajaProducto(int id){
        return persistenciaProducto.bajaProducto(id);
    }
    
    public boolean modificarProducto(Producto producto){
       return persistenciaProducto.modificarProducto(producto);
    }
    
    public Producto buscarProducto(int id){
        return persistenciaProducto.buscarProducto(id);
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
    
    
    PersistenciaInsumo persistenciaInsumo = new PersistenciaInsumo();
    public boolean altaInsumo(Insumo tambo){
       return persistenciaInsumo.altaInsumo(tambo);
    }
    
    public List listarInsumos(){
        return persistenciaInsumo.listarInsumos();
    }
    
    public boolean bajaInsumo(int id){
        return persistenciaInsumo.bajaInsumo(id);
    }
    
    public boolean modificarInsumo(Insumo tambo){
       return persistenciaInsumo.modificarInsumo(tambo);
    }
    
    public Insumo buscarInsumo(int id){
        return persistenciaInsumo.buscarInsumo(id);
    }
    
    PersistenciaEmpleado persistenciaEmpleado = new PersistenciaEmpleado();
    public boolean altaEmpleado(Empleado empleado){
       return persistenciaEmpleado.altaEmpleado(empleado);
    }
    
    public List listarEmpleados(){
        return persistenciaEmpleado.listarEmpleados();
    }
    
    public boolean bajaEmpleado(int id){
        return persistenciaEmpleado.bajaEmpleado(id);
    }
    
    public boolean modificarEmpleado(Empleado empleado){
       return persistenciaEmpleado.modificarEmpleado(empleado);
    }
    
    public Empleado buscarEmpleado(int id){
        return persistenciaEmpleado.buscarEmpleado(id);
    }
    
    public Empleado buscarEmpleadoXCi(int ci){
        return persistenciaEmpleado.buscarEmpleadoXCi(ci);
    }
}
