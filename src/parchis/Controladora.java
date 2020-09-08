package parchis;

import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

/**
 *
 * @author ricar
 */
/*
    Clase cerebro del programa encargada de controlar las ventanas y las llamadas entre las clases
*/    
public class Controladora {

    private Estadisticas estadisticas;
    private VentanaMenuPrincipal menuPpal = null;
    private VentanaTablero tablero;
    private Jugadores jugadores;
    private Casas casas;
    private Casillas casillas;
    private Turno turno;
    private boolean mute = false;

    public Controladora() {
        this.estadisticas = new Estadisticas();
        this.jugadores = new Jugadores();
        desSerializarEstadisticas();
    }

    public Casillas getCasillas() {
        return casillas;
    }

    public Turno getTurno() {
        return turno;
    }

    public Casas getCasas() {
        return casas;
    }

    public Jugadores getJugadores() {
        return jugadores;
    }

    public VentanaTablero getTablero() {
        return tablero;
    }

    public Estadisticas getEstadisticas() {
        return estadisticas;
    }

    public void setMute(boolean mute) {
        this.mute = mute;
    }

    public boolean isMute() {
        return mute;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Controladora c = new Controladora();
        c.menuPpal = new VentanaMenuPrincipal(c);
        c.menuPpal.setVisible(true);
    }

    public void abreMenu1vs1() {
        VentanaMenuSelector1vs1 menu1vs1 = new VentanaMenuSelector1vs1(this);
        menu1vs1.setVisible(true);
        this.menuPpal.setVisible(false);
    }

    public void abreReglas() {
        VentanaReglas reglas = new VentanaReglas(this);
        reglas.setVisible(true);
        this.menuPpal.setVisible(false);
    }

    public void abreRanking() {
        VentanaRanking ranking = new VentanaRanking(this);
        ranking.setVisible(true);
        this.menuPpal.setVisible(false);

    }

    public void vuelveMenuPrincipal() {
        this.menuPpal.setVisible(true);
        this.jugadores = new Jugadores();
    }

    /*
        Crea una nueva ventana con el tablero
    */
    public void prepararTablero() {
        tablero = new VentanaTablero(this);
        tablero.setVisible(true);

        this.casas = new Casas();
        this.casillas = new Casillas();

        generaCasas();

        Jugador j1 = null;
        Jugador j2 = null;
        
        //Coloca las fichas en el tablero para ambos jugadores, se tienen que colocar por delante de la imagen de fondo
        for (Jugador j : jugadores) {
            if (j1 == null) {
                j1 = j;
            } else {
                j2 = j;
            }
            if (j.getEquipo().name().equals(Equipo.AZUL.name())) {
                j.setFichas(this.generaFichasAzules());
                for (Ficha f : j.getFichas()) {
                    tablero.getContentPane().add(f, new org.netbeans.lib.awtextra.AbsoluteConstraints(f.getPosicion()));
                    tablero.getContentPane().setComponentZOrder(f, tablero.getContentPane().getComponentCount() - 2);
                }
            }
            if (j.getEquipo().name().equals(Equipo.VERDE.name())) {
                j.setFichas(this.generaFichasVerdes());
                for (Ficha f : j.getFichas()) {
                    tablero.getContentPane().add(f, new org.netbeans.lib.awtextra.AbsoluteConstraints(f.getPosicion()));
                    tablero.getContentPane().setComponentZOrder(f, tablero.getContentPane().getComponentCount() - 2);
                }
            }
        }

        tablero.iniciaTablaStats(j1, j2);
        
        //Se inician los turnos de la partida
        this.turno = new Turno(jugadores, this.tablero);

        this.empezarJuego();

    }

    private void empezarJuego() {
        for (Jugador j : jugadores) {
            if (turno.turnoDe(j)) {
                tablero.turnoDe(j.getEquipo());
                tablero.setTextoAccionTirar();
            }
        }
    }

    public boolean añadirJugador(Jugador j) {
        return jugadores.añadirJugador(j);
    }

    /*
        Crea 4 fichas para el equipo azul y las coloca en su posición
    */
    private Fichas generaFichasAzules() {
        Fichas f = new Fichas();
        f.add(new Ficha(Equipo.AZUL, casas.get(Equipo.AZUL).get(11)));
        f.add(new Ficha(Equipo.AZUL, casas.get(Equipo.AZUL).get(12)));
        f.add(new Ficha(Equipo.AZUL, casas.get(Equipo.AZUL).get(13)));
        f.add(new Ficha(Equipo.AZUL, casas.get(Equipo.AZUL).get(14)));
        return f;
    }

