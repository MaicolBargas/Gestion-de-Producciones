
package fabrica.gestiondeproducciones.dominio;


public class AnalisisLechePasteurizada extends AnalisisLeche{
    
    private LechePasteurizada lechePast;

    public LechePasteurizada getLechePast() {
        return lechePast;
    }

    public void setLechePast(LechePasteurizada lechePast) {
        this.lechePast = lechePast;
    }

    public AnalisisLechePasteurizada() {
    }

    public AnalisisLechePasteurizada(int grasa, int proteina, int agua) {
        super(grasa, proteina, agua);
    }
    
    
}
