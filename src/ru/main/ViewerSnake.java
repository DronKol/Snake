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

import static ru.main.Constans.FONT_GAMEOVER;

public class ViewerSnake extends Application {
    private ModelSnake modelSnake = new ModelSnake();
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
        canvas.setWidth(modelSnake.getCanvasWidth());
        canvas.setHeight(modelSnake.getCanvasHeight());
        double canvasStartPositionY = 50;

        GraphicsContext context = canvas.getGraphicsContext2D();

        //Canvas appleCanvas = new Canvas();
        //GraphicsContext appleContext = appleCanvas.getGraphicsContext2D();
        //appleCanvas.setWidth(600);
        //appleCanvas.setHeight(400);

        canvas.addEventFilter(MouseEvent.ANY, (e) -> canvas.requestFocus());
        //canvas.setOnKeyPressed(new EventHandler<KeyEvent>() {
        //    @Override
        //    public void handle(KeyEvent event) {
        //
        //        if (event.getCode() == KeyCode.DOWN && modelSnake.getDirection()!="up") {
        //            modelSnake.setDirection("down");
        //        }
        //        if (event.getCode() == KeyCode.UP && modelSnake.getDirection()!="down") {
        //            modelSnake.setDirection("up");
        //        }
        //        if (event.getCode() == KeyCode.LEFT && modelSnake.getDirection()!="right") {
        //            modelSnake.setDirection("left");
        //        }
        //        if (event.getCode() == KeyCode.RIGHT && modelSnake.getDirection()!="left") {
        //            modelSnake.setDirection("right");
        //
        //        }
        //        System.out.println("direction =" +
        //                " " + modelSnake.getDirection());
        //
        //
        //    }
        //});

        canvas.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                if (event.getCode() == KeyCode.S && modelSnake.getDirection()!="up") {
                    modelSnake.setDirection("down");
                }
                if (event.getCode() == KeyCode.W && modelSnake.getDirection()!="down") {
                    modelSnake.setDirection("up");
                }
                if (event.getCode() == KeyCode.A && modelSnake.getDirection()!="right") {
                    modelSnake.setDirection("left");
                }
                if (event.getCode() == KeyCode.D && modelSnake.getDirection()!="left") {
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

                            //modelSnake.setDirection("d");
                            modelSnake.setDirection("right");

                        }
                        //

                        //Snake generation
                        int tmpSize = modelSnake.getListOfElements().size();
                        boolean outOfBorder = false;
                        if (modelSnake.getDirection().equals("down") && modelSnake.getListOfElements().get(modelSnake.getListOfElements().size() - 1).getY() <= canvas.getHeight() - modelSnake.getElementSize() && modelSnake.isAlive() == true) {

                            tmpSize = modelSnake.getListOfElements().size();
                            for (int i = 0; i < tmpSize - 1; i++) {
                                modelSnake.getListOfElements().get(i).setLocation(modelSnake.getListOfElements().get(i + 1).getX(), modelSnake.getListOfElements().get(i + 1).getY());
                            }

                            modelSnake.setElementLocation(tmpSize - 1, modelSnake.getListOfElements().get(tmpSize - 1).getX(), modelSnake.getListOfElements().get(tmpSize - 1).getY() + modelSnake.getElementSize());

                            list = modelSnake.getListOfElements();
                            for (int i = 0; i < list.size(); i++) {

                                context.fillRect(list.get(i).getX(), list.get(i).getY(), modelSnake.getElementSize(), modelSnake.getElementSize());
                            }

                        } else if (modelSnake.getDirection().equals("down") && list.get(tmpSize - 1).getY() >= canvas.getHeight() - modelSnake.getElementSize()) {
                            modelSnake.setAlive(false);
                            context.setFont(FONT_GAMEOVER);
                            context.strokeText("OVER", 250, 200);
                        }
                        if (modelSnake.getDirection().equals("up") && list.get(tmpSize - 1).getY() >= canvasStartPositionY + modelSnake.getElementSize() && modelSnake.isAlive() == true) {
                            tmpSize = modelSnake.getListOfElements().size();
                            for (int i = 0; i < tmpSize - 1; i++) {
                                modelSnake.getListOfElements().get(i).setLocation(list.get(i + 1).getX(), modelSnake.getListOfElements().get(i + 1).getY());
                            }

                            modelSnake.setElementLocation(tmpSize - 1, modelSnake.getListOfElements().get(tmpSize - 1).getX(), modelSnake.getListOfElements().get(tmpSize - 1).getY() - modelSnake.getElementSize());

                            list = modelSnake.getListOfElements();
                            for (int i = 0; i < list.size(); i++) {

                                context.fillRect(list.get(i).getX(), list.get(i).getY(), modelSnake.getElementSize(), modelSnake.getElementSize());
                            }

                        } else if (modelSnake.getDirection().equals("up") && list.get(tmpSize - 1).getY() <= canvasStartPositionY + modelSnake.getElementSize()) {
                            modelSnake.setAlive(false);
                            context.setFont(new Font("Verdana", 30));
                            context.strokeText("OVER", 250, 200);
                        }

