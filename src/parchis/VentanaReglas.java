
package parchis;

import javax.swing.ImageIcon;

/**
 *
 * @author ricar
 */

/*
    Ventana con las reglas e información de la aplicación
*/
public class VentanaReglas extends javax.swing.JFrame {

    private Controladora c1;
    public VentanaReglas(Controladora c) {
        this.c1 = c;
        initComponents();
        this.setIconImage(new ImageIcon("./src/res/img/ico.png").getImage());
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mi Parchís - Reglas");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setText("\nSe trata de un parchís 1vs1 donde se enfrentarán dos jugadores, uno en el equipo azul y otro en el equipo verde.\n\n-- Objetivo --\nCada jugador tendrá 4 fichas de un color y deberá llevarlas desde casa, a través de las casillas del tablero, a la meta de su color.\nGanará el primer jugador que logre meter las 4 fichas en la meta.\n\n-- Disposición del tablero --\nEl tablero tiene 68 casillas numeradas por las que harán el recorrido las fichas, hay 4 casas de 4 colores con su respectiva casilla\nde salida, sus 9 casillas de llegada y la casilla de meta.\nHay casillas especiales que tienen un círculo en su interior, eso indica que la casilla es segura (comodín) y ninguna ficha podrá ser comida\nmientras se encuentre dentro de ella.\nA la derecha del tablero aparece información extra, se trata de una ayuda que indica de quién es el turno y la acción que puede realizar\nasí como una casilla en la que a veces aparece información extra. Debajo hay una tabla con los jugadores y las estadísticas de la partida.\nTambién se ve una imágen con un altavoz, esta indica si el juego reproducirá sonidos o no, para alternar basa con hacer click sobre ella.\n\n-- Mecánica --\nCada jugador dispone de un botón de lanzar dado y en el centro del mapa saldrá el dado con el resultado, que será un número aleatorio\nentre 1 y 6.\nLos jugadores empezarán el juego con todas las fichas en casa y estarán obligados a sacar una ficha cuando saquen un 5 y la casilla\nde salida no esté obstruída por dos fichas suyas, si está obstruída pero hay alguna ficha del equipo rival debe sacar la ficha comiéndose\nasí al rival.\nCuando dos fichas están en la misma casilla, solo pueden ser de distinto equipo cuando estén en una casilla comodín, si son del mismo\nequipo la casilla se bloquea y nadie puede pasar el bloqueo hasta que el dueño de las fichas lo rompa, si son de distinto equipo cualquier\nficha puede pasar.\nEl dado indica el numero de casillas que siempre debe avanzar una ficha, salvo que no pueda. \nEn el caso de que el jugador saque un 6 y no tenga ninguna ficha en casa, el 6 pasará a valer 7.\nSi un jugador saca un 6 y tiene algún bloqueo está obligado a romper el bloqueo.\nSi un jugador saca un 6 si puede mueve y además tira otra vez, pero si saca un 6 tres veces consecutivas la última ficha movida volverá a casa,\nsalvo que esté en las casillas de llegada a meta.\nUna ficha come a otra si al mover acaba en la misma casilla y esta no es un comodín, además de que las fichas sean de distinto color, si todo\nse cumple el jugador que haya comido tiene la oportunicad de avanzar 20 posiciones con cualquier ficha siempre que le sea posible.\nPara meter una ficha en la meta esta debe sacar el número justo, si se pasa no se podrá mover, y cuando entre en la meta el jugador podrá\nmover cualquier ficha 10 posiciones, siempre se sea posible.\n\nSolo se podrán mover las fichas que se iluminen, y estas se iluminarán siempre que se cumplan las mecánicas anteriores.\nUna vez elegida la ficha a mover basta con clicar en ella y avanzará sola a la posición correspondiente.");
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 797, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 597, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        c1.vuelveMenuPrincipal();
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
