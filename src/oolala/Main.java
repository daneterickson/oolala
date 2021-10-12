package oolala;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import oolala.games.DarwinGame;
import oolala.games.FractalGame;
import oolala.games.TurtleGame;
import oolala.view.ScreenDisplay;
import oolala.view.game.DarwinView;
import oolala.view.game.FractalView;
import oolala.view.game.TurtleView;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class Main extends Application {
    public static final String TITLE = "OOLALA";
    public static final Paint BACKGROUND = Color.THISTLE;
    public static final int ORIGIN_X = 650;
    public static final int ORIGIN_Y = 300;


    /**
     * Organize display of game in a scene and start the game.
     */
    @Override
    public void start (Stage stage) {
        TurtleGame turtleGame = new TurtleGame(ORIGIN_X, ORIGIN_Y);
        TurtleView turtleView = new TurtleView(turtleGame, ORIGIN_X, ORIGIN_Y);
        FractalGame fractalGame = new FractalGame();
        FractalView fractalView = new FractalView(fractalGame);
        DarwinGame darwinGame = new DarwinGame();
        DarwinView darwinView = new DarwinView(darwinGame, ORIGIN_X, ORIGIN_Y);
        ScreenDisplay display = new ScreenDisplay(turtleView, turtleGame, "English", ORIGIN_X, ORIGIN_Y);
        stage.setScene(display.setupDisplay(BACKGROUND));
        stage.setTitle(TITLE);
        stage.show();

    }
}
