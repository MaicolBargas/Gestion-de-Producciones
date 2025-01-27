
package fabrica.gestiondeproducciones.dominio;

public class AnalisisYogur extends Analisis{
    private int ph;
    private int acidez;
    private Produccion produccion;

    public AnalisisYogur() {
    }

    public AnalisisYogur(int ph, int acidez, Produccion produccion) {
        this.ph = ph;
        this.acidez = acidez;
        this.produccion = produccion;
    }

    public AnalisisYogur(int ph, int acidez, Produccion produccion, int id, Empleado encargado, String fecha, int levadura, int mos, int poliformosTotales, int poliformosFecales, String tipo) {
        super(id, encargado, fecha, levadura, mos, poliformosTotales, poliformosFecales, tipo);
        this.ph = ph;
        this.acidez = acidez;
        this.produccion = produccion;
    }

    public int getPh() {
        return ph;
    }

    public void setPh(int ph) {
        this.ph = ph;
    }

    public int getAcidez() {
        return acidez;
    }

    public void setAcidez(int acidez) {
        this.acidez = acidez;
    }

    public Produccion getProduccion() {
        return produccion;
    }

    public void setProduccion(Produccion produccion) {
        this.produccion = produccion;
    }

    
    
    
}
