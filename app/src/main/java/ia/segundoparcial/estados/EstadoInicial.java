package ia.segundoparcial.estados;

import com.golden.gamedev.Game;
import ia.segundoparcial.Recursos;
import ia.segundoparcial.componentes.*;

public class EstadoInicial extends Estado {
  boolean siguientePresionado = false;

  @Override
  public Estado actualizar(Game app, long elapsedTime) {
    if (siguientePresionado) return new EstadoJuego();
    else return this;
  }

  @Override
  public Componente dibujar() {
    return new Flex(
        Flex.Vertical,
        Flex.Centro,
        new Componente[] {
          new Imagen(Recursos.SPLASH_SCREEN, 0.2),
          new BotonSimple("Jugar", () -> siguientePresionado = true),
          Espacio.V(12),
          new BotonSimple("Texto un poco largo", () -> siguientePresionado = true),
          Espacio.V(12),
          new BotonSimple("Salir", () -> System.exit(0))
        });
  }
}
