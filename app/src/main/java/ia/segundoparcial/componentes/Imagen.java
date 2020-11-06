package ia.segundoparcial.componentes;

import com.golden.gamedev.Game;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;

// Clase para mostrar una imagen con una escala dada
public class Imagen extends Componente {

  private BufferedImage imagen;
  private double escala;

  public Imagen(BufferedImage bi, double escala) {
    this.imagen = bi;
    this.escala = escala;
  }

  @Override
  public void dibujar(Graphics2D g, Game game) {
    g.drawImage(imagen, AffineTransform.getScaleInstance(escala, escala), null);
  }

  @Override
  public Dimension obtenerDimensiones() {
    return new Dimension((int) (imagen.getWidth() * escala), (int) (imagen.getHeight() * escala));
  }

  @Override
  public void update(Game game, long elapsed) {}
}
