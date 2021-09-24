package Creatures;

public class Creature {

    private int myPosX, myPosY;
    private double myDirectX, myDirectY;
    private boolean isPenActive, isCreatureVisible, isCreatureActive;

    public Creature () {
        reset();
        isPenActive = true;
//        isCreatureActive = false;
        isCreatureVisible = true;
    }

    public boolean getStatusIsPenActive () { return isPenActive; }
    public boolean getStatusIsCreatureVisible () { return isCreatureVisible; }
    public void setPenActivity (boolean status) { isPenActive = status; }
    public void setCreatureVisibility (boolean status) { isCreatureVisible = status; }
    public int getPoX () { return myPosX; }
    public int getPosY () { return myPosY; }

    public void reset () {
        // initialize position to (0, 0) and direction to (0, 1)
        myPosX = 0;
        myPosY = 0;
        myDirectX = 0;
        myDirectY = 1;
    }



    public void move (int distance) {
        myPosX += distance * myDirectX;
        myPosY += distance * myDirectY;
    }

    public void changeOrientation (int angle) {
        // positive angle means clockwise
        // https://en.wikipedia.org/wiki/Rotation_matrix
        // rotationMatrix = [cos(x), -sin(x); sin(x), cos(x)];
        // oldDirect = [directX, directY];
        // newDirect = rotationMatrix * oldDirect;
        double newDirectX = myDirectX * Math.cos(angle) - myDirectY * Math.sin(angle);
        double newDirectY = myDirectX * Math.sin(angle) + myDirectY * Math.cos(angle);
        myDirectX = newDirectX;
        myDirectY = newDirectY;
    }

}