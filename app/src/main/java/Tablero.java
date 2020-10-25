import java.util.Arrays;

public class Tablero {
  public static final int COLUMNAS = 7;
  public static final int FILAS = 6;

  private Celda[][] celdas;
  private int[] contadores = new int[COLUMNAS];
  private boolean turnoJugador;

  public Tablero() {
    celdas = new Celda[FILAS][COLUMNAS];
    Arrays.fill(contadores, 5);
    turnoJugador = true;
    for (Celda[] fila : celdas) {
      Arrays.fill(fila, Celda.Vacio);
    }
  }

  public boolean estaVacio() {
    for (Celda[] fila : celdas) {
      for (Celda celda : fila) {
        if (celda != Celda.Vacio) return false;
      }
    }

    return true;
  }

  public Celda obtener(int fila, int columna) {
    boolean estaRangoFilas = 0 <= fila && fila <= FILAS - 1;
    boolean estaRangoColumnas = 0 <= columna && columna <= COLUMNAS - 1;

    if (estaRangoFilas && estaRangoColumnas) return celdas[fila][columna];

    throw new IllegalArgumentException("Coordenada fuera de límites");
  }

  public boolean tirar(int columna) {
    int fila = contadores[columna];
    if (fila == -1) return false;

    celdas[fila][columna] = turnoJugador ? Celda.Jugador1 : Celda.Jugador2;
    turnoJugador = !turnoJugador;
    contadores[columna]--;
    return true;
  }

  public boolean estaVaciaColumna(int i) {
    return celdas[FILAS - 1][i] == Celda.Vacio;
  }
}
