
package fabrica.gestiondeproducciones.persistencia;

import fabrica.gestiondeproducciones.dominio.Empleado;
import fabrica.gestiondeproducciones.dominio.EnvasesDulce;
import fabrica.gestiondeproducciones.dominio.LechePasteurizada;
import fabrica.gestiondeproducciones.dominio.LineaEnvase;
import fabrica.gestiondeproducciones.dominio.LineaInsumo;
import fabrica.gestiondeproducciones.dominio.ProduccionDulce;

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


public class PersistenciaProduccionDulce {
    Conexion conexion = new Conexion();
    Connection con;
    PreparedStatement consulta;
    PersistenciaProduccion persProduccion = new PersistenciaProduccion();
    PersistenciaPasteurizado persLecheP = new PersistenciaPasteurizado();
    PersistenciaEmpleado persEmpleado = new PersistenciaEmpleado();
    PersistenciaProducto persProducto = new PersistenciaProducto();

    ResultSet resultado;

    
    public boolean altaProduccionDulce(ProduccionDulce produccion) {
    String sqlProduccion = "INSERT INTO produccion " + 
        "(codInterno, idLechePast,litros, idProducto, rendimiento, kgLtsObt, fecha, encargadoId, horaInicio, horaFin, tiempoTrabajado, nroTacho) " + 
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
    String sqlProduccionDulce = "INSERT INTO produccion_dulce" + 
        "(idProduccion, phLecheSn, phLecheNeut, litrosSuero) " + 
        "VALUES (?, ?, ?, ?)";


    try {
        con = conexion.obtenerConexion();
        
        // Preparar el primer insert
        consulta = con.prepareStatement(sqlProduccion, Statement.RETURN_GENERATED_KEYS);
        consulta.setString(1, produccion.getCodInterno());
        consulta.setInt(2, produccion.getLechep().getId());
        consulta.setInt(3,produccion.getLitros());
        consulta.setInt(4, produccion.getProducto().getId());
        consulta.setFloat(5, produccion.getRendimiento());
        consulta.setInt(6, produccion.getKgLtsObt());
        consulta.setString(7, produccion.getFecha());
        consulta.setInt(8, produccion.getEncargado().getId());
        consulta.setString(9, produccion.getHoraInicio());
        consulta.setString(10, produccion.getHoraFin());
        consulta.setString(11, produccion.getTiempoTrabajado());
        consulta.setInt(12, produccion.getNroTacho());
        // Ejecutar el primer insert
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
        consulta = con.prepareStatement(sqlProduccionDulce);
        consulta.setInt(1, idProduccion);
        consulta.setFloat(2, produccion.getPhLechSn());
        consulta.setFloat(3, produccion.getPhLechNeut());
        consulta.setInt(4,produccion.getLitrosSuero());
        

        // Ejecutar el segundo insert
        consulta.executeUpdate();
        
        for(Empleado empleado : produccion.getListaEmpleados()){                
                persProduccion.agregarEmpleado(idProduccion, empleado.getId());                
            }
        
      
        
        
        for(LineaInsumo insumo : produccion.getListaInsumos()){                
                persProduccion.agregarInsumos(idProduccion, insumo.getInsumo().getId(), insumo.getCantidad());
            }

        for(LineaEnvase envase: produccion.getListaEnvases()){
            persProduccion.agregarEnvase(idProduccion,envase.getEnvase().getId(),envase.getCantidad());
            
        }
        return true;

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
        return false;

    } finally {
        try {
            if (con != null) con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
        }
    }
}

    
    public void agregarEmpleado(int idProd,int idEmpleado){
        String sqlAgregarEmpleados = "INSERT INTO produccion_empleados" +"(idProduccion,idEmpleado) VALUES (?,?)";
        
        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sqlAgregarEmpleados);
            consulta.setInt(1, idProd);
            consulta.setInt(2, idEmpleado);
            consulta.execute();
        }catch(SQLException e){            
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
        }finally{
            try{
                con.close();
            }catch(SQLException e){
               JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            }
        }
    }
    
    public boolean agregarInsumos(int idProd,int idInsumo,int cantidad){
        String sql = "INSERT INTO linea_insumos" +"(idProduccion,idInsumo,cantidad) VALUES (?,?,?)";
        
        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            consulta.setInt(1, idProd);
            consulta.setInt(2, idInsumo);
            consulta.setInt(3,cantidad);
            consulta.execute();
            return true;
        }catch(SQLException e){            
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            return false;
        }finally{
            try{
                con.close();
            }catch(SQLException e){
               JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            }
        }
    }
    
    public boolean agregarEnvases(int idProd,int idEnvase,int cantidad){
        String sql = "INSERT INTO linea_envases" +"(idProduccion,idEnvase,cantidad) VALUES (?,?,?)";
        
        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            consulta.setInt(1, idProd);
            consulta.setInt(2, idEnvase);
            consulta.setInt(3,cantidad);
            consulta.execute();
            return true;
        }catch(SQLException e){            
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            return false;
        }finally{
            try{
                con.close();
            }catch(SQLException e){
               JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            }
        }
    }
    
    public List listarProduccionesDulce() {
        List<ProduccionDulce> lista = new ArrayList<>();
        String sql = "SELECT p.*, pm.* FROM produccion p INNER JOIN produccion_dulce pm ON p.idProduccion = pm.idProduccion LEFT JOIN analisis a ON p.idProduccion = a.idProduccion WHERE p.activo = '1' AND pm.activo = '1' AND a.idProduccion IS NULL GROUP BY p.idProduccion;";
        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            resultado = consulta.executeQuery();
            while(resultado.next()){
                ProduccionDulce produccion = new ProduccionDulce();
                int id = resultado.getInt("idProduccion");
                produccion.setIdProduccion(id);
                produccion.setCodInterno(resultado.getString("codInterno"));
                LechePasteurizada lecheP = persLecheP.buscarPasteurizado(resultado.getInt("idLechePast"));
                
                if(lecheP instanceof LechePasteurizada){
                       produccion.setLechep(lecheP); 
                }
                
                Producto producto = persProducto.buscarProducto(resultado.getInt("idProducto"));
                if(producto instanceof Producto){
                       produccion.setProducto(producto); 
                }
                
                produccion.setRendimiento(resultado.getInt("rendimiento"));
                produccion.setKgLtsObt(resultado.getInt("kgLtsObt"));
                produccion.setFecha(resultado.getString("fecha"));              
                produccion.setLitros(resultado.getInt("litros"));
                Empleado encargado = persEmpleado.buscarEmpleado(resultado.getInt("encargadoId"));
                if(encargado instanceof Empleado){
                    produccion.setEncargado(encargado);
                } 
                produccion.setHoraInicio(resultado.getString("horaInicio"));
                produccion.setHoraFin(resultado.getString("horaFin"));
                produccion.setTiempoTrabajado(resultado.getString("tiempoTrabajado"));
                produccion.setNroTacho(resultado.getInt("NroTacho"));
                
                produccion.setPhLechSn(resultado.getFloat("pm.phLecheSn"));
                produccion.setPhLecheNeut(resultado.getFloat("pm.phLecheNeut"));
                produccion.setLitrosSuero(resultado.getInt("pm.litrosSuero"));
                
               
               
                  
                
                List<Empleado> empleados = persProduccion.listarEmpleadosXProduccion(id);                
                produccion.setListaEmpleados(empleados);
                
                List<LineaInsumo> insumos = persProduccion.listarInsumoXProduccion(id);
                produccion.setListaInsumos(insumos);
                
                List<LineaEnvase> envases= this.listarEnvaseXProduccion(id);
                produccion.setListaEnvases(envases);
                lista.add(produccion);
            } 
            return lista;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            return null;
        }finally{
            try{
                con.close();
            }catch(SQLException e){
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            }
    }}
    
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
    
    
    public ProduccionDulce buscarProduccionDulce(int id){
        String sql = "SELECT * FROM produccion p INNER JOIN produccion_dulce pm  On p.idProduccion=pm.idProduccion where p.activo='1' and pm.activo='1' and p.idProduccion=?";
                //"SELECT * FROM produccion WHERE idProduccion = ? AND activo = '1'";
        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            consulta.setInt(1, id);
            resultado = consulta.executeQuery();
            
            while(resultado.next()){
                ProduccionDulce produccion = new ProduccionDulce();
                produccion.setIdProduccion(id);
                produccion.setCodInterno(resultado.getString("codInterno"));
                LechePasteurizada lecheP = persLecheP.buscarPasteurizado(resultado.getInt("idLechePast"));
                
                if(lecheP instanceof LechePasteurizada){
                       produccion.setLechep(lecheP); 
                }
                
                Producto producto = persProducto.buscarProducto(resultado.getInt("idProducto"));
                if(producto instanceof Producto){
                       produccion.setProducto(producto); 
                }
                
                produccion.setRendimiento(resultado.getInt("rendimiento"));
                produccion.setKgLtsObt(resultado.getInt("kgLtsObt"));
                produccion.setFecha(resultado.getString("fecha"));
                produccion.setLitros(resultado.getInt("litros"));

                Empleado encargado = persEmpleado.buscarEmpleado(resultado.getInt("encargadoId"));
                if(encargado instanceof Empleado){
                    produccion.setEncargado(encargado);
                } 
                produccion.setHoraInicio(resultado.getString("horaInicio"));
                produccion.setHoraFin(resultado.getString("horaFin"));
                produccion.setTiempoTrabajado(resultado.getString("tiempoTrabajado"));
                produccion.setNroTacho(resultado.getInt("NroTacho"));
                
               
//                listarInfoEspecifica(produccion);     
                 produccion.setPhLechSn(resultado.getFloat("phLecheSn"));
                produccion.setPhLecheNeut(resultado.getFloat("phLecheNeut"));
                produccion.setLitrosSuero(resultado.getInt("litrosSuero"));
                List<Empleado> empleados = persProduccion.listarEmpleadosXProduccion(id);                
                produccion.setListaEmpleados(empleados);
                
                List<LineaInsumo> insumos = persProduccion.listarInsumoXProduccion(id);
                produccion.setListaInsumos(insumos);
                
                List<LineaEnvase> envase= this.listarEnvaseXProduccion(id);
                produccion.setListaEnvases(envase);
                return produccion;
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            return null;
        }finally{
            try{
                con.close();
            }catch(SQLException e){
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            }}
        return null;
    }
    
    public boolean modificarProduccionDulce(ProduccionDulce produccion){
        String sql = "UPDATE produccion SET codInterno = ?, idLechePast = ?, idProducto = ?, rendimiento = ?, kgLtsObt = ?, fecha = ?, encargadoId = ?, horaInicio = ? ,horaFin = ?, tiempoTrabajado = ?, nroTacho = ?, litros=? WHERE idProduccion = ?";
        String sqlProduccionDulce = "UPDATE produccion_dulce SET phLecheSn= ?, phLecheNeut= ?, litrosSuero= ? WHERE idProduccion = ?";
        try{
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
            consulta.setInt(12,produccion.getLitros());
            consulta.setInt(13, produccion.getIdProduccion());
             consulta.executeUpdate();
           
        
            consulta = con.prepareStatement(sqlProduccionDulce);
            consulta.setFloat(1, produccion.getPhLechSn());
            consulta.setFloat(2, produccion.getPhLechNeut());
            consulta.setInt(3, produccion.getLitrosSuero());
            
            consulta.setInt(4, produccion.getIdProduccion());
            consulta.executeUpdate();
            persProduccion.actualizarEmpleadosxProduccion(produccion.getIdProduccion(), produccion.getListaEmpleados());
            persProduccion.actualizarInsumosxProduccion(produccion.getIdProduccion(), produccion.getListaInsumos());
            return true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            return false;
        }finally{
            try{
                con.close();
            }catch(SQLException e){
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            }
        }
    }
    
    public List listarDulcePendienteAnalizar(){
        List<ProduccionDulce> lista = new ArrayList<>();
        String sql = "SELECT p.* FROM produccion p LEFT JOIN analisis a On p.idProduccion=a.idProduccion where p.activo='1' and a.activo='1' AND a.idProduccion IS NULL";
        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            resultado = consulta.executeQuery();
            while(resultado.next()){
                ProduccionDulce produccion = new ProduccionDulce();
                int id = resultado.getInt("idProduccion");
                produccion.setIdProduccion(id);
                produccion.setCodInterno(resultado.getString("codInterno"));
                LechePasteurizada lecheP = persLecheP.buscarPasteurizado(resultado.getInt("idLechePast"));
                
                if(lecheP instanceof LechePasteurizada){
                       produccion.setLechep(lecheP); 
                }
                
                Producto producto = persProducto.buscarProducto(resultado.getInt("idProducto"));
                if(producto instanceof Producto){
                       produccion.setProducto(producto); 
                }
                
                produccion.setRendimiento(resultado.getInt("rendimiento"));
                produccion.setKgLtsObt(resultado.getInt("kgLtsObt"));
                produccion.setFecha(resultado.getString("fecha"));              
                produccion.setLitros(resultado.getInt("litros"));
                Empleado encargado = persEmpleado.buscarEmpleado(resultado.getInt("encargadoId"));
                if(encargado instanceof Empleado){
                    produccion.setEncargado(encargado);
                } 
                produccion.setHoraInicio(resultado.getString("horaInicio"));
                produccion.setHoraFin(resultado.getString("horaFin"));
                produccion.setTiempoTrabajado(resultado.getString("tiempoTrabajado"));
                produccion.setNroTacho(resultado.getInt("NroTacho"));       
                
                List<Empleado> empleados = persProduccion.listarEmpleadosXProduccion(id);                
                produccion.setListaEmpleados(empleados);
                
                List<LineaInsumo> insumos = persProduccion.listarInsumoXProduccion(id);
                produccion.setListaInsumos(insumos);
                lista.add(produccion);
            } 
            return lista;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            return null;
        }finally{
            try{
                con.close();
            }catch(SQLException e){
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            }            
        }
    }
    
    PersistenciaEnvases persEnvase= new PersistenciaEnvases();
    
    public List listarEnvaseXProduccion(int idProduccion){
        List<LineaEnvase> lista = new ArrayList();
        String sql = "SELECT * FROM linea_envases WHERE idProduccion = ?";
        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            consulta.setInt(1, idProduccion);
            resultado = consulta.executeQuery();
            while(resultado.next()){
                EnvasesDulce envase = persEnvase.buscarEnvase(resultado.getInt("idEnvase"));
                if( envase instanceof EnvasesDulce){
                    int id = resultado.getInt("idLinea");
                    int cantidad = resultado.getInt("cantidad");
                    LineaEnvase linea = new LineaEnvase(id,envase,cantidad);
                    lista.add(linea);
                }
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            return null;
        }
        return lista;
    }
        
}
