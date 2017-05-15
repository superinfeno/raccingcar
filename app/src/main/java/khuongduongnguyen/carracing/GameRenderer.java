package khuongduongnguyen.carracing;

/**
 * Created by Khuong Duong Nguyen on 10/05/2017.
 */

import android.opengl.GLSurfaceView.Renderer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static khuongduongnguyen.carracing.Global.context;

public class GameRenderer implements Renderer{

    private TexRoad road = new TexRoad();
    private long loopStart = 0;
    private long loopEnd = 0;
    private long loopRunTime = 0;
    private TexCar car = new TexCar();
    private float roadYOffset = 0.0f;
    private float opponent = 6.66f;
    private float opponent2 = 7.96f;
    private float opponent3 = 9.16f;
    private float opponent4 = 10.56f;
    private float carSpeed = 0.0f;
    private TexController right = new TexController();
    private TexController left = new TexController();
    private float carCurrentPos = 2.8f;
    private static float carLLimit = 1.8f;
    private static float carRLimit = 3.8f;
    private static float carCenterPos = 2.8f;
    private float saiso = 0f;
    private float i = 0f;
    private TexCar chalenger = new TexCar();

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
        Drawchalenger(gl);
        //crash();


        ScrollRoad();
        Turn();
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
    }

    public void crash()
    {
        if(opponent <= 2f) {
            if (carCurrentPos >= 2.5f & carCurrentPos <= 3.1f) {
                carSpeed = 0;
                carSpeed -= 0.0002f;
            }
            else{
                opponent = 13f + saiso;
                saiso += 0.08f*i;
                i++;
            }
        }
        if(opponent2 <= 2f) {
            if (carCurrentPos >= 1.5f & carCurrentPos <= 2.1f) {
                carSpeed = 0;
                carSpeed -= 0.0002f;
            }
            else{
                opponent2 = 13f + saiso;
                saiso += 0.08f*i;
                i++;
            }
        }
        if(opponent3 <= 2f) {
            if (carCurrentPos >= 3.5f & carCurrentPos <= 4.1f) {
                carSpeed = 0;
                carSpeed -= 0.0002f;
            }
            else{
                opponent3 = 13f + saiso;
                saiso += 0.08f*i;
                i++;
            }
        }
        if(opponent4 <= 2f) {
            if (carCurrentPos >= 1.5f & carCurrentPos <= 2.1f) {
                carSpeed = 0;
                carSpeed -= 0.0002f;
            }
            else{
                opponent4 = 13f + saiso;
                saiso += 0.08f*i;
                i++;
            }
        }
    }

    public void Drawchalenger(GL10 gl){
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glPushMatrix();

        gl.glScalef(.4f, 1f, 0f);
        gl.glTranslatef(0.7f, 0f, 0f);

        gl.glMatrixMode(GL10.GL_TEXTURE);
        gl.glLoadIdentity();
        gl.glTranslatef(0.0f, roadYOffset, 0.0f);

        chalenger.draw(gl);
        gl.glPopMatrix();
        gl.glLoadIdentity();
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
        car.loadTexture(gl, Global.CAR, context);
        chalenger.loadTexture(gl, Global.CAR2, context);
        gl.glEnable(GL10.GL_TEXTURE_2D);
        gl.glClearDepthf(1.0f);

        // Text depthe of all objects on surface
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glDepthFunc(GL10.GL_LEQUAL);

        // Enable blend to create transperency
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

        // Load textures
        road.loadTexture(gl, Global.ROAD , context);
        right.loadTexture(gl, Global.TURN_RIGHT, context);
        left.loadTexture(gl, Global.TURN_LEFT, context);
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
            opponent -=(carSpeed*11.2);
            opponent2 -=(carSpeed*11.2);
            opponent3 -=(carSpeed*11.2);
            opponent4 -=(carSpeed*11.2);
            crash();
            if(carSpeed < 0.03f){
                carSpeed += 0.0002f;
            }
        }else{
            roadYOffset -= 1.0f;
            //opponent
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