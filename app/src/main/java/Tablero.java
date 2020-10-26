import java.util.Arrays;
import java.util.Optional;

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

  public int obtenerConsecutivos(int n, Celda jugador) {
    return 0;
  }

  public static Optional<Tablero> deArreglo(String[] a) {
    if (a.length != FILAS) return Optional.empty();
    if (!Arrays.stream(a).allMatch(fila -> fila.length() == COLUMNAS)) return Optional.empty();
    // if (!Arrays.stream(a).flatMapToInt(s -> s.chars()).allMatch(c -> c == '1' || c == '2' || c ==
    // '3'))
    if (!Arrays.stream(a)
        .allMatch(fila -> fila.chars().allMatch(c -> c == '1' || c == '2' || c == ' ')))
      return Optional.empty();

    return Optional.of(new Tablero());
  }
}
