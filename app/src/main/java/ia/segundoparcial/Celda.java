package ia.segundoparcial;

// Enumeración para representar el estado de una celda
public enum Celda {
  Vacio,
  Jugador1,
  Jugador2;

  // Calcular el jugador contrario al actual
  public Celda contrario() {
    switch (this) {
      default:
      case Vacio:
        return Vacio;
      case Jugador1:
        return Jugador2;
      case Jugador2:
        return Jugador1;
    }
  }
}
