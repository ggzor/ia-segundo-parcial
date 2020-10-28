package ia.segundoparcial.estados;

import com.golden.gamedev.Game;
import ia.segundoparcial.componentes.*;

public class EstadoJuego extends Estado {
  @Override
  public Estado actualizar(Game app, long elapsedTime) {
    return this;
  }

  @Override
  public Componente dibujar() {
    return null;
  }
}
