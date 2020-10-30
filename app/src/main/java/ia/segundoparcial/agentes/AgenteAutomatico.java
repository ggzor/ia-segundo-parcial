package ia.segundoparcial.agentes;

import ia.segundoparcial.Tablero;
import java.util.concurrent.ForkJoinTask;

/** Representa un agente que puede tomar decisiones de forma automática */
public abstract class AgenteAutomatico extends Agente {
  // Calcula el siguiente tiro con respecto al tablero
  public abstract ForkJoinTask<Integer> calcularTiro(Tablero tablero);
}
