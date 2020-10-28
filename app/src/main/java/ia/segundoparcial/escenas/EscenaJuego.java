package ia.segundoparcial.escenas;

import com.golden.gamedev.Game;
import ia.segundoparcial.Celda;
import ia.segundoparcial.Tablero;
import ia.segundoparcial.componentes.*;
import java.util.*;
import java.util.concurrent.*;

public class EscenaJuego extends Escena {
  public static final int DEBOUNCE_MOVIMIENTO_MS = 190;

  private static ForkJoinPool pool = new ForkJoinPool();
  private static Random random = new Random();

  private boolean volver = false;

  private boolean mouseDentroJuego = false;
  private int nuevoIndice = Tablero.COLUMNAS / 2;

  private Tablero tablero;
  private int indiceActual;

  private ForkJoinTask<Integer> resultadoTiro;
  private EstadoIA estadoIA = EstadoIA.Esperando;
  private long tiempoFin;

  public EscenaJuego() {
    tablero = new Tablero();
    indiceActual = Tablero.COLUMNAS / 2;
  }

  public static enum EstadoIA {
    Esperando,
    Pensando,
    Mostrar
  };

  @Override
  public Escena actualizar(Game app, long elapsedTime) {
    if (volver) return new EscenaPrincipal();
    else {
      if (tablero.obtenerJugadorActual() == Celda.Jugador1) {
        if (mouseDentroJuego) {
          indiceActual = nuevoIndice;

          if (app.click()) {
            tablero.tirar(indiceActual);
          }
        }
      } else {
        switch (estadoIA) {
          default:
          case Esperando:
            resultadoTiro =
                pool.submit(
                    () -> {
                      Thread.sleep(500);
                      return random.nextInt(Tablero.COLUMNAS);
                    });
            estadoIA = EstadoIA.Pensando;
            break;

          case Pensando:
            if (resultadoTiro.isDone()) {
              tiempoFin = System.currentTimeMillis();
              estadoIA = EstadoIA.Mostrar;
              indiceActual = resultadoTiro.join();
            }
            break;

          case Mostrar:
            if (System.currentTimeMillis() - tiempoFin > 500) {
              tablero.tirar(indiceActual);
              estadoIA = EstadoIA.Esperando;
            }
            break;
        }
      }

      return this;
    }
  }

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
