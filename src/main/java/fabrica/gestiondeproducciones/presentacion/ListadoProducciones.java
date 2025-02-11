package fabrica.gestiondeproducciones.presentacion;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import fabrica.gestiondeproducciones.dominio.Analisis;
import fabrica.gestiondeproducciones.dominio.Produccion;
import fabrica.gestiondeproducciones.dominio.Controlador;
import fabrica.gestiondeproducciones.dominio.Empleado;
import fabrica.gestiondeproducciones.dominio.ProduccionDulce;
import fabrica.gestiondeproducciones.dominio.ProduccionManteca;
import fabrica.gestiondeproducciones.dominio.ProduccionQueso;
import fabrica.gestiondeproducciones.dominio.ProduccionYogur;
import fabrica.gestiondeproducciones.dominio.Producto;
import fabrica.gestiondeproducciones.utilidades.Utilidades;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dialog;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class ListadoProducciones extends javax.swing.JInternalFrame {

    Utilidades utilidad = new Utilidades();
    DefaultTableModel modelo = new DefaultTableModel();
    Controlador controlador = new Controlador();
    private TableRowSorter<TableModel> filtroTabla;
    Produccion produccionSeleccionada = null;
    List<Produccion> listaProduccionesSeleccionadas = new ArrayList<>();
    ListadoAnalisis listadoAnalisis = new ListadoAnalisis();

    /**
     * Creates new form ListadoProducciones
     */
    public ListadoProducciones() {
        initComponents();
        listar();
        agregarFiltros(txtEncargado, filtroTabla, 4);
        agregarFiltros(txtCodigoInterno, filtroTabla, 1);
        agregarFiltros(txtProducto, filtroTabla, 2);
        detalleProducciones();
        seleccionDeProducciones();
        filtroTabla.setSortKeys(java.util.List.of(new RowSorter.SortKey(0, SortOrder.DESCENDING)));

    }

    private void listar() {
        cargarFecha();
        List<Produccion> lista = controlador.listarProducciones();
        modelo = (DefaultTableModel) tablaProducciones.getModel();
        Object[] objeto;
        for (int i = 0; i < lista.size(); i++) {
            objeto = obtenerProduccion(lista.get(i));
            modelo.addRow(objeto);
        }
        tablaProducciones.setModel(modelo);
        filtroTabla = new TableRowSorter<>(modelo);
        tablaProducciones.setRowSorter(filtroTabla);
    }

    private Object[] obtenerProduccion(Produccion produccion) {
        Object[] objeto = new Object[15];
        objeto[0] = produccion.getIdProduccion();
        objeto[1] = produccion.getCodInterno();

        if (produccion.getProducto() instanceof Producto) {
            objeto[2] = produccion.getProducto().getNombre();
        }

        objeto[3] = produccion.getFecha();
        if (produccion.getEncargado() instanceof Empleado) {
            objeto[4] = produccion.getEncargado().getInfoCompleta();
        }
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

    private void detalleProducciones() {
        tablaProducciones.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Doble clic en la fila
                    int filaSeleccionada = tablaProducciones.getSelectedRow();
                    if (filaSeleccionada != -1) {
                        int id = (int) tablaProducciones.getValueAt(filaSeleccionada, 0);
                        String codigoInterno = (String) tablaProducciones.getValueAt(filaSeleccionada, 1);

                        Object[] produccion = obtenerProduccionPorCodigo(id, codigoInterno);
                        mostrarDetalleProduccion(tablaProducciones, produccion);
                    }
                }
            }
        });
    }

    private void mostrarDetalleProduccion(Component parent, Object[] produccion) {
        JDialog dialogo = new JDialog(SwingUtilities.getWindowAncestor(parent), "Detalles de Producción", Dialog.ModalityType.APPLICATION_MODAL);
        dialogo.setSize(600, 800);
        dialogo.setLayout(new GridLayout(produccion.length, 1, 5, 5));
        dialogo.getRootPane().setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        dialogo.getContentPane().setBackground(Color.WHITE);

        for (Object obj : produccion) {
            Object[] atributo = (Object[]) obj;
            JLabel label = new JLabel("<html><b>" + atributo[0] + ":</b> " + atributo[1] + "</html>");
            label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            label.setForeground(Color.DARK_GRAY);
            dialogo.add(label);
        }

        dialogo.setLocationRelativeTo(parent);
        dialogo.setVisible(true);
    }

    private Object[] obtenerProduccionPorCodigo(int id, String codigo) {

        switch (codigo.charAt(0)) {
            case 'M' -> {
                ProduccionManteca produccionManteca = controlador.buscarProduccionManteca(id);
                    return produccionManteca.produccionToArray();
                }
            case 'Q' -> {
                ProduccionQueso produccionQueso = controlador.buscarProduccionQueso(id);
                return produccionQueso.produccionToArray();
            }
            case 'Y' -> {
                ProduccionYogur produccionYogur = controlador.buscarProduccionYogur(id);
                return produccionYogur.produccionToArray();
            }
            case 'D' -> {
                ProduccionDulce produccionDulce = controlador.buscarProduccionDulce(id);
                return produccionDulce.produccionToArray();
            }
            default -> {
                return null;
            }
        }
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

    private void seleccionDeProducciones() {
        tablaProducciones.getSelectionModel().addListSelectionListener(e -> {
            listaProduccionesSeleccionadas.clear();
            if (!e.getValueIsAdjusting()) {
                int[] selectedRows = tablaProducciones.getSelectedRows();
                if (selectedRows.length <= 10) {
                    for (int fila : selectedRows) {
                        int id = Integer.parseInt(tablaProducciones.getValueAt(fila, 0).toString());
                        Produccion produccion = controlador.buscarProduccion(id);
                        listaProduccionesSeleccionadas.add(produccion);
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

        scroll = new javax.swing.JScrollPane();
        principal = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtEncargado = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtFechaInicio = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtFechaFin = new javax.swing.JTextField();
        btnFiltrar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtCodigoInterno = new javax.swing.JTextField();
        txtProducto = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        txtProduccionSeleccionada = new javax.swing.JTextField();
        btnImprimirUnico = new javax.swing.JButton();
        btnImprimirListado = new javax.swing.JButton();
        btnImprimirFlujoCompleto = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaProducciones = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Listado de Producciones");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtros"));

        jLabel1.setText("Encargado");

        jLabel2.setText("Producto");

        jLabel3.setText("Fecha inicio");

        jLabel4.setText("Fecha fin");

        btnFiltrar.setText("Filtrar");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        btnLimpiar.setText("Limpiar filtros");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        jLabel5.setText("Codigo Interno");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEncargado, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtCodigoInterno, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txtFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnFiltrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLimpiar))
                    .addComponent(jLabel4))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEncargado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFiltrar)
                    .addComponent(btnLimpiar)
                    .addComponent(txtCodigoInterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Imprimir PDF"));

        txtProduccionSeleccionada.setEditable(false);

        btnImprimirUnico.setText("Imprimir Produccion Unica");
        btnImprimirUnico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirUnicoActionPerformed(evt);
            }
        });

        btnImprimirListado.setText("Imprimir Lista de Producciones ");
        btnImprimirListado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirListadoActionPerformed(evt);
            }
        });

        btnImprimirFlujoCompleto.setText("Imprimir flujo completo");
        btnImprimirFlujoCompleto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirFlujoCompletoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtProduccionSeleccionada, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnImprimirFlujoCompleto))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnImprimirUnico)
                        .addGap(31, 31, 31)
                        .addComponent(btnImprimirListado)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProduccionSeleccionada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnImprimirFlujoCompleto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnImprimirUnico)
                    .addComponent(btnImprimirListado))
                .addContainerGap())
        );

        tablaProducciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Codigo Interno", "Producto", "Fecha", "Encargado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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

        javax.swing.GroupLayout principalLayout = new javax.swing.GroupLayout(principal);
        principal.setLayout(principalLayout);
        principalLayout.setHorizontalGroup(
            principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(principalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1402, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(principalLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        principalLayout.setVerticalGroup(
            principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(principalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        jPanel1.getAccessibleContext().setAccessibleParent(scroll);

        scroll.setViewportView(principal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scroll)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 831, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
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
                            String fechaStr = (String) entry.getValue(3);
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
    }//GEN-LAST:event_btnFiltrarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        filtroTabla.setRowFilter(null);
        cargarFecha();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void tablaProduccionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProduccionesMouseClicked
        int fila = tablaProducciones.rowAtPoint(evt.getPoint());
        int id = Integer.parseInt(tablaProducciones.getValueAt(fila, 0).toString());
        txtProduccionSeleccionada.setText(tablaProducciones.getValueAt(fila, 0).toString());
        produccionSeleccionada = controlador.buscarProduccion(id);
    }//GEN-LAST:event_tablaProduccionesMouseClicked

    private void btnImprimirUnicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirUnicoActionPerformed
        Document documento = new Document();
        try {
            if (produccionSeleccionada == null) {
                throw new DocumentException("Debe seleccionar un análisis");
            }

            LocalDateTime fechaHoraActual = LocalDateTime.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM_HH-mm");
            String fechaHoraString = fechaHoraActual.format(formato);

            String ruta = System.getProperty("user.home") + "/Desktop/Reportes/Produccion_" + produccionSeleccionada.getCodInterno() + "_" + fechaHoraString + ".pdf";
            PdfWriter.getInstance(documento, new FileOutputStream(ruta));

            documento.open();

            // Agregar tabla de encabezado con logo y textos
            PdfPTable headerTable = new PdfPTable(3);
            headerTable.setWidthPercentage(100);
            float[] columnWidths = {1f, 2f, 1f};
            headerTable.setWidths(columnWidths);

            //Logo
            String path = System.getProperty("user.dir") + "/src/main/java/fabrica/gestiondeproducciones/img/magnolia-logo.jpg";
            Image logo = Image.getInstance(path);
            logo.scaleToFit(80, 80);
            PdfPCell cellLogo = new PdfPCell(logo);
            cellLogo.setBorder(Rectangle.NO_BORDER);
            headerTable.addCell(cellLogo);

            // Títulos centrales
            Font fontTitulo = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            PdfPCell cellTitulo = new PdfPCell(new Paragraph("Granja La Magnolia", fontTitulo));
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
            Paragraph subTitulo = new Paragraph("Reporte de Produccion", fontSubTitulo);
            subTitulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(subTitulo);
            documento.add(Chunk.NEWLINE);

            PdfPTable tabla = new PdfPTable(2);
            tabla.setWidthPercentage(100);
           
            Object[] campos = obtenerProduccionPorCodigo(produccionSeleccionada.getIdProduccion(), produccionSeleccionada.getCodInterno());

            //FORMATO TABLA
            for (Object campo : campos) {
                Object[] linea = (Object[]) campo;
                String titulo = (String) linea[0];
                String valor = linea[1]+"";
                PdfPCell cellCampo = new PdfPCell(new Phrase(titulo, new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
                cellCampo.setPadding(5);
                cellCampo.setBorderWidth(1.5f);
                tabla.addCell(cellCampo);

                PdfPCell cellValor = new PdfPCell(new Phrase(valor));
                cellValor.setPadding(5);
                cellValor.setBorderWidth(1.5f);
                tabla.addCell(cellValor);
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

    private void btnImprimirListadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirListadoActionPerformed
        Document documento = new Document();
        try {
            if (listaProduccionesSeleccionadas.isEmpty()) {
                throw new DocumentException("El listado de análisis está vacío");
            }

            LocalDateTime fechaHoraActual = LocalDateTime.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM_HH-mm");
            String fechaHoraString = fechaHoraActual.format(formato);

            String ruta = System.getProperty("user.home") + "/Desktop/Reportes/Lista_Producciones_" + fechaHoraString + ".pdf";
            PdfWriter.getInstance(documento, new FileOutputStream(ruta));

            documento.open();

            // Agregar tabla de encabezado con logo y textos
            PdfPTable headerTable = new PdfPTable(3);
            headerTable.setWidthPercentage(100);
            float[] columnWidths = {1f, 2f, 1f};
            headerTable.setWidths(columnWidths);

            //Logo
            String path = System.getProperty("user.dir") + "/src/main/java/fabrica/gestiondeproducciones/img/magnolia-logo.jpg";
            Image logo = Image.getInstance(path);
            logo.scaleToFit(80, 80);
            PdfPCell cellLogo = new PdfPCell(logo);
            cellLogo.setBorder(Rectangle.NO_BORDER);
            headerTable.addCell(cellLogo);

            // Títulos centrales
            Font fontTitulo = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            PdfPCell cellTitulo = new PdfPCell(new Paragraph("Granja La Magnolia", fontTitulo));
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
            Paragraph subTitulo = new Paragraph("Listado de Producciones", fontSubTitulo);
            subTitulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(subTitulo);
            documento.add(Chunk.NEWLINE);

            for (Produccion produccion : listaProduccionesSeleccionadas) {
                PdfPTable tabla = new PdfPTable(2);
                tabla.setWidthPercentage(100);

                Paragraph codigoProd = new Paragraph(produccion.getCodInterno(), fontSubTitulo);
                documento.add(codigoProd);
                documento.add(Chunk.SPACETABBING);

                Object[] campos = obtenerProduccionPorCodigo(produccion.getIdProduccion(), produccion.getCodInterno());

                //FORMATO TABLA
                for (Object campo : campos) {
                    Object[] linea = (Object[]) campo;
                    String titulo = (String) linea[0];
                    String valor = linea[1]+"";
                    PdfPCell cellCampo = new PdfPCell(new Phrase(titulo, new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
                    cellCampo.setPadding(5);
                    cellCampo.setBorderWidth(1.5f);
                    tabla.addCell(cellCampo);

                    PdfPCell cellValor = new PdfPCell(new Phrase(valor));
                    cellValor.setPadding(5);
                    cellValor.setBorderWidth(1.5f);
                    tabla.addCell(cellValor);
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
    }//GEN-LAST:event_btnImprimirListadoActionPerformed

    private void btnImprimirFlujoCompletoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirFlujoCompletoActionPerformed
        Document documento = new Document();
        try {
            if (produccionSeleccionada == null) {
                throw new DocumentException("Debe seleccionar un análisis");
            }

            LocalDateTime fechaHoraActual = LocalDateTime.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM_HH-mm");
            String fechaHoraString = fechaHoraActual.format(formato);

            String ruta = System.getProperty("user.home") + "/Desktop/Reportes/Produccion_FlujoCompleto_" + produccionSeleccionada.getCodInterno() + "_" + fechaHoraString + ".pdf";
            PdfWriter.getInstance(documento, new FileOutputStream(ruta));

            documento.open();

            // <editor-fold defaultstate="collapsed" desc="HEADER PDF">                          
            // Agregar tabla de encabezado con logo y textos
            PdfPTable headerTable = new PdfPTable(3);
            headerTable.setWidthPercentage(100);
            float[] columnWidths = {1f, 2f, 1f};
            headerTable.setWidths(columnWidths);

            //Logo
            String path = System.getProperty("user.dir") + "/src/main/java/fabrica/gestiondeproducciones/img/magnolia-logo.jpg";
            Image logo = Image.getInstance(path);
            logo.scaleToFit(80, 80);
            PdfPCell cellLogo = new PdfPCell(logo);
            cellLogo.setBorder(Rectangle.NO_BORDER);
            headerTable.addCell(cellLogo);

            // Títulos centrales
            Font fontTitulo = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            PdfPCell cellTitulo = new PdfPCell(new Paragraph("Granja La Magnolia", fontTitulo));
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
            Paragraph subTitulo = new Paragraph("Flujo completo de Produccion", fontSubTitulo);
            subTitulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(subTitulo);
            documento.add(Chunk.NEWLINE);

            //</editor-fold>
            
            // <editor-fold defaultstate="collapsed" desc="PRODUCCION"> 
            PdfPTable tablaProduccion = new PdfPTable(2);
            tablaProduccion.setWidthPercentage(100);

            Object[] campos = obtenerProduccionPorCodigo(produccionSeleccionada.getIdProduccion(), produccionSeleccionada.getCodInterno());
            Paragraph subProd = new Paragraph("Produccion", fontSubTitulo);
            documento.add(subProd);
            documento.add(Chunk.SPACETABBING);
            for (Object campo : campos) {
                Object[] linea = (Object[]) campo;
                String titulo = (String) linea[0];
                String valor = linea[1]+"";
                PdfPCell cellCampo = new PdfPCell(new Phrase(titulo, new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
                cellCampo.setPadding(5);
                cellCampo.setBorderWidth(1.5f);
                tablaProduccion.addCell(cellCampo);

                PdfPCell cellValor = new PdfPCell(new Phrase(valor));
                cellValor.setPadding(5);
                cellValor.setBorderWidth(1.5f);
                tablaProduccion.addCell(cellValor);
            }

            documento.add(tablaProduccion);
            documento.add(Chunk.NEWLINE);

            //</editor-fold>
            
            // <editor-fold defaultstate="collapsed" desc="ANALISIS">                          
            Analisis analisis = controlador.buscarAnalisisXProduccion(produccionSeleccionada.getIdProduccion());

            if (analisis instanceof Analisis) {
                PdfPTable tablaAnalisis = new PdfPTable(2);
                tablaAnalisis.setWidthPercentage(100);
                Object[] objetoAnalisis = listadoAnalisis.obtenerAnalisis(analisis);

                Map<String, Object> datosAnalisis = new HashMap<>();
                String[] nombresCampos = {"Id", "Encargado", "Fecha", "Levadura", "Mohos", "Coliformes Totales", "Coliformes Fecales", "Grasa", "Proteína", "Agua", "Humedad", "Sal", "PH", "Tipo", "Analizado"};

                // Indices para excluir
                Set<Integer> indicesExcluidos = new HashSet<>();
                indicesExcluidos.add(4);
                indicesExcluidos.add(6);
                indicesExcluidos.add(8);

                for (int i = 0; i < nombresCampos.length; i++) {
                    if (!indicesExcluidos.contains(i) && objetoAnalisis[i] != null) {
                        datosAnalisis.put(nombresCampos[i], objetoAnalisis[i]);
                    }
                }

                Paragraph subAnalisis = new Paragraph("Analisis", fontSubTitulo);
                documento.add(subAnalisis);
                documento.add(Chunk.SPACETABBING);

                for (String campo : nombresCampos) {
                    if (datosAnalisis.containsKey(campo)) {
                        PdfPCell cellCampo = new PdfPCell(new Phrase(campo, new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
                        cellCampo.setPadding(5);
                        cellCampo.setBorderWidth(1.5f);
                        tablaAnalisis.addCell(cellCampo);

                        PdfPCell cellValor = new PdfPCell(new Phrase(datosAnalisis.get(campo).toString()));
                        cellValor.setPadding(5);
                        cellValor.setBorderWidth(1.5f);
                        tablaAnalisis.addCell(cellValor);
                    }
                }
                documento.add(tablaAnalisis);
            } else {
                Paragraph subAnalisis = new Paragraph("Esta producción aún no tiene un análisis.", fontSubTitulo);
                documento.add(subAnalisis);
                documento.add(Chunk.SPACETABBING);
            }
            documento.add(Chunk.NEWLINE);

            //</editor-fold>
            
            // <editor-fold defaultstate="collapsed" desc="LECHE">                          
            PdfPTable tablaLeche = new PdfPTable(2);
            tablaLeche.setWidthPercentage(100);

            Object[] pasteurizadaObj = produccionSeleccionada.getLechep().pasteurizadaToArray();
            Paragraph subLeche = new Paragraph("Leche", fontSubTitulo);
            documento.add(subLeche);
            documento.add(Chunk.SPACETABBING);

            for (Object campo : pasteurizadaObj) {
                Object[] linea = (Object[]) campo;
                String titulo = (String) linea[0];
                String valor = linea[1]+"";
                PdfPCell cellCampo = new PdfPCell(new Phrase(titulo, new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
                cellCampo.setPadding(5);
                cellCampo.setBorderWidth(1.5f);
                tablaLeche.addCell(cellCampo);

                PdfPCell cellValor = new PdfPCell(new Phrase(valor));
                cellValor.setPadding(5);
                cellValor.setBorderWidth(1.5f);
                tablaLeche.addCell(cellValor);
            }
            documento.add(tablaLeche);
            documento.add(Chunk.NEWLINE);

            // </editor-fold>
            documento.add(Chunk.NEWLINE);
            documento.close();

            JOptionPane.showMessageDialog(null, "El reporte se ha generado correctamente.");
            abrirPDF(ruta);
        } catch (DocumentException | FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error al generar el PDF: " + e.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(ListadoAnalisis.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnImprimirFlujoCompletoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnImprimirFlujoCompleto;
    private javax.swing.JButton btnImprimirListado;
    private javax.swing.JButton btnImprimirUnico;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel principal;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTable tablaProducciones;
    private javax.swing.JTextField txtCodigoInterno;
    private javax.swing.JTextField txtEncargado;
    private javax.swing.JTextField txtFechaFin;
    private javax.swing.JTextField txtFechaInicio;
    private javax.swing.JTextField txtProduccionSeleccionada;
    private javax.swing.JTextField txtProducto;
    // End of variables declaration//GEN-END:variables
}
