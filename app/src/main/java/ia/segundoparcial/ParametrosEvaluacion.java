package ia.segundoparcial;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ParametrosEvaluacion {

  private double alpha1;
  private double maxh1;
  private double alpha2;
  private double maxh2;
  private double c1;
  private double c2;

  public ParametrosEvaluacion(
      double alpha1, double maxh1, double alpha2, double maxh2, double c1, double c2) {
    this.alpha1 = alpha1;
    this.maxh1 = maxh1;
    this.alpha2 = alpha2;
    this.maxh2 = maxh2;
    this.c1 = c1;
    this.c2 = c2;
  }

  public double f(Tablero tablero) {
    return h(tablero, Celda.Jugador1) - h(tablero, Celda.Jugador2);
  }

  private double h(Tablero tablero, Celda jugador) {
    return alpha1 * h1(tablero, jugador) / maxh1 + alpha2 * h2(tablero, jugador) / maxh2;
  }

  private double h1(Tablero tablero, Celda jugador) {
    double k2 = tablero.obtenerLineasConsecutivas(2, jugador).length;
    double k3 = tablero.obtenerLineasConsecutivas(3, jugador).length;
    double k4 = tablero.obtenerLineasConsecutivas(4, jugador).length;

    return c1 * k2 + c2 * k3 + 6 * (c1 + c2) * k4;
  }

  private double h2(Tablero tablero, Celda jugador) {
    return IntStream.range(1, 3 + 1)
        .mapToObj(i -> (Integer) i)
        .flatMap(
            i ->
                Arrays.stream(tablero.obtenerLineasConsecutivas(i, jugador))
                    .flatMap(Arrays::stream))
        .mapToInt(Tablero::obtenerDistancia)
        .sum();
  }
}
