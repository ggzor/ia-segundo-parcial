package ia.segundoparcial.componentes;

import com.golden.gamedev.Game;
import java.awt.*;
import java.util.function.Consumer;

// Clase con utilerías para decorar elementos
public class Decorar {
  // Establecer el color antes de dibujar el componente
  public static Decorador Color(Color color, Componente componente) {
    return new Decorador(componente) {
      @Override
      public void dibujar(Graphics2D g, Game game) {
        g.setColor(color);
        componente.dibujar(g, game);
      }
    };
  }

  // Obtener la posición del mouse después del dibujado
  public static Decorador PosicionMouse(Consumer<Point> notificar, Componente componente) {
    return new Decorador(componente) {
      @Override
      public void dibujar(Graphics2D g, Game game) {
        componente.dibujar(g, game);
        notificar.accept(Utilerias.obtenerPosicionLocalMouse(g, game));
      }
    };
  }

  // Verificar si el mouse se encuentra dentro de este componente
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

  // Agregar un margen a un elemento
  public static Decorador Margen(int margenX, int margenY, Componente componente) {
    return new Decorador(componente) {
      @Override
      public void dibujar(Graphics2D g, Game game) {
        g.translate(margenX, margenY);
        super.dibujar(g, game);
        g.translate(-margenX, -margenY);
      }

      @Override
      public Dimension obtenerDimensiones() {
        Dimension anteriores = super.obtenerDimensiones();
        return new Dimension(anteriores.width + 2 * margenX, anteriores.height + 2 * margenY);
      }
    };
  }

  // Cambiar el fondo con el que se va a dibujar un componente
  public static Decorador Fondo(Color color, Componente componente) {
    return new Decorador(componente) {
      @Override
      public void dibujar(Graphics2D g, Game game) {
        Dimension medidas = componente.obtenerDimensiones();
        g.setColor(color);
        g.fillRect(0, 0, medidas.width, medidas.height);
        super.dibujar(g, game);
      }
    };
  }

  // Ocultar un componente pero conservar sus dimensiones
  public static Decorador Ocultar(boolean oculto, Componente componente) {
    return new Decorador(componente) {
      @Override
      public void dibujar(Graphics2D g, Game game) {
        if (!oculto) super.dibujar(g, game);
      }
    };
  }

  // Cambiar el componente que será dibujado a partir del arreglo de componentes
  public static Componente Conmutar(int indice, Componente... componentes) {
    return new Componente() {
      @Override
      public void dibujar(Graphics2D g, Game game) {
        componentes[indice].dibujar(g, game);
      }

      @Override
      public void update(Game game, long elapsed) {
        for (Componente componente : componentes) {
          componente.update(game, elapsed);
        }
      }

      @Override
      public Dimension obtenerDimensiones() {
        return componentes[indice].obtenerDimensiones();
      }
    };
  }
}
