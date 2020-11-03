package ia.segundoparcial.componentes;

import com.golden.gamedev.Game;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

public class MarcadorProgreso extends Componente {

  private static final int PERIODO = 2000;
  private static final int MEDIDA = 32;
  private static final int MEDIDA_CENTRO = 20;
  private double progreso;

  public MarcadorProgreso(long tiempo) {
    this.progreso = (double) (tiempo % PERIODO) / (double) PERIODO;
  }

  @Override
  public void dibujar(Graphics2D g, Game game) {
    g.setColor(new Color(0x673AB7));

    double anguloBase = 360 * progreso;
    double despBase = 20 * Math.sin(progreso * Math.PI);
    g.fillArc(0, 0, MEDIDA, MEDIDA, (int) -anguloBase, (int) -(anguloBase + despBase));
    int desp = (MEDIDA - MEDIDA_CENTRO) / 2;

    g.setColor(Color.WHITE);
    g.fillOval(desp, desp, MEDIDA_CENTRO, MEDIDA_CENTRO);
  }

  @Override
  public void update(Game game, long elapsed) {}

  @Override
  public Dimension obtenerDimensiones() {
    return new Dimension(MEDIDA, MEDIDA);
  }
}
