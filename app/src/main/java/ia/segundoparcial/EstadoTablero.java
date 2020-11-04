package ia.segundoparcial;

import java.util.ArrayList;

import ia.segundoparcial.alphabeta.Estado;

public class EstadoTablero implements Estado<Integer> {

  private Tablero tablero;
  private Integer tiro;
  private ParametrosEvaluacion evaluacion;

  public EstadoTablero(Tablero tablero, Integer tiro, ParametrosEvaluacion evaluacion) {
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
      expansiones.add(new EstadoTablero(nuevo, columna, evaluacion));
    }

    return expansiones;
  }

  @Override
  public Integer generadoPor() {
    return tiro;
  }

  @Override
  public boolean esTerminal() {
    return tablero.esGanador(Celda.Jugador1) || tablero.esGanador(Celda.Jugador2) || tablero.estaLleno();
  }

  @Override
  public double f() {
    return evaluacion.f(tablero);
  }
}
