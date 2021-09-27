package oolala;

import View.GameView;
import View.ScreenDisplay;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class Main extends Application {
    public static final String TITLE = "OOLALA";
    public static final Paint BACKGROUND = Color.THISTLE;

    /**
     * Organize display of game in a scene and start the game.
     */
    @Override
    public void start (Stage stage) {
        GameView view = new GameView();
        stage.setScene(view.setupGame(BACKGROUND));
        stage.setTitle(TITLE);
        stage.show();
    }
}
