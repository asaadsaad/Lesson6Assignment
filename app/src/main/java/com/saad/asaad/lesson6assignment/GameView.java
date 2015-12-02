package com.saad.asaad.lesson6assignment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by asaad on 12/2/2015.
 */
public class GameView extends SurfaceView {
    private SurfaceHolder holder;
    private Bitmap Pot;
    private Bitmap Onion;
    private Bitmap Tomato;
    private Bitmap Carrot;
    private GameThread gthread = null;
    private float PotX = -250.0f;
    private float PotY = 100.0f;
    private float OnionX = -200.0f;
    private float OnionY = -206.0f;
    private float TomatoX = -200.0f;
    private float TomatoY = -200.0f;
    private float CarrotX = -200.0f;
    private float CarrotY = -125.0f;
    private boolean OnionActive = false;
    private boolean TomatoActive = false;
    private boolean CarrotActive = false;
    private int score = 0;
    private Paint scorePaint;

    public GameView(Context context) {
        super(context);
        holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                Pot = BitmapFactory.decodeResource(getResources(), R.drawable.pot);
                Onion = BitmapFactory.decodeResource(getResources(),R.drawable.onion);
                Tomato = BitmapFactory.decodeResource(getResources(),R.drawable.tomato);
                Carrot = BitmapFactory.decodeResource(getResources(),R.drawable.carrot);
                scorePaint = new Paint();
                scorePaint.setColor(Color.BLACK);
                scorePaint.setTextSize(50.0f);
                makeThread();
                gthread.setRunning(true);
                gthread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawColor(Color.WHITE);
        canvas.drawText("Vegetables Count: " + String.valueOf(score), 10.0f, 50.0f, scorePaint);
        if(OnionActive){
            OnionY = OnionY - 5;
            if ( OnionY < 325 ) {
                OnionX = -50.0f;
                OnionY = -101.0f;
                OnionActive = false;
            }else{
                canvas.drawBitmap(Onion, OnionX, OnionY, null);
            }
        }
        if(TomatoActive){
            TomatoY = TomatoY - 5;
            if ( TomatoY < 325 ) {
                TomatoX = -50.0f;
                TomatoY = -101.0f;
                TomatoActive = false;
            }else{
                canvas.drawBitmap(Tomato, TomatoX, TomatoY, null);
            }
        }
        if(CarrotActive){
            CarrotY = CarrotY - 5;
            if ( CarrotY < 325 ) {
                CarrotX = -50.0f;
                CarrotY = -101.0f;
                CarrotActive = false;
            }else{
                canvas.drawBitmap(Carrot, CarrotX, CarrotY, null);
            }
        }

        PotX = PotX + 2.0f;
        if(PotX > getWidth()) PotX = -205.0f;
        canvas.drawBitmap(Pot, PotX, PotY, null);

        if (OnionX >= PotX && OnionX <= PotX + Pot.getWidth()
                && OnionY <= PotY + Pot.getHeight() && OnionY >= PotY + Pot.getHeight() - 25.0f
        || TomatoX >= PotX && TomatoX <= PotX + Pot.getWidth()
                && TomatoY <= PotY + Pot.getHeight() && TomatoY >= PotY + Pot.getHeight() - 25.0f
        || CarrotX >= PotX && CarrotX <= PotX + Pot.getWidth()
                && CarrotY <= PotY + Pot.getHeight() && CarrotY >= PotY + Pot.getHeight() - 25.0f ){
            score++;
            OnionActive = false;
            OnionX = -50.0f;
            OnionY = -101.0f;
            TomatoActive = false;
            TomatoX = -50.0f;
            TomatoY = -101.0f;
            CarrotActive = false;
            CarrotX = -50.0f;
            CarrotY = -101.0f;
        }

    }

    public void sendOnion()
    {
        OnionActive = true;
        OnionX = getWidth() / 2.0f - Onion.getWidth() / 2;
        OnionY = getHeight() - Onion.getHeight() - 100;
    }

    public void sendTomato()
    {
        TomatoActive = true;
        TomatoX = getWidth() / 2.0f - Tomato.getWidth() / 2;
        TomatoY = getHeight() - Tomato.getHeight() - 100;
    }

    public void sendCarrot()
    {
        CarrotActive = true;
        CarrotX = getWidth() / 2.0f - Carrot.getWidth() / 2;
        CarrotY = getHeight() - Carrot.getHeight() - 100;
    }

    public void makeThread()
    {
        gthread = new GameThread(this);

    }

    public void killThread()
    {
        boolean retry = true;
        gthread.setRunning(false);
        while(retry)
        {
            try
            {
                gthread.join();
                retry = false;
            }
            catch (InterruptedException e)
            {
            }
        }
    }

    public void onDestroy()
    {
        Pot.recycle();
        Pot = null;
        System.gc();
    }
}
