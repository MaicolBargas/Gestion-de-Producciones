
package fabrica.gestiondeproducciones.utilidades;


public class Utilidades {
    
    public Integer validarNumericos(String valor, String nombre){
        if(validarVacios(valor)){
           try{
            Integer numero = Integer.valueOf(valor);
            return numero;
           }catch(NumberFormatException e){
               
           }
        }
        return null;
    }
    
    public final boolean validarVacios(String valor){
        return !valor.isEmpty();
    }
    
}
