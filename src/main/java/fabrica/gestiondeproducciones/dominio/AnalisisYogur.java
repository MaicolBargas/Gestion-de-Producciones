
package fabrica.gestiondeproducciones.dominio;

public class AnalisisYogur extends Analisis{
    private float ph;
    private Produccion produccion;

    public AnalisisYogur() {
    }

    public AnalisisYogur(float ph, Produccion produccion) {
        this.ph = ph;
        this.produccion = produccion;
    }

    public AnalisisYogur(float ph, Produccion produccion, int id, Empleado encargado, String fecha, int levadura, int mos, int poliformosTotales, int poliformosFecales, String tipo) {
        super(id, encargado, fecha, levadura, mos, poliformosTotales, poliformosFecales, tipo);
        this.ph = ph;
        this.produccion = produccion;
    }

    public float getPh() {
        return ph;
    }

    public void setPh(float ph) {
        this.ph = ph;
    }

    public Produccion getProduccion() {
        return produccion;
    }

    public void setProduccion(Produccion produccion) {
        this.produccion = produccion;
    }

    
    
    
}
