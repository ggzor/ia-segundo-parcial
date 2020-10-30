package ia.segundoparcial.agentes;

import ia.segundoparcial.Tablero;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/** AgenteAlphaBeta */
public class AgenteAlphaBeta extends AgenteAutomatico {
  private static ForkJoinPool pool = new ForkJoinPool();

  public AgenteAlphaBeta(int horizonteLimitado) {}

  @Override
  public ForkJoinTask<Integer> calcularTiro(Tablero tablero) {
    return pool.submit(
        () -> {
          Thread.sleep(200);
          return 3;
        });
  }
}
