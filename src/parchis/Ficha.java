
package parchis;

import java.awt.Dimension;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author ricar
 */

/*
    Clase que extiende de un JLabel, será una ficha y tiene información como el equipo al que pertenece, una imagen de fondo correspondiente
    al equipo, si está en casa, en la llegada a meta o en la meta y más
*/
public class Ficha extends JLabel {

    private Equipo equipo;
    private Point posicion;
    private boolean estaEnCasa;
    private boolean haLlegado;
    private boolean casiMeta;
    private Casilla casilla = null;
    private int casillasAvanzadas;
    private boolean acabaDeComer = false;

    public Ficha(Equipo equipo, Casilla casilla) {
        this.equipo = equipo;
        //this.posicion = casilla.getPosicion_C();
        this.estaEnCasa = true;
        this.haLlegado = false;
        this.casillasAvanzadas = 0;
        this.setSize(new Dimension(30, 30));
        this.setCasilla(casilla);

        //Le ponemos la imagen de la ficha correspondiente al equipo
        this.ponerImagenFija();
        this.setVisible(true);
    }

    public Point getPosicion() {
        return posicion;
    }

    public void setPosicion(Point posicion) {
        this.posicion = posicion;
        this.setLocation(this.posicion);
    }

    public boolean isEnCasa() {
        return estaEnCasa;
    }

    public void setEstaEnCasa(boolean estaEnCasa) {
        this.estaEnCasa = estaEnCasa;
    }

    /*
        Al establecerle una nueva casilla hay que eliminarla de la casilla en la que estaba, si estaba en una, y añadirsela a la nueva casilla
    */
    public void setCasilla(Casilla casilla) {
        if (this.casilla != null) {
            this.casilla.eliminarFicha(this);
        }
        this.casilla = casilla;
        this.casilla.añadirFicha(this);
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public Casilla getCasilla() {
        return casilla;
    }

    public int getCasillasAvanzadas() {
        return casillasAvanzadas;
    }

    public void setCasillasAvanzadas(int casillasAvanzadas) {
        this.casillasAvanzadas = casillasAvanzadas;
    }

    public boolean isAcabaDeComer() {
        return acabaDeComer;
    }

    public void setAcabaDeComer(boolean acabaDeComer) {
        this.acabaDeComer = acabaDeComer;
    }

    /*
        Establece como imagen la "iluminada" que aparece cuando se pueda mover la ficha
    */
    public void ponerImagenSeleccion() {
        this.setIcon(new ImageIcon("./src/res/img/fichas/" + equipo.name() + "MV.png"));
    }

    /*
        Establece la imagen por defecto de la ficha según su equipo
    */
    public void ponerImagenFija() {
        this.setIcon(new ImageIcon("./src/res/img/fichas/" + equipo.name() + ".png"));
    }

    /*
        Hace los cambios necesarios cuando se envía una ficha a casa (a una casilla libre de casa)
    */
    public void mandarACasa(Casilla c) {
        if (!this.casiMeta) {
            this.setCasilla(c);
            this.casillasAvanzadas = 0;
            this.estaEnCasa = true;
        }
    }

    public boolean isHaLlegado() {
        return haLlegado;
    }

    public void setHaLlegado(boolean haLlegado) {
        this.haLlegado = haLlegado;
    }

    public boolean isCasiMeta() {
        return casiMeta;
    }

    public void setCasiMeta(boolean casiMeta) {
        this.casiMeta = casiMeta;
    }
}
