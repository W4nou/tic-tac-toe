package ihm_project.tictactoe;

import javafx.scene.effect.Effect;
import javafx.scene.image.Image;

public class Player {
    public static final String P1_NAME_DEFAULT = "Joueur 1";
    public static final String P2_NAME_DEFAULT = "Joueur 2";
    public static final String BOT_NAME_DEFAULT = "Ordinateur";

    private final Image shape;
    private final Effect color;
    private String name;
    private boolean bot;

    public Player(Image shape, Effect color, String name) {
        this.shape = shape;
        this.color = color;
        this.name = name;
        this.bot = false;
    }

    public void setBot(boolean isBot) {
        bot = isBot;
    }

    public boolean isBot() {
        return bot;
    }

    public Image getShape() {
        return shape;
    }

    public Effect getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
