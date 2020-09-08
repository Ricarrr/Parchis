package parchis;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;

/**
 *
 * @author ricar
 */

/*
    Ventana donde se desarrolla el juego, contiene el tablero e información mientras se desarrolla la partida
*/
public class VentanaTablero extends javax.swing.JFrame {

    private Controladora c1;

    public VentanaTablero(Controladora c) {
        this.c1 = c;
        initComponents();
        this.setIconImage(new ImageIcon("./src/res/img/ico.png").getImage());
        lblMute.setIcon(new ImageIcon(new ImageIcon("./src/res/img/unmute.png").getImage().getScaledInstance(lblMute.getWidth(), lblMute.getHeight(), Image.SCALE_SMOOTH)));
    }

    public void turnoDe(Equipo e) {
        if (e.equals(Equipo.AZUL)) {
            btTirarAzul.setEnabled(true);
            btTirarVerde.setEnabled(false);
        } else {
            btTirarVerde.setEnabled(true);
            btTirarAzul.setEnabled(false);
        }
        txtTurno.setText(c1.getJugadorDeEquipo(e).getNombre() + " - " + e.name());
    }

    public void bloqueaBotones() {
        btTirarVerde.setEnabled(false);
        btTirarAzul.setEnabled(false);
    }

    public void setNumeroDado(int numero) {
        if (numero >= 1 && numero <= 6) {
            dado.setIcon(new ImageIcon("./src/res/img/dado/" + numero + ".png"));
        }
    }    

    /*
        Métodos para cambiar los textos
    */
    public void setTextAccionVacio() {
        this.txtAccion.setText("");
    }

    public void setTextoAccionSacarFicha() {
        this.txtAccion.setText(" SACAR FICHA");
    }

    public void setTextoAccionMoverFicha() {
        this.txtAccion.setText(" MOVER FICHA");
    }

    public void setTextoAccionRomperBloqueo() {
        this.txtAccion.setText(" ROMPER BLOQUEO");
    }

    public void setTextoAccionVolverATirar() {
        this.txtAccion.setText(" VUELVE A TIRAR");
    }

    public void setTextoAccionTirar() {
        this.txtAccion.setText(" TIRAR DADO");
    }

    public void setTextoExtra6Vale7() {
        this.txtExtra.setText(" EL 6 VALE 7 por tener todas las fichas fuera");
    }

    public void setTextoExtraTresVeces6() {
        this.txtExtra.setText(" 3 VECES SEGUIDAS UN 6, tu ficha vuelve a casa");
    }

    public void setTextoExtraFichaComida() {
        this.txtExtra.setText(" FICHA COMIDA, AVANZAS 20 CASILLAS");
    }

    public void setTextoExtraFichaLLegaMeta() {
        this.txtExtra.setText(" FICHA EN META, AVANZAS 10 CASILLAS");
    }

    public void setTextoExtraVacio() {
        this.txtExtra.setText("");
    }

    /*
        Rellena la tabla de estadísticas por primera vez
    */
    public void iniciaTablaStats(Jugador j1, Jugador j2) {
        tblStats.setValueAt(j1.getNombre(), 0, 0);
        tblStats.setValueAt(j2.getNombre(), 1, 0);
        tblStats.setValueAt(0, 0, 1);
        tblStats.setValueAt(0, 0, 2);
        tblStats.setValueAt(0, 1, 1);
        tblStats.setValueAt(0, 1, 2);
    }

    /*
        Suma una ficha en meta de la tabla estadísticas a un jugador
    */
    public void statsSumaFichaMeta(Jugador j) {
        if (j.getNombre().equals(tblStats.getValueAt(0, 0))) {
            tblStats.setValueAt((Integer) tblStats.getValueAt(0, 1) + 1, 0, 1);
        } else {
            tblStats.setValueAt((Integer) tblStats.getValueAt(1, 1) + 1, 1, 1);
        }
    }

    /*
        Suma una ficha comida de la tabla estadísticas a un jugador
    */
    public void statsSumaFichaComida(Jugador j) {
        if (j.getNombre().equals(tblStats.getValueAt(0, 0))) {
            tblStats.setValueAt((Integer) tblStats.getValueAt(0, 2) + 1, 0, 2);
        } else {
            tblStats.setValueAt((Integer) tblStats.getValueAt(1, 2) + 1, 1, 2);
        }
    }

    /*
        Devuelve un ArrayList con los Stats recorriéndolos de la tabla
    */
    public ArrayList<Stats> getStats() {
        ArrayList<Stats> e = new ArrayList<Stats>();
        e.add(new Stats((String) tblStats.getValueAt(0, 0), (Integer) tblStats.getValueAt(0, 1), (Integer) tblStats.getValueAt(0, 2), 0));
        e.add(new Stats((String) tblStats.getValueAt(1, 0), (Integer) tblStats.getValueAt(1, 1), (Integer) tblStats.getValueAt(1, 2), 0));
        return e;
    }

