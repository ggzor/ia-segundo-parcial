package ia.segundoparcial.alphabeta;

import java.util.*;

public class AlphaBeta {
  public static interface Estado<M> {
    M generadoPor();

    ArrayList<Estado<M>> expandir();

    double f();

    boolean esTerminal();
  }

  public static class Par<A, B> {
    public final A primero;
    public final B segundo;

    public Par(A primero, B segundo) {
      this.primero = primero;
      this.segundo = segundo;
    }

    public static <A, B> Par<A, B> de(A primero, B segundo) {
      return new Par<A, B>(primero, segundo);
    }
  }

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
    if (actual.esTerminal() || profundidad == profundidadMaxima)
      return Par.de(actual.f(), actual.generadoPor());

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

    if (esMax) {
      return Par.de(alpha, movimiento);
    } else {
      return Par.de(beta, movimiento);
    }
  }
}
