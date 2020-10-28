package ia.segundoparcial.componentes;

import com.golden.gamedev.Game;
import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// Clase para agregar texto
public class Label extends Componente {

  private final String texto;
  private final Font fuente;

  public Label(String texto, Font fuente) {
    this.texto = texto;
    this.fuente = fuente;
  }

  @Override
  public void dibujar(Graphics2D g, Game game) {
    Dimension posibles = dimensionesPosibles(texto, fuente);
    Dimension visuales = dimensionesVisuales(texto, fuente);

    int dx = (int) ((posibles.getWidth() - visuales.getWidth()) / 2);
    int dy = (int) ((posibles.getHeight() - visuales.getHeight()) / 2);

    g.setFont(fuente);
    g.drawString(texto, dx, dy);
  }

  @Override
  public Dimension obtenerDimensiones() {
    return dimensionesPosibles(texto, fuente);
  }

  // Cálculo de dimensiones máximas y reales del texto
  private static final FontRenderContext frc =
      new FontRenderContext(new AffineTransform(), true, true);
  private static final String caracteresImportantes =
      IntStream.rangeClosed(65, 90)
          .mapToObj(i -> Character.valueOf((char) i).toString())
          .collect(Collectors.joining());

  private static Dimension dimensionesPosibles(String texto, Font fuente) {
    GlyphVector gl = fuente.createGlyphVector(frc, texto);
    GlyphVector glCaracteresImportantes = fuente.createGlyphVector(frc, caracteresImportantes);

    return new Dimension(
        (int) gl.getVisualBounds().getWidth(),
        (int) glCaracteresImportantes.getVisualBounds().getHeight());
  }

  private static Dimension dimensionesVisuales(String texto, Font fuente) {
    GlyphVector gl = fuente.createGlyphVector(frc, texto);
    Rectangle2D medidas = gl.getVisualBounds();

    return new Dimension((int) medidas.getWidth(), (int) medidas.getHeight());
  }
}
