package fabrica.gestiondeproducciones.dominio;

public class Tambo {

    private int id;
    private String propietario;
    private String contacto;
    private String direccion;

    public Tambo() {
    }

    public Tambo(int id, String propietario, String contacto, String direccion) {
        this.id = id;
        this.propietario = propietario;
        this.contacto = contacto;
        this.direccion = direccion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}
