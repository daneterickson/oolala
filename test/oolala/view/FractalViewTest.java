package oolala.view;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputControl;
import javafx.stage.Stage;
import oolala.Main;
import oolala.games.FractalGame;
import oolala.games.TurtleGame;
import oolala.view.game.FractalView;
import oolala.view.game.TurtleView;
import util.DukeApplicationTest;

public class FractalViewTest extends DukeApplicationTest {

  public static final int originX = Main.ORIGIN_X;
  public static final int originY = Main.ORIGIN_Y;

  private TextInputControl myTextField;
  private Button myRunButton;

  private FractalGame myFractalGame;
  private FractalView myFractalView;
  private ScreenDisplay myDisplay;
  private Node myFractalButton;

  @Override
  public void start (Stage stage) {
    myFractalGame = new FractalGame();
    myFractalView = new FractalView(myFractalGame);
    myDisplay = new ScreenDisplay("English", originX, originY);
    stage.setScene(myDisplay.setupDisplay(Main.BACKGROUND));
    stage.setTitle(Main.TITLE);
    stage.show();

    myFractalButton = lookup("#Fractal").query();
    clickOn(myFractalButton);
  }

  private void getButtons() {
    myTextField = lookup("#CommandBox").query();
    myRunButton = lookup("Run").query();
    myFractalGame = (FractalGame) myDisplay.getGame();
    myFractalView = (FractalView) myDisplay.getGameView();
  }



}
