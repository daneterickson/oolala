package Creatures;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Creature {

    private final int WIDTH = 2;
    private final int HEIGHT = 1;
    private final Color COLOR_TURTLE = Color.GREEN;

    private boolean isPenActive, isCreatureVisible, isCreatureActive, isStamp;

    // can call myShape.getX(), getY(), getRotate(), myShape.isVisible()
    private Rectangle myShape;


    public Creature () {
        myShape = new Rectangle(0,0, WIDTH, HEIGHT);
        myShape.setFill(COLOR_TURTLE);
        isPenActive = true;
//        isCreatureActive = false;
//        isStamp = false;
        isCreatureVisible = true;
    }

    public boolean getPenActivity () { return isPenActive; }
    public boolean getCreatureVisibility () { return isCreatureVisible; }
    public void setPenActivity (boolean status) { isPenActive = status; }
    public void setCreatureVisibility (boolean status) {
        myShape.setVisible(status);
        isCreatureVisible = status;
    }

    public void reset () {
        myShape.setX(0);
        myShape.setY(0);
        myShape.setRotate(0);
    }

    public void move (int distance) {
        myShape.setTranslateX(distance * Math.cos(myShape.getRotate()));
        myShape.setTranslateY(distance * Math.sin(myShape.getRotate()));
    }

    public void changeOrientation (int angle) {
        myShape.setRotate(angle + myShape.getRotate());
    }

}