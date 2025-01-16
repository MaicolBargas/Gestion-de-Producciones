/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package fabrica.gestiondeproducciones.presentacion;

import fabrica.gestiondeproducciones.dominio.Controlador;
import fabrica.gestiondeproducciones.dominio.Empleado;
import fabrica.gestiondeproducciones.dominio.IngresoLeche;
import fabrica.gestiondeproducciones.dominio.Insumo;
import fabrica.gestiondeproducciones.dominio.LechePasteurizada;
import fabrica.gestiondeproducciones.dominio.Produccion;
import fabrica.gestiondeproducciones.dominio.Producto;
import fabrica.gestiondeproducciones.dominio.Tambo;
import fabrica.gestiondeproducciones.utilidades.Utilidades;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MSI
 */
public class GestionProduccionBase1 extends javax.swing.JInternalFrame {

    Controlador controlador= new Controlador();
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableModel modeloEmpleadosAgregar = new DefaultTableModel();
    DefaultTableModel modelo2 = new DefaultTableModel();
    DefaultTableModel modeloInsumosAgregar = new DefaultTableModel();
    Utilidades utilidad= new Utilidades();
    Produccion produccion= new Produccion();
    int idEncargadoobtener;
    int idEncargado;
    int idEmpleado;
    
    public GestionProduccionBase1() {
        initComponents();
        listarLeche();
        listarProductos();
        listarAgregarEmpleado();
        listarAgregarInsumo();
    }

