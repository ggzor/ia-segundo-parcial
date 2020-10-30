package ia.segundoparcial.escenas;

import com.golden.gamedev.Game;
import ia.segundoparcial.Celda;
import ia.segundoparcial.Tablero;
import ia.segundoparcial.agentes.*;
import ia.segundoparcial.componentes.*;
import java.util.concurrent.*;

public class EscenaJuego extends Escena {
  private Tablero tablero;
  private int indiceActual;

  private final Agente jugador1;
  private final Agente jugador2;

  public EscenaJuego(Agente jugador1, Agente jugador2) {
    tablero = new Tablero();
    indiceActual = Tablero.COLUMNAS / 2;

    this.jugador1 = jugador1;
    this.jugador2 = jugador2;
  }

  public static enum EstadoAgente {
    Esperando,
    Pensando,
    Mostrar
  };

  private EstadoAgente estadoAgente = EstadoAgente.Esperando;
  private ForkJoinTask<Integer> respuestaAgente = null;
  private long tiempoFin;

  @Override
  public Escena actualizar(Game app, long elapsedTime) {
    if (volver) return new EscenaDificultad();
    else {
      Agente agente = tablero.obtenerJugadorActual() == Celda.Jugador1 ? jugador1 : jugador2;

      switch (estadoAgente) {
        default:
        case Esperando:
          if (agente instanceof Persona) {
            if (mouseDentroJuego) {
              indiceActual = nuevoIndice;

              if (app.click()) {
                tablero.tirar(indiceActual);
              }
            }
          } else {
            AgenteAutomatico agenteAutomatico = (AgenteAutomatico) agente;
            respuestaAgente = agenteAutomatico.calcularTiro(tablero);
            estadoAgente = EstadoAgente.Pensando;
          }
          break;

        case Pensando:
          if (respuestaAgente.isDone()) {
            tiempoFin = System.currentTimeMillis();
            estadoAgente = EstadoAgente.Mostrar;
            indiceActual = respuestaAgente.join();
          }
          break;

        case Mostrar:
          if (System.currentTimeMillis() - tiempoFin > 500) {
            tablero.tirar(indiceActual);
            estadoAgente = EstadoAgente.Esperando;
          }
          break;
      }

      return this;
    }
  }

  private boolean volver = false;
  private boolean mouseDentroJuego = false;
  private int nuevoIndice = Tablero.COLUMNAS / 2;

  @Override
  public Componente dibujar() {
    return Flex.VCentro(
        Decorar.MouseDentro(
            dentro -> mouseDentroJuego = dentro,
            Flex.VCentro(
                new SelectorIndice(
                    indiceActual, tablero.obtenerJugadorActual(), i -> nuevoIndice = i),
                Espacio.A(16),
                new VistaTablero(tablero),
                Espacio.A(16))),
        new Boton("Volver", () -> volver = true));
  }
}
