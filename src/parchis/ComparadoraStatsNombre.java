
package parchis;

import java.util.Comparator;

/**
 *
 * @author ricar
 */

/*
    Clase para ordenar la tabla de ranking comparando los nombres de los jugadores
*/
public class ComparadoraStatsNombre implements Comparator<Stats>{
    @Override
    public int compare(Stats o1, Stats o2) {
        return o1.getNombreJugador().compareTo(o2.getNombreJugador());
    }
    
}
