## Names
Dane Erickson (dte12)
Norah Tan (xt22)
Evelyn Cupil-Garcia (ec220)

## View
We decided to **refactor the ScreenDisplay** class to create a separate class that controls the canvas on the screen, 
which is the actual window where the creature and lines are drawn. The new CanvasDisplay class used 
inheritance to extend a TurtleCanvasDisplay, FractalCanvasDisplay, and DarwinCanvasDisplay class that
create the canvases for each application. This inheritance made it so the ScreenDisplay only has one responsibility, 
which is to create the buttons and window, and the CanvasDisplay only has one responsibility, which is
to create the canvas for the creature and lines to be drawn on and move around on. 

We refactored some JavaFX code that was styling to the Resources CSS file and fixed variables so that they were public static
final.
## Model

