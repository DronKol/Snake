package ru.main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.LinkedList;

public class ViewerSnake extends Application {
    private ModelSnake modelSnake = new ModelSnake(1);
    private ModelSnakeListener modelSnakeListener;
    LinkedList snakeElements = new LinkedList();

//    public ViewerSnake(ModelSnake modelSnake) {
//        this.modelSnake=modelSnake;
//        modelSnakeListener = new ModelSnakeListener() {
//            @Override
//            public void onchange() {
//                System.out.println("ViewerSnake.onchange");
//
//            }
//        };
//        modelSnake.addChangeListener(modelSnakeListener);
//    }


    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage initialStage) throws Exception {

        Canvas canvas = new Canvas();
        canvas.setWidth(600);
        canvas.setHeight(400);
        double canvasStartPositionY = 50;

        GraphicsContext context = canvas.getGraphicsContext2D();

        Canvas appleCanvas = new Canvas();
        GraphicsContext appleContext = appleCanvas.getGraphicsContext2D();
        appleCanvas.setWidth(600);
        appleCanvas.setHeight(400);

        canvas.addEventFilter(MouseEvent.ANY, (e) -> canvas.requestFocus());
        canvas.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                System.out.println("handle event, RectPositionY = " + modelSnake.getRectPositionY());

                if (event.getCode() == KeyCode.DOWN && modelSnake.getRectPositionY() <= canvas.getHeight() - 2 * modelSnake.getElementSize()) {
                    modelSnake.setDirection("down");
//        }
                }
                if (event.getCode() == KeyCode.UP && modelSnake.getRectPositionY() >= canvasStartPositionY + modelSnake.getElementSize()) {
                    modelSnake.setDirection("up");


                }
                if (event.getCode() == KeyCode.LEFT && modelSnake.getRectPositionX() >= modelSnake.getElementSize()) {
                    modelSnake.setDirection("left");


                }
                if (event.getCode() == KeyCode.RIGHT && modelSnake.getRectPositionX() < canvas.getWidth() - 2 * modelSnake.getElementSize()) {
                    modelSnake.setDirection("right");

                }

                System.out.println("direction =" +
                        " " + modelSnake.getDirection());


            }
        });


        new Thread(new Runnable() {
            @Override
            public void run() {
                Runnable updater = new Runnable() {
                    @Override
                    public void run() {

//                      Field generation
                        context.setFill(Color.BLUE);
                        context.fillRect(0, canvasStartPositionY, canvas.getWidth(), canvas.getHeight());

//                      Snake generation
                        context.setFill(modelSnake.getSnakeColor());
                        if (modelSnake.getDirection().equals("")) {
                            snakeElements.addLast(modelSnake);
                            context.fillRect(canvas.getWidth() / 2, canvas.getHeight() / 2, modelSnake.getElementSize(), modelSnake.getElementSize());
                            modelSnake.setRectPositionX(canvas.getWidth() / 2);
                            modelSnake.setRectPositionY(canvas.getHeight() / 2);
                            modelSnake.setDirection("right");

                        }
                        if (modelSnake.getDirection().equals("down") && modelSnake.getRectPositionY() <= canvas.getHeight() - modelSnake.getElementSize()) {
//                            context.setFill(Color.RED);
                            for (int i = 0; i < modelSnake.getSnakeLength(); i++) {
                                context.fillRect(modelSnake.getRectPositionX(), modelSnake.getRectPositionY() - i * modelSnake.getElementSize(), modelSnake.getElementSize(), modelSnake.getElementSize());
                            }
                            modelSnake.setRectPositionY(modelSnake.getRectPositionY() + modelSnake.getElementSize());
                        } else if (modelSnake.getDirection().equals("down")) {
                            context.setFont(new Font("Verdana", 30));
                            context.strokeText("OVER", 250, 200);
                        }
                        if (modelSnake.getDirection().equals("up") && modelSnake.getRectPositionY() >= canvasStartPositionY) {
                            for (int i = 0; i < modelSnake.getSnakeLength(); i++) {
                                context.fillRect(modelSnake.getRectPositionX(), modelSnake.getRectPositionY() + i * modelSnake.getElementSize(), modelSnake.getElementSize(), modelSnake.getElementSize());
                            }
                            modelSnake.setRectPositionY(modelSnake.getRectPositionY() - modelSnake.getElementSize());
                        } else if (modelSnake.getDirection().equals("up")) {
                            context.setFont(new Font("Verdana", 30));
                            context.strokeText("OVER", 250, 200);
                        }

                        if (modelSnake.getDirection().equals("left") && modelSnake.getRectPositionX() >= modelSnake.getElementSize()) {
                            for (int i = 0; i < modelSnake.getSnakeLength(); i++) {
                                context.fillRect(modelSnake.getRectPositionX() + i * modelSnake.getElementSize(), modelSnake.getRectPositionY(), modelSnake.getElementSize(), modelSnake.getElementSize());
                            }
                            modelSnake.setRectPositionX(modelSnake.getRectPositionX() - modelSnake.getElementSize());
                        } else if (modelSnake.getDirection().equals("left")) {
                            context.setFont(new Font("Verdana", 30));
                            context.strokeText("OVER", 250, 200);
                        }

                        if (modelSnake.getDirection().equals("right") && modelSnake.getRectPositionX() < canvas.getWidth() - modelSnake.getElementSize()) {
                            for (int i = 0; i < modelSnake.getSnakeLength(); i++) {
                                context.fillRect(modelSnake.getRectPositionX() - i * modelSnake.getElementSize(), modelSnake.getRectPositionY(), modelSnake.getElementSize(), modelSnake.getElementSize());
                            }
                            modelSnake.setRectPositionX(modelSnake.getRectPositionX() + modelSnake.getElementSize());
                        } else if (modelSnake.getDirection().equals("right")) {
                            context.setFont(new Font("Verdana", 30));
                            context.strokeText("OVER", 250, 200);
                        }

//                        Apple generation
                        if (modelSnake.getApplePositionX() == 0) {
                            do {
                                context.setFill(Color.BLUE);
                                context.fillOval(modelSnake.getNewApplePositionX(), canvasStartPositionY + modelSnake.getNewApplePositionY(), modelSnake.getElementSize(), modelSnake.getElementSize());
                                System.out.println(modelSnake.getNewApplePositionX());
                                System.out.println(modelSnake.getNewApplePositionY());
                                System.out.println(modelSnake.getApplePositionX());
                                System.out.println(modelSnake.getApplePositionY());
                            }
                            while (modelSnake.getApplePositionX() == modelSnake.getRectPositionX() && modelSnake.getApplePositionY() == modelSnake.getRectPositionY());
                        }
//                        System.out.println(modelSnake.getApplePositionX());
//                        System.out.println(modelSnake.getApplePositionY());
                        context.setFill(modelSnake.getAppleColor());
                        context.fillOval(modelSnake.getApplePositionX(), canvasStartPositionY + modelSnake.getApplePositionY(), modelSnake.getElementSize(), modelSnake.getElementSize());
//
//                        Snake Growth
                        if (modelSnake.getApplePositionX() == modelSnake.getRectPositionX() && canvasStartPositionY + modelSnake.getApplePositionY() == modelSnake.getRectPositionY()) {
                            modelSnake.setSnakeLength(modelSnake.getSnakeLength() + 1);
                            modelSnake.setApplePositionX(0);
                        }


                    }
                };

                updater(updater);

            }
        }).start();


       // new Thread(new Runnable() {
       //     @Override
       //     public void run() {
       //         Runnable updater = new Runnable() {
       //             @Override
       //             public void run() {
       //                 //                        Apple generation
       //                 if (modelSnake.getApplePositionX() == 0) {
       //                     do {
       //                         appleContext.setFill(modelSnake.getAppleColor());
       //                         appleContext.fillRect(modelSnake.getNewApplePositionX(), canvasStartPositionY+modelSnake.getNewApplePositionY(), modelSnake.getElementSize(), modelSnake.getElementSize());
       //                         System.out.println(modelSnake.getNewApplePositionX());
       //                         System.out.println(modelSnake.getNewApplePositionY());
       //                     } while (modelSnake.getApplePositionX()==modelSnake.getRectPositionX()&&modelSnake.getApplePositionY()==modelSnake.getRectPositionY());
       //                 }
       //                 appleContext.fillRect(modelSnake.getNewApplePositionX(), canvasStartPositionY+modelSnake.getNewApplePositionY(), modelSnake.getElementSize(), modelSnake.getElementSize());
       //
       //             }
       //         };
       //
       //         updater(updater);
       //         return;
       //     }
       // }).start();




        AnchorPane anchorPane = new AnchorPane();
        //anchorPane.setLayoutX(300);
        //anchorPane.setLayoutY(20);
        anchorPane.setPrefWidth(canvas.getWidth());

        String startButton = "(Re)Start game";
        Button gameStartButton = new Button(startButton);


        anchorPane.getChildren().add(gameStartButton);
        AnchorPane.setRightAnchor(gameStartButton, 5d);
        //anchorPane.getChildren().add(textField);
        //AnchorPane.setRightAnchor(textField, 1.0);

        Pane pane = new Pane();
        Button speedUp = new Button("Up Speed");
        Button speedDown = new Button("Down Speed");
        speedUp.setLayoutX(20);
        speedUp.setLayoutY(20);
        speedDown.setLayoutX(100);
        speedDown.setLayoutY(20);

        TextField textField = new TextField("Welcome");
        textField.setLayoutX(200);
        textField.setLayoutY(0);

        speedUp.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                modelSnake.setGameSpeed(modelSnake.getGameSpeed()/2);
                textField.setText("Game speed have been doubled");
            }
        });
        speedUp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                modelSnake.setGameSpeed(modelSnake.getGameSpeed()/2);
                textField.setText("Game speed have been doubled");
                System.out.println("Speed Up button");
            }
        });
        speedDown.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                modelSnake.setGameSpeed(modelSnake.getGameSpeed()*2);
                textField.setText("Game speed have been halved");
            }
        });


        pane.getChildren().add(speedUp);
        pane.getChildren().add(speedDown);
        pane.getChildren().add(textField);

        anchorPane.getChildren().add(pane);

        BorderPane borderPane = new BorderPane();
        
        //AnchorPane.setLeftAnchor(pane);

        //BorderPane borderPane = new BorderPane();
        


        Group root = new Group();
        root.getChildren().add(anchorPane);
        root.getChildren().add(canvas);
//        root.getChildren().add(gridPane);

//        Snake snake = new Snake();
//        snake.snake(currentSnakeLength);
//
//        root.getChildren().add(snake);


//        Scene scene = new Scene(root);
        Scene scene = new Scene(root);


        initialStage.setScene(scene);
        initialStage.setTitle("Snake game window");
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