    /*
        Crea 4 fichas para el equipo verde y las coloca en su posición
    */
    private Fichas generaFichasVerdes() {
        Fichas f = new Fichas();
        f.add(new Ficha(Equipo.VERDE, casas.get(Equipo.VERDE).get(11)));
        f.add(new Ficha(Equipo.VERDE, casas.get(Equipo.VERDE).get(12)));
        f.add(new Ficha(Equipo.VERDE, casas.get(Equipo.VERDE).get(13)));
        f.add(new Ficha(Equipo.VERDE, casas.get(Equipo.VERDE).get(14)));
        return f;
    }

    /*
        Como el tablero se basa en coordenadas tengo que crear las casillas para las casas manualmente, cada color tiene su TreeMap de casillas
        Aquí se generan las casillas útiles para cada equipo según un código de números:
        - El 1 es la casilla donde saca por primera vez
        - El 2 es la casilla que hace de entrada a casa
        - Del 3 al 9 son las casillas de su casa
        - El 10 es la meta, ya en casa -> posiciones de la 15 a la 18
        - De la 11 a la 14 son donde empieza
    
        (Como solo se usan el equipo verde y azul no he calculado las posiciones del rojo y amarillo)
    */
    private void generaCasas() {
        TreeMap taz = new TreeMap();
        TreeMap tve = new TreeMap();
        TreeMap tro = new TreeMap();
        TreeMap tam = new TreeMap();
        taz.put(1, casillas.get(22));
        taz.put(2, casillas.get(17));
        taz.put(3, new Casilla(1, new Point(633, 336), true, new Point(633, 336 - 15), new Point(633, 336 + 15)));
        taz.put(4, new Casilla(2, new Point(598, 336), true, new Point(598, 336 - 15), new Point(598, 336 + 15)));
        taz.put(5, new Casilla(3, new Point(563, 336), true, new Point(563, 336 - 15), new Point(563, 336 + 15)));
        taz.put(6, new Casilla(4, new Point(528, 336), true, new Point(528, 336 - 15), new Point(528, 336 + 15)));
        taz.put(7, new Casilla(5, new Point(493, 336), true, new Point(493, 336 - 15), new Point(493, 336 + 15)));
        taz.put(8, new Casilla(6, new Point(458, 336), true, new Point(458, 336 - 15), new Point(458, 336 + 15)));
        taz.put(9, new Casilla(7, new Point(423, 336), true, new Point(423, 336 - 15), new Point(423, 336 + 15)));
        taz.put(10, new Casilla(8, new Point(388, 336), true, new Point(388, 336 - 15), new Point(388, 336 + 15)));
        taz.put(11, new Casilla(1, new Point(530, 70), true));
        taz.put(12, new Casilla(2, new Point(530, 150), true));
        taz.put(13, new Casilla(3, new Point(610, 70), true));
        taz.put(14, new Casilla(4, new Point(610, 150), true));
        taz.put(15, new Casilla(15, new Point(390, 350), true));
        taz.put(16, new Casilla(16, new Point(390, 320), true));
        taz.put(17, new Casilla(17, new Point(390, 290), true));
        taz.put(18, new Casilla(18, new Point(390, 380), true));

        tve.put(1, casillas.get(56));
        tve.put(2, casillas.get(51));
        tve.put(3, new Casilla(1, new Point(38, 336), true, new Point(38, 336 - 15), new Point(38, 336 + 15)));
        tve.put(4, new Casilla(2, new Point(73, 336), true, new Point(73, 336 - 15), new Point(73, 336 + 15)));
        tve.put(5, new Casilla(3, new Point(108, 336), true, new Point(108, 336 - 15), new Point(108, 336 + 15)));
        tve.put(6, new Casilla(4, new Point(143, 336), true, new Point(143, 336 - 15), new Point(143, 336 + 15)));
        tve.put(7, new Casilla(5, new Point(178, 336), true, new Point(178, 336 - 15), new Point(178, 336 + 15)));
        tve.put(8, new Casilla(6, new Point(213, 336), true, new Point(213, 336 - 15), new Point(213, 336 + 15)));
        tve.put(9, new Casilla(7, new Point(248, 336), true, new Point(248, 336 - 15), new Point(248, 336 + 15)));
        tve.put(10, new Casilla(8, new Point(283, 336), true, new Point(283, 336 - 15), new Point(283, 336 + 15)));
        tve.put(11, new Casilla(1, new Point(70, 530), true));
        tve.put(12, new Casilla(2, new Point(150, 530), true));
        tve.put(13, new Casilla(3, new Point(70, 610), true));
        tve.put(14, new Casilla(4, new Point(150, 610), true));
        tve.put(15, new Casilla(15, new Point(280, 350), true));
        tve.put(16, new Casilla(16, new Point(280, 320), true));
        tve.put(17, new Casilla(17, new Point(280, 290), true));
        tve.put(18, new Casilla(18, new Point(280, 380), true));

        tro.put(1, casillas.get(39));
        tro.put(2, casillas.get(34));
        tam.put(1, casillas.get(5));
        tam.put(1, casillas.get(68));
        casas.put(Equipo.AZUL, taz);
        casas.put(Equipo.AMARILLO, tam);
        casas.put(Equipo.VERDE, tve);
        casas.put(Equipo.ROJO, tro);
    }

