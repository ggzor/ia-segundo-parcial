package ia.segundoparcial.escenas;

import com.golden.gamedev.Game;
import ia.segundoparcial.Recursos;
import ia.segundoparcial.componentes.*;

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
          // spotless:off
          new Imagen(Recursos.SPLASH_SCREEN, 0.3), 
          new Boton("Volver", () -> volver = true),
          // spotless:on
        });
  }
}
