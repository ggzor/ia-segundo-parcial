package ia.segundoparcial;

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

  public boolean estaLleno() {
    return Arrays.stream(celdas).flatMap(Arrays::stream).allMatch(c -> c != Celda.Vacio);
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
    int contador, fila, columna, k;
    boolean bandera;
    contador = columna = 0;
    // Buscar Horizontal
    fila = FILAS - 1;
    while (fila >= 0) {
      columna = 0;
      while (columna < COLUMNAS) {
        if (celdas[fila][columna] == jugador) {
          bandera = false;
          k = 0;
          if (columna != 0) if (celdas[fila][columna - 1] == Celda.Vacio) bandera = !bandera;
          while (celdas[fila][columna] == jugador) {
            k++;
            columna++;
            if (columna == COLUMNAS) break;
          }
          if (k == n && bandera) contador++;
          else if (columna != COLUMNAS && k == n)
            if (celdas[fila][columna] == Celda.Vacio) contador++;
        }
        columna++;
      }
      fila--;
    }
    // Buscar en vertical
    columna = 0;
    while (columna < COLUMNAS) {
      fila = FILAS - 1;
      while (fila >= 0) {
        if (celdas[fila][columna] == jugador) {
          k = 0;
          while (celdas[fila][columna] == jugador) {
            k++;
            fila--;
            if (fila == -1) break;
          }
          if (fila != -1 && k == n) if (celdas[fila][columna] == Celda.Vacio) contador++;
        }
        fila--;
      }
      columna++;
    }
    // Buscar en diagonal (\)
    // Buscar en la primera mitad (\)
    int auxCelda;
    columna = 0;
    while (columna < COLUMNAS) {
      fila = FILAS - 1;
      auxCelda = columna;
      while (auxCelda >= 0 && fila >= 0) {
        if (celdas[fila][auxCelda] == jugador) {
          bandera = false;
          k = 0;
          if (fila != FILAS - 1)
            if (celdas[fila + 1][auxCelda + 1] == Celda.Vacio) bandera = !bandera;
          while (celdas[fila][auxCelda] == jugador) {
            fila--;
            auxCelda--;
            k++;
            if (auxCelda == -1 || fila == -1) break;
          }
          if (k == n && bandera) contador++;
          else if (auxCelda != -1 && fila != -1 && k == n)
            if (celdas[fila][auxCelda] == Celda.Vacio) contador++;
        }
        fila--;
        auxCelda--;
      }
      columna++;
    }
    // Busca la 2 mitad (\)
    fila = FILAS - 2;
    while (fila >= 0) {
      auxCelda = fila;
      columna = COLUMNAS - 1;
      while (auxCelda >= 0 && columna >= 0) {
        if (celdas[fila][auxCelda] == jugador) {
          bandera = false;
          k = 0;
          if (columna != COLUMNAS - 1)
            if (celdas[auxCelda + 1][columna + 1] == Celda.Vacio) bandera = !bandera;
          while (celdas[auxCelda][columna] == jugador) {
            columna--;
            auxCelda--;
            k++;
            if (auxCelda == -1 || columna == -1) break;
          }
          if (k == n && bandera) contador++;
          else if (auxCelda != -1 && columna != -1 && k == n)
            if (celdas[auxCelda][columna] == Celda.Vacio) contador++;
        }
        columna--;
        auxCelda--;
      }
      fila--;
    }
    // Buscar en diagonal (/)
    // Busca en la 1 mitad (/)
    columna = COLUMNAS - 1;
    while (columna >= 0) {
      fila = FILAS - 1;
      auxCelda = columna;
      while (auxCelda < COLUMNAS && fila >= 0) {
        if (celdas[fila][auxCelda] == jugador) {
          bandera = false;
          k = 0;
          if (fila != FILAS - 1)
            if (celdas[fila + 1][auxCelda - 1] == Celda.Vacio) bandera = !bandera;
          while (celdas[fila][auxCelda] == jugador) {
            fila--;
            auxCelda++;
            k++;
            if (auxCelda == COLUMNAS || fila == -1) break;
          }
          if (k == n && bandera) contador++;
          else if (auxCelda != COLUMNAS && fila != -1 && k == n)
            if (celdas[fila][auxCelda] == Celda.Vacio) contador++;
        }
        fila--;
        auxCelda++;
      }
      columna--;
    }
    // Busca en la 2 mitad (/)
    fila = FILAS - 2;
    while (fila >= 0) {
      auxCelda = fila;
      columna = 0;
      while (auxCelda >= 0 && columna < COLUMNAS) {
        if (celdas[auxCelda][columna] == jugador) {
          bandera = false;
          k = 0;
          if (columna != 0)
            if (celdas[auxCelda + 1][columna - 1] == Celda.Vacio) bandera = !bandera;
          while (celdas[auxCelda][columna] == jugador) {
            columna++;
            auxCelda--;
            k++;
            if (auxCelda == -1 || columna == COLUMNAS) break;
          }
          if (k == n && bandera) contador++;
          else if (auxCelda != -1 && columna != COLUMNAS && k == n)
            if (celdas[auxCelda][columna] == Celda.Vacio) contador++;
        }
        columna++;
        auxCelda--;
      }
      fila--;
    }
    return contador;
  }

  public boolean esGanador(Celda jugador) {
    int fila, columna, k, ganadorNum;
    ganadorNum = 4;
    // Busca en horizontal
    fila = FILAS - 1;
    while (fila >= 0) {
      columna = 0;
      while (columna < COLUMNAS) {
        if (celdas[fila][columna] == jugador) {
          k = 0;
          while (celdas[fila][columna] == jugador) {
            k++;
            columna++;
            if (columna == COLUMNAS) break;
          }
          if (k == ganadorNum) return true;
        }
        columna++;
      }
      fila--;
    }
    // Buscar en vertical
    columna = 0;
    while (columna < COLUMNAS) {
      fila = FILAS - 1;
      while (fila >= 0) {
        if (celdas[fila][columna] == jugador) {
          k = 0;
          while (celdas[fila][columna] == jugador) {
            k++;
            fila--;
            if (fila == -1) break;
          }
          if (k == ganadorNum) return true;
        }
        fila--;
      }
      columna++;
    }
    // Buscar en diagonal (\)
    // Buscar en la primera mitad (\)
    int auxCelda;
    columna = 0;
    while (columna < COLUMNAS) {
      fila = FILAS - 1;
      auxCelda = columna;
      while (auxCelda >= 0 && fila >= 0) {
        if (celdas[fila][auxCelda] == jugador) {
          k = 0;
          while (celdas[fila][auxCelda] == jugador) {
            fila--;
            auxCelda--;
            k++;
            if (auxCelda == -1 || fila == -1) break;
          }
          if (k == ganadorNum) return true;
        }
        fila--;
        auxCelda--;
      }
      columna++;
    }
    // Busca la 2 mitad (\)
    fila = FILAS - 2;
    while (fila >= 0) {
      auxCelda = fila;
      columna = COLUMNAS - 1;
      while (auxCelda >= 0 && columna >= 0) {
        if (celdas[fila][auxCelda] == jugador) {
          k = 0;
          while (celdas[auxCelda][columna] == jugador) {
            columna--;
            auxCelda--;
            k++;
            if (auxCelda == -1 || columna == -1) break;
          }
          if (k == ganadorNum) return true;
        }
        columna--;
        auxCelda--;
      }
      fila--;
    }
    // Buscar en diagonal (/)
    // Busca en la 1 mitad (/)
    columna = COLUMNAS - 1;
    while (columna >= 0) {
      fila = FILAS - 1;
      auxCelda = columna;
      while (auxCelda < COLUMNAS && fila >= 0) {
        if (celdas[fila][auxCelda] == jugador) {
          k = 0;
          while (celdas[fila][auxCelda] == jugador) {
            fila--;
            auxCelda++;
            k++;
            if (auxCelda == COLUMNAS || fila == -1) break;
          }
          if (k == ganadorNum) return true;
        }
        fila--;
        auxCelda++;
      }
      columna--;
    }
    // Busca en la 2 mitad (/)
    fila = FILAS - 2;
    while (fila >= 0) {
      auxCelda = fila;
      columna = 0;
      while (auxCelda >= 0 && columna < COLUMNAS) {
        if (celdas[auxCelda][columna] == jugador) {
          k = 0;
          while (celdas[auxCelda][columna] == jugador) {
            columna++;
            auxCelda--;
            k++;
            if (auxCelda == -1 || columna == COLUMNAS) break;
          }
          if (k == ganadorNum) return true;
        }
        columna++;
        auxCelda--;
      }
      fila--;
    }
    return false;
  }

  public Celda obtenerJugadorActual() {
    return turnoJugador ? Celda.Jugador1 : Celda.Jugador2;
  }

  public static Optional<Tablero> deArreglo(String[] a) {
    if (a.length != FILAS) return Optional.empty();
    if (!Arrays.stream(a).allMatch(fila -> fila.length() == COLUMNAS)) return Optional.empty();
    // if (!Arrays.stream(a).flatMapToInt(s -> s.chars()).allMatch(c -> c == '1' || c == '2' || c ==
    // '3'))
    if (!Arrays.stream(a)
        .allMatch(fila -> fila.chars().allMatch(c -> c == '1' || c == '2' || c == ' ')))
      return Optional.empty();
    for (int j = COLUMNAS - 1; j >= 0; j--)
      for (int i = 0; i < FILAS; i++) {
        if (a[i].charAt(j) != ' ') {
          int k = i;
          while (k < FILAS) {
            if (a[k].charAt(j) == ' ') return Optional.empty();
            k++;
          }
          break;
        }
      }
    // exitOptional.of(new Tablero());
    Tablero t = new Tablero();
    for (int i = 0; i < FILAS; i++)
      for (int j = 0; j < COLUMNAS; j++) {
        switch (a[i].charAt(j)) {
          case ' ':
            t.celdas[i][j] = Celda.Vacio;
            break;
          case '1':
            t.celdas[i][j] = Celda.Jugador1;
            break;
          case '2':
            t.celdas[i][j] = Celda.Jugador2;
            break;
        }
      }
    return Optional.of(t);
  }

  public int[] obtenerColumnasDisponibles() {
    int j, contador;
    j = contador = 0;
    int[] disponibles = new int[COLUMNAS];
    while (j < COLUMNAS) {
      if (celdas[0][j] == Celda.Vacio) {
        contador++;
        disponibles[j] = j;
      } else disponibles[j] = -1;
      j++;
    }
    int[] vacias = new int[contador];
    j = 0;
    for (int i = 0; i < COLUMNAS; i++)
      if (disponibles[i] == i) {
        vacias[j] = i;
        j++;
      }
    return vacias;
  }
}
