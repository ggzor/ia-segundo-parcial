package ia.segundoparcial;

import com.golden.gamedev.util.ImageUtil;
import java.awt.*;
import java.awt.image.*;
import java.io.InputStream;

// Los recursos utilizados por el juego
public class Recursos {
  public static final BufferedImage SPLASH_SCREEN =
      ImageUtil.getImage(Recursos.class.getResource("/splash_screen.png"));

  public static InputStream ARCHIVO_FUENTE =
      Recursos.class.getResourceAsStream("/ComingSoon-Regular.ttf");
  public static Font FUENTE = new Font("Tahoma", Font.PLAIN, 12);

  public static Color BACKGROUND = new Color(0xFFFFFF);
  public static Color FOREGROUND = new Color(0x000000);

  public static final Color COLOR_JUGADOR1 = new Color(0xB23A48);
  public static final Color COLOR_JUGADOR2 = new Color(0xFF9505);
  public static final Color COLOR_VACIO = new Color(0xE0E0E0);

  public static final Color COLOR_PRIMARIO = new Color(0x016FB9);
  public static final Color COLOR_COMPLEMENTARIO = new Color(0x320D6D);

  public static void inicializar() {
    // Intentar cambiar la fuente a una más acorde al juego
    try {
      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      Font fuente = Font.createFont(Font.TRUETYPE_FONT, Recursos.ARCHIVO_FUENTE);
      ge.registerFont(fuente);
      Recursos.FUENTE = new Font("Coming Soon Regular", Font.PLAIN, 18);
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println("No se pudo cargar la fuente del juego.");
    }
  }

  private Recursos() {}
}
