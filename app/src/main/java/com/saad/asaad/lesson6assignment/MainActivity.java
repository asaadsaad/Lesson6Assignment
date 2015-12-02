package com.saad.asaad.lesson6assignment;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class MainActivity extends Activity
{
    private GameView gameView;
    private Button onionButton;
    private Button tomatoButton;
    private Button carrotButton;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);  //gets rid of the title at the top of the app

        gameView = new GameView(this);

        onionButton = new Button(this);
        onionButton.setWidth(200);
        onionButton.setHeight(75);
        onionButton.setBackgroundColor(Color.LTGRAY);
        onionButton.setTextColor(Color.RED);
        onionButton.setTextSize(20);
        onionButton.setText("Add Onion");
        onionButton.setOnClickListener(addOnion);
        onionButton.setGravity(Gravity.CENTER);

        tomatoButton = new Button(this);
        tomatoButton.setWidth(200);
        tomatoButton.setHeight(75);
        tomatoButton.setBackgroundColor(Color.LTGRAY);
        tomatoButton.setTextColor(Color.RED);
        tomatoButton.setTextSize(20);
        tomatoButton.setText("Add Tomato");
        tomatoButton.setOnClickListener(addTomato);
        tomatoButton.setGravity(Gravity.CENTER);

        carrotButton = new Button(this);
        carrotButton.setWidth(200);
        carrotButton.setHeight(75);
        carrotButton.setBackgroundColor(Color.LTGRAY);
        carrotButton.setTextColor(Color.RED);
        carrotButton.setTextSize(20);
        carrotButton.setText("Add Carrot");
        carrotButton.setOnClickListener(addCarrot);
        carrotButton.setGravity(Gravity.CENTER);

        FrameLayout GameLayout = new FrameLayout(this);
        LinearLayout ButtonLayout = new LinearLayout (this);
        ButtonLayout.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
        ButtonLayout.addView(onionButton);
        ButtonLayout.addView(tomatoButton);
        ButtonLayout.addView(carrotButton);
        GameLayout.addView(gameView);
        GameLayout.addView(ButtonLayout);
        setContentView(GameLayout);
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.killThread(); //Notice this reaches into the GameView object and runs the killThread mehtod.
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        gameView.onDestroy();
    }

    private View.OnClickListener addOnion = new View.OnClickListener()
    {
        @Override
        public void onClick(View v) {
            gameView.sendOnion();
        }
    };

    private View.OnClickListener addTomato = new View.OnClickListener()
    {
        @Override
        public void onClick(View v) {
            gameView.sendTomato();
        }
    };

    private View.OnClickListener addCarrot = new View.OnClickListener()
    {
        @Override
        public void onClick(View v) {
            gameView.sendCarrot();
        }
    };


}
