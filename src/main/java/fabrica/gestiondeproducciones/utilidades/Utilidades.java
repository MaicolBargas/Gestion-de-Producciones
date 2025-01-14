
package fabrica.gestiondeproducciones.utilidades;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilidades {
    

    public Integer validarNumericos(String valor, String nombre, Boolean permiteVacios) throws Exception{
        try{
            if(!valor.isEmpty() || permiteVacios){
               try{
                Integer numero = Integer.valueOf(valor);
                return numero;
               }catch(NumberFormatException e){
                throw new Exception("El campo "+nombre+" no es numerico, por favor verifique.");
               }
            }else{
                throw new Exception("El campo "+nombre+" no puede ser vacio");
            }
        }catch(Exception e){ 
           throw new Exception(e.getMessage());
        }
    }
    
    
    public String sanitizarCampos(String valor, String nombre, Boolean permiteVacios) throws Exception{
        try{
            if(!valor.isEmpty() || permiteVacios){
                return valor.replaceAll("[^\\w\\s]", "");                
            }else{
                throw new Exception("El campo "+nombre+" no puede ser vacio");
            }
        }catch(Exception e){ 
            throw new Exception(e.getMessage());               
        }
    }
    
    public String controlarFechas(String fecha) throws Exception{
        String regex = "^\\d{2}/\\d{2}/\\d{4}$";
        try{
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(fecha);

        if (matcher.matches()) {
            return fecha;
        } else {
            throw new Exception("La fecha tiene un formato incorecto. Formato esperado dd/mm/aaaa");
        }
        }catch(Exception e){
            throw new Exception(e.getMessage());               
        }
    }
    
    public String validarVacios(String valor, String nombre) throws Exception{
        try{
            if(!valor.isEmpty()){
                return valor;
            }else{
                throw new Exception("El campo "+nombre+" no puede ser vacio");
            }
        }catch(Exception e){ 
            throw new Exception(e.getMessage());               
        }
    }
    
    public Integer validarCi(final String ci) throws Exception {
        final int MINIMO_DIGITOS = 7;
        final String ciFixed;

        if (ci.length() <= MINIMO_DIGITOS) {
            // El algoritmo está hecho para 8 dígitos, se completa con 0 las cédulas de 7 dígitos o menos
            final String DEFAULT_DIGITOS_FORMAT = "%08d";
            ciFixed = String.format(DEFAULT_DIGITOS_FORMAT, Integer.valueOf(ci));
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
            return Integer.valueOf(ci);
        } else {
           throw new Exception("La Cedula de identidad ingresada no es correcta.");
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


