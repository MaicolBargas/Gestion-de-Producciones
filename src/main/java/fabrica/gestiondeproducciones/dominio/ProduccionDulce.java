
package fabrica.gestiondeproducciones.dominio;

public class ProduccionDulce extends Produccion {
    private Float phLecheSn;
    private Float phLecheNeut;
    private int litrosSuero;

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

    public ProduccionDulce(Float phLecheSn, Float phLecheNeut, int litrosSuero) {
        this.phLecheSn = phLecheSn;
        this.phLecheNeut = phLecheNeut;
        this.litrosSuero = litrosSuero;
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
