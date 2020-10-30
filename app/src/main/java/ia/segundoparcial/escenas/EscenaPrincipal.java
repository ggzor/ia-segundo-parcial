package ia.segundoparcial.escenas;

import com.golden.gamedev.Game;
import ia.segundoparcial.Recursos;
import ia.segundoparcial.componentes.*;

public class EscenaPrincipal extends Escena {
  boolean siguientePresionado = false;

  @Override
  public Escena actualizar(Game app, long elapsedTime) {
    if (siguientePresionado) return new EscenaDificultad();
    else return this;
  }

  @Override
  public Componente dibujar() {
    return new Flex(
        Flex.Vertical,
        Flex.Centro,
        new Componente[] {
          new Imagen(Recursos.SPLASH_SCREEN, 0.2),
          new Boton("Jugar", () -> siguientePresionado = true),
          Espacio.A(12),
          new Boton("Salir", () -> System.exit(0))
        });
  }
}
