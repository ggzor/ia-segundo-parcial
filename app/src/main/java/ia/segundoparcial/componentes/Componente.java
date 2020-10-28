package ia.segundoparcial.componentes;

import com.golden.gamedev.Game;
import java.awt.*;

public abstract class Componente {
  public abstract void dibujar(Graphics2D g, Game game);

  public abstract Dimension obtenerDimensiones();
}
