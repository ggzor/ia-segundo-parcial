package ia.segundoparcial.componentes;

import com.golden.gamedev.Game;
import ia.segundoparcial.Celda;
import ia.segundoparcial.Tablero;
import java.awt.*;

/** Componente para mostrar la selección de un índice donde tirar */
public class SelectorIndice extends Componente {
  public static final int ALTURA = 24;

  private final int indiceActual;
  private final Celda jugadorActual;

  public SelectorIndice(int indiceActual, Celda jugadorActual) {
    this.indiceActual = indiceActual;
    this.jugadorActual = jugadorActual;
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
  }

  @Override
  public void update(Game game, long elapsed) {}

  @Override
  public Dimension obtenerDimensiones() {
    return new Dimension(Tablero.COLUMNAS * (VistaTablero.GAP + VistaTablero.MEDIDA_CELDA), ALTURA);
  }
}
