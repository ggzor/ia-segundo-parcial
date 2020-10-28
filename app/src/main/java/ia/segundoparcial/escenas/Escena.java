package ia.segundoparcial.escenas;

import com.golden.gamedev.Game;
import ia.segundoparcial.componentes.*;

public abstract class Escena {

  public abstract Escena actualizar(Game app, long elapsedTime);

  public abstract Componente dibujar();
}
