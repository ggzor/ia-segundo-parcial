package ia.segundoparcial.estados;

import com.golden.gamedev.Game;
import ia.segundoparcial.Recursos;
import ia.segundoparcial.componentes.*;

public class EstadoJuego extends Estado {
  private boolean volver = false;

  @Override
  public Estado actualizar(Game app, long elapsedTime) {
    if (volver) return new EstadoInicial();
    return this;
  }

  @Override
  public Componente dibujar() {
    return new Flex(
        Flex.Horizontal,
        Flex.Centro,
        new Componente[] {
          // spotless:off
          new Imagen(Recursos.SPLASH_SCREEN, 0.3), 
          new Boton("Volver", () -> volver = true),
          // spotless:on
        });
  }
}
