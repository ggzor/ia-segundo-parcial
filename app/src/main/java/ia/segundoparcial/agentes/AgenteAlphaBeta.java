package ia.segundoparcial.agentes;

import ia.segundoparcial.EstadoTablero;
import ia.segundoparcial.ParametrosEvaluacion;
import ia.segundoparcial.Tablero;
import ia.segundoparcial.alphabeta.AlphaBeta;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/** AgenteAlphaBeta */
public class AgenteAlphaBeta extends AgenteAutomatico {
  private ForkJoinPool pool = new ForkJoinPool();
  private final int horizonteLimitado;
  private ParametrosEvaluacion evaluacion;

  public AgenteAlphaBeta(int horizonteLimitado, ParametrosEvaluacion evaluacion) {
    this.horizonteLimitado = horizonteLimitado;
    this.evaluacion = evaluacion;
  }

  @Override
  public ForkJoinTask<Integer> calcularTiro(Tablero tablero) {
    return pool.submit(
        () -> {
          EstadoTablero inicial =
              new EstadoTablero(tablero.obtenerJugadorActual(), tablero, null, evaluacion);
          int resultado = AlphaBeta.calcular(inicial, horizonteLimitado).segundo;
          System.gc();
          return resultado;
        });
  }
}
