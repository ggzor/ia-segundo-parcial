package ia.segundoparcial;

import java.util.Arrays;
import java.util.stream.IntStream;

// Clase para calcular la función f con los parámetros dados
public class ParametrosEvaluacion {

  // Los parámetros de las funciones y sus pesos
  private double alpha1;
  private double maxh1;
  private double alpha2;
  private double maxh2;
  private double alpha3;
  private double maxh3;
  private double c1;
  private double c2;

  public ParametrosEvaluacion(
      double alpha1,
      double maxh1,
      double alpha2,
      double maxh2,
      double alpha3,
      double maxh3,
      double c1,
      double c2) {
    this.alpha1 = alpha1;
    this.maxh1 = maxh1;
    this.alpha2 = alpha2;
    this.maxh2 = maxh2;
    this.alpha3 = alpha3;
    this.maxh3 = maxh3;
    this.c1 = c1;
    this.c2 = c2;
  }

  // La función heurística para un tablero dado y un jugador
  public double f(Tablero tablero, Celda jugador) {
    return h(tablero, jugador) - h(tablero, jugador.contrario());
  }

  // La función heurística para uno sólo de los jugadores
  private double h(Tablero tablero, Celda jugador) {
    return alpha1 * h1(tablero, jugador) / maxh1
        - alpha2 * h2(tablero, jugador) / maxh2
        + alpha3 * h3(tablero, jugador) / maxh3;
  }

  // La función que calcula el conteo de las líneas extensibles de un jugador
  private double h1(Tablero tablero, Celda jugador) {
    double k2 = tablero.obtenerLineasExtensibles(2, jugador).length;
    double k3 = tablero.obtenerLineasExtensibles(3, jugador).length;
    double k4 = tablero.esGanador(jugador) ? 1 : 0;

    return c1 * k2 + c2 * k3 + 6 * (c1 + c2) * k4;
  }

  // La función que calcula la suma de las distancias de las coordenadas de las
  // líneas extensibles del jugador dado
  private double h2(Tablero tablero, Celda jugador) {
    return IntStream.range(1, 3 + 1)
        .mapToObj(i -> (Integer) i)
        .flatMap(
            i ->
                Arrays.stream(tablero.obtenerLineasExtensibles(i, jugador)).flatMap(Arrays::stream))
        .mapToInt(Tablero::obtenerDistancia)
        .sum();
  }

  // La función que calcula la cantidad de líneas completables de un jugador
  private double h3(Tablero tablero, Celda jugador) {
    return tablero.obtenerLineasCompletables(jugador);
  }
}