                        if (modelSnake.getDirection().equals("left") && list.get(tmpSize - 1).getX() >= modelSnake.getElementSize() && modelSnake.isAlive() == true) {
                            tmpSize = modelSnake.getListOfElements().size();
                            for (int i = 0; i < tmpSize - 1; i++) {
                                modelSnake.getListOfElements().get(i).setLocation(modelSnake.getListOfElements().get(i + 1).getX(), modelSnake.getListOfElements().get(i + 1).getY());
                            }

                            modelSnake.setElementLocation(tmpSize - 1, modelSnake.getListOfElements().get(tmpSize - 1).getX() - modelSnake.getElementSize(), modelSnake.getListOfElements().get(tmpSize - 1).getY());

                            list = modelSnake.getListOfElements();
                            for (int i = 0; i < list.size(); i++) {

                                context.fillRect(list.get(i).getX(), list.get(i).getY(), modelSnake.getElementSize(), modelSnake.getElementSize());
                            }

                        } else if (modelSnake.getDirection().equals("left") && list.get(tmpSize - 1).getX() <= modelSnake.getElementSize()) {
                            modelSnake.setAlive(false);
                            context.setFont(new Font("Verdana", 30));
                            context.strokeText("OVER", 250, 200);
                        }

                        if (modelSnake.getDirection().equals("right") && list.get(tmpSize - 1).getX() < canvas.getWidth() - modelSnake.getElementSize() && modelSnake.isAlive() == true) {
                            tmpSize = modelSnake.getListOfElements().size();
                            for (int i = 0; i < tmpSize - 1; i++) {
                                modelSnake.getListOfElements().get(i).setLocation(modelSnake.getListOfElements().get(i + 1).getX(), modelSnake.getListOfElements().get(i + 1).getY());
                            }

                            modelSnake.setElementLocation(tmpSize - 1, modelSnake.getListOfElements().get(tmpSize - 1).getX() + modelSnake.getElementSize(), modelSnake.getListOfElements().get(tmpSize - 1).getY());

                            list = modelSnake.getListOfElements();
                            for (int i = 0; i < list.size(); i++) {

                                context.fillRect(list.get(i).getX(), list.get(i).getY(), modelSnake.getElementSize(), modelSnake.getElementSize());
                            }

                        } else if (modelSnake.getDirection().equals("right") && list.get(tmpSize - 1).getX() >= canvas.getWidth() - modelSnake.getElementSize()) {
                            modelSnake.setAlive(false);
                            context.setFont(new Font("Verdana", 30));
                            context.strokeText("OVER", 250, 200);
                        }



