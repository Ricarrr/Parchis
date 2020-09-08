package parchis;

/**
 *
 * @author ricar
 */
/*
    Clase que contiene varios métodos estáticos relacionados con el movimiento de las fichas
*/
public class Movimiento {

    /*
        Cuando se clicka en una ficha para moverse se llama a este método
        Tiene en cuenta las reglas y permitirá un movimiento u otro según estas
    */
    public static boolean moverFicha(Controladora c1, Ficha f, int res) {

        //Primero mueve la ficha dada las posiciones dadas
        mover(c1, f, res);

        c1.getTablero().bloqueaBotones();
        Jugador j = c1.getDueñoDeFicha(f);
        //Si con el útimo movimiento ha ganado se acaba la partida
        if (j.haGanado()) {
            c1.finPartida(j);
        } else {
            //Si aun no ha ganado
            if (j != null) {
                //Se le asigna la última ficha movida
                j.setUltimaFichaMovida(f);
            }
            //Si la ficha ha llegado a meta se tiene que sumar 10
            if (f.isHaLlegado()) {
                c1.getTablero().statsSumaFichaMeta(c1.getDueñoDeFicha(f));
                c1.elegirMovimiento(c1, c1.getDueñoDeFicha(f).getFichasFueraDeCasa(), 10);
                return true;
            }
            //Si la ficha comió a otra el jugador debe realizar 20 movimientos
            if (f.isAcabaDeComer()) {
                c1.getTablero().statsSumaFichaComida(c1.getDueñoDeFicha(f));
                c1.elegirMovimiento(c1, c1.getDueñoDeFicha(f).getFichasFueraDeCasa(), 20);
                f.setAcabaDeComer(false);
            } else {
                //Si sacó un 6 o 7 le toca otra vez sino pasa de turno
                if (res == 6 || res == 7) {
                    c1.getTurno().vuelveAToacar();
                } else {
                    c1.getTurno().siguienteTurno();
                }
            }

        }

        return true;
    }

    /*
        Método encargado de mover una ficha dada las posiciones dadas teniendo en cuenta varios factores
    */
    private static void mover(Controladora c1, Ficha f, int res) {
        Casilla sigCas = null;
        int casillasAvanzadas = f.getCasillasAvanzadas();

        //Miro si ya está en casa
        if (casillasAvanzadas > 63 && !f.isHaLlegado()) {
            if (casillasAvanzadas + res == 71) {
                f.setHaLlegado(true);
                for (Casilla c : c1.getCasas().devuelveMeta(f.getEquipo()).values()) {
                    if (!c.tieneFicha()) {
                        sigCas = c;
                    }
                }
            } else {
                sigCas = c1.getCasas().get(f.getEquipo()).get(f.getCasilla().getNumero() + res + 2);
            }
            f.setCasillasAvanzadas(casillasAvanzadas + res);
        } else {
            //Averiguo si debe entrar en casa o no en este movimiento
            if (casillasAvanzadas + res > 63) {
                //Se suma de uno en uno las casillas avanzadas (y se resta de uno en uno el resultado) hasta que de más de 63
                // que en ese caso la casilla final será la del treeMap de las casas correspondiente al resto de la tirada
                f.setCasiMeta(true);
                for (int i = res; i > 0; i--) {
                    if (f.getCasillasAvanzadas() + 1 <= 63) {
                        f.setCasillasAvanzadas(f.getCasillasAvanzadas() + 1);
                    } else {
                        //Se le suma dos porque en el treeMap de las casas la 1 es en la que sale y la 2 es en la que empieza a entrar
                        sigCas = c1.getCasas().get(f.getEquipo()).get(i + 2);
                        //Si el numero de la casilla es 8 es que ya es la casilla de la meta
                        if (sigCas.getNumero() == 8) {
                            f.setHaLlegado(true);
                            for (Casilla c : c1.getCasas().devuelveMeta(f.getEquipo()).values()) {
                                if (!c.tieneFicha()) {
                                    sigCas = c;
                                }
                            }
                        }
                        f.setCasillasAvanzadas(f.getCasillasAvanzadas() + i);
                        break;
                    }
                }
            } else {
                //Si no debe entrar en casa la casilla final será la correspondiente a sumar el resultado
                int siguientePos = f.getCasilla().getNumero() + res > 68 ? f.getCasilla().getNumero() + res - 68 : f.getCasilla().getNumero() + res;
                sigCas = c1.getCasillas().get(siguientePos);
                f.setCasillasAvanzadas(casillasAvanzadas + res);

                //Si en la casilla final hay ya una ficha, es de distinto equipo a la que se mueve y la casilla no es comodín
                //La ficha movida se come a la que estaba en la casilla
                if (sigCas.tieneFicha() && !sigCas.devuelveFicha().getEquipo().equals(f.getEquipo()) && !sigCas.isEsComodin()) {
                    //Mandamos a casa a la ficha que estaba en la casilla
                    sigCas.devuelveFicha().mandarACasa(c1.getCasas().getCasaLibre(sigCas.devuelveFicha().getEquipo()));

                    f.setAcabaDeComer(true);
                }
            }
        }

        //Una vez que se sabe la casilla final hay que 
        // Eliminar la ficha de la casilla en la que estaba
        f.getCasilla().eliminarFicha(f);
        // Establecer la nueva casilla a la ficha
        f.setCasilla(sigCas);
        //Las casillas avanzadas se suman antes
    }

