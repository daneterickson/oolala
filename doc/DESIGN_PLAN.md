# OOLALA Design Plan
## Name: Norah Tan (xt22), Dane Erickson (dte12), Evelyn Cupil-Garcia (ec220)


### Team Roles and Responsibilities
- Will be figured out on Meeting 9/23 @ 9pm EDT

### High Level Design
- Model folder
  - oolala.creatures Superclass --> turtles etc.
  - Game Superclass
    - Turtle: commands/creatures
    - Fractal: commands
    - Darwin: commands/creatures
- oolala.view folder
  - ScreenDisplay --> text box to run code and screen for drawing
  - CreatureView
    - TurtleView
    - FractalView
    - DarwinView
    
### Logo Programming IDE Card Classes

#### oolala.creatures Class
This class's purpose is to represent the oolala.creatures Superclass:

|oolala.creatures| |
|---|---|
|getXPosition         ||
|getYPosition      ||
| getXOrientation ||
| getYOrientation ||
| setOrientation(int angle) ||
| boolean isPenActive ||
| reset ||
| boolean isCreatureVisible ||
| boolean isStamped ||
| move(int distance) ||
| boolean isCreatureActive ||

#### Game Class
This class's purpose is to represent the Game Superclass:

|Game| |
|---|---|
|step()         | oolala.creatures |
|initializeCreature()      | oolala.creatures |
|interpretCommand(String command) | oolala.creatures |

#### ScreenDisplay Class
This class's purpose is to represent the ScreenDisplay Superclass:

|ScreenDisplay| |
|---|---|
| makeButton()        | |
| makeCommandBox()   | |
| makeScene() | |
| makeGraphicsWindow() | |
| handleKeyInput() | |
| actionReset() | |

#### CreatureView Class
This class's purpose is to represent the CreatureView Superclass:

|CreatureView| |
|---|---|
| drawCreature(int x, int y)        | oolala.creatures |
| drawLine(oolala.creatures creature)   |  oolala.creatures |
| updateCreature(oolala.creatures creature) | oolala.creatures |
