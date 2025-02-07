
package fabrica.gestiondeproducciones.dominio;

import java.util.List;


public class ProduccionManteca extends Produccion {
    private String horaComienzoBatido;
    private String horaFinBatido;
    private String tiempoTotalBatido;
    private int cantidad;

    public ProduccionManteca() {
    }

   

    public String getHoraComienzoBatido() {
        return horaComienzoBatido;
    }

    public void setHoraComienzoBatido(String horaComienzoBatido) {
        this.horaComienzoBatido = horaComienzoBatido;
    }

    public String getHoraFinBatido() {
        return horaFinBatido;
    }

    public void setHoraFinBatido(String horaFinBatido) {
        this.horaFinBatido = horaFinBatido;
    }

    public String getTiempoTotalBatido() {
        return tiempoTotalBatido;
    }

    public void setTiempoTotalBatido(String tiempoTotalBatido) {
        this.tiempoTotalBatido = tiempoTotalBatido;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
     public ProduccionManteca(String horaComienzoBatido, String horaFinBatido, String tiempoTotalBatido, int cantidad, int idProduccion, String codInterno, List<LineaInsumo> listaInsumos, List<Empleado> listaEmpleados, LechePasteurizada lechep,int litros, Producto producto, int rendimiento, int kgLtsObt, String fecha, Empleado encargado, String horaInicio, String horaFin, String tiempoTrabajado, int nroTacho) {
        super(idProduccion, codInterno, listaInsumos, listaEmpleados, lechep,litros, producto, rendimiento, kgLtsObt, fecha, encargado, horaInicio, horaFin, tiempoTrabajado, nroTacho);
        this.horaComienzoBatido = horaComienzoBatido;
        this.horaFinBatido = horaFinBatido;
        this.tiempoTotalBatido = tiempoTotalBatido;
        this.cantidad = cantidad;
    }
     
    @Override
    public Object[] produccionToArray() {
        Object[] datosProduccion = super.produccionToArray();

        Object[] datosManteca = new Object[]{
            new Object[]{"Hora Comienzo Batido", horaComienzoBatido},
            new Object[]{"Hora Fin Batido", horaFinBatido},
            new Object[]{"Tiempo Total Batido", tiempoTotalBatido},
            new Object[]{"Cantidad", cantidad}
        };

        Object[] resultado = new Object[datosProduccion.length + datosManteca.length];
        System.arraycopy(datosProduccion, 0, resultado, 0, datosProduccion.length);
        System.arraycopy(datosManteca, 0, resultado, datosProduccion.length, datosManteca.length);

        return resultado;
    }

}
