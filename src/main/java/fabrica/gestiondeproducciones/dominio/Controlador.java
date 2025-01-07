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
    
    public Silo buscarSiloXCodigo(int codigo){
        return persistencia.buscarSiloXCodigo(codigo);
    }
    
    public boolean altaProducto(Producto producto){
        return persistencia.altaProducto(producto);
    }
    
    public List listarProductos(){
        return persistencia.listarProductos();
    }
    
    public boolean bajaProducto (int id){
        return persistencia.bajaProducto(id);
    }
    
    public boolean modificarProducto (Producto producto ){
       return persistencia.modificarProducto(producto);
    }
    
    public Producto buscarProducto(int id){
        return persistencia.buscarProducto(id);
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
    
    
    public boolean altaInsumo(Insumo tambo){
       return persistencia.altaInsumo(tambo);
    }
    
    public List listarInsumos(){
        return persistencia.listarInsumos();
    }
    
    public boolean bajaInsumo(int id){
        return persistencia.bajaInsumo(id);
    }
    
    public boolean modificarInsumo(Insumo tambo){
       return persistencia.modificarInsumo(tambo);
    }
    
    public Insumo buscarInsumo(int id){
        return persistencia.buscarInsumo(id);
    }
    
    public boolean altaEmpleado(Empleado empleado){
       return persistencia.altaEmpleado(empleado);
    }
    
    public List listarEmpleados(){
        return persistencia.listarEmpleados();
    }
    
    public boolean bajaEmpleado(int id){
        return persistencia.bajaEmpleado(id);
    }
    
    public boolean modificarEmpleado(Empleado empleado){
       return persistencia.modificarEmpleado(empleado);
    }
    
    public Empleado buscarEmpleado(int id){
        return persistencia.buscarEmpleado(id);
    }
    
    public Empleado buscarEmpleadoXCi(int ci){
        return persistencia.buscarEmpleado(ci);
    }
    
    public boolean altaIngreso(IngresoLeche ingreso){
       return persistencia.altaIngreso(ingreso);
    }
    
    public List listarIngresos(){
        return persistencia.listarIngresos();
    }
    
    public boolean bajaIngreso(int id){
        return persistencia.bajaIngreso(id);
    }
    
    public boolean modificarIngreso(IngresoLeche ingreso){
       return persistencia.modificarIngreso(ingreso);
    }
    
    public IngresoLeche buscarIngreso(int id){
        return persistencia.buscarIngreso(id);
    }
    
        public boolean altaPasteurizado(LechePasteurizada lecheP){
       return persistencia.altaPasteurizado(lecheP);
    }
    
    public List listarPasteurizados(){
        return persistencia.listarPasteurizados();
    }
    
    public boolean bajaPasteurizado(int id){
        return persistencia.bajaPasteurizado(id);
    }
    
    public boolean modificarPasteurizado(LechePasteurizada lecheP){
       return persistencia.modificarPasteurizado(lecheP);
    }
    
    public LechePasteurizada buscarPasteurizado(int id){
        return persistencia.buscarPasteurizado(id);
    }
   
}
