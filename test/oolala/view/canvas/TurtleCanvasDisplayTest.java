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
import oolala.view.game.TurtleView;
import org.junit.jupiter.api.Test;
import org.testfx.matcher.base.NodeMatchers;
import util.DukeApplicationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.testfx.api.FxAssert.verifyThat;

/**
 * Class that tests TurtleCanvasDisplay by testing functionality of Turtle
 *
 * @author: Evelyn Cupil-Garcia, Dane Erickson
 */
public class TurtleCanvasDisplayTest extends DukeApplicationTest {
    private static final String TITLE = "OOLALA";
    private static final Paint BACKGROUND = Color.THISTLE;
    private static final int ORIGIN_X = 650;
    private static final int ORIGIN_Y = 300;
    private TextInputControl myHomeLocationX;
    private TextInputControl myHomeLocationY;
    private TextInputControl myTextField;
    private Button myRunButton;
    private Button myClearButton;
    private TurtleGame turtleGame;
    private TurtleView turtleView;
    private ScreenDisplay display;

    @Override
    public void start (Stage stage) {
        display = new ScreenDisplay("English", ORIGIN_X, ORIGIN_Y);
        stage.setScene(display.setupDisplay(BACKGROUND));
        stage.setTitle(TITLE);
        stage.show();
        stage.setFullScreen(true);
        Button myTurtleButton = lookup("#Turtle").query();
        clickOn(myTurtleButton);
    }

    private void lookupButtons() {
        myHomeLocationX = lookup("#LocationX").query();
        myHomeLocationY =  lookup("#LocationY").query();
        myTextField = lookup("#CommandBox").query();
        myRunButton = lookup("#Run").query();
        myClearButton = lookup("#Clear").query();
        turtleGame = (TurtleGame) display.getGame();
        turtleView = (TurtleView) display.getGameView();
    }

    /**
     * Test that checks that writing to home x-value textbox is correct
     */
    @Test
    void homeLocationXAction () {
        lookupButtons();
        String expected = "200";
        clickOn(myHomeLocationX).write(expected).write(KeyCode.ENTER.getChar());
        assertEquals(expected, myHomeLocationX.getText());
    }

    /**
     * Test that checks that writing to home y-value textbox is correct
     */
    @Test
    void homeLocationYAction () {
        lookupButtons();
        String expected = "200";
        clickOn(myHomeLocationY).write(expected).write(KeyCode.ENTER.getChar());
        assertEquals(expected, myHomeLocationY.getText());

    }

    /**
     * Test that checks that the value that the user slided to is what we are grabbing from the slider
     */
    @Test
    void testSliderAction () {
        Slider slider = lookup("#PenSlider").query();
        Double expected = 2.0;
        setValue(slider, 2);
        assertEquals(expected, slider.getValue());
    }

    /**
     * Test that checks that the line width assigned to the turtle is the same as the one the user chose with slider
     */
    @Test
    void testLineWidthSlider () {
        lookupButtons();
        String command = "fd 100";
        double lineWidth = 4;
        clickOn(myTextField);
        writeInputTo(myTextField, command);
        Slider slider = lookup("#PenSlider").query();
        setValue(slider, lineWidth);
        clickOn(myRunButton);
        assertEquals(lineWidth, turtleView.getMyLineWidth());
    }

    /**
     * Test that checks when we change the Image of the creature, it does change.
     */
    @Test
    void testChangeCreature () {
        lookupButtons();
        String command = "fd 100\ntell 2\nbk 100";
        clickOn(lookup("#CatButton").query());
        clickOn(myTextField);
        writeInputTo(myTextField, command);
        clickOn(myRunButton);
        assertEquals("cat.png", turtleView.getCreatureImage());
    }

    /**
     * Test that checks the turtle's position after running commands is what is expected
     */
    @Test
    void testCurrentCreaturePosition () {
        lookupButtons();
        String command = "fd 100\nrt 90\nfd 100";
        clickOn(myTextField);
        writeInputTo(myTextField, command);
        clickOn(myRunButton);
        double x = ORIGIN_X + 100;
        double y = ORIGIN_Y - 100;
        TurtleCanvasDisplay turtleCanvas = (TurtleCanvasDisplay)display.getMyCanvasDisplay();
        clickOn(myTextField);
        assertEquals(String.valueOf(x), turtleCanvas.getTurtleStateXLabel().getText().split(" ")[1]);
        assertEquals(String.valueOf(y), turtleCanvas.getTurtleStateYLabel().getText().split(" ")[1]);
    }

    /**
     * Test that checks if invalid input is entered into commandBox, an alert shows
     */
    @Test
    void testInvalidCommand() {
        lookupButtons();
        String command = "fd 210\nff\nfd 300";
        clickOn(myTextField);
        writeInputTo(myTextField, command);
        clickOn(myRunButton);
        verifyThat("OK", NodeMatchers.isVisible());
    }

    /**
     * Test that checks that when we run clear, we initialize a new Turtle session
     */
    @Test
    void testClearing() {
        lookupButtons();
        String command = "fd 100\n bk 300";
        clickOn(myTextField);
        writeInputTo(myTextField, command);
        clickOn(myRunButton);
        clickOn(myClearButton);
        TurtleView newTurtleView = (TurtleView) display.getGameView();
        assertNotEquals(newTurtleView.getX(), turtleView.getX());
    }








}
