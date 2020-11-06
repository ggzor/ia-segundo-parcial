package ia.segundoparcial.escenas;

import com.golden.gamedev.Game;
import ia.segundoparcial.Recursos;
import ia.segundoparcial.componentes.*;

// La escena que muestra el menú principal del juego
public class EscenaPrincipal extends Escena {
  boolean siguientePresionado = false;

  @Override
  public Escena actualizar(Game app, long elapsedTime) {
    // Cambiar de escena cuando se solicite
    if (siguientePresionado) return new EscenaDificultad();
    else return this;
  }

  @Override
  public Componente dibujar() {
    return new Flex(
        Flex.Vertical,
        Flex.Centro,
        new Componente[] {
          Decorar.Color(
              Recursos.FOREGROUND, new Label("Conecta 4 - Segundo Parcial", Recursos.FUENTE)),
          new Imagen(Recursos.SPLASH_SCREEN, 0.8),
          Espacio.A(32),
          new Boton("Jugar", () -> siguientePresionado = true),
          Espacio.A(16),
          new Boton("Salir", () -> System.exit(0))
        });
  }
}
