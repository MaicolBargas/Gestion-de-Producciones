
package fabrica.gestiondeproducciones.dominio;

public class AnalisisIngreso extends AnalisisLeche{
    
    private IngresoLeche ingreso;

    public AnalisisIngreso() {
    }

    public AnalisisIngreso(int grasa, int proteina, int agua) {
        super(grasa, proteina, agua);
    }

    public IngresoLeche getIngreso() {
        return ingreso;
    }

    public void setIngreso(IngresoLeche ingreso) {
        this.ingreso = ingreso;
    }
    
    
}
