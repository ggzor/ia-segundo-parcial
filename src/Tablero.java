import java.util.Arrays;

public class Tablero {
	public static final int COLUMNAS = 7;
  public static final int FILAS = 6;

  private Celda[][] celdas;
  private int[] contadores = new int[COLUMNAS];

  public Tablero() {
    celdas = new Celda[FILAS][COLUMNAS];
    Arrays.fill(contadores, 5);

    for (Celda[] fila : celdas) {
      Arrays.fill(fila, Celda.Vacio);
    }
  }

  public boolean estaVacio() {
    for (Celda[] fila : celdas) {
      for (Celda celda : fila) {
        if (celda != Celda.Vacio)
          return false;
      }
    }

    return true;
	}

  public Celda obtener(int fila, int columna) {
    boolean estaRangoFilas = 0 <= fila && fila <= FILAS - 1;
    boolean estaRangoColumnas = 0 <= columna && columna <= COLUMNAS - 1;

    if (estaRangoFilas && estaRangoColumnas)
      return celdas[fila][columna];

    throw new IllegalArgumentException("Coordenada fuera de límites");
  }

  public void tirar(int columna) {
    int fila = contadores[columna];

    if (fila % 2 == 1)
      celdas[fila][0] = Celda.Jugador1;
    else
      celdas[fila][0] = Celda.Jugador2;

    contadores[columna]--;
  }

  public boolean estaVaciaColumna(int i) {
    return false;
  }
}