                        //if (modelSnake.getDirection().equals("right")
                        //    //&&list.get(tmpSize - 1).getX() < canvas.getWidth() - modelSnake.getElementSize()
                        //) {
                        //    outOfBorder = false;
                        //    for (int i = 0; i < list.size(); i++) {
                        //        if (list.get(i).getX() >= canvas.getWidth() - modelSnake.getElementSize()) {
                        //            outOfBorder = true;
                        //            System.out.println("right out");
                        //            return;
                        //        }
                        //    }
                        //    if (!outOfBorder) {
                        //
                        //        tmpSize = modelSnake.getListOfElements().size();
                        //        for (int i = 0; i < tmpSize - 1; i++) {
                        //            modelSnake.getListOfElements().get(i).setLocation(modelSnake.getListOfElements().get(i + 1).getX(), modelSnake.getListOfElements().get(i + 1).getY());
                        //        }
                        //
                        //        modelSnake.setElementLocation(tmpSize - 1, modelSnake.getListOfElements().get(tmpSize - 1).getX() + modelSnake.getElementSize(), modelSnake.getListOfElements().get(tmpSize - 1).getY());
                        //
                        //        list = modelSnake.getListOfElements();
                        //        for (int i = 0; i < list.size(); i++) {
                        //
                        //            context.fillRect(list.get(i).getX(), list.get(i).getY(), modelSnake.getElementSize(), modelSnake.getElementSize());
                        //        }
                        //
                        //    }
                        //    else if (outOfBorder) {
                        //        tmpSize = modelSnake.getListOfElements().size();
                        //        for (int i = 0; i < tmpSize - 1; i++) {
                        //            modelSnake.getListOfElements().get(i).setLocation(modelSnake.getListOfElements().get(i + 1).getX(), modelSnake.getListOfElements().get(i + 1).getY());
                        //        }
                        //        modelSnake.setElementLocation(tmpSize - 1, modelSnake.getListOfElements().get(tmpSize - 1).getX() + modelSnake.getElementSize()-canvas.getWidth(), modelSnake.getListOfElements().get(tmpSize - 1).getY());
                        //
                        //        list = modelSnake.getListOfElements();
                        //        for (int i = 0; i < list.size(); i++) {
                        //
                        //            context.fillRect(list.get(i).getX(), list.get(i).getY(), modelSnake.getElementSize(), modelSnake.getElementSize());
                        //        }
                        //    }

                            //} else if (modelSnake.getDirection().equals("right") && list.get(tmpSize - 1).getX() > canvas.getWidth() - modelSnake.getElementSize()) {
                            //    modelSnake.setAlive(false);
                            //    context.setFont(new Font("Verdana", 30));
                            //    context.strokeText("OVER", 250, 200);
                            //
                        //}

//                        Apple generation
                        if (modelSnake.getApplePositionX() == 0) {
                            context.setFill(Color.BLUE);
                            context.fillOval(modelSnake.getNewApplePositionX(), canvasStartPositionY + modelSnake.getNewApplePositionY(), modelSnake.getElementSize(), modelSnake.getElementSize());
                            System.out.println("New X = " + modelSnake.getNewApplePositionX());
                            System.out.println("New Y = " + modelSnake.getNewApplePositionY());

                            for (int i = 0; i < tmpSize - 1; i++)
                                while (modelSnake.getApplePositionX() == modelSnake.getListOfElements().get(i).getX() && modelSnake.getApplePositionY() == modelSnake.getListOfElements().get(i).getY()) {
                                    context.setFill(Color.BLUE);
                                    context.fillOval(modelSnake.getNewApplePositionX(), canvasStartPositionY + modelSnake.getNewApplePositionY(), modelSnake.getElementSize(), modelSnake.getElementSize());
                                }

                        }
                        context.setFill(modelSnake.getAppleColor());
                        context.fillOval(modelSnake.getApplePositionX(), canvasStartPositionY + modelSnake.getApplePositionY(), modelSnake.getElementSize(), modelSnake.getElementSize());
//
//                        Snake Growth
                        if (modelSnake.getApplePositionX() == modelSnake.getListOfElements().get(tmpSize - 1).getX() && canvasStartPositionY + modelSnake.getApplePositionY() == modelSnake.getListOfElements().get(tmpSize - 1).getY()) {
                            modelSnake.addSnakeElements();
                            modelSnake.setApplePositionX(0);
                        }

