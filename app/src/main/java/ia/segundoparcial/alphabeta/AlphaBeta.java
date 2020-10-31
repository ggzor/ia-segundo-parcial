package ia.segundoparcial.alphabeta;

import ia.segundoparcial.utils.Par;

// Contiene la función para aplicar el algoritmo de poda alpha-beta dado un estado
// inicial y una profundidad máxima
public class AlphaBeta {
  // Realizar la llamada inicial a alpha-beta
  public static <M> Par<Double, M> calcular(Estado<M> inicial, int profundidadMaxima) {
    return alphaBeta(
        inicial, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 0, profundidadMaxima, true);
  }

  private static <M> Par<Double, M> alphaBeta(
      Estado<M> actual,
      double alpha,
      double beta,
      int profundidad,
      int profundidadMaxima,
      boolean esMax) {
    // Determinar si es un nodo hoja o está en el horizonte limitado
    if (actual.esTerminal() || profundidad == profundidadMaxima)
      return Par.de(actual.f(), actual.generadoPor());

    // Iteracion sobre los hijos
    M movimiento = actual.generadoPor();
    for (Estado<M> siguiente : actual.expandir()) {
      if (esMax) {
        Par<Double, M> resultado =
            alphaBeta(siguiente, alpha, beta, profundidad + 1, profundidadMaxima, false);

        if (resultado.primero > alpha) {
          alpha = resultado.primero;
          movimiento = siguiente.generadoPor();
        }

        if (alpha >= beta) return Par.de(beta, movimiento);
      } else {
        Par<Double, M> resultado =
            alphaBeta(siguiente, alpha, beta, profundidad + 1, profundidadMaxima, true);

        if (resultado.primero < beta) {
          beta = resultado.primero;
          movimiento = siguiente.generadoPor();
        }

        if (alpha >= beta) return Par.de(alpha, movimiento);
      }
    }

    // Determinar el valor de retorno cuando se ha iterado en todos los hijos
    if (esMax) {
      return Par.de(alpha, movimiento);
    } else {
      return Par.de(beta, movimiento);
    }
  }
}
