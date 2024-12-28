
package fabrica.gestiondeproducciones.persistencia;

import fabrica.gestiondeproducciones.dominio.Producto;
import fabrica.gestiondeproducciones.utilidades.Excepciones;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class PersistenciaProducto {
    Conexion conexion = new Conexion();
    Connection con;
    PreparedStatement consulta;
    ResultSet resultado;
    String nombreTabla = "producto";
    
    public boolean altaProducto(Producto producto){
        String sql = "INSERT INTO "+ nombreTabla +"(nombre, descripcion) VALUES (?,?)";
        
        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            consulta.setString(1,producto.getNombre());
            consulta.setString(2,producto.getDescripcion());
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
    
    public List listarProductos(){
        List<Producto> lista = new ArrayList();
        String sql = "SELECT * FROM "+ nombreTabla +" WHERE activo = '1'";
        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            resultado = consulta.executeQuery();
            while(resultado.next()){
                Producto producto = new Producto();
                producto.setId(resultado.getInt("id"));
                producto.setNombre(resultado.getString("nombre"));
                producto.setDescripcion(resultado.getString("descripcion"));
                lista.add(producto);
            }
        }catch(SQLException e){
            System.out.println(e.toString());
            return null;
        }
        return lista;
    }
    
    
    public boolean bajaProducto(int id){
        String sql = "UPDATE "+ nombreTabla +" SET activo = 0 WHERE id = ?";
       
        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            consulta.setInt(1, id);
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
    
     public boolean modificarProducto(Producto producto){
        String sql = "UPDATE "+ nombreTabla +" SET nombre = ?, descripcion = ? WHERE id = ?";
        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            consulta.setString(1, producto.getNombre());
            consulta.setString(2, producto.getDescripcion());
            consulta.setInt(3, producto.getId());
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
     
     public Producto buscarProducto(int id){
        String sql = "SELECT * FROM "+ nombreTabla +" WHERE id =?";
        try{
            con = conexion.obtenerConexion();
            consulta = con.prepareStatement(sql);
            consulta.setInt(1, id);
            resultado = consulta.executeQuery();     
            if(resultado.next()){
                Producto producto = new Producto();
                producto.setId(resultado.getInt("id"));
                producto.setNombre(resultado.getString("nombre"));
                producto.setDescripcion(resultado.getString("descripcion"));
                return producto;
            }
        }catch(SQLException e){
            System.out.println(e.toString());
            return null;
        }finally{
            try{
                con.close();
            }catch(SQLException e){
            JOptionPane.showMessageDialog(null, Excepciones.controlaExepciones(e));
            }
        }
        return null;
    } 
    
    
}
