
package parchis;

import java.util.HashMap;
import java.util.TreeMap;

/**
 *
 * @author ricar
 */

/*
    Esta clase extiende de HashMap y contendrá para cada equipo las casillas correspondientes a su casa, salida, llegada a meta y meta
*/
public class Casas extends HashMap<Equipo, TreeMap<Integer, Casilla>> {

    /*
        Devuelve un TreeMap de las casillas correspondientes a casa
    */
    public TreeMap<Integer, Casilla> devuelveCasas(Equipo e) {
        TreeMap<Integer, Casilla> casas = new TreeMap<Integer, Casilla>();

        for (Entry<Equipo, TreeMap<Integer, Casilla>> obj : this.entrySet()) {
            if (obj.getKey().equals(e)) {
                casas.put(11, obj.getValue().get(11));
                casas.put(12, obj.getValue().get(12));
                casas.put(13, obj.getValue().get(13));
                casas.put(14, obj.getValue().get(14));
            }
        }
        return casas;
    }

    /*
        Devuelve un TreeMap de las casillas correspondientes a la meta
    */
    public TreeMap<Integer, Casilla> devuelveMeta(Equipo e) {
        TreeMap<Integer, Casilla> casas = new TreeMap<Integer, Casilla>();

        for (Entry<Equipo, TreeMap<Integer, Casilla>> obj : this.entrySet()) {
            if (obj.getKey().equals(e)) {
                casas.put(15, obj.getValue().get(15));
                casas.put(16, obj.getValue().get(16));
                casas.put(17, obj.getValue().get(17));
                casas.put(18, obj.getValue().get(18));
            }
        }
        return casas;
    }

    /*
        Devuelve una casilla si hubiera alguna casilla de casa libre, sin fichas, sino devuelve null
    */
    public Casilla getCasaLibre(Equipo e) {
        Casilla c = null;
        for (Casilla c1 : this.devuelveCasas(e).values()) {
            if (!c1.tieneFicha()) {
                c = c1;
                break;
            }
        }
        return c;
    }
    
}
