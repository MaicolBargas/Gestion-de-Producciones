
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
}
