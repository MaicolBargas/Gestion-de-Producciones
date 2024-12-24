
package fabrica.gestiondeproducciones.utilidades;

import javax.swing.JOptionPane;


public class Utilidades {
    
    public Integer validarNumericos(String valor, String nombre){
        if(validarVacios(valor)){
           try{
            Integer numero = Integer.valueOf(valor);
            return numero;
           }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "El campo "+nombre+" no es numerico, por favor verifique.");
           }
        }else{
        JOptionPane.showMessageDialog(null, "El campo "+nombre+" no puede ser vacio");
        }
        return null;
    }
    
    public final boolean validarVacios(String valor){
        return !valor.isEmpty();
    }
    
    public String ValidarVacioTexto(String valor,String nombre)
    {
       if(!valor.isEmpty()){
        return valor;
       }
       else
       {
        JOptionPane.showMessageDialog(null, "El campo "+nombre+" no puede ser vacio");
        return null;
        }
    }
    
}
