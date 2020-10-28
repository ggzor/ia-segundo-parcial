package ia.segundoparcial.componentes;

import com.golden.gamedev.Game;
import java.awt.*;

// Clase con utilerías para decorar elementos
public class Decorar {
  public static DecoradorGrafico Color(Color color, Componente componente) {
    return new DecoradorGrafico(componente) {
      @Override
      public void dibujar(Graphics2D g, Game game) {
        g.setColor(color);
        componente.dibujar(g, game);
      }
    };
  }
}
