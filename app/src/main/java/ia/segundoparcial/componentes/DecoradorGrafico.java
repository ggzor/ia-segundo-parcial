package ia.segundoparcial.componentes;

import com.golden.gamedev.Game;
import java.awt.Dimension;

public abstract class DecoradorGrafico extends Componente {
  protected final Componente componente;

  public DecoradorGrafico(Componente componente) {
    this.componente = componente;
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
