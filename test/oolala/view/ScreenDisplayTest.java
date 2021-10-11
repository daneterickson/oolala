package oolala.view;

import javafx.scene.control.Button;
import javafx.scene.control.TextInputControl;
import static org.junit.jupiter.api.Assertions.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class ScreenDisplayTest extends DukeApplicationTest {
    private static final String TITLE = "OOLALA";
    private static final Paint BACKGROUND = Color.THISTLE;
    private static int ORIGIN_X = 650;
    private static int ORIGIN_Y = 300;
    private TextInputControl myCommandBox;
    private Button myRunButton;
    private Button myTurtleButton;

    @Override
    public void start (Stage stage) {
        ScreenDisplay display = new ScreenDisplay("English", ORIGIN_X, ORIGIN_Y);
        stage.setScene(display.setupDisplay(BACKGROUND));
        stage.setTitle(TITLE);
        stage.setFullScreen(true);
        stage.show();
        myTurtleButton = lookup("#Turtle").query();
        clickOn(myTurtleButton);
    }

    @Test
    void commandBoxAction () {
        String expected = "fd 100";
        myCommandBox = lookup("#CommandBox").query();
        myRunButton =  (Button)lookup("#Run").query();
        clickOn(myCommandBox);
        writeInputTo(myCommandBox, expected);
        clickOn(myRunButton);
        assertEquals(expected, myCommandBox.getText());
    }
}
