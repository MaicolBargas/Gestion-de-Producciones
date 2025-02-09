package fabrica.gestiondeproducciones.dominio;

public class AnalisisIngreso extends AnalisisLeche {

    private IngresoLeche ingreso;

    public AnalisisIngreso() {
    }

    public AnalisisIngreso(IngresoLeche ingreso, int grasa, int proteina, int agua, float ph) {
        super(grasa, proteina, agua, ph);
        this.ingreso = ingreso;
    }

    public IngresoLeche getIngreso() {
        return ingreso;
    }

    public void setIngreso(IngresoLeche ingreso) {
        this.ingreso = ingreso;
    }

}