                        //    Snake self-destruction
                        for (int i = 0; i < tmpSize - 2; i++) {
                            if (list.get(tmpSize - 1).getX() == list.get(i).getX() && list.get(tmpSize - 1).getY() == list.get(i).getY()) {
                                modelSnake.setAlive(false);
                                context.setFont(new Font("Verdana", 30));
                                context.strokeText("OVER", 250, 200);


                            }
                        }
                    } else if (!modelSnake.isAlive()) {
                            context.setFont(new Font("Verdana", 30));
                            context.strokeText("OVER", 250, 200);
                        }


                    }

                };

                updater(updater);

            }
        }).start();


        AnchorPane anchorPane = new AnchorPane();
        //anchorPane.setLayoutX(300);
        anchorPane.setLayoutY(550);
        anchorPane.setPrefWidth(canvas.getWidth());

        String startButtonLabel = "(Re)Start game";
        Button startButton = new Button(startButtonLabel);


        startButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                modelSnake.setDirection("");
                modelSnake.getListOfElements().removeAll(modelSnake.getListOfElements()) ;
                modelSnake.setGameSpeed(200);
                System.out.println("start");
                System.out.println("List size = "+modelSnake.getListOfElements().size());

                modelSnake.setAlive(true);

            }
        });


        anchorPane.getChildren().add(startButton);
        AnchorPane.setRightAnchor(startButton, 5d);
        
        //anchorPane.getChildren().add(textField);
        //AnchorPane.setRightAnchor(textField, 1.0);

        Pane pane = new Pane();
        Button speedUp = new Button("Up Speed");
        Button speedDown = new Button("Down Speed");
        speedUp.setLayoutX(20);
        speedUp.setLayoutY(20);
        speedDown.setLayoutX(100);
        speedDown.setLayoutY(20);

        Text textField = new Text("Welcome");
        textField.setLayoutX(modelSnake.getCanvasWidth()/2-modelSnake.getCanvasWidth()/16);
        
        textField.setLayoutY(-50);



        

        //speedUp.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
        //    @Override
        //    public void handle(MouseEvent event) {
        //        modelSnake.setGameSpeed(modelSnake.getGameSpeed() / 2);
        //        textField.setText("Game speed have been doubled");
        //    }
        //});
        //speedUp.setOnAction(new EventHandler<ActionEvent>() {
        //    @Override
        //    public void handle(ActionEvent event) {
        //        modelSnake.setGameSpeed(modelSnake.getGameSpeed() / 2);
        //        textField.setText("Game speed have been doubled");
        //        System.out.println("Speed Up button");
        //    }
        //});
        //speedDown.setOnAction(new EventHandler<ActionEvent>() {
        //    @Override
        //    public void handle(ActionEvent event) {
        //        modelSnake.setGameSpeed(modelSnake.getGameSpeed() * 2);
        //        textField.setText("Game speed have been halved");
        //        System.out.println("Speed Down button");
        //    }
        //});
        speedUp.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                modelSnake.setGameSpeed(modelSnake.getGameSpeed() / 2);
                textField.setText("Game speed have been doubled");
                System.out.println("Speed Up button");
            }
        });
        speedDown.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                modelSnake.setGameSpeed(modelSnake.getGameSpeed() * 2);
                textField.setText("Game speed have been halved");
            }
        });



        BorderPane borderPane = new BorderPane();
        
        //borderPane.setCenter();

        pane.getChildren().add(speedUp);
        pane.getChildren().add(speedDown);
        pane.getChildren().add(textField);

        anchorPane.getChildren().add(pane);


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
                //if (modelSnake.isAlive()) {
                    Thread.currentThread().sleep(modelSnake.getGameSpeed());
                //} else {
                //    break;
                //}
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.runLater(updater);
        }
    }
}
