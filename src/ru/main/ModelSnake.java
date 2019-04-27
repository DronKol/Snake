package ru.main;

import javafx.scene.paint.Color;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class ModelSnake {
    private double elementSize = 10;
    //private double rectPositionX;
    //private double rectPositionY;

    private long gameSpeed = 200;

    //private double snakeLength = 0;
    private List<Point2D.Double> listOfElements = new ArrayList<Point2D.Double>();
    private ModelSnake modelSnake;

    private String direction = "";

    private double applePositionX;
    private double applePositionY;

    private boolean alive=true;

    private Color snakeColor = Color.RED;
    private Color appleColor = Color.GREEN;

    //Canvas dimensions
    private double canvasWidth=600;
    private double canvasHeight=400;


    private double x;
    private double y;
    private List<ModelSnakeListener> listeners = new ArrayList<>();

    public ModelSnake() {
        
    }

    private void notifyAllListeners() {
        for (ModelSnakeListener listener : listeners) {
            listener.onchange();
        }
    }

    public void addChangeListener(ModelSnakeListener modelSnakeListener) {
        listeners.add(modelSnakeListener);
    }

    public void removeChangeListener(ModelSnakeListener modelSnakeListener) {
        listeners.remove(modelSnakeListener);
    }


    public double getElementSize() {
        return elementSize;
    }

    //public double getSnakeLength() {
    //    return snakeLength;
    //}
    //
    //public double getRectPositionY() {
    //    return rectPositionY;
    //}
    //
    //public double getRectPositionX() {
    //    return rectPositionX;
    //}


    public double getCanvasWidth() {
        return canvasWidth;
    }

    public void setCanvasWidth(double canvasWidth) {
        this.canvasWidth = canvasWidth;
    }

    public double getCanvasHeight() {
        return canvasHeight;
    }

    public void setCanvasHeight(double canvasHeight) {
        this.canvasHeight = canvasHeight;
    }

    public String getDirection() {
        return direction;
    }

    public long getGameSpeed() {
        return gameSpeed;
    }

    public double getNewApplePositionX() {
//        this.setApplePositionX(((int)(Math.random()*60))*10);
        return applePositionX = ((int) (Math.random() * 60)) * 10;
    }

    public double getNewApplePositionY() {
//        applePositionY.setApplePositionY(((int)(Math.random()*35))*10);
        return applePositionY = ((int) (Math.random() * 35)) * 10;
    }

    public double getApplePositionX() {

        return applePositionX;
    }

    public double getApplePositionY() {

        return applePositionY;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Color getSnakeColor() {
        return snakeColor;
    }

    public Color getAppleColor() {
        return appleColor;
    }


    //public void setSnakeLength(double snakeLength) {
    //    this.snakeLength = snakeLength;
    //    notifyAllListeners();
    //}
    //
    //public void setRectPositionY(double rectPositionY) {
    //    this.rectPositionY = rectPositionY;
    //    notifyAllListeners();
    //}
    //
    //public void setRectPositionX(double rectPositionX) {
    //    this.rectPositionX = rectPositionX;
    //    notifyAllListeners();
    //}

    public void setDirection(String direction) {
        this.direction = direction;
        notifyAllListeners();
    }

    public void setApplePositionX(double applePositionX) {
        this.applePositionX = applePositionX;
        notifyAllListeners();
    }

    public void setApplePositionY(double applePositionY) {
        this.applePositionY = applePositionY;
        notifyAllListeners();
    }

    public void setGameSpeed(long gameSpeed) {
        this.gameSpeed = gameSpeed;
        notifyAllListeners();
    }

    //public List<Point2D.Double> getListOfElements() {
    //    for (int i = 0; i < listOfElements.size(); i++) {
    //        listOfElements.get(i);
    //    }
    //
    //
    //    return listOfElements;
    //
    //}


    public List<Point2D.Double> getListOfElements() {
        return listOfElements;
    }

    public void setElementLocation(int element, double locationX, double locationY) {
        listOfElements.get(element).setLocation(locationX,locationY);

        //Point2D.Double point = new Point2D.Double();
        //point = modelSnake.getListOfElements().get(element);
        //point.setLocation(locationX,locationY);

        //modelSnake.getListOfElements().get(element).setLocation(locationX,locationY);
        //modelSnake.listOfElements.set(element,point);
        //return listOfElements;
    }

    public void addSnakeElements() {
        Point2D.Double point = new Point2D.Double();
        //point.setLocation(10,20);
        listOfElements.add(point);

        System.out.println("ListSize = "+listOfElements.size());
        for (int i = listOfElements.size()-1 ; i >0; ) {

            listOfElements.get(i).setLocation(listOfElements.get(i-1).getX(),listOfElements.get(i-1).getY());
            //listOfElements.set(i, listOfElements.get(i - 1));
            i--;
        }
        //conditions for size()==1 in viewer "start conditions" part

        if (listOfElements.size() == 2) {
            if (direction.equals("down")) {
                listOfElements.get(0).setLocation(listOfElements.get(1).getX(), listOfElements.get(1).getY() - elementSize);
            }
            if (direction.equals("up")) {
                listOfElements.get(0).setLocation(listOfElements.get(1).getX(), listOfElements.get(1).getY() + elementSize);
            }
            if (direction.equals("right")) {
                listOfElements.get(0).setLocation(listOfElements.get(1).getX()- elementSize, listOfElements.get(1).getY() );
            }
            if (direction.equals("left")) {
                listOfElements.get(0).setLocation(listOfElements.get(1).getX()+ elementSize, listOfElements.get(1).getY() );
            }
        }

        if (listOfElements.size()>2) {
            if (listOfElements.get(2).getX() - listOfElements.get(1).getX() == 0 && listOfElements.get(2).getY() - listOfElements.get(1).getY() > 0) {
                listOfElements.get(0).setLocation(listOfElements.get(1).getX(), listOfElements.get(1).getY() - elementSize);
            }
            if (listOfElements.get(2).getX() - listOfElements.get(1).getX() == 0 && listOfElements.get(2).getY() - listOfElements.get(1).getY() < 0) {
                listOfElements.get(0).setLocation(listOfElements.get(1).getX(), listOfElements.get(1).getY() + elementSize);
            }
            if (listOfElements.get(2).getX() - listOfElements.get(1).getX() > 0 && listOfElements.get(2).getY() - listOfElements.get(1).getY() == 0) {
                listOfElements.get(0).setLocation(listOfElements.get(1).getX() - elementSize, listOfElements.get(1).getY());
            }
            if (listOfElements.get(2).getX() - listOfElements.get(1).getX() < 0 && listOfElements.get(2).getY() - listOfElements.get(1).getY() == 0) {
                listOfElements.get(0).setLocation(listOfElements.get(1).getX() + elementSize, listOfElements.get(1).getY());
            }

        }
        return;

    }


    public void removeAllSnakeElements (List<Point2D.Double> list) {
        for (int i = 0; i < list.size(); i++) {
            list.removeAll(listOfElements);
        }
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
