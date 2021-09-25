package Games;

import Creatures.Creature;

import java.util.List;

public class Game {

//    private List<Creature> myListCreatures;
    private Creature myCurrent;

    public Game () {
        myCurrent = new Creature();
    }

    public void step (String command) {
        String[] commands = command.split(" ");
        switch (commands[0]) {
            case "fd" -> myCurrent.move(Integer.valueOf(commands[1]));
            case "bk" -> myCurrent.move(-Integer.valueOf(commands[1]));
            case "lt" -> myCurrent.changeOrientation(-Integer.valueOf(commands[1]));
            case "rt" -> myCurrent.changeOrientation(Integer.valueOf(commands[1]));
            case "pd" -> myCurrent.setPenActivity(true);
            case "pu" -> myCurrent.setPenActivity(false);
            case "st" -> myCurrent.setCreatureVisibility(true);
            case "ht" -> myCurrent.setCreatureVisibility(false);
            case "home" -> myCurrent.reset();
//            case "stamp" -> //stamp
//            case "tell" ->
        }
    }
}
