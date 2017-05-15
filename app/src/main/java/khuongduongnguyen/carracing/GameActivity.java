package khuongduongnguyen.carracing;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.WindowManager;

import static khuongduongnguyen.carracing.Global.display;

public class GameActivity extends Activity {
    private GameView gameView;
    public int check=0;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new GameView(this);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(gameView);
        }


     	@Override
        protected void onResume() {
                super.onResume();
                gameView.onResume();
        }

        @Override
        protected void onPause() {
                super.onPause();
                gameView.onPause();
        }


    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        this.getWindowManager().getDefaultDisplay().getMetrics(display);
        int height = Math.round(Global.display.heightPixels * Global.getProportionateHeight(0.25f));
        int excludedArea = Global.display.heightPixels - height;
        if(y > excludedArea){
            switch(event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    if(x < Global.display.widthPixels/2){
                        Global.PLAYER_ACTION = Global.LEFT_PRESSED;
                    }else if(x > Global.display.widthPixels/2){
                            Global.PLAYER_ACTION = Global.RIGHT_PRESSED;
                    }
                    break;
                case MotionEvent.ACTION_UP:
                        Global.PLAYER_ACTION = Global.CONTROL_RELEASED;
                    break;
            }
        }
        return false;
    }
}
