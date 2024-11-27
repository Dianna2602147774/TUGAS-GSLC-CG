package page;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ResultPage extends VBox {
    private ImageView originalImageView, transformedImageView;
    private Label lblOriginal, lblTransformed;
    private Button backButton;

    public ResultPage(UploadPage uploadPage, Image originalImage, ImageView transformedImageView) {
        originalImageView = new ImageView(originalImage);
        this.transformedImageView = transformedImageView;

        originalImageView.setFitWidth(250);
        originalImageView.setPreserveRatio(true);

        this.transformedImageView.setFitWidth(250);
        this.transformedImageView.setPreserveRatio(true);

        // Labels for the images
        lblOriginal = new Label("Original Image");
        lblOriginal.setFont(new Font("Arial", 16));

        lblTransformed = new Label("Transformed Image");
        lblTransformed.setFont(new Font("Arial", 16));

        // Back button
        backButton = new Button("Go Back");
        backButton.setStyle("-fx-background-color: #007bff; -fx-text-fill: white; -fx-font-size: 14;");
        backButton.setOnAction(e -> {
            // Reset upload page state
            uploadPage.reset();
            Stage stage = (Stage) this.getScene().getWindow();
            stage.close(); // Close the result page
        });

        // Arrange images and labels
        HBox imageBox = new HBox(20);
        imageBox.getChildren().addAll(
                new VBox(10, lblOriginal, originalImageView),
                new VBox(10, lblTransformed, this.transformedImageView)
        );

        this.setSpacing(20);
        this.getChildren().addAll(imageBox, backButton);
        this.setStyle("-fx-alignment: center; -fx-padding: 20;");
    }
}
