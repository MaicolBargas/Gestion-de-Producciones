package fabrica.gestiondeproducciones.dominio;

public class AnalisisLechePasteurizada extends AnalisisLeche {

    private LechePasteurizada lechePast;

    public LechePasteurizada getLechePast() {
        return lechePast;
    }

    public void setLechePast(LechePasteurizada lechePast) {
        this.lechePast = lechePast;
    }

    public AnalisisLechePasteurizada() {
    }

    public AnalisisLechePasteurizada(LechePasteurizada lechePast, int grasa, int proteina, int agua, float ph) {
        super(grasa, proteina, agua, ph);
        this.lechePast = lechePast;
    }

}
