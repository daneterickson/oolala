package oolala.creatures;

import oolala.games.DarwinGame;
import oolala.games.Game;

public class Creature {

    private boolean isPenActive, isCreatureVisible, isStamp;
    private double myOldPosX, myOldPoxY, myNewPosX, myNewPosY;
    private int myAngle;
    private String myType;
    private boolean isInfected;


    public Creature (int x, int y) {
        isPenActive = true;
        isStamp = false;
        isCreatureVisible = true;
        isInfected = false;
        setInitialPosition(x, y,0);
    }

    public void setInitialPosition (double x, double y, int angle) {
        myOldPosX = x;
        myNewPosX = x;
        myOldPoxY = y;
        myNewPosY = y;
        myAngle = angle;
    }

    public void setType (String type) { myType = type; }
    public String getType () { return myType; }

    public void setStatusInfection (boolean status) { isInfected = status; }
    public boolean getStatusInfection () { return isInfected; }

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

    public void reset (Game game) {
        updateOldPos();
        myNewPosX = game.getHomeX();
        myNewPosY = game.getHomeY();
        myAngle = 0;
    }

    private void updateOldPos () {
        myOldPosX = myNewPosX;
        myOldPoxY = myNewPosY;
    }

    public void move (double distance, Game game) {
        double newX = myNewPosX + distance * Math.sin(Math.toRadians(myAngle));
        double newY = myNewPosY - distance * Math.cos(Math.toRadians(myAngle));
        if (game instanceof DarwinGame) {
            DarwinGame DGame = (DarwinGame) game;
            if (newX < 0 || newX > DGame.getMaxX()
                    || newY < 0 || newY > DGame.getMaxY()){
                return;
            }
            else {
                for (Creature c: game.getCreaturesMap().values()) {
                    if ((!c.equals(this)) && newX == c.getNewX() && newY == c.getNewY()) {
                        return;
                    }
                }
            }
        }
        updateOldPos();
        myNewPosX = newX;
        myNewPosY = newY;
    }

    public void changeOrientation (double angle) {
        myAngle += angle;
    }

    public boolean isNearbyAhead (Creature c, Game game) {
        if (game instanceof DarwinGame) {
            DarwinGame DGame = (DarwinGame) game;
            if (Math.pow(c.getNewX() - myNewPosX, 2) + Math.pow(c.getNewY() - myNewPosY, 2)
                    < Math.pow(DGame.getRadius(), 2)) {
                double directX = Math.sin(Math.toRadians(myAngle));
                double directY = -Math.cos(Math.toRadians(myAngle));
                double creatureX = c.getNewX() - myNewPosX;
                double creatureY = c.getNewY() - myNewPosY;
                return (directX * creatureX + directY * creatureY > 0);
            }
        }
        return false;
    }

    public void infect (Creature c, Game game) {
        if (isNearbyAhead(c, game)) {
            c.setStatusInfection(true);
            c.setType(myType);
        }
    }

}