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
import javafx.stage.Stage;

public class SnakeUI extends Application {
//    private int currentSnakeLength = 5;
//
//    private final double canvasStartPositionY = 50;
//
////    private final double elementSizeX = 20;
////    private final double elementSizeY = 20;
////    private double initialPositionX = 0;
////    private double initialPositionY = 50;
////    private double rectPositionX = initialPositionX;
////    private double rectPositionY = initialPositionY;
////
////    private double lastPositionX = initialPositionX;
////    private double lastPositionY = initialPositionY;
//
//
////    public void movementControl (Button btn) {
////        Canvas canvas = new Canvas();
////        GraphicsContext gc = canvas.getGraphicsContext2D();
////        gc.setFill(Color.RED);
////        while (true) {
////            try {
////                Thread.currentThread().sleep(500);
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////            }
////            gc.fillRect(rectPositionX,rectPositionY,elementSizeX,elementSizeY);
//////            if ()
////        }
////    }
//
//    public static void main(String[] args) {
//        Application.launch(args);
//    }
//
//    @Override
    public void start(Stage initialStage) throws Exception {
//
//        Canvas canvas = new Canvas();
//        canvas.setHeight(400);
//        canvas.setWidth(600);
//
//        EventLogics eventLogics = new EventLogics();
//
//
//        GraphicsContext context = canvas.getGraphicsContext2D();
////        context.setFill(Color.BLUE);
////        context.fillRect(0, canvasStartPositionY, canvas.getWidth(), canvas.getHeight());
//
//        canvas.addEventFilter(MouseEvent.ANY, (e) -> canvas.requestFocus());
//        canvas.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent event) {
////                     EventLogics eventLogics = new EventLogics();
//                     eventLogics.handle(event,canvas);
//
//            }
////                if (event.getCode()==KeyCode.DOWN && rectPositionY<=canvas.getHeight()-2*elementSizeY) {
//////                    context.fillRect(rectPositionX,rectPositionY,elementSizeX,elementSizeY);
////                    rectPositionY+=elementSizeY;
////                    System.out.println("SnakeUI.handle DOWN");
////                }
////                if (event.getCode() == KeyCode.UP&& rectPositionY>=canvasStartPositionY+elementSizeY) {
////                    rectPositionY-=elementSizeY;
////                    System.out.println("SnakeUI.handle UP");
////
////                }if (event.getCode() == KeyCode.LEFT&& rectPositionX>=elementSizeX) {
////                    rectPositionX-=elementSizeX;
////                    System.out.println("SnakeUI.handle LEFT");
////
////                }if (event.getCode() == KeyCode.RIGHT&& rectPositionY<canvas.getWidth()-2*elementSizeX) {
////                    rectPositionX+=elementSizeX;
////                    System.out.println("SnakeUI.handle RIGHT");
////
////                }
////
////            }
//        });
//
//
////        canvas.set
//
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Runnable updater = new Runnable() {
//                    @Override
//                    public void run() {
////                        EventLogics eventLogics = new EventLogics();
//                        context.setFill(Color.BLUE);
//                        context.fillRect(0, 50, canvas.getWidth(), canvas.getHeight());
//                        context.setFill(Color.RED);
//                        eventLogics.movement(canvas);
//
////                        context.fillRect(rectPositionX,rectPositionY,elementSizeX,elementSizeY);
////                        if (rectPositionX < canvas.getWidth() - elementSizeX) {
////                            rectPositionX += elementSizeX;
////
////                        } else {
////                            rectPositionX= initialPositionX;
////                        }
//                    }
//                };
//
//                while (true) {
//                    try {
//                        Thread.currentThread().sleep(500);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    Platform.runLater(updater);
//
//                }
//
//            }
//        }).start();
//
//
//
//
//        AnchorPane anchorPane = new AnchorPane();
//        anchorPane.setLayoutX(300);
//        anchorPane.setLayoutY(20);
//
//        String startButton = "(Re)Start game";
//        Button gameStartButton = new Button(startButton);
//
//
//
//        anchorPane.getChildren().add(gameStartButton);
//        AnchorPane.setRightAnchor(gameStartButton, 5d);
//        TextField textField = new TextField("textField");
//        anchorPane.getChildren().add(textField);
//        AnchorPane.setLeftAnchor(textField,1.0);
//
//
//        Group root = new Group();
//        root.getChildren().add(anchorPane);
//        root.getChildren().add(canvas);
////        root.getChildren().add(gridPane);
//
////        Snake snake = new Snake();
////        snake.snake(currentSnakeLength);
////
////        root.getChildren().add(snake);
//
//
////        Scene scene = new Scene(root);
//        Scene scene = new Scene(root);
//
//
//        initialStage.setScene(scene);
//        initialStage.setTitle("Snake game window");
//        initialStage.show();
//
    }
}
