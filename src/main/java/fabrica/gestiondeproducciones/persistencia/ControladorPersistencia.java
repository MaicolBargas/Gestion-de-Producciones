
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
    
    public List listarPasteurizadosNoUsados(){
        return persistenciaPasteurizado.listarPasteurizadosProducidos();
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
    
    // <editor-fold defaultstate="collapsed" desc="Analisis Manteca">    
    public boolean altaAnalisisManteca(AnalisisManteca analisis){
       return persistenciaAnalisis.altaAnalisisManteca(analisis);
    }
    
    public List listarAnalisisManteca(){
        return persistenciaAnalisis.listarAnalisisManteca();
    }
    
   
    public boolean modificarAnalisisManteca(AnalisisManteca analisis){
       return persistenciaAnalisis.modificarAnalisisManteca(analisis);
    }
    
    public AnalisisManteca buscarAnalisisManteca(int id){
        return persistenciaAnalisis.buscarAnalisisManteca(id);
    }
    
    // </editor-fold> 
    
    // <editor-fold defaultstate="collapsed" desc="Analisis Yogur">    
    public boolean altaAnalisisYogur(AnalisisYogur analisis){
       return persistenciaAnalisis.altaAnalisisYogur(analisis);
    }
    
    public List listarAnalisisYogur(){
        return persistenciaAnalisis.listarAnalisisYogur();
    }
    
   
    public boolean modificarAnalisisYogur(AnalisisYogur analisis){
       return persistenciaAnalisis.modificarAnalisisYogur(analisis);
    }
    
    public AnalisisYogur buscarAnalisisYogur(int id){
        return persistenciaAnalisis.buscarAnalisisYogur(id);
    }
    
    // </editor-fold>     
    
    // <editor-fold defaultstate="collapsed" desc="Analisis Queso">    
    public boolean altaAnalisisQueso(AnalisisQueso analisis){
       return persistenciaAnalisis.altaAnalisisQueso(analisis);
    }
    
    public List listarAnalisisQueso(){
        return persistenciaAnalisis.listarAnalisisQueso();
    }
    
   
    public boolean modificarAnalisisQueso(AnalisisQueso analisis){
       return persistenciaAnalisis.modificarAnalisisQueso(analisis);
    }
    
    public AnalisisQueso buscarAnalisisQueso(int id){
        return persistenciaAnalisis.buscarAnalisisQueso(id);
    }
    
    // </editor-fold>         
    
    // <editor-fold defaultstate="collapsed" desc="Produccion">
    
    PersistenciaProduccion persistenciaProduccion= new PersistenciaProduccion();
    PersistenciaProduccionManteca persistenciaProduccionManteca= new PersistenciaProduccionManteca();
    PersistenciaProduccionYogur persistenciaProduccionYogur=new PersistenciaProduccionYogur();
    PersistenciaProduccionQueso persistenciaProduccionQueso= new PersistenciaProduccionQueso();
    PersistenciaProduccionDulce persistenciaProduccionDulce= new PersistenciaProduccionDulce();
    PersistenciaEnvases persistenciaEnvases= new PersistenciaEnvases();
    
    public boolean altaProduccion(Produccion produccion){
        return persistenciaProduccion.altaProduccion(produccion);
    }
    
    public boolean bajaProduccion(int id, String tabla) throws Exception{
        return persistenciaProduccion.bajaProduccion(id, tabla);
    }
        
    // <editor-fold defaultstate="collapsed" desc="Produccion Manteca">
    public boolean altaProduccionManteca(ProduccionManteca produccion)
    {
        return persistenciaProduccionManteca.altaProduccionManteca(produccion);
    }
    
    public List listarProduccionesManteca(){
        return persistenciaProduccionManteca.listarProduccionesManteca();
    }
    
    public ProduccionManteca buscarProduccionManteca(int id){
        return persistenciaProduccionManteca.buscarProduccionManteca(id);
    }
    
    public boolean modificarProduccionManteca(ProduccionManteca produccion){
        return persistenciaProduccionManteca.modificarProduccionManteca(produccion);
    }
    
    public List listarMantecaPendienteAnalizar(){
        return persistenciaProduccionManteca.listarMantecaPendienteAnalizar();
    }
    // </editor-fold>  

    // <editor-fold defaultstate="collapsed" desc="Produccion Yogur">
    public boolean altaProduccionYogur(ProduccionYogur produccion)
    {
        return persistenciaProduccionYogur.altaProduccionYogur(produccion);
    }
    
    public List listarProduccionesYogur(){
        return persistenciaProduccionYogur.listarProduccionesYogur();
    }
    
    public ProduccionYogur buscarProduccionYogur(int id){
        return persistenciaProduccionYogur.buscarProduccionYogur(id);
    }
    
    public boolean modificarProduccionYogur(ProduccionYogur produccion){
        return persistenciaProduccionYogur.modificarProduccionYogur(produccion);
    }
    
    public List listarYogurPendienteAnalizar(){
        return persistenciaProduccionYogur.listarYogurPendienteAnalizar();
    }
    // </editor-fold>  
    
    // <editor-fold defaultstate="collapsed" desc="Produccion Queso">
    public boolean altaProduccionQueso(ProduccionQueso produccion)
    {
        return persistenciaProduccionQueso.altaProduccionQueso(produccion);
    }
    
    public List listarProduccionesQueso(){
        return persistenciaProduccionQueso.listarProduccionesQueso();
    }
    
    public ProduccionQueso buscarProduccionQueso(int id){
        return persistenciaProduccionQueso.buscarProduccionQueso(id);
    }
    
    public boolean modificarProduccionQueso(ProduccionQueso produccion){
        return persistenciaProduccionQueso.modificarProduccionQueso(produccion);
    }
    
    public List listarQuesoPendienteAnalizar(){
        return persistenciaProduccionQueso.listarQuesoPendienteAnalizar();
    }
    // </editor-fold>  
     // <editor-fold defaultstate="collapsed" desc="Produccion Dulce">
    public boolean altaProduccionDulce(ProduccionDulce produccion)
    {
        return persistenciaProduccionDulce.altaProduccionDulce(produccion);
    }
    
    public List listarProduccionesDulce(){
        return persistenciaProduccionDulce.listarProduccionesDulce();
    }
    
    public ProduccionDulce buscarProduccionDulce(int id){
        return persistenciaProduccionDulce.buscarProduccionDulce(id);
    }
    
    public boolean modificarProduccionDulce(ProduccionDulce produccion){
        return persistenciaProduccionDulce.modificarProduccionDulce(produccion);
    }
    
    public List listarDulcePendienteAnalizar(){
        return persistenciaProduccionDulce.listarDulcePendienteAnalizar();
    }
    // </editor-fold> 
    
   // </editor-fold>  
   
    // <editor-fold defaultstate="collapsed" desc="Envases Dulce">
    public boolean altaEnvase(EnvasesDulce envase)
    {
        return persistenciaEnvases.altaEnvase(envase);
    }
    
    public List listarEnvases(){
        return persistenciaEnvases.listarEnvases();
    }
    
    public EnvasesDulce buscarEnvase(int id){
        return persistenciaEnvases.buscarEnvase(id);
    }
    
    public boolean modificarEnvase(EnvasesDulce envase){
        return persistenciaEnvases.modificarEnvase(envase);
    }
     public boolean bajaEnvase(int id){
        return persistenciaEnvases.bajaEnvase(id);
    }
    
    // </editor-fold>  
}
