package ia.segundoparcial.componentes;

import com.golden.gamedev.Game;
import ia.segundoparcial.Recursos;
import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;

public class BotonSimple extends Componente {
  public static final int PADDING = 8;

  private final String label;
  private final Runnable click;
  private final Dimension medida;

  public BotonSimple(String label, Runnable click) {
    this.label = label;
    this.click = click;
    this.medida = medirTexto(Recursos.FUENTE, label);
  }

  private static FontRenderContext frc;

  private static Dimension medirTexto(Font fuente, String texto) {
    if (frc == null) {
      frc = new FontRenderContext(new AffineTransform(), true, true);
    }

    GlyphVector gl = fuente.createGlyphVector(frc, texto);
    Rectangle2D medida = gl.getVisualBounds();

    return new Dimension((int) medida.getWidth(), (int) medida.getHeight());
  }

  @Override
  public void dibujar(Graphics2D g, Game game) {
    Dimension medidas = obtenerDimensiones();
    AffineTransform inversa = new AffineTransform();

    // Ahogar la excepción
    try {
      inversa = g.getTransform().createInverse();
    } catch (Exception ex) {
      System.err.println("Transformación no invertible.");
    }
    Point2D[] punto = new Point2D[] {new Point2D.Double(game.getMouseX(), game.getMouseY())};
    Point2D[] transformado = new Point2D[1];
    inversa.transform(punto, 0, transformado, 0, 1);
    Point2D mouse = transformado[0];
    boolean mouseDentro =
        0 <= mouse.getX()
            && mouse.getX() < medidas.getWidth()
            && 0 <= mouse.getY()
            && mouse.getY() < medidas.getHeight();

    g.setFont(Recursos.FUENTE);

    if (mouseDentro) g.setColor(Color.RED);
    else g.setColor(Color.BLACK);

    g.fillRect(
        0, 0, (int) (medida.getWidth() + PADDING * 2), (int) (medida.getHeight() + PADDING * 2));
    g.setColor(Color.WHITE);
    g.drawString(label, PADDING, (int) (medida.getHeight() + (double) PADDING / 1.5));
  }

  @Override
  public Dimension obtenerDimensiones() {
    return new Dimension(
        (int) (medida.getWidth() + PADDING * 2), (int) (medida.getHeight()) + PADDING * 2);
  }
}
