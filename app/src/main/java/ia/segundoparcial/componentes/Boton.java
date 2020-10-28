package ia.segundoparcial.componentes;

import ia.segundoparcial.Recursos;

import com.golden.gamedev.Game;

import java.awt.*;

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
    hayClick = game.click();
  }

  @Override
  public void dibujar(Graphics2D g, Game game) {
    Dimension dimensiones = obtenerDimensiones();
    Point posicionMouse = Utilerias.obtenerPosicionLocalMouse(g, game);
    boolean mouseDentro = Utilerias.dentroDe(dimensiones, posicionMouse);

    if (mouseDentro) g.setColor(Color.RED);
    else g.setColor(Color.BLACK);

    g.fillRoundRect(0, 0, (int) dimensiones.getWidth(), (int) dimensiones.getHeight(), ARC, ARC);

    g.setColor(Color.WHITE);
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
