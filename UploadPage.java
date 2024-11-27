package page;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class UploadPage extends VBox {
    private Button uploadButton, transformButton;
    private Label lblTitle, lblImg, lblOpt;
    private ImageView imageView;
    private ComboBox<String> transformOptions;
    private Image originalImage;

    public UploadPage() {
        uploadButton = new Button("Browse...");
        transformButton = new Button("Upload and Convert");
        lblTitle = new Label("Upload and Transform Your Image");
        lblImg = new Label("Choose an Image:");
        lblOpt = new Label("Choose an option:");
        imageView = new ImageView();
        transformOptions = new ComboBox<>();
        transformOptions.getItems().addAll("Grayscale", "Blur");
        transformOptions.setValue("Grayscale"); // Set default option

        // Set styles for labels
        lblTitle.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-alignment: center;");
        lblImg.setStyle("-fx-font-weight: bold;");
        lblOpt.setStyle("-fx-font-weight: bold;");

        // Setup event handlers for buttons
        uploadButton.setOnAction(e -> openFileChooser());
        transformButton.setOnAction(e -> applyTransformation());

        // Arrange layout in VBox
        this.setSpacing(10);
        this.getChildren().addAll(lblTitle, lblImg, uploadButton, lblOpt, transformOptions, transformButton, imageView);
        this.setStyle("-fx-alignment: center;"); // Center all elements in VBox
    }

    private void openFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.gif"));

        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            originalImage = new Image(selectedFile.toURI().toString());
            imageView.setImage(originalImage);
        }
    }

    private void applyTransformation() {
        if (originalImage == null) {
            return; // No image selected
        }

        Image transformedImage = originalImage;
        String selectedOption = transformOptions.getValue();

        // Apply the transformation
        ImageView transformedImageView = new ImageView(originalImage);
        if ("Grayscale".equals(selectedOption)) {
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setSaturation(-1); 
            transformedImageView.setEffect(colorAdjust);
        } else if ("Blur".equals(selectedOption)) {
            GaussianBlur blur = new GaussianBlur(10); 
            transformedImageView.setEffect(blur);
        }

        // Show result page
        showResultPage(this, originalImage, transformedImageView);
    }

    private void showResultPage(UploadPage uploadPage, Image originalImage, ImageView transformedImageView) {
        ResultPage resultPage = new ResultPage(uploadPage, originalImage, transformedImageView);
        Stage resultStage = new Stage();
        Scene resultScene = new Scene(resultPage, 600, 500);
        resultStage.setScene(resultScene);
        resultStage.setTitle("Original and Transformed Image");
        resultStage.show();
    }

    
    public void reset() {
        imageView.setImage(null);
        originalImage = null;    
        transformOptions.setValue("Grayscale"); 
    }

}
