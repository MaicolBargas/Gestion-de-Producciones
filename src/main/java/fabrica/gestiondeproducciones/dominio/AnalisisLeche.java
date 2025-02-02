
package fabrica.gestiondeproducciones.dominio;


public class AnalisisLeche extends Analisis {
    
    private int grasa;
    private int proteina;
    private int agua;
    private float ph;

    public AnalisisLeche() {
    }

    public float getPh() {
        return ph;
    }

    public void setPh(float ph) {
        this.ph = ph;
    }

    public AnalisisLeche(int grasa, int proteina, int agua, float ph) {
        this.grasa = grasa;
        this.proteina = proteina;
        this.agua = agua;
        this.ph = ph;
    }

   

    public int getGrasa() {
        return grasa;
    }

    public void setGrasa(int grasa) {
        this.grasa = grasa;
    }

    public int getProteina() {
        return proteina;
    }

    public void setProteina(int proteina) {
        this.proteina = proteina;
    }

    public int getAgua() {
        return agua;
    }

    public void setAgua(int agua) {
        this.agua = agua;
    }
    
}
