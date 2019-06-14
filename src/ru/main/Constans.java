package ru.main;

import javafx.geometry.Point2D;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.awt.*;

public class Constans {
    public final static Font FONT_GAMEOVER_MESSAGE = new Font("Verdana", 30);
    public final static String GAME_OVER_MESSAGE = "Game Over";
    public final static Text GAME_TITLE = new Text("Snake game window");

    public final static int GAME_OVER_TEXT_POSITION_X = 200;
    public final static int GAME_OVER_TEXT_POSITION_Y = 220;
    public final static double CANVAS_START_POSITION_Y = 50;

    public final static String BUTTON_GAME_SPEED_UP_RESULT = "Game speed have been doubled";
    public final static String BUTTON_GAME_SPEED_DOWN_RESULT = "Game speed have been halved";
    public final static String BUTTON_GAME_SPEED_UP_TITLE = "Up speed";
    public final static String BUTTON_GAME_SPEED_DOWN_TITLE = "Down speed";
    public final static String BUTTON_RESTART_TITLE = "Restart the game";
    public final static String RESTART_GAME_HINT_FOR_TEXTFIELD = "Press button "+BUTTON_RESTART_TITLE+" for another try";

    public final static long INITIAL_GAME_SPEED = 200;
    public final static int POINTS_FROM_APPLE = 10;

}
