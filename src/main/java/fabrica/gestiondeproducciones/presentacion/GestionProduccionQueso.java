package fabrica.gestiondeproducciones.presentacion;

import fabrica.gestiondeproducciones.dominio.Controlador;
import fabrica.gestiondeproducciones.dominio.Empleado;
import fabrica.gestiondeproducciones.dominio.Insumo;
import fabrica.gestiondeproducciones.dominio.LechePasteurizada;
import fabrica.gestiondeproducciones.dominio.LineaInsumo;
import fabrica.gestiondeproducciones.dominio.ProduccionManteca;
import fabrica.gestiondeproducciones.dominio.ProduccionQueso;
import fabrica.gestiondeproducciones.dominio.ProduccionYogur;
import fabrica.gestiondeproducciones.dominio.Producto;
import fabrica.gestiondeproducciones.utilidades.Utilidades;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class GestionProduccionQueso extends javax.swing.JInternalFrame {

    Controlador controlador = new Controlador();
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableModel modeloEmpleadosAgregar = new DefaultTableModel();
    DefaultTableModel modeloEmpleadosTrabajaron = new DefaultTableModel();
    DefaultTableModel modeloInsumosAgregar = new DefaultTableModel();
    DefaultTableModel modeloInsumosUtilizados = new DefaultTableModel();
    Utilidades utilidad = new Utilidades();
    ProduccionQueso produccion = new ProduccionQueso();
    List<Empleado> listaEmpleados = new ArrayList<>();
    List<LineaInsumo> listaInsumosLinea = new ArrayList<>();
    int idEncargadoobtener;
    int idEmpleadoObtener;
    int idEncargado;
    int idEmpleado;
    int idInsumo;
    int idEmpleadoEliminar;
    int idInsumoEliminar;
    private TableRowSorter<TableModel> filtroFilaEmpleados;
    private TableRowSorter<TableModel> filtroFilaInsumos;
    int idProduccionObtenido;

    public GestionProduccionQueso() {
        initComponents();
        listar();
        listarLeche();
        listarProductos();
        listarAgregarEmpleado();
        listarAgregarInsumo();
        
        agregarFiltros(txtFiltroEmpleados, filtroFilaEmpleados);
        agregarFiltros(txtFiltroInsumos, filtroFilaInsumos);

    }
    
    // <editor-fold defaultstate="collapsed" desc="Funciones auxiliares">
    private void listar() {

        limpiarTabla();
        cargarFecha();
        List<ProduccionQueso> lista = controlador.listarProduccionesQueso();
  
        modelo = (DefaultTableModel) tablaProducciones.getModel();
        Object[] objeto = new Object[5];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getIdProduccion();
            objeto[1] = lista.get(i).getCodInterno();
            objeto[2] = lista.get(i).getFecha();
            objeto[3] = lista.get(i).getNroTacho();
            modelo.addRow(objeto);
        }
        tablaProducciones.setModel(modelo);
    }

    private void limpiarTabla() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = - 1;
        }
    }

    private void listarLeche() {
        cbxLeche.removeAllItems();
        List<LechePasteurizada> leche = controlador.listarPasteurizadosNoUsados();
        for (LechePasteurizada t : leche) {
            cbxLeche.addItem(t.getId() + " - Tambo de : " + t.getIngreso().getTambo().getPropietario() + " -Litros: "
                    + "" + t.getLitros()+"l");
        }
    }
