
package parchis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author ricar
 */

/*
    Clase que extiende de HashMap que tiene como clave un String que corresponde con el nombre de un jugador y contiene un objeto Stats
    que se correspondería con sus estadísticas
*/
public class Estadisticas extends HashMap<String, Stats> implements Serializable {

    /*
        Añade (crea o suma) las estadísticas pasadas
    */
    public void añadir(ArrayList<Stats> stats) {
        //Por cada estadística a añadir
        for (Stats es : stats) {
            //Si el jugador ya tiene datos
            if (existeStat(es)) {
                //Se suman los nuevos
                Stats e1 = this.get(es.getNombreJugador());
                e1.añadirFichasComidas(es.getFichasComidas());
                e1.añadirFichasEnMeta(es.getFichasMeta());
                e1.añadirVecesGanadas(es.getVecesGanadas());
            }else{
                //Si aun no tiene datos se crea la nueva estadística para el
                this.put(es.getNombreJugador(), es);
            }    
        }
    }

    /*
        Devuelve true si ya existen estadísticas para las estadísticas de un jugador pasadas
    */
    private boolean existeStat(Stats es) {
        for (Entry<String, Stats> e : this.entrySet()) {
            if (e.getKey().equals(es.getNombreJugador())) {
                return true;
            }
        }
        return false;
    }

}
