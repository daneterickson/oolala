package View;

import javafx.scene.Scene;
import javafx.scene.paint.Paint;

public class GameView {
    private ScreenDisplay display;

    public GameView() {
        display = new ScreenDisplay();
    }

    public Scene setupGame (Paint background) {
        return display.setupDisplay(background);
    }
}
