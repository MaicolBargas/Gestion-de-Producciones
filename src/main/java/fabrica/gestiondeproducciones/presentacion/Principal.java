
package fabrica.gestiondeproducciones.presentacion;


public class Principal extends javax.swing.JFrame {

    /**
     * Creates new form Principal
     */
    public Principal() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        escritorio = new javax.swing.JDesktopPane();
        menuPrincipal = new javax.swing.JMenuBar();
        menuSilos = new javax.swing.JMenu();
        menuSecciones = new javax.swing.JMenu();
        menuTambos = new javax.swing.JMenu();
        menuInsumos = new javax.swing.JMenu();
        menuEmpleados = new javax.swing.JMenu();
        menuProductos = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Principal");

        javax.swing.GroupLayout escritorioLayout = new javax.swing.GroupLayout(escritorio);
        escritorio.setLayout(escritorioLayout);
        escritorioLayout.setHorizontalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 659, Short.MAX_VALUE)
        );
        escritorioLayout.setVerticalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 429, Short.MAX_VALUE)
        );

        menuSilos.setText("Silos");
        menuSilos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuSilosMouseClicked(evt);
            }
        });
        menuSilos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSilosActionPerformed(evt);
            }
        });
        menuPrincipal.add(menuSilos);

        menuSecciones.setText("Secciones");
        menuSecciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuSeccionesMouseClicked(evt);
            }
        });
        menuSecciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSeccionesActionPerformed(evt);
            }
        });
        menuPrincipal.add(menuSecciones);

        menuTambos.setText("Tambos");
        menuTambos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuTambosMouseClicked(evt);
            }
        });
        menuTambos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuTambosActionPerformed(evt);
            }
        });
        menuPrincipal.add(menuTambos);

        menuInsumos.setText("Insumos");
        menuInsumos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuInsumosMouseClicked(evt);
            }
        });
        menuPrincipal.add(menuInsumos);

        menuEmpleados.setText("Empleados");
        menuEmpleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuEmpleadosMouseClicked(evt);
            }
        });
        menuPrincipal.add(menuEmpleados);

        menuProductos.setText("Productos");
        menuProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuProductosMouseClicked(evt);
            }
        });
        menuPrincipal.add(menuProductos);

        setJMenuBar(menuPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 659, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(escritorio))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 429, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(escritorio))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuSilosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSilosActionPerformed

    }//GEN-LAST:event_menuSilosActionPerformed

    private void menuSilosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuSilosMouseClicked
        GestionSilos gestionSilos = new GestionSilos();
        escritorio.add(gestionSilos);
        gestionSilos.show();
    }//GEN-LAST:event_menuSilosMouseClicked

    private void menuSeccionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuSeccionesMouseClicked
        GestionSecciones gestionSecciones = new GestionSecciones();
        escritorio.add(gestionSecciones);
        gestionSecciones.show();
    }//GEN-LAST:event_menuSeccionesMouseClicked

    private void menuSeccionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSeccionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuSeccionesActionPerformed

    private void menuTambosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuTambosMouseClicked
        GestionTambo gestionTambo = new GestionTambo();
        escritorio.add(gestionTambo);
        gestionTambo.show();
    }//GEN-LAST:event_menuTambosMouseClicked

    private void menuTambosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuTambosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuTambosActionPerformed

    private void menuInsumosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuInsumosMouseClicked
        GestionInsumos gestionInsumos = new GestionInsumos();
        escritorio.add(gestionInsumos);
        gestionInsumos.show();
    }//GEN-LAST:event_menuInsumosMouseClicked

    private void menuEmpleadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuEmpleadosMouseClicked
        GestionEmpleados gestionEmpleados = new GestionEmpleados();
        escritorio.add(gestionEmpleados);
        gestionEmpleados.show();
    }//GEN-LAST:event_menuEmpleadosMouseClicked

    private void menuProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuProductosMouseClicked
       GestionProductos gestionProductos = new GestionProductos();
        escritorio.add(gestionProductos);
        gestionProductos.show();
    }//GEN-LAST:event_menuProductosMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane escritorio;
    private javax.swing.JMenu menuEmpleados;
    private javax.swing.JMenu menuInsumos;
    private javax.swing.JMenuBar menuPrincipal;
    private javax.swing.JMenu menuProductos;
    private javax.swing.JMenu menuSecciones;
    private javax.swing.JMenu menuSilos;
    private javax.swing.JMenu menuTambos;
    // End of variables declaration//GEN-END:variables


}
