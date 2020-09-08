
package parchis;

/**
 *
 * @author ricar
 */

/*
    Clase que contiene los datos de un jugador como el nombre, equipo, las fichas, etc.
*/
public class Jugador {

    private String nombre;
    private Equipo equipo;
    private Fichas fichas;
    private int numTirada = 0;
    private Ficha ultimaFichaMovida;
    private boolean haGanado = false;

    public Jugador(String nombre, Equipo equipo) {
        this.nombre = nombre;
        this.equipo = equipo;
    }

    public String getNombre() {
        return nombre;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public Fichas getFichas() {
        return fichas;
    }

    public void setFichas(Fichas fichas) {
        this.fichas = fichas;
    }

    public int getNumTirada() {
        return numTirada;
    }

    /*
        Devuelve true si tiene alguna ficha fuera de casa
    */
    public boolean isHaSalido() {
        for (Ficha f : fichas) {
            if (!f.isEnCasa()) {
                return true;
            }
        }
        return false;
    }

    /*
        Devuelve true si tiene alguna ficha fuera en casa
    */
    public boolean tieneFichasCasa() {
        for (Ficha f : fichas) {
            if (f.isEnCasa()) {
                return true;
            }
        }
        return false;
    }

    /*
        Devuelve las fichas que tenga en casa, si no tiene el array estará vacío
    */
    public Fichas getFichasEnCasa() {
        Fichas f = new Fichas();
        for (Ficha f2 : fichas) {
            if (f2.isEnCasa()) {
                f.add(f2);
            }
        }
        return f;
    }

    /*
        Devuelve las fichas que tenga fuera de casa, si no tiene el array estará vacío
    */
    public Fichas getFichasFueraDeCasa() {
        Fichas f = new Fichas();
        for (Ficha f2 : fichas) {
            if (!f2.isEnCasa() && !f2.isHaLlegado()) {
                f.add(f2);
            }
        }
        return f;
    }

    /*
        Devuelve las fichas que tenga en casillas bloqueadas, si no tiene el array estará vacío
    */
    public Fichas getFichasConBloqueo() {
        Fichas f = new Fichas();
        for (Ficha f2 : fichas) {
            if (f2.getCasilla().isBloqueada()) {
                f.add(f2);
            }
        }
        return f;
    }

    public void añadirTirada() {
        numTirada++;
    }

    public void resetearTiradas() {
        numTirada = 0;
    }

    public void resetearUltimaFicha() {
        ultimaFichaMovida = null;
    }

    public Ficha getUltimaFichaMovida() {
        return ultimaFichaMovida;
    }

    public void setUltimaFichaMovida(Ficha ultimaFichaMovida) {
        this.ultimaFichaMovida = ultimaFichaMovida;
    }

    /*
        Devuelve true si las fichas del jugador forman algún puente
    */
    public boolean tienePuente() {
        for (Ficha f : fichas) {
            if (f.getCasilla().isEsPuente() && !f.isHaLlegado()) {
                return true;
            }
        }
        return false;
    }
    
    /*
        Devuelve true si las fichas del jugador forman algún bloqueo
    */
    public boolean tieneBloqueo() {
        for (Ficha f : fichas) {
            if (f.getCasilla().isBloqueada()&& !f.isHaLlegado()) {
                return true;
            }
        }
        return false;
    }

    /*
        Devuelve true si el jugador ha ganado
    */
    public boolean haGanado() {
        for (Ficha f : this.fichas) {
            if (!f.isHaLlegado()) {
                return false;
            }
        }
        this.haGanado = true;
        return true;
    }
}
