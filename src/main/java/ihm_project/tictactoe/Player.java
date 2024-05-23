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

    protected Player(Image shape, Effect color, String name) {
        this.shape = shape;
        this.color = color;
        this.name = name;
        this.bot = false;
    }

    protected void setBot(boolean isBot) {
        bot = isBot;
    }

    protected boolean isBot() {
        return bot;
    }

    protected Image getShape() {
        return shape;
    }

    protected Effect getColor() {
        return color;
    }

    protected String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }
}
