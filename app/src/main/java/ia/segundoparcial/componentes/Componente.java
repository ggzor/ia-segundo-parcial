package ia.segundoparcial.componentes;

import com.golden.gamedev.Game;
import java.awt.*;

// Clase que representa un componente dibujable y actualizable por GTGE
public abstract class Componente {
  // Método principal para dibujar el componente actual
  public abstract void dibujar(Graphics2D g, Game game);

  // Método para realizar la actualización de variable de los componentes
  public abstract void update(Game game, long elapsed);

  // Método para determinar las dimensiones de este componente
  public abstract Dimension obtenerDimensiones();
}
