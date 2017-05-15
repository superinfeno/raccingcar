package khuongduongnguyen.carracing;

import android.view.MotionEvent;

/**
 * Created by Khuong Duong Nguyen on 12/05/2017.
 */

public class TexController extends Texture {
    private static float texture[] = {
            0.0f, 0.0f,
            1.0f, 0.0f,
            1.0f, 1.0f,
            0.0f, 1.0f
    };
	public TexController(){
        super(texture);
        }
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        return false;
    }
}
