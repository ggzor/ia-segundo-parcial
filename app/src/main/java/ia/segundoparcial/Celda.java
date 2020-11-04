package ia.segundoparcial;

public enum Celda {
  Vacio,
  Jugador1,
  Jugador2;

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
