package ia.segundoparcial.estados;

import com.golden.gamedev.Game;
import ia.segundoparcial.componentes.*;

public abstract class Estado {

  public abstract Estado actualizar(Game app, long elapsedTime);

  public abstract Componente dibujar();
}
