
package fabrica.gestiondeproducciones.dominio;

public class LineaInsumo {
    private int id;
    private Insumo insumo;
    private int cantidad;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public LineaInsumo(int id, Insumo insumo, int cantidad) {
        this.id = id;
        this.insumo = insumo;
        this.cantidad = cantidad;
    }

    public LineaInsumo(Insumo insumo, int cantidad) {
         this.insumo = insumo;
        this.cantidad = cantidad;
    }
    
}
