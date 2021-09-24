# OOLALA Design Plan
## Name: Norah Tan (xt22), Dane Erickson (dte12), Evelyn Cupil-Garcia (ec220)


### Team Roles and Responsibilities
09/23/21
- Evelyn Cupil-Garcia: ScreenDisplay (Frontend)
- Norah Tan: Creature Model/Game Superclass (Backend)
- Dane Erickson: Creature View (Frontend)

### High Level Design
- Model folder
  - Creature Superclass --> turtles etc.
  - Game Superclass
    - Turtle: commands/creatures
    - Fractal: commands
    - Darwin: commands/creatures
- View folder
  - ScreenDisplay --> text box to run code and screen for drawing
  - CreatureView
    - TurtleView
    - FractalView
    - DarwinView
    
### Logo Programming IDE Card Classes

#### Creature Class
This class's purpose is to represent the Creature Superclass:

|Creature| |
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
|step()         | Creature |
|initializeCreature()      | Creature |
|interpretCommand(String command) | Creature |

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
| drawCreature(int x, int y)        | Creature |
| drawLine(Creature creature)   |  Creature |
| updateCreature(Creature creature) | Creature |
