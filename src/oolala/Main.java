package oolala;

import Creatures.Creature;
import Games.TurtleGame;
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
    public static int ORIGIN_X = 650;
    public static int ORIGIN_Y = 300;

    /**
     * Organize display of game in a scene and start the game.
     */
    @Override
    public void start (Stage stage) {
        TurtleGame turtle = new TurtleGame(ORIGIN_X, ORIGIN_Y);
        TurtleView game = new TurtleView(turtle, ORIGIN_X, ORIGIN_Y);
        ScreenDisplay display = new ScreenDisplay(game, turtle, "English", ORIGIN_X, ORIGIN_Y);
        stage.setScene(display.setupDisplay(BACKGROUND));
        stage.setTitle(TITLE);
        stage.show();
    }
}
