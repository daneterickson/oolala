package View;

import Games.TurtleGame;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextInputControl;
import static org.junit.jupiter.api.Assertions.*;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.util.ResourceBundle;

public class ScreenDisplayTest extends DukeApplicationTest {
    private ScreenDisplay myScreenDisplay;
    private ScreenDisplayComponents myScreenDisplayComponents;
    private ResourceBundle myResources;
    private static final String TITLE = "OOLALA";
    private static final Paint BACKGROUND = Color.THISTLE;
    private static int ORIGIN_X = 650;
    private static int ORIGIN_Y = 300;
    private TextInputControl myCommandBox;
    private Button myRunButton;




    @Override
    public void start (Stage stage) {
        TurtleGame turtle = new TurtleGame(ORIGIN_X, ORIGIN_Y);
        TurtleView game = new TurtleView(turtle, ORIGIN_X, ORIGIN_Y);
        ScreenDisplay display = new ScreenDisplay(game, turtle, "English", ORIGIN_X, ORIGIN_Y);
        stage.setScene(display.setupDisplay(BACKGROUND));
        stage.setTitle(TITLE);
        stage.show();

        myCommandBox = lookup("#Pane #CommandBoxPanel #CommandBox").query();
        myRunButton =  (Button)lookup("#Pane #CommandBoxPanel #CommandBoxButtonPanel #Run").query();
        myCommandBox.clear();
    }

    @Test
    void commandBoxAction() {
        String expected = "fd 100";
        clickOn(myCommandBox).write(expected).clickOn(myRunButton);
        assertLabelText(expected);
    }

    private void assertLabelText (String expected) {
        assertEquals(expected, myCommandBox.getText());
    }


}
