package com.example.khanh.awaytime;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView countdownText;
    private Button countdownButton;

    private CountDownTimer countDownTimer;
    private long timeLeftInMilliseconds = 420000;
    private boolean timerRunning;
    MediaPlayer meditationMusic;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countdownText = findViewById(R.id.countdown_text);
        countdownButton = findViewById(R.id.countdown_button);
        meditationMusic = MediaPlayer.create(this, R.raw.meditate);

        countdownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startStop();

                            }
        });
        updateTimer();






    }

    public void playMusic(Button countdownButton) {
        meditationMusic.start();

    }

    public void pauseMusic (Button countdownButton) {
        meditationMusic.pause();
    }



    public void startStop() {
        if (timerRunning) {
            stopTimer();
            pauseMusic(countdownButton);
        } else {
            startTimer();
            playMusic(countdownButton);
        }
    }

    public void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMilliseconds, 1000) {
            @Override
            public void onTick(long l) {
                timeLeftInMilliseconds = l;
                updateTimer();
            }


            @Override
            public void onFinish() {

            }
        }.start();


        countdownButton.setText("PAUSE");
        timerRunning = true;
    }

    public void stopTimer() {
        countDownTimer.cancel();
        pauseMusic(countdownButton);
        timerRunning = false;
        countdownButton.setText("START");
    }

    public void updateTimer() {
        int minutes = (int) timeLeftInMilliseconds / 60000;
        int seconds = (int) timeLeftInMilliseconds % 60000 / 1000;

        String timeLeftText;

        timeLeftText = "" + minutes;
        timeLeftText += ":";
        if (seconds < 10) timeLeftText += "0";
        timeLeftText += seconds;

        countdownText.setText(timeLeftText);

    }
}
