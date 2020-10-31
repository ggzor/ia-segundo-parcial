package ia.segundoparcial.alphabeta;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ia.segundoparcial.utils.Par;
import java.util.*;
import org.junit.jupiter.api.Test;

public class AlphaBetaTest {
  public static enum Direccion {
    Izquierda,
    Derecha
  };

  public static class EstadoEjemplo1 implements Estado<Direccion> {
    private final ArrayList<Estado<Direccion>> hijos;
    private final Integer valor;
    private final Direccion direccion;

    public EstadoEjemplo1(Integer valor, Direccion direccion, ArrayList<Estado<Direccion>> hijos) {
      this.hijos = hijos;
      this.valor = valor;
      this.direccion = direccion;
    }

    @Override
    public Direccion generadoPor() {
      return direccion;
    }

    @Override
    public ArrayList<Estado<Direccion>> expandir() {
      return this.hijos;
    }

    @Override
    public double f() {
      return valor;
    }

    @Override
    public boolean esTerminal() {
      return hijos == null;
    }
  }

  private static EstadoEjemplo1 EI(Estado<Direccion>... elementos) {
    return new EstadoEjemplo1(null, Direccion.Izquierda, generar(elementos));
  }

  private static EstadoEjemplo1 ED(Estado<Direccion>... elementos) {
    return new EstadoEjemplo1(null, Direccion.Derecha, generar(elementos));
  }

  private static EstadoEjemplo1 EIL(int valor) {
    return new EstadoEjemplo1(valor, Direccion.Izquierda, null);
  }

  private static EstadoEjemplo1 EDL(int valor) {
    return new EstadoEjemplo1(valor, Direccion.Derecha, null);
  }

  private static ArrayList<Estado<Direccion>> generar(Estado<Direccion>... elementos) {
    ArrayList<Estado<Direccion>> lista = new ArrayList<>();
    for (Estado<Direccion> elemento : elementos) {
      lista.add(elemento);
    }
    return lista;
  }

  @Test
  public void ejemploProfesor() {
    EstadoEjemplo1 inicial =
        // spotless:off
      new EstadoEjemplo1(null, null, generar(
        EI(
          EI(
            EI(
              EIL(16),
              EDL(15)
            ),
            ED(
              EIL(14),
              EDL(13)
            )
          ),
          ED(
            EI(
              EIL(12),
              EDL(11)
            ),
            ED(
              EIL(10),
              EDL(9)
            )
          )
        ),
        ED(
          EI(
            EI(
              EIL(8),
              EDL(7)
            ),
            ED(
              EIL(6),
              EDL(5)
            )
          ),
          ED(
            EI(
              EIL(4),
              EDL(3)
            ),
            ED(
              EIL(2),
              EDL(1)
            )
          )
        )
      ));
      // spotless:on

    Par<Double, Direccion> resultado = AlphaBeta.calcular(inicial, 4);
    assertEquals(11.0, resultado.primero, 0.001);
    assertEquals(Direccion.Izquierda, resultado.segundo);
  }
}
