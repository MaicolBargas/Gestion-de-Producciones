
package fabrica.gestiondeproducciones.dominio;

import java.util.List;


public class ProduccionQueso extends Produccion {
    private int tempPastQueso;
    private String tiempoReposoFermento;
    private int tempReposoFermento;
    private String tipoCuajoObtenido;
    private String tiempoCuajado;
    private int tempAlCuajar;
    private int cantCuajoObtenido;
    private String tipoDeGrano;
    private int litrosSueroObtenidos;
    private String tiempoAgregadoAgua;
    private int tempAgua;
    private int tempCuajoFinal;
    private int unidadesObtenidas;
    private int acidesFermento;

    public int getTempPastQueso() {
        return tempPastQueso;
    }

    public void setTempPastQueso(int tempPastQueso) {
        this.tempPastQueso = tempPastQueso;
    }

    public String getTiempoReposoFermento() {
        return tiempoReposoFermento;
    }

    public void setTiempoReposoFermento(String tiempoReposoFermento) {
        this.tiempoReposoFermento = tiempoReposoFermento;
    }

    public int getTempReposoFermento() {
        return tempReposoFermento;
    }

    public void setTempReposoFermento(int tempReposoFermento) {
        this.tempReposoFermento = tempReposoFermento;
    }

    public String getTipoCuajoObtenido() {
        return tipoCuajoObtenido;
    }

    public void setTipoCuajoObtenido(String tipoCuajoObtenido) {
        this.tipoCuajoObtenido = tipoCuajoObtenido;
    }

    public String getTiempoCuajado() {
        return tiempoCuajado;
    }

    public void setTiempoCuajado(String tiempoCuajado) {
        this.tiempoCuajado = tiempoCuajado;
    }

    public int getTempAlCuajar() {
        return tempAlCuajar;
    }

    public void setTempAlCuajar(int tempAlCuajar) {
        this.tempAlCuajar = tempAlCuajar;
    }

    public int getCantCuajoObtenido() {
        return cantCuajoObtenido;
    }

    public void setCantCuajoObtenido(int cantCuajoObtenido) {
        this.cantCuajoObtenido = cantCuajoObtenido;
    }

    public String getTipoDeGrano() {
        return tipoDeGrano;
    }

    public void setTipoDeGrano(String tipoDeGrano) {
        this.tipoDeGrano = tipoDeGrano;
    }

    public int getLitrosSueroObtenidos() {
        return litrosSueroObtenidos;
    }

    public void setLitrosSueroObtenidos(int litrosSueroObtenidos) {
        this.litrosSueroObtenidos = litrosSueroObtenidos;
    }

    public String getTiempoAgregadoAgua() {
        return tiempoAgregadoAgua;
    }

    public void setTiempoAgregadoAgua(String tiempoAgregadoAgua) {
        this.tiempoAgregadoAgua = tiempoAgregadoAgua;
    }

    public int getTempAgua() {
        return tempAgua;
    }

    public void setTempAgua(int tempAgua) {
        this.tempAgua = tempAgua;
    }

    public int getTempCuajoFinal() {
        return tempCuajoFinal;
    }

    public void setTempCuajoFinal(int tempCuajoFinal) {
        this.tempCuajoFinal = tempCuajoFinal;
    }

    public int getUnidadesObtenidas() {
        return unidadesObtenidas;
    }

    public void setUnidadesObtenidas(int unidadesObtenidas) {
        this.unidadesObtenidas = unidadesObtenidas;
    }

    public int getAcidesFermento() {
        return acidesFermento;
    }

    public void setAcidesFermento(int acidesFermento) {
        this.acidesFermento = acidesFermento;
    }

    public ProduccionQueso(int tempPastQueso, String tiempoReposoFermento, int tempReposoFermento, String tipoCuajoObtenido, String tiempoCuajado, int tempAlCuajar, int cantCuajoObtenido, String tipoDeGrano, int litrosSueroObtenidos, String tiempoAgregadoAgua, int tempAgua, int tempCuajoFinal, int unidadesObtenidas, int acidesFermento, int idProduccion, String codInterno, List<LineaInsumo> listaInsumos, List<Empleado> listaEmpleados, LechePasteurizada lechep, int litros, Producto producto, int rendimiento, int kgLtsObt, String fecha, Empleado encargado, String horaInicio, String horaFin, String tiempoTrabajado, int nroTacho) {
        super(idProduccion, codInterno, listaInsumos, listaEmpleados, lechep, litros, producto, rendimiento, kgLtsObt, fecha, encargado, horaInicio, horaFin, tiempoTrabajado, nroTacho);
        this.tempPastQueso = tempPastQueso;
        this.tiempoReposoFermento = tiempoReposoFermento;
        this.tempReposoFermento = tempReposoFermento;
        this.tipoCuajoObtenido = tipoCuajoObtenido;
        this.tiempoCuajado = tiempoCuajado;
        this.tempAlCuajar = tempAlCuajar;
        this.cantCuajoObtenido = cantCuajoObtenido;
        this.tipoDeGrano = tipoDeGrano;
        this.litrosSueroObtenidos = litrosSueroObtenidos;
        this.tiempoAgregadoAgua = tiempoAgregadoAgua;
        this.tempAgua = tempAgua;
        this.tempCuajoFinal = tempCuajoFinal;
        this.unidadesObtenidas = unidadesObtenidas;
        this.acidesFermento = acidesFermento;
    }

    public ProduccionQueso() {
    }
    
    
}
