
package parchis;

import java.awt.Point;
import java.util.TreeMap;

/**
 *
 * @author ricar
 */

/*
    Clase que extiende de un TreeMap que contendrá como clave un numero entero que se corresponde con el número de la casilla que contiene
    Funciona como si fuese un mapa del tablero
*/
public class Casillas extends TreeMap<Integer,Casilla>{

    public Casillas() {
        super();
        generaCasillas();
    }
    
    /*
        Genera las casillas del recorrido en función de las coordenadas, lo he calculado a mano
    */
    private void generaCasillas() {
        int x = 407;
        int y = 668;
        int i = 1;
        while (i >= 1 && i <= 8) {
            if (i == 5) {
                this.put(i, new Casilla(i, new Point(x, y), true,new Point(x-15, y),new Point(x+15, y)));
            } else {
                this.put(i, new Casilla(i, new Point(x, y), false,new Point(x-15, y),new Point(x+15, y)));
            }
            y = y - 35;
            i++;
        }
        x = 423;
        y = 406;
        while (i >= 9 && i <= 16) {
            if (i == 12) {
                this.put(i, new Casilla(i, new Point(x, y), true,new Point(x, y-15),new Point(x, y+15)));
            } else {
                this.put(i, new Casilla(i, new Point(x, y), false,new Point(x, y-15),new Point(x, y+15)));
            }
            x = x + 35;
            i++;
        }
        while (i >= 17 && i <= 25) {
            if (i == 17) {
                x = x - 35;
                y = 336;
                this.put(i, new Casilla(i, new Point(x, y), true,new Point(x, y-15),new Point(x, y+15)));
            } else if (i == 18) {
                y = y - 70;
                this.put(i, new Casilla(i, new Point(x, y), false,new Point(x, y-15),new Point(x, y+15)));
                x = x - 35;
            } else {
                if (i == 22) {
                    this.put(i, new Casilla(i, new Point(x, y), true,new Point(x, y-15),new Point(x, y+15)));
                } else {
                    this.put(i, new Casilla(i, new Point(x, y), false,new Point(x, y-15),new Point(x, y+15)));
                }
                x = x - 35;
            }
            i++;
        }
        x = 407;
        y = 248;
        while (i >= 26 && i <= 33) {
            if (i == 29) {
                this.put(i, new Casilla(i, new Point(x, y), true,new Point(x-15, y),new Point(x+15, y)));
            } else {
                this.put(i, new Casilla(i, new Point(x, y), false,new Point(x-15, y),new Point(x+15, y)));
            }
            y = y - 35;
            i++;
        }
        while (i >= 34 && i <= 42) {
            if (i == 34) {
                y = y + 35;
                x = 336;
                this.put(i, new Casilla(i, new Point(x, y), true,new Point(x-15, y),new Point(x+15, y)));
            } else if (i == 35) {
                x = x - 70;
                this.put(i, new Casilla(i, new Point(x, y), false,new Point(x-15, y),new Point(x+15, y)));
                y = y + 35;
            } else {
                if (i == 39) {
                    this.put(i, new Casilla(i, new Point(x, y), true,new Point(x-15, y),new Point(x+15, y)));
                } else {
                    this.put(i, new Casilla(i, new Point(x, y), false,new Point(x-15, y),new Point(x+15, y)));
                }
                y = y + 35;
            }
            i++;
        }
        int a = y;
        int b = x - 35;
        x = 248;
        y = 266;
        while (i >= 43 && i <= 50) {
            if (i == 46) {
                this.put(i, new Casilla(i, new Point(x, y), true,new Point(x, y-15),new Point(x, y+15)));
            } else {
                this.put(i, new Casilla(i, new Point(x, y), false,new Point(x, y-15),new Point(x, y+15)));
            }
            x = x - 35;
            i++;
        }
        x = x + 35;
        y = y + 70;
        this.put(i, new Casilla(i, new Point(x, y), true,new Point(x, y-15),new Point(x, y+15)));
        i++;
        y = y + 70;
        while (i >= 52 && i <= 59) {
            if (i == 56) {
                this.put(i, new Casilla(i, new Point(x, y), true,new Point(x, y-15),new Point(x, y+15)));
            } else {
                this.put(i, new Casilla(i, new Point(x, y), false,new Point(x, y-15),new Point(x, y+15)));
            }
            x = x + 35;
            i++;
        }
        x = 266;
        y = 423;
        while (i >= 60 && i <= 67) {
            if (i == 63) {
                this.put(i, new Casilla(i, new Point(x, y), true,new Point(x-15, y),new Point(x+15, y)));
            } else {
                this.put(i, new Casilla(i, new Point(x, y), false,new Point(x-15, y),new Point(x+15, y)));
            }
            y = y + 35;
            i++;
        }
        this.put(i, new Casilla(i, new Point(336, 668), true,new Point(336-15, 668),new Point(336+15, 668)));
    }
}
