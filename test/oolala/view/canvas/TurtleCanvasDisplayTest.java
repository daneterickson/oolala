package oolala.view.canvas;

import javafx.scene.control.Slider;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import oolala.games.TurtleGame;
import oolala.view.ScreenDisplay;
import oolala.view.game.TurtleView;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TurtleCanvasDisplayTest extends DukeApplicationTest {
    private static final String TITLE = "OOLALA";
    private static final Paint BACKGROUND = Color.THISTLE;
    private static final int ORIGIN_X = 650;
    private static final int ORIGIN_Y = 300;
    private TextInputControl myHomeLocationX;
    private TextInputControl myHomeLocationY;

    @Override
    public void start (Stage stage) {
        TurtleGame turtle = new TurtleGame(ORIGIN_X, ORIGIN_Y);
        TurtleView game = new TurtleView(turtle, ORIGIN_X, ORIGIN_Y);
        ScreenDisplay display = new ScreenDisplay(game, turtle, "English", ORIGIN_X, ORIGIN_Y);
        stage.setScene(display.setupDisplay(BACKGROUND));
        stage.setTitle(TITLE);
        stage.show();

        myHomeLocationX = lookup("#LocationX").query();
        myHomeLocationY =  lookup("#LocationY").query();
        myHomeLocationX.clear();
        myHomeLocationY.clear();
    }

    @Test
    void homeLocationXAction () {
        String expected = "200";
        clickOn(myHomeLocationX).write(expected).write(KeyCode.ENTER.getChar());
        assertEquals(expected, myHomeLocationX.getText());
    }

    @Test
    void homeLocationYAction () {
        String expected = "200";
        clickOn(myHomeLocationY).write(expected).write(KeyCode.ENTER.getChar());
        assertEquals(expected, myHomeLocationY.getText());

    }

    @Test
    void testSliderAction () {
        Slider slider = lookup("#PenSlider").query();
        Double expected = 2.0;
        setValue(slider, 2);
        assertEquals(expected, slider.getValue());
    }




}
