package fabrica.gestiondeproducciones.dominio;


public class LechePasteurizada {
    private int id;
    private float temperatura;
    private int litros;
    private IngresoLeche ingreso;
    private boolean descremado;
    private int crema;
    private int cremaDisponible;

    public int getCremaDisponible() {
        return cremaDisponible;
    }

    public void setCremaDisponible(int cremaDisponible) {
        this.cremaDisponible = cremaDisponible;
    }

    public LechePasteurizada() {
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    public LechePasteurizada(int id, float temperatura, int litros, IngresoLeche ingreso, boolean descremado, int crema, int cremaDisponible) {
        this.id = id;
        this.temperatura = temperatura;
        this.litros = litros;
        this.ingreso = ingreso;
        this.descremado = descremado;
        this.crema = crema;
        this.cremaDisponible = cremaDisponible;
    }

    
    public Object[] pasteurizadaToArray() {
        return new Object[]{
            new Object[]{"Id", id},
            new Object[]{"Temperatura", temperatura},
            new Object[]{"Litros", litros},
            new Object[]{"Descremado", descremado ? "Sí, "+ crema+ " de crema obtenido" : "No"},
            new Object[]{"Ingreso de Leche", ingreso.getIdIngreso()},
            new Object[]{"Fecha ingreso", ingreso.getFecha()},
            new Object[]{"Tambo de origen", ingreso.getTambo().getPropietario()+" - "+ ingreso.getTambo().getContacto()},
            new Object[]{"Silo", ingreso.getSilo().getCodigoInterno()},                
        };
    }


    public int getLitros() {
        return litros;
    }

    public void setLitros(int litros) {
        this.litros = litros;
    }

    public IngresoLeche getIngreso() {
        return ingreso;
    }

    public void setIngreso(IngresoLeche ingreso) {
        this.ingreso = ingreso;
    }

    public boolean getDescremado() {
        return descremado;
    }

    public void setDescremado(boolean descremado) {
        this.descremado = descremado;
    }

    public int getCrema() {
        return crema;
    }

    public void setCrema(int crema) {
        this.crema = crema;
    }
    
    
}
