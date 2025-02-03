
package fabrica.gestiondeproducciones.presentacion;

import com.toedter.calendar.JDateChooser;
import fabrica.gestiondeproducciones.dominio.Analisis;
import fabrica.gestiondeproducciones.dominio.AnalisisDulce;
import fabrica.gestiondeproducciones.dominio.AnalisisIngreso;
import fabrica.gestiondeproducciones.dominio.AnalisisLechePasteurizada;
import fabrica.gestiondeproducciones.dominio.AnalisisManteca;
import fabrica.gestiondeproducciones.dominio.AnalisisQueso;
import fabrica.gestiondeproducciones.dominio.AnalisisYogur;
import fabrica.gestiondeproducciones.dominio.Controlador;
import fabrica.gestiondeproducciones.dominio.Empleado;
import java.awt.event.ItemEvent;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class ListadoAnalisis extends javax.swing.JInternalFrame {

    DefaultTableModel modelo = new DefaultTableModel();
    Controlador controlador = new Controlador();
    private TableRowSorter<TableModel> filtroTabla;

    
    /**
     * Creates new form ListadoAnalisis
     */
    public ListadoAnalisis() {
        initComponents();
        agregaFiltroFechas();
        listar();
        agregarFiltros(txtEncargado, filtroTabla,1);
        agregarFiltrosComboBox(cbxTipo, filtroTabla,13);

    }

    private void agregaFiltroFechas(){ 
        
        JDateChooser dateChooserStart = new JDateChooser();
        dateChooserStart.setDateFormatString("dd/MM/yyyy");
        
        JDateChooser dateChooserEnd = new JDateChooser();
        dateChooserEnd.setDateFormatString("dd/MM/yyyy");
        
        panelFecha.add(new JLabel("Fecha de inicio:"));
        panelFecha.add(dateChooserStart);
        panelFecha.add(new JLabel("Fecha de fin:"));
        panelFecha.add(dateChooserEnd);
        
        JButton filterButton = new JButton("Filtrar");
        filterButton.addActionListener(e -> {
            Date startDate = dateChooserStart.getDate();
            Date endDate = dateChooserEnd.getDate();

            if (startDate != null && endDate != null) {
                
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione ambas fechas.");
            }
        });
        
        panelFecha.add(filterButton);
        panelFecha.setVisible(true);
    }
    
    private void listar(){
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
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaAnalisis = new javax.swing.JTable();

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

        javax.swing.GroupLayout panelFechaLayout = new javax.swing.GroupLayout(panelFecha);
        panelFecha.setLayout(panelFechaLayout);
        panelFechaLayout.setHorizontalGroup(
            panelFechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 294, Short.MAX_VALUE)
        );
        panelFechaLayout.setVerticalGroup(
            panelFechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelFiltrosLayout = new javax.swing.GroupLayout(panelFiltros);
        panelFiltros.setLayout(panelFiltrosLayout);
        panelFiltrosLayout.setHorizontalGroup(
            panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFiltrosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFiltrosLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel1))
                    .addComponent(txtEncargado, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(93, 93, 93)
                .addGroup(panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(104, 104, 104)
                .addComponent(panelFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        panelFiltrosLayout.setVerticalGroup(
            panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFiltrosLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEncargado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
            .addGroup(panelFiltrosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(panelFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 655, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE)
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbxTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panelFecha;
    private javax.swing.JPanel panelFiltros;
    private javax.swing.JTable tablaAnalisis;
    private javax.swing.JTextField txtEncargado;
    // End of variables declaration//GEN-END:variables
}
