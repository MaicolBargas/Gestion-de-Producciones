
package fabrica.gestiondeproducciones.dominio;

public class AnalisisQueso extends Analisis{
    
    private int humedad;
    private int sal;
    private int ph;
    private int grasa;
    private ProduccionQueso produccion;

    public AnalisisQueso() {
    }

    public AnalisisQueso(int humedad, int sal, int ph, int grasa, ProduccionQueso produccion) {
        this.humedad = humedad;
        this.sal = sal;
        this.ph = ph;
        this.grasa = grasa;
        this.produccion = produccion;
    }

    public AnalisisQueso(int humedad, int sal, int ph, int grasa, ProduccionQueso produccion, int id, Empleado encargado, String fecha, int levadura, int mos, int poliformosTotales, int poliformosFecales, String tipo) {
        super(id, encargado, fecha, levadura, mos, poliformosTotales, poliformosFecales, tipo);
        this.humedad = humedad;
        this.sal = sal;
        this.ph = ph;
        this.grasa = grasa;
        this.produccion = produccion;
    }

    public int getHumedad() {
        return humedad;
    }

    public void setHumedad(int humedad) {
        this.humedad = humedad;
    }

    public int getSal() {
        return sal;
    }

    public void setSal(int sal) {
        this.sal = sal;
    }

    public int getPh() {
        return ph;
    }

    public void setPh(int ph) {
        this.ph = ph;
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

    public void setProduccion(ProduccionQueso produccion) {
        this.produccion = produccion;
    }
    
    
    
}
