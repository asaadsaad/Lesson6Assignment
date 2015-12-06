package com.saad.asaad.lesson6assignment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by asaad on 12/6/2015.
 */
public class SplashScreen extends Activity implements View.OnClickListener{

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        ImageView splash = (ImageView) findViewById(R.id.imgLogo);
        splash.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(SplashScreen.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
