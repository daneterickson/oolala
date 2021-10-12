package oolala.view.canvas;

import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextInputControl;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import oolala.games.DarwinGame;
import oolala.games.FractalGame;
import oolala.games.TurtleGame;
import oolala.view.ScreenDisplay;
import oolala.view.game.DarwinView;
import oolala.view.game.FractalView;
import oolala.view.game.TurtleView;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FractalCanvasDisplayTest extends DukeApplicationTest {
  private static final String TITLE = "OOLALA";
  private static final Paint BACKGROUND = Color.THISTLE;
  private static final int ORIGIN_X = 650;
  private static final int ORIGIN_Y = 300;
  private TextInputControl myTextField;
  private TextInputControl myAngleField;
  private TextInputControl myLengthField;
  private TextInputControl myLevelField;
  private TextInputControl myHomeLocationX;
  private TextInputControl myHomeLocationY;
  private Button myRenderButton;
  private Button myRunButton;
  private FractalGame fractalGame;
  private FractalView fractalView;
  private ScreenDisplay display;
  private FractalCanvasDisplay fractalCanvas;

  @Override
  public void start (Stage stage) {
    fractalGame = new FractalGame();
    fractalView = new FractalView(fractalGame);
    display = new ScreenDisplay("English", ORIGIN_X, ORIGIN_Y);
    fractalCanvas = (FractalCanvasDisplay) display.getMyCanvasDisplay();
    stage.setScene(display.setupDisplay(BACKGROUND));
    stage.setTitle(TITLE);
    stage.show();

    myTextField = lookup("#CommandBox").query();
    myTextField.clear();
    myAngleField = lookup("#AngleBox").query();
    myAngleField.clear();
    myLengthField = lookup("#LengthBox").query();
    myLengthField.clear();
    myLevelField = lookup("#LevelBox").query();
    myLevelField.clear();
    myHomeLocationX = lookup("#LocationX").query();
    myHomeLocationX.clear();
    myHomeLocationY =  lookup("#LocationY").query();
    myHomeLocationY.clear();
    myRunButton = lookup("Run").query();
    myRenderButton = lookup("#SetButton").query();
  }

  @Test
  void testMakeLeaves () {
    setUp(30, 100, 3, 650, 100);
    String command = "start F\nrule F F+F-F-F+F";
    clickOn(myTextField).write(command);
    clickOn(myRunButton);
    assertEquals(3, fractalView.getMyCreatureMap().size());
  }

  @Test
  void testChangeLeaf () {
    setUp(30, 100, 3, 650, 100);
    clickOn(lookup("#CatButton").query());
    String command = "start F\nrule F F+F-F-F+F";
    clickOn(myTextField).write(command);
    clickOn(myRunButton);
    assertEquals("cat.png", fractalView.getCreatureImage());
  }

  private void setUp (int angle, int length, int levels, int x, int y) {
    clickOn(myAngleField).write(String.valueOf(angle));
    clickOn(myLengthField).write(String.valueOf(length));
    clickOn(myLevelField).write(String.valueOf(levels));
    clickOn(myHomeLocationX).write(String.valueOf(x));
    clickOn(myHomeLocationY).write(String.valueOf(y));
    clickOn(myRenderButton);
  }

}
