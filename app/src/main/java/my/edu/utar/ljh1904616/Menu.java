package my.edu.utar.ljh1904616;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        Button startButton = findViewById(R.id.start_button);
        Button topScoresButton = findViewById(R.id.topscores_button);
        Button menuQuitButton = findViewById(R.id.menu_quit_button);


        //set listener on the buttons

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, MainActivity.class);
                startActivity(intent);
            }
        });

        topScoresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, ScoreActivity.class);
                startActivity(intent);
            }
        });

        menuQuitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
