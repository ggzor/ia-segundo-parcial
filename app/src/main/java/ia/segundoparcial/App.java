package ia.segundoparcial;

import com.golden.gamedev.*;
import ia.segundoparcial.componentes.*;
import ia.segundoparcial.escenas.*;
import java.awt.*;

public class App extends Game {
  private Escena escena;

  public App() {}

  public void initResources() {
    Recursos.inicializar();

    escena = new EscenaPrincipal();
  }

  private Componente raiz;

  public void update(long elapsedTime) {
    escena = escena.actualizar(this, elapsedTime);

    // Dibujar componentes
    raiz = escena.dibujar();

    // Actualizar estados
    raiz.update(this, elapsedTime);
  }

  public void render(Graphics2D g) {
    // Activar antialising
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g.setRenderingHint(
        RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

    g.setColor(Color.WHITE);
    g.fillRect(0, 0, getWidth(), getHeight());

    Dimension dimension = raiz.obtenerDimensiones();

    int dx = (getWidth() - dimension.width) / 2;
    int dy = (getHeight() - dimension.height) / 2;

    g.translate(dx, dy);
    raiz.dibujar(g, this);

    g.translate(-getWidth() * 2, -getHeight() * 2);
  }

  public static void main(String[] args) throws Exception {
    GameLoader game = new GameLoader();
    game.setup(new App(), new Dimension(640, 480), false);
    game.start();
  }
}
