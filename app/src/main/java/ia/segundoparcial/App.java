package ia.segundoparcial;

import com.golden.gamedev.*;
import ia.segundoparcial.componentes.*;
import ia.segundoparcial.estados.*;
import java.awt.*;

public class App extends Game {
  private Estado estado;

  public App() {}

  public void initResources() {
    Recursos.inicializar();

    estado = new EstadoInicial();
  }

  public void update(long elapsedTime) {
    estado = estado.actualizar(this, elapsedTime);
  }

  public void render(Graphics2D g) {
    // Activar antialising
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g.setRenderingHint(
        RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

    g.setColor(Color.WHITE);
    g.fillRect(0, 0, getWidth(), getHeight());

    Componente raiz = estado.dibujar();
    raiz.dibujar(g, this);

    g.translate(-getWidth() * 2, -getHeight() * 2);
  }

  public static void main(String[] args) throws Exception {
    GameLoader game = new GameLoader();
    game.setup(new App(), new Dimension(640, 480), false);
    game.start();
  }
}
