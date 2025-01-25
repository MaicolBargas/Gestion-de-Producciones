
package fabrica.gestiondeproducciones.dominio;


public class AnalisisManteca extends Analisis {
    private int acidez;
    private int humedad;
    private int grasa;
    private Produccion produccion;

    public AnalisisManteca() {
    }

    public AnalisisManteca(int acidez, int humedad, int grasa, Produccion produccion) {
        this.acidez = acidez;
        this.humedad = humedad;
        this.grasa = grasa;
        this.produccion = produccion;
    }

    public AnalisisManteca(int acidez, int humedad, int grasa, Produccion produccion, int id, Empleado encargado, String fecha, int levadura, int mos, int poliformosTotales, int poliformosFecales, String tipo) {
        super(id, encargado, fecha, levadura, mos, poliformosTotales, poliformosFecales, tipo);
        this.acidez = acidez;
        this.humedad = humedad;
        this.grasa = grasa;
        this.produccion = produccion;
    }

    public int getAcidez() {
        return acidez;
    }

    public void setAcidez(int acidez) {
        this.acidez = acidez;
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
