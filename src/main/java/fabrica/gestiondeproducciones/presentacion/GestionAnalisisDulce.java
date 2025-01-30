
package fabrica.gestiondeproducciones.presentacion;

import fabrica.gestiondeproducciones.dominio.AnalisisDulce;
import fabrica.gestiondeproducciones.dominio.Controlador;
import fabrica.gestiondeproducciones.dominio.Empleado;
import fabrica.gestiondeproducciones.dominio.ProduccionDulce;
import fabrica.gestiondeproducciones.utilidades.Utilidades;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class GestionAnalisisDulce extends javax.swing.JInternalFrame {
    
    Utilidades utilidad = new Utilidades();
    AnalisisDulce analisis = new AnalisisDulce();
    Controlador controlador = new Controlador();
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableModel modeloProducciones = new DefaultTableModel();
    String tipoAnalisis = "dulce";
    Empleado encargado = new Empleado();
    
    /**
     * Creates new form GestionAnalisisDulce
     */
    public GestionAnalisisDulce() {
        initComponents();
        listar();
    }

    // <editor-fold defaultstate="collapsed" desc="Funciones auxiliares">  
    
        private void listar(){
        limpiarTabla();
        listarProducciones();
        cargarFecha();
        List<AnalisisDulce> lista = controlador.listarAnalisisDulce();
        modelo = (DefaultTableModel) tablaAnalisis.getModel();
        Object[] objeto = new Object[11];
        for(int i = 0; i < lista.size(); i++){
            objeto[0] = lista.get(i).getId();
            if(lista.get(i).getEncargado() instanceof Empleado){
                objeto[1] = lista.get(i).getEncargado().getInfoCompleta();
            }
            objeto[2] = lista.get(i).getFecha();
            objeto[3] = lista.get(i).getLevadura();
            objeto[4] = lista.get(i).getMos();
            objeto[5] = lista.get(i).getPoliformosTotales();
            objeto[6] = lista.get(i).getPoliformosFecales();
            objeto[7] = lista.get(i).getGrasa();
            objeto[8] = lista.get(i).getHumedad();
            objeto[9] = lista.get(i).getProduccion().getIdProduccion();

            modelo.addRow(objeto);
        }
        tablaAnalisis.setModel(modelo);
    }
        
    private void listarProducciones(){     
        limpiarTablaProducciones();
        List<ProduccionDulce> lista = controlador.listarDulcePendienteAnalizar();
        modeloProducciones = (DefaultTableModel) tablaProducciones.getModel();
        Object[] objeto = new Object[4];
        for(int i = 0; i < lista.size(); i++){
            objeto[0] = lista.get(i).getIdProduccion();
            objeto[1] = lista.get(i).getCodInterno();
            objeto[2] = lista.get(i).getFecha();
            modeloProducciones.addRow(objeto);
        }
        tablaProducciones.setModel(modeloProducciones);
    }
    
    private void limpiarTabla(){
        for(int i = 0; i < modelo.getRowCount(); i++){
            modelo.removeRow(i);
            i =- 1;
        }
    }
    
    private void limpiarTablaProducciones(){
        for(int i = 0; i < modeloProducciones.getRowCount(); i++){
            modeloProducciones.removeRow(i);
            i =- 1;
        }
    }
    
    private void cargarFecha(){
        LocalDate fechaHoy = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fecha = fechaHoy.format(format);
        txtFecha.setText(fecha);
    }
        
    public void limpiarFormulario(){
        txtId.setText("");
        txtEncargado.setText("");
        txtFecha.setText("");
        txtLevadura.setText("");
        txtMos.setText("");
        txtPTotales.setText("");
        txtPFecales.setText("");
        txtGrasa.setText("");
        txtHumedad.setText("");

        txtIdProduccion.setText("");
        txtFechaProduccion.setText("");
        txtCodigoInterno.setText("");        
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
    // </editor-fold>
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtEncargado = new javax.swing.JTextField();
        txtFecha = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        txtLevadura = new javax.swing.JTextField();
        txtMos = new javax.swing.JTextField();
        txtPTotales = new javax.swing.JTextField();
        txtPFecales = new javax.swing.JTextField();
        txtGrasa = new javax.swing.JTextField();
        txtHumedad = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaProducciones = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtIdProduccion = new javax.swing.JTextField();
        txtCodigoInterno = new javax.swing.JTextField();
        txtFechaProduccion = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        btnAlta = new javax.swing.JButton();
        btnBaja = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaAnalisis = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Gestion de Analisis de Dulce");

        jLabel1.setText("Id:");

        jLabel2.setText("Encargado:");

        jLabel3.setText("Fecha:");

        jLabel4.setText("Levadura:");

        jLabel5.setText("Mos:");

        jLabel6.setText("Poliformos totales:");

        jLabel7.setText("Poliformos fecales:");

        jLabel8.setText("Grasa:");

        jLabel9.setText("Humedad:");

        txtId.setEditable(false);

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtEncargado, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscar))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel9))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtGrasa, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                                            .addComponent(txtHumedad)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtPFecales, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel3))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtFecha)
                                            .addComponent(txtLevadura)
                                            .addComponent(txtMos)
                                            .addComponent(txtPTotales))))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtEncargado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtLevadura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtMos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtPTotales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtPFecales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtGrasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtHumedad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(104, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tablaProducciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Codigo Interno", "Fecha"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaProducciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProduccionesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaProducciones);

        jLabel11.setText("Seleccione la produccion de dulce analizada");

        jLabel12.setText("Id Produccion:");

        jLabel13.setText("Codigo Interno:");

        jLabel14.setText("Fecha:");

        txtIdProduccion.setEditable(false);

        txtCodigoInterno.setEditable(false);

        txtFechaProduccion.setEditable(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(25, 25, 25)
                        .addComponent(txtIdProduccion, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCodigoInterno, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                            .addComponent(txtFechaProduccion))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtIdProduccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtCodigoInterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtFechaProduccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addGap(263, 263, 263))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

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

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnLimpiar)
                .addGap(18, 18, 18)
                .addComponent(btnAlta)
                .addGap(18, 18, 18)
                .addComponent(btnBaja)
                .addGap(18, 18, 18)
                .addComponent(btnModificar)
                .addGap(0, 9, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAlta)
                    .addComponent(btnBaja)
                    .addComponent(btnModificar)
                    .addComponent(btnLimpiar))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        tablaAnalisis.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Encargado", "Fecha", "Levadura", "Mos", "Poliformos totales", "Poliformos fecales", "Grasa", "Humedad", "Produccion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
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
        jScrollPane1.setViewportView(tablaAnalisis);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 13, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            int humedad = utilidad.validarNumericos(txtHumedad.getText(), "Humedad", false);
            int idProduccion = utilidad.validarNumericos(txtIdProduccion.getText(), "Produccion", false);
            ProduccionDulce produccion = controlador.buscarProduccionDulce(idProduccion); 
            
            try{
                String[] partes = utilidad.validarVacios(txtEncargado.getText(), "Encargado").split(" - ");
                Empleado empleado = controlador.buscarEmpleado(Integer.parseInt(partes[0]));
                if(empleado instanceof Empleado){
                    analisis.setEncargado(empleado);
                }else{
                    throw new Exception("Debe seleccionar un empleado habilitado");
                }
            }catch(Exception e){
                throw new Exception("Debe buscar un usuario valido primero, por favor verifique");
            }
            
            if(pFecales > pTotales){
                throw new Exception("Los Poliformos Fecales no pueden ser mayores a los PoliformosTotales");
            }
            
           analisis.setTipo(tipoAnalisis);
           analisis.setFecha(fecha);
           analisis.setLevadura(levadura);
           analisis.setMos(mos);
           analisis.setPoliformosTotales(pTotales);
           analisis.setPoliformosFecales(pFecales);
           analisis.setGrasa(grasa);
           analisis.setHumedad(humedad);
           
           if(produccion instanceof ProduccionDulce){
            analisis.setProduccion(produccion);            
           }else{throw new Exception("La produccion no existe en el sistema");}
          

            boolean alta = controlador.altaAnalisisDulce(analisis);
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
            int id = utilidad.validarNumericos(txtId.getText(), "Id", false);
            String fecha = utilidad.controlarFechas(txtFecha.getText());
            int levadura = utilidad.validarNumericos(txtLevadura.getText(), "Levadura", false);
            int mos = utilidad.validarNumericos(txtMos.getText(), "Mos", false);
            int pTotales = utilidad.validarNumericos(txtPTotales.getText(), "Poliformos Totales", false);
            int pFecales = utilidad.validarNumericos(txtPFecales.getText(), "Poliformos Fecales", false);
            int grasa = utilidad.validarNumericos(txtGrasa.getText(), "Grasa", false);
            int humedad = utilidad.validarNumericos(txtHumedad.getText(), "Humedad", false);
            int idProduccion = utilidad.validarNumericos(txtIdProduccion.getText(), "Produccion", false);
            ProduccionDulce produccion = controlador.buscarProduccionDulce(idProduccion); 
            
            analisis = controlador.buscarAnalisisDulce(id);
            
            try{
                String[] partes = utilidad.validarVacios(txtEncargado.getText(), "Encargado").split(" - ");
                Empleado empleado = controlador.buscarEmpleado(Integer.parseInt(partes[0]));
                if(empleado instanceof Empleado){
                    analisis.setEncargado(empleado);
                }else{
                    throw new Exception("Debe seleccionar un empleado habilitado");
                }
            }catch(Exception e){
                throw new Exception("Debe buscar un usuario valido primero, por favor verifique");
            }
            
            if(pFecales > pTotales){
                throw new Exception("Los Poliformos Fecales no pueden ser mayores a los PoliformosTotales");
            }           
            
           analisis.setFecha(fecha);
           analisis.setLevadura(levadura);
           analisis.setMos(mos);
           analisis.setPoliformosTotales(pTotales);
           analisis.setPoliformosFecales(pFecales);
           analisis.setGrasa(grasa);
           analisis.setHumedad(humedad);
           
           if(produccion instanceof ProduccionDulce){
            analisis.setProduccion(produccion);            
           }else{throw new Exception("La produccion no existe en el sistema");}

            boolean modificar = controlador.modificarAnalisisDulce(analisis);
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
            txtEncargado.setText(encargado.getInfoCompleta());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void tablaProduccionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProduccionesMouseClicked
        int fila = tablaProducciones.rowAtPoint(evt.getPoint());
        txtIdProduccion.setText(tablaProducciones.getValueAt(fila, 0).toString());
        txtCodigoInterno.setText(tablaProducciones.getValueAt(fila, 1).toString());
        txtFechaProduccion.setText(tablaProducciones.getValueAt(fila, 2).toString());
    }//GEN-LAST:event_tablaProduccionesMouseClicked

    private void tablaAnalisisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAnalisisMouseClicked
        int fila = tablaAnalisis.rowAtPoint(evt.getPoint());       
        txtId.setText(tablaAnalisis.getValueAt(fila, 0).toString());
        txtEncargado.setText(tablaAnalisis.getValueAt(fila, 1).toString());  
        txtFecha.setText(tablaAnalisis.getValueAt(fila,2).toString());
        txtLevadura.setText(tablaAnalisis.getValueAt(fila, 3).toString());
        txtMos.setText(tablaAnalisis.getValueAt(fila,4).toString());
        txtPTotales.setText(tablaAnalisis.getValueAt(fila, 5).toString());
        txtPFecales.setText(tablaAnalisis.getValueAt(fila,6).toString());
        txtGrasa.setText(tablaAnalisis.getValueAt(fila, 7).toString());
        txtHumedad.setText(tablaAnalisis.getValueAt(fila, 8).toString());
        int id = Integer.parseInt(tablaAnalisis.getValueAt(fila, 9).toString());
        ProduccionDulce produccion = controlador.buscarProduccionDulce(id);
        if(produccion instanceof ProduccionDulce){
            txtIdProduccion.setText(tablaAnalisis.getValueAt(fila, 9).toString());
            txtCodigoInterno.setText(produccion.getCodInterno());
            txtFechaProduccion.setText(produccion.getFecha());
        }        
        btnBuscar.doClick();    
    }//GEN-LAST:event_tablaAnalisisMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlta;
    private javax.swing.JButton btnBaja;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaAnalisis;
    private javax.swing.JTable tablaProducciones;
    private javax.swing.JTextField txtCodigoInterno;
    private javax.swing.JTextField txtEncargado;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtFechaProduccion;
    private javax.swing.JTextField txtGrasa;
    private javax.swing.JTextField txtHumedad;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIdProduccion;
    private javax.swing.JTextField txtLevadura;
    private javax.swing.JTextField txtMos;
    private javax.swing.JTextField txtPFecales;
    private javax.swing.JTextField txtPTotales;
    // End of variables declaration//GEN-END:variables
}
