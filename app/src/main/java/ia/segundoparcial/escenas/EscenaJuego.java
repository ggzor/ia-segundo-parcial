package ia.segundoparcial.escenas;

import com.golden.gamedev.Game;
import ia.segundoparcial.Recursos;
import ia.segundoparcial.componentes.*;
import java.awt.Color;

public class EscenaJuego extends Escena {
  private boolean volver = false;

  @Override
  public Escena actualizar(Game app, long elapsedTime) {
    if (volver) return new EscenaPrincipal();
    return this;
  }

  @Override
  public Componente dibujar() {
    return new Flex(
        Flex.Vertical,
        Flex.Centro,
        new Componente[] {
          Decorar.Color(Color.BLACK, new Label("Comienza a jugar ya!", Recursos.FUENTE)),
          new Imagen(Recursos.SPLASH_SCREEN, 0.2),
          new Boton("Volver", () -> volver = true),
        });
  }
}
