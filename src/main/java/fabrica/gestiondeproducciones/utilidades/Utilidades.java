package fabrica.gestiondeproducciones.utilidades;

import fabrica.gestiondeproducciones.dominio.Controlador;
import fabrica.gestiondeproducciones.dominio.LechePasteurizada;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilidades {

    public Integer validarNumericos(String valor, String nombre, Boolean permiteVacios) throws Exception {
        try {
            if (!valor.isEmpty() || permiteVacios) {
                try {
                    Integer numero = Integer.valueOf(valor);

                    if (numero <= 0) {
                        throw new Exception("El campo " + nombre + " debe ser mayor a 0.");
                    }

                    return numero;
                } catch (NumberFormatException e) {
                    throw new Exception("El campo " + nombre + " no es numerico, por favor verifique.");
                }
            } else {
                throw new Exception("El campo " + nombre + " no puede ser vacio");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Float validarNumericosFloat(String valor, String nombre, Boolean permiteVacios) throws Exception {
        try {
            if (!valor.isEmpty() || permiteVacios) {
                try {
                    Float numero = Float.valueOf(valor);

                    if (numero <= 0) {
                        throw new Exception("El campo " + nombre + " debe ser mayor a 0.");
                    }

                    return numero;
                } catch (NumberFormatException e) {
                    throw new Exception("El campo " + nombre + " no es numérico, por favor verifique.");
                }
            } else {
                throw new Exception("El campo " + nombre + " no puede estar vacío.");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public String sanitizarCampos(String valor, String nombre, Boolean permiteVacios) throws Exception {
        try {
            if (!valor.isEmpty() || permiteVacios) {
                return valor.replaceAll("[^\\w\\s]", "");
            } else {
                throw new Exception("El campo " + nombre + " no puede ser vacio");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public String controlarFechas(String fecha) throws Exception {
        String regex = "^\\d{2}/\\d{2}/\\d{4}$";

        try {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(fecha);

            if (matcher.matches()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                try {
                    LocalDate fechaValida = LocalDate.parse(fecha, formatter);
                    return fecha;
                } catch (DateTimeParseException e) {
                    throw new Exception("La fecha no es válida en el calendario.");
                }
            } else {
                throw new Exception("La fecha tiene un formato incorrecto. Formato esperado dd/mm/yyyy.");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public String validarVacios(String valor, String nombre) throws Exception {
        try {
            if (!valor.isEmpty()) {
                return valor;
            } else {
                throw new Exception("El campo " + nombre + " no puede ser vacio");
            }
        } catch (Exception e) {
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

    public String validarHora(String texto, String cualEs) throws Exception {
        try {

            String patron = "^([0-1]?[0-9]|2[0-3]):([0-5]?[0-9])$";

            Pattern pattern = Pattern.compile(patron);
            Matcher matcher = pattern.matcher(texto);

            if (matcher.matches()) {
                // Extraer las horas y minutos desde el texto
                String[] partes = texto.split(":");
                int horas = Integer.parseInt(partes[0]);
                int minutos = Integer.parseInt(partes[1]);

                if (horas >= 0 && horas <= 23 && minutos >= 0 && minutos <= 59) {
                    return texto;
                }
            }

            throw new Exception("La hora tiene un formato incorrecto en : " + cualEs + " . Formato esperado HH:MM");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public String validarHoraNoMayor(String hora1, String hora2, String cualEs, String cualEs1, String cualEs2) throws Exception {
        try {
            // Dividir las horas y minutos
            String[] partes1 = hora1.split(":");
            String[] partes2 = hora2.split(":");

            int horas1 = Integer.parseInt(partes1[0]);
            int minutos1 = Integer.parseInt(partes1[1]);

            int horas2 = Integer.parseInt(partes2[0]);
            int minutos2 = Integer.parseInt(partes2[1]);

            // Comparar las horas y minutos
            if (horas1 > horas2 || (horas1 == horas2 && minutos1 > minutos2)) {
                throw new Exception("La hora de " + cualEs1 + " no puede ser mayor que la hora de " + cualEs2 + " entre: " + cualEs);
            }

            return hora1 + " <= " + hora2;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public String calcularDiferenciaHoras(String hora1, String hora2) throws Exception {
        try {
            // Dividir las horas y minutos
            String[] partes1 = hora1.split(":");
            String[] partes2 = hora2.split(":");

            int horas1 = Integer.parseInt(partes1[0]);
            int minutos1 = Integer.parseInt(partes1[1]);

            int horas2 = Integer.parseInt(partes2[0]);
            int minutos2 = Integer.parseInt(partes2[1]);

            // Convertir ambas horas a minutos totales
            int totalMinutos1 = horas1 * 60 + minutos1;
            int totalMinutos2 = horas2 * 60 + minutos2;

            // Calcular la diferencia en minutos
            int diferenciaMinutos = Math.abs(totalMinutos1 - totalMinutos2);

            // Convertir la diferencia en horas y minutos
            int horasDiferencia = diferenciaMinutos / 60;
            int minutosDiferencia = diferenciaMinutos % 60;

            return String.format("%02d:%02d", horasDiferencia, minutosDiferencia);
        } catch (NumberFormatException e) {
            throw new Exception("Error al calcular la diferencia: " + e.getMessage());
        }
    }

    public int validarCantidadCrema(int litros, int id) throws Exception {
        try {

            Controlador c = new Controlador();
            LechePasteurizada l = new LechePasteurizada();
            l = c.buscarPasteurizado(id);
            int i = l.getCremaDisponible();

            // Comparar las horas y minutos
            if (litros > i) {
                throw new Exception("Los Litros de Crema Ingresados No pueden se mayor a los disponibles En el Pasteurizado");
            }

            return litros;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    Controlador c = new Controlador();

    public void actualizarLitros(LechePasteurizada lecheP, int litrosUtilizados) {
        int litrosDisponibles = lecheP.getCremaDisponible();
        lecheP.setCremaDisponible(litrosDisponibles - (litrosUtilizados));
        c.modificarPasteurizado(lecheP);
    }

    public float validarTemperatura(String temperatura) throws Exception {
        if (Float.parseFloat(temperatura) < 120) {
            return Float.parseFloat(temperatura);

        } else {
            throw new Exception("Ningun Proceso Alcanza valores de temperatura tan altos, Verificar Temperaturas Ingresadas");
        }
    }

    public float validarPh(String ph) throws Exception {

        if (Float.parseFloat(ph) > 0 && Float.parseFloat(ph) < 14) {
            return Float.parseFloat(ph);
        }
        throw new Exception("Los Valores de ph deben de estar contenidos entre 0.0 y 14.0");
    }

}
