package ia.segundoparcial.agentes;

import ia.segundoparcial.Tablero;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/** Un agente que sólo tira aleatoriamente en alguna posición disponible */
public class AgenteAleatorio extends AgenteAutomatico {
  private ForkJoinPool pool = new ForkJoinPool();
  private Random random = new Random();

  @Override
  public ForkJoinTask<Integer> calcularTiro(Tablero tablero) {
    return pool.submit(() -> random.nextInt(Tablero.COLUMNAS));
  }
}