    /*
        Saca un número aleatorio entre 1 y 6 
    */
    public int tirarDado() {
        double resultado;
        resultado = Math.random() * 6 + 1;
        tablero.setNumeroDado((int) resultado);
        tablero.bloqueaBotones();
        return (int) resultado;
    }

    /*
        Aquí está la lógica detrás de cada vez que un jugador pulsa el botón tirar
        Es algo enrevesado pues hay bastante reglas a tener en cuenta y va llamando a distintos métodos
    */
    public void tirar(Jugador j) {
        //Saco un numero aleatorio entre 1 y 6
        int res;
        res = tirarDado();

        
        //Le añado una tirada
        j.añadirTirada();
        //Si es la tercera vez que tira seguida hay que moverle la última ficha movida a casa (siempre y cuando no esté casi en la meta)
        if (j.getNumTirada() == 3 && res == 6) {
            if (j.getUltimaFichaMovida() != null && !j.getUltimaFichaMovida().isCasiMeta()) {
                for (Casilla c : casas.devuelveCasas(j.getEquipo()).values()) {
                    if (!c.tieneFicha()) {
                        j.getUltimaFichaMovida().mandarACasa(c);
                        tablero.setTextoExtraTresVeces6();
                        turno.siguienteTurno();
                        break;
                    }
                }
            } else {
                turno.siguienteTurno();
            }
        } else {
            //Hay que saber si tiene fichas o no en el campo
            //Si no tiene fichas en el campo solo puede hacer algo si es un 5 (saca una ficha) o si es un 6 (tira otra vez)
            //sino, cambio de turno
            if (!j.isHaSalido()) {
                //Si saca un 5 y tiene todas las fichas en casa le dejo elegir mover una ficha de las que tiene en casa
                if (res == 5) {
                    tablero.setTextoAccionSacarFicha();
                    elegirMovimiento(this, j.getFichasEnCasa(), res);
                } //Si saca un 6 le vuelve a tocar
                else if (res == 6) {
                    tablero.setTextoAccionVolverATirar();
                    turno.vuelveAToacar();
                } //Sino, le toca al siguiente
                else {
                    turno.siguienteTurno();
                }
                //Si tiene fichas en el campo
            } else {
                //Si saca un 5, la casilla de su casa no está bloqueada y le queda alguna ficha en casa
                if (res == 5 && !casas.get(j.getEquipo()).get(1).isBloqueada() && j.tieneFichasCasa()) {
                    tablero.setTextoAccionSacarFicha();
                    elegirMovimiento(this, j.getFichasEnCasa(), res);
                } else {
                    if (!j.tieneFichasCasa() && res == 6) {
                        res = 7;
                    }

                    //Si saca un 6 y tiene algún bloqueo está obligado a romper el bloqueo
                    if ((res == 6 || res == 7) && j.tieneBloqueo()) {
                        tablero.setTextoAccionRomperBloqueo();
                        elegirMovimiento(this, j.getFichasConBloqueo(), res);
                    } else {
                        boolean puedeMover = false;
                        for (Ficha f : j.getFichasFueraDeCasa()) {
                            if (Movimiento.puedeMoverse(this, f, res)) {
                                puedeMover = true;
                            }
                        }
                        if (puedeMover) {
                            tablero.setTextoAccionMoverFicha();
                            elegirMovimiento(this, j.getFichasFueraDeCasa(), res);
                        } else {
                            if (res == 6 || res == 7) {
                                tablero.setTextoAccionVolverATirar();
                                turno.vuelveAToacar();

                            } else {
                                turno.siguienteTurno();
                            }
                        }
                    }

                }
            }
        }
    }

