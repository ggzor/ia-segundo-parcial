package ia.segundoparcial.componentes;

import com.golden.gamedev.Game;
import ia.segundoparcial.Celda;
import ia.segundoparcial.Tablero;
import java.awt.*;
import java.util.function.Consumer;

/** Componente para mostrar la selección de un índice donde tirar */
public class SelectorIndice extends Componente {
  public static final int ALTURA = 24;

  private final int indiceActual;
  private final Celda jugadorActual;
  private final Consumer<Integer> actualizarIndice;

  public SelectorIndice(int indiceActual, Celda jugadorActual, Consumer<Integer> actualizarIndice) {
    this.indiceActual = indiceActual;
    this.jugadorActual = jugadorActual;
    this.actualizarIndice = actualizarIndice;
  }

  @Override
  public void dibujar(Graphics2D g, Game game) {
    int x = indiceActual * (VistaTablero.GAP + VistaTablero.MEDIDA_CELDA);

    Polygon triangulo = new Polygon();
    triangulo.addPoint(0, 0);
    triangulo.addPoint(VistaTablero.MEDIDA_CELDA, 0);
    triangulo.addPoint(VistaTablero.MEDIDA_CELDA / 2, ALTURA);

    g.setColor(VistaTablero.colorParaCelda(jugadorActual));

    g.translate(x, 0);
    g.fillPolygon(triangulo);
    g.translate(-x, 0);

    Point mouse = Utilerias.obtenerPosicionLocalMouse(g, game);
    Dimension medidas = obtenerDimensiones();

    if (0 <= mouse.x && mouse.x <= medidas.width) {
      int mouseX = mouse.x / (VistaTablero.GAP + VistaTablero.MEDIDA_CELDA);

      actualizarIndice.accept(Math.max(0, Math.min(mouseX, Tablero.COLUMNAS - 1)));
    }
  }

  @Override
  public void update(Game game, long elapsed) {}

  @Override
  public Dimension obtenerDimensiones() {
    return new Dimension(Tablero.COLUMNAS * (VistaTablero.GAP + VistaTablero.MEDIDA_CELDA), ALTURA);
  }
}
