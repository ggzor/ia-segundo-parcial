import com.golden.gamedev.*;
import java.awt.*;

public class App extends Game {
  public void initResources() {
    // initialize game variables
  }

  public void update(long elapsedTime) {
    // update the game variables
  }

  public void render(Graphics2D g) {
    // render the game to the screen
  }

  public static void main(String[] args) throws Exception {
    GameLoader game = new GameLoader();
    game.setup(new App(), new Dimension(640, 480), false);
    game.start();
  }
}
