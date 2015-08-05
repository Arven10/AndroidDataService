package ua.kharkiv.dorozhan.androiddataservice.viewmodel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import ua.kharkiv.dorozhan.androiddataservice.R;

public class SplashScreen extends Activity {
    private static final int TIME_TO_SLEEP = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(TIME_TO_SLEEP);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}