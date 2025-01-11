
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
    
    public Silo buscarSiloXCodigo(int codigo){
        return persistenciaSilo.buscarSiloXCodigo(codigo);
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
    
    PersistenciaIngresoLeche persistenciaIngreso = new PersistenciaIngresoLeche();
    public boolean altaIngreso(IngresoLeche ingreso){
       return persistenciaIngreso.altaIngreso(ingreso);
    }
    
    public List listarIngresos(){
        return persistenciaIngreso.listarIngresos();
    }
    
    public boolean bajaIngreso(int id){
        return persistenciaIngreso.bajaIngreso(id);
    }
    
    public boolean modificarIngreso(IngresoLeche ingreso){
       return persistenciaIngreso.modificarIngreso(ingreso);
    }
    
    public IngresoLeche buscarIngreso(int id){
        return persistenciaIngreso.buscarIngreso(id);
    }
    
    PersistenciaPasteurizado persistenciaPasteurizado = new PersistenciaPasteurizado();
    
    public boolean altaPasteurizado(LechePasteurizada lecheP){
       return persistenciaPasteurizado.altaPasteurizado(lecheP);
    }
    
    public List listarPasteurizados(){
        return persistenciaPasteurizado.listarPasteurizados();
    }
    
    public boolean bajaPasteurizado(int id){
        return persistenciaPasteurizado.bajaPasteurizado(id);
    }
    
    public boolean modificarPasteurizado(LechePasteurizada lecheP){
       return persistenciaPasteurizado.modificarPasteurizado(lecheP);
    }
    
    public LechePasteurizada buscarPasteurizado(int id){
        return persistenciaPasteurizado.buscarPasteurizado(id);
    }
    
    
    
    PersistenciaAnalisis persistenciaAnalisis = new PersistenciaAnalisis();
    public boolean altaAnalisisIngreso(AnalisisIngreso analisis){
       return persistenciaAnalisis.altaAnalisisIngreso(analisis);
    }
    
    public List listarAnalisisIngreso(){
        return persistenciaAnalisis.listarAnalisisIngreso();
    }
    
    public boolean bajaAnalisis(int id){
        return persistenciaAnalisis.bajaAnalisis(id);
    }
    
    public boolean modificarAnalisisIngreso(AnalisisIngreso analisis){
       return persistenciaAnalisis.modificarAnalisisIngreso(analisis);
    }
    
    public AnalisisIngreso buscarAnalisisIngreso(int id){
        return persistenciaAnalisis.buscarAnalisisIngreso(id);
    }
    
    
    public boolean altaAnalisisLechePast(AnalisisLechePasteurizada analisisLechePast){
       return persistenciaAnalisis.altaAnalisisLechePast(analisisLechePast);
    }
    
    public List listarAnalisisLechePast(){
        return persistenciaAnalisis.listarAnalisisLechePast();
    }
    
    
    
    public boolean modificarAnalisisLechePast(AnalisisLechePasteurizada analisisLechePast){
       return persistenciaAnalisis.modificarAnalisisLechePast(analisisLechePast);
    }
    
    public AnalisisLechePasteurizada buscarAnalisisLechePast(int id){
        return persistenciaAnalisis.buscarAnalisisLechePast(id);
    }
    
}
