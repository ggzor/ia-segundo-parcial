package ia.segundoparcial.escenas;

import com.golden.gamedev.Game;
import ia.segundoparcial.componentes.*;

// Clase para representar una escena del juego
public abstract class Escena {

  // Actualizar las propiedades de la escena con el estado del juego
  public abstract Escena actualizar(Game app, long elapsedTime);

  // Crear una representación de la escena que posteriormente se dibujará
  public abstract Componente dibujar();
}
