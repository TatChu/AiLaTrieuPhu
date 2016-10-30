package xyz.dichvuso.ailatrieuphu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_Start extends AppCompatActivity {

    Button btnOK, btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        ///
        btnOK = (Button)findViewById(R.id.buttonOK);
        btnCancel = (Button)findViewById(R.id.buttonCancel);



        final MediaPlayer sound = MediaPlayer.create(Activity_Start.this, R.raw.huong_dan);
        sound.start();



        ///Vào chơi
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sound.stop();
                Intent PlayScreen = new Intent(Activity_Start.this, Activity_Play.class);
                finish();
                startActivity(PlayScreen);
            }
        });


        ///Cancel
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sound.stop();
                //Go to HomeScreen
                Intent HomeScreen = new Intent(Activity_Start.this, MainActivity.class);
                finish();
                startActivity(HomeScreen);
            }
        });
    }

}