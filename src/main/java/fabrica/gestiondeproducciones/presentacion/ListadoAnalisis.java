
package fabrica.gestiondeproducciones.presentacion;

import fabrica.gestiondeproducciones.dominio.Analisis;
import fabrica.gestiondeproducciones.dominio.AnalisisDulce;
import fabrica.gestiondeproducciones.dominio.AnalisisIngreso;
import fabrica.gestiondeproducciones.dominio.AnalisisLechePasteurizada;
import fabrica.gestiondeproducciones.dominio.AnalisisManteca;
import fabrica.gestiondeproducciones.dominio.AnalisisQueso;
import fabrica.gestiondeproducciones.dominio.AnalisisYogur;
import fabrica.gestiondeproducciones.dominio.Controlador;
import fabrica.gestiondeproducciones.dominio.Empleado;
import fabrica.gestiondeproducciones.utilidades.Utilidades;
import java.awt.event.ItemEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class ListadoAnalisis extends javax.swing.JInternalFrame {

    Utilidades utilidad = new Utilidades();
    DefaultTableModel modelo = new DefaultTableModel();
    Controlador controlador = new Controlador();
    private TableRowSorter<TableModel> filtroTabla;

    
    /**
     * Creates new form ListadoAnalisis
     */
    public ListadoAnalisis() {
        initComponents();
        listar();
        agregarFiltros(txtEncargado, filtroTabla,1);
        agregarFiltrosComboBox(cbxTipo, filtroTabla,13);

    }
    private void listar(){
        cargarFecha();
        List<Analisis> lista = controlador.listarAnalisis();
        modelo = (DefaultTableModel) tablaAnalisis.getModel();
        Object[] objeto = new Object[15];
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
            listarAnalisisEspecifico(lista.get(i),objeto);
            modelo.addRow(objeto);
        }
        tablaAnalisis.setModel(modelo);
        filtroTabla = new TableRowSorter<>(modelo);
        tablaAnalisis.setRowSorter(filtroTabla);
    }
    
    private void cargarFecha(){
        LocalDate fechaHoy = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fecha = fechaHoy.format(format);
        txtFechaFin.setText(fecha);
        
        LocalDate fechaInicio = fechaHoy.minusDays(30);
        String fechaInicioStr = fechaInicio.format(format);
        txtFechaInicio.setText(fechaInicioStr);
    }
    
    private void listarAnalisisEspecifico(Analisis analisis, Object[] objeto){
        int id = analisis.getId();
        switch(analisis.getTipo()){
            case "ingreso" -> {
                AnalisisIngreso a = controlador.buscarAnalisisIngreso(id);
                objeto[7] = a.getGrasa();
                objeto[8] = a.getProteina();
                objeto[9] = a.getAgua();
                objeto[12] = a.getPh();
                objeto[13] = a.getTipo();
                objeto[14] = a.getIngreso().getIdIngreso();
                break;
            }
            case "pasteurizada" -> {
                AnalisisLechePasteurizada a = controlador.buscarAnalisisLechePast(id);
                objeto[7] = a.getGrasa();
                objeto[8] = a.getProteina();
                objeto[9] = a.getAgua();
                objeto[12] = a.getPh();
                objeto[13] = a.getTipo();
                objeto[14] = a.getLechePast().getId();
                break;
            }
            case "manteca" -> {
                AnalisisManteca a = controlador.buscarAnalisisManteca(id);
                objeto[7] = a.getGrasa();
                objeto[10] = a.getHumedad();
                objeto[12] = a.getPh();
                objeto[13] = a.getTipo();
                objeto[14] = a.getProduccion().getIdProduccion();
                break;
            }
            case "yogur" -> {
                AnalisisYogur a = controlador.buscarAnalisisYogur(id);
                objeto[12] = a.getPh();
                objeto[13] = a.getTipo();
                objeto[14] = a.getProduccion().getIdProduccion();
                break;
            }
            case "dulce" -> {
                AnalisisDulce a = controlador.buscarAnalisisDulce(id);
                objeto[7] = a.getGrasa();
                objeto[10] = a.getHumedad();
                objeto[12] = a.getPh();
                objeto[13] = a.getTipo();
                objeto[14] = a.getProduccion().getIdProduccion();
                break;
            }      
            case "queso" -> {
                AnalisisQueso a = controlador.buscarAnalisisQueso(id);
                objeto[7] = a.getGrasa();
                objeto[10] = a.getHumedad();
                objeto[11] = a.getSal();                
                objeto[12] = a.getPh();
                objeto[13] = a.getTipo();
                objeto[14] = a.getProduccion().getIdProduccion();
                break;
            } 
        }
    }
    
    private void agregarFiltros(javax.swing.JTextField campo, TableRowSorter fila, int columna) {
        campo.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                applyFilter(campo, fila, columna);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                applyFilter(campo, fila, columna);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                applyFilter(campo, fila, columna);
            }
        });
    }

    private void applyFilter(javax.swing.JTextField campo, TableRowSorter fila, int columna) {
        RowFilter<TableModel, Object> rf;

        if (campo.getText().length() == 0) {
            // El filtro no es restrictivo cuando el campo está vacío
            rf = RowFilter.regexFilter(".*", columna);
        } else {
            // Filtrar solo en la columna específica (columna)
            rf = RowFilter.regexFilter("(?i)" + campo.getText(), columna);
        }
        fila.setRowFilter(rf);
    }

    private void agregarFiltrosComboBox(javax.swing.JComboBox<String> comboBox, TableRowSorter fila, int columna) {
    comboBox.addItemListener((ItemEvent e) -> {
        applyFilterComboBox(comboBox, fila, columna);
    });
    }

    private void applyFilterComboBox(javax.swing.JComboBox<String> comboBox, TableRowSorter fila, int columna) {
        RowFilter<TableModel, Object> rf;

        String selectedItem = (String) comboBox.getSelectedItem();  // Obtener el valor seleccionado del JComboBox

        if (selectedItem == null || selectedItem.isEmpty()) {
            // Si no hay selección o la selección es vacía, mostrar todas las filas
            rf = RowFilter.regexFilter(".*", columna);
        } else {
            // Si hay selección, aplicar el filtro solo para esa columna
            rf = RowFilter.regexFilter("(?i)" + selectedItem, columna);
        }
        fila.setRowFilter(rf);
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        panelFiltros = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtEncargado = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cbxTipo = new javax.swing.JComboBox<>();
        panelFecha = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtFechaInicio = new javax.swing.JTextField();
        txtFechaFin = new javax.swing.JTextField();
        btnFechas = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaAnalisis = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        btnImprimirUnico = new javax.swing.JButton();
        txtAnalisisSeleccionado = new javax.swing.JTextField();

        jLabel3.setText("jLabel3");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Listado de Analisis");

        panelFiltros.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtros"));

        jLabel1.setText("Encargado");

        jLabel2.setText("Tipo de analisis");

        cbxTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ingreso", "Pasteurizada", "Manteca", "Yogur", "Queso", "Dulce" }));
        cbxTipo.setSelectedIndex(-1);

        jLabel4.setText("Fecha inicio");

        jLabel5.setText("Fecha fin");

        btnFechas.setText("Filtrar");
        btnFechas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFechasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelFechaLayout = new javax.swing.GroupLayout(panelFecha);
        panelFecha.setLayout(panelFechaLayout);
        panelFechaLayout.setHorizontalGroup(
            panelFechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFechaLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(panelFechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelFechaLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(62, 62, 62)
                        .addComponent(jLabel5)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelFechaLayout.createSequentialGroup()
                        .addComponent(txtFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnFechas)
                        .addContainerGap())))
        );
        panelFechaLayout.setVerticalGroup(
            panelFechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFechaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFechas))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        btnLimpiar.setText("Limpiar Filtros");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelFiltrosLayout = new javax.swing.GroupLayout(panelFiltros);
        panelFiltros.setLayout(panelFiltrosLayout);
        panelFiltrosLayout.setHorizontalGroup(
            panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFiltrosLayout.createSequentialGroup()
                .addGroup(panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFiltrosLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel1))
                    .addComponent(txtEncargado, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addComponent(panelFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLimpiar)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        panelFiltrosLayout.setVerticalGroup(
            panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFiltrosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(8, Short.MAX_VALUE))
            .addGroup(panelFiltrosLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEncargado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFiltrosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLimpiar)
                .addGap(20, 20, 20))
        );

        tablaAnalisis.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Encargado", "Fecha", "Levadura", "Mos", "Poliformos totales", "Poliformos fecales", "Grasa", "Proteina", "Agua", "Humedad", "Sal", "PH", "Tipo de analisis", "Ingreso / Produccion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Float.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaAnalisis.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tablaAnalisis.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jScrollPane2.setViewportView(tablaAnalisis);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Imprimir PDF"));

        btnImprimirUnico.setText("Imprimir Analisis");
        btnImprimirUnico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirUnicoActionPerformed(evt);
            }
        });

        txtAnalisisSeleccionado.setEditable(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnImprimirUnico)
                    .addComponent(txtAnalisisSeleccionado, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(398, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtAnalisisSeleccionado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnImprimirUnico)
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(panelFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(86, 86, 86)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 162, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelFiltros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 621, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(172, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1419, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 752, Short.MAX_VALUE))
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
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFechasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFechasActionPerformed
        try{
            String fechaInicioStr = utilidad.controlarFechas(txtFechaInicio.getText());
            String fechaFinStr = utilidad.controlarFechas(txtFechaFin.getText());

            if (!fechaInicioStr.isEmpty() && !fechaFinStr.isEmpty()) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date fechaInicio = sdf.parse(fechaInicioStr);
                    Date fechaFin = sdf.parse(fechaFinStr);

                    RowFilter<TableModel, Object> rf = new RowFilter<TableModel, Object>() {
                        @Override
                        public boolean include(RowFilter.Entry<? extends TableModel, ? extends Object> entry) {
                            String fechaStr = (String) entry.getValue(2); 
                            try {
                                Date fecha = sdf.parse(fechaStr);

                                return !fecha.before(fechaInicio) && !fecha.after(fechaFin);
                            } catch (ParseException ex) {
                                return false;
                            }
                        }
                    };

                    filtroTabla.setRowFilter(rf);

                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Las fechas no son válidas.");
                }
            } else {
                filtroTabla.setRowFilter(null);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btnFechasActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        filtroTabla.setRowFilter(null);
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnImprimirUnicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirUnicoActionPerformed
        
    }//GEN-LAST:event_btnImprimirUnicoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFechas;
    private javax.swing.JButton btnImprimirUnico;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JComboBox<String> cbxTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panelFecha;
    private javax.swing.JPanel panelFiltros;
    private javax.swing.JTable tablaAnalisis;
    private javax.swing.JTextField txtAnalisisSeleccionado;
    private javax.swing.JTextField txtEncargado;
    private javax.swing.JTextField txtFechaFin;
    private javax.swing.JTextField txtFechaInicio;
    // End of variables declaration//GEN-END:variables
}