    private void listarLeche(){      
        List<LechePasteurizada> leche = controlador.listarPasteurizados();
        for(LechePasteurizada t : leche){        
            cbxLeche.addItem(t.getId() + " - Tambo de Origen: " +t.getIngreso().getTambo());
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
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtId = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        txtObtenidos = new javax.swing.JTextField();
        txtFecha = new javax.swing.JTextField();
        txtEncargado = new javax.swing.JTextField();
        txtHoraInicio = new javax.swing.JTextField();
        txtHoraFin = new javax.swing.JTextField();
        txtNroTacho = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        cbxLeche = new javax.swing.JComboBox<>();
        cbxProducto = new javax.swing.JComboBox<>();
        jScrollPane8 = new javax.swing.JScrollPane();
        tablaIngresos6 = new javax.swing.JTable();
        txtIRendimiento = new javax.swing.JTextField();
        txtCodigoInterno = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane9 = new javax.swing.JScrollPane();
        tablaAgregarInsumos = new javax.swing.JTable();
        jScrollPane12 = new javax.swing.JScrollPane();
        tablaAgregarEmpleados = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        btnAgregarEmpleado = new javax.swing.JButton();
        btnSeleccionarEncargado = new javax.swing.JButton();
        btnAgregarInsumo = new javax.swing.JButton();
        txtTiempoTrabajado = new javax.swing.JTextField();
        txtCantidadInsumo = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        btnAlta = new javax.swing.JButton();

        txtId.setEditable(false);

        jLabel1.setText("Id:");

        jLabel41.setText("Codigo Interno:");

        txtObtenidos.setToolTipText("");

        txtFecha.setToolTipText("");

        txtEncargado.setEditable(false);
        txtEncargado.setToolTipText("");

        txtHoraInicio.setToolTipText("");

        txtHoraFin.setToolTipText("");

        txtNroTacho.setToolTipText("");

        jLabel42.setText("Leche Pasteurizada:");

        jLabel43.setText("Rendimiento:");

        jLabel44.setText("Producto:");

        jLabel45.setText("Kg Obtenidos:");

        jLabel46.setText("Encargado:");

        jLabel47.setText("Hora Inicio:");

        jLabel48.setText("Hora Fin:");

        jLabel49.setText("Nro de Tacho:");

        jLabel50.setText("Fecha:");

        jLabel54.setText("Tiempo Trabajado:");

        tablaIngresos6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Tambo", "Litros", "Silo", "Fecha"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
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
        tablaIngresos6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaIngresos6MouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tablaIngresos6);

        txtIRendimiento.setEditable(false);

        txtCodigoInterno.setEditable(false);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane7.setViewportView(jTable2);

        tablaAgregarInsumos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Nombre", "Descripcion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane9.setViewportView(tablaAgregarInsumos);

        tablaAgregarEmpleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "CI", "Nombre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tablaAgregarEmpleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaAgregarEmpleadosMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(tablaAgregarEmpleados);

        jLabel7.setText("Empleados Que Trabajaron");

        jLabel8.setText("Insumos Utilizados");

        jLabel29.setText("Agregar Empleados/ Seleccion de Encargado");

        jLabel30.setText("Agregar Insumos");

        btnAgregarEmpleado.setText("Agregar Empleado");

        btnSeleccionarEncargado.setText("Seleccionar Encargado");
        btnSeleccionarEncargado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarEncargadoActionPerformed(evt);
            }
        });

        btnAgregarInsumo.setText("Agregar Insumo");

        txtTiempoTrabajado.setEditable(false);

        txtCantidadInsumo.setToolTipText("");

        jLabel31.setText("Cantidad Utilizada");

        btnAlta.setText("Alta");
        btnAlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAltaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel41)
                                            .addComponent(jLabel1))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(53, 53, 53)
                                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addGap(39, 39, 39)
                                                .addComponent(txtCodigoInterno, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel42)
                                            .addComponent(jLabel44, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel43, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel45, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel50, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel46, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel47, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel48, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel54, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel49, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txtObtenidos)
                                                .addComponent(txtFecha)
                                                .addComponent(txtEncargado)
                                                .addComponent(txtHoraInicio)
                                                .addComponent(txtHoraFin)
                                                .addComponent(txtNroTacho)
                                                .addComponent(cbxLeche, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(cbxProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtIRendimiento))
                                            .addComponent(txtTiempoTrabajado))))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(59, 59, 59)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(38, 38, 38)
                                                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(btnAgregarEmpleado)
                                                    .addComponent(btnSeleccionarEncargado)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(38, 38, 38)
                                                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(txtCantidadInsumo))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(40, 40, 40)
                                                        .addComponent(jLabel31)
                                                        .addGap(0, 0, Short.MAX_VALUE))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btnAgregarInsumo)
                                                        .addGap(20, 20, 20))))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(164, 164, 164)
                                        .addComponent(jLabel8)
                                        .addGap(241, 241, 241)
                                        .addComponent(jLabel30))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(436, 436, 436)
                                .addComponent(jLabel7)
                                .addGap(152, 152, 152)
                                .addComponent(jLabel29)))
                        .addGap(0, 6, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane8)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(426, 426, 426)
                .addComponent(btnAlta)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtId, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel41)
                                    .addComponent(txtCodigoInterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbxLeche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbxProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(7, 7, 7)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtIRendimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(btnAgregarEmpleado)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSeleccionarEncargado)))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtObtenidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel30)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEncargado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtHoraInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtHoraFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTiempoTrabajado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNroTacho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(21, 21, 21)
                            .addComponent(jLabel31)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtCantidadInsumo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnAgregarInsumo))
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addComponent(btnAlta)
                .addGap(63, 63, 63)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(276, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaIngresos6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaIngresos6MouseClicked
       
    }//GEN-LAST:event_tablaIngresos6MouseClicked

    private void btnAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAltaActionPerformed
        try{
            String fecha = utilidad.controlarFechas(txtFecha.getText());
            String CodigoInterno = "CODIGO";
            int rendimiento = 1;
            int kgObtenidos = utilidad.validarNumericos(txtObtenidos.getText(), "Kg Obtenidos", false);
            String horaInicio = utilidad.sanitizarCampos(txtHoraInicio.getText(),"Hora Inicio",false);
            String horaFin = utilidad.sanitizarCampos(txtHoraFin.getText(),"Hora Fin",false);
            String TiempoTrabajado = "TIME";
            int nroTacho = utilidad.validarNumericos(txtNroTacho.getText(), "Numero de Tacho", false);
             
            try{
                
                Empleado empleado = controlador.buscarEmpleado(idEncargado);
                if(empleado instanceof Empleado){
                    produccion.setEncargado(empleado);
                }else{
                    throw new Exception("Debe seleccionar un empleado habilitado");
                }
            }catch(Exception e){
                throw new Exception("Debe buscar un usuario valido primero, por favor verifique");
            }
            
            List<Empleado> listaEmpleados= new ArrayList();
            listaEmpleados=controlador.listarEmpleados();
            List<Insumo> listaInsumos= new ArrayList();
            listaInsumos=controlador.listarInsumos();
            
            String[] partes = cbxLeche.getSelectedItem().toString().split(" - ");
            LechePasteurizada lechep= controlador.buscarPasteurizado(Integer.parseInt(partes[0]));
            
             String[] partes2 = cbxProducto.getSelectedItem().toString().split(" - ");
            Producto producto= controlador.buscarProducto(Integer.parseInt(partes[0]));
            
            produccion.setCodInterno(CodigoInterno);
            produccion.setListaInsumos(listaInsumos);
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
            
            boolean alta = controlador.altaProduccion(produccion);
            if(alta){
              JOptionPane.showMessageDialog(null, "Produccion dada de alta.");
             
              limpiarFormulario();
              //listar();
        }
      }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Advertencia", JOptionPane.WARNING_MESSAGE);
      }
      
    }//GEN-LAST:event_btnAltaActionPerformed

    
    
    private void tablaAgregarEmpleadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAgregarEmpleadosMouseClicked
        
        int fila = tablaAgregarEmpleados.rowAtPoint(evt.getPoint());
        idEncargadoobtener=(int) tablaAgregarEmpleados.getValueAt(fila, 0);
        idEmpleado=(int) tablaAgregarEmpleados.getValueAt(fila, 0);
        
    }//GEN-LAST:event_tablaAgregarEmpleadosMouseClicked

    private void btnSeleccionarEncargadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarEncargadoActionPerformed
       
        idEncargado=idEncargadoobtener;
        txtEncargado.setText(controlador.buscarEmpleado(idEncargado).getId()+"-"+controlador.buscarEmpleado(idEncargado).getNombre());
        
    }//GEN-LAST:event_btnSeleccionarEncargadoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarEmpleado;
    private javax.swing.JButton btnAgregarInsumo;
    private javax.swing.JButton btnAlta;
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
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable tablaAgregarEmpleados;
    private javax.swing.JTable tablaAgregarInsumos;
    private javax.swing.JTable tablaIngresos6;
    private javax.swing.JTextField txtCantidadInsumo;
    private javax.swing.JTextField txtCodigoInterno;
    private javax.swing.JTextField txtEncargado;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtHoraFin;
    private javax.swing.JTextField txtHoraInicio;
    private javax.swing.JTextField txtIRendimiento;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNroTacho;
    private javax.swing.JTextField txtObtenidos;
    private javax.swing.JTextField txtTiempoTrabajado;
    // End of variables declaration//GEN-END:variables
}
