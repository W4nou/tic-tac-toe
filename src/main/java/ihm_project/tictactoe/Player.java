package ihm_project.tictactoe;

import javafx.scene.paint.Color;

public class Player {

    private final Color color;
    private final String shape;
    private String name;
    private boolean bot;

    public Player(Color color, String shape, String name) {
        this.color = color;
        this.shape = shape;
        this.name = name;
        this.bot = false;
    }

    public void setBot(boolean isbot){
        bot = isbot;
    }

    public boolean isBot() {
        return bot;
    }

    public Color getColor() {
        return color;
    }

    public String getShape() {
        return shape;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
