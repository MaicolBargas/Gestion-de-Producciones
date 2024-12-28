
package fabrica.gestiondeproducciones.utilidades;

import java.util.regex.Pattern;
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
    public  String sanitizarCampos(String valor, String nombre){
        if(validarVacios(valor)){
            return valor.replaceAll("[^\\w\\s]", "");                
        }else{
        JOptionPane.showMessageDialog(null, "El "+nombre+" no puede ser vacio");
        }
        return null;

    }
    
    
    
   public String validarCi(final String ci) {
    final int MINIMO_DIGITOS = 7;
    final String ciFixed;
    
    if (ci.length() <= MINIMO_DIGITOS) {
        // El algoritmo está hecho para 8 dígitos, se completa con 0 las cédulas de 7 dígitos o menos
        final String DEFAULT_DIGITOS_FORMAT = "%08d";
        ciFixed = String.format(DEFAULT_DIGITOS_FORMAT, Integer.parseInt(ci));
    } else {
        ciFixed = ci;
    }
    
    final int[] ciNumeric = toIntArray(ciFixed);
    final int digitoOriginal = ciNumeric[ciNumeric.length - 1];
    final int[] COEFICIENTES_ALGORITMO = {2, 9, 8, 7, 6, 3, 4};
    int suma = 0;
    
    for (int i = 0; i < 7; i++) {
        suma += ciNumeric[i] * COEFICIENTES_ALGORITMO[i] % 10;
    }
    
    final int digitoCalculado = (suma % 10 == 0) ? 0 : 10 - (suma % 10);
    
    
   if (digitoOriginal == digitoCalculado) {
        return ci;
    } else {
       JOptionPane.showMessageDialog(null, "La Cedula de identidad ingresada no es correcta.");
        return null;
    }
}

private int[] toIntArray(String text) {
    int[] array = new int[text.length()];
    for (int i = 0; i < array.length; i++) {
        array[i] = Character.getNumericValue(text.charAt(i));
    }
    return array;
}


}


