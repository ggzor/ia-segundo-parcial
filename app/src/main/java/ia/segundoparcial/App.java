package ia.segundoparcial;

import com.golden.gamedev.*;
import ia.segundoparcial.componentes.*;
import ia.segundoparcial.escenas.*;
import java.awt.*;

// Clase principal para que extiende de la clase juego de GTGE
public class App extends Game {
  // Variable para guardar la escena que se está mostrando actualmente
  private Escena escena;
  // El componente que representa la escena actual
  private Componente raiz;

  public void initResources() {
    Recursos.inicializar();

    escena = new EscenaPrincipal();
  }

  public void update(long elapsedTime) {
    // Enviar actualización para la escena actual
    escena = escena.actualizar(this, elapsedTime);

    // Generar el componente para la escena actual
    raiz = escena.dibujar();

    // Actualizar de componentes
    raiz.update(this, elapsedTime);
  }

  public void render(Graphics2D g) {
    // Activar antialising
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g.setRenderingHint(
        RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

    // Limpiar fondo de la aplicación
    g.setColor(Recursos.BACKGROUND);
    g.fillRect(0, 0, getWidth(), getHeight());

    // Centrar el componente de la escena actual
    Dimension dimension = raiz.obtenerDimensiones();
    int dx = (getWidth() - dimension.width) / 2;
    int dy = (getHeight() - dimension.height) / 2;
    g.translate(dx, dy);
    raiz.dibujar(g, this);

    // Ocultar componentes dibujados por GTGE
    g.translate(-getWidth() * 2, -getHeight() * 2);
  }

  public static void main(String[] args) throws Exception {
    // Cargar el juego
    GameLoader game = new GameLoader();
    game.setup(new App(), new Dimension(640, 480), false);

    // Iniciar juego
    game.start();
  }
}
