package oolala.view;

import javafx.scene.control.Button;
import javafx.scene.control.TextInputControl;
import javafx.stage.Stage;
import oolala.Main;
import oolala.games.FractalGame;
import oolala.view.game.FractalView;
import util.DukeApplicationTest;

public class FractalViewTest extends DukeApplicationTest {

  public static final int originX = Main.ORIGIN_X;
  public static final int originY = Main.ORIGIN_Y;

  private TextInputControl myTextField;
  private Button myRunButton;

  private FractalGame myFractalGame;
  private FractalView myFractalView;
  private ScreenDisplay myDisplay;

  @Override
  public void start (Stage stage) {
    myFractalGame = new FractalGame();
    myFractalView = new FractalView(myFractalGame);
    myDisplay = new ScreenDisplay(myFractalView, myFractalGame, "English", originX, originY);
    stage.setScene(myDisplay.setupDisplay(Main.BACKGROUND));
    stage.setTitle(Main.TITLE);
    stage.show();

    myTextField = lookup("#CommandBox").query();
    myTextField.clear();
    myRunButton = lookup("Run").query();
  }

}
