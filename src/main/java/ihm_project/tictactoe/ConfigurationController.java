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
    private static final String SHAPES_LOCATION_DEFAULT = "src/main/resources/ihm_project/tictactoe/shapes/";

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

    private Player p1;
    private Player p2;
    private boolean isFirstPlayerRandom;

    public File[] folderContent(String path) {
        File shapesFolder = new File(path);
        return shapesFolder.getAbsoluteFile().listFiles();
    }

    public Blend colorToBlend(Color color) {
        ColorInput colorInput = new ColorInput(0, 0, Double.MAX_VALUE, Double.MAX_VALUE, color);
        return new Blend(BlendMode.SRC_ATOP, null, colorInput);
    }

    public void updateShape(String shape, ImageView imageView) {
        File previewFile = new File(SHAPES_LOCATION_DEFAULT + shape);
        Image previewImage = new Image(previewFile.toURI().toString());
        imageView.setImage(previewImage);
    }

    public void updateShapeColor(ImageView imageView, Blend effect) {
        imageView.setEffect(effect);
    }

    @FXML
    public void initialize() {
        File[] shapes = folderContent(SHAPES_LOCATION_DEFAULT);
        for (File shape : shapes) {
            p1ShapeComboBox.getItems().add(shape.getName());
            p2ShapeComboBox.getItems().add(shape.getName());
        }
        p1ShapeComboBox.setValue("emptyCircle.png");
        p2ShapeComboBox.setValue("fullCross.png");

        updateShape(p1ShapeComboBox.getValue(), p1ImageView);
        updateShapeColor(p1ImageView, colorToBlend(p1ColorPicker.getValue()));

        updateShape(p2ShapeComboBox.getValue(), p2ImageView);
        updateShapeColor(p2ImageView, colorToBlend(p2ColorPicker.getValue()));

        isFirstPlayerRandom = false;

        // EventListener for shape and color selection

        p1ShapeComboBox.setOnAction(event -> updateShape(p1ShapeComboBox.getValue(), p1ImageView));
        p2ShapeComboBox.setOnAction(event -> updateShape(p2ShapeComboBox.getValue(), p2ImageView));

        // use of Platform.runLater because without it, It was not updating in real time

        p1ColorPicker.setOnAction(event -> Platform.runLater(() -> updateShapeColor(p1ImageView, colorToBlend(p1ColorPicker.getValue()))));
        p2ColorPicker.setOnAction(event -> Platform.runLater(() -> updateShapeColor(p2ImageView, colorToBlend(p2ColorPicker.getValue()))));

        p1 = new Player(p1ImageView.getImage(), p1ImageView.getEffect(), p1PseudoTextField.getText());
        p2 = new Player(p2ImageView.getImage(), p2ImageView.getEffect(), p2PseudoTextField.getText());
    }

    public void reloadPseudo(String newName, TextField textfield) {
        if (Objects.equals(textfield.getId(), "p1TextField")) {
            p1PseudoTextField.setText(newName);
            p1.setName(newName);
        } else {
            p2PseudoTextField.setText(newName);
            p2.setName(newName);
        }
    }

    @FXML
    void onConfirmerButtonClicked(ActionEvent event) {
        p1 = new Player(p1ImageView.getImage(), p1ImageView.getEffect(), p1PseudoTextField.getText());
        p2 = new Player(p2ImageView.getImage(), p2ImageView.getEffect(), p2PseudoTextField.getText());

        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();

        stage.close();
    }

    public Player getP1() {
        return p1;
    }

    public Player getP2() {
        return p2;
    }

    public int getBoardSize() {
        return (int) boardSizeSlider.getValue();
    }

    @FXML
    public void randomFirstPlayerChecked() {
        isFirstPlayerRandom = !isFirstPlayerRandom;
    }

    public boolean getIsRandomFirstPlayer() {
        return isFirstPlayerRandom;
    }
}
