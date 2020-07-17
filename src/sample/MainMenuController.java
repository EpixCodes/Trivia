package sample;


import animatefx.animation.FadeIn;
import animatefx.animation.FadeOut;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXToggleButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import java.io.IOException;


public class MainMenuController {

    @FXML
    private JFXButton playButton;

    @FXML
    private FontAwesomeIcon minimize;

    @FXML
    private FontAwesomeIcon close;

    @FXML
    private AnchorPane main;

    @FXML
    private AnchorPane loadingScreen;

    @FXML
    private AnchorPane settings;

    @FXML
    private AnchorPane wholeWindow;

    @FXML
    private AnchorPane dragWindow;

    @FXML
    private JFXToggleButton darkThemeButton;

    @FXML
    private JFXButton settingsButton;

    @FXML
    private FontAwesomeIcon backArrow;

    private double xOffset = 0;
    private double yOffset = 0;

    public void initialize() {
        setVisibility();
        setMouseDrag();
    }

    public void setVisibility() {
        loadingScreen.setVisible(false);
        settings.setVisible(false);
        main.setVisible(true);
    }

    public void setMouseDrag() {
        Stage f = MainMenu.getPrimaryStage();

        dragWindow.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        dragWindow.setOnMouseDragged(event -> {
            f.setX(event.getScreenX() - xOffset);
            f.setY(event.getScreenY() - yOffset);
        });
    }

    public void backToMainMenu() {
        settings.setVisible(false);
        main.setVisible(true);
    }

    public void setDarkTheme() {
        String white = "-fx-background-color: #FFFFFF";
        String blue = "-fx-background-color: #36393F";
        String something = "-fx-background-color: #202225";
        String somethingElse = "-fx-background-color: #E3E5E8";

        if (darkThemeButton.isSelected()) {
            wholeWindow.setStyle(blue);
            loadingScreen.setStyle(blue);
            settings.setStyle(blue);
            dragWindow.setStyle(something);
        }
        else {
            wholeWindow.setStyle(white);
            loadingScreen.setStyle(white);
            settings.setStyle(white);
            dragWindow.setStyle(somethingElse);
        }
    }

    public void play() throws IOException {
        loadingScreen.setVisible(true);
        main.setVisible(false);
        Stage f = MainMenu.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        if (!darkThemeButton.isSelected())
            root.setStyle("-fx-background-color: #FFFFFF;");

        new Thread(() -> {
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.runLater(() -> f.setScene(new Scene(root, 1116, 671)));
            Platform.runLater(f::centerOnScreen);
        }).start();
    }

    public void setSettings() {
        settings.setVisible(true);
        main.setVisible(false);
    }

    public void backArrowEnter() {
        backArrow.setFill(Paint.valueOf("#6783da"));
    }

    public void backArrowExit() {
        backArrow.setFill(Paint.valueOf("#d7cec4"));
    }

    public void buttonEnter() {
        if (playButton.isHover()) {
            playButton.setStyle("-fx-background-color: #6783da");
            playButton.setButtonType(JFXButton.ButtonType.RAISED);
        }
        else if (settingsButton.isHover()) {
            settingsButton.setStyle("-fx-background-color: #6783da");
            settingsButton.setButtonType(JFXButton.ButtonType.RAISED);
        }
    }

    public void buttonExit() {
        playButton.setStyle("-fx-background-color: #7289DA");
        playButton.setButtonType(JFXButton.ButtonType.FLAT);
        settingsButton.setStyle("-fx-background-color: #7289DA");
        settingsButton.setButtonType(JFXButton.ButtonType.FLAT);
    }

    public void setMinimize() {
        Stage f = MainMenu.getPrimaryStage();
        f.setIconified(true);
    }

    public void minimizeEnter() {
        minimize.setFill(Paint.valueOf("#4DABF7"));
    }

    public void minimizeExit() {
        minimize.setFill(Paint.valueOf("#D7CEC4"));
    }

    public void setClose() {
        Stage f = MainMenu.getPrimaryStage();
        f.close();
    }

    public void setCloseEnter() {
        close.setFill(Paint.valueOf("red"));
    }

    public void setCloseExit() {
        close.setFill(Paint.valueOf("#d7cec4"));
    }
}
