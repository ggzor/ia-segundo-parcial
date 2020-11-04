package ia.segundoparcial;

import ia.segundoparcial.alphabeta.Estado;
import java.util.ArrayList;

public class EstadoTablero implements Estado<Integer> {

  private Tablero tablero;
  private Integer tiro;
  private ParametrosEvaluacion evaluacion;
  private Celda jugador;

  public EstadoTablero(
      Celda jugador, Tablero tablero, Integer tiro, ParametrosEvaluacion evaluacion) {
    this.jugador = jugador;
    this.tablero = tablero;
    this.tiro = tiro;
    this.evaluacion = evaluacion;
  }

  @Override
  public ArrayList<Estado<Integer>> expandir() {
    ArrayList<Estado<Integer>> expansiones = new ArrayList<>();

    for (int columna : tablero.obtenerColumnasDisponibles()) {
      Tablero nuevo = new Tablero(tablero);
      nuevo.tirar(columna);
      expansiones.add(new EstadoTablero(jugador, nuevo, columna, evaluacion));
    }

    return expansiones;
  }

  @Override
  public Integer generadoPor() {
    return tiro;
  }

  @Override
  public boolean esTerminal() {
    return tablero.esGanador(Celda.Jugador1)
        || tablero.esGanador(Celda.Jugador2)
        || tablero.estaLleno();
  }

  @Override
  public double f() {
    return evaluacion.f(tablero, jugador);
  }
}
