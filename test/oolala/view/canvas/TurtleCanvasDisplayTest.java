package oolala.view.canvas;

import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import oolala.games.TurtleGame;
import oolala.view.ScreenDisplay;
import oolala.view.ScreenDisplayComponents;
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
    private TextInputControl myTextField;
    private Button myRunButton;
    private TurtleGame turtleGame;
    private TurtleView turtleView;
    private ScreenDisplay display;
    private TurtleCanvasDisplay myCanvas;

    @Override
    public void start (Stage stage) {
        turtleGame = new TurtleGame(ORIGIN_X, ORIGIN_Y);
        turtleView = new TurtleView(turtleGame, ORIGIN_X, ORIGIN_Y);
        ScreenDisplayComponents components = new ScreenDisplayComponents("English");
        myCanvas = new TurtleCanvasDisplay(turtleView, turtleGame, components);
        display = new ScreenDisplay(turtleView, turtleGame, "English", ORIGIN_X, ORIGIN_Y);
        stage.setScene(display.setupDisplay(BACKGROUND));
        stage.setTitle(TITLE);
        stage.show();

        myHomeLocationX = lookup("#LocationX").query();
        myHomeLocationY =  lookup("#LocationY").query();
        myHomeLocationX.clear();
        myHomeLocationY.clear();
        myTextField = lookup("#CommandBox").query();
        myTextField.clear();
        myRunButton = lookup("Run").query();
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

    @Test
    void testLineWidthSlider () {
        String command = "fd 100";
        double lineWidth = 4;
        clickOn(myTextField);
        writeInputTo(myTextField, command);
        Slider slider = lookup("#PenSlider").query();
        setValue(slider, lineWidth);
        clickOn(myRunButton);
        assertEquals(lineWidth, turtleView.getMyLineWidth());
    }

    @Test
    void testChangeCreature () {
        String command = "fd 100\ntell 2\nbk 100";
        clickOn(lookup("#CatButton").query());
        clickOn(myTextField);
        writeInputTo(myTextField, command);
        clickOn(myRunButton);
        assertEquals("cat.png", turtleView.getTurtleImage());
    }
    




}
