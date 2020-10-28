package ia.segundoparcial.componentes;

import com.golden.gamedev.Game;
import java.awt.*;
import java.awt.geom.*;
import java.util.Arrays;
import java.util.function.*;

public class Flex extends Componente {
  private final Function<Dimension, Double> ejePrimario;
  private final Function<Dimension, Double> ejeSecundario;

  private final BiConsumer<Double, Graphics2D> transformadorPrimario;
  private final BiConsumer<Double, Graphics2D> transformadorSecundario;

  private final BinaryOperator<Dimension> incrementarDimension;
  private final BinaryOperator<Double> ajuste;

  private final Componente[] componentes;

  public Flex(Direccion direccion, Alineamiento alineamiento, Componente[] componentes) {
    this.componentes = componentes;

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
    AffineTransform previa = g.getTransform();

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
