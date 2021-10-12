package oolala.commands;

import oolala.creatures.Creature;
import oolala.games.DarwinGame;
import oolala.games.Game;

/**
 * CommandIfWall class that creates executable commands for "ifwall"
 *
 * @author Norah Tan
 */
public class CommandIfWall extends Command {
    /**
     * Constructor that sets the name and sets the number of arguments it takes in for execution
     */
    public CommandIfWall () {
        setName("ifwall");
        setNumArgs(2);
    }

    @Override
    public boolean execute (Creature current, Game game) {
        if (game instanceof DarwinGame) {
            DarwinGame DGame = (DarwinGame) game;
            double directX = Math.sin(Math.toRadians(current.getAngle()));
            double directY = -Math.cos(Math.toRadians(current.getAngle()));
            if (current.getNewX() <= DGame.getRadius()) {
                if (directX <= 0) return true;
            }
            else if ( current.getNewX() + DGame.getRadius() >= DGame.getMaxX() ){
                if (directX >= 0) return true;
            }
            else if ( current.getNewY() <= DGame.getRadius() ) {
                if (directY <= 0) return true;
            }
            else if (current.getNewY() + DGame.getRadius() >= DGame.getMaxY()) {
                if (directY >= 0) return true;
            }
        }
        return false;
    }
}
