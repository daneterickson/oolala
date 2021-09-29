package oolala;

import Creatures.Creature;
import View.GameView;
import View.ScreenDisplay;
import View.TurtleView;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class Main extends Application {
    public static final String TITLE = "OOLALA";
    public static final Paint BACKGROUND = Color.THISTLE;
    private static int ORIGIN_X = 650;
    private static int ORIGIN_Y = 300;

    /**
     * Organize display of game in a scene and start the game.
     */
    @Override
    public void start (Stage stage) {
        Creature turtle = new Creature();
        TurtleView game = new TurtleView(turtle, ORIGIN_X, ORIGIN_Y);
        ScreenDisplay display = new ScreenDisplay(game);
        stage.setScene(display.setupDisplay(BACKGROUND));
        stage.setTitle(TITLE);
        stage.show();
    }
}
