
package fabrica.gestiondeproducciones.dominio;

import java.util.List;

public class ProduccionDulce extends Produccion {
    private Float phLecheSn;
    private Float phLecheNeut;
    private int litrosSuero;
    private List<LineaEnvase> listaEnvases;

    public Float getPhLechSn() {
        return phLecheSn;
    }

    public void setPhLechSn(Float phLecheSn) {
        this.phLecheSn = phLecheSn;
    }

    public Float getPhLechNeut() {
        return phLecheNeut;
    }

    public void setPhLecheNeut(Float phLecheNeut) {
        this.phLecheNeut = phLecheNeut;
    }

    public List<LineaEnvase> getListaEnvases() {
        return listaEnvases;
    }

    public void setListaEnvases(List<LineaEnvase> listaEnvases) {
        this.listaEnvases = listaEnvases;
    }

    public ProduccionDulce(Float phLecheSn, Float phLecheNeut, int litrosSuero, List<LineaEnvase> listaEnvases, int idProduccion, String codInterno, List<LineaInsumo> listaInsumos, List<Empleado> listaEmpleados, LechePasteurizada lechep, int litros, Producto producto, int rendimiento, int kgLtsObt, String fecha, Empleado encargado, String horaInicio, String horaFin, String tiempoTrabajado, int nroTacho) {
        super(idProduccion, codInterno, listaInsumos, listaEmpleados, lechep, litros, producto, rendimiento, kgLtsObt, fecha, encargado, horaInicio, horaFin, tiempoTrabajado, nroTacho);
        this.phLecheSn = phLecheSn;
        this.phLecheNeut = phLecheNeut;
        this.litrosSuero = litrosSuero;
        this.listaEnvases = listaEnvases;
    }

  

    public int getLitrosSuero() {
        return litrosSuero;
    }

    public void setLitrosSuero(int litrosSuero) {
        this.litrosSuero = litrosSuero;
    }

    

    public ProduccionDulce() {
    }
    
    
    
}
