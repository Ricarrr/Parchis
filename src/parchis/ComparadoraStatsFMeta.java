
package parchis;

import java.util.Comparator;

/**
 *
 * @author ricar
 */

/*
    Clase para ordenar la tabla de ranking comparando las fichas en meta
*/
public class ComparadoraStatsFMeta implements Comparator<Stats>{
    @Override
    public int compare(Stats o1, Stats o2) {
        if(o1.getFichasMeta()<o2.getFichasMeta()){
            return 1;
        }else if (o1.getFichasMeta()>o2.getFichasMeta()){
            return -1;
        }else{
            return 0;
        }
    }
    
}
