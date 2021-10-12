package oolala.view;

import javafx.scene.control.Button;
import javafx.scene.control.TextInputControl;
import static org.junit.jupiter.api.Assertions.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import oolala.games.TurtleGame;
import oolala.view.game.TurtleView;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

/**
 * ScreenDisplayTest: Has Unit Testing for components that are across game such as CommandBox, Run, and Clear
 *
 * @author: Evelyn Cupil-Garcia
 */
public class ScreenDisplayTest extends DukeApplicationTest {
    private static final String TITLE = "OOLALA";
    private static final Paint BACKGROUND = Color.THISTLE;
    private static int ORIGIN_X = 650;
    private static int ORIGIN_Y = 300;
    private TextInputControl myCommandBox;
    private Button myRunButton;
    private Button myTurtleButton;
    private ScreenDisplay display;

    @Override
    public void start (Stage stage) {
        display = new ScreenDisplay("English", ORIGIN_X, ORIGIN_Y);
        stage.setScene(display.setupDisplay(BACKGROUND));
        stage.setTitle(TITLE);
        stage.setFullScreen(true);
        stage.show();
        myTurtleButton = lookup("#Turtle").query();
        clickOn(myTurtleButton);
    }


    private void lookupButtons() {
        myCommandBox = lookup("#CommandBox").query();
        myRunButton =  (Button)lookup("#Run").query();
    }

    /**
     * Test to check that commandBox values are the same as input
     */
    @Test
    void commandBoxAction () {
        lookupButtons();
        String expected = "fd 100";
        clickOn(myCommandBox);
        writeInputTo(myCommandBox, expected);
        clickOn(myRunButton);
        assertEquals(expected, myCommandBox.getText());
    }

    /**
     * Test that checks that when you hit clear, a new default game is initialized
     */
    @Test
    void clearAction() {
        lookupButtons();
        String command = "fd 100\nbk 201";
        clickOn(myCommandBox);
        writeInputTo(myCommandBox, command);
        clickOn(myRunButton);
        TurtleView newTurtle = new TurtleView((TurtleGame) display.getGame(), ORIGIN_X, ORIGIN_Y);
        TurtleView oldTurtle = (TurtleView) display.getGameView();
        assertNotEquals(oldTurtle.getY(), newTurtle.getY());
    }
}
