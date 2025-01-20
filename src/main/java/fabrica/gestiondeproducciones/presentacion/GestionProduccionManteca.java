
package fabrica.gestiondeproducciones.presentacion;

import fabrica.gestiondeproducciones.dominio.Controlador;
import fabrica.gestiondeproducciones.dominio.Empleado;
import fabrica.gestiondeproducciones.dominio.IngresoLeche;
import fabrica.gestiondeproducciones.dominio.Insumo;
import fabrica.gestiondeproducciones.dominio.LechePasteurizada;
import fabrica.gestiondeproducciones.dominio.LineaInsumo;
import fabrica.gestiondeproducciones.dominio.Produccion;
import fabrica.gestiondeproducciones.dominio.ProduccionManteca;
import fabrica.gestiondeproducciones.dominio.Producto;
import fabrica.gestiondeproducciones.dominio.Tambo;
import fabrica.gestiondeproducciones.persistencia.PersistenciaProduccion;
import fabrica.gestiondeproducciones.utilidades.Utilidades;
import java.sql.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class GestionProduccionManteca extends javax.swing.JInternalFrame {

  
    Controlador controlador= new Controlador();
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableModel modeloEmpleadosAgregar = new DefaultTableModel();
    DefaultTableModel modeloEmpleadosTrabajaron = new DefaultTableModel();
    DefaultTableModel modeloInsumosAgregar = new DefaultTableModel();
    DefaultTableModel modeloInsumosUtilizados = new DefaultTableModel();
    Utilidades utilidad= new Utilidades();
    ProduccionManteca produccion= new ProduccionManteca();
    List <Empleado> listaEmpleados= new ArrayList<>();
    List<LineaInsumo> listaInsumosLinea= new ArrayList<>();
    int idEncargadoobtener;
    int idEmpleadoObtener;
    int idEncargado;
    int idEmpleado;
    int idInsumo;
    int idEmpleadoEliminar;
    int idInsumoEliminar;
    
    
    public GestionProduccionManteca() {
        initComponents();
        listar();
        listarLeche();
        listarProductos();
        listarAgregarEmpleado();
        listarAgregarInsumo();
    }

    private void listar(){
        limpiarTabla();
        cargarFecha();
        List<ProduccionManteca> lista = controlador.listarProduccionesManteca();
        modelo = (DefaultTableModel) tablaProducciones.getModel();
        Object[] objeto = new Object[5];
        for(int i = 0; i < lista.size(); i++){
            objeto[0] = lista.get(i).getIdProduccion();
            objeto[1] = lista.get(i).getCodInterno();
            objeto[2] = lista.get(i).getFecha();
            objeto[3] = lista.get(i).getNroTacho();
            modelo.addRow(objeto);
        }
        tablaProducciones.setModel(modelo);
    }
    
    private void limpiarTabla(){
        for(int i = 0; i < modelo.getRowCount(); i++){
            modelo.removeRow(i);
            i =- 1;
        }
    }
    
    private void listarLeche(){      
        List<LechePasteurizada> leche = controlador.listarPasteurizados();
        for(LechePasteurizada t : leche){        
            cbxLeche.addItem(t.getId() + " - Tambo de Origen: " +t.getIngreso().getTambo().getPropietario());
        }
    }
    
    private void listarProductos(){      
        List<Producto> productos = controlador.listarProductos();
        for(Producto t : productos){        
            cbxProducto.addItem(t.getId() + " - " +t.getNombre());
        }
    }
    
    private void listarAgregarEmpleado(){     
        limpiarTablaEmpleados();
        List<Empleado> lista = controlador.listarEmpleados();
        modeloEmpleadosAgregar = (DefaultTableModel) tablaAgregarEmpleados.getModel();
        Object[] objeto = new Object[3];
        for(int i = 0; i < lista.size(); i++){
            objeto[0] = lista.get(i).getId();
            objeto[1] = lista.get(i).getCi();
            objeto[2] = lista.get(i).getNombre();
            
            modeloEmpleadosAgregar.addRow(objeto);
        }
        tablaAgregarEmpleados.setModel(modeloEmpleadosAgregar);
    }
    
    private void limpiarTablaEmpleados(){
        for(int i = 0; i < modeloEmpleadosAgregar.getRowCount(); i++){
            modeloEmpleadosAgregar.removeRow(i);
            i =- 1;
        }
    }
    
    private void cargarFecha(){
        LocalDate fechaHoy = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fecha = fechaHoy.format(format);
        txtFecha.setText(fecha);
    }
    
    private void listarEmpleados(List <Empleado> lista){     
        limpiarTablaEmpleadosTrabajaron();
        
        modeloEmpleadosTrabajaron = (DefaultTableModel) tablaEmpleadosTrabajaron.getModel();
        Object[] objeto = new Object[3];
        for(int i = 0; i < lista.size(); i++){
            objeto[0] = lista.get(i).getId();
            objeto[1] = lista.get(i).getCi();
            objeto[2] = lista.get(i).getNombre();
            
            modeloEmpleadosTrabajaron.addRow(objeto);
        }
        tablaEmpleadosTrabajaron.setModel(modeloEmpleadosTrabajaron);
    }
    private void limpiarTablaEmpleadosTrabajaron(){
        for(int i = 0; i < modeloEmpleadosTrabajaron.getRowCount(); i++){
            modeloEmpleadosTrabajaron.removeRow(i);
            i =- 1;
        }
    }
    
    
    private void listarAgregarInsumo(){     
        limpiarTablaAgregarInsumos();
        List<Insumo> lista = controlador.listarInsumos();
        modeloInsumosAgregar = (DefaultTableModel) tablaAgregarInsumos.getModel();
        Object[] objeto = new Object[3];
        for(int i = 0; i < lista.size(); i++){
            objeto[0] = lista.get(i).getId();
            objeto[1] = lista.get(i).getNombre();
            objeto[2] = lista.get(i).getDescripcion();
          
            
            modeloInsumosAgregar.addRow(objeto);
        }
        tablaAgregarInsumos.setModel(modeloInsumosAgregar);
    }
    private void limpiarTablaAgregarInsumos(){
        for(int i = 0; i < modeloInsumosAgregar.getRowCount(); i++){
            modeloInsumosAgregar.removeRow(i);
            i =- 1;
        }
    }
    
    private void listarLineaInsumos(List <LineaInsumo> lista){     
        limpiarTablaInsumosUtilizados();
        
        modeloInsumosUtilizados = (DefaultTableModel) tablaInsumosAgregados.getModel();
        Object[] objeto = new Object[4];
        for(int i = 0; i < lista.size(); i++){
            objeto[0] = lista.get(i).getInsumo().getId();
            objeto[1] = lista.get(i).getInsumo().getNombre();
            objeto[2] = lista.get(i).getInsumo().getDescripcion();
            objeto[3]= lista.get(i).getCantidad()+" "+lista.get(i).getInsumo().getUnidad();
            modeloInsumosUtilizados.addRow(objeto);
        }
        tablaInsumosAgregados.setModel(modeloInsumosUtilizados);
    }
    private void limpiarTablaInsumosUtilizados(){
        for(int i = 0; i < modeloInsumosUtilizados.getRowCount(); i++){
            modeloInsumosUtilizados.removeRow(i);
            i =- 1;
        }
    }
    
    private void limpiarFormulario(){
    this.txtCantidadInsumo.setText("");
    this.txtCodigoInterno.setText("");
    this.txtEncargado.setText("");
    this.txtFecha.setText("");
    this.txtHoraFin.setText("");
    this.txtHoraInicio.setText("");
    this.txtIRendimiento.setText("");
    this.txtId.setText("");
    this.txtNroTacho.setText("");
    this.txtTiempoTrabajado.setText("");
    this.txtObtenidos.setText("");
    this.txtComienzoBatido.setText("");
    this.txtFinBatido.setText("");
    this.txtTotalBatido.setText("");
    this.txtOrmas.setText("");
    idEncargado=-1;
    idEncargadoobtener=-1;
    idEmpleado=-1;
    idEmpleadoObtener=-1;
    idInsumo=-1;
    
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane8 = new javax.swing.JScrollPane();
        tablaProducciones = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        txtCodigoInterno = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        cbxLeche = new javax.swing.JComboBox<>();
        jLabel44 = new javax.swing.JLabel();
        cbxProducto = new javax.swing.JComboBox<>();
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
        txtComienzoBatido = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        txtFinBatido = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        txtTotalBatido = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        txtOrmas = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaEmpleadosTrabajaron = new javax.swing.JTable();
        jLabel29 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        tablaAgregarEmpleados = new javax.swing.JTable();
        btnAgregarEmpleado = new javax.swing.JButton();
        btnSeleccionarEncargado = new javax.swing.JButton();
        btnEliminarEmpleado = new javax.swing.JToggleButton();
        jPanel4 = new javax.swing.JPanel();
        btnAlta = new javax.swing.JButton();
        btnBaja = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tablaInsumosAgregados = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tablaAgregarInsumos = new javax.swing.JTable();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        txtCantidadInsumo = new javax.swing.JTextField();
        btnAgregarInsumo = new javax.swing.JButton();
        btnEliminarInsumo = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Produccion de Manteca");
        setToolTipText("");
        setPreferredSize(new java.awt.Dimension(2000, 1000));

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

        jLabel1.setText("Id:");

        txtId.setEditable(false);

        jLabel41.setText("Codigo Interno:");

        txtCodigoInterno.setEditable(false);

        jLabel42.setText("Leche Pasteurizada:");

        jLabel44.setText("Producto:");

        jLabel43.setText("Rendimiento:");

        txtIRendimiento.setEditable(false);

        jLabel45.setText("Kg/L Obtenidos:");

        txtObtenidos.setToolTipText("");

        jLabel50.setText("Fecha:");

        txtFecha.setToolTipText("");

        jLabel46.setText("Encargado:");

        txtEncargado.setEditable(false);
        txtEncargado.setToolTipText("");

        jLabel47.setText("Hora Inicio:");

        txtHoraInicio.setToolTipText("");

        jLabel48.setText("Hora Fin:");

        txtHoraFin.setToolTipText("");

        jLabel54.setText("Tiempo Trabajado:");

        txtTiempoTrabajado.setEditable(false);

        jLabel49.setText("Nro de Tacho:");

        txtNroTacho.setToolTipText("");

        jLabel51.setText("Hora Comienzo Batido");

        txtComienzoBatido.setToolTipText("");

        jLabel52.setText("Hora Fin Batido");

        txtFinBatido.setToolTipText("");

        jLabel55.setText("Tiempo Total Batido:");

        txtTotalBatido.setToolTipText("");

        jLabel53.setText("Cantidad de Ormas:");

        txtOrmas.setToolTipText("");
        txtOrmas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOrmasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel50)
                            .addComponent(jLabel46)
                            .addComponent(jLabel47)
                            .addComponent(jLabel48)
                            .addComponent(jLabel54)
                            .addComponent(jLabel49)
                            .addComponent(jLabel51)
                            .addComponent(jLabel52)
                            .addComponent(jLabel53)
                            .addComponent(jLabel55))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtOrmas, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTotalBatido, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFinBatido, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtComienzoBatido, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEncargado, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtHoraInicio, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtHoraFin, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNroTacho, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTiempoTrabajado)
                            .addComponent(txtFecha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel41)
                            .addComponent(jLabel1))
                        .addGap(58, 58, 58)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCodigoInterno)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel42)
                            .addComponent(jLabel44, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel43, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel45, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtObtenidos, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxLeche, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxProducto, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtIRendimiento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtId, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
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
                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIRendimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtObtenidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEncargado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtHoraInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtHoraFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTiempoTrabajado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNroTacho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtComienzoBatido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFinBatido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(257, 257, 257)
                        .addComponent(txtTotalBatido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtOrmas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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

        btnEliminarEmpleado.setText("Eliminar empleado");
        btnEliminarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarEmpleadoActionPerformed(evt);
            }
        });

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSeleccionarEncargado)
                            .addComponent(btnAgregarEmpleado)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addGap(217, 217, 217)))
                .addGap(27, 27, 27))
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
                        .addGap(76, 76, 76)
                        .addComponent(btnAgregarEmpleado)
                        .addGap(18, 18, 18)
                        .addComponent(btnSeleccionarEncargado))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(btnEliminarEmpleado)))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        btnAlta.setText("Alta");
        btnAlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAltaActionPerformed(evt);
            }
        });

        btnBaja.setText("Baja");

        btnModificar.setText("Modificar");

        btnLimpiar.setText("Limpiar");

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnLimpiar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAlta)
                    .addComponent(btnBaja)
                    .addComponent(btnModificar))
                .addGap(17, 17, 17))
        );

        tablaInsumosAgregados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Insumo", "Nombre", "Descripcion", "Cantidad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
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
                "idInsumo", "Nombre", "Descripcion"
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

        jLabel31.setText("Cantidad Utilizada");

        txtCantidadInsumo.setToolTipText("");

        btnAgregarInsumo.setText("Agregar Insumo");
        btnAgregarInsumo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarInsumoActionPerformed(evt);
            }
        });

        btnEliminarInsumo.setText("Eliminar insumo");
        btnEliminarInsumo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarInsumoActionPerformed(evt);
            }
        });

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminarInsumo, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtCantidadInsumo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarInsumo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(164, Short.MAX_VALUE))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(0, 39, Short.MAX_VALUE)
                                .addComponent(jLabel31)
                                .addGap(18, 18, 18)
                                .addComponent(txtCantidadInsumo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnAgregarInsumo)
                                .addGap(130, 130, 130))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(btnEliminarInsumo)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 1389, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaProduccionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProduccionesMouseClicked
       
    }//GEN-LAST:event_tablaProduccionesMouseClicked

    private void btnAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAltaActionPerformed
        try{
            String fecha = utilidad.controlarFechas(txtFecha.getText());
            int rendimiento = 1;
            int kgObtenidos = utilidad.validarNumericos(txtObtenidos.getText(), "Kg Obtenidos", false);
            String horaInicio = utilidad.sanitizarCampos(txtHoraInicio.getText(),"Hora Inicio",false);
            String horaFin = utilidad.sanitizarCampos(txtHoraFin.getText(),"Hora Fin",false);
            String TiempoTrabajado = "TIME";
            int nroTacho = utilidad.validarNumericos(txtNroTacho.getText(), "Numero de Tacho", false);
            String[] partesFecha= txtFecha.getText().split("/");
            String CodigoInterno = "M"+partesFecha[0]+partesFecha[1]+partesFecha[2]+txtOrmas.getText()+txtNroTacho.getText();
            String InicioBatido=txtComienzoBatido.getText();
            String FinBatido=txtFinBatido.getText();
            String totalBatido=txtTotalBatido.getText();
            int ormas=utilidad.validarNumericos(txtOrmas.getText(),"Ormas",false);
            try{
                
                Empleado empleado = controlador.buscarEmpleado(idEncargado);
                if(empleado instanceof Empleado){
                    produccion.setEncargado(empleado);
                }else{
                    throw new Exception("Debe seleccionar un Encargado");
                }
            }catch(Exception e){
                throw new Exception("Debe buscar un usuario valido primero, por favor verifique");
            }
            
            
            
            
            String[] partes = cbxLeche.getSelectedItem().toString().split(" - ");
            LechePasteurizada lechep= controlador.buscarPasteurizado(Integer.parseInt(partes[0]));
            
            String[] partes2 = cbxProducto.getSelectedItem().toString().split(" - ");
            Producto producto= controlador.buscarProducto(Integer.parseInt(partes[0]));
            
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
            produccion.setHoraComienzoBatido(InicioBatido);
            produccion.setHoraFinBatido(FinBatido);
            produccion.setTiempoTotalBatido(TiempoTrabajado);
            produccion.setCantidad(ormas);
            produccion.setListaEmpleados(listaEmpleados);
            
            
            
         
            boolean alta=controlador.altaProduccionManteca(produccion);
            if(alta ){
               
              JOptionPane.showMessageDialog(null, "Produccion dada de alta.");
             
              limpiarFormulario();
              
              listar();
        }
      }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Advertencia", JOptionPane.WARNING_MESSAGE);
      }
      
    }//GEN-LAST:event_btnAltaActionPerformed

    
    
    private void tablaAgregarEmpleadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAgregarEmpleadosMouseClicked
        
        int fila = tablaAgregarEmpleados.rowAtPoint(evt.getPoint());
        idEncargadoobtener=(int) tablaAgregarEmpleados.getValueAt(fila, 0);
        idEmpleadoObtener=(int) tablaAgregarEmpleados.getValueAt(fila, 0);
        
    }//GEN-LAST:event_tablaAgregarEmpleadosMouseClicked

    private void btnSeleccionarEncargadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarEncargadoActionPerformed
       
        idEncargado=idEncargadoobtener;
        Empleado empleado = controlador.buscarEmpleado(idEncargado);        
        txtEncargado.setText(empleado.getId()+"-"+empleado.getNombre());
        
    }//GEN-LAST:event_btnSeleccionarEncargadoActionPerformed

    private void txtOrmasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOrmasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOrmasActionPerformed

    private void btnAgregarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarEmpleadoActionPerformed
        try{            
            idEmpleado=idEmpleadoObtener;          
            Empleado unEmpleado = controlador.buscarEmpleado(idEmpleado);
            if(unEmpleado instanceof Empleado){
                if (!listaEmpleados.contains(unEmpleado)) {
                    listaEmpleados.add(unEmpleado);
                } else { throw new Exception("El cliente ya se encuentra agregado a la lista");}
                listarEmpleados(listaEmpleados);
            }else{
                throw new Exception("Debe seleccionar un empleado de la lista");
            }            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnAgregarEmpleadoActionPerformed

    private void btnAgregarInsumoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarInsumoActionPerformed
        try{            
            Insumo insumo = controlador.buscarInsumo(idInsumo);
            int cantidad= Integer.parseInt(txtCantidadInsumo.getText());

            LineaInsumo insumoLinea= new LineaInsumo(insumo,cantidad);
            if(insumo instanceof Insumo && insumoLinea instanceof LineaInsumo){
                if(!listaInsumosLinea.contains(insumoLinea)){
                listaInsumosLinea.add(insumoLinea);            
                } else { throw new Exception("El insumo ya se encuentra agregado a la lista, puede modificar su cantidad desde la tabla");}
                listarLineaInsumos(listaInsumosLinea);
            }else{
                throw new Exception("Debe seleccionar un empleado de la lista");
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Advertencia", JOptionPane.WARNING_MESSAGE);
        }        
    }//GEN-LAST:event_btnAgregarInsumoActionPerformed

    private void tablaInsumosAgregadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaInsumosAgregadosMouseClicked
        int fila = tablaInsumosAgregados.rowAtPoint(evt.getPoint());
        idInsumoEliminar = (int) tablaInsumosAgregados.getValueAt(fila, 0);
    }//GEN-LAST:event_tablaInsumosAgregadosMouseClicked

    private void tablaAgregarInsumosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAgregarInsumosMouseClicked
        int fila = tablaAgregarInsumos.rowAtPoint(evt.getPoint());
        idInsumo=(int) tablaAgregarInsumos.getValueAt(fila, 0);
        
    }//GEN-LAST:event_tablaAgregarInsumosMouseClicked

    private void tablaEmpleadosTrabajaronMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaEmpleadosTrabajaronMouseClicked
        int fila = tablaEmpleadosTrabajaron.rowAtPoint(evt.getPoint());
        idEmpleadoEliminar = (int) tablaEmpleadosTrabajaron.getValueAt(fila, 0);
    }//GEN-LAST:event_tablaEmpleadosTrabajaronMouseClicked

    private void btnEliminarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarEmpleadoActionPerformed
        try{
            idEmpleado = idEmpleadoEliminar;
            Empleado empleadoEliminar = controlador.buscarEmpleado(idEmpleado);
            if (listaEmpleados.contains(empleadoEliminar)) {
                int respuesta = JOptionPane.showInternalConfirmDialog(null, "Desea eliminar a "+empleadoEliminar.getNombreCompleto()+ " de la lista?");
                if(respuesta == JOptionPane.YES_OPTION){
                    listaEmpleados.remove(empleadoEliminar);
                }
            } else { throw new Exception("Debe seleccionar un cliente a eliminar");}
            listarEmpleados(listaEmpleados);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Advertencia", JOptionPane.WARNING_MESSAGE);
        }   
    }//GEN-LAST:event_btnEliminarEmpleadoActionPerformed

    private void btnEliminarInsumoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarInsumoActionPerformed
        try{
            idInsumo = idInsumoEliminar;
            Insumo insumo = controlador.buscarInsumo(idInsumo);
            LineaInsumo lineaInsumo = new LineaInsumo();
            lineaInsumo.setInsumo(insumo);
            if (listaInsumosLinea.contains(lineaInsumo)) {
                int respuesta = JOptionPane.showInternalConfirmDialog(null, "Desea eliminar "+insumo.getNombre()+ " de la lista?");
                if(respuesta == JOptionPane.YES_OPTION){
                    listaInsumosLinea.remove(lineaInsumo);                    
                }
            } else { throw new Exception("Debe seleccionar un insumo a eliminar");}
            listarLineaInsumos(listaInsumosLinea);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Advertencia", JOptionPane.WARNING_MESSAGE);
        }   
    }//GEN-LAST:event_btnEliminarInsumoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
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
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
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
    private javax.swing.JTextField txtCantidadInsumo;
    private javax.swing.JTextField txtCodigoInterno;
    private javax.swing.JTextField txtComienzoBatido;
    private javax.swing.JTextField txtEncargado;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtFinBatido;
    private javax.swing.JTextField txtHoraFin;
    private javax.swing.JTextField txtHoraInicio;
    private javax.swing.JTextField txtIRendimiento;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNroTacho;
    private javax.swing.JTextField txtObtenidos;
    private javax.swing.JTextField txtOrmas;
    private javax.swing.JTextField txtTiempoTrabajado;
    private javax.swing.JTextField txtTotalBatido;
    // End of variables declaration//GEN-END:variables
}
