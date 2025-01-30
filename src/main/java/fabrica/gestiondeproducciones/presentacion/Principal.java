
package fabrica.gestiondeproducciones.presentacion;

import fabrica.gestiondeproducciones.dominio.Controlador;
import fabrica.gestiondeproducciones.dominio.Empleado;
import fabrica.gestiondeproducciones.dominio.Insumo;
import fabrica.gestiondeproducciones.dominio.LechePasteurizada;
import fabrica.gestiondeproducciones.dominio.Produccion;
import fabrica.gestiondeproducciones.dominio.Producto;
import fabrica.gestiondeproducciones.persistencia.PersistenciaProduccion;
import fabrica.gestiondeproducciones.utilidades.Utilidades;
import java.util.ArrayList;
import java.util.List;


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
        menuLeche = new javax.swing.JMenu();
        menuIngresos = new javax.swing.JMenuItem();
        menuPasteurizados = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        menuAnalisisLeche = new javax.swing.JMenuItem();
        menuAnalisisLeche1 = new javax.swing.JMenuItem();
        menuAnalisisManteca = new javax.swing.JMenuItem();
        menuAnalisisYogur = new javax.swing.JMenuItem();
        menuAnalisisQueso = new javax.swing.JMenuItem();
        menuAnalisisDulce = new javax.swing.JMenuItem();
        jMenuProducciones = new javax.swing.JMenu();
        menuProduccionesManteca = new javax.swing.JMenuItem();
        menuAnalisisLeche3 = new javax.swing.JMenuItem();
        menuAnalisisLeche4 = new javax.swing.JMenuItem();
        menuAnalisisLeche5 = new javax.swing.JMenuItem();
        menuEnvases = new javax.swing.JMenu();

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

        menuLeche.setText("Leche");

        menuIngresos.setText("Ingresos de Leche");
        menuIngresos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuIngresosActionPerformed(evt);
            }
        });
        menuLeche.add(menuIngresos);

        menuPasteurizados.setText("Pasteurizar Leche");
        menuPasteurizados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPasteurizadosActionPerformed(evt);
            }
        });
        menuLeche.add(menuPasteurizados);

        menuPrincipal.add(menuLeche);

        jMenu1.setText("Analisis");

        menuAnalisisLeche.setText("Analisis de Leche");
        menuAnalisisLeche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAnalisisLecheActionPerformed(evt);
            }
        });
        jMenu1.add(menuAnalisisLeche);

        menuAnalisisLeche1.setText("Analisis de Leche Pasteurizada");
        menuAnalisisLeche1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAnalisisLeche1ActionPerformed(evt);
            }
        });
        jMenu1.add(menuAnalisisLeche1);

        menuAnalisisManteca.setText("Analisis de Manteca");
        menuAnalisisManteca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAnalisisMantecaActionPerformed(evt);
            }
        });
        jMenu1.add(menuAnalisisManteca);

        menuAnalisisYogur.setText("Analisis de Yogur");
        menuAnalisisYogur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAnalisisYogurActionPerformed(evt);
            }
        });
        jMenu1.add(menuAnalisisYogur);

        menuAnalisisQueso.setText("Analisis de Queso");
        menuAnalisisQueso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAnalisisQuesoActionPerformed(evt);
            }
        });
        jMenu1.add(menuAnalisisQueso);

        menuAnalisisDulce.setText("Analisis de Dulce");
        menuAnalisisDulce.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAnalisisDulceActionPerformed(evt);
            }
        });
        jMenu1.add(menuAnalisisDulce);

        menuPrincipal.add(jMenu1);

        jMenuProducciones.setText("Producciones");

        menuProduccionesManteca.setText("Produccion de Manteca");
        menuProduccionesManteca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuProduccionesMantecaActionPerformed(evt);
            }
        });
        jMenuProducciones.add(menuProduccionesManteca);

        menuAnalisisLeche3.setText("Produccion de Yogur");
        menuAnalisisLeche3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAnalisisLeche3ActionPerformed(evt);
            }
        });
        jMenuProducciones.add(menuAnalisisLeche3);

        menuAnalisisLeche4.setText("Produccion de Queso");
        menuAnalisisLeche4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAnalisisLeche4ActionPerformed(evt);
            }
        });
        jMenuProducciones.add(menuAnalisisLeche4);

        menuAnalisisLeche5.setText("Produccion de Dulce");
        menuAnalisisLeche5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAnalisisLeche5ActionPerformed(evt);
            }
        });
        jMenuProducciones.add(menuAnalisisLeche5);

        menuPrincipal.add(jMenuProducciones);

        menuEnvases.setText("Envases Dulce");
        menuEnvases.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuEnvasesMouseClicked(evt);
            }
        });
        menuPrincipal.add(menuEnvases);

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

    private void menuIngresosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuIngresosActionPerformed
        GestionIngresoLeche gestionIngresoLeche = new GestionIngresoLeche();
        escritorio.add(gestionIngresoLeche);
        gestionIngresoLeche.show();
    }//GEN-LAST:event_menuIngresosActionPerformed

    private void menuPasteurizadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPasteurizadosActionPerformed
        GestionPasteurizados gestionPasteurizados = new GestionPasteurizados();
        escritorio.add(gestionPasteurizados);
        gestionPasteurizados.show();
    }//GEN-LAST:event_menuPasteurizadosActionPerformed

    private void menuAnalisisLecheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAnalisisLecheActionPerformed
        GestionAnalisisLeche gestionAnalisisLeche = new GestionAnalisisLeche();
        escritorio.add(gestionAnalisisLeche);
        gestionAnalisisLeche.show();
    }//GEN-LAST:event_menuAnalisisLecheActionPerformed

    private void menuAnalisisLeche1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAnalisisLeche1ActionPerformed
       GestionAnalisisLechePasteurizada gest= new GestionAnalisisLechePasteurizada();
       escritorio.add(gest);
       gest.show();
    }//GEN-LAST:event_menuAnalisisLeche1ActionPerformed

    private void menuProduccionesMantecaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuProduccionesMantecaActionPerformed
        GestionProduccionManteca gestionProduccion= new GestionProduccionManteca();
        escritorio.add(gestionProduccion);
        gestionProduccion.show();
    }//GEN-LAST:event_menuProduccionesMantecaActionPerformed

    private void menuAnalisisLeche3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAnalisisLeche3ActionPerformed
        GestionProduccionYogur gestionProduccion= new GestionProduccionYogur();
        escritorio.add(gestionProduccion);
        gestionProduccion.show();
    }//GEN-LAST:event_menuAnalisisLeche3ActionPerformed

    private void menuAnalisisMantecaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAnalisisMantecaActionPerformed
        GestionAnalisisManteca gestionAnalisis = new GestionAnalisisManteca();
        escritorio.add(gestionAnalisis);
        gestionAnalisis.show();
    }//GEN-LAST:event_menuAnalisisMantecaActionPerformed

    private void menuAnalisisLeche4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAnalisisLeche4ActionPerformed
        GestionProduccionQueso gestionProduccion = new GestionProduccionQueso();
        escritorio.add(gestionProduccion);
        gestionProduccion.show();
    }//GEN-LAST:event_menuAnalisisLeche4ActionPerformed

    private void menuAnalisisYogurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAnalisisYogurActionPerformed
        GestionAnalisisYogur gestionAnalisis = new GestionAnalisisYogur();
        escritorio.add(gestionAnalisis);
        gestionAnalisis.show();
    }//GEN-LAST:event_menuAnalisisYogurActionPerformed

    private void menuAnalisisQuesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAnalisisQuesoActionPerformed
        GestionAnalisisQueso gestionAnalisis = new GestionAnalisisQueso();
        escritorio.add(gestionAnalisis);
        gestionAnalisis.show();
    }//GEN-LAST:event_menuAnalisisQuesoActionPerformed

    private void menuEnvasesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuEnvasesMouseClicked
       GestionEnvases gestionEnvases = new GestionEnvases();
        escritorio.add(gestionEnvases);
        gestionEnvases.show();
    }//GEN-LAST:event_menuEnvasesMouseClicked

    private void menuAnalisisLeche5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAnalisisLeche5ActionPerformed
       GestionProduccionDulce gestionProduccion = new GestionProduccionDulce();
        escritorio.add(gestionProduccion);
        gestionProduccion.show();
    }//GEN-LAST:event_menuAnalisisLeche5ActionPerformed

    private void menuAnalisisDulceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAnalisisDulceActionPerformed
        GestionAnalisisDulce gestionAnalisis = new GestionAnalisisDulce();
        escritorio.add(gestionAnalisis);
        gestionAnalisis.show();
    }//GEN-LAST:event_menuAnalisisDulceActionPerformed

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
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenuProducciones;
    private javax.swing.JMenuItem menuAnalisisDulce;
    private javax.swing.JMenuItem menuAnalisisLeche;
    private javax.swing.JMenuItem menuAnalisisLeche1;
    private javax.swing.JMenuItem menuAnalisisLeche3;
    private javax.swing.JMenuItem menuAnalisisLeche4;
    private javax.swing.JMenuItem menuAnalisisLeche5;
    private javax.swing.JMenuItem menuAnalisisManteca;
    private javax.swing.JMenuItem menuAnalisisQueso;
    private javax.swing.JMenuItem menuAnalisisYogur;
    private javax.swing.JMenu menuEmpleados;
    private javax.swing.JMenu menuEnvases;
    private javax.swing.JMenuItem menuIngresos;
    private javax.swing.JMenu menuInsumos;
    private javax.swing.JMenu menuLeche;
    private javax.swing.JMenuItem menuPasteurizados;
    private javax.swing.JMenuBar menuPrincipal;
    private javax.swing.JMenuItem menuProduccionesManteca;
    private javax.swing.JMenu menuProductos;
    private javax.swing.JMenu menuSecciones;
    private javax.swing.JMenu menuSilos;
    private javax.swing.JMenu menuTambos;
    // End of variables declaration//GEN-END:variables


}
