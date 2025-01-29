
package fabrica.gestiondeproducciones.dominio;

import java.util.List;


public class ProduccionQueso extends Produccion {
    private float tempPastQueso;
    private String tiempoReposoFermento;
    private float tempReposoFermento;
    private String tipoCuajoObtenido;
    private String tiempoCuajado;
    private float tempAlCuajar;
    private int cantCuajoObtenido;
    private String tipoDeGrano;
    private int litrosSueroObtenidos;
    private String tiempoAgregadoAgua;
    private float tempAgua;
    private float tempCuajoFinal;
    private int unidadesObtenidas;
    private float acidesFermento;

    public ProduccionQueso() {
    }

    public ProduccionQueso(float tempPastQueso, String tiempoReposoFermento, float tempReposoFermento, String tipoCuajoObtenido, String tiempoCuajado, float tempAlCuajar, int cantCuajoObtenido, String tipoDeGrano, int litrosSueroObtenidos, String tiempoAgregadoAgua, float tempAgua, float tempCuajoFinal, int unidadesObtenidas, float acidesFermento) {
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

    public ProduccionQueso(float tempPastQueso, String tiempoReposoFermento, float tempReposoFermento, String tipoCuajoObtenido, String tiempoCuajado, float tempAlCuajar, int cantCuajoObtenido, String tipoDeGrano, int litrosSueroObtenidos, String tiempoAgregadoAgua, float tempAgua, float tempCuajoFinal, int unidadesObtenidas, float acidesFermento, int idProduccion, String codInterno, List<LineaInsumo> listaInsumos, List<Empleado> listaEmpleados, LechePasteurizada lechep, int litros, Producto producto, int rendimiento, int kgLtsObt, String fecha, Empleado encargado, String horaInicio, String horaFin, String tiempoTrabajado, int nroTacho) {
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

    public float getTempPastQueso() {
        return tempPastQueso;
    }

    public void setTempPastQueso(float tempPastQueso) {
        this.tempPastQueso = tempPastQueso;
    }

    public String getTiempoReposoFermento() {
        return tiempoReposoFermento;
    }

    public void setTiempoReposoFermento(String tiempoReposoFermento) {
        this.tiempoReposoFermento = tiempoReposoFermento;
    }

    public float getTempReposoFermento() {
        return tempReposoFermento;
    }

    public void setTempReposoFermento(float tempReposoFermento) {
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

    public float getTempAlCuajar() {
        return tempAlCuajar;
    }

    public void setTempAlCuajar(float tempAlCuajar) {
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

    public float getTempAgua() {
        return tempAgua;
    }

    public void setTempAgua(float tempAgua) {
        this.tempAgua = tempAgua;
    }

    public float getTempCuajoFinal() {
        return tempCuajoFinal;
    }

    public void setTempCuajoFinal(float tempCuajoFinal) {
        this.tempCuajoFinal = tempCuajoFinal;
    }

    public int getUnidadesObtenidas() {
        return unidadesObtenidas;
    }

    public void setUnidadesObtenidas(int unidadesObtenidas) {
        this.unidadesObtenidas = unidadesObtenidas;
    }

    public float getAcidesFermento() {
        return acidesFermento;
    }

    public void setAcidesFermento(float acidesFermento) {
        this.acidesFermento = acidesFermento;
    }

    
    
}
