package ia.segundoparcial.componentes;

import com.golden.gamedev.Game;
import java.awt.*;
import java.awt.geom.*;
import java.util.Arrays;
import java.util.function.*;

/**
 * Clase que puede apilar sub-componentes en cualquiera de los dos ejes
 * y los puede alinear al inicio, centro y fin.
 */
public class Flex extends Componente {
  // Funciones para obtener el eje primario y secundario de una dimension
  // Estos valores dependen de la dirección que se haya escogido para el componente
  private final Function<Dimension, Double> ejePrimario;
  private final Function<Dimension, Double> ejeSecundario;

  // Una idea similar a las anteriores funciones, pero para aplicar una translación
  // en los ejes de un Graphics2D
  private final BiConsumer<Double, Graphics2D> transformadorPrimario;
  private final BiConsumer<Double, Graphics2D> transformadorSecundario;

  // La regla de como incrementar dimensiones dependiendo del eje
  private final BinaryOperator<Dimension> incrementarDimension;

  // Este operador depende del tipo de alineamiento que se utilice, tomando el máximo
  // y el valor actual
  private final BinaryOperator<Double> ajuste;

  private final Componente[] componentes;

  public Flex(Direccion direccion, Alineamiento alineamiento, Componente[] componentes) {
    this.componentes = componentes;

    // Determinar funciones que dependen de la dirección
    switch (direccion) {
      case Horizontal:
        ejePrimario = d -> d.getWidth();
        ejeSecundario = d -> d.getHeight();

        transformadorPrimario = (d, g) -> g.translate(d, 0);
        transformadorSecundario = (d, g) -> g.translate(0, d);

        incrementarDimension =
            (d1, d2) ->
                new Dimension(
                    (int) (d1.getWidth() + d2.getWidth()),
                    (int) Math.max(d1.getHeight(), d2.getHeight()));
        break;

      case Vertical:
      default:
        ejePrimario = d -> d.getHeight();
        ejeSecundario = d -> d.getWidth();

        transformadorPrimario = (d, g) -> g.translate(0, d);
        transformadorSecundario = (d, g) -> g.translate(d, 0);
        incrementarDimension =
            (d1, d2) ->
                new Dimension(
                    (int) Math.max(d1.getWidth(), d2.getWidth()),
                    (int) (d1.getHeight() + d2.getHeight()));
        break;
    }

    // Determinar funciones que dependen del alineamiento
    switch (alineamiento) {
      default:
      case Inicio:
        ajuste = (max, val) -> 0.0;
        break;
      case Centro:
        ajuste = (max, val) -> (max - val) / 2;
        break;
      case Fin:
        ajuste = (max, val) -> max - val;
        break;
    }
  }

  @Override
  public void dibujar(Graphics2D g, Game game) {
    // Guardar transformación anterior
    AffineTransform previa = g.getTransform();

    // Apilar los componentes de acuerdo a la dirección y alineamiento específicos
    double maximo = ejeSecundario.apply(obtenerDimensiones());
    for (Componente hijo : componentes) {
      if (hijo != null) {
        Dimension dimensiones = hijo.obtenerDimensiones();
        double desplazamiento = ajuste.apply(maximo, ejeSecundario.apply(dimensiones));

        // Aplicar transformaciones y dibujar
        transformadorSecundario.accept(desplazamiento, g);
        hijo.dibujar(g, game);
        transformadorSecundario.accept(-desplazamiento, g);

        transformadorPrimario.accept(ejePrimario.apply(dimensiones), g);
      }
    }

    // Restaurar transformación anterior
    g.setTransform(previa);
  }

  @Override
  public Dimension obtenerDimensiones() {
    return Arrays.stream(componentes)
        .filter(h -> h != null)
        .map(h -> h.obtenerDimensiones())
        .reduce(new Dimension(0, 0), incrementarDimension);
  }

  // Propiedades
  public static enum Direccion {
    Horizontal,
    Vertical
  };

  public static final Direccion Horizontal = Direccion.Horizontal;
  public static final Direccion Vertical = Direccion.Vertical;

  public static enum Alineamiento {
    Inicio,
    Centro,
    Fin
  };

  public static final Alineamiento Inicio = Alineamiento.Inicio;
  public static final Alineamiento Centro = Alineamiento.Centro;
  public static final Alineamiento Fin = Alineamiento.Fin;
}
