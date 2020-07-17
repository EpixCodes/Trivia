package sample;


import animatefx.animation.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXToggleButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;


public class Controller {

    // FXML Vars
    @FXML
    private FontAwesomeIcon close;

    @FXML
    private FontAwesomeIcon minimize;

    @FXML
    private Label question;

    @FXML
    private Label grade;

    @FXML
    private Label timer;

    @FXML
    private Label questionNumber;

    @FXML
    private Label submitStatement;

    @FXML
    private FontAwesomeIcon backArrow;

    @FXML
    private JFXRadioButton trueButton;

    @FXML
    private JFXRadioButton falseButton;

    @FXML
    private JFXButton nextButton;

    @FXML
    private JFXButton submitButton;

    @FXML
    private JFXButton button1;

    @FXML
    private JFXButton button2;

    @FXML
    private JFXButton button3;

    @FXML
    private JFXButton button4;

    @FXML
    private JFXButton button5;

    @FXML
    private JFXButton backArrowButton;

    @FXML
    private AnchorPane loadingScreen;

    @FXML
    private AnchorPane dragWindow;

    @FXML
    private AnchorPane navBar;

    @FXML
    private AnchorPane aboutMe;

    @FXML
    private AnchorPane playPage;

    @FXML
    private AnchorPane wholeWindow;

    @FXML
    private AnchorPane settings;

    @FXML
    private AnchorPane settingsPage;

    @FXML
    private AnchorPane submitTab;

    @FXML
    private AnchorPane goBack;

    @FXML
    private AnchorPane main;

    @FXML
    private AnchorPane main2;

    @FXML
    private JFXButton navPlay;

    @FXML
    private JFXButton navAbout;

    @FXML
    private JFXButton navSettings;

    @FXML
    private JFXButton finishButton;

    @FXML
    private JFXToggleButton darkThemeButton;

    @FXML
    private FontAwesomeIcon infoIcon;

    @FXML
    private FontAwesomeIcon cogIcon;

    @FXML
    private FontAwesomeIcon playIcon;


    private final String question1 = "There are 60 mins in a min";
    private final String question2 = "Cars used to drive on the water";
    private final String question3 = "Bananas and oranges are sweet vegetables";
    private final String question4 = "Umbrellas are used to keep you wet";
    private final String question5 = "Houses are more than $50 dollars";

    private String question1Yes = "";
    private String question2Yes = "";
    private String question3Yes = "";
    private String question4Yes = "";
    private String question5Yes = "";

    private final String style = "-fx-background-color: #369369";

    private int score = 0;

    private double xOffset = 0;
    private double yOffset = 0;


    public void initialize() {
        setVisibility();
        setTimer();
        setMouseDrag();
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


    public void setVisibility() {
        submitTab.setVisible(false);
        loadingScreen.setVisible(false);
        aboutMe.setVisible(false);
        settingsPage.setVisible(false);
        playPage.setVisible(true);
    }

    public void setFinish() throws IOException {
        loadingScreen.setVisible(true);
        goBack.setVisible(false);
        submitTab.setVisible(false);

        Stage f = MainMenu.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));

