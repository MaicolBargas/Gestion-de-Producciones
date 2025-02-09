package fabrica.gestiondeproducciones.dominio;

public class Silo {

    private int id;
    private int codigoInterno;
    private int capacidad;
    private boolean activo;

    public Silo() {
    }

    public Silo(int id, int codigoInterno, int capacidad, boolean activo) {
        this.id = id;
        this.codigoInterno = codigoInterno;
        this.capacidad = capacidad;
        this.activo = activo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCodigoInterno() {
        return codigoInterno;
    }

    public void setCodigoInterno(int codigoInterno) {
        this.codigoInterno = codigoInterno;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

}
