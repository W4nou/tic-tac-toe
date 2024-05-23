package ihm_project.tictactoe;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.util.Objects;

public class ConfigurationController {
    public static final String SHAPES_LOCATION_DEFAULT = "src/main/resources/ihm_project/tictactoe/shapes/";
    public static final String P1_SHAPE_DEFAULT = "emptyCircle.png";
    public static final String P2_SHAPE_DEFAULT = "fullCross.png";

    private Player p1;
    private Player p2;
    private boolean isFirstPlayerRandom = false;

    private File[] folderContent() {
        File shapesFolder = new File(ConfigurationController.SHAPES_LOCATION_DEFAULT);
        return shapesFolder.getAbsoluteFile().listFiles();
    }

    private Blend colorToBlend(Color color) {
        ColorInput colorInput = new ColorInput(0, 0, Double.MAX_VALUE, Double.MAX_VALUE, color);
        return new Blend(BlendMode.SRC_ATOP, null, colorInput);
    }

    private void updateShape(String shape, ImageView imageView) {
        File previewFile = new File(SHAPES_LOCATION_DEFAULT + shape);
        Image previewImage = new Image(previewFile.toURI().toString());
        imageView.setImage(previewImage);
    }

    private void updateShapeColor(ImageView imageView, Blend effect) {
        imageView.setEffect(effect);
    }

    private Player initializePlayer(ComboBox<String> shapeComboBox, ImageView imageView, ColorPicker colorPicker, TextField pseudoTextField) {
        updateShape(shapeComboBox.getValue(), imageView);
        updateShapeColor(imageView, colorToBlend(colorPicker.getValue()));

        shapeComboBox.setOnAction(event -> updateShape(shapeComboBox.getValue(), imageView));
        colorPicker.setOnAction(event -> Platform.runLater(() -> updateShapeColor(imageView, colorToBlend(colorPicker.getValue()))));

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

    protected boolean getIsRandomFirstPlayer() {
        return isFirstPlayerRandom;
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
    private void initialize() {
        File[] shapes = folderContent();
        for (File shape : shapes) {
            p1ShapeComboBox.getItems().add(shape.getName());
            p2ShapeComboBox.getItems().add(shape.getName());
        }
        p1ShapeComboBox.setValue(P1_SHAPE_DEFAULT);
        p2ShapeComboBox.setValue(P2_SHAPE_DEFAULT);

        p1 = initializePlayer(p1ShapeComboBox, p1ImageView, p1ColorPicker, p1PseudoTextField);
        p2 = initializePlayer(p2ShapeComboBox, p2ImageView, p2ColorPicker, p2PseudoTextField);
    }

    @FXML
    private void onConfirmerButtonClicked(ActionEvent event) {
        p1 = new Player(p1ImageView.getImage(), p1ImageView.getEffect(), p1PseudoTextField.getText());
        p2 = new Player(p2ImageView.getImage(), p2ImageView.getEffect(), p2PseudoTextField.getText());

        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();

        stage.close();
    }

    @FXML
    private void randomFirstPlayerChecked() {
        isFirstPlayerRandom = !isFirstPlayerRandom;
    }
}
