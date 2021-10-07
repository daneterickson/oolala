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

We refactored some JavaFX code that was styling to the Default.css file and fixed variables so that they were public static
final both in ScreenDisplay, Main, and ScreenDisplay.

## Model
We modified some data structures used in Model. 
Design Coach alerts to us that one method is too long and the structure is too nested (while, for, if statements).
We reread the code and refactored it using Iterator, instead of a while loop (with an index i), since it makes the code more concise and easy to understand.
We also added more tests to verify that the refactored codes still work properly. 