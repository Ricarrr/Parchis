
package parchis;

import java.io.Serializable;

/**
 *
 * @author ricar
 */

/*
    Clase que contiene las estacisticas como veces ganadas, fichas comidas y fichas en meta para un jugador, se puede serializar
*/
public class Stats implements Serializable{
    private String nombreJugador;
    private int vecesGanadas;
    private int fichasComidas;
    private int fichasMeta;

    public Stats(String nombreJugador, int fichasMeta, int fichasComidas, int vecesGanadas) {
        this.nombreJugador = nombreJugador;
        this.vecesGanadas = vecesGanadas;
        this.fichasComidas = fichasComidas;
        this.fichasMeta = fichasMeta;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }
    
    public void añadirVecesGanadas(int vecesGanadas){
        this.vecesGanadas = this.vecesGanadas + vecesGanadas;
    }
    
    public void añadirFichasComidas(int numeroComidas){
        fichasComidas = fichasComidas + numeroComidas;
    }
    
    public void añadirFichasEnMeta(int numeroFichasMeta){
        fichasMeta = fichasMeta + numeroFichasMeta;
    }

    public int getVecesGanadas() {
        return vecesGanadas;
    }

    public int getFichasComidas() {
        return fichasComidas;
    }

    public int getFichasMeta() {
        return fichasMeta;
    }
    
}
