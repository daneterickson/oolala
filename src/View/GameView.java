package View;

import javafx.scene.image.ImageView;

public abstract class GameView {

    protected abstract void drawCreature(double x, double y, double width, double height);

    public abstract void drawLine();

    public abstract void updateCreature();


}


