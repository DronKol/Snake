package ru.main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import static ru.main.Constans.*;

public class ViewerSnake extends Application {
    private ModelSnake modelSnake = new ModelSnake();
    private Runnable updater;


    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage initialStage) throws Exception {


        Canvas canvas = new Canvas();
        canvas.setWidth(modelSnake.getCanvasWidth());
        canvas.setHeight(modelSnake.getCanvasHeight());
        double canvasStartPositionY = CANVAS_START_POSITION_Y;

        GraphicsContext context = canvas.getGraphicsContext2D();

        canvas.addEventFilter(MouseEvent.ANY, (e) -> canvas.requestFocus());
        canvas.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                List<Point2D.Double> list = modelSnake.getListOfElements();
                int listSize = modelSnake.getListOfElements().size();

                if ((event.getCode() == KeyCode.S && listSize == 1) ||
                        (event.getCode() == KeyCode.S && list.get(listSize - 1).getY() >= list.get(listSize - 2).getY())) {
                    modelSnake.setDirection("down");
                }
                if ((event.getCode() == KeyCode.W && listSize == 1) ||
                        (event.getCode() == KeyCode.W && list.get(listSize - 1).getY() <= list.get(listSize - 2).getY())) {
                    modelSnake.setDirection("up");
                }
                if ((event.getCode() == KeyCode.A && listSize == 1) ||
                        (event.getCode() == KeyCode.A && list.get(listSize - 1).getX() <= list.get(listSize - 2).getX())) {
                    modelSnake.setDirection("left");
                }
                if ((event.getCode() == KeyCode.D && listSize == 1) ||
                        (event.getCode() == KeyCode.D && list.get(listSize - 1).getX() >= list.get(listSize - 2).getX())) {
                    modelSnake.setDirection("right");

                }
                System.out.println("direction =" +
                        " " + modelSnake.getDirection());


            }
        });


        new Thread(new Runnable() {
            @Override
            public void run() {
                updater = new Runnable() {
                    @Override
                    public void run() {


//                      Field generation
                        context.setFill(modelSnake.getGameFieldColor());
                        context.fillRect(0, canvasStartPositionY, canvas.getWidth(), canvas.getHeight());

                        if (modelSnake.isAlive()) {

//                      Snake elements import
                            List<Point2D.Double> list = new ArrayList<>();
                            list = modelSnake.getListOfElements();


                            //Start conditions
                            context.setFill(modelSnake.getSnakeColor());
                            if (modelSnake.getDirection().equals("")) {
                                modelSnake.addSnakeElements();
                                modelSnake.setElementLocation(0, canvas.getWidth() / 2, canvas.getHeight() / 2);
                                list = modelSnake.getListOfElements();

                                context.fillRect(list.get(0).getX(), list.get(0).getY(), modelSnake.getElementSize(), modelSnake.getElementSize());

                                modelSnake.setDirection("right");

                            }

                            //Snake generation
                            int tmpSize = list.size();
                            boolean outOfBorder = false;
                            if (modelSnake.getDirection().equals("down") && list.get(list.size() - 1).getY() <= canvas.getHeight() - 2 * modelSnake.getElementSize()) {

                                for (int i = 0; i < tmpSize - 1; i++) {
                                    list.get(i).setLocation(list.get(i + 1).getX(), list.get(i + 1).getY());
                                }

                                modelSnake.setElementLocation(tmpSize - 1, list.get(tmpSize - 1).getX(), list.get(tmpSize - 1).getY() + modelSnake.getElementSize());

                                for (int i = 0; i < list.size(); i++) {

                                    context.fillRect(list.get(i).getX(), list.get(i).getY(), modelSnake.getElementSize(), modelSnake.getElementSize());
                                }

                            } else if (modelSnake.getDirection().equals("down") && list.get(tmpSize - 1).getY() >= canvas.getHeight() - modelSnake.getElementSize()) {
                                modelSnake.setAlive(false);
                                if (modelSnake.getCurrentPoints() > modelSnake.getBestPoints()) {
                                    modelSnake.setBestPoints(modelSnake.getCurrentPoints());
                                }
                            }
                            if (modelSnake.getDirection().equals("up") && list.get(tmpSize - 1).getY() >= canvasStartPositionY + modelSnake.getElementSize()) {
                                for (int i = 0; i < tmpSize - 1; i++) {
                                    list.get(i).setLocation(list.get(i + 1).getX(), list.get(i + 1).getY());
                                }

                                modelSnake.setElementLocation(tmpSize - 1, list.get(tmpSize - 1).getX(), list.get(tmpSize - 1).getY() - modelSnake.getElementSize());

                                for (int i = 0; i < list.size(); i++) {

                                    context.fillRect(list.get(i).getX(), list.get(i).getY(), modelSnake.getElementSize(), modelSnake.getElementSize());
                                }

                            } else if (modelSnake.getDirection().equals("up") && list.get(tmpSize - 1).getY() <= canvasStartPositionY + modelSnake.getElementSize()) {
                                modelSnake.setAlive(false);
                                if (modelSnake.getCurrentPoints() > modelSnake.getBestPoints()) {
                                    modelSnake.setBestPoints(modelSnake.getCurrentPoints());
                                }
                            }

                            if (modelSnake.getDirection().equals("left") && list.get(tmpSize - 1).getX() >= modelSnake.getElementSize()) {
                                for (int i = 0; i < tmpSize - 1; i++) {
                                    list.get(i).setLocation(list.get(i + 1).getX(), list.get(i + 1).getY());
                                }

                                modelSnake.setElementLocation(tmpSize - 1, list.get(tmpSize - 1).getX() - modelSnake.getElementSize(), list.get(tmpSize - 1).getY());

                                for (int i = 0; i < list.size(); i++) {

                                    context.fillRect(list.get(i).getX(), list.get(i).getY(), modelSnake.getElementSize(), modelSnake.getElementSize());
                                }

                            } else if (modelSnake.getDirection().equals("left") && list.get(tmpSize - 1).getX() <= modelSnake.getElementSize()) {
                                modelSnake.setAlive(false);
                                if (modelSnake.getCurrentPoints() > modelSnake.getBestPoints()) {
                                    modelSnake.setBestPoints(modelSnake.getCurrentPoints());
                                }
                            }

                            if (modelSnake.getDirection().equals("right") && list.get(tmpSize - 1).getX() < canvas.getWidth() - modelSnake.getElementSize()) {
                                for (int i = 0; i < tmpSize - 1; i++) {
                                    modelSnake.getListOfElements().get(i).setLocation(list.get(i + 1).getX(), list.get(i + 1).getY());
                                }

                                modelSnake.setElementLocation(tmpSize - 1, list.get(tmpSize - 1).getX() + modelSnake.getElementSize(), list.get(tmpSize - 1).getY());

                                for (int i = 0; i < list.size(); i++) {

                                    context.fillRect(list.get(i).getX(), list.get(i).getY(), modelSnake.getElementSize(), modelSnake.getElementSize());
                                }

                            } else if (modelSnake.getDirection().equals("right") && list.get(tmpSize - 1).getX() >= canvas.getWidth() - modelSnake.getElementSize()) {
                                modelSnake.setAlive(false);
                                if (modelSnake.getCurrentPoints() > modelSnake.getBestPoints()) {
                                    modelSnake.setBestPoints(modelSnake.getCurrentPoints());
                                }
                            }


//                        Apple generation
                            if (modelSnake.getApplePositionX() == 0) {
                                context.setFill(Color.BLUE);
                                context.fillOval(modelSnake.getNewApplePositionX(), canvasStartPositionY + modelSnake.getNewApplePositionY(), modelSnake.getElementSize(), modelSnake.getElementSize());
                                System.out.println("New X = " + modelSnake.getNewApplePositionX());
                                System.out.println("New Y = " + modelSnake.getNewApplePositionY());

                                for (int i = 0; i < tmpSize - 1; i++)
                                    while (modelSnake.getApplePositionX() == list.get(i).getX() && modelSnake.getApplePositionY() == list.get(i).getY()) {
                                        context.setFill(Color.BLUE);
                                        context.fillOval(modelSnake.getNewApplePositionX(), canvasStartPositionY + modelSnake.getNewApplePositionY(), modelSnake.getElementSize(), modelSnake.getElementSize());
                                    }

                            }
                            context.setFill(modelSnake.getAppleColor());
                            context.fillOval(modelSnake.getApplePositionX(), canvasStartPositionY + modelSnake.getApplePositionY(), modelSnake.getElementSize(), modelSnake.getElementSize());
//
//                        Snake Growth
                            if (modelSnake.getApplePositionX() == list.get(tmpSize - 1).getX() && canvasStartPositionY + modelSnake.getApplePositionY() == list.get(tmpSize - 1).getY()) {
                                modelSnake.addSnakeElements();
                                modelSnake.setApplePositionX(0);
                                modelSnake.accelerationFromLength();
                                System.out.println("Game speed = " + modelSnake.getGameSpeed());
                                if (list.size() % 10 == 0) {
                                    modelSnake.setCurrentPoints(modelSnake.getCurrentPoints() + 3 * POINTS_FROM_APPLE);
                                } else {
                                    modelSnake.setCurrentPoints(modelSnake.getCurrentPoints() + POINTS_FROM_APPLE);
                                }
                                //System.out.println(modelSnake.getCurrentPoints());
                            }

                            //    Snake self-destruction
                            for (int i = 0; i < tmpSize - 2; i++) {
                                if (list.get(tmpSize - 1).getX() == list.get(i).getX() && list.get(tmpSize - 1).getY() == list.get(i).getY()) {
                                    modelSnake.setAlive(false);
                                    if (modelSnake.getCurrentPoints() > modelSnake.getBestPoints()) {
                                        modelSnake.setBestPoints(modelSnake.getCurrentPoints());
                                    }

                                }
                            }

                        } else if (!modelSnake.isAlive()) {
                            context.setFont(FONT_GAMEOVER_MESSAGE);
                            context.strokeText(GAME_OVER_MESSAGE, GAME_OVER_TEXT_POSITION_X, GAME_OVER_TEXT_POSITION_Y);

                        }


                    }

                };

                updater(updater);

            }
        }).start();


        AnchorPane anchorPane = new AnchorPane();
        //anchorPane.setLayoutX(300);
        anchorPane.setLayoutY(450);
        anchorPane.setPrefWidth(canvas.getWidth());

        Text currentPointsLabel = new Text();
        anchorPane.getChildren().add(currentPointsLabel);
        AnchorPane.setTopAnchor(currentPointsLabel,1.0);



        String startButtonLabel = BUTTON_RESTART_TITLE;
        Button startButton = new Button(startButtonLabel);



        startButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                modelSnake.setDirection("");
                modelSnake.getListOfElements().removeAll(modelSnake.getListOfElements());
                modelSnake.setGameSpeed(INITIAL_GAME_SPEED);
                modelSnake.setCurrentPoints(0);
                System.out.println("start");
                System.out.println("List size = " + modelSnake.getListOfElements().size());

                modelSnake.setAlive(true);

            }
        });


        anchorPane.getChildren().add(startButton);
        AnchorPane.setRightAnchor(startButton, 5d);

        Pane pane = new Pane();
        Button speedUp = new Button(BUTTON_GAME_SPEED_UP_TITLE);
        Button speedDown = new Button(BUTTON_GAME_SPEED_DOWN_TITLE);


        speedUp.setLayoutX(20);
        speedUp.setLayoutY(120);
        speedDown.setLayoutX(100);
        speedDown.setLayoutY(120);

        Text textField = new Text("Welcome");
        textField.setLayoutX(modelSnake.getCanvasWidth() / 2 - modelSnake.getCanvasWidth() / 16);

        textField.setLayoutY(-50);

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                updater = new Runnable() {
                    @Override
                    public void run() {
                        currentPointsLabel.setText("Current points  " + modelSnake.getCurrentPoints() + "\n" + "\n" + "Best Points  " + modelSnake.getBestPoints());
                        if (!modelSnake.isAlive()) {
                            textField.setText(RESTART_GAME_HINT_FOR_TEXTFIELD);
                        }
                        System.out.println("Current score = " + modelSnake.getCurrentPoints());


                        updater(updater);
                    }
                };




            }
        });
        thread.start();



        speedUp.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                modelSnake.setGameSpeed(modelSnake.getGameSpeed() / 2);
                textField.setText(BUTTON_GAME_SPEED_UP_RESULT);
                //System.out.println("Speed Up button");
            }
        });
        speedDown.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                modelSnake.setGameSpeed(modelSnake.getGameSpeed() * 2);
                textField.setText(BUTTON_GAME_SPEED_DOWN_RESULT);
            }
        });


        BorderPane borderPane = new BorderPane();

        pane.getChildren().add(speedUp);
        pane.getChildren().add(speedDown);
        pane.getChildren().add(textField);

        anchorPane.getChildren().add(pane);



        Group root = new Group();
        root.getChildren().add(anchorPane);
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);


        initialStage.setScene(scene);
        initialStage.setTitle(String.valueOf(GAME_TITLE));
        initialStage.show();

    }

    private void updater(Runnable updater) {
        while (true) {
            try {

                Thread.currentThread().sleep(modelSnake.getGameSpeed());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.runLater(updater);
        }
    }
}
