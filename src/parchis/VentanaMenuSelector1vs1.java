
package parchis;

import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author ricar
 */

/*
    Ventana de elección de jugadores
    Hace comprobación de nombres y equipos
*/
public class VentanaMenuSelector1vs1 extends javax.swing.JFrame {

    private Controladora c1;
    public VentanaMenuSelector1vs1(Controladora c) {
        this.c1 = c;
        initComponents();
        rellenarCboEquipoJ1();
        rellenarCboEquipoJ2();
        this.setIconImage(new ImageIcon("./src/res/img/ico.png").getImage());
    }
    
    private void rellenarCboEquipoJ1(){
        cboEquipoJ1.removeAllItems();
        cboEquipoJ1.addItem(Equipo.AZUL);
        cboEquipoJ1.addItem(Equipo.VERDE);
        cboEquipoJ1.setSelectedIndex(0);
    }
    
    private void rellenarCboEquipoJ2(){
        cboEquipoJ2.removeAllItems();
        cboEquipoJ2.addItem(Equipo.AZUL);
        cboEquipoJ2.addItem(Equipo.VERDE);
        cboEquipoJ2.setSelectedIndex(1);
    }
    
    private boolean comprobar(){
        if(!Pattern.matches("[A-ZÑÁÉÍÓÚ0-9]{3,16}", txtNombreJ1.getText().toUpperCase())){
            JOptionPane.showMessageDialog(rootPane, "El nombre del Jugador 1 no es válido (Entre 3 y 16 letras o números)");
            txtNombreJ1.requestFocus();
            return false;
        }
        if(cboEquipoJ1.getSelectedIndex()==-1){
            JOptionPane.showMessageDialog(rootPane, "Debes seleccionar el equipo del Jugador 1");
            cboEquipoJ1.requestFocus();
            return false;
        }
        if(!Pattern.matches("[A-ZÑÁÉÍÓÚ0-9]{3,16}", txtNombreJ2.getText().toUpperCase())){
            JOptionPane.showMessageDialog(rootPane, "El nombre del Jugador 2 no es válido (Entre 3 y 16 letras o números)");
            txtNombreJ2.requestFocus();
            return false;
        }
        if(cboEquipoJ2.getSelectedIndex()==-1){
            JOptionPane.showMessageDialog(rootPane, "Debes seleccionar el equipo del Jugador 2");
            cboEquipoJ2.requestFocus();
            return false;
        }
        if(txtNombreJ1.getText().equals(txtNombreJ2.getText())){
            JOptionPane.showMessageDialog(rootPane, "Los jugadores deben tener nombres distintos");
            txtNombreJ2.requestFocus();
            return false;
        }
        if(cboEquipoJ1.getSelectedItem().equals(cboEquipoJ2.getSelectedItem())){
            JOptionPane.showMessageDialog(rootPane, "Los jugadores deben pertenecer a equipos distintos");
            cboEquipoJ2.requestFocus();
            return false;
        }
        return true;
    }
    
    private boolean procesar(){
        if(c1.añadirJugador(new Jugador(txtNombreJ1.getText(),(Equipo)cboEquipoJ1.getSelectedItem())) &&
        c1.añadirJugador(new Jugador(txtNombreJ2.getText(),(Equipo)cboEquipoJ2.getSelectedItem()))){
            return true;
        }
        return false;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabPanJ1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNombreJ1 = new javax.swing.JTextField();
        cboEquipoJ1 = new javax.swing.JComboBox<>();
        tabPanJ2 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNombreJ2 = new javax.swing.JTextField();
        cboEquipoJ2 = new javax.swing.JComboBox<>();
        btJugar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mi Parchís - Selección de Jugadores");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setToolTipText("");

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel1.setText("Nombre:");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel2.setText("Equipo:");

        txtNombreJ1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N

        cboEquipoJ1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNombreJ1)
                    .addComponent(cboEquipoJ1, 0, 160, Short.MAX_VALUE))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombreJ1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cboEquipoJ1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        tabPanJ1.addTab("Jugador 1", jPanel1);

        jPanel2.setToolTipText("");

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel3.setText("Nombre:");

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel4.setText("Equipo:");

        txtNombreJ2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N

        cboEquipoJ2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNombreJ2)
                    .addComponent(cboEquipoJ2, 0, 160, Short.MAX_VALUE))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNombreJ2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cboEquipoJ2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        tabPanJ2.addTab("Jugador 2", jPanel2);

        btJugar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btJugar.setText("JUGAR");
        btJugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btJugarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tabPanJ2, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tabPanJ1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(btJugar)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(tabPanJ1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabPanJ2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btJugar)
                .addGap(123, 123, 123))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btJugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btJugarActionPerformed
        if(comprobar()){
            procesar();
            this.setVisible(false);
            c1.prepararTablero();
            this.dispose();
        }
    }//GEN-LAST:event_btJugarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        c1.vuelveMenuPrincipal();
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btJugar;
    private javax.swing.JComboBox<Equipo> cboEquipoJ1;
    private javax.swing.JComboBox<Equipo> cboEquipoJ2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTabbedPane tabPanJ1;
    private javax.swing.JTabbedPane tabPanJ2;
    private javax.swing.JTextField txtNombreJ1;
    private javax.swing.JTextField txtNombreJ2;
    // End of variables declaration//GEN-END:variables
}
