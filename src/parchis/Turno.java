
package parchis;

/**
 *
 * @author ricar
 */

/*
    Clase encargada de controlar los turnos
*/
public class Turno {

    private Jugador turnoDe;
    private Jugadores jugadores;
    private VentanaTablero tablero;

    public Turno(Jugadores jugadores, VentanaTablero tablero) {
        this.jugadores = jugadores;
        turnoDe = jugadores.get(0);
        this.tablero = tablero;
    }

    public void siguienteTurno() {
        for(int i = 0;i < jugadores.size();i++){
            if(jugadores.get(i).equals(turnoDe)){
                turnoDe.resetearTiradas();
                if(i == jugadores.size()-1){
                    turnoDe = jugadores.get(0);
                    break;
                }else{
                    turnoDe = jugadores.get(i+1);
                    break;
                }
                
            }
        }
        tablero.turnoDe(turnoDe.getEquipo());
        tablero.setTextoAccionTirar();
    }
    
    public void vuelveAToacar(){
        tablero.turnoDe(turnoDe.getEquipo());
        tablero.setTextoAccionVolverATirar();
    }

    public Jugador getTurnoDe() {
        return turnoDe;
    }
    
    public boolean turnoDe(Jugador j){
        return j.equals(this.turnoDe);
    }
}
