
package parchis;

import java.util.ArrayList;

/**
 *
 * @author ricar
 */

/*
    Clase que extiende de un ArrayList que contendrá Jugadores
*/
public class Jugadores extends ArrayList<Jugador>{
    public boolean añadirJugador(Jugador j){
        for(Jugador jug:this){
            if(jug.getNombre().equals(j.getNombre())){
                return false;
            }
        }
        this.add(j);
        return true;
    }
}
