package fabrica.gestiondeproducciones.dominio;

import fabrica.gestiondeproducciones.dominio.Tambo;
import fabrica.gestiondeproducciones.dominio.Silo;

import java.sql.Date;

public class IngresoLeche {

    private int idIngreso;
    private Tambo tambo;
    private int litros;
    private Silo silo;
    private Date fecha;
    private boolean activo;

    public IngresoLeche() {
    }

    public IngresoLeche(int idIngreso, Tambo tambo, int litros, Silo silo, Date fecha, boolean activo) {
        this.idIngreso = idIngreso;
        this.tambo = tambo;
        this.litros = litros;
        this.silo = silo;
        this.fecha = fecha;
        this.activo = activo;
    }

    public int getIdIngreso() {
        return idIngreso;
    }

    public void setIdIngreso(int idIngreso) {
        this.idIngreso = idIngreso;
    }

    public Tambo getTambo() {
        return tambo;
    }

    public void setTambo(Tambo tambo) {
        this.tambo = tambo;
    }

    public int getLitros() {
        return litros;
    }

    public void setLitros(int litros) {
        this.litros = litros;
    }

    public Silo getSilo() {
        return silo;
    }

    public void setSilo(Silo silo) {
        this.silo = silo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

}
