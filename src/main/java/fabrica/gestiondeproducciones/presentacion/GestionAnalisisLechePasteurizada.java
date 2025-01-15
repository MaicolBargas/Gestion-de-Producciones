
package fabrica.gestiondeproducciones.presentacion;

import fabrica.gestiondeproducciones.dominio.AnalisisLechePasteurizada;
import fabrica.gestiondeproducciones.dominio.Controlador;
import fabrica.gestiondeproducciones.dominio.Empleado;
import fabrica.gestiondeproducciones.dominio.LechePasteurizada;
import fabrica.gestiondeproducciones.utilidades.Utilidades;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class GestionAnalisisLechePasteurizada extends javax.swing.JInternalFrame {

    Utilidades utilidad = new Utilidades();
    AnalisisLechePasteurizada analisis = new AnalisisLechePasteurizada();
    Controlador controlador = new Controlador();
    Empleado encargado= new Empleado();
    LechePasteurizada lechePast= new LechePasteurizada();
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableModel modeloIngresos = new DefaultTableModel();
    String tipoAnalisis = "pasteurizada";
    
    public GestionAnalisisLechePasteurizada() {
        initComponents();
        listar();
    }

    
    
     private String descremadoTexto(boolean p){
        if(!p){
            return "No";
        }
        else
        {
            return "Si";
        }
    }
     
  public void limpiarFormulario(){
        txtId.setText("");
        txtLitros.setText("");      
        txtEncargado.setText("");
        txtFecha.setText("");
        txtLevadura.setText("");
        txtMos.setText("");
        txtPTotales.setText("");
        txtPFecales.setText("");
        txtGrasa.setText("");
        txtProteina.setText("");
        txtAgua.setText("");

        txtIdLechePast.setText("");
        txtTemp.setText("");
        txtLitros.setText("");
        txtDescremado.setText("");
        
    }
  
  private void limpiarTabla(){
        for(int i = 0; i < modelo.getRowCount(); i++){
            modelo.removeRow(i);
            i =- 1;
        }
    }
   
    
    private void limpiarTablaIngresos(){
        for(int i = 0; i < modeloIngresos.getRowCount(); i++){
            modeloIngresos.removeRow(i);
            i =- 1;
        }
    }
  private void listarLechePasteurizada(){     
        limpiarTablaIngresos();
        List<LechePasteurizada> lista = controlador.listarPasteurizadosPendientesAnalizar();
        modeloIngresos = (DefaultTableModel) tablaIngresos.getModel();
        Object[] objeto = new Object[6];
        for(int i = 0; i < lista.size(); i++){
            objeto[0] = lista.get(i).getId();
            objeto[1] = lista.get(i).getTemperatura();
            objeto[2] = lista.get(i).getLitros();
            objeto[3] =lista.get(i).getIngreso().getFecha()+"-Tambo:"+lista.get(i).getIngreso().getTambo().getPropietario();
            objeto[4] = descremadoTexto(lista.get(i).getDescremado());
            objeto[5] = lista.get(i).getCrema();
            modeloIngresos.addRow(objeto);
        }
        tablaIngresos.setModel(modeloIngresos);
    }
  
  private void cargarFecha(){
        LocalDate fechaHoy = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fecha = fechaHoy.format(format);
        txtFecha.setText(fecha);
    }
  private void listar(){
        limpiarTabla();
        listarLechePasteurizada();
        cargarFecha();
        List<AnalisisLechePasteurizada> lista = controlador.listarAnalisisLechePast();
        modelo = (DefaultTableModel) tablaAnalisis.getModel();
        Object[] objeto = new Object[12];
        for(int i = 0; i < lista.size(); i++){
            objeto[0] = lista.get(i).getId();
            objeto[1] = lista.get(i).getEncargado().getNombre();
            objeto[2] = lista.get(i).getFecha();
            objeto[3] = lista.get(i).getLevadura();
            objeto[4] = lista.get(i).getMos();
            objeto[5] = lista.get(i).getPoliformosTotales();
            objeto[6] = lista.get(i).getPoliformosFecales();
            objeto[7] = lista.get(i).getGrasa();
            objeto[8] = lista.get(i).getProteina();
            objeto[9] = lista.get(i).getAgua();
            objeto[10] = lista.get(i).getLechePast().getId();

            modelo.addRow(objeto);
        }
        tablaAnalisis.setModel(modelo);
    }
  
  private Empleado buscarEncargado() throws Exception{
        String valor = utilidad.sanitizarCampos(txtEncargado.getText(), "Encargado", false);        
        String[] nombreCompleto = valor.split(" ");        
        List<Empleado> empleados = controlador.listarEmpleados();
        
        for(Empleado empleado : empleados){
            try{
                Integer ci = Integer.valueOf(valor);
                if(ci.equals(empleado.getCi())){
                    return empleado;
                }
            }catch(NumberFormatException e){
                
            }                                    
            if(empleado.getNombre().equalsIgnoreCase(valor) ||
               empleado.getApellido().equalsIgnoreCase(valor) ||
               Arrays.asList(nombreCompleto).contains(empleado.getNombre())||
               Arrays.asList(nombreCompleto).contains(empleado.getApellido())
               ){
                return empleado;              
            }                         
        }
        throw new Exception("No existe un usuario con estos datos");
    }
    
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaIngresos = new javax.swing.JTable();
        txtIdLechePast = new javax.swing.JTextField();
        txtTemp = new javax.swing.JTextField();
        txtLitros = new javax.swing.JTextField();
        txtDescremado = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtCantCrema = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtLecheIng = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        btnLimpiar = new javax.swing.JButton();
        btnAlta = new javax.swing.JButton();
        btnBaja = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtEncargado = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtLevadura = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtMos = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtPTotales = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtPFecales = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtGrasa = new javax.swing.JTextField();
        txtProteina = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtAgua = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaAnalisis = new javax.swing.JTable();

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel9.setText("Id Pasteurizada:");

        jLabel10.setText("Temperatura:");

        jLabel11.setText("Litros:");

        jLabel12.setText("Descremado:");

        tablaIngresos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Temperatura", "Litros", "Leche Ingresada", "Descremado", "Crema Obt"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tablaIngresos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaIngresosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaIngresos);

        txtIdLechePast.setEditable(false);

        txtTemp.setEditable(false);
        txtTemp.setToolTipText("");

        txtLitros.setEditable(false);

        txtDescremado.setEditable(false);

        jLabel16.setText("Crema Obt:");

        txtCantCrema.setEditable(false);

        jLabel17.setText("Leche Ing:");

        txtLecheIng.setEditable(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(57, 57, 57)
                        .addComponent(txtLitros))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(23, 23, 23)
                        .addComponent(txtIdLechePast))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(28, 28, 28)
                        .addComponent(txtCantCrema))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(txtDescremado))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(57, 57, 57)
                        .addComponent(txtLecheIng)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtIdLechePast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtTemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtLitros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLecheIng, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtDescremado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtCantCrema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnAlta.setText("Alta");
        btnAlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAltaActionPerformed(evt);
            }
        });

        btnBaja.setText("Baja");
        btnBaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBajaActionPerformed(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(btnLimpiar)
                .addGap(18, 18, 18)
                .addComponent(btnAlta)
                .addGap(18, 18, 18)
                .addComponent(btnBaja)
                .addGap(18, 18, 18)
                .addComponent(btnModificar)
                .addGap(0, 13, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLimpiar)
                    .addComponent(btnAlta)
                    .addComponent(btnBaja)
                    .addComponent(btnModificar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setText("Id:");

        txtId.setEditable(false);

        jLabel3.setText("Encargado:");

        txtEncargado.setToolTipText("");

        jLabel4.setText("Fecha:");

        txtFecha.setToolTipText("");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel5.setText("Levadura:");

        txtLevadura.setToolTipText("");

        jLabel6.setText("Mos:");

        txtMos.setToolTipText("");

        jLabel7.setText("Poliformos totales:");

        jLabel8.setText("Poliformos fecales:");

        jLabel13.setText("Grasa:");

        jLabel14.setText("Proteina:");

        jLabel15.setText("Agua:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtFecha, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                            .addComponent(txtEncargado)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPTotales)
                            .addComponent(txtPFecales)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14))
                        .addGap(59, 59, 59)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtGrasa)
                            .addComponent(txtProteina)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtAgua, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtLevadura)
                            .addComponent(txtMos, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtEncargado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtLevadura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txtMos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txtPTotales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtPFecales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtGrasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtProteina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtAgua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(8747, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        tablaAnalisis.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Encargado", "Fecha", "Levadura", "Mos", "Poliformos Totales", "Poliformos fecales", "Grasa", "Proteina", "Agua", "Pasteurizada"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaAnalisis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaAnalisisMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaAnalisis);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1086, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaIngresosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaIngresosMouseClicked
        int fila = tablaIngresos.rowAtPoint(evt.getPoint());
        txtIdLechePast.setText(tablaIngresos.getValueAt(fila, 0).toString());
        txtTemp.setText(tablaIngresos.getValueAt(fila, 1).toString());
        txtLitros.setText(tablaIngresos.getValueAt(fila, 2).toString());
        txtLecheIng.setText(tablaIngresos.getValueAt(fila, 3).toString());
        txtDescremado.setText(tablaIngresos.getValueAt(fila, 4).toString());
        txtCantCrema.setText(tablaIngresos.getValueAt(fila, 5).toString());
    }//GEN-LAST:event_tablaIngresosMouseClicked

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarFormulario();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAltaActionPerformed
        try{
            String fecha = utilidad.controlarFechas(txtFecha.getText());
            int levadura = utilidad.validarNumericos(txtLevadura.getText(), "Levadura", false);
            int mos = utilidad.validarNumericos(txtMos.getText(), "Mos", false);
            int pTotales = utilidad.validarNumericos(txtPTotales.getText(), "Poliformos Totales", false);
            int pFecales = utilidad.validarNumericos(txtPFecales.getText(), "Poliformos Fecales", false);
            int grasa = utilidad.validarNumericos(txtGrasa.getText(), "Grasa", false);
            int proteina = utilidad.validarNumericos(txtProteina.getText(), "Proteina", false);
            int agua = utilidad.validarNumericos(txtAgua.getText(), "Agua", false);
            int id = utilidad.validarNumericos(txtIdLechePast.getText(), "leche pasteurizada", false);
            LechePasteurizada ingreso = controlador.buscarPasteurizado(id);

            if(pFecales > pTotales){
                throw new Exception("Los Poliformos Fecales no pueden ser mayores a los PoliformosTotales");
            }
            analisis.setTipo(tipoAnalisis);
            if(encargado instanceof Empleado){
                analisis.setEncargado(encargado);
            }else{throw new Exception("Debe seleccionar un empleado habilitado");}

            analisis.setFecha(fecha);
            analisis.setLevadura(levadura);
            analisis.setMos(mos);
            analisis.setPoliformosTotales(pTotales);
            analisis.setPoliformosFecales(pFecales);
            analisis.setGrasa(grasa);
            analisis.setProteina(proteina);
            analisis.setAgua(agua);
            

            if(lechePast instanceof LechePasteurizada){
                analisis.setLechePast(ingreso);
            }else{throw new Exception("La Pasteurizacion no existe en el sistema");}

            boolean alta = controlador.altaAnalisisLechePast(analisis);
            if(alta){
                JOptionPane.showMessageDialog(null, "Analisis dado de alta.");
                limpiarFormulario();
                listar();
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnAltaActionPerformed

    private void btnBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBajaActionPerformed
        try{
            int id = utilidad.validarNumericos(txtId.getText(), "Id", false);
            boolean baja = controlador.bajaAnalisis(id);
            if(baja){
                JOptionPane.showMessageDialog(null, "Analisis dado de baja.");
                limpiarFormulario();
                listar();
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnBajaActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        try{
            String fecha = utilidad.controlarFechas(txtFecha.getText());
            int levadura = utilidad.validarNumericos(txtLevadura.getText(), "Levadura", false);
            int mos = utilidad.validarNumericos(txtMos.getText(), "Mos", false);
            int pTotales = utilidad.validarNumericos(txtPTotales.getText(), "Poliformos Totales", false);
            int pFecales = utilidad.validarNumericos(txtPFecales.getText(), "Poliformos Fecales", false);
            int grasa = utilidad.validarNumericos(txtGrasa.getText(), "Grasa", false);
            int proteina = utilidad.validarNumericos(txtProteina.getText(), "Proteina", false);
            int agua = utilidad.validarNumericos(txtAgua.getText(), "Agua", false);
            int id = utilidad.validarNumericos(txtIdLechePast.getText(), "Ingreso", false);
            LechePasteurizada lechePast = controlador.buscarPasteurizado(id);

            if(pFecales > pTotales){
                throw new Exception("Los Poliformos Fecales no pueden ser mayores a los PoliformosTotales");
            }

            if(encargado instanceof Empleado){
                analisis.setEncargado(encargado);
            }else{throw new Exception("Debe seleccionar un empleado habilitado");}

            analisis.setFecha(fecha);
            analisis.setLevadura(levadura);
            analisis.setMos(mos);
            analisis.setPoliformosTotales(pTotales);
            analisis.setPoliformosFecales(pFecales);
            analisis.setGrasa(grasa);
            analisis.setProteina(proteina);
            analisis.setAgua(agua);

            if(lechePast instanceof LechePasteurizada){
                analisis.setLechePast(lechePast);
            }else{throw new Exception("El Pasteurizado no existe en el sistema");}

            boolean modificar = controlador.modificarAnalisisLechePast(analisis);
            if(modificar){
                JOptionPane.showMessageDialog(null, "Analisis modificado correctamente.");
                limpiarFormulario();
                listar();
            }
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        try {
            encargado = buscarEncargado();
            txtEncargado.setText(encargado.getId() + " - " + encargado.getNombre() +" "+ encargado.getApellido());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

  
    
    private void tablaAnalisisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAnalisisMouseClicked
        int fila = tablaAnalisis.rowAtPoint(evt.getPoint());
        txtId.setText(tablaAnalisis.getValueAt(fila, 0).toString());
        txtEncargado.setText(tablaAnalisis.getValueAt(fila,1).toString());
        txtFecha.setText(tablaAnalisis.getValueAt(fila,2).toString());
        txtLevadura.setText(tablaAnalisis.getValueAt(fila,3).toString());
        txtMos.setText(tablaAnalisis.getValueAt(fila,4).toString());
        txtPTotales.setText(tablaAnalisis.getValueAt(fila,5).toString());
        txtPFecales.setText(tablaAnalisis.getValueAt(fila,6).toString());
        txtGrasa.setText(tablaAnalisis.getValueAt(fila,7).toString());
        txtProteina.setText(tablaAnalisis.getValueAt(fila,8).toString());
        txtAgua.setText(tablaAnalisis.getValueAt(fila,9).toString());
        int id = Integer.parseInt(tablaAnalisis.getValueAt(fila, 10).toString());
        LechePasteurizada ingreso = controlador.buscarPasteurizado(id);
        if(ingreso instanceof LechePasteurizada){
            txtIdLechePast.setText(tablaAnalisis.getValueAt(fila, 11).toString());
            txtTemp.setText(""+ingreso.getTemperatura());
            txtLitros.setText(ingreso.getLitros()+"");
            txtDescremado.setText(descremadoTexto(ingreso.getDescremado()));
            txtCantCrema.setText(ingreso.getCrema()+"");
        }

    }//GEN-LAST:event_tablaAnalisisMouseClicked

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlta;
    private javax.swing.JButton btnBaja;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaAnalisis;
    private javax.swing.JTable tablaIngresos;
    private javax.swing.JTextField txtAgua;
    private javax.swing.JTextField txtCantCrema;
    private javax.swing.JTextField txtDescremado;
    private javax.swing.JTextField txtEncargado;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtGrasa;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIdLechePast;
    private javax.swing.JTextField txtLecheIng;
    private javax.swing.JTextField txtLevadura;
    private javax.swing.JTextField txtLitros;
    private javax.swing.JTextField txtMos;
    private javax.swing.JTextField txtPFecales;
    private javax.swing.JTextField txtPTotales;
    private javax.swing.JTextField txtProteina;
    private javax.swing.JTextField txtTemp;
    // End of variables declaration//GEN-END:variables
}
