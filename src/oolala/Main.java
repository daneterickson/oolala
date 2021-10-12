package oolala;

import oolala.view.ScreenDisplay;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class Main extends Application {
    public static final String TITLE = "OOLALA";
    public static final Paint BACKGROUND = Color.THISTLE;
    public static final int ORIGIN_X = 650;
    public static final int ORIGIN_Y = 300;
    public static final String LANGUAGE = "Spanish";


    /**
     * Organize display of game in a scene and start the game.
     */
    @Override
    public void start (Stage stage) {
        ScreenDisplay display = new ScreenDisplay(LANGUAGE, ORIGIN_X, ORIGIN_Y);
        stage.setScene(display.setupDisplay(BACKGROUND));
        stage.setTitle(TITLE);
        stage.show();

    }
}
