package ihm_project.tictactoe;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class ConfigurationController {
    public static final String SHAPES_LOCATION_DEFAULT = "/ihm_project/tictactoe/shapes";
    public static final String P1_SHAPE_DEFAULT = "emptyCircle.png";
    public static final String P2_SHAPE_DEFAULT = "fullCross.png";
    public static final Color P2_COLOR_DEFAULT = Color.RED;
    public static final Color P1_COLOR_DEFAULT = Color.BLUE;

    private Player p1;
    private Player p2;

    // not my original approach for this since I had a big trouble with getResource and the JAR compilation
    private List<String> folderContent() {
        try {
            Path path = Paths.get(Objects.requireNonNull(getClass().getResource(SHAPES_LOCATION_DEFAULT)).toURI());
            try (Stream<Path> paths = Files.walk(path)) {
                return paths.filter(Files::isRegularFile).map(Path::getFileName).map(Path::toString).toList();
            }
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private Blend colorToBlend(Color color) {
        ColorInput colorInput = new ColorInput(0, 0, Double.MAX_VALUE, Double.MAX_VALUE, color);
        return new Blend(BlendMode.SRC_ATOP, null, colorInput);
    }

    private void updateShape(String shape, ImageView imageView) {
        InputStream stream = getClass().getResourceAsStream(SHAPES_LOCATION_DEFAULT + "/" + shape);
        assert stream != null;
        Image image = new Image(stream);
        imageView.setImage(image);
    }

    private void updateShapeColor(ImageView imageView, Blend effect) {
        imageView.setEffect(effect);
    }

    private Player initializePlayer(ComboBox<String> shapeComboBox, ImageView imageView, ColorPicker colorPicker, TextField pseudoTextField) {
        updateShape(shapeComboBox.getValue(), imageView);
        updateShapeColor(imageView, colorToBlend(colorPicker.getValue()));

        shapeComboBox.setOnAction(event -> updateShape(shapeComboBox.getValue(), imageView));
        colorPicker.setOnAction(event -> updateShapeColor(imageView, colorToBlend(colorPicker.getValue())));

        return new Player(imageView.getImage(), imageView.getEffect(), pseudoTextField.getText());
    }

    protected void reloadPseudo(String newName, TextField textfield) {
        if (Objects.equals(textfield.getId(), "p1TextField")) {
            p1PseudoTextField.setText(newName);
            p1.setName(newName);
        } else {
            p2PseudoTextField.setText(newName);
            p2.setName(newName);
        }
    }

    protected void resetConfiguration() {
        p1ShapeComboBox.setValue(P1_SHAPE_DEFAULT);
        p2ShapeComboBox.setValue(P2_SHAPE_DEFAULT);

        p1ColorPicker.setValue(P1_COLOR_DEFAULT);
        p2ColorPicker.setValue(P2_COLOR_DEFAULT);

        updateShape(P1_SHAPE_DEFAULT,p1ImageView);
        updateShape(P2_SHAPE_DEFAULT,p2ImageView);

        boardSizeSlider.setValue(Game.BOARD_SIZE_DEFAULT);

        randomFirstPlayerCheckBox.setSelected(false);
    }

    protected boolean getIsRandomFirstPlayer() {
        return randomFirstPlayerCheckBox.isSelected();
    }

    protected Player getP1() {
        return p1;
    }

    protected Player getP2() {
        return p2;
    }

    protected int getBoardSize() {
        return (int) boardSizeSlider.getValue();
    }


    @FXML
    private TextField p2PseudoTextField;

    @FXML
    private ColorPicker p1ColorPicker;

    @FXML
    private ComboBox<String> p1ShapeComboBox;

    @FXML
    private TextField p1PseudoTextField;

    @FXML
    private ColorPicker p2ColorPicker;

    @FXML
    private ComboBox<String> p2ShapeComboBox;

    @FXML
    private Slider boardSizeSlider;

    @FXML
    private ImageView p1ImageView;

    @FXML
    private ImageView p2ImageView;

    @FXML
    private CheckBox randomFirstPlayerCheckBox;

    @FXML
    private void initialize() {
        List<String> shapes = folderContent();
        for (String shape : shapes) {
            p1ShapeComboBox.getItems().add(shape);
            p2ShapeComboBox.getItems().add(shape);
        }
        p1ShapeComboBox.setValue(P1_SHAPE_DEFAULT);
        p2ShapeComboBox.setValue(P2_SHAPE_DEFAULT);

        p1 = initializePlayer(p1ShapeComboBox, p1ImageView, p1ColorPicker, p1PseudoTextField);
        p2 = initializePlayer(p2ShapeComboBox, p2ImageView, p2ColorPicker, p2PseudoTextField);
    }

    @FXML
    private void onConfirmerButtonClicked() {
        p1 = new Player(p1ImageView.getImage(), p1ImageView.getEffect(), p1PseudoTextField.getText());
        p2 = new Player(p2ImageView.getImage(), p2ImageView.getEffect(), p2PseudoTextField.getText());

        TicTacToeController.getConfigurationGameStage().close();
    }
}
