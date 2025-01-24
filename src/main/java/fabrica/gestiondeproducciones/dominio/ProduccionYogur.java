
package fabrica.gestiondeproducciones.dominio;

import java.util.List;


public class ProduccionYogur extends Produccion{
    
    private int temperaturaIncubacion;
    private String horaComienzoIncubacion;
    private String horaFinIncubacion;
    private String tiempoIncubacion;
    private String horaComienzoEnfriado;
    private String horaFinEnfriado;
    private String tiempoTotalEnfriado;
    private int tempAguaHelada;
    private int tempAgregadoSabor;
    private int tempAgregadoColor;
    private int litrosSuero;
    private int unidadesObtenidas;

    public int getTemperaturaIncubacion() {
        return temperaturaIncubacion;
    }

    public void setTemperaturaIncubacion(int temperaturaIncubacion) {
        this.temperaturaIncubacion = temperaturaIncubacion;
    }

    public String getTiempoIncubacion() {
        return tiempoIncubacion;
    }

    public void setTiempoIncubacion(String tiempoIncubacion) {
        this.tiempoIncubacion = tiempoIncubacion;
    }

    public String getHoraComienzoEnfriado() {
        return horaComienzoEnfriado;
    }

    public void setHoraComienzoEnfriado(String horaComienzoEnfriado) {
        this.horaComienzoEnfriado = horaComienzoEnfriado;
    }

    public String getHoraFinEnfriado() {
        return horaFinEnfriado;
    }

    public void setHoraFinEnfriado(String horaFinEnfriado) {
        this.horaFinEnfriado = horaFinEnfriado;
    }

    public String getTiempoTotalEnfriado() {
        return tiempoTotalEnfriado;
    }

    public void setTiempoTotalEnfriado(String tiempoTotalEnfriado) {
        this.tiempoTotalEnfriado = tiempoTotalEnfriado;
    }

    public int getTempAguaHelada() {
        return tempAguaHelada;
    }

    public void setTempAguaHelada(int tempAguaHelada) {
        this.tempAguaHelada = tempAguaHelada;
    }

    public int getTempAgregadoSabor() {
        return tempAgregadoSabor;
    }

    public void setTempAgregadoSabor(int tempAgregadoSabor) {
        this.tempAgregadoSabor = tempAgregadoSabor;
    }

    public int getTempAgregadoColor() {
        return tempAgregadoColor;
    }

    public void setTempAgregadoColor(int tempAgregadoColor) {
        this.tempAgregadoColor = tempAgregadoColor;
    }

    public int getLitrosSuero() {
        return litrosSuero;
    }

    public void setLitrosSuero(int litrosSuero) {
        this.litrosSuero = litrosSuero;
    }

    public String getHoraComienzoIncubacion() {
        return horaComienzoIncubacion;
    }

    public void setHoraComienzoIncubacion(String horaComienzoIncubacion) {
        this.horaComienzoIncubacion = horaComienzoIncubacion;
    }

    public String getHoraFinIncubacion() {
        return horaFinIncubacion;
    }

    public void setHoraFinIncubacion(String horaFinIncubacion) {
        this.horaFinIncubacion = horaFinIncubacion;
    }

    public int getUnidadesObtenidas() {
        return unidadesObtenidas;
    }

    public void setUnidadesObtenidas(int unidadesObtenidas) {
        this.unidadesObtenidas = unidadesObtenidas;
    }

    public ProduccionYogur(int temperaturaIncubacion, String horaComienzoIncubacion, String horaFinIncubacion, String tiempoIncubacion, String horaComienzoEnfriado, String horaFinEnfriado, String tiempoTotalEnfriado, int tempAguaHelada, int tempAgregadoSabor, int tempAgregadoColor, int litrosSuero, int unidadesObtenidas, int idProduccion, String codInterno, List<LineaInsumo> listaInsumos, List<Empleado> listaEmpleados, LechePasteurizada lechep, int litros, Producto producto, int rendimiento, int kgLtsObt, String fecha, Empleado encargado, String horaInicio, String horaFin, String tiempoTrabajado, int nroTacho) {
        super(idProduccion, codInterno, listaInsumos, listaEmpleados, lechep, litros, producto, rendimiento, kgLtsObt, fecha, encargado, horaInicio, horaFin, tiempoTrabajado, nroTacho);
        this.temperaturaIncubacion = temperaturaIncubacion;
        this.horaComienzoIncubacion = horaComienzoIncubacion;
        this.horaFinIncubacion = horaFinIncubacion;
        this.tiempoIncubacion = tiempoIncubacion;
        this.horaComienzoEnfriado = horaComienzoEnfriado;
        this.horaFinEnfriado = horaFinEnfriado;
        this.tiempoTotalEnfriado = tiempoTotalEnfriado;
        this.tempAguaHelada = tempAguaHelada;
        this.tempAgregadoSabor = tempAgregadoSabor;
        this.tempAgregadoColor = tempAgregadoColor;
        this.litrosSuero = litrosSuero;
        this.unidadesObtenidas = unidadesObtenidas;
    }

    

    



    public ProduccionYogur() {
    }
 
}
