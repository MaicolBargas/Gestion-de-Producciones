
package fabrica.gestiondeproducciones.dominio;


public class Analisis {
    private int id;
    private int codigo;
    private String fecha;
    private int levadura;
    private int mos;
    private int poliformosTotales;
    private int poliformosFecales;
    private String tipo;

    public Analisis() {
    }

    public Analisis(int id, int codigo, String fecha, int levadura, int mos, int poliformosTotales, int poliformosFecales, String tipo) {
        this.id = id;
        this.codigo = codigo;
        this.fecha = fecha;
        this.levadura = levadura;
        this.mos = mos;
        this.poliformosTotales = poliformosTotales;
        this.poliformosFecales = poliformosFecales;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getLevadura() {
        return levadura;
    }

    public void setLevadura(int levadura) {
        this.levadura = levadura;
    }

    public int getMos() {
        return mos;
    }

    public void setMos(int mos) {
        this.mos = mos;
    }

    public int getPoliformosTotales() {
        return poliformosTotales;
    }

    public void setPoliformosTotales(int poliformosTotales) {
        this.poliformosTotales = poliformosTotales;
    }

    public int getPoliformosFecales() {
        return poliformosFecales;
    }

    public void setPoliformosFecales(int poliformosFecales) {
        this.poliformosFecales = poliformosFecales;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
    
}
