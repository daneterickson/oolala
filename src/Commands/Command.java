package Commands;

import Creatures.Creature;

import java.util.ArrayList;
import java.util.List;

public class Command {

    private List<Command> myCommandsList = new ArrayList<>() {{
            add(new CommandForward());
            add(new CommandBackward());
            add(new CommandLeftTurn());
            add(new CommandRightTurn());
            add(new CommandPenDown());
            add(new CommandPenUp());
            add(new CommandShowTurtle());
            add(new CommandHideTurtle());
            add(new CommandReset());
            add(new CommandStamp());
    }};
    private String myName;
    private int myNumArgs;

    public Command () {}

    public Command (String name) {
        myName = name.toLowerCase();
    }

    public String getName () { return myName; }
    public void setName (String name) { myName = name; }
    public int getNumArgs () { return myNumArgs; }
    public void setNumArgs (int num) { myNumArgs = num; }

    public Command recognize () {
        for (Command cd: myCommandsList)  {
            if (cd.getName().equals(myName)) {
                return cd;
            }
        }
        return null;
    }



    public void execute (Creature current, int val) {}



//     switch (commands[0]) {
//        case "fd" -> current.move(Integer.valueOf(commands[1]));
//        case "bk" -> current.move(-Integer.valueOf(commands[1]));
//        case "lt" -> current.changeOrientation(-Integer.valueOf(commands[1]));
//        case "rt" -> current.changeOrientation(Integer.valueOf(commands[1]));
//        case "pd" -> current.setPenActivity(true);
//        case "pu" -> current.setPenActivity(false);

//        case "st" -> current.setCreatureVisibility(true);
//        case "ht" -> current.setCreatureVisibility(false);
//        case "home" -> current.reset();
//        case "stamp" -> current.setStatusStamp(true);
//    }


}