        new Thread(() -> {
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.runLater(() -> f.setScene(new Scene(root, 600, 400)));
            Platform.runLater(f::centerOnScreen);
        }).start();
    }

    public void setAboutMe() {
        submitTab.setVisible(false);
        aboutMe.setVisible(true);
        playPage.setVisible(false);
        settingsPage.setVisible(false);

        new FadeIn(aboutMe).setSpeed(3).play();

        infoIcon.setFill(Paint.valueOf("#3ca374"));
        playIcon.setFill(Paint.valueOf("#d7cec4"));
        cogIcon.setFill(Paint.valueOf("#d7cec4"));

        navAbout.setStyle("-fx-text-fill: #3CA374");
        navSettings.setStyle("-fx-text-fill: #d7cec4");
        navPlay.setStyle("-fx-text-fill: #d7cec4"); 
    }

    public void setPlay() {
        playPage.setVisible(true);
        aboutMe.setVisible(false);
        submitTab.setVisible(false);
        settingsPage.setVisible(false);

        new FadeIn(main).setSpeed(3).play();
        new FadeIn(main2).setSpeed(3).play();

        infoIcon.setFill(Paint.valueOf("#d7cec4"));
        playIcon.setFill(Paint.valueOf("#3ca374"));
        cogIcon.setFill(Paint.valueOf("#d7cec4"));

        navPlay.setStyle("-fx-text-fill: #3CA374");
        navAbout.setStyle("-fx-text-fill: #d7cec4");
        navSettings.setStyle("-fx-text-fill: #d7cec4");
    }

    public void setSettings() {
        playPage.setVisible(false);
        aboutMe.setVisible(false);
        submitTab.setVisible(false);
        settingsPage.setVisible(true);

        new FadeIn(settingsPage).setSpeed(3).play();

        infoIcon.setFill(Paint.valueOf("#d7cec4"));
        playIcon.setFill(Paint.valueOf("#d7cec4"));
        cogIcon.setFill(Paint.valueOf("#3ca374"));

        navSettings.setStyle("-fx-text-fill: #3CA374");
        navAbout.setStyle("-fx-text-fill: #d7cec4");
        navPlay.setStyle("-fx-text-fill: #d7cec4");
    }

    public void backToMainMenu() throws IOException {
        loadingScreen.setVisible(true);
        navBar.setVisible(false);
        submitTab.setVisible(false);
        playPage.setVisible(false);
        settingsPage.setVisible(false);
        aboutMe.setVisible(false);
        goBack.setVisible(false);


        Stage f = MainMenu.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));

        new Thread(() -> {
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.runLater(() -> f.setScene(new Scene(root, 600, 400)));
            Platform.runLater(f::centerOnScreen);
        }).start();
    }


    public void setDarkTheme() {
        if (darkThemeButton.isSelected()) {
            String styleDark = "-fx-background-color: #36393F";

            wholeWindow.setStyle(styleDark);
            loadingScreen.setStyle(styleDark);
            settings.setStyle(styleDark);

            dragWindow.setStyle("-fx-background-color: #202225");
        }
        else {
            wholeWindow.setStyle("-fx-background-color: #FFFFFF");
            loadingScreen.setStyle("-fx-background-color: #FFFFFF");
            settings.setStyle("-fx-background-color: #FFFFFF");
            dragWindow.setStyle("-fx-background-color: #E3E5E8");
        }
    }

    public void backArrowEnter() {
        backArrowButton.setButtonType(JFXButton.ButtonType.RAISED);
        backArrow.setFill(Paint.valueOf("#6783da"));
    }

    public void backArrowExit() {
        backArrowButton.setButtonType(JFXButton.ButtonType.FLAT);
        backArrow.setFill(Paint.valueOf("#d7cec4"));
    }

    public void setYesOrNo() {
        if (trueButton.isSelected())
            setTrue();

        else if (falseButton.isSelected())
            setFalse();
    }

    // if user presses yes or no
    public void setTrue() {

        switch (question.getText()) {


            case question1:
                question1Yes = "true";
                question.setText(question2);
                questionNumber.setText("Q2");
                nextButton.setDisable(false);
                button1.setStyle(style);
                break;

            case question2:
                question2Yes = "true";
                question.setText(question3);
                questionNumber.setText("Q3");
                nextButton.setDisable(false);
                button2.setStyle(style);
                break;

            case question3:
                question3Yes = "true";
                question.setText(question4);
                questionNumber.setText("Q4");
                nextButton.setDisable(false);
                button3.setStyle(style);
                break;

            case question4:
                question4Yes = "true";
                question.setText(question5);
                questionNumber.setText("Q5");
                nextButton.setDisable(true);
                button4.setStyle(style);
                break;

            case question5:
                question5Yes = "true";
                button5.setStyle(style);

                break;

        }

    }

    public void setFalse() {

        switch (question.getText()) {

            case question1:
                question1Yes = "false";
                question.setText(question2);
                questionNumber.setText("Q2");
                nextButton.setDisable(false);
                button1.setStyle(style);
                break;

            case question2:
                question2Yes = "false";
                question.setText(question3);
                questionNumber.setText("Q3");
                nextButton.setDisable(false);
                button2.setStyle(style);
                break;

            case question3:
                question3Yes = "false";
                question.setText(question4);
                questionNumber.setText("Q4");
                nextButton.setDisable(false);
                button3.setStyle(style);
                break;

            case question4:
                question4Yes = "false";
                question.setText(question5);
                questionNumber.setText("Q5");
                nextButton.setDisable(true);
                button4.setStyle(style);

                break;

            case question5:
                question5Yes = "false";
                button5.setStyle(style);
                break;

        }

    }

    public void onActionTrueButton() {
        falseButton.setSelected(false);
        trueButton.setSelected(true);
    }

    public void onActionFalseButton() {
        trueButton.setSelected(false);
        falseButton.setSelected(true);
    }

    public void next() {
        playAnimation();

        setYesOrNo();

        setSelected();
    }

    public void setSelected() {
        trueButton.setSelected(false);
        falseButton.setSelected(false);

        switch (question.getText()) {


            case question1:
                switch (question1Yes) {
                    case "true":
                        trueButton.setSelected(true);
                        break;
                    case "false":
                        falseButton.setSelected(true);
                        break;
                    default:
                        falseButton.setSelected(false);
                        trueButton.setSelected(false);
                        break;
                }
                break;


            case question2:

                switch (question2Yes) {
                    case "true":
                        trueButton.setSelected(true);
                        break;
                    case "false":
                        falseButton.setSelected(true);
                        break;
                    default:
                        falseButton.setSelected(false);
                        trueButton.setSelected(false);
                        break;
                }

                break;


            case question3:

                switch (question3Yes) {
                    case "true":
                        trueButton.setSelected(true);
                        break;
                    case "false":
                        falseButton.setSelected(true);
                        break;
                    default:
                        falseButton.setSelected(false);
                        trueButton.setSelected(false);
                        break;
                }

                break;


            case question4:

                switch (question4Yes) {
                    case "true":
                        trueButton.setSelected(true);
                        break;
                    case "false":
                        falseButton.setSelected(true);
                        break;
                    default:
                        falseButton.setSelected(false);
                        trueButton.setSelected(false);
                        break;
                }

                break;


            case question5:

                switch (question5Yes) {
                    case "true":
                        trueButton.setSelected(true);
                        break;
                    case "false":
                        falseButton.setSelected(true);
                        break;
                    default:
                        falseButton.setSelected(false);
                        trueButton.setSelected(false);
                        break;
                }

                break;
        }

    }

    // Close Button Enter and Exit
    public void setCloseEnter() { close.setFill(Paint.valueOf("red")); }

    public void setCloseExit() { close.setFill(Paint.valueOf("#d7cec4")); }


    // Minimize Button Enter And Exit
    public void minimizeEnter() { minimize.setFill(Paint.valueOf("#4DABF7")); }

    public void minimizeExit() { minimize.setFill(Paint.valueOf("#D7CEC4")); }


    // On Action Closes and Minimizes
    public void setClose() {
        Stage f = MainMenu.getPrimaryStage();
        f.close();
    }

    public void setMinimize() {
        Stage f = MainMenu.getPrimaryStage();
        f.setIconified(true);
    }

    public void setSubmit() {
        score = 0;

        setYesOrNo();

        setSelected();

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText("Are you sure you want to submit this quiz?");
        alert.setTitle("Epix Trivia");
        alert.setHeaderText("Confirmation");

        ButtonType YesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);

        alert.getButtonTypes().setAll(YesButton, noButton);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == YesButton) {
            new FadeIn(grade).setSpeed(0.5).play();

            submitTab.setVisible(true);
            settingsPage.setVisible(false);
            aboutMe.setVisible(false);
            playPage.setVisible(false);
            navBar.setVisible(false);
            navPlay.setStyle("");

            if (question1Yes.equals("false")) score++;

            if (question2Yes.equals("false")) score++;

            if (question3Yes.equals("false")) score++;

            if (question4Yes.equals("false")) score++;

            if (question5Yes.equals("true")) score++;

            submitStatement.setText("Congratulations You Got " +  score + "/5" + " Questions Correct\n\n" +
                    "it took you " + timer.getText() + " seconds to complete this quiz");

            grade.setText(score + "/5");
        }
    }


    public void setButton1() {
        setYesOrNo();
        playAnimation();
        questionNumber.setText("Q1");
        nextButton.setDisable(false);
        question.setText(question1);
        setSelected();
    }

    public void setButton2() {
        setYesOrNo();

        playAnimation();
        questionNumber.setText("Q2");
        nextButton.setDisable(false);
        question.setText(question2);
        setSelected();
    }

    public void setButton3() {
        setYesOrNo();

        playAnimation();
        questionNumber.setText("Q3");
        nextButton.setDisable(false);
        question.setText(question3);
        setSelected();
    }

    public void setButton4() {
        setYesOrNo();

        playAnimation();
        questionNumber.setText("Q4");
        nextButton.setDisable(false);
        question.setText(question4);
        setSelected();
    }

    public void setButton5() {
        setYesOrNo();

        playAnimation();
        questionNumber.setText("Q5");
        nextButton.setDisable(true);
        question.setText(question5);
        setSelected();
    }

    public void playAnimation() {
        new FadeIn(question).setSpeed(3).play();
        new FadeIn(questionNumber).setSpeed(3).play();
    }

    public void setTimer() {
        AnimationTimer animationTimer = new AnimationTimer() {
            private long timestamp;
            private long time = 0;
            private long fraction = 0;

            @Override
            public void start() {
                // current time adjusted by remaining time from last run
                timestamp = System.currentTimeMillis() - fraction;
                super.start();
            }

            @Override
            public void stop() {
                super.stop();
                // save leftover time not handled with the last update
                fraction = System.currentTimeMillis() - timestamp;
            }

            @Override
            public void handle(long now) {
                long newTime = System.currentTimeMillis();
                if (timestamp + 1000 <= newTime) {
                    long deltaT = (newTime - timestamp) / 1000;
                    time += deltaT;
                    timestamp += 1000 * deltaT;
                    if (!grade.getText().equals(score + "/5"))
                        Platform.runLater(() -> timer.setText(Long.toString(time)));
                }
            }
        };
        animationTimer.start();
    }

    public void buttonEnter() {
        if (nextButton.isHover()) {
            nextButton.setStyle("-fx-background-color: #6783da");
            nextButton.setButtonType(JFXButton.ButtonType.RAISED);
        }
        else if (submitButton.isHover()) {
            submitButton.setStyle("-fx-background-color: #6783da");
            submitButton.setButtonType(JFXButton.ButtonType.RAISED);
        }
        else if (finishButton.isHover()) {
            finishButton.setStyle("-fx-background-color: #6783da");
            finishButton.setButtonType(JFXButton.ButtonType.RAISED);
        }
    }

    public void buttonExit() {
        submitButton.setStyle("-fx-background-color: #7289DA");
        submitButton.setButtonType(JFXButton.ButtonType.FLAT);

        nextButton.setStyle("-fx-background-color: #7289DA");
        nextButton.setButtonType(JFXButton.ButtonType.FLAT);

        finishButton.setStyle("-fx-background-color: #7289DA");
        finishButton.setButtonType(JFXButton.ButtonType.FLAT);
    }

    public void buttonBarEnter() {
        if (button1.isHover()) {
            button1.setButtonType(JFXButton.ButtonType.RAISED);
        }
        else if (button2.isHover()) {
            button2.setButtonType(JFXButton.ButtonType.RAISED);
        }
        else if (button3.isHover()) {
            button3.setButtonType(JFXButton.ButtonType.RAISED);
        }
        else if (button4.isHover()) {
            button4.setButtonType(JFXButton.ButtonType.RAISED);
        }
        else if (button5.isHover()) {
            button5.setButtonType(JFXButton.ButtonType.RAISED);
        }
    }

    public void buttonBarExit() {
        button1.setButtonType(JFXButton.ButtonType.FLAT);
        button2.setButtonType(JFXButton.ButtonType.FLAT);
        button3.setButtonType(JFXButton.ButtonType.FLAT);
        button4.setButtonType(JFXButton.ButtonType.FLAT);
        button5.setButtonType(JFXButton.ButtonType.FLAT);
    }

    public void navBarEnter() {
        String style = "-fx-text-fill: #ffffff;";

        if (settingsPage.isVisible()) {
            if (navAbout.isHover()) {
                navAbout.setStyle(style);
                infoIcon.setFill(Paint.valueOf("#ffffff"));
            }
            else if (navPlay.isHover()) {
                navPlay.setStyle(style);
                playIcon.setFill(Paint.valueOf("#ffffff"));
            }
        }

        else if (aboutMe.isVisible()) {
            if (navPlay.isHover()) {
                navPlay.setStyle(style);
                playIcon.setFill(Paint.valueOf("#ffffff"));
            }
            else if (navSettings.isHover()) {
                navSettings.setStyle(style);
                cogIcon.setFill(Paint.valueOf("#ffffff"));
            }
        }

        else if (playPage.isVisible()) {
            if (navSettings.isHover()) {
                navSettings.setStyle(style);
                cogIcon.setFill(Paint.valueOf("#ffffff"));
            }
            else if (navAbout.isHover()) {
                navAbout.setStyle(style);
                infoIcon.setFill(Paint.valueOf("#ffffff"));
            }
        }
    }

    public void navBarExit() {
        String exitStyle = "-fx-text-fill: #d7cec4";

        if (settingsPage.isVisible()) {
            navAbout.setStyle(exitStyle);
            infoIcon.setFill(Paint.valueOf("#d7cec4"));

            navPlay.setStyle(exitStyle);
            playIcon.setFill(Paint.valueOf("#d7cec4"));
        }

        else if (aboutMe.isVisible()) {
            navSettings.setStyle(exitStyle);
            cogIcon.setFill(Paint.valueOf("#d7cec4"));

            navPlay.setStyle(exitStyle);
            playIcon.setFill(Paint.valueOf("#d7cec4"));
        }

        else if (playPage.isVisible()) {
            navSettings.setStyle(exitStyle);
            cogIcon.setFill(Paint.valueOf("#d7cec4"));

            navAbout.setStyle(exitStyle);
            infoIcon.setFill(Paint.valueOf("#d7cec4"));
        }
    }
}
