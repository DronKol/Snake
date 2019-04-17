package ru.main;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class ModelSnake {
    private double elementSize=10;
    private double rectPositionX=0;
    private double rectPositionY=50;

    private double snakeLength=0;

    private String direction = "";

    private double newApplePositionX;
    private double newApplePositionY;
    private double applePositionX=0;
    private double applePositionY=0;

    private Color snakeColor=Color.RED;
    private Color appleColor=Color.GREEN;

    private double x;
    private double y;
    private List<ModelSnakeListener> listeners = new ArrayList<>();
    public ModelSnake(double snakeLength) {
        this.snakeLength=snakeLength;
    }

    private void notifyAllListeners () {
        for (ModelSnakeListener listener : listeners) {
            listener.onchange();
        }
    }

    public void addChangeListener (ModelSnakeListener modelSnakeListener) {
            listeners.add(modelSnakeListener);
    }

    public void removeChangeListener (ModelSnakeListener modelSnakeListener) {
        listeners.remove(modelSnakeListener);
    }


    public double getElementSize() {
        return elementSize;
    }
    public double getSnakeLength() {
        return snakeLength;
    }
    public double getRectPositionY() {
        return rectPositionY;
    }
    public double getRectPositionX() {
        return rectPositionX;
    }
    public String getDirection() {
        return direction;
    }
    public double getNewApplePositionX() {
        this.setApplePositionX(((int)(Math.random()*60))*10);
        return newApplePositionX=((int)(Math.random()*60))*10;
    }
    public double getNewApplePositionY() {
        this.setApplePositionY(((int)(Math.random()*35))*10);
        return newApplePositionY=((int)(Math.random()*35))*10;
    }
    public double getApplePositionX() {
        return applePositionX;
    }
    public double getApplePositionY() {
        return applePositionY;
    }


    public Color getSnakeColor() {
        return snakeColor;
    }
    public Color getAppleColor() {
        return appleColor;
    }


    public void setSnakeLength(double snakeLength) {
        this.snakeLength = snakeLength;
        notifyAllListeners();
    }
    public void setRectPositionY(double rectPositionY) {
        this.rectPositionY = rectPositionY;
        notifyAllListeners();
    }
    public void setRectPositionX(double rectPositionX) {
        this.rectPositionX = rectPositionX;
        notifyAllListeners();
    }
    public void setDirection(String direction) {
        this.direction = direction;
        notifyAllListeners();
    }

    public void setApplePositionX (double applePositionX) {
        this.applePositionX=applePositionX;
        notifyAllListeners();
    }
    public void setApplePositionY (double applePositionY) {
        this.applePositionY=applePositionY;
        notifyAllListeners();
    }





    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
        notifyAllListeners();
    }

    public void setY(double y) {
        this.y = y;
        notifyAllListeners();
    }
}
