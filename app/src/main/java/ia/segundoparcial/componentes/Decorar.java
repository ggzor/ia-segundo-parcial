package ia.segundoparcial.componentes;

import com.golden.gamedev.Game;
import java.awt.*;
import java.util.function.Consumer;

// Clase con utilerías para decorar elementos
public class Decorar {
  public static Decorador Color(Color color, Componente componente) {
    return new Decorador(componente) {
      @Override
      public void dibujar(Graphics2D g, Game game) {
        g.setColor(color);
        componente.dibujar(g, game);
      }
    };
  }

  public static Decorador PosicionMouse(Consumer<Point> notificar, Componente componente) {
    return new Decorador(componente) {
      @Override
      public void dibujar(Graphics2D g, Game game) {
        componente.dibujar(g, game);
        notificar.accept(Utilerias.obtenerPosicionLocalMouse(g, game));
      }
    };
  }

  public static Decorador MouseDentro(Consumer<Boolean> notificar, Componente componente) {
    return new Decorador(componente) {
      private Point mouse;

      {
        componente = PosicionMouse(p -> mouse = p, componente);
      }

      @Override
      public void dibujar(Graphics2D g, Game game) {
        super.dibujar(g, game);
        notificar.accept(Utilerias.dentroDe(obtenerDimensiones(), mouse));
      }
    };
  }
}
