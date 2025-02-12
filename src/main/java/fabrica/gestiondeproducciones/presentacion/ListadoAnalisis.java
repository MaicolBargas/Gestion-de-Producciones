package fabrica.gestiondeproducciones.presentacion;

import com.itextpdf.text.Chunk;
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
import com.itextpdf.text.*;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.RowSorter;
import javax.swing.SortOrder;

public class ListadoAnalisis extends javax.swing.JInternalFrame {

    Utilidades utilidad = new Utilidades();
    DefaultTableModel modelo = new DefaultTableModel();
    Controlador controlador = new Controlador();
    private TableRowSorter<TableModel> filtroTabla;
    Analisis analisisSeleccionado = null;
    List<Analisis> listaAnalisisSeleccionados = new ArrayList<>();

    /**
     * Creates new form ListadoAnalisis
     */
    public ListadoAnalisis() {
        initComponents();
        listar();
        agregarFiltros(txtEncargado, filtroTabla, 1);
        agregarFiltrosComboBox(cbxTipo, filtroTabla, 13);
        seleccionDeAnalisis();
        filtroTabla.setSortKeys(java.util.List.of(new RowSorter.SortKey(0, SortOrder.DESCENDING)));

    }

    private void listar() {
        cargarFecha();
        List<Analisis> lista = controlador.listarAnalisis();
        modelo = (DefaultTableModel) tablaAnalisis.getModel();
        Object[] objeto;
        for (int i = 0; i < lista.size(); i++) {
            objeto = obtenerAnalisis(lista.get(i));
            modelo.addRow(objeto);
        }
        tablaAnalisis.setModel(modelo);
        filtroTabla = new TableRowSorter<>(modelo);
        tablaAnalisis.setRowSorter(filtroTabla);
    }

    public Object[] obtenerAnalisis(Analisis analisis) {
        Object[] objeto = new Object[15];
        objeto[0] = analisis.getId();
        if (analisis.getEncargado() instanceof Empleado) {
            objeto[1] = analisis.getEncargado().getInfoCompleta();
        }
        objeto[2] = analisis.getFecha();
        objeto[3] = analisis.getLevadura();
        objeto[4] = analisis.getMos();
        objeto[5] = analisis.getPoliformosTotales();
        objeto[6] = analisis.getPoliformosFecales();
        listarAnalisisEspecifico(analisis, objeto);
        return objeto;
    }

    private void cargarFecha() {
        LocalDate fechaHoy = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fecha = fechaHoy.format(format);
        txtFechaFin.setText(fecha);

        LocalDate fechaInicio = fechaHoy.minusDays(30);
        String fechaInicioStr = fechaInicio.format(format);
        txtFechaInicio.setText(fechaInicioStr);
    }

