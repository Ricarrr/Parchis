
package parchis;

import java.util.Comparator;

/**
 *
 * @author ricar
 */

/*
    Clase para ordenar la tabla de ranking comparando las victorias
*/
public class ComparadoraStatsVictorias implements Comparator<Stats>{
    @Override
    public int compare(Stats o1, Stats o2) {
        if(o1.getVecesGanadas()<o2.getVecesGanadas()){
            return 1;
        }else if (o1.getVecesGanadas()>o2.getVecesGanadas()){
            return -1;
        }else{
            return 0;
        }
    }
    
}
