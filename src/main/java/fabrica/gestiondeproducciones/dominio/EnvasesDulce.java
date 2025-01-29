
package fabrica.gestiondeproducciones.dominio;


public class EnvasesDulce {
    private int id;
    private String descripcion;
    private float capacidad;

    public EnvasesDulce() {
    }

    public EnvasesDulce(int id, String descripcion, float capacidad) {
        this.id = id;
        this.descripcion = descripcion;
        this.capacidad = capacidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(float capacidad) {
        this.capacidad = capacidad;
    }
}
