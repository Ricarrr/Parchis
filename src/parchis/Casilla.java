
package parchis;

import java.awt.Point;

/**
 *
 * @author ricar
 */

/*
    Clase casilla, contiene el numero, distintas posiciones y valores como si es puente, comodín o las fichas que contiene
    Una casilla es algo abstracto que se corresponde con unos valores X e Y de la pantalla, está pensado para que las casillas queden bien
    con la imagen de fondo (un tablero de parchís)
*/

public class Casilla {
    private int numero;
    private Point posicion_C;
    private Point posicion_I;
    private Point posicion_D;
    private boolean esComodin;
    private Fichas fichas;
    private boolean bloqueada;
    private boolean esPuente;

    public Casilla(int numero, Point posicion_C, boolean esComodin) {
        this.numero = numero;
        this.posicion_C = posicion_C;
        this.esComodin = esComodin;
        this.fichas = new Fichas();
        this.bloqueada = false;
    }
    
    public Casilla(int numero, Point posicion_C, boolean esComodin, Point posicion_I, Point posicion_D) {
        this.numero = numero;
        this.posicion_C = posicion_C;
        this.esComodin = esComodin;
        this.posicion_I = posicion_I;
        this.posicion_D = posicion_D;
        this.fichas = new Fichas();
        this.bloqueada = false;
        this.esPuente = false;
    }

    public boolean isEsComodin() {
        return esComodin;
    }

    public int getNumero() {
        return numero;
    }

    public Point getPosicion_C() {
        return posicion_C;
    }

    public boolean isBloqueada() {
        return bloqueada;
    }
    
    /*
        Hará las comprobaciones pertinentes y dará los valores adecuados de posición a las fichas que contiene en función de si hay una o dos
    */
    public boolean añadirFicha(Ficha f){
        //Si solo hay una ficha se puede añadir otra
        if(!this.isEsPuente()){
            if(fichas.size()==1){
                fichas.add(f);
                //Si ya había una y añadimos otra se forma un puente
                this.esPuente = true;
                //Si el puente es de fichas del mismo equipo la casilla bloquea el paso
                if(fichas.get(0).getEquipo().equals(fichas.get(1).getEquipo())){
                    this.bloqueada = true;
                }
                fichas.get(0).setPosicion(posicion_I);
                fichas.get(1).setPosicion(posicion_D);
                return true;
            }else if (fichas.size()==0){
                fichas.add(f);
                fichas.get(0).setPosicion(posicion_C);
                return true;
            }
        }
        return false;
    }
    
    /*
        Hace todo lo necesario al eliminar una ficha de una casilla
    */
    public void eliminarFicha(Ficha f2){
        for(Ficha f:fichas){
            if(f2.equals(f)){
                fichas.remove(f);
                //Al quitar una ficha se elimina el puente y el bloqueo
                this.bloqueada = false;
                this.esPuente = false;
                break;
            }
        }
        if(tieneFicha()){
            fichas.get(0).setPosicion(posicion_C);
        }
    }
    
    public boolean tieneFicha(){
        return fichas.size()>=1;
    }

    public Fichas getFichas() {
        return fichas;
    }

    public Point getPosicion_I() {
        return posicion_I;
    }

    public Point getPosicion_D() {
        return posicion_D;
    }

    public boolean isEsPuente() {
        return esPuente;
    }
    
    public Ficha devuelveFicha(){
        Ficha ficha = null;
        for(int i = 0; i < fichas.size();i++){
            if(fichas.get(i)!=null){
                ficha = fichas.get(i);
                break;
            }
        }
        return ficha;
    }
    
    public Ficha getFichaDistEquipoCasSal(Equipo e){
        Ficha f1 = null;
        for(Ficha f : fichas){
            if(!f.getEquipo().equals(e)){
                f1 = f;
            }
        }
        return f1;
    }
    
    
}