    private void tirar() {
        try {
            //Como tiene sonidos (tirar y mover ficha) comprueba si tienen que sonar o no (el usuario pulsó el boton de mute o no)
            if (!c1.isMute()) {
                Clip sonido = AudioSystem.getClip();
                sonido.open(AudioSystem.getAudioInputStream(new File("./src/res/snd/dado.wav")));
                sonido.start();
            }

            c1.tirar(c1.getTurno().getTurnoDe());
            this.setTextoExtraVacio();
        } catch (LineUnavailableException ex) {
            Logger.getLogger(VentanaTablero.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(VentanaTablero.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(VentanaTablero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btTirarVerde = new javax.swing.JButton();
        dado = new javax.swing.JLabel();
        btTirarAzul = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTurno = new javax.swing.JTextField();
        txtAccion = new javax.swing.JTextField();
        txtExtra = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblStats = new javax.swing.JTable();
        lblMute = new javax.swing.JLabel();
        fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mi Parchís - Tablero");
        setIconImages(null);
        setMinimumSize(new java.awt.Dimension(1060, 730));
        setModalExclusionType(null);
        setResizable(false);
        setSize(new java.awt.Dimension(1060, 730));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btTirarVerde.setText("TIRAR");
        btTirarVerde.setEnabled(false);
        btTirarVerde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTirarVerdeActionPerformed(evt);
            }
        });
        getContentPane().add(btTirarVerde, new org.netbeans.lib.awtextra.AbsoluteConstraints(178, 458, -1, -1));

        dado.setIcon(new javax.swing.ImageIcon("C:\\Users\\ricar\\Documents\\NetBeansProjects\\Parchis2\\img\\dado\\0.png")); // NOI18N
        getContentPane().add(dado, new org.netbeans.lib.awtextra.AbsoluteConstraints(316, 316, -1, -1));

        btTirarAzul.setText("TIRAR");
        btTirarAzul.setEnabled(false);
        btTirarAzul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTirarAzulActionPerformed(evt);
            }
        });
        getContentPane().add(btTirarAzul, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 216, -1, -1));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 255), 2));

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel1.setText("Turno de:");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel2.setText("Acción posible:");

        txtTurno.setEditable(false);

        txtAccion.setEditable(false);

        txtExtra.setEditable(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 255), 2), "STATS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12))); // NOI18N
        jPanel2.setFocusable(false);

        jScrollPane1.setEnabled(false);
        jScrollPane1.setFocusable(false);
        jScrollPane1.setMaximumSize(new java.awt.Dimension(22, 24));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(22, 24));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(22, 24));

        tblStats.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        tblStats.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "", "Fichas en Meta", "Fichas comidas"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class
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
        tblStats.setEnabled(false);
        tblStats.setFocusable(false);
        tblStats.setRequestFocusEnabled(false);
        tblStats.setRowSelectionAllowed(false);
        tblStats.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblStats);
        if (tblStats.getColumnModel().getColumnCount() > 0) {
            tblStats.getColumnModel().getColumn(0).setResizable(false);
            tblStats.getColumnModel().getColumn(0).setPreferredWidth(100);
            tblStats.getColumnModel().getColumn(1).setResizable(false);
            tblStats.getColumnModel().getColumn(1).setPreferredWidth(140);
            tblStats.getColumnModel().getColumn(2).setResizable(false);
            tblStats.getColumnModel().getColumn(2).setPreferredWidth(140);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtExtra)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAccion, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 37, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtAccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtExtra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 40, 320, 200));

        lblMute.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMuteMouseClicked(evt);
            }
        });
        getContentPane().add(lblMute, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 250, 40, 40));

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/img/tablero/mitablero.png"))); // NOI18N
        fondo.setFocusable(false);
        fondo.setName(""); // NOI18N
        getContentPane().add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btTirarVerdeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTirarVerdeActionPerformed
        tirar();
    }//GEN-LAST:event_btTirarVerdeActionPerformed

    private void btTirarAzulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTirarAzulActionPerformed
        tirar();
    }//GEN-LAST:event_btTirarAzulActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        c1.vuelveMenuPrincipal();
        this.setVisible(false);
        c1.recuperarDatos();
        this.dispose();
    }//GEN-LAST:event_formWindowClosing

    private void lblMuteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMuteMouseClicked
        if(c1.isMute()){
            lblMute.setIcon(new ImageIcon(new ImageIcon("./src/res/img/unmute.png").getImage().getScaledInstance(lblMute.getWidth(), lblMute.getHeight(), Image.SCALE_SMOOTH)));
            c1.setMute(false);
        }else{
            lblMute.setIcon(new ImageIcon(new ImageIcon("./src/res/img/mute.png").getImage().getScaledInstance(lblMute.getWidth(), lblMute.getHeight(), Image.SCALE_SMOOTH)));
            c1.setMute(true);
        }
    }//GEN-LAST:event_lblMuteMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btTirarAzul;
    private javax.swing.JButton btTirarVerde;
    private javax.swing.JLabel dado;
    private javax.swing.JLabel fondo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblMute;
    private javax.swing.JTable tblStats;
    private javax.swing.JTextField txtAccion;
    private javax.swing.JTextField txtExtra;
    private javax.swing.JTextField txtTurno;
    // End of variables declaration//GEN-END:variables
}
