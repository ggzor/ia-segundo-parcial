package ia.segundoparcial.escenas;

import com.golden.gamedev.Game;
import ia.segundoparcial.Tablero;
import ia.segundoparcial.componentes.*;
import java.awt.event.*;
import java.util.HashMap;

public class EscenaJuego extends Escena {
  public static final int DEBOUNCE_MOVIMIENTO_MS = 190;

  private boolean volver = false;

  private Tablero tablero;
  private int indiceActual;

  public EscenaJuego() {
    tablero = new Tablero();
    indiceActual = Tablero.COLUMNAS / 2;
  }

  private static HashMap<Integer, HashMap<Integer, Long>> registro = new HashMap<>();

  private static void debounce(Object id1, int id2, long tiempo, final Runnable accion) {
    HashMap<Integer, Long> tiempos =
        registro.computeIfAbsent(
            id1.hashCode(),
            k -> {
              HashMap<Integer, Long> nuevo = new HashMap<>();
              accion.run();
              nuevo.put(id2, System.currentTimeMillis());
              return nuevo;
            });

    long ultimo = tiempos.getOrDefault((Integer) id2, -1L);
    long ahora = System.currentTimeMillis();
    if (ahora - tiempo > ultimo) {
      tiempos.put(id2, ahora);
      accion.run();
    }
  }

  @Override
  public Escena actualizar(Game app, long elapsedTime) {
    if (volver) return new EscenaPrincipal();
    else {
      if (app.keyDown(KeyEvent.VK_RIGHT)) {
        debounce(
            this,
            0,
            DEBOUNCE_MOVIMIENTO_MS,
            () -> indiceActual = Math.min(Tablero.COLUMNAS - 1, indiceActual + 1));
      } else if (app.keyDown(KeyEvent.VK_LEFT)) {
        debounce(
            this, 0, DEBOUNCE_MOVIMIENTO_MS, () -> indiceActual = Math.max(0, indiceActual - 1));
      } else if (app.keyPressed(KeyEvent.VK_ENTER) || app.keyPressed(KeyEvent.VK_SPACE)) {
        tablero.tirar(indiceActual);
      }

      return this;
    }
  }

  @Override
  public Componente dibujar() {
    return new Flex(
        Flex.Vertical,
        Flex.Centro,
        new Componente[] {
          // spotless:off
          new SelectorIndice(indiceActual, tablero.obtenerJugadorActual()),
          Espacio.A(16),
          new VistaTablero(tablero),
          Espacio.A(16),
          new Boton("Volver", () -> volver = true),
          // spotless:on
        });
  }
}
