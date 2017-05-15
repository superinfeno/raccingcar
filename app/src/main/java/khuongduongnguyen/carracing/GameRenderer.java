package khuongduongnguyen.carracing;

/**
 * Created by Khuong Duong Nguyen on 10/05/2017.
 */

import android.opengl.GLSurfaceView.Renderer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class GameRenderer implements Renderer{

    private TexRoad road = new TexRoad();
    private long loopStart = 0;
    private long loopEnd = 0;
    private long loopRunTime = 0;
    private TexCar car = new TexCar();
    private float roadYOffset = 0.0f;
    private float carSpeed = 0.0f;
    private TexController right = new TexController();
    private TexController left = new TexController();
    private float carCurrentPos = 2.8f;
    private static float carLLimit = 1.8f;
    private static float carRLimit = 3.8f;
    private static float carCenterPos = 2.8f;

    GameActivity b;

    @Override
    public void onDrawFrame(GL10 gl) {
        loopStart = System.currentTimeMillis();
        try {
            if (loopRunTime < Global.GAME_THREAD_FPS_SLEEP) {
                Thread.sleep(Global.GAME_THREAD_FPS_SLEEP - loopRunTime);
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        DrawRoad(gl);
        DrawCar(gl);
        DrawLeft(gl);
        DrawRight(gl);

        ScrollRoad();
        Turn();
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
    }

    public void DrawCar(GL10 gl){
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glPushMatrix();

        gl.glScalef(.15f, Global.getProportionateHeight(0.15f), .15f);
        gl.glTranslatef(carCurrentPos, 1f, 0f);

        gl.glMatrixMode(GL10.GL_TEXTURE);
        gl.glLoadIdentity();
        gl.glTranslatef(0.0f, 0.0f, 0.0f);

        car.draw(gl);
        gl.glPopMatrix();

        gl.glLoadIdentity();
    }

    public void DrawRoad(GL10 gl){
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glPushMatrix();
        gl.glScalef(1f, 1f, 1f);
        gl.glTranslatef(0f, 0f, 0f);

        gl.glMatrixMode(GL10.GL_TEXTURE);
        gl.glLoadIdentity();
        gl.glTranslatef(0.0f, roadYOffset, 0.0f);

        road.draw(gl);
        gl.glPopMatrix();
        gl.glLoadIdentity();
    }



    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        // Enable 2D maping capability
        car.loadTexture(gl, Global.CAR, Global.context);
        gl.glEnable(GL10.GL_TEXTURE_2D);
        gl.glClearDepthf(1.0f);

        // Text depthe of all objects on surface
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glDepthFunc(GL10.GL_LEQUAL);

        // Enable blend to create transperency
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

        // Load textures
        road.loadTexture(gl, Global.ROAD , Global.context);
        right.loadTexture(gl, Global.TURN_RIGHT, Global.context);
        left.loadTexture(gl, Global.TURN_LEFT, Global.context);
    }

    public void DrawRight(GL10 gl){
            gl.glMatrixMode(GL10.GL_MODELVIEW);
            gl.glLoadIdentity();
            gl.glPushMatrix();
            gl.glScalef(.25f, Global.getProportionateHeight(0.25f), .25f);
            gl.glTranslatef(3f, 0f, 0f);
        	gl.glMatrixMode(GL10.GL_TEXTURE);
            gl.glLoadIdentity();
            gl.glTranslatef(0.0f, 0.0f, 0.0f);
       		right.draw(gl);
            gl.glPopMatrix();
            gl.glLoadIdentity();
    }
    public void DrawLeft(GL10 gl){
            gl.glMatrixMode(GL10.GL_MODELVIEW);
            gl.glLoadIdentity();
            gl.glPushMatrix();
            gl.glScalef(.25f, Global.getProportionateHeight(0.25f), .25f);
            gl.glTranslatef(0f, 0f, 0f);
        	gl.glMatrixMode(GL10.GL_TEXTURE);
            gl.glLoadIdentity();
            gl.glTranslatef(0.0f, 0.0f, 0.0f);
      		left.draw(gl);
            gl.glPopMatrix();
       		gl.glLoadIdentity();
    }
    private void ScrollRoad(){
        if(roadYOffset < 1.0f){ // reset road texture position
            roadYOffset += carSpeed;
            if(carSpeed < 0.03f){
                carSpeed += 0.0002f;
            }
        }else{
            roadYOffset -= 1.0f;
        }
    }

    private void Turn() {
        switch (Global.PLAYER_ACTION) {
            case Global.RIGHT_PRESSED:
                if(carCurrentPos < carRLimit){
                    carCurrentPos = carCurrentPos + 0.05f;
                }else{
                    carCurrentPos = carRLimit;
                }
                break;
            case Global.LEFT_PRESSED:
                if(carCurrentPos > carLLimit){
                    carCurrentPos = carCurrentPos - 0.05f;
                }else{
                    carCurrentPos = carLLimit;
                }
            case Global.CONTROL_RELEASED:

                break;
        }
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

        // Enable game screen width and height to access other functions and classes
        Global.GAME_SCREEN_WIDTH = width;
        Global.GAME_SCREEN_HEIGHT = height;

        // set position and size of viewport
        gl.glViewport(0, 0, width, height);

        // Put OpenGL to projectiong matrix to access glOrthof()
        gl.glMatrixMode(GL10.GL_PROJECTION);

        // Load current identity of OpenGL state
        gl.glLoadIdentity();

        // set orthogonal two dimensional rendering of scene
        gl.glOrthof(0f, 1f, 0f, 1f, -1f, 1f);
    }

}