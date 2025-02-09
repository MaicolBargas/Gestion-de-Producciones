package fabrica.gestiondeproducciones.dominio;

import java.util.Objects;

public class LineaInsumo {

    private int id;
    private Insumo insumo;
    private float cantidad;

    public LineaInsumo() {
    }

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

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public LineaInsumo(int id, Insumo insumo, float cantidad) {
        this.id = id;
        this.insumo = insumo;
        this.cantidad = cantidad;
    }

    public LineaInsumo(Insumo insumo, float cantidad) {
        this.insumo = insumo;
        this.cantidad = cantidad;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.insumo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LineaInsumo other = (LineaInsumo) obj;
        return Objects.equals(this.insumo, other.insumo);
    }

}
