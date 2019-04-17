package ru.main;

import javafx.application.Application;
import javafx.application.Platform;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ViewerSnake extends Application {
    private ModelSnake modelSnake = new ModelSnake(1);
    private ModelSnakeListener modelSnakeListener;

    


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
        canvas.setHeight(400);
        canvas.setWidth(600);
        double canvasStartPositionY = 50;

        GraphicsContext context = canvas.getGraphicsContext2D();

        Canvas appleCanvas = new Canvas();
        GraphicsContext appleContext = appleCanvas.getGraphicsContext2D();

        canvas.addEventFilter(MouseEvent.ANY, (e) -> canvas.requestFocus());
        canvas.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                System.out.println("handle event, RectPositionY = "+modelSnake.getRectPositionY());
                System.out.println("direction =" +
                        " "+modelSnake.getDirection());

                if (event.getCode()== KeyCode.DOWN &&modelSnake.getRectPositionY()<=canvas.getHeight()-2*modelSnake.getElementSize()) {
                    modelSnake.setDirection("down");
                    System.out.println(modelSnake.getDirection());
//        }
                }
                if (event.getCode() == KeyCode.UP&&modelSnake.getRectPositionY()>=canvasStartPositionY+modelSnake.getElementSize() ) {
                    modelSnake.setDirection("up");


                }if (event.getCode() == KeyCode.LEFT&&modelSnake.getRectPositionX()>=modelSnake.getElementSize() ) {
                    modelSnake.setDirection("left");


                }if (event.getCode() == KeyCode.RIGHT&&modelSnake.getRectPositionX()<canvas.getWidth()-2*modelSnake.getElementSize() ) {
                    modelSnake.setDirection("right");

                }


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
                        if (modelSnake.getDirection().equals("down")&&modelSnake.getRectPositionY()<=canvas.getHeight()-modelSnake.getElementSize()) {
//                            context.setFill(Color.RED);
                            for (int i=0;i<modelSnake.getSnakeLength();i++)
                            context.fillRect(modelSnake.getRectPositionX(),modelSnake.getRectPositionY()-i*modelSnake.getElementSize(),modelSnake.getElementSize(),modelSnake.getElementSize());
                            modelSnake.setRectPositionY(modelSnake.getRectPositionY()+modelSnake.getElementSize());
                        } else if (modelSnake.getDirection().equals("down")) {
                            context.setFont(new Font("Verdana",30));
                            context.strokeText("OVER",250,200);
                        }
                        if (modelSnake.getDirection().equals("up")&&modelSnake.getRectPositionY()>=canvasStartPositionY) {
//                            context.setFill(Color.RED);
                            context.fillRect(modelSnake.getRectPositionX(),modelSnake.getRectPositionY(),modelSnake.getElementSize(),modelSnake.getElementSize());
                            modelSnake.setRectPositionY(modelSnake.getRectPositionY()-modelSnake.getElementSize());
                        } else if (modelSnake.getDirection().equals("up")) {
                            context.setFont(new Font("Verdana",30));
                            context.strokeText("OVER",250,200);
                        }

                        if (modelSnake.getDirection().equals("left")&&modelSnake.getRectPositionX()>=modelSnake.getElementSize() ) {
//                            context.setFill(Color.RED);
                            context.fillRect(modelSnake.getRectPositionX(),modelSnake.getRectPositionY(),modelSnake.getElementSize(),modelSnake.getElementSize());
                            modelSnake.setRectPositionX(modelSnake.getRectPositionX() - modelSnake.getElementSize());
                        } else if (modelSnake.getDirection().equals("left")) {
                            context.setFont(new Font("Verdana",30));
                            context.strokeText("OVER",250,200);
                        }

                        if (modelSnake.getDirection().equals("right")&&modelSnake.getRectPositionX()<canvas.getWidth()-modelSnake.getElementSize() ) {
//                            context.setFill(Color.RED);
                            context.fillRect(modelSnake.getRectPositionX(),modelSnake.getRectPositionY(),modelSnake.getElementSize(),modelSnake.getElementSize());
                            modelSnake.setRectPositionX(modelSnake.getRectPositionX() + modelSnake.getElementSize());
                        } else if (modelSnake.getDirection().equals("right")) {
                            context.setFont(new Font("Verdana",30));
                            context.strokeText("OVER",250,200);
                        }

//                        Apple generation
                        if (modelSnake.getApplePositionX() == 0) {
                            do {
                                context.setFill(modelSnake.getAppleColor());
                                context.fillRect(modelSnake.getNewApplePositionX(), canvasStartPositionY+modelSnake.getNewApplePositionY(), modelSnake.getElementSize(), modelSnake.getElementSize());
                                System.out.println(modelSnake.getNewApplePositionX());
                                System.out.println(modelSnake.getNewApplePositionY());
                            } while (modelSnake.getApplePositionX()==modelSnake.getRectPositionX()&&modelSnake.getApplePositionY()==modelSnake.getRectPositionY());
                        }
                        appleContext.fillRect(modelSnake.getNewApplePositionX(), canvasStartPositionY+modelSnake.getNewApplePositionY(), modelSnake.getElementSize(), modelSnake.getElementSize());
//                      Snake Growth
//                        if (modelSnake.getApplePositionX() + modelSnake.getElementSize() / 2 == modelSnake.getRectPositionX() + modelSnake.getElementSize() / 2 && modelSnake.getApplePositionY() + modelSnake.getElementSize() / 2 == modelSnake.getRectPositionY() + modelSnake.getElementSize() / 2) {
//                            modelSnake.setSnakeLength(modelSnake.getSnakeLength()+1);
//                            modelSnake.setApplePositionX(0);
//                        }


                    }
                };

                while (true) {
                    try {
                        Thread.currentThread().sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Platform.runLater(updater);

                }

            }
        }).start();




        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setLayoutX(300);
        anchorPane.setLayoutY(20);

        String startButton = "(Re)Start game";
        Button gameStartButton = new Button(startButton);



        anchorPane.getChildren().add(gameStartButton);
        AnchorPane.setRightAnchor(gameStartButton, 5d);
        TextField textField = new TextField("textField");
        anchorPane.getChildren().add(textField);
        AnchorPane.setLeftAnchor(textField,1.0);


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
}
