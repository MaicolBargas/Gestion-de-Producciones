package fabrica.gestiondeproducciones.dominio;

public class LineaEnvase {
    private int id;
    private EnvasesDulce envase;
    private int cantidad;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EnvasesDulce getEnvase() {
        return envase;
    }

    public void setEnvase(EnvasesDulce envase) {
        this.envase = envase;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public LineaEnvase(int id, EnvasesDulce envase, int cantidad) {
        this.id = id;
        this.envase = envase;
        this.cantidad = cantidad;
    }

    public LineaEnvase() {
    }
    
    
    
}
