package ia.segundoparcial.escenas;

import com.golden.gamedev.Game;
import ia.segundoparcial.*;
import ia.segundoparcial.agentes.*;
import ia.segundoparcial.componentes.*;
import java.util.concurrent.*;

// La escena del juego principal
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
  private long tiempoInicioPensar;
  private long tiempoFin;

  private boolean teniaGanador = false;
  private Celda ganador;
  private long tiempoGanador;

  @Override
  public Escena actualizar(Game app, long elapsedTime) {
    // Cuando se presiona el botón volver se devuelve al menú anterior
    if (volver) return new EscenaDificultad();
    else {
      // Verificar si existe un ganador o si ha ocurrido un empate
      ganador =
          tablero.esGanador(Celda.Jugador1)
              ? Celda.Jugador1
              : tablero.esGanador(Celda.Jugador2)
                  ? Celda.Jugador2
                  : tablero.estaLleno() ? Celda.Vacio : null;

      if (ganador != null) {
        // Es la primera vez que aparece un ganador
        if (!teniaGanador) {
          tiempoGanador = System.currentTimeMillis();
        }
      } else {
        Agente agente = tablero.obtenerJugadorActual() == Celda.Jugador1 ? jugador1 : jugador2;

        switch (estadoAgente) {
          default:
          case Esperando:
            // Si el agente es una persona, esperar tiro con el mouse
            if (agente instanceof Persona) {
              if (mouseDentroJuego) {
                indiceActual = nuevoIndice;

                if (app.click()) {
                  tablero.tirar(indiceActual);
                }
              }
            } else {
              // Lanzar la ejecución del cálculo del tiro del agente automático
              AgenteAutomatico agenteAutomatico = (AgenteAutomatico) agente;
              respuestaAgente = agenteAutomatico.calcularTiro(tablero);
              tiempoInicioPensar = System.currentTimeMillis();
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
      }
      if (ganador != null && !teniaGanador) {
        teniaGanador = true;
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
                Decorar.Conmutar(
                    estadoAgente == EstadoAgente.Pensando ? 0 : 1,
                    new Flex(
                        Flex.Horizontal,
                        Flex.Centro,
                        new Componente[] {
                          new MarcadorProgreso(System.currentTimeMillis() - tiempoInicioPensar),
                          Espacio.A(16),
                          Flex.VCentro(
                              Espacio.V(28),
                              Decorar.Color(
                                  Recursos.FOREGROUND,
                                  new Label("Calculando movimiento...", Recursos.FUENTE)))
                        }),
                    Flex.VCentro(
                        Espacio.V(20),
                        new SelectorIndice(
                            indiceActual, tablero.obtenerJugadorActual(), i -> nuevoIndice = i))),
                Espacio.A(16),
                new VistaTablero(tablero))),
        Espacio.A(32),
        Decorar.Ocultar(
            ganador == null,
            Decorar.Color(
                Recursos.FOREGROUND,
                new Label(
                    ganador == Celda.Vacio ? "Empate" : "El ganador es: " + ganador,
                    Recursos.FUENTE))),
        Espacio.A(16),
        new Boton("Volver", () -> volver = true));
  }
}
