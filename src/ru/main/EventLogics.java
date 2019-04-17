package ru.main;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.awt.image.SampleModel;

import static javafx.scene.paint.Color.*;

public class EventLogics {

    private final double canvasStartPositionY = 50;

    private final double elementSizeX = 20;
    private final double elementSizeY = 20;
    private double initialPositionX = 0;
    private double initialPositionY = 50;
    private double rectPositionX = initialPositionX;
    private double rectPositionY = initialPositionY;
    private String string = "0";

    public Canvas handle (KeyEvent event,Canvas canvas) {
//        Canvas canvas = new Canvas();
//        canvas.setHeight(400);
//        canvas.setWidth(600);
        GraphicsContext context = canvas.getGraphicsContext2D();

    if (event.getCode()== KeyCode.DOWN &&rectPositionY<=canvas.getHeight()-2*elementSizeY) {
//                    context.fillRect(rectPositionX,rectPositionY,elementSizeX,elementSizeY);
//        while (rectPositionY<=canvas.getHeight()-2*elementSizeY) {
//        rectPositionY += elementSizeY;
//        context.setFill(Color.RED);
//        context.fillRect(rectPositionX, rectPositionY, elementSizeX, elementSizeY);
            string="down";
            System.out.println("SnakeUI.handle DOWN");
//        }
    }
    if (event.getCode() == KeyCode.UP&&rectPositionY>=canvasStartPositionY+elementSizeY ) {
//        rectPositionY-=elementSizeY;
//        context.setFill(Color.RED);
//        context.fillRect(rectPositionX,rectPositionY,elementSizeX,elementSizeY);
        string="up";
        System.out.println("SnakeUI.handle UP");


    }if (event.getCode() == KeyCode.LEFT&&rectPositionX>=elementSizeX ) {
//            rectPositionX-=elementSizeX;
//            context.setFill(Color.RED);
//            context.fillRect(rectPositionX,rectPositionY,elementSizeX,elementSizeY);
            string="left";
        System.out.println("SnakeUI.handle LEFT");


    }if (event.getCode() == KeyCode.RIGHT&&rectPositionX<canvas.getWidth()-2*elementSizeX ) {
//            rectPositionX+=elementSizeX;
//            context.setFill(Color.RED);
//            context.fillRect(rectPositionX,rectPositionY,elementSizeX,elementSizeY);
            string="right";
        System.out.println("SnakeUI.handle RIGHT");

    }

    return canvas;
    }


    public GraphicsContext movement (Canvas canvas) {
//        Canvas canvas = new Canvas();
        canvas.setHeight(400);
        canvas.setWidth(600);
        GraphicsContext context = canvas.getGraphicsContext2D();
        context.setFill(Color.BLUE);
        context.fillRect(0, 50, canvas.getWidth(), canvas.getHeight());

        if (string.equals("down")&&rectPositionY<=canvas.getHeight()-elementSizeY) {
            context.setFill(Color.RED);
            context.fillRect(rectPositionX,rectPositionY,elementSizeX,elementSizeY);
            rectPositionY += elementSizeY;
        } else if (string.equals("down")) {
            context.setFont(new Font("Verdana",30));
            context.strokeText("OVER",250,200);
        }
        if (string.equals("up")&&rectPositionY>=canvasStartPositionY) {
            context.setFill(Color.RED);
            context.fillRect(rectPositionX,rectPositionY,elementSizeX,elementSizeY);
            rectPositionY-=elementSizeY;
        } else if (string.equals("up")) {
            context.setFont(new Font("Verdana",30));
            context.strokeText("OVER",250,200);
        }

        if (string.equals("left")&&rectPositionX>=elementSizeX ) {
            context.setFill(Color.RED);
            context.fillRect(rectPositionX,rectPositionY,elementSizeX,elementSizeY);
            rectPositionX -= elementSizeX;
        } else if (string.equals("left")) {
            context.setFont(new Font("Verdana",30));
            context.strokeText("OVER",250,200);
        }

        if (string.equals("right")&&rectPositionX<canvas.getWidth()-elementSizeX ) {
            context.setFill(Color.RED);
            context.fillRect(rectPositionX,rectPositionY,elementSizeX,elementSizeY);
            rectPositionX += elementSizeX;
        } else if (string.equals("right")) {
            context.setFont(new Font("Verdana",30));
            context.strokeText("OVER",250,200);
        }


        return context;


    }







}
