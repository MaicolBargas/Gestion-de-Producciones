
package fabrica.gestiondeproducciones.dominio;


public class AnalisisManteca extends Analisis {
    private int ph;
    private int humedad;
    private int grasa;
    private Produccion produccion;

    public AnalisisManteca() {
    }

    public AnalisisManteca(int ph, int humedad, int grasa, Produccion produccion) {
        this.ph = ph;
        this.humedad = humedad;
        this.grasa = grasa;
        this.produccion = produccion;
    }

    public AnalisisManteca(int ph, int humedad, int grasa, Produccion produccion, int id, Empleado encargado, String fecha, int levadura, int mos, int poliformosTotales, int poliformosFecales, String tipo) {
        super(id, encargado, fecha, levadura, mos, poliformosTotales, poliformosFecales, tipo);
        this.ph = ph;
        this.humedad = humedad;
        this.grasa = grasa;
        this.produccion = produccion;
    }

    public int getPh() {
        return ph;
    }

    public void setPh(int acidez) {
        this.ph = acidez;
    }

    public int getHumedad() {
        return humedad;
    }

    public void setHumedad(int humedad) {
        this.humedad = humedad;
    }

    public int getGrasa() {
        return grasa;
    }

    public void setGrasa(int grasa) {
        this.grasa = grasa;
    }

    public Produccion getProduccion() {
        return produccion;
    }

    public void setProduccion(Produccion produccion) {
        this.produccion = produccion;
    }
    
    
}
