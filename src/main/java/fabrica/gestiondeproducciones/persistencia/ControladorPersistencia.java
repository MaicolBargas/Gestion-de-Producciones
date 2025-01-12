
package fabrica.gestiondeproducciones.persistencia;

import fabrica.gestiondeproducciones.dominio.*;
import fabrica.gestiondeproducciones.dominio.Seccion;
import fabrica.gestiondeproducciones.dominio.Tambo;
import java.util.List;

public class ControladorPersistencia {
    
    // <editor-fold defaultstate="collapsed" desc="Silo">

    PersistenciaSilo persistenciaSilo = new PersistenciaSilo();

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
        // </editor-fold>  

    // <editor-fold defaultstate="collapsed" desc="Producto">
    PersistenciaProducto persistenciaProducto= new PersistenciaProducto();

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
        // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc="Seccion">

    PersistenciaSeccion persistenciaSeccion = new PersistenciaSeccion();

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
      // </editor-fold> 
    
    // <editor-fold defaultstate="collapsed" desc="Tambo">

    PersistenciaTambo persistenciaTambo = new PersistenciaTambo();

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
       // </editor-fold>   

    // <editor-fold defaultstate="collapsed" desc="Insumo">

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
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc="Empleado">    
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
   // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc="Ingreso Leche">    
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
    
    public List listarIngresosPendientesAnalizar(){
        return persistenciaIngreso.listarIngresosPendientesAnalizar();
    }
     // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc="Leche Pasteurizada">

    PersistenciaPasteurizado persistenciaPasteurizado = new PersistenciaPasteurizado();
    
    public boolean altaPasteurizado(LechePasteurizada lecheP){
       return persistenciaPasteurizado.altaPasteurizado(lecheP);
    }
    
    public List listarPasteurizados(){
        return persistenciaPasteurizado.listarPasteurizados();
    }
    
    public List listarPasteurizadosPendientesAnalizar(){
        return persistenciaPasteurizado.listarPasteurizadosPendientesAnalizar();
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
     // </editor-fold>    
    
    // <editor-fold defaultstate="collapsed" desc="Analisis Ingreso Leche">    
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
    
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc="Analisis Leche Pasteurizada">
    
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
     // </editor-fold> 
   
}
