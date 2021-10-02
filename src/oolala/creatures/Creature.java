package oolala.creatures;

public class Creature {

    private boolean isPenActive, isCreatureVisible, isStamp;
    private double myOldPosX, myOldPoxY, myNewPosX, myNewPosY;
    private int myAngle;
    private double myHomeX, myHomeY;


    public Creature (int x, int y) {
        myHomeX = x;
        myHomeY = y;
        isPenActive = true;
        isStamp = false;
        isCreatureVisible = true;
        setInitialPosition(x, y,0);
    }

    public void setInitialPosition (double x, double y, int angle) {
        myOldPosX = x;
        myNewPosX = x;
        myOldPoxY = y;
        myNewPosY = y;
        myAngle = angle;
    }

    public boolean getPenActivity () { return isPenActive; }
    public void setPenActivity (boolean status) {
        isPenActive = status;
        updateOldPos();
    }
    public boolean getCreatureVisibility () { return isCreatureVisible; }
    public void setCreatureVisibility (boolean status) {
        isCreatureVisible = status;
        updateOldPos();
    }
    public boolean getStatusStamp () { return isStamp; }
    public void setStatusStamp (boolean status) {
        isStamp = status;
        updateOldPos();
    }

    public double getOldX () { return myOldPosX; }
    public double getOldY () { return myOldPoxY; }
    public double getNewX () { return myNewPosX; }
    public double getNewY () { return myNewPosY; }
    public int getAngle () { return myAngle; }

    public void reset () {
        updateOldPos();
        myNewPosX = myHomeX;
        myNewPosY = myHomeY;
        myAngle = 0;
    }

    private void updateOldPos () {
        myOldPosX = myNewPosX;
        myOldPoxY = myNewPosY;
    }

    public void move (int distance) {
        updateOldPos();
        myNewPosX = myOldPosX + distance * Math.sin(Math.toRadians(myAngle));
        myNewPosY = myOldPoxY - distance * Math.cos(Math.toRadians(myAngle));
    }

    public void changeOrientation (int angle) {
        myAngle += angle;
    }

}