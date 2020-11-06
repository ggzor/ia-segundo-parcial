package ia.segundoparcial.componentes;

import com.golden.gamedev.Game;
import ia.segundoparcial.Recursos;
import java.awt.*;

// Clase que representa un componente que implementa la lógica de un botón
public class Boton extends Componente {
  public static int PAD_X = 16;
  public static int PAD_Y = 12;
  public static int ARC = 4;

  private final Label texto;
  private final Runnable accion;

  public Boton(String texto, Runnable accion) {
    this.texto = new Label(texto, Recursos.FUENTE);
    this.accion = accion;
  }

  private boolean hayClick = false;

  @Override
  public void update(Game game, long elapsed) {
    // Prepararse para recibir un click, ya que GTGE sólo nos garantiza un correcto
    // cálculo de click durante un update
    hayClick = game.click();
  }

  @Override
  public void dibujar(Graphics2D g, Game game) {
    Dimension dimensiones = obtenerDimensiones();
    Point posicionMouse = Utilerias.obtenerPosicionLocalMouse(g, game);
    boolean mouseDentro = Utilerias.dentroDe(dimensiones, posicionMouse);

    if (mouseDentro) g.setColor(Recursos.COLOR_PRIMARIO);
    else g.setColor(Recursos.FOREGROUND);

    g.fillRoundRect(0, 0, (int) dimensiones.getWidth(), (int) dimensiones.getHeight(), ARC, ARC);

    g.setColor(Recursos.BACKGROUND);
    // Ese -2 es un poco truculento
    int dy = PAD_Y + (int) (dimensiones.getHeight() / 2) - PAD_Y / 3;
    g.translate(PAD_X, dy);
    texto.dibujar(g, game);
    g.translate(-PAD_X, -dy);

    // Ejecutar acción si el mouse está dentro y se ha hecho click
    if (mouseDentro && hayClick) accion.run();
  }

  @Override
  public Dimension obtenerDimensiones() {
    Dimension dimensionTexto = texto.obtenerDimensiones();
    return new Dimension(
        (int) (dimensionTexto.getWidth() + 2 * PAD_X),
        (int) (dimensionTexto.getHeight() + 2 * PAD_Y));
  }
}
