
package parchis;


import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ricar
 */

/*
    Clase que extiende de DefaultTableModel y se usa para la tabla de ranking
*/
public class ModeloTablaRanking extends DefaultTableModel {

    private final Stats[] stats;
    
    ModeloTablaRanking(ArrayList<Stats> stats) {
        this.stats = new Stats[stats.size()];
        stats.toArray(this.stats);
    }

    //Establecemos el tamaño de la tabla en columnas: 
    @Override
    public int getColumnCount() {
        return 4;
    }

    //EStablcer nombres de las columnas
    @Override
    public String getColumnName(int i) {
        String[] nombreColumna = {"NOMBRE JUGADOR", "Nº VICTORIAS", "Nº FICHAS COMIDAS","Nº FICHAS EN META"};
        return nombreColumna[i];
    }

    //Establecer los tipos de datos
    @Override
    public Class<?> getColumnClass(int i) {
        Class[] lasClases = {String.class, Integer.class, Integer.class, Integer.class};
        return lasClases[i];
    }

    //Establecer el número de filas
    @Override
    public int getRowCount() {
        return stats == null ? 0 : stats.length;
    }

    //indicar si las celdas son editables
    @Override
    public boolean isCellEditable(int f, int c) {
        return false;
    }

    //Devolver el valor que hay en una determinada posición
    @Override
    public Object getValueAt(int fila, int columna) {
        if (getRowCount() > 0) {
            switch (columna) {
                case 0:
                    return stats[fila].getNombreJugador();
                case 1:
                    return stats[fila].getVecesGanadas();
                case 2:
                    return stats[fila].getFichasComidas();
                case 3:
                    return stats[fila].getFichasMeta();
            }
        }
        return null;
    }
}
