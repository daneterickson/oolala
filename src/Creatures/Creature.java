package Creatures;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Creature {

    private final int WIDTH = 2;
    private final int HEIGHT = 1;
    private final Color COLOR_TURTLE = Color.GREEN;

    private boolean isPenActive, isCreatureVisible, isCreatureActive, isStamp;

    // can call myShape.getX(), getY(), getRotate(), myShape.isVisible()
//    private Rectangle myShape;

    private double myOldPosX, myOldPoxY, myNewPosX, myNewPosY;
    private int myAngle;


    public Creature () {
//        myShape = new Rectangle(0,0, WIDTH, HEIGHT);
//        myShape.setFill(COLOR_TURTLE);

        isPenActive = true;
//        isCreatureActive = false;
        isStamp = false;
        isCreatureVisible = true;

        myOldPosX = 0;
        myOldPoxY = 0;
        myNewPosX = 0;
        myNewPosY = 0;
        myAngle = 0;
    }

    public boolean getPenActivity () { return isPenActive; }
    public boolean getCreatureVisibility () { return isCreatureVisible; }
    public void setPenActivity (boolean status) { isPenActive = status; }
    public void setCreatureVisibility (boolean status) {
//        myShape.setVisible(status);
        isCreatureVisible = status;
    }
    public boolean getStatusStamp () { return isStamp; }
    public void setStatusStamp (boolean status) { isStamp = status; }
//    public Rectangle getShape () { return myShape; }

    public double getOldX () { return myOldPosX; }
    public double getOldY () { return myOldPoxY; }
    public double getNewX () { return myNewPosX; }
    public double getNewY () { return myNewPosY; }
    public int getAngle () { return myAngle; }

    public void reset () {
        updateOldPos();
        myNewPosX = 0;
        myNewPosY = 0;
        myAngle = 0;
//        myShape.setX(0);
//        myShape.setY(0);
//        myShape.setRotate(0);
    }

    private void updateOldPos () {
        myOldPosX = myNewPosX;
        myOldPoxY = myNewPosY;
    }

    public void move (int distance) {
        updateOldPos();
        myNewPosX = myOldPosX + distance * Math.cos(Math.toRadians(myAngle));
        myNewPosY = myOldPoxY + distance * Math.sin(Math.toRadians(myAngle));
//        myShape.setX(myShape.getX() + distance * Math.cos(Math.toRadians(myShape.getRotate())));
//        myShape.setY(myShape.getY() + distance * Math.sin(Math.toRadians(myShape.getRotate())));
    }

    public void changeOrientation (int angle) {
        // initial getRotate() = 0 -> positive x-axis
        // positive angle means clockwise
        myAngle += angle;
//        myShape.setRotate(angle + myShape.getRotate());
    }

}