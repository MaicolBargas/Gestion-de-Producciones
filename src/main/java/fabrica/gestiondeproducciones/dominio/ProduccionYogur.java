package fabrica.gestiondeproducciones.dominio;

import java.util.List;

public class ProduccionYogur extends Produccion {

    private float temperaturaIncubacion;
    private String horaComienzoIncubacion;
    private String horaFinIncubacion;
    private String tiempoIncubacion;
    private String horaComienzoEnfriado;
    private String horaFinEnfriado;
    private String tiempoTotalEnfriado;
    private float tempAguaHelada;
    private float tempAgregadoSabor;
    private float tempAgregadoColor;
    private int litrosSuero;
    private int unidadesObtenidas;

    public ProduccionYogur() {
    }

    public ProduccionYogur(float temperaturaIncubacion, String horaComienzoIncubacion, String horaFinIncubacion, String tiempoIncubacion, String horaComienzoEnfriado, String horaFinEnfriado, String tiempoTotalEnfriado, float tempAguaHelada, float tempAgregadoSabor, float tempAgregadoColor, int litrosSuero, int unidadesObtenidas) {
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

    public ProduccionYogur(float temperaturaIncubacion, String horaComienzoIncubacion, String horaFinIncubacion, String tiempoIncubacion, String horaComienzoEnfriado, String horaFinEnfriado, String tiempoTotalEnfriado, float tempAguaHelada, float tempAgregadoSabor, float tempAgregadoColor, int litrosSuero, int unidadesObtenidas, int idProduccion, String codInterno, List<LineaInsumo> listaInsumos, List<Empleado> listaEmpleados, LechePasteurizada lechep, int litros, Producto producto, int rendimiento, int kgLtsObt, String fecha, Empleado encargado, String horaInicio, String horaFin, String tiempoTrabajado, int nroTacho, String observaciones) {
        super(idProduccion, codInterno, listaInsumos, listaEmpleados, lechep, litros, producto, rendimiento, kgLtsObt, fecha, encargado, horaInicio, horaFin, tiempoTrabajado, nroTacho, observaciones);
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

    public float getTemperaturaIncubacion() {
        return temperaturaIncubacion;
    }

    public void setTemperaturaIncubacion(float temperaturaIncubacion) {
        this.temperaturaIncubacion = temperaturaIncubacion;
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

    public float getTempAguaHelada() {
        return tempAguaHelada;
    }

    public void setTempAguaHelada(float tempAguaHelada) {
        this.tempAguaHelada = tempAguaHelada;
    }

    public float getTempAgregadoSabor() {
        return tempAgregadoSabor;
    }

    public void setTempAgregadoSabor(float tempAgregadoSabor) {
        this.tempAgregadoSabor = tempAgregadoSabor;
    }

    public float getTempAgregadoColor() {
        return tempAgregadoColor;
    }

    public void setTempAgregadoColor(float tempAgregadoColor) {
        this.tempAgregadoColor = tempAgregadoColor;
    }

    public int getLitrosSuero() {
        return litrosSuero;
    }

    public void setLitrosSuero(int litrosSuero) {
        this.litrosSuero = litrosSuero;
    }

    public int getUnidadesObtenidas() {
        return unidadesObtenidas;
    }

    public void setUnidadesObtenidas(int unidadesObtenidas) {
        this.unidadesObtenidas = unidadesObtenidas;
    }

    @Override
    public Object[] produccionToArray() {
        Object[] datosProduccion = super.produccionToArray();

        Object[] datosYogur = new Object[]{
            new Object[]{"Temperatura Incubación", temperaturaIncubacion},
            new Object[]{"Hora Comienzo Incubación", horaComienzoIncubacion},
            new Object[]{"Hora Fin Incubación", horaFinIncubacion},
            new Object[]{"Tiempo Incubación", tiempoIncubacion},
            new Object[]{"Hora Comienzo Enfriado", horaComienzoEnfriado},
            new Object[]{"Hora Fin Enfriado", horaFinEnfriado},
            new Object[]{"Tiempo Total Enfriado", tiempoTotalEnfriado},
            new Object[]{"Temp. Agua Helada", tempAguaHelada},
            new Object[]{"Temp. Agregado Sabor", tempAgregadoSabor},
            new Object[]{"Temp. Agregado Color", tempAgregadoColor},
            new Object[]{"Litros de Suero", litrosSuero},
            new Object[]{"Unidades Obtenidas", unidadesObtenidas}
        };

        Object[] resultado = new Object[datosProduccion.length + datosYogur.length];
        System.arraycopy(datosProduccion, 0, resultado, 0, datosProduccion.length);
        System.arraycopy(datosYogur, 0, resultado, datosProduccion.length, datosYogur.length);

        return resultado;
    }

}
