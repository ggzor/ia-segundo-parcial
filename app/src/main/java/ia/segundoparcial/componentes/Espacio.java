package ia.segundoparcial.componentes;

import com.golden.gamedev.Game;
import java.awt.Dimension;
import java.awt.Graphics2D;

/** Una clase que no hace más que agregar espacio pero de una medida fija */
public class Espacio extends Componente {
  private final Dimension medida;

  public Espacio(int ancho, int alto) {
    this.medida = new Dimension(ancho, alto);
  }

  @Override
  public void dibujar(Graphics2D g, Game game) {
    // Un componente Espacio no tiene contenido
  }

  @Override
  public Dimension obtenerDimensiones() {
    return medida;
  }

  // Crea espacio horizontal
  public static Espacio H(int ancho) {
    return new Espacio(ancho, 0);
  }

  // Crea espacio vertical
  public static Espacio V(int alto) {
    return new Espacio(0, alto);
  }

  // Crea espacio en ambas direcciones
  public static Espacio A(int medida) {
    return new Espacio(medida, medida);
  }
}
