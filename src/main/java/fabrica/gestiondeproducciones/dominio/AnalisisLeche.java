
package fabrica.gestiondeproducciones.dominio;


public class AnalisisLeche extends Analisis {
    
    private int grasa;
    private int proteina;
    private int agua;

    public AnalisisLeche() {
    }

    public AnalisisLeche(int grasa, int proteina, int agua) {
        this.grasa = grasa;
        this.proteina = proteina;
        this.agua = agua;
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
