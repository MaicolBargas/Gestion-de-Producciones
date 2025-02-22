package fabrica.gestiondeproducciones.persistencia;

import fabrica.gestiondeproducciones.dominio.Empleado;
import fabrica.gestiondeproducciones.dominio.LechePasteurizada;
import fabrica.gestiondeproducciones.dominio.LineaInsumo;
import fabrica.gestiondeproducciones.dominio.ProduccionYogur;
import fabrica.gestiondeproducciones.dominio.Producto;
import fabrica.gestiondeproducciones.utilidades.Excepciones;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class PersistenciaProduccionYogur {

    Conexion conexion = new Conexion();
    Connection con;
    PreparedStatement consulta;
    PersistenciaProduccion persProduccion = new PersistenciaProduccion();
    PersistenciaPasteurizado persLecheP = new PersistenciaPasteurizado();
    PersistenciaEmpleado persEmpleado = new PersistenciaEmpleado();
    PersistenciaProducto persProducto = new PersistenciaProducto();

    ResultSet resultado;

    public boolean altaProduccionYogur(ProduccionYogur produccion) {
        String sqlProduccion = "INSERT INTO produccion "
                + "(codInterno, idLechePast,litros, idProducto, rendimiento, "
                + "kgLtsObt, fecha, encargadoId, horaInicio, horaFin, tiempoTrabajado, nroTacho, observaciones) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String sqlProduccionYogur = "INSERT INTO produccion_yogur"
                + "(idProduccion, tempIncubacion,horaComienzoInc,horaFinInc,tiempoIncubacion,horaComienzoEnfriado,horaFinEnfriado"
                + ",tiempoTotalEnfriado,tempAguaHelada,tempAgregadoSabor,tempAgregadoColor,litrosSuero,unidadesObtenidas) "
                + "VALUES (?, ?, ?, ?, ?,?,?,?,?,?,?,?,?)";

        try {
            con = conexion.obtenerConexion();

            consulta = con.prepareStatement(sqlProduccion, Statement.RETURN_GENERATED_KEYS);
            consulta.setString(1, produccion.getCodInterno());
            consulta.setInt(2, produccion.getLechep().getId());
            consulta.setInt(3, produccion.getLitros());
            consulta.setInt(4, produccion.getProducto().getId());
            consulta.setFloat(5, produccion.getRendimiento());
            consulta.setInt(6, produccion.getKgLtsObt());
            consulta.setString(7, produccion.getFecha());
            consulta.setInt(8, produccion.getEncargado().getId());
            consulta.setString(9, produccion.getHoraInicio());
            consulta.setString(10, produccion.getHoraFin());
            consulta.setString(11, produccion.getTiempoTrabajado());
            consulta.setInt(12, produccion.getNroTacho());
            consulta.setString(13, produccion.getObservaciones());

            consulta.executeUpdate();

            // Obtener el ID generado automáticamente
            ResultSet rs = consulta.getGeneratedKeys();
            int idProduccion = -1;
            if (rs.next()) {
                idProduccion = rs.getInt(1);
            }

            if (idProduccion == -1) {
                throw new SQLException("No se pudo obtener el ID de la tabla producciones.");
            }

            // Preparar el segundo insert
            consulta = con.prepareStatement(sqlProduccionYogur);
            consulta.setInt(1, idProduccion);
            consulta.setFloat(2, produccion.getTemperaturaIncubacion());
            consulta.setString(3, produccion.getHoraComienzoIncubacion());
            consulta.setString(4, produccion.getHoraFinIncubacion());
            consulta.setString(5, produccion.getTiempoIncubacion());
            consulta.setString(6, produccion.getHoraComienzoEnfriado());
            consulta.setString(7, produccion.getHoraFinEnfriado());
            consulta.setString(8, produccion.getTiempoTotalEnfriado());
            consulta.setFloat(9, produccion.getTempAguaHelada());
            consulta.setFloat(10, produccion.getTempAgregadoSabor());
            consulta.setFloat(11, produccion.getTempAgregadoColor());
            consulta.setInt(12, produccion.getLitrosSuero());
            consulta.setInt(13, produccion.getUnidadesObtenidas());

            consulta.executeUpdate();

            for (Empleado empleado : produccion.getListaEmpleados()) {
                persProduccion.agregarEmpleado(idProduccion, empleado.getId());
            }

            for (LineaInsumo insumo : produccion.getListaInsumos()) {
                persProduccion.agregarInsumos(idProduccion, insumo.getInsumo().getId(), insumo.getCantidad());
            }
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            return false;

        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            }
        }
    }

    public List listarProduccionesYogur() {
        List<ProduccionYogur> lista = new ArrayList<>();
        String sql = "SELECT * FROM produccion p INNER JOIN produccion_yogur pm  On p.idProduccion=pm.idProduccion where p.activo='1' and pm.activo='1' GROUP BY p.idProduccion";
        try {
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            resultado = consulta.executeQuery();
            while (resultado.next()) {
                ProduccionYogur produccion = new ProduccionYogur();
                int id = resultado.getInt("idProduccion");
                produccion.setIdProduccion(id);
                produccion.setCodInterno(resultado.getString("codInterno"));
                LechePasteurizada lecheP = persLecheP.buscarPasteurizado(resultado.getInt("idLechePast"));

                if (lecheP instanceof LechePasteurizada) {
                    produccion.setLechep(lecheP);
                }

                Producto producto = persProducto.buscarProducto(resultado.getInt("idProducto"));
                if (producto instanceof Producto) {
                    produccion.setProducto(producto);
                }

                produccion.setRendimiento(resultado.getInt("rendimiento"));
                produccion.setKgLtsObt(resultado.getInt("kgLtsObt"));
                produccion.setFecha(resultado.getString("fecha"));
                produccion.setLitros(resultado.getInt("litros"));
                Empleado encargado = persEmpleado.buscarEmpleado(resultado.getInt("encargadoId"));
                if (encargado instanceof Empleado) {
                    produccion.setEncargado(encargado);
                }
                produccion.setHoraInicio(resultado.getString("horaInicio"));
                produccion.setHoraFin(resultado.getString("horaFin"));
                produccion.setTiempoTrabajado(resultado.getString("tiempoTrabajado"));
                produccion.setNroTacho(resultado.getInt("NroTacho"));
                produccion.setObservaciones(resultado.getString("observaciones"));
                produccion.setTemperaturaIncubacion(resultado.getFloat("tempIncubacion"));
                produccion.setHoraComienzoIncubacion(resultado.getString("horaComienzoInc"));
                produccion.setHoraFinIncubacion(resultado.getString("horaFinInc"));
                produccion.setTiempoIncubacion(resultado.getString("tiempoIncubacion"));
                produccion.setHoraComienzoEnfriado(resultado.getString("horaComienzoEnfriado"));
                produccion.setHoraFinEnfriado(resultado.getString("horaFinEnfriado"));
                produccion.setTiempoTotalEnfriado(resultado.getString("tiempoTotalEnfriado"));
                produccion.setTempAguaHelada(resultado.getFloat("tempAguaHelada"));
                produccion.setTempAgregadoSabor(resultado.getFloat("tempAgregadoSabor"));
                produccion.setTempAgregadoColor(resultado.getFloat("tempAgregadoColor"));
                produccion.setLitrosSuero(resultado.getInt("litrosSuero"));
                produccion.setUnidadesObtenidas(resultado.getInt("unidadesObtenidas"));

                List<Empleado> empleados = persProduccion.listarEmpleadosXProduccion(id);
                produccion.setListaEmpleados(empleados);

                List<LineaInsumo> insumos = persProduccion.listarInsumoXProduccion(id);
                produccion.setListaInsumos(insumos);
                lista.add(produccion);
            }
            return lista;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            return null;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            }

        }
    }

    public ProduccionYogur buscarProduccionYogur(int id) {
        String sql = "SELECT * FROM produccion p INNER JOIN produccion_yogur pm  On p.idProduccion=pm.idProduccion where p.activo='1' and pm.activo='1' and p.idProduccion=?";
        try {
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            consulta.setInt(1, id);
            resultado = consulta.executeQuery();

            while (resultado.next()) {
                ProduccionYogur produccion = new ProduccionYogur();
                produccion.setIdProduccion(id);
                produccion.setCodInterno(resultado.getString("codInterno"));
                LechePasteurizada lecheP = persLecheP.buscarPasteurizado(resultado.getInt("idLechePast"));

                if (lecheP instanceof LechePasteurizada) {
                    produccion.setLechep(lecheP);
                }

                Producto producto = persProducto.buscarProducto(resultado.getInt("idProducto"));
                if (producto instanceof Producto) {
                    produccion.setProducto(producto);
                }

                produccion.setRendimiento(resultado.getInt("rendimiento"));
                produccion.setKgLtsObt(resultado.getInt("kgLtsObt"));
                produccion.setFecha(resultado.getString("fecha"));
                produccion.setLitros(resultado.getInt("litros"));

                Empleado encargado = persEmpleado.buscarEmpleado(resultado.getInt("encargadoId"));
                if (encargado instanceof Empleado) {
                    produccion.setEncargado(encargado);
                }
                produccion.setHoraInicio(resultado.getString("horaInicio"));
                produccion.setHoraFin(resultado.getString("horaFin"));
                produccion.setTiempoTrabajado(resultado.getString("tiempoTrabajado"));
                produccion.setNroTacho(resultado.getInt("NroTacho"));
                produccion.setObservaciones(resultado.getString("observaciones"));

                produccion.setTemperaturaIncubacion(resultado.getFloat("tempIncubacion"));
                produccion.setHoraComienzoIncubacion(resultado.getString("horaComienzoInc"));
                produccion.setHoraFinIncubacion(resultado.getString("horaFinInc"));
                produccion.setTiempoIncubacion(resultado.getString("tiempoIncubacion"));
                produccion.setHoraComienzoEnfriado(resultado.getString("horaComienzoEnfriado"));
                produccion.setHoraFinEnfriado(resultado.getString("horaFinEnfriado"));
                produccion.setTiempoTotalEnfriado(resultado.getString("tiempoTotalEnfriado"));
                produccion.setTempAguaHelada(resultado.getFloat("tempAguaHelada"));
                produccion.setTempAgregadoSabor(resultado.getFloat("tempAgregadoSabor"));
                produccion.setTempAgregadoColor(resultado.getFloat("tempAgregadoColor"));
                produccion.setLitrosSuero(resultado.getInt("litrosSuero"));
                produccion.setUnidadesObtenidas(resultado.getInt("unidadesObtenidas"));
                List<Empleado> empleados = persProduccion.listarEmpleadosXProduccion(id);
                produccion.setListaEmpleados(empleados);

                List<LineaInsumo> insumos = persProduccion.listarInsumoXProduccion(id);
                produccion.setListaInsumos(insumos);
                return produccion;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            return null;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            }
        }
        return null;
    }

    public boolean modificarProduccionYogur(ProduccionYogur produccion) {
        String sql = "UPDATE produccion SET codInterno = ?, idLechePast = ?, idProducto = ?, rendimiento = ?, kgLtsObt = ?, fecha = ?, encargadoId = ?, horaInicio = ? ,horaFin = ?, tiempoTrabajado = ?, nroTacho = ?, litros = ?, observaciones = ? WHERE idProduccion = ?";
        String sqlProduccionYogur = "UPDATE produccion_yogur SET  tempIncubacion=?,horaComienzoInc=?,horaFinInc=?,"
                + "tiempoIncubacion=?,horaComienzoEnfriado=?,horaFinEnfriado=?"
                + ",tiempoTotalEnfriado=?,tempAguaHelada=?,tempAgregadoSabor=?,tempAgregadoColor=?,litrosSuero=?, unidadesObtenidas=?"
                + " WHERE idProduccion = ?";
        try {
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            consulta.setString(1, produccion.getCodInterno());
            consulta.setInt(2, produccion.getLechep().getId());
            consulta.setInt(3, produccion.getProducto().getId());
            consulta.setFloat(4, produccion.getRendimiento());
            consulta.setInt(5, produccion.getKgLtsObt());
            consulta.setString(6, produccion.getFecha());
            consulta.setInt(7, produccion.getEncargado().getId());
            consulta.setString(8, produccion.getHoraInicio());
            consulta.setString(9, produccion.getHoraFin());
            consulta.setString(10, produccion.getTiempoTrabajado());
            consulta.setInt(11, produccion.getNroTacho());
            consulta.setInt(12, produccion.getLitros());
            consulta.setString(13, produccion.getObservaciones());
            consulta.setInt(14, produccion.getIdProduccion());
            consulta.executeUpdate();

            consulta = con.prepareStatement(sqlProduccionYogur);
            consulta.setFloat(1, produccion.getTemperaturaIncubacion());
            consulta.setString(2, produccion.getHoraComienzoIncubacion());
            consulta.setString(3, produccion.getHoraFinIncubacion());
            consulta.setString(4, produccion.getTiempoIncubacion());
            consulta.setString(5, produccion.getHoraComienzoEnfriado());
            consulta.setString(6, produccion.getHoraFinEnfriado());
            consulta.setString(7, produccion.getTiempoTotalEnfriado());
            consulta.setFloat(8, produccion.getTempAguaHelada());
            consulta.setFloat(9, produccion.getTempAgregadoSabor());
            consulta.setFloat(10, produccion.getTempAgregadoColor());
            consulta.setInt(11, produccion.getLitrosSuero());
            consulta.setInt(12, produccion.getUnidadesObtenidas());
            consulta.setInt(13, produccion.getIdProduccion());
            consulta.executeUpdate();

            persProduccion.actualizarEmpleadosxProduccion(produccion.getIdProduccion(), produccion.getListaEmpleados());
            persProduccion.actualizarInsumosxProduccion(produccion.getIdProduccion(), produccion.getListaInsumos());
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            }
        }
    }

    public List listarYogurPendienteAnalizar() {
        List<ProduccionYogur> lista = new ArrayList<>();
        String sql = """
                     SELECT p.*, pm.* FROM produccion p INNER JOIN produccion_yogur pm ON p.idProduccion = pm.idProduccion WHERE p.activo = '1' AND pm.activo = '1'
                     AND NOT EXISTS (
                         SELECT 1 FROM analisis a 
                         WHERE a.idProduccion = p.idProduccion 
                         AND a.activo = '1'
                     );""";
        try {
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            resultado = consulta.executeQuery();
            while (resultado.next()) {
                ProduccionYogur produccion = new ProduccionYogur();
                int id = resultado.getInt("idProduccion");
                produccion.setIdProduccion(id);
                produccion.setCodInterno(resultado.getString("codInterno"));
                LechePasteurizada lecheP = persLecheP.buscarPasteurizado(resultado.getInt("idLechePast"));

                if (lecheP instanceof LechePasteurizada) {
                    produccion.setLechep(lecheP);
                }

                Producto producto = persProducto.buscarProducto(resultado.getInt("idProducto"));
                if (producto instanceof Producto) {
                    produccion.setProducto(producto);
                }

                produccion.setRendimiento(resultado.getInt("rendimiento"));
                produccion.setKgLtsObt(resultado.getInt("kgLtsObt"));
                produccion.setFecha(resultado.getString("fecha"));
                produccion.setLitros(resultado.getInt("litros"));
                Empleado encargado = persEmpleado.buscarEmpleado(resultado.getInt("encargadoId"));
                if (encargado instanceof Empleado) {
                    produccion.setEncargado(encargado);
                }
                produccion.setHoraInicio(resultado.getString("horaInicio"));
                produccion.setHoraFin(resultado.getString("horaFin"));
                produccion.setTiempoTrabajado(resultado.getString("tiempoTrabajado"));
                produccion.setNroTacho(resultado.getInt("NroTacho"));
                produccion.setTemperaturaIncubacion(resultado.getFloat("tempIncubacion"));
                produccion.setHoraComienzoIncubacion(resultado.getString("horaComienzoInc"));
                produccion.setHoraFinIncubacion(resultado.getString("horaFinInc"));
                produccion.setTiempoIncubacion(resultado.getString("tiempoIncubacion"));
                produccion.setHoraComienzoEnfriado(resultado.getString("horaComienzoEnfriado"));
                produccion.setHoraFinEnfriado(resultado.getString("horaFinEnfriado"));
                produccion.setTiempoTotalEnfriado(resultado.getString("tiempoTotalEnfriado"));
                produccion.setTempAguaHelada(resultado.getFloat("tempAguaHelada"));
                produccion.setTempAgregadoSabor(resultado.getFloat("tempAgregadoSabor"));
                produccion.setTempAgregadoColor(resultado.getFloat("tempAgregadoColor"));
                produccion.setLitrosSuero(resultado.getInt("litrosSuero"));
                produccion.setUnidadesObtenidas(resultado.getInt("unidadesObtenidas"));
                produccion.setObservaciones(resultado.getString("observaciones"));

                List<Empleado> empleados = persProduccion.listarEmpleadosXProduccion(id);
                produccion.setListaEmpleados(empleados);

                List<LineaInsumo> insumos = persProduccion.listarInsumoXProduccion(id);
                produccion.setListaInsumos(insumos);
                lista.add(produccion);
            }
            return lista;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            return null;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            }
        }
    }
}

