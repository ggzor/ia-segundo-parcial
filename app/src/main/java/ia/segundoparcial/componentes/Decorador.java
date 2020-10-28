package ia.segundoparcial.componentes;

import com.golden.gamedev.Game;
import java.awt.Dimension;
import java.awt.Graphics2D;

public abstract class Decorador extends Componente {
  protected Componente componente;

  public Decorador(Componente componente) {
    this.componente = componente;
  }

  @Override
  public void dibujar(Graphics2D g, Game game) {
    componente.dibujar(g, game);
  }

  @Override
  public void update(Game game, long elapsed) {
    componente.update(game, elapsed);
  }

  @Override
  public Dimension obtenerDimensiones() {
    return componente.obtenerDimensiones();
  }
}
