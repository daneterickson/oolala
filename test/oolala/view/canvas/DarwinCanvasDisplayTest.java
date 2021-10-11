package oolala.view.canvas;

import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextInputControl;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import oolala.games.DarwinGame;
import oolala.games.TurtleGame;
import oolala.view.ScreenDisplay;
import oolala.view.game.DarwinView;
import oolala.view.game.TurtleView;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DarwinCanvasDisplayTest extends DukeApplicationTest {
  private static final String TITLE = "OOLALA";
  private static final Paint BACKGROUND = Color.THISTLE;
  private static final int ORIGIN_X = 650;
  private static final int ORIGIN_Y = 300;
  private TextInputControl myTextField;
  private Button myRunButton;
  private DarwinGame darwinGame;
  private DarwinView darwinView;
  private ScreenDisplay display;
  private DarwinCanvasDisplay darwinCanvas;

  @Override
  public void start (Stage stage) {
    darwinGame = new DarwinGame();
    darwinView = new DarwinView(darwinGame, ORIGIN_X, ORIGIN_Y);
    display = new ScreenDisplay(darwinView, darwinGame, "English", ORIGIN_X, ORIGIN_Y);
    darwinCanvas = (DarwinCanvasDisplay) display.getMyCanvasDisplay();
    stage.setScene(display.setupDisplay(BACKGROUND));
    stage.setTitle(TITLE);
    stage.show();

    myTextField = lookup("#CommandBox").query();
    myTextField.clear();
    myRunButton = lookup("Run").query();
  }

  @Test
  void testPlayPauseButton () {
    Button playPause = lookup("#PlayPauseButton").query();
    clickOn(playPause);
    assertEquals(false, darwinCanvas.isPlaying);
    clickOn(playPause);
    assertEquals(true, darwinCanvas.isPlaying);
  }

  @Test
  void testSliderAction () {
    Slider slider = lookup("#SpeedSlider").query();
    Double expected = 2.0;
    setValue(slider, 2);
    assertEquals(expected, slider.getValue());
  }

}
