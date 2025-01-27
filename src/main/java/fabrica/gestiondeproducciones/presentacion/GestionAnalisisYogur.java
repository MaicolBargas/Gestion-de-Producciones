
package fabrica.gestiondeproducciones.presentacion;

import fabrica.gestiondeproducciones.dominio.AnalisisYogur;
import fabrica.gestiondeproducciones.dominio.Controlador;
import fabrica.gestiondeproducciones.dominio.Empleado;
import fabrica.gestiondeproducciones.dominio.ProduccionYogur;
import fabrica.gestiondeproducciones.utilidades.Utilidades;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class GestionAnalisisYogur extends javax.swing.JInternalFrame {

    Utilidades utilidad = new Utilidades();
    AnalisisYogur analisis = new AnalisisYogur();
    Controlador controlador = new Controlador();
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableModel modeloProducciones = new DefaultTableModel();
    String tipoAnalisis = "yogur";
    Empleado encargado = new Empleado();
    
    /**
     * Creates new form GestionAnalisisYogur
     */
    public GestionAnalisisYogur() {
        initComponents();
        listar();
    }
    
    // <editor-fold defaultstate="collapsed" desc="Funciones auxiliares">  
        private void listar(){
        limpiarTabla();
        listarProducciones();
        cargarFecha();
        List<AnalisisYogur> lista = controlador.listarAnalisisYogur();
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
            objeto[7] = lista.get(i).getPh();
            objeto[8] = lista.get(i).getAcidez();
            objeto[9] = lista.get(i).getProduccion().getIdProduccion();

            modelo.addRow(objeto);
        }
        tablaAnalisis.setModel(modelo);
    }
        
    private void listarProducciones(){     
        limpiarTablaProducciones();
        List<ProduccionYogur> lista = controlador.listarYogurPendienteAnalizar();
        modeloProducciones = (DefaultTableModel) tablaProducciones.getModel();
        Object[] objeto = new Object[4];
        for(int i = 0; i < lista.size(); i++){
            objeto[0] = lista.get(i).getIdProduccion();
            objeto[1] = lista.get(i).getCodInterno();
            objeto[2] = lista.get(i).getFecha();
            objeto[3] = lista.get(i).getUnidadesObtenidas();
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
        txtAcidez.setText("");
        txtPh.setText("");

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

        jToggleButton1 = new javax.swing.JToggleButton();
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
        btnBuscar = new javax.swing.JButton();
        txtFecha = new javax.swing.JTextField();
        txtLevadura = new javax.swing.JTextField();
        txtMos = new javax.swing.JTextField();
        txtPTotales = new javax.swing.JTextField();
        txtPFecales = new javax.swing.JTextField();
        txtAcidez = new javax.swing.JTextField();
        txtPh = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaProducciones = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtIdProduccion = new javax.swing.JTextField();
        txtCodigoInterno = new javax.swing.JTextField();
        txtFechaProduccion = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnLimpiar = new javax.swing.JButton();
        btnAlta = new javax.swing.JButton();
        btnBaja = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaAnalisis = new javax.swing.JTable();

        jToggleButton1.setText("jToggleButton1");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Gestion Analisis de yogur");

        jLabel1.setText("Id:");

        jLabel2.setText("Encargado:");

        jLabel3.setText("Fecha:");

        jLabel4.setText("Levadura:");

        jLabel5.setText("Mos:");

        jLabel6.setText("Poliformos totales:");

        jLabel7.setText("Poliformos fecales:");

        jLabel8.setText("Acidez");

        jLabel9.setText("P.H.:");

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
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(108, 108, 108)
                        .addComponent(txtLevadura, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtMos, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(125, 125, 125)
                        .addComponent(txtFecha))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(157, 157, 157)
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtEncargado, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel8)
                                        .addComponent(jLabel9))
                                    .addGap(59, 59, 59)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtPh, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtAcidez, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtPFecales, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtPTotales, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(btnBuscar)
                .addContainerGap())
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
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtAcidez, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtPh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tablaProducciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Codigo Interno", "Fecha", "Cantidad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
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

        jLabel11.setText("Id Produccion:");

        jLabel12.setText("Codigo Interno:");

        jLabel13.setText("Fecha:");

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
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(txtIdProduccion, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCodigoInterno, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                            .addComponent(txtFechaProduccion))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtIdProduccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtCodigoInterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtFechaProduccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel10.setText("Seleccione la produccion de yogur analizada");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(213, 213, 213))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
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
                .addContainerGap()
                .addComponent(btnLimpiar)
                .addGap(18, 18, 18)
                .addComponent(btnAlta)
                .addGap(18, 18, 18)
                .addComponent(btnBaja)
                .addGap(18, 18, 18)
                .addComponent(btnModificar)
                .addContainerGap(39, Short.MAX_VALUE))
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
                .addContainerGap(12, Short.MAX_VALUE))
        );

        tablaAnalisis.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Encargado", "Fecha", "Levadura", "Mos", "Poliformos totales", "Poliformos fecales", "PH", "Acidez", "Produccion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1265, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
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
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // <editor-fold defaultstate="collapsed" desc="ABM">  
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
            int ph = utilidad.validarNumericos(txtPh.getText(), "PH", false);
            int acidez = utilidad.validarNumericos(txtAcidez.getText(), "Acidez", false);
            int idProduccion = utilidad.validarNumericos(txtIdProduccion.getText(), "Produccion", false);
            ProduccionYogur produccion = controlador.buscarProduccionYogur(idProduccion); 
            
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
           analisis.setPh(ph);
           analisis.setAcidez(acidez);
           
           if(produccion instanceof ProduccionYogur){
            analisis.setProduccion(produccion);            
           }else{throw new Exception("La produccion no existe en el sistema");}
          

            boolean alta = controlador.altaAnalisisYogur(analisis);
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
            int ph = utilidad.validarNumericos(txtPh.getText(), "PH", false);
            int acidez = utilidad.validarNumericos(txtAcidez.getText(), "Acidez", false);
            int idProduccion = utilidad.validarNumericos(txtIdProduccion.getText(), "Produccion", false);
            ProduccionYogur produccion = controlador.buscarProduccionYogur(idProduccion); 
            
            analisis = controlador.buscarAnalisisYogur(id);
            
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
           analisis.setPh(ph);
           analisis.setAcidez(acidez);
           
           if(produccion instanceof ProduccionYogur){
            analisis.setProduccion(produccion);            
           }else{throw new Exception("La produccion no existe en el sistema");}

            boolean modificar = controlador.modificarAnalisisYogur(analisis);
            if(modificar){
                JOptionPane.showMessageDialog(null, "Analisis modificado correctamente.");
                limpiarFormulario();
                listar();
            } 
        }catch (Exception ex) {
          JOptionPane.showMessageDialog(null, ex.getMessage(),"Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnModificarActionPerformed

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
        txtPh.setText(tablaAnalisis.getValueAt(fila, 7).toString());
        txtAcidez.setText(tablaAnalisis.getValueAt(fila, 8).toString());
        int id = Integer.parseInt(tablaAnalisis.getValueAt(fila, 9).toString());
        ProduccionYogur produccion = controlador.buscarProduccionYogur(id);
        if(produccion instanceof ProduccionYogur){
            txtIdProduccion.setText(tablaAnalisis.getValueAt(fila, 9).toString());
            txtCodigoInterno.setText(produccion.getCodInterno());
            txtFechaProduccion.setText(produccion.getFecha());
        }        
        btnBuscar.doClick();    
    }//GEN-LAST:event_tablaAnalisisMouseClicked

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        try {
            encargado = buscarEncargado();
            txtEncargado.setText(encargado.getInfoCompleta());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    //</editor-fold>

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
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JTable tablaAnalisis;
    private javax.swing.JTable tablaProducciones;
    private javax.swing.JTextField txtAcidez;
    private javax.swing.JTextField txtCodigoInterno;
    private javax.swing.JTextField txtEncargado;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtFechaProduccion;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIdProduccion;
    private javax.swing.JTextField txtLevadura;
    private javax.swing.JTextField txtMos;
    private javax.swing.JTextField txtPFecales;
    private javax.swing.JTextField txtPTotales;
    private javax.swing.JTextField txtPh;
    // End of variables declaration//GEN-END:variables
}
