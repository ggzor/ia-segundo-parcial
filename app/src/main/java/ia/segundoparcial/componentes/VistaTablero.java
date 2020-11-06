package ia.segundoparcial.componentes;

import com.golden.gamedev.Game;
import ia.segundoparcial.Celda;
import ia.segundoparcial.Recursos;
import ia.segundoparcial.Tablero;
import java.awt.*;

// Componente para mostrar un tablero
public class VistaTablero extends Componente {
  public static final int MEDIDA_CELDA = 32;
  public static final int GAP = 8;

  public static Color colorParaCelda(Celda celda) {
    switch (celda) {
      case Jugador1:
        return Recursos.COLOR_JUGADOR1;
      case Jugador2:
        return Recursos.COLOR_JUGADOR2;
      default:
      case Vacio:
        return Recursos.COLOR_VACIO;
    }
  }

  private Tablero tablero;

  public VistaTablero(Tablero tablero) {
    this.tablero = tablero;
  }

  @Override
  public void dibujar(Graphics2D g, Game game) {
    for (int y = 0; y < Tablero.FILAS; y++) {
      for (int x = 0; x < Tablero.COLUMNAS; x++) {
        int sx = x * (MEDIDA_CELDA + GAP);
        int sy = y * (MEDIDA_CELDA + GAP);

        Celda celda = tablero.obtener(y, x);

        g.setColor(colorParaCelda(celda));
        g.fillOval(sx, sy, MEDIDA_CELDA, MEDIDA_CELDA);
      }
    }
  }

  @Override
  public void update(Game game, long elapsed) {}

  @Override
  public Dimension obtenerDimensiones() {
    return new Dimension(
        Tablero.COLUMNAS * (MEDIDA_CELDA + GAP), Tablero.FILAS * (MEDIDA_CELDA + GAP));
  }
}