    /*
        Método que devuelve true si dada una ficha y un numero se puede mover esa cantidad de casillas
    */
    public static boolean puedeMoverse(Controladora c1, Ficha f, int res) {
        if (!f.isEnCasa()) {
            int numCasilla = f.getCasilla().getNumero();
            if (f.getCasillasAvanzadas() >= 63) {
                if (f.isHaLlegado()) {
                    //Si ha llegado ya no se puede mover
                    return false;
                } else {
                    //Sino, solo puede mover si no se pasa (>71 ya se pasa)
                    return f.getCasillasAvanzadas() + res <= 71;
                }
            } else {
                //Si no está cerca de la meta pero se pasaría (si cuenta 10 o 20 por meter ficha o por comer) devuelve false
                if (f.getCasillasAvanzadas() + res > 71) {
                    return false;
                } else {
                    //Hay que comprobar casilla por casilla las siguientes que avanzaría
                    for (int i = 1; i <= res; i++) {
                        //Sino, hay que tener en cuenta si entraría en casa o no, es decir si las casillas avanzadas son 63 la siguiente es entrar en casa
                        if (f.getCasillasAvanzadas() + i <= 63) {
                            if (numCasilla + i > 68) {
                                if (c1.getCasillas().get(numCasilla + i - 68).isBloqueada()) {
                                    return false;
                                }
                            } else {
                                if (c1.getCasillas().get(numCasilla + i).isBloqueada()) {
                                    return false;
                                }
                            }
                        } else {
                            return true;
                        }
                    }

                    //Si la casilla final es un puente no se puede quedar en ella
                    if (numCasilla + res > 68) {
                        if (c1.getCasillas().get(numCasilla + res - 68).isEsPuente()) {
                            return false;
                        }
                    } else {
                        if (c1.getCasillas().get(numCasilla + res).isEsPuente()) {
                            return false;
                        }
                    }
                }
            }
        } else {
            //Si está en casa
            //Si saca un 5 y la casilla de salida no tiene un puente él mismo devuelve true
            return (res == 5 && !(c1.getCasas().get(f.getEquipo()).get(1).isBloqueada() && c1.getCasas().get(f.getEquipo()).get(1).getFichas().get(0).getEquipo().equals(f.getEquipo())));
        }
        return true;
    }

    /*
        Método encargado de sacar una ficha de casa a su casilla de inicio
    */
    public static void sacarFicha(Ficha f, Controladora c) {
        Casilla casa = c.getCasas().get(f.getEquipo()).get(1);
        //Si no hay puente saca ficha normal
        if (!casa.isEsPuente()) {
            f.setCasilla(casa);
            f.setEstaEnCasa(false);
            c.getTurno().siguienteTurno();
        } else if ((casa.isEsPuente() && casa.getFichaDistEquipoCasSal(f.getEquipo()) != null)) {
            casa.getFichaDistEquipoCasSal(f.getEquipo()).mandarACasa(c.getCasas().getCasaLibre(casa.getFichaDistEquipoCasSal(f.getEquipo()).getEquipo()));
            f.setCasilla(casa);
            f.setEstaEnCasa(false);
            c.elegirMovimiento(c, c.getDueñoDeFicha(f).getFichasFueraDeCasa(), 20);
            c.getTablero().statsSumaFichaComida(c.getDueñoDeFicha(f));
        }

    }

}