    /*
        Se enccarga de "iluminar" las fichas y crear los trigger para saber que ficha eligió mover el jugador
        Para saber que fichas puede mover y cuántas casillas se lo pasamos como parámetro, pues esto ya se averigua en el método tirar()
    */
    public void elegirMovimiento(Controladora c1, Fichas f, int res) {
        boolean haMovido = false;
        //Comprobamos los textos

        if (res == 20) {
            //Si mueve 20 es solo porque ha comido una ficha
            tablero.setTextoExtraFichaComida();
        } else if (res == 10) {//Si mueve 20 es solo porque ha metido una ficha en la meta 
            tablero.setTextoExtraFichaLLegaMeta();
        } else if (res == 7) {
            //Si mueve 7 es porque ya tiene todas las fichas fuera de casa y sacó un 6 que se convirtió en 7
            tablero.setTextoExtra6Vale7();
        }

        for (Ficha f1 : f) {
            if (Movimiento.puedeMoverse(c1, f1, res)) {
                haMovido = true;
                f1.ponerImagenSeleccion();
                f1.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        if (!c1.isMute()) {
                            try {
                                int res;
                                res = (int) (Math.random() * 3 + 1);
                                Clip sonido;
                                sonido = AudioSystem.getClip();
                                sonido.open(AudioSystem.getAudioInputStream(new File("./src/res/snd/ficha" + res + ".wav")));
                                sonido.start();
                                
                                
                            } catch (LineUnavailableException ex) {
                                Logger.getLogger(Controladora.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (UnsupportedAudioFileException ex) {
                                Logger.getLogger(Controladora.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                                Logger.getLogger(Controladora.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        for (Ficha f2 : f) {
                            f2.ponerImagenFija();

                            for (int i = 0; i < f2.getMouseListeners().length; i++) {
                                f2.removeMouseListener(f2.getMouseListeners()[i]);
                            }
                        }
                        if (f1.isEnCasa()) {
                            Movimiento.sacarFicha(f1, c1);
                        } else {
                            Movimiento.moverFicha(c1, f1, res);
                        }
                    }
                });
            }
        }
        if (!haMovido) {
            turno.siguienteTurno();
        } else {
            tablero.bloqueaBotones();
        }
    }

    public Jugador getDueñoDeFicha(Ficha f) {
        for (Jugador j : jugadores) {
            if (f.getEquipo().equals(j.getEquipo())) {
                return j;
            }
        }
        return null;
    }

    public Jugador getJugadorDeEquipo(Equipo e) {
        Jugador j2 = null;
        for (Jugador j : jugadores) {
            if (j.getEquipo().equals(e)) {
                j2 = j;
                break;
            }
        }
        return j2;
    }

    /*
        Se lleva a cabo todo lo que conlleva que acabe una partida
    */
    public void finPartida(Jugador ganador) {
        //Se recuperan las estadísticas y se busca al ganador
        ArrayList<Stats> stats = tablero.getStats();
        for (Stats e : stats) {
            //Cuando se encuentre al ganador se le suma la estadística de partida ganada
            if (e.getNombreJugador().equals(ganador.getNombre())) {
                e.añadirVecesGanadas(1);
                e.añadirFichasEnMeta(1);
                break;
            }
        }
        //Se añaden las estadísticas (si ya había para esos jugadores se actualizan)
        estadisticas.añadir(stats);

        //Se muestra un mensaje de ganador
        JOptionPane.showMessageDialog(tablero, "Enhorabuena " + ganador.getNombre() + " has ganado la partida!");
        //Se cierra el tablero
        tablero.setVisible(false);
        tablero.dispose();
        this.vuelveMenuPrincipal();
    }

    /*
        Cuando se cierra el tablero sin haber ganado una partida se actualizan las estadísticas (salvo la de ganador de partida claro)
    */
    public void recuperarDatos() {
        estadisticas.añadir(tablero.getStats());
    }

    /*
        Guarda en formato binario los datos de las estadísticas en un fichero "stats.dat"
    */
    public void serializarEstadisticas() {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            fos = new FileOutputStream("stats.dat");
            oos = new ObjectOutputStream(fos);

            oos.writeObject(estadisticas);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Controladora.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Controladora.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fos.close();
                oos.close();
            } catch (IOException ex) {
                Logger.getLogger(Controladora.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /*
        Recoge los datos del fichero "stats.dat"
    */
    public void desSerializarEstadisticas() {
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            fis = new FileInputStream("stats.dat");
            ois = new ObjectInputStream(fis);

            this.estadisticas = (Estadisticas) ois.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Controladora.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Controladora.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Controladora.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
                ois.close();
            } catch (IOException ex) {
                Logger.getLogger(Controladora.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NullPointerException ex) {
                Logger.getLogger(Controladora.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /*
        Elimina el contenido del fichero "stats.dat"
    */
    public void borraEstadisticas() {
        File ruta = new File("stats.dat");
        ruta.delete();
        this.estadisticas = new Estadisticas();
    }
}
