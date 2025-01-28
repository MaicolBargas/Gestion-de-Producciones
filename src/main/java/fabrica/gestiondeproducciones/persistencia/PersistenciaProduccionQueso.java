package fabrica.gestiondeproducciones.persistencia;

import fabrica.gestiondeproducciones.dominio.Empleado;
import fabrica.gestiondeproducciones.dominio.LechePasteurizada;
import fabrica.gestiondeproducciones.dominio.LineaInsumo;

import fabrica.gestiondeproducciones.dominio.ProduccionQueso;

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

public class PersistenciaProduccionQueso {

    Conexion conexion = new Conexion();
    Connection con;
    PreparedStatement consulta;
    PersistenciaProduccion persProduccion = new PersistenciaProduccion();
    PersistenciaPasteurizado persLecheP = new PersistenciaPasteurizado();
    PersistenciaEmpleado persEmpleado = new PersistenciaEmpleado();
    PersistenciaProducto persProducto = new PersistenciaProducto();

    ResultSet resultado;

    public boolean altaProduccionQueso(ProduccionQueso produccion) {
        String sqlProduccion = "INSERT INTO produccion "
                + "(codInterno, idLechePast,litros, idProducto, rendimiento, "
                + "kgLtsObt, fecha, encargadoId, horaInicio, horaFin, tiempoTrabajado, nroTacho) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
        String sqlProduccionQueso = "INSERT INTO produccion_queso"
                + "(idProduccion,"
                + "tempPastQueso,"
                + "tiempoReposoFermento,"
                + "tempReposoFermento,"
                + "tipoCuajoObtenido,"
                + "tiempoCuajado,"
                + "tempAlCuajar,"
                + "cantCuajoObtenido,"
                + "tipoDeGrano,"
                + "litrosSueroObtenidos,"
                + "tiempoAgregadoAgua,"
                + "tempAgua,"
                + "tempCuajoFinal,"
                + "unidadesObtenidas,"
                + "acidesFermento) "
                + "VALUES (?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?)";

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
            consulta = con.prepareStatement(sqlProduccionQueso);
            consulta.setInt(1, idProduccion);
            consulta.setInt(2,produccion.getTempPastQueso());
            consulta.setString(3, produccion.getTiempoReposoFermento());
            consulta.setInt(4,produccion.getTempReposoFermento());
            consulta.setString(5,produccion.getTipoCuajoObtenido());
            consulta.setString(6,produccion.getTiempoCuajado());
            consulta.setInt(7,produccion.getTempAlCuajar());
            consulta.setInt(8,produccion.getCantCuajoObtenido());
            consulta.setString(9,produccion.getTipoDeGrano());
            consulta.setInt(10,produccion.getLitrosSueroObtenidos());
            consulta.setString(11,produccion.getTiempoAgregadoAgua());
            consulta.setInt(12,produccion.getTempAgua());
            consulta.setInt(13,produccion.getTempCuajoFinal());
            consulta.setInt(14,produccion.getUnidadesObtenidas());
            consulta.setInt(15,produccion.getAcidesFermento());
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

    public void agregarEmpleado(int idProd, int idEmpleado) {
        String sqlAgregarEmpleados = "INSERT INTO produccion_empleados" + "(idProduccion,idEmpleado) VALUES (?,?)";

        try {
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sqlAgregarEmpleados);
            consulta.setInt(1, idProd);
            consulta.setInt(2, idEmpleado);
            consulta.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            }
        }
    }

    public boolean agregarInsumos(int idProd, int idInsumo, int cantidad) {
        String sql = "INSERT INTO linea_insumos" + "(idProduccion,idInsumo,cantidad) VALUES (?,?,?)";

        try {
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            consulta.setInt(1, idProd);
            consulta.setInt(2, idInsumo);
            consulta.setInt(3, cantidad);
            consulta.execute();
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

    public List listarProduccionesQueso() {
        List<ProduccionQueso> lista = new ArrayList<>();
        String sql = "SELECT * FROM produccion p INNER JOIN produccion_queso pm  On p.idProduccion=pm.idProduccion where p.activo='1' and pm.activo='1' GROUP BY p.idProduccion";
        try {
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            resultado = consulta.executeQuery();
            while (resultado.next()) {
                ProduccionQueso produccion = new ProduccionQueso();
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

                produccion.setTempPastQueso(resultado.getInt("tempPastQueso"));
                produccion.setTiempoReposoFermento(resultado.getString("tiempoReposoFermento"));
                produccion.setTempReposoFermento(resultado.getInt("tempReposoFermento"));
                produccion.setTipoCuajoObtenido(resultado.getString("tipoCuajoObtenido"));
                produccion.setTiempoCuajado(resultado.getString("tiempoCuajado"));
                produccion.setTempAlCuajar(resultado.getInt("tempAlCuajar"));
                produccion.setCantCuajoObtenido(resultado.getInt("cantCuajoObtenido"));
                produccion.setTipoDeGrano(resultado.getString("tipoDeGrano"));
                produccion.setLitrosSueroObtenidos(resultado.getInt("litrosSueroObtenidos"));
                produccion.setTiempoAgregadoAgua(resultado.getString("tiempoAgregadoAgua"));
                produccion.setTempAgua(resultado.getInt("tempAgua"));
                produccion.setTempCuajoFinal(resultado.getInt("tempCuajoFinal"));
                produccion.setUnidadesObtenidas(resultado.getInt("unidadesObtenidas"));
                produccion.setAcidesFermento(resultado.getInt("acidesFermento")); 

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

    public ProduccionQueso buscarProduccionQueso(int id) {
        String sql = "SELECT * FROM produccion p INNER JOIN produccion_queso pm  On p.idProduccion=pm.idProduccion where p.activo='1' and pm.activo='1' and p.idProduccion=?";
        //"SELECT * FROM produccion WHERE idProduccion = ? AND activo = '1'";
        try {
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            consulta.setInt(1, id);
            resultado = consulta.executeQuery();

            while (resultado.next()) {
                ProduccionQueso produccion = new ProduccionQueso();
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

//                listarInfoEspecifica(produccion);     
                produccion.setTempPastQueso(resultado.getInt("tempPastQueso"));
                produccion.setTiempoReposoFermento(resultado.getString("tiempoReposoFermento"));
                produccion.setTempReposoFermento(resultado.getInt("tempReposoFermento"));
                produccion.setTipoCuajoObtenido(resultado.getString("tipoCuajoObtenido"));
                produccion.setTiempoCuajado(resultado.getString("tiempoCuajado"));
                produccion.setTempAlCuajar(resultado.getInt("tempAlCuajar"));
                produccion.setCantCuajoObtenido(resultado.getInt("cantCuajoObtenido"));
                produccion.setTipoDeGrano(resultado.getString("tipoDeGrano"));
                produccion.setLitrosSueroObtenidos(resultado.getInt("litrosSueroObtenidos"));
                produccion.setTiempoAgregadoAgua(resultado.getString("tiempoAgregadoAgua"));
                produccion.setTempAgua(resultado.getInt("tempAgua"));
                produccion.setTempCuajoFinal(resultado.getInt("tempCuajoFinal"));
                produccion.setUnidadesObtenidas(resultado.getInt("unidadesObtenidas"));
                produccion.setAcidesFermento(resultado.getInt("acidesFermento")); 
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

    public boolean modificarProduccionQueso(ProduccionQueso produccion) {
        String sql = "UPDATE produccion SET codInterno = ?, idLechePast = ?, idProducto = ?, rendimiento = ?, kgLtsObt = ?, fecha = ?, encargadoId = ?, horaInicio = ? ,horaFin = ?, tiempoTrabajado = ?, nroTacho = ?, litros=? WHERE idProduccion = ?";
        String sqlProduccionQueso = "UPDATE produccion_queso SET "
                + "tempPastQueso = ?, "
                + "tiempoReposoFermento = ?, "
                + "tempReposoFermento = ?, "
                + "tipoCuajoObtenido = ?, "
                + "tiempoCuajado = ?, "
                + "tempAlCuajar = ?, "
                + "cantCuajoObtenido = ?, "
                + "tipoDeGrano = ?, "
                + "litrosSueroObtenidos = ?, "
                + "tiempoAgregadoAgua = ?, "
                + "tempAgua = ?, "
                + "tempCuajoFinal = ?, "
                + "unidadesObtenidas = ?, "
                + "acidesFermento = ? "
                + "WHERE idProduccion = ?";

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
            consulta.setInt(13, produccion.getIdProduccion());
            consulta.executeUpdate();
            
            consulta = con.prepareStatement(sqlProduccionQueso);
            consulta.setInt(1,produccion.getTempPastQueso());
            consulta.setString(2, produccion.getTiempoReposoFermento());
            consulta.setInt(3,produccion.getTempReposoFermento());
            consulta.setString(4,produccion.getTipoCuajoObtenido());
            consulta.setString(5,produccion.getTiempoCuajado());
            consulta.setInt(6,produccion.getTempAlCuajar());
            consulta.setInt(7,produccion.getCantCuajoObtenido());
            consulta.setString(8,produccion.getTipoDeGrano());
            consulta.setInt(9,produccion.getLitrosSueroObtenidos());
            consulta.setString(10,produccion.getTiempoAgregadoAgua());
            consulta.setInt(11,produccion.getTempAgua());
            consulta.setInt(12,produccion.getTempCuajoFinal());
            consulta.setInt(13,produccion.getUnidadesObtenidas());
            consulta.setInt(14,produccion.getAcidesFermento());
            consulta.setInt(15, produccion.getIdProduccion());
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
    
    public List listarQuesoPendienteAnalizar() {
        List<ProduccionQueso> lista = new ArrayList<>();
        String sql = "SELECT p.*, pm.* FROM produccion p INNER JOIN produccion_queso pm ON p.idProduccion = pm.idProduccion LEFT JOIN analisis a ON p.idProduccion = a.idProduccion WHERE p.activo = '1' AND pm.activo = '1' AND a.idProduccion IS NULL OR a.activo = '0' GROUP BY p.idProduccion;";
        try {
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            resultado = consulta.executeQuery();
            while (resultado.next()) {
                ProduccionQueso produccion = new ProduccionQueso();
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

                produccion.setTempPastQueso(resultado.getInt("tempPastQueso"));
                produccion.setTiempoReposoFermento(resultado.getString("tiempoReposoFermento"));
                produccion.setTempReposoFermento(resultado.getInt("tempReposoFermento"));
                produccion.setTipoCuajoObtenido(resultado.getString("tipoCuajoObtenido"));
                produccion.setTiempoCuajado(resultado.getString("tiempoCuajado"));
                produccion.setTempAlCuajar(resultado.getInt("tempAlCuajar"));
                produccion.setCantCuajoObtenido(resultado.getInt("cantCuajoObtenido"));
                produccion.setTipoDeGrano(resultado.getString("tipoDeGrano"));
                produccion.setLitrosSueroObtenidos(resultado.getInt("litrosSueroObtenidos"));
                produccion.setTiempoAgregadoAgua(resultado.getString("tiempoAgregadoAgua"));
                produccion.setTempAgua(resultado.getInt("tempAgua"));
                produccion.setTempCuajoFinal(resultado.getInt("tempCuajoFinal"));
                produccion.setUnidadesObtenidas(resultado.getInt("unidadesObtenidas"));
                produccion.setAcidesFermento(resultado.getInt("acidesFermento")); 

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

/*private void listarInfoEspecifica(ProduccionManteca produccion){
        String sql = "SELECT * FROM produccion_manteca WHERE activo = '1'";
        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            resultado = consulta.executeQuery();
            while(resultado.next()){           
                produccion.setHoraComienzoBatido(resultado.getString("comienzoBatido"));
                produccion.setHoraFinBatido(resultado.getString("finBatido"));
                produccion.setTiempoTotalBatido(resultado.getString("totalBatido"));
                produccion.setCantidad(resultado.getInt("ormas"));
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
        }
    }*/