    private void listarAnalisisEspecifico(Analisis analisis, Object[] objeto) {
        int id = analisis.getId();
        switch (analisis.getTipo()) {
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
                objeto[14] = a.getProduccion().getCodInterno();
                break;
            }
            case "yogur" -> {
                AnalisisYogur a = controlador.buscarAnalisisYogur(id);
                objeto[12] = a.getPh();
                objeto[13] = a.getTipo();
                objeto[14] = a.getProduccion().getCodInterno();
                break;
            }
            case "dulce" -> {
                AnalisisDulce a = controlador.buscarAnalisisDulce(id);
                objeto[7] = a.getGrasa();
                objeto[10] = a.getHumedad();
                objeto[12] = a.getPh();
                objeto[13] = a.getTipo();
                objeto[14] = a.getProduccion().getCodInterno();
                break;
            }
            case "queso" -> {
                AnalisisQueso a = controlador.buscarAnalisisQueso(id);
                objeto[7] = a.getGrasa();
                objeto[10] = a.getHumedad();
                objeto[11] = a.getSal();
                objeto[12] = a.getPh();
                objeto[13] = a.getTipo();
                objeto[14] = a.getProduccion().getCodInterno();
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
            rf = RowFilter.regexFilter(".*", columna);
        } else {
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

        String selectedItem = (String) comboBox.getSelectedItem();

        if (selectedItem == null || selectedItem.isEmpty()) {
            rf = RowFilter.regexFilter(".*", columna);
        } else {
            rf = RowFilter.regexFilter("(?i)" + selectedItem, columna);
        }
        fila.setRowFilter(rf);
    }

    private void abrirPDF(String rutaArchivo) {
        try {
            File archivoPDF = new File(rutaArchivo);
            if (archivoPDF.exists()) {
                Desktop.getDesktop().open(archivoPDF);
            } else {
                JOptionPane.showMessageDialog(null, "El archivo PDF no se encuentra en la ruta especificada.");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al intentar abrir el archivo PDF: " + e.getMessage());
        }
    }

    private void seleccionDeAnalisis() {
        tablaAnalisis.getSelectionModel().addListSelectionListener(e -> {
            listaAnalisisSeleccionados.clear();
            if (!e.getValueIsAdjusting()) {
                int[] selectedRows = tablaAnalisis.getSelectedRows();
                if (selectedRows.length <= 10) {
                    for (int fila : selectedRows) {
                        int id = Integer.parseInt(tablaAnalisis.getValueAt(fila, 0).toString());
                        Analisis analisis = controlador.buscarAnalisis(id);
                        listaAnalisisSeleccionados.add(analisis);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No es posible imprimir mas de 10 registros a la vez");
                }

            }
        });
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
        btnLimpiar = new javax.swing.JButton();
        txtFechaFin = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtFechaInicio = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnFechas = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaAnalisis = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        btnImprimirUnico = new javax.swing.JButton();
        txtAnalisisSeleccionado = new javax.swing.JTextField();
        btnImprimirListadoAnalisis = new javax.swing.JButton();

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

        btnLimpiar.setText("Limpiar Filtros");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        jLabel4.setText("Fecha inicio");

        jLabel5.setText("Fecha fin");

        btnFechas.setText("Filtrar");
        btnFechas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFechasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelFiltrosLayout = new javax.swing.GroupLayout(panelFiltros);
        panelFiltros.setLayout(panelFiltrosLayout);
        panelFiltrosLayout.setHorizontalGroup(
            panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFiltrosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEncargado, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(44, 44, 44)
                .addGroup(panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65)
                .addGroup(panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(panelFiltrosLayout.createSequentialGroup()
                        .addComponent(txtFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFechas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addComponent(btnLimpiar)))
                .addContainerGap())
        );
        panelFiltrosLayout.setVerticalGroup(
            panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFiltrosLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEncargado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiar)
                    .addComponent(btnFechas))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        tablaAnalisis.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Encargado", "Fecha", "Levadura", "Mohos", "Coliformes totales", "Coliformes fecales", "Grasa", "Proteina", "Agua", "Humedad", "Sal", "PH", "Tipo de analisis", "Ingreso / Produccion"
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
        tablaAnalisis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaAnalisisMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaAnalisis);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Imprimir PDF"));

        btnImprimirUnico.setText("Imprimir Analisis Unico");
        btnImprimirUnico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirUnicoActionPerformed(evt);
            }
        });

        txtAnalisisSeleccionado.setEditable(false);

        btnImprimirListadoAnalisis.setText("Imprimir Lista de Analisis");
        btnImprimirListadoAnalisis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirListadoAnalisisActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtAnalisisSeleccionado, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnImprimirUnico)
                        .addGap(18, 18, 18)
                        .addComponent(btnImprimirListadoAnalisis)))
                .addGap(210, 210, 210))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(txtAnalisisSeleccionado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnImprimirUnico)
                    .addComponent(btnImprimirListadoAnalisis))
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
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 588, Short.MAX_VALUE))
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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1443, Short.MAX_VALUE)
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
        try {
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
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btnFechasActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        filtroTabla.setRowFilter(null);
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnImprimirUnicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirUnicoActionPerformed
        Document documento = new Document();
        try {
            if (analisisSeleccionado == null) {
                throw new DocumentException("Debe seleccionar un análisis");
            }

            LocalDateTime fechaHoraActual = LocalDateTime.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM_HH-mm");
            String fechaHoraString = fechaHoraActual.format(formato);

            String ruta = System.getProperty("user.home") + "/Desktop/Reportes/Analisis_" + analisisSeleccionado.getId() + "_" + fechaHoraString + ".pdf";
            PdfWriter.getInstance(documento, new FileOutputStream(ruta));

            documento.open();

            // Agregar tabla de encabezado con logo y textos
            PdfPTable headerTable = new PdfPTable(3);
            headerTable.setWidthPercentage(100);
            float[] columnWidths = {1f, 2f, 1f};
            headerTable.setWidths(columnWidths);

            //Logo
            InputStream imageStream = getClass().getClassLoader().getResourceAsStream("magnolia-logo.jpg");
          
            if (imageStream != null) {
                byte[] imageBytes = imageStream.readAllBytes();
                Image logo = Image.getInstance(imageBytes);
                logo.scaleToFit(80, 80);
                PdfPCell cellLogo = new PdfPCell(logo);
                cellLogo.setBorder(Rectangle.NO_BORDER);
                headerTable.addCell(cellLogo);
            }

            // Títulos centrales
            Font fontTitulo = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            PdfPCell cellTitulo = new PdfPCell(new Paragraph("Granja La Magnolia - Reporte de Análisis", fontTitulo));
            cellTitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTitulo.setBorder(Rectangle.NO_BORDER);
            headerTable.addCell(cellTitulo);

            // Fecha
            PdfPCell cellFecha = new PdfPCell(new Phrase("Fecha: " + fechaHoraActual.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
            cellFecha.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellFecha.setBorder(Rectangle.NO_BORDER);
            headerTable.addCell(cellFecha);
            headerTable.setSpacingBefore(10);
            headerTable.getDefaultCell().setBorder(Rectangle.BOTTOM);

            documento.add(headerTable);
            documento.add(Chunk.NEWLINE);

            // Subtitulo
            Font fontSubTitulo = new Font(Font.FontFamily.HELVETICA, 14);
            Paragraph subTitulo = new Paragraph("Analisis de " + analisisSeleccionado.getTipo().toUpperCase(), fontSubTitulo);
            subTitulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(subTitulo);
            documento.add(Chunk.NEWLINE);

            PdfPTable tabla = new PdfPTable(2);
            tabla.setWidthPercentage(100);

            Object[] objeto = obtenerAnalisis(analisisSeleccionado);

            Map<String, Object> datosAnalisis = new HashMap<>();

            String[] nombresCampos = {"Id", "Encargado", "Fecha", "Levadura", "Mohos", "Coliformes Totales", "Coliformes Fecales", "Grasa", "Proteína", "Agua", "Humedad", "Sal", "PH", "Tipo", "Analizado"};

            // Indices para excluir
            Set<Integer> indicesExcluidos = new HashSet<>();
            indicesExcluidos.add(4);
            indicesExcluidos.add(6);
            indicesExcluidos.add(8);

            for (int i = 0; i < nombresCampos.length; i++) {
                if (!indicesExcluidos.contains(i) && objeto[i] != null) {
                    datosAnalisis.put(nombresCampos[i], objeto[i]);
                }
            }

            //FORMATO TABLA
            for (String campo : nombresCampos) {
                if (datosAnalisis.containsKey(campo)) {
                    PdfPCell cellCampo = new PdfPCell(new Phrase(campo, new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
                    cellCampo.setPadding(5);
                    cellCampo.setBorderWidth(1.5f);
                    tabla.addCell(cellCampo);

                    PdfPCell cellValor = new PdfPCell(new Phrase(datosAnalisis.get(campo).toString()));
                    cellValor.setPadding(5);
                    cellValor.setBorderWidth(1.5f);
                    tabla.addCell(cellValor);
                }
            }
            documento.add(tabla);
            documento.add(Chunk.NEWLINE);
            documento.close();

            JOptionPane.showMessageDialog(null, "El reporte se ha generado correctamente.");
            abrirPDF(ruta);
        } catch (DocumentException | FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error al generar el PDF: " + e.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(ListadoAnalisis.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnImprimirUnicoActionPerformed

    private void tablaAnalisisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAnalisisMouseClicked
        int fila = tablaAnalisis.rowAtPoint(evt.getPoint());
        int id = Integer.parseInt(tablaAnalisis.getValueAt(fila, 0).toString());
        txtAnalisisSeleccionado.setText(tablaAnalisis.getValueAt(fila, 0).toString());
        analisisSeleccionado = controlador.buscarAnalisis(id);
    }//GEN-LAST:event_tablaAnalisisMouseClicked

    private void btnImprimirListadoAnalisisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirListadoAnalisisActionPerformed
        Document documento = new Document();
        try {
            if (listaAnalisisSeleccionados.isEmpty()) {
                throw new DocumentException("El listado de análisis está vacío");
            }

            LocalDateTime fechaHoraActual = LocalDateTime.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM_HH-mm");
            String fechaHoraString = fechaHoraActual.format(formato);

            String ruta = System.getProperty("user.home") + "/Desktop/Reportes/Lista_Analisis_" + fechaHoraString + ".pdf";
            PdfWriter.getInstance(documento, new FileOutputStream(ruta));

            documento.open();

            // Agregar tabla de encabezado con logo y textos
            PdfPTable headerTable = new PdfPTable(3);
            headerTable.setWidthPercentage(100);
            float[] columnWidths = {1f, 2f, 1f};
            headerTable.setWidths(columnWidths);

            //Logo
            InputStream imageStream = getClass().getClassLoader().getResourceAsStream("magnolia-logo.jpg");
          
            if (imageStream != null) {
                byte[] imageBytes = imageStream.readAllBytes();
                Image logo = Image.getInstance(imageBytes);
                logo.scaleToFit(80, 80);
                PdfPCell cellLogo = new PdfPCell(logo);
                cellLogo.setBorder(Rectangle.NO_BORDER);
                headerTable.addCell(cellLogo);
            }

            // Títulos centrales
            Font fontTitulo = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            PdfPCell cellTitulo = new PdfPCell(new Paragraph("Granja La Magnolia - Reporte de Análisis", fontTitulo));
            cellTitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTitulo.setBorder(Rectangle.NO_BORDER);
            headerTable.addCell(cellTitulo);

            // Fecha
            PdfPCell cellFecha = new PdfPCell(new Phrase("Fecha: " + fechaHoraActual.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
            cellFecha.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellFecha.setBorder(Rectangle.NO_BORDER);
            headerTable.addCell(cellFecha);
            headerTable.setSpacingBefore(10);
            headerTable.getDefaultCell().setBorder(Rectangle.BOTTOM);

            documento.add(headerTable);
            documento.add(Chunk.NEWLINE);

            // Subtítulo
            Font fontSubTitulo = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
            Paragraph subTitulo = new Paragraph("Listado de Análisis", fontSubTitulo);
            subTitulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(subTitulo);
            documento.add(Chunk.NEWLINE);

            for (Analisis analisis : listaAnalisisSeleccionados) {
                PdfPTable tabla = new PdfPTable(2);
                tabla.setWidthPercentage(100);
                tabla.setSpacingBefore(10);

                Object[] objeto = obtenerAnalisis(analisis);
                Map<String, Object> datosAnalisis = new HashMap<>();
                String[] nombresCampos = {"Id", "Encargado", "Fecha", "Levadura", "Mohos", "Coliformes Totales", "Coliformes Fecales", "Grasa", "Proteína", "Agua", "Humedad", "Sal", "PH", "Tipo", "Analizado"};

                // Indices para excluir
                Set<Integer> indicesExcluidos = new HashSet<>();
                indicesExcluidos.add(4);
                indicesExcluidos.add(6);
                indicesExcluidos.add(8);

                for (int i = 0; i < nombresCampos.length; i++) {
                    if (!indicesExcluidos.contains(i) && objeto[i] != null) {
                        datosAnalisis.put(nombresCampos[i], objeto[i]);
                    }
                }

                for (String campo : nombresCampos) {
                    if (datosAnalisis.containsKey(campo)) {
                        PdfPCell cellCampo = new PdfPCell(new Phrase(campo, new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
                        cellCampo.setPadding(5);
                        cellCampo.setBorderWidth(1.5f);
                        tabla.addCell(cellCampo);

                        PdfPCell cellValor = new PdfPCell(new Phrase(datosAnalisis.get(campo).toString()));
                        cellValor.setPadding(5);
                        cellValor.setBorderWidth(1.5f);
                        tabla.addCell(cellValor);
                    }
                }

                documento.add(tabla);
                documento.add(Chunk.NEWLINE);
            }

            documento.close();
            JOptionPane.showMessageDialog(null, "El reporte se ha generado correctamente.");
            abrirPDF(ruta);
        } catch (DocumentException | HeadlessException | IOException e) {
            JOptionPane.showMessageDialog(null, "Error al generar el PDF: " + e.getMessage());
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnImprimirListadoAnalisisActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFechas;
    private javax.swing.JButton btnImprimirListadoAnalisis;
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
    private javax.swing.JPanel panelFiltros;
    private javax.swing.JTable tablaAnalisis;
    private javax.swing.JTextField txtAnalisisSeleccionado;
    private javax.swing.JTextField txtEncargado;
    private javax.swing.JTextField txtFechaFin;
    private javax.swing.JTextField txtFechaInicio;
    // End of variables declaration//GEN-END:variables
}
