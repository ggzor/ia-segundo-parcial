package ia.segundoparcial.componentes;

import com.golden.gamedev.Game;
import java.awt.*;
import java.awt.geom.*;

public class Utilerias {
  // Transforma la posición del mouse a coordenadas locales de acuerdo a las
  // transformaciones de g
  public static Point obtenerPosicionLocalMouse(Graphics2D g, Game game) {
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

    return new Point((int) transformado[0].getX(), (int) transformado[0].getY());
  }

  public static boolean dentroDe(Dimension medidas, Point mouse) {
    return 0 <= mouse.getX()
        && mouse.getX() < medidas.getWidth()
        && 0 <= mouse.getY()
        && mouse.getY() < medidas.getHeight();
  }
}
