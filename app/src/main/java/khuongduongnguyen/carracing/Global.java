package khuongduongnguyen.carracing;

/**
 * Created by Khuong Duong Nguyen on 10/05/2017.
 */

import android.content.Context;
import android.util.DisplayMetrics;

public class Global {
    public static Context context;
    public static int ROAD = R.drawable.road;
    public static final int GAME_THREAD_FPS_SLEEP = (1000 / 60);
    public static int CAR = R.drawable.carr01;
    public static int CAR2 = R.drawable.player_car;
    public static int GAME_SCREEN_WIDTH = 0;
    public static int GAME_SCREEN_HEIGHT = 0;
    public static int PLAYER_ACTION = 0;
    public static final int CONTROL_RELEASED = 0;
    public static final int RIGHT_PRESSED = 1;
    public static final int LEFT_PRESSED = 2;
    public static int TURN_LEFT = R.drawable.left;
    public static int TURN_RIGHT = R.drawable.right;
    public static DisplayMetrics display = new DisplayMetrics();

    public static float getProportionateHeight(float width){
        float ratio = (float)GAME_SCREEN_WIDTH/GAME_SCREEN_HEIGHT;
        float height = ratio * width;
        return height;
    }
}

