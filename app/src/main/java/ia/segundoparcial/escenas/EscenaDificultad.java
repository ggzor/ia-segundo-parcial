package ia.segundoparcial.escenas;

import com.golden.gamedev.Game;
import ia.segundoparcial.ParametrosEvaluacion;
import ia.segundoparcial.Recursos;
import ia.segundoparcial.agentes.*;
import ia.segundoparcial.componentes.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.IntStream;

/** EscenaDificultad */
public class EscenaDificultad extends Escena {
  public static final int PROFUNDIDAD_FACIL = 2;
  public static final int PROFUNDIDAD_MODERADO = 5;
  public static final int PROFUNDIDAD_DIFICIL = 7;

  public static final double alpha1 = 0.6;
  public static final double alpha2 = 0.1;
  public static final double alpha3 = 0.3;

  public static final double c1 = 0.1;
  public static final double c2 = 0.9;

  public static final double maxh1 = 12 * (c1 + c2);
  public static final double maxh2 = (24.0 + 30.0 + 36.0) / 3.0;
  public static final double maxh3 = 5.0;

  public static final ParametrosEvaluacion evaluacion =
      new ParametrosEvaluacion(alpha1, maxh1, alpha2, maxh2, alpha3, maxh3, c1, c2);

  private int jugador1 = 0;
  private int jugador2 = 3;

  private ArrayList<String> etiquetas = new ArrayList<>();
  private ArrayList<Supplier<Agente>> generadores = new ArrayList<>();

  public EscenaDificultad() {
    etiquetas.add("Persona");
    generadores.add(() -> new Persona());

    etiquetas.add("Aleatoria");
    generadores.add(() -> new AgenteAleatorio());

    etiquetas.add(String.format("Fácil-%d", PROFUNDIDAD_FACIL));
    generadores.add(() -> new AgenteAlphaBeta(PROFUNDIDAD_FACIL, evaluacion));
    etiquetas.add(String.format("Moderada-%d", PROFUNDIDAD_MODERADO));
    generadores.add(() -> new AgenteAlphaBeta(PROFUNDIDAD_MODERADO, evaluacion));
    etiquetas.add(String.format("Difícil-%d", PROFUNDIDAD_DIFICIL));
    generadores.add(() -> new AgenteAlphaBeta(PROFUNDIDAD_DIFICIL, evaluacion));
  }

  private boolean continuar = false;
  private boolean volver = false;

  @Override
  public Escena actualizar(Game app, long elapsedTime) {
    if (continuar)
      return new EscenaJuego(generadores.get(jugador1).get(), generadores.get(jugador2).get());
    else if (volver) return new EscenaPrincipal();
    else return this;
  }

  @Override
  public Componente dibujar() {
    return Flex.VCentro(
        Decorar.Color(Recursos.FOREGROUND, new Label("Selección de jugadores", Recursos.FUENTE)),
        Espacio.A(32),
        dibujarOpcionesJugador(1, i -> jugador1 = i, () -> jugador1),
        Espacio.A(32),
        dibujarOpcionesJugador(2, i -> jugador2 = i, () -> jugador2),
        Espacio.A(64),
        new Boton("Iniciar juego", () -> continuar = true),
        Espacio.A(12),
        new Boton("Volver", () -> volver = true));
  }

  public Componente dibujarOpcionesJugador(
      int jugador, Consumer<Integer> establecer, Supplier<Integer> leer) {
    return new Flex(
        Flex.Vertical,
        Flex.Inicio,
        new Componente[] {
          Decorar.Color(Recursos.FOREGROUND, new Label("Jugador " + jugador, Recursos.FUENTE)),
          new Flex(
              Flex.Horizontal,
              Flex.Centro,
              IntStream.range(0, generadores.size())
                  .mapToObj(
                      i ->
                          new Componente[] {
                            Decorar.Fondo(
                                etiquetas.get(i) == etiquetas.get(leer.get())
                                    ? Recursos.COLOR_PRIMARIO
                                    : Recursos.BACKGROUND,
                                Decorar.Margen(
                                    4, 4, new Boton(etiquetas.get(i), () -> establecer.accept(i)))),
                            Espacio.A(8)
                          })
                  .flatMap(Arrays::stream)
                  .toArray(Componente[]::new))
        });
  }
}
