## Introduction
### Context Reminder

This project was carried out as part of an evaluated practical work for the course R2.02: Introduction to Human-Machine Interfaces (HMI). The application was carefully developed over a period of several weeks. Each stage of the design was thought out and executed rigorously to meet the course requirements and to ensure an optimal user experience. This work allowed us to put into practice the theories learned in class and to become familiar with the challenges and opportunities of HMI development.

## Application Structure

The application was coded in Java, adopting an object-oriented approach, to ensure a modular and maintainable code structure. For the display part, I used JavaFX, the tool that is imposed and taught to us as part of the course. JavaFX is a powerful framework allowing the creation of rich and interactive user interfaces. This combination of technologies was chosen to take advantage of the benefits of object-oriented programming and the advanced graphical capabilities of JavaFX, thus ensuring a robust and visually appealing application. By working alone on this project, I was able to deepen my skills in software development and interface design, while independently addressing the challenges encountered.

### Files

The project is separated into two main folders:

- java
- resources

Inside the java folder, we find the package `ihm_project.tictactoe` which contains the source code of the entire application and the file `module-info.java` which defines the package dependencies.
Inside the resources folder, we find a `ihm_project.tictactoe` folder corresponding to the Java package associated with the application. Inside it, we find the various FXML files that define the display of the windows. We also find a shapes folder that contains all the shapes selectable by the user when creating the game in .png format to manage transparency and in white color to be modified via an effect within the application.

We also find the Styles folder containing a CSS stylesheet that allows modifying the display parameters of the buttons inside the grid used for the tic-tac-toe game itself.

### Classes

This application uses a total of 8 different classes:

4 simple controller classes that manage the display and interactions in the corresponding windows, indicated by their respective names:

- ConfigurationController
- EndGameController
- GameController
- NewGameController

2 classes dedicated to processing post-display logic:

- Game
- Player

1 general controller class that supervises all other controllers and contains many static methods allowing access to a controller from another. This class also manages the display shared with several windows, mainly the menu bar:

- TicTacToeController

1 application class responsible for creating and displaying the windows:

- TicTacToeApplication

These classes work together to ensure efficient management of the display, user interactions, and application logic.

### Issues Encountered

At the time of writing this report, two known issues persist:

An encoding issue with the display that forced me to use UTF codes to use special/accented characters in the "Labels". This issue only affects code reading.

A minor and display-only issue that does not update the preview "ImageView" of the shape chosen by the player in real-time, despite various attempts with different approaches. The most conclusive result (current implementation) requires hovering the mouse over the "ColorPicker" to update the preview.

### Decisions

I decided to name all my files, variables, methods, and classes in English, as well as the comments, to maintain consistency with Java and JavaFX naming conventions. I believe this increases code readability. I also decided to use inheritance from the TicTacToeController class to limit code duplication and improve project maintainability.

I used SceneBuilder to generate all the application's windows. However, the game grid was generated procedurally because it depends on the configuration parameters entered by the user.

I used the protected scope for all functions and methods used within the application, and private for those used only within the same class, including attributes. The public scope was used only for constants to simplify application configuration and facilitate modification of default values.
