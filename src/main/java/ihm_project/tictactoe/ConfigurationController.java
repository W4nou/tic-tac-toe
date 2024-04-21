package ihm_project.tictactoe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;

public class ConfigurationController {
    private Player p1;
    private Player p2;

    @FXML
    private TextField p2PseudoTextField;

    @FXML
    private Button confirmerButton;

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

    public File[] folderContent(String path) {
        File shapesFolder = new File(path);
        return shapesFolder.getAbsoluteFile().listFiles();
    }

    public Blend colorToBlend(Color color) {
        ColorInput colorInput = new ColorInput(0, 0, Double.MAX_VALUE, Double.MAX_VALUE, color);
        return new Blend(BlendMode.SRC_ATOP, null, colorInput);
    }

    @FXML
    public void initialize() {
        File[] shapes = folderContent("src/main/resources/ihm_project/tictactoe/shapes");
        for (File shape : shapes) {
            p1ShapeComboBox.getItems().add(shape.getName());
            p2ShapeComboBox.getItems().add(shape.getName());
        }
        p1ShapeComboBox.setValue("emptyCircle.png");
        p2ShapeComboBox.setValue("fullCross.png");

        File p1previewFile = new File("src/main/resources/ihm_project/tictactoe/shapes/"+p1ShapeComboBox.getValue());
        Image p1PreviewImage = new Image(p1previewFile.toURI().toString());
        p1ImageView.setImage(p1PreviewImage);
        p1ImageView.setEffect(colorToBlend(p1ColorPicker.getValue()));

        File p2previewFile = new File("src/main/resources/ihm_project/tictactoe/shapes/"+p2ShapeComboBox.getValue());
        Image p2PreviewImage = new Image(p2previewFile.toURI().toString());
        p2ImageView.setImage(p2PreviewImage);
        p2ImageView.setEffect(colorToBlend(p2ColorPicker.getValue()));
    }

    public void reloadPseudo(String p1, String p2) {
        p1PseudoTextField.setText(p1);
        p2PseudoTextField.setText(p2);
    }

    @FXML
    void onConfirmerButtonClicked(ActionEvent event) {
        this.p1 = new Player(p1ColorPicker.getValue(), "chemin/shape.png", p1PseudoTextField.getText());
        this.p2 = new Player(p2ColorPicker.getValue(), "chemin/shape.png", p2PseudoTextField.getText());

        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();

        stage.hide();
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

}
