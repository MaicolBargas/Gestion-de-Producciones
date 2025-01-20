
package fabrica.gestiondeproducciones.dominio;

import java.util.List;


public class Produccion {
    private int idProduccion;
    private String codInterno;
    private List<LineaInsumo> listaInsumos;
    private List<Empleado> listaEmpleados;
    private LechePasteurizada lechep;
    private int litros;
    private Producto producto;
    private int rendimiento;
    private int kgLtsObt;
    private String fecha;
    private Empleado encargado;
    private String horaInicio;
    private String horaFin;
    private String tiempoTrabajado;
    private int nroTacho;

    public void setIdProduccion(int idProduccion) {
        this.idProduccion = idProduccion;
    }

    public void setCodInterno(String codInterno) {
        this.codInterno = codInterno;
    }

    public int getLitros() {
        return litros;
    }

    public void setLitros(int litros) {
        this.litros = litros;
    }

    public void setListaInsumos(List<LineaInsumo> listaInsumos) {
        this.listaInsumos = listaInsumos;
    }

    public void setListaEmpleados(List<Empleado> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    public void setLechep(LechePasteurizada lechep) {
        this.lechep = lechep;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void setRendimiento(int rendimiento) {
        this.rendimiento = rendimiento;
    }

    public void setKgLtsObt(int kgLtsObt) {
        this.kgLtsObt = kgLtsObt;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setEncargado(Empleado encargado) {
        this.encargado = encargado;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public void setTiempoTrabajado(String tiempoTrabajado) {
        this.tiempoTrabajado = tiempoTrabajado;
    }

    public void setNroTacho(int nroTacho) {
        this.nroTacho = nroTacho;
    }

    public int getIdProduccion() {
        return idProduccion;
    }

    public String getCodInterno() {
        return codInterno;
    }

    public List<LineaInsumo> getListaInsumos() {
        return listaInsumos;
    }

    public List<Empleado> getListaEmpleados() {
        return listaEmpleados;
    }

    public LechePasteurizada getLechep() {
        return lechep;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getRendimiento() {
        return rendimiento;
    }

    public int getKgLtsObt() {
        return kgLtsObt;
    }

    public String getFecha() {
        return fecha;
    }

    public Empleado getEncargado() {
        return encargado;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public String getTiempoTrabajado() {
        return tiempoTrabajado;
    }

    public int getNroTacho() {
        return nroTacho;
    }

    public Produccion() {
    }

    public Produccion(int idProduccion, String codInterno, List<LineaInsumo> listaInsumos, List<Empleado> listaEmpleados, LechePasteurizada lechep,int litros, Producto producto, int rendimiento, int kgLtsObt, String fecha, Empleado encargado, String horaInicio, String horaFin, String tiempoTrabajado, int nroTacho) {
        this.idProduccion = idProduccion;
        this.codInterno = codInterno;
        this.listaInsumos = listaInsumos;
        this.listaEmpleados = listaEmpleados;
        this.lechep = lechep;
        this.litros=litros;
        this.producto = producto;
        this.rendimiento = rendimiento;
        this.kgLtsObt = kgLtsObt;
        this.fecha = fecha;
        this.encargado = encargado;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.tiempoTrabajado = tiempoTrabajado;
        this.nroTacho = nroTacho;
    }
    
    
    
}