private void listarLecheModificar(LechePasteurizada lechePast ) {
        cbxLeche.removeAllItems();
        List<LechePasteurizada> leche = controlador.listarPasteurizadosNoUsados();
        leche.add(lechePast);
        for (LechePasteurizada t : leche) {
            cbxLeche.addItem(t.getId() + " - Tambo de : " + t.getIngreso().getTambo().getPropietario() + " -Litros: "
                    + "" + t.getLitros()+"-L");
        }
    }
    private void listarProductos() {
        List<Producto> productos = controlador.listarProductos();
        for (Producto t : productos) {
            cbxProducto.addItem(t.getId() + " - " + t.getNombre());
        }
    }

    private void listarAgregarEmpleado() {
        limpiarTablaEmpleados();
        List<Empleado> lista = controlador.listarEmpleados();
        modeloEmpleadosAgregar = (DefaultTableModel) tablaAgregarEmpleados.getModel();
        Object[] objeto = new Object[3];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getId();
            objeto[1] = lista.get(i).getCi();
            objeto[2] = lista.get(i).getNombre();

            modeloEmpleadosAgregar.addRow(objeto);
        }
        tablaAgregarEmpleados.setModel(modeloEmpleadosAgregar);

        filtroFilaEmpleados = new TableRowSorter<>(modeloEmpleadosAgregar);
        tablaAgregarEmpleados.setRowSorter(filtroFilaEmpleados);
    }

    private void limpiarTablaEmpleados() {
        for (int i = 0; i < modeloEmpleadosAgregar.getRowCount(); i++) {
            modeloEmpleadosAgregar.removeRow(i);
            i = - 1;
        }
    }

    private void cargarFecha() {
        LocalDate fechaHoy = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fecha = fechaHoy.format(format);
        txtFecha.setText(fecha);
    }

    private void listarEmpleados(List<Empleado> lista) {
        limpiarTablaEmpleadosTrabajaron();
        
        modeloEmpleadosTrabajaron = (DefaultTableModel) tablaEmpleadosTrabajaron.getModel();
        Object[] objeto = new Object[3];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getId();
            objeto[1] = lista.get(i).getCi();
            objeto[2] = lista.get(i).getNombre();

            modeloEmpleadosTrabajaron.addRow(objeto);
        }
        tablaEmpleadosTrabajaron.setModel(modeloEmpleadosTrabajaron);
    }

    private void limpiarTablaEmpleadosTrabajaron() {
        for (int i = 0; i < modeloEmpleadosTrabajaron.getRowCount(); i++) {
            modeloEmpleadosTrabajaron.removeRow(i);
            i = - 1;
        }
    }

    private void listarAgregarInsumo() {
        limpiarTablaAgregarInsumos();
        List<Insumo> lista = controlador.listarInsumos();
        modeloInsumosAgregar = (DefaultTableModel) tablaAgregarInsumos.getModel();
        Object[] objeto = new Object[3];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getId();
            objeto[1] = lista.get(i).getNombre();
            objeto[2] = lista.get(i).getUnidad();

            modeloInsumosAgregar.addRow(objeto);
        }
        tablaAgregarInsumos.setModel(modeloInsumosAgregar);
        filtroFilaInsumos = new TableRowSorter<>(modeloInsumosAgregar);
        tablaAgregarInsumos.setRowSorter(filtroFilaInsumos);
    }

    private void limpiarTablaAgregarInsumos() {
        for (int i = 0; i < modeloInsumosAgregar.getRowCount(); i++) {
            modeloInsumosAgregar.removeRow(i);
            i = - 1;
        }
    }

    private void listarLineaInsumos(List<LineaInsumo> lista) {
        limpiarTablaInsumosUtilizados();

        modeloInsumosUtilizados = (DefaultTableModel) tablaInsumosAgregados.getModel();
        Object[] objeto = new Object[4];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getInsumo().getId();
            objeto[1] = lista.get(i).getInsumo().getNombre();
            objeto[2] = lista.get(i).getCantidad();
            objeto[3] = lista.get(i).getInsumo().getUnidad();
            
            modeloInsumosUtilizados.addRow(objeto);
        }
        tablaInsumosAgregados.setModel(modeloInsumosUtilizados);
    }

    private void limpiarTablaInsumosUtilizados() {
        for (int i = 0; i < modeloInsumosUtilizados.getRowCount(); i++) {
            modeloInsumosUtilizados.removeRow(i);
            i = - 1;
        }
    }
    
    private void seleccionarEnComboBox(String idBuscado, JComboBox comboBox){
        
        for (int i = 0; i < comboBox.getItemCount(); i++) {
            String item = comboBox.getItemAt(i).toString();
            String[] parts = item.split(" - ");
        
            if (parts[0].trim().equals(idBuscado)) {
                comboBox.setSelectedIndex(i);
                break;
            }
        }
    }

    
    private void limpiarFormulario() {
    
        this.txtCantidadInsumo.setText("");
        this.txtCodigoInterno.setText("");
        this.txtEncargado.setText("");
        this.txtHoraFin.setText("");
        this.txtHoraInicio.setText("");
        this.txtIRendimiento.setText("");
        this.txtId.setText("");
        this.txtNroTacho.setText("");
        this.txtTiempoTrabajado.setText("");
        this.txtObtenidos.setText("");
        this.txtTiempoReposoFermento.setText("");
        this.txtTempReposoFermento.setText("");
        this.txtTipoCuajoObtenido.setText("");
        this.txtCantidadCuajoObtenido.setText("");
        this.txtLitros.setText("");
        this.txtTempPastQueso.setText("");
        this.txtTempAlCuajar.setText("");
        this.txtSueroObtenido.setText("");
        this.txtTiempoAgregadoAgua.setText("");
        this.txtxTempAgua.setText("");
        this.txtTempCuajoFinal.setText("");
        this.txtTipoGrano.setText("");
        this.txtTiempoCuajado.setText("");
        this.txtUnidadesObtenidas.setText("");
        this.txtAcidesFermento.setText("");
        idEncargado = -1;
        idEncargadoobtener = -1;
        idEmpleado = -1;
        idEmpleadoObtener = -1;
        idInsumo = -1;
        limpiarTablaEmpleadosTrabajaron();
        limpiarTablaInsumosUtilizados();
        listarLeche();
        listaEmpleados.clear();
        listaInsumosLinea.clear();

    }

    private void agregarFiltros(javax.swing.JTextField campo, TableRowSorter fila) {
        campo.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                applyFilter(campo, fila);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                applyFilter(campo, fila);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                applyFilter(campo, fila);
            }
        });

    }

    private void applyFilter(javax.swing.JTextField campo, TableRowSorter fila) {
        RowFilter<TableModel, Object> rf;

        // Si el filtro está vacío, mostrar todas las filas
        if (campo.getText().length() == 0) {
            rf = RowFilter.regexFilter(".*");
        } else {
            // Usar el texto del JTextField como filtro
            rf = RowFilter.regexFilter("(?i)" + campo.getText());
        }
        fila.setRowFilter(rf);
    }
    
   
    
   
    // </editor-fold>  

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelScroll = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        txtCodigoInterno = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        cbxLeche = new javax.swing.JComboBox<>();
        jLabel44 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        txtIRendimiento = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        txtObtenidos = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        txtEncargado = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        txtHoraInicio = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        txtHoraFin = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        txtTiempoTrabajado = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        txtNroTacho = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        txtTiempoReposoFermento = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        txtTempReposoFermento = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        txtTipoCuajoObtenido = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        txtCantidadCuajoObtenido = new javax.swing.JTextField();
        txtLitros = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tablaProducciones = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaEmpleadosTrabajaron = new javax.swing.JTable();
        jLabel29 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        tablaAgregarEmpleados = new javax.swing.JTable();
        btnEliminarEmpleado = new javax.swing.JToggleButton();
        jPanel5 = new javax.swing.JPanel();
        btnAgregarEmpleado = new javax.swing.JButton();
        btnSeleccionarEncargado = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtFiltroEmpleados = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tablaInsumosAgregados = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tablaAgregarInsumos = new javax.swing.JTable();
        jLabel30 = new javax.swing.JLabel();
        btnEliminarInsumo = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtFiltroInsumos = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        txtCantidadInsumo = new javax.swing.JTextField();
        btnAgregarInsumo = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        btnAlta = new javax.swing.JButton();
        btnBaja = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        txtTiempoCuajado = new javax.swing.JTextField();
        txtSueroObtenido = new javax.swing.JTextField();
        txtTiempoAgregadoAgua = new javax.swing.JTextField();
        txtTipoGrano = new javax.swing.JTextField();
        txtTempAlCuajar = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        txtTempPastQueso = new javax.swing.JTextField();
        txtxTempAgua = new javax.swing.JTextField();
        cbxProducto = new javax.swing.JComboBox<>();
        jLabel64 = new javax.swing.JLabel();
        txtTempCuajoFinal = new javax.swing.JTextField();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        txtAcidesFermento = new javax.swing.JTextField();
        txtUnidadesObtenidas = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Produccion de Manteca");
        setToolTipText("");
        setMaximumSize(new java.awt.Dimension(2100000000, 2100000000));
        setPreferredSize(new java.awt.Dimension(1800, 1000));

        jPanel1.setMinimumSize(getPreferredSize());

        jLabel1.setText("Id:");

        txtId.setEditable(false);

        jLabel41.setText("Codigo Interno:");

        txtCodigoInterno.setEditable(false);

        jLabel42.setText("Leche Pasteurizada:");

        jLabel44.setText("Producto:");

        jLabel43.setText("Rendimiento :    %");

        txtIRendimiento.setEditable(false);

        jLabel45.setText("Kg/L Obtenidos:");

        txtObtenidos.setToolTipText("");

        jLabel50.setText("Fecha:");

        txtFecha.setToolTipText("");

        jLabel46.setText("Encargado:");

        txtEncargado.setEditable(false);
        txtEncargado.setToolTipText("");

        jLabel47.setText("Hora Inicio Produccion:");

        txtHoraInicio.setToolTipText("");

        jLabel48.setText("Hora Fin:");

        txtHoraFin.setToolTipText("");

        jLabel54.setText("Tiempo Trabajado:");

        txtTiempoTrabajado.setEditable(false);

        jLabel49.setText("Nro de Tacho:");

        txtNroTacho.setToolTipText("");

        jLabel51.setText("Tiempo Reposo Fermento:");

        txtTiempoReposoFermento.setToolTipText("");

        jLabel52.setText("Temp Reposo Fermento:");

        txtTempReposoFermento.setToolTipText("");

        jLabel55.setText("Tipo Cuajo Obtenido:");

        txtTipoCuajoObtenido.setToolTipText("");

        jLabel53.setText("Litros Suero Obtenido:");

        txtCantidadCuajoObtenido.setToolTipText("");
        txtCantidadCuajoObtenido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadCuajoObtenidoActionPerformed(evt);
            }
        });

        txtLitros.setEditable(false);
        txtLitros.setToolTipText("");

        jLabel4.setText("Litros Leche:");

        tablaProducciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Codigo Interno", "Fecha", "Nº Tacho"
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
        jScrollPane8.setViewportView(tablaProducciones);
        tablaProducciones.getAccessibleContext().setAccessibleParent(this);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setText("Empleados Que Trabajaron");

        tablaEmpleadosTrabajaron.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "CI", "Nombre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
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
        tablaEmpleadosTrabajaron.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaEmpleadosTrabajaronMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tablaEmpleadosTrabajaron);

        jLabel29.setText("Agregar Empleados/ Seleccion de Encargado");

        tablaAgregarEmpleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "CI", "Nombre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaAgregarEmpleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaAgregarEmpleadosMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(tablaAgregarEmpleados);

        btnEliminarEmpleado.setText("Eliminar empleado");
        btnEliminarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarEmpleadoActionPerformed(evt);
            }
        });

        btnAgregarEmpleado.setText("Agregar Empleado");
        btnAgregarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarEmpleadoActionPerformed(evt);
            }
        });

        btnSeleccionarEncargado.setText("Seleccionar Encargado");
        btnSeleccionarEncargado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarEncargadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btnAgregarEmpleado)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnSeleccionarEncargado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAgregarEmpleado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSeleccionarEncargado)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jLabel2.setText("Filtrar empleado");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFiltroEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFiltroEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(jLabel7))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminarEmpleado)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addGap(68, 68, 68)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(95, 95, 95))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel29))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(btnEliminarEmpleado)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tablaInsumosAgregados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Insumo", "Nombre", "Cantidad", "Unidad Medida"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaInsumosAgregados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaInsumosAgregadosMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tablaInsumosAgregados);

        jLabel8.setText("Insumos Utilizados");

        tablaAgregarInsumos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "idInsumo", "Nombre", "Unidad Medida"
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
        tablaAgregarInsumos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaAgregarInsumosMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tablaAgregarInsumos);

        jLabel30.setText("Agregar Insumos");

        btnEliminarInsumo.setText("Eliminar insumo");
        btnEliminarInsumo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarInsumoActionPerformed(evt);
            }
        });

        jLabel3.setText("Filtrar insumos");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtFiltroInsumos, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel3)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFiltroInsumos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        txtCantidadInsumo.setToolTipText("");

        btnAgregarInsumo.setText("Agregar Insumo");
        btnAgregarInsumo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarInsumoActionPerformed(evt);
            }
        });

        jLabel31.setText("Cantidad Utilizada");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAgregarInsumo, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCantidadInsumo, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel31)))
                .addGap(35, 35, 35))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel31)
                .addGap(2, 2, 2)
                .addComponent(txtCantidadInsumo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAgregarInsumo)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 163, Short.MAX_VALUE)
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
                .addGap(40, 40, 40)
                .addComponent(btnAlta)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLimpiar)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnBaja)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnModificar)))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnLimpiar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAlta)
                    .addComponent(btnBaja)
                    .addComponent(btnModificar))
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel30)
                .addGap(415, 415, 415))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminarInsumo, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel30))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(btnEliminarInsumo))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel56.setText("Cantidad Cuajo Obtenido:");

        jLabel57.setText("Tipo Grano Obtenido:");

        jLabel58.setText("Tiempo Agregado Agua:");

        jLabel59.setText("Tiempo Cuajado:");

        jLabel60.setText("Temperatura al Cuajar:");

        txtTiempoCuajado.setToolTipText("");
        txtTiempoCuajado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTiempoCuajadoActionPerformed(evt);
            }
        });

        txtSueroObtenido.setToolTipText("");
        txtSueroObtenido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSueroObtenidoActionPerformed(evt);
            }
        });

        txtTiempoAgregadoAgua.setToolTipText("");
        txtTiempoAgregadoAgua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTiempoAgregadoAguaActionPerformed(evt);
            }
        });

        txtTipoGrano.setToolTipText("");
        txtTipoGrano.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTipoGranoActionPerformed(evt);
            }
        });

        txtTempAlCuajar.setToolTipText("");
        txtTempAlCuajar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTempAlCuajarActionPerformed(evt);
            }
        });

        jLabel62.setText("Temp Past Queso:");

        jLabel63.setText("Temp Cuajo Final:");

        txtTempPastQueso.setToolTipText("");
        txtTempPastQueso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTempPastQuesoActionPerformed(evt);
            }
        });

        txtxTempAgua.setToolTipText("");
        txtxTempAgua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtxTempAguaActionPerformed(evt);
            }
        });

        jLabel64.setText("Temp  Agua:");

        txtTempCuajoFinal.setToolTipText("");
        txtTempCuajoFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTempCuajoFinalActionPerformed(evt);
            }
        });

        jLabel65.setText("Unidades Obtenidas:");

        jLabel66.setText("Acides Fermento:");

        txtAcidesFermento.setToolTipText("");
        txtAcidesFermento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAcidesFermentoActionPerformed(evt);
            }
        });

        txtUnidadesObtenidas.setToolTipText("");
        txtUnidadesObtenidas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUnidadesObtenidasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 1389, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtUnidadesObtenidas, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel66)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtAcidesFermento, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel44)
                                    .addComponent(jLabel43)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtLitros, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtIRendimiento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(cbxProducto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel42)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbxLeche, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel41)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCodigoInterno, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtId, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel50)
                                    .addComponent(jLabel46)
                                    .addComponent(jLabel47)
                                    .addComponent(jLabel48)
                                    .addComponent(jLabel54)
                                    .addComponent(jLabel49)
                                    .addComponent(jLabel45))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtObtenidos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtFecha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEncargado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtHoraInicio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtHoraFin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTiempoTrabajado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNroTacho, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel63)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTempCuajoFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel65, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel62)
                                                .addGap(81, 81, 81))
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addComponent(jLabel60)
                                                    .addGap(12, 12, 12))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel52)
                                                    .addComponent(jLabel51)
                                                    .addComponent(jLabel55)
                                                    .addComponent(jLabel59)
                                                    .addComponent(jLabel56)
                                                    .addComponent(jLabel57)
                                                    .addComponent(jLabel53)
                                                    .addComponent(jLabel58)))
                                            .addComponent(jLabel64))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtTempPastQueso, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtTiempoReposoFermento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtTempReposoFermento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtTipoCuajoObtenido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtCantidadCuajoObtenido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtSueroObtenido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtTiempoCuajado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtTiempoAgregadoAgua, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtTipoGrano, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtxTempAgua, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtTempAlCuajar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1043, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(448, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel41)
                            .addComponent(txtCodigoInterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxLeche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtLitros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIRendimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtObtenidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEncargado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtHoraInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtHoraFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTiempoTrabajado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNroTacho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTempPastQueso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTiempoReposoFermento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTempReposoFermento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTipoCuajoObtenido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTiempoCuajado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTempAlCuajar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCantidadCuajoObtenido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTipoGrano, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSueroObtenido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTiempoAgregadoAgua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtxTempAgua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTempCuajoFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUnidadesObtenidas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAcidesFermento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
        );

        PanelScroll.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 1776, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PanelScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 1285, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void tablaProduccionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProduccionesMouseClicked
        int fila = tablaProducciones.rowAtPoint(evt.getPoint());
        int id = Integer.parseInt(tablaProducciones.getValueAt(fila, 0).toString());
        idProduccionObtenido=id;
        
        ProduccionQueso prod = controlador.buscarProduccionQueso(id);
        txtId.setText(tablaProducciones.getValueAt(fila, 0).toString());
        txtCodigoInterno.setText(prod.getCodInterno());
        if (prod.getLechep() instanceof LechePasteurizada) {
            listarLecheModificar(prod.getLechep());
            seleccionarEnComboBox(prod.getLechep().getId()+"",cbxLeche);            
        }
        if (prod.getProducto() instanceof Producto) {
            
            seleccionarEnComboBox(prod.getProducto().getId()+"",cbxProducto);            
        }
        txtIRendimiento.setText(""+prod.getRendimiento());
        txtLitros.setText(prod.getLitros() + "");
        
        txtObtenidos.setText(prod.getKgLtsObt() + "");
        txtFecha.setText(prod.getFecha());
        if (prod.getEncargado() instanceof Empleado) {
            txtEncargado.setText(prod.encargadoToString());
            idEncargado = prod.getEncargado().getId();
        }
        
       
       
        this.txtHoraFin.setText(prod.getHoraFin());
        this.txtHoraInicio.setText(prod.getHoraInicio());
        this.txtNroTacho.setText(prod.getNroTacho()+"");
        this.txtTiempoTrabajado.setText(prod.getTiempoTrabajado());
        this.txtTiempoReposoFermento.setText(""+prod.getTiempoReposoFermento());
        this.txtTempReposoFermento.setText(prod.getTempReposoFermento()+"");
        this.txtTipoCuajoObtenido.setText(prod.getTipoCuajoObtenido());
        this.txtCantidadCuajoObtenido.setText(prod.getCantCuajoObtenido()+"");
        this.txtTempPastQueso.setText(""+prod.getTempPastQueso());
        this.txtTempAlCuajar.setText(prod.getTempAlCuajar()+"");
        this.txtSueroObtenido.setText(""+prod.getLitrosSueroObtenidos());
        this.txtTiempoAgregadoAgua.setText(""+prod.getTiempoAgregadoAgua());
        this.txtxTempAgua.setText(""+prod.getTempAgua());
        this.txtTempCuajoFinal.setText(""+prod.getTempCuajoFinal());
        this.txtTiempoCuajado.setText(prod.getTiempoCuajado());
        this.txtTipoGrano.setText(prod.getTipoDeGrano());
        this.txtUnidadesObtenidas.setText(prod.getUnidadesObtenidas()+"");
        this.txtAcidesFermento.setText(prod.getAcidesFermento()+"");
        listaEmpleados = prod.getListaEmpleados();
        listaInsumosLinea = prod.getListaInsumos();       
        
        listarEmpleados(prod.getListaEmpleados());
        listarLineaInsumos(prod.getListaInsumos());
    }//GEN-LAST:event_tablaProduccionesMouseClicked

    private void btnAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAltaActionPerformed
        try {
            String fecha = utilidad.controlarFechas(txtFecha.getText());

            String horaInicio = utilidad.validarHora(txtHoraInicio.getText(), "Hora de Inicio");
            String horaFin = utilidad.validarHora(txtHoraFin.getText(), "Hora de Finalizacion");
            String TiempoTrabajado = utilidad.calcularDiferenciaHoras(horaInicio, horaFin);
            int nroTacho = utilidad.validarNumericos(txtNroTacho.getText(), "Numero de Tacho", false);
            String[] partesFecha = txtFecha.getText().split("/");
            String CodigoInterno = "Q" + partesFecha[0] + partesFecha[1] + partesFecha[2] + txtTempCuajoFinal.getText() + txtNroTacho.getText();
            int kgObtenidos=utilidad.validarNumericos(txtObtenidos.getText(),"Kg Obtenidos",false);
            
            String tiempoReposoFermento=utilidad.validarHora(txtTiempoReposoFermento.getText(),"Tiempo de Reposo del Fermento"); 
            int tempPastQueso=utilidad.validarNumericos(txtTempPastQueso.getText(), "Temperatura de Pasteurizado del queso",false);
            int tempReposoFermento= utilidad.validarNumericos(txtTempReposoFermento.getText(), "Temperatura Reposo Fermento", false);
            String tipoCuajo = utilidad.sanitizarCampos(txtTipoCuajoObtenido.getText(), "Tipo de Cuajo Obtenido",false);
            String tiempoCuajado=utilidad.validarHora(txtTiempoCuajado.getText(),"Tiempo de Cuajado");
            int tempAlCuajar=utilidad.validarNumericos(txtTempAlCuajar.getText(),"Temperatura al Cuajar", false);
            int cantCuajoObtenido=utilidad.validarNumericos(txtCantidadCuajoObtenido.getText(),"Cantidad de Cuajo Obtenido",false);
            String tipoGrano= utilidad.sanitizarCampos(txtTipoGrano.getText(),"Tipo de Grano Obtenido",false);
            int sueroObtenido=utilidad.validarNumericos(txtSueroObtenido.getText(), "Cantidad de Suero Obtenido",false);
            String tiempoAgregadoAgua=utilidad.validarHora(txtTiempoAgregadoAgua.getText(),"Tiempo de Agregado de Agua");
            int tempAgua=utilidad.validarNumericos(txtxTempAgua.getText(),"Temperatura de Agua", false);
            int tempCuajoFinal=utilidad.validarNumericos(txtTempCuajoFinal.getText(),"Unidades Obtenidas",false);
            int unidadesObtenidas= utilidad.validarNumericos(txtUnidadesObtenidas.getText(),"Unidades Obtenidas",false);
            int acidesFermento= utilidad.validarNumericos(txtAcidesFermento.getText(),"Acides de Fermento",false);
          

            utilidad.validarHoraNoMayor(horaInicio, horaFin, "Hora de Inicio y Hora de Fin", "Inicio de Produccion ", "Fin de Produccion");
            
            try {
                Empleado empleado = controlador.buscarEmpleado(idEncargado);
                
                if (empleado instanceof Empleado) {
                    produccion.setEncargado(empleado);
                } else {
                    throw new Exception("Debe seleccionar un Encargado");
                }
            } catch (Exception e) {
                throw new Exception("Debe buscar un encargado valido primero, por favor verifique");
            }

            String[] partes = cbxLeche.getSelectedItem().toString().split(" - ");
            LechePasteurizada lechep = controlador.buscarPasteurizado(Integer.parseInt(partes[0]));
            int litros =  lechep.getLitros();
           
            if (lechep instanceof LechePasteurizada) {

                produccion.setLitros(litros);
            } else {
                throw new Exception("El Pasteurizado seleccionado ya no esta disponible");
            }
            
            if(acidesFermento<14&&acidesFermento>0){
                    System.out.println("ACIDES  "+acidesFermento);
                    produccion.setAcidesFermento(acidesFermento);
            }
            else{
                throw new Exception("El valor de acides del fermento debe de estar contenido entre 0.0 y 14.0");
            }
            
            if(kgObtenidos>litros){
                throw new Exception("Los Kg obtenidos no pueden ser mayor a la cantidad de materia prima utilizada(Litros de Leche)");
            }
           
            
            float rendimiento = ((float) kgObtenidos / (litros)) * 100; // Usa división en coma flotante
            
            String[] partes2 = cbxProducto.getSelectedItem().toString().split(" - ");
            Producto producto = controlador.buscarProducto(Integer.parseInt(partes2[0]));
            produccion.setCodInterno(CodigoInterno);
            produccion.setListaInsumos(listaInsumosLinea);
            produccion.setListaEmpleados(listaEmpleados);
            produccion.setLechep(lechep);
            produccion.setProducto(producto);
            produccion.setRendimiento(rendimiento);
            produccion.setKgLtsObt(kgObtenidos);
            produccion.setFecha(fecha);
            produccion.setHoraInicio(horaInicio);
            produccion.setHoraFin(horaFin);
            produccion.setTiempoTrabajado(TiempoTrabajado);
            produccion.setNroTacho(nroTacho);
            
            produccion.setTempPastQueso(tempPastQueso);
            produccion.setTiempoReposoFermento(tiempoReposoFermento);
            produccion.setTempReposoFermento(tempReposoFermento);
            produccion.setTipoCuajoObtenido(tipoCuajo);
            produccion.setTiempoCuajado(tiempoCuajado);
            produccion.setTempAlCuajar(tempAlCuajar);
            produccion.setCantCuajoObtenido(cantCuajoObtenido);
            produccion.setTipoDeGrano(tipoGrano);
            produccion.setLitrosSueroObtenidos(sueroObtenido);
            produccion.setTiempoAgregadoAgua(tiempoAgregadoAgua);
            produccion.setTempAgua(tempAgua);
            produccion.setTempCuajoFinal(tempCuajoFinal);
            produccion.setUnidadesObtenidas(unidadesObtenidas);
        
           
            
            
            

            boolean alta = controlador.altaProduccionQueso(produccion);
            if (alta) {

                JOptionPane.showMessageDialog(null, "Produccion dada de alta.");
                
                limpiarFormulario();
                listar();
                listarLeche();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Advertencia", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_btnAltaActionPerformed

    private void btnAgregarInsumoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarInsumoActionPerformed
        try {
            Insumo insumo = controlador.buscarInsumo(idInsumo);
            int cantidad = Integer.parseInt(txtCantidadInsumo.getText());

            LineaInsumo insumoLinea = new LineaInsumo(insumo, cantidad);
            if (insumo instanceof Insumo && insumoLinea instanceof LineaInsumo) {
                if (!listaInsumosLinea.contains(insumoLinea)) {
                    listaInsumosLinea.add(insumoLinea);
                } else {
                    throw new Exception("El insumo ya se encuentra agregado a la lista, puede modificar su cantidad desde la tabla");
                }
                listarLineaInsumos(listaInsumosLinea);
            } else {
                throw new Exception("Debe seleccionar un Insumo de la lista");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnAgregarInsumoActionPerformed

    private void tablaInsumosAgregadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaInsumosAgregadosMouseClicked
        int fila = tablaInsumosAgregados.rowAtPoint(evt.getPoint());
        idInsumoEliminar = (int) tablaInsumosAgregados.getValueAt(fila, 0);
    }//GEN-LAST:event_tablaInsumosAgregadosMouseClicked

    private void tablaAgregarInsumosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAgregarInsumosMouseClicked
        int fila = tablaAgregarInsumos.rowAtPoint(evt.getPoint());
        idInsumo = (int) tablaAgregarInsumos.getValueAt(fila, 0);

    }//GEN-LAST:event_tablaAgregarInsumosMouseClicked

    private void btnEliminarInsumoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarInsumoActionPerformed
        try {
            idInsumo = idInsumoEliminar;
            Insumo insumo = controlador.buscarInsumo(idInsumo);
            LineaInsumo lineaInsumo = new LineaInsumo();
            lineaInsumo.setInsumo(insumo);
            if (listaInsumosLinea.contains(lineaInsumo)) {
                int respuesta = JOptionPane.showInternalConfirmDialog(null, "Desea eliminar " + insumo.getNombre() + " de la lista?");
                if (respuesta == JOptionPane.YES_OPTION) {
                    listaInsumosLinea.remove(lineaInsumo);
                }
            } else {
                throw new Exception("Debe seleccionar un insumo a eliminar");
            }
            listarLineaInsumos(listaInsumosLinea);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarInsumoActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarFormulario();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBajaActionPerformed
        try {
            int id = utilidad.validarNumericos(txtId.getText(), "Id", false);
            boolean baja = controlador.bajaProduccion(id, "produccion_queso");
            if (baja) {
                JOptionPane.showMessageDialog(null, "Produccion dada de baja.");
                limpiarFormulario();
                listar();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnBajaActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        try{   
            int id= Integer.parseInt(txtId.getText());
            String fecha = utilidad.controlarFechas(txtFecha.getText());

            String horaInicio = utilidad.validarHora(txtHoraInicio.getText(), "Hora de Inicio");
            String horaFin = utilidad.validarHora(txtHoraFin.getText(), "Hora de Finalizacion");
            String TiempoTrabajado = utilidad.calcularDiferenciaHoras(horaInicio, horaFin);
            int nroTacho = utilidad.validarNumericos(txtNroTacho.getText(), "Numero de Tacho", false);
            String[] partesFecha = txtFecha.getText().split("/");
            String CodigoInterno = "Q" + partesFecha[0] + partesFecha[1] + partesFecha[2] + txtTempCuajoFinal.getText() + txtNroTacho.getText();
            int kgObtenidos=utilidad.validarNumericos(txtObtenidos.getText(),"Kg Obtenidos",false);
            
            String tiempoReposoFermento=utilidad.validarHora(txtTiempoReposoFermento.getText(),"Tiempo de Reposo del Fermento"); 
            int tempPastQueso=utilidad.validarNumericos(txtTempPastQueso.getText(), "Temperatura de Pasteurizado del queso",false);
            int tempReposoFermento= utilidad.validarNumericos(txtTempReposoFermento.getText(), "Temperatura Reposo Fermento", false);
            String tipoCuajo = utilidad.sanitizarCampos(txtTipoCuajoObtenido.getText(), "Tipo de Cuajo Obtenido",false);
            String tiempoCuajado=utilidad.validarHora(txtTiempoCuajado.getText(),"Tiempo de Cuajado");
            int tempAlCuajar=utilidad.validarNumericos(txtTempAlCuajar.getText(),"Temperatura al Cuajar", false);
            int cantCuajoObtenido=utilidad.validarNumericos(txtCantidadCuajoObtenido.getText(),"Cantidad de Cuajo Obtenido",false);
            String tipoGrano= utilidad.sanitizarCampos(txtTipoGrano.getText(),"Tipo de Grano Obtenido",false);
            int sueroObtenido=utilidad.validarNumericos(txtSueroObtenido.getText(), "Cantidad de Suero Obtenido",false);
            String tiempoAgregadoAgua=utilidad.validarHora(txtTiempoAgregadoAgua.getText(),"Tiempo de Agregado de Agua");
            int tempAgua=utilidad.validarNumericos(txtxTempAgua.getText(),"Temperatura de Agua", false);
            int tempCuajoFinal=utilidad.validarNumericos(txtTempCuajoFinal.getText(),"Unidades Obtenidas",false);
            int unidadesObtenidas= utilidad.validarNumericos(txtUnidadesObtenidas.getText(),"Unidades Obtenidas",false);
            int acidesFermento= utilidad.validarNumericos(txtAcidesFermento.getText(),"Acides de Fermento",false);
            
            
            Controlador c= new Controlador();
            ProduccionQueso produccion=c.buscarProduccionQueso(id);
            
            try {
                Empleado empleado = controlador.buscarEmpleado(idEncargado);
                
                if (empleado instanceof Empleado) {
                    produccion.setEncargado(empleado);
                } else {
                    throw new Exception("Debe seleccionar un Encargado");
                }
            } catch (Exception e) {
                throw new Exception("Debe buscar un encargado valido primero, por favor verifique");
            }

            String[] partes = cbxLeche.getSelectedItem().toString().split(" - ");
            LechePasteurizada lechep = controlador.buscarPasteurizado(Integer.parseInt(partes[0]));
            int litros =  lechep.getLitros();
           
            if (lechep instanceof LechePasteurizada) {

                produccion.setLitros(litros);
            } else {
                throw new Exception("El Pasteurizado seleccionado ya no esta disponible");
            }
             if(acidesFermento<14&&acidesFermento>0){
                    System.out.println("ACIDES  "+acidesFermento);
                    produccion.setAcidesFermento(acidesFermento);
            }
            else{
                throw new Exception("El valor de acides del fermento debe de estar contenido entre 0.0 y 14.0");
            }
            
            if(kgObtenidos>litros){
                throw new Exception("Los Kg obtenidos no pueden ser mayor a la cantidad de materia prima utilizada(Litros de Leche)");
            }
           
            
            float rendimiento = ((float) kgObtenidos / (litros)) * 100; // Usa división en coma flotante
            String[] partes2 = cbxProducto.getSelectedItem().toString().split(" - ");
            Producto producto = controlador.buscarProducto(Integer.parseInt(partes2[0]));
           produccion.setCodInterno(CodigoInterno);
            produccion.setListaInsumos(listaInsumosLinea);
            produccion.setListaEmpleados(listaEmpleados);
            produccion.setLechep(lechep);
            produccion.setProducto(producto);
            produccion.setRendimiento(rendimiento);
            produccion.setKgLtsObt(kgObtenidos);
            produccion.setFecha(fecha);
            produccion.setHoraInicio(horaInicio);
            produccion.setHoraFin(horaFin);
            produccion.setTiempoTrabajado(TiempoTrabajado);
            produccion.setNroTacho(nroTacho);
            
            produccion.setTempPastQueso(tempPastQueso);
            produccion.setTiempoReposoFermento(tiempoReposoFermento);
            produccion.setTempReposoFermento(tempReposoFermento);
            produccion.setTipoCuajoObtenido(tipoCuajo);
            produccion.setTiempoCuajado(tiempoCuajado);
            produccion.setTempAlCuajar(tempAlCuajar);
            produccion.setCantCuajoObtenido(cantCuajoObtenido);
            produccion.setTipoDeGrano(tipoGrano);
            produccion.setLitrosSueroObtenidos(sueroObtenido);
            produccion.setTiempoAgregadoAgua(tiempoAgregadoAgua);
            produccion.setTempAgua(tempAgua);
            produccion.setTempCuajoFinal(tempCuajoFinal);
            produccion.setUnidadesObtenidas(unidadesObtenidas);
            produccion.setAcidesFermento(acidesFermento);
            
            

            boolean modificar = controlador.modificarProduccionQueso(produccion);
            if (modificar) {
               
                JOptionPane.showMessageDialog(null, "Produccion Modificada Correctamente.");
                
                limpiarFormulario();
                listar();
                listarLeche();
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnSeleccionarEncargadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarEncargadoActionPerformed

        idEncargado = idEncargadoobtener;
        Empleado empleado = controlador.buscarEmpleado(idEncargado);
        txtEncargado.setText(empleado.getId() + "-" + empleado.getNombre());
    }//GEN-LAST:event_btnSeleccionarEncargadoActionPerformed

    private void btnAgregarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarEmpleadoActionPerformed
        try {
            idEmpleado = idEmpleadoObtener;
            Empleado unEmpleado = controlador.buscarEmpleado(idEmpleado);
            if (unEmpleado instanceof Empleado) {
                if (!listaEmpleados.contains(unEmpleado)) {
                    listaEmpleados.add(unEmpleado);
                } else {
                    throw new Exception("El Empleado ya se encuentra agregado a la lista");
                }
                listarEmpleados(listaEmpleados);
            } else {
                throw new Exception("Debe seleccionar un empleado de la lista");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnAgregarEmpleadoActionPerformed

    private void btnEliminarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarEmpleadoActionPerformed
        try {
            idEmpleado = idEmpleadoEliminar;
            Empleado empleadoEliminar = controlador.buscarEmpleado(idEmpleado);
            if (listaEmpleados.contains(empleadoEliminar)) {
                int respuesta = JOptionPane.showInternalConfirmDialog(null, "Desea eliminar a " + empleadoEliminar.getNombreCompleto() + " de la lista?");
                if (respuesta == JOptionPane.YES_OPTION) {
                    listaEmpleados.remove(empleadoEliminar);
                }
            } else {
                throw new Exception("Debe seleccionar un empleado a eliminar");
            }
            listarEmpleados(listaEmpleados);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarEmpleadoActionPerformed

    private void tablaAgregarEmpleadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAgregarEmpleadosMouseClicked

        int fila = tablaAgregarEmpleados.rowAtPoint(evt.getPoint());
        idEncargadoobtener = (int) tablaAgregarEmpleados.getValueAt(fila, 0);
        idEmpleadoObtener = (int) tablaAgregarEmpleados.getValueAt(fila, 0);
    }//GEN-LAST:event_tablaAgregarEmpleadosMouseClicked

    private void tablaEmpleadosTrabajaronMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaEmpleadosTrabajaronMouseClicked
        int fila = tablaEmpleadosTrabajaron.rowAtPoint(evt.getPoint());
        idEmpleadoEliminar = (int) tablaEmpleadosTrabajaron.getValueAt(fila, 0);
    }//GEN-LAST:event_tablaEmpleadosTrabajaronMouseClicked

    private void txtCantidadCuajoObtenidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadCuajoObtenidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadCuajoObtenidoActionPerformed

    private void txtTiempoCuajadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTiempoCuajadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTiempoCuajadoActionPerformed

    private void txtSueroObtenidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSueroObtenidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSueroObtenidoActionPerformed

    private void txtTiempoAgregadoAguaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTiempoAgregadoAguaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTiempoAgregadoAguaActionPerformed

    private void txtTipoGranoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTipoGranoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTipoGranoActionPerformed

    private void txtTempAlCuajarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTempAlCuajarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTempAlCuajarActionPerformed

    private void txtTempPastQuesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTempPastQuesoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTempPastQuesoActionPerformed

    private void txtxTempAguaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtxTempAguaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtxTempAguaActionPerformed

    private void txtTempCuajoFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTempCuajoFinalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTempCuajoFinalActionPerformed

    private void txtAcidesFermentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAcidesFermentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAcidesFermentoActionPerformed

    private void txtUnidadesObtenidasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUnidadesObtenidasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUnidadesObtenidasActionPerformed

   /* private void actualizarLitros(LechePasteurizada lecheP, int litrosUtilizados) {
        int litrosDisponibles = lecheP.getCremaDisponible();
        lecheP.setCremaDisponible(litrosDisponibles - litrosUtilizados);
        controlador.modificarPasteurizado(lecheP);
    }*/
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane PanelScroll;
    private javax.swing.JButton btnAgregarEmpleado;
    private javax.swing.JButton btnAgregarInsumo;
    private javax.swing.JButton btnAlta;
    private javax.swing.JButton btnBaja;
    private javax.swing.JToggleButton btnEliminarEmpleado;
    private javax.swing.JButton btnEliminarInsumo;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSeleccionarEncargado;
    private javax.swing.JComboBox<String> cbxLeche;
    private javax.swing.JComboBox<String> cbxProducto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable tablaAgregarEmpleados;
    private javax.swing.JTable tablaAgregarInsumos;
    private javax.swing.JTable tablaEmpleadosTrabajaron;
    private javax.swing.JTable tablaInsumosAgregados;
    private javax.swing.JTable tablaProducciones;
    private javax.swing.JTextField txtAcidesFermento;
    private javax.swing.JTextField txtCantidadCuajoObtenido;
    private javax.swing.JTextField txtCantidadInsumo;
    private javax.swing.JTextField txtCodigoInterno;
    private javax.swing.JTextField txtEncargado;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtFiltroEmpleados;
    private javax.swing.JTextField txtFiltroInsumos;
    private javax.swing.JTextField txtHoraFin;
    private javax.swing.JTextField txtHoraInicio;
    private javax.swing.JTextField txtIRendimiento;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtLitros;
    private javax.swing.JTextField txtNroTacho;
    private javax.swing.JTextField txtObtenidos;
    private javax.swing.JTextField txtSueroObtenido;
    private javax.swing.JTextField txtTempAlCuajar;
    private javax.swing.JTextField txtTempCuajoFinal;
    private javax.swing.JTextField txtTempPastQueso;
    private javax.swing.JTextField txtTempReposoFermento;
    private javax.swing.JTextField txtTiempoAgregadoAgua;
    private javax.swing.JTextField txtTiempoCuajado;
    private javax.swing.JTextField txtTiempoReposoFermento;
    private javax.swing.JTextField txtTiempoTrabajado;
    private javax.swing.JTextField txtTipoCuajoObtenido;
    private javax.swing.JTextField txtTipoGrano;
    private javax.swing.JTextField txtUnidadesObtenidas;
    private javax.swing.JTextField txtxTempAgua;
    // End of variables declaration//GEN-END:variables
}
