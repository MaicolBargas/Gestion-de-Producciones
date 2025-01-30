
package fabrica.gestiondeproducciones.dominio;

public class AnalisisDulce extends Analisis{
    
    private int grasa;
    private int humedad;
    private Produccion produccion;

    public AnalisisDulce() {
    }

    public AnalisisDulce(int grasa, int humedad, Produccion produccion) {
        this.grasa = grasa;
        this.humedad = humedad;
        this.produccion = produccion;
    }

    public AnalisisDulce(int grasa, int humedad, Produccion produccion, int id, Empleado encargado, String fecha, int levadura, int mos, int poliformosTotales, int poliformosFecales, String tipo) {
        super(id, encargado, fecha, levadura, mos, poliformosTotales, poliformosFecales, tipo);
        this.grasa = grasa;
        this.humedad = humedad;
        this.produccion = produccion;
    }

    public int getGrasa() {
        return grasa;
    }

    public void setGrasa(int grasa) {
        this.grasa = grasa;
    }

    public int getHumedad() {
        return humedad;
    }

    public void setHumedad(int humedad) {
        this.humedad = humedad;
    }

    public Produccion getProduccion() {
        return produccion;
    }

    public void setProduccion(Produccion produccion) {
        this.produccion = produccion;
    }
    
    
}
