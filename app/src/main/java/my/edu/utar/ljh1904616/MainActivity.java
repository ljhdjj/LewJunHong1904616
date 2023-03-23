package my.edu.utar.ljh1904616;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private List<HighlightButton> gameViews;
    private int level;
    private int score;
    Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameViews = new ArrayList<>();
        level = 1;
        score = 0;
        incrementViews(4); // start with 4 views in level 1
        start();
        Button mainQuitButton = findViewById(R.id.quitBtn);
        mainQuitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Menu.class);
                startActivity(intent);
            }
        });

    }

    /*private void populateViews(int numViews) {
        LinearLayout container = findViewById(R.id.container);
        TextView scoreTextView = findViewById(R.id.score_text_view);
        container.removeAllViews();
        views.clear();
        for (int i = 0; i < numViews; i++) {
            HighlightView view = new HighlightView(this);
            view.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (view.isHighlighted) {
                        score++;
                        scoreTextView.setText("Score " + score);
                        view.unhighlight();
                        highlightRandomView();
                    }
                }
            });
            views.add(view);
            container.addView(view);
        }
    }*/

    private void incrementViews(int numViews) {
        LinearLayout game = findViewById(R.id.gamelayout);
        TextView scoreTextView = findViewById(R.id.score_text_view);
        game.removeAllViews();
        gameViews.clear();

        for (int i = 0; i < numViews; i++) {
            HighlightButton button = new HighlightButton(this);
            button.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (button.isHighlighted) {
                        score++;
                        scoreTextView.setText("Score " + score);
                        button.unhighlight();
                        randomlyHighlightViews();
                    }
                }
            });
            gameViews.add(button);
            game.addView(button);
        }
    }







    private void start() {
        TextView levelTextView = findViewById(R.id.level_text_view);
        levelTextView.setText("Level " + level);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                end();
            }
        }, 5000); // level lasts 5 seconds
        if (level == 1) {
            incrementViews(4);
        }
        else if (level == 2) {
            incrementViews(9);
        }
        else if (level == 3) {
            incrementViews(16);}
        else if (level == 4) {
            incrementViews(25);}
        else if (level == 5) {
            incrementViews(36);
        }
        else {
            int numViews = level * level;
            incrementViews(numViews);
        }
        randomlyHighlightViews();
    }

    //used to randomly highlight views
    private void randomlyHighlightViews() {
        int size = gameViews.size();
        int randomIndex = (int) (Math.random() * size);
        HighlightButton randomView = gameViews.get(randomIndex);
        randomView.highlight();
    }

    //when timer pass 5 seconds, then end the level and continue to another level and add views
    private void end() {
        level++;
        if (level <= 5) {
            /*int numViews = currentLevel * currentLevel;
            populateViews(numViews);*/
            start();
        } else {
            enterName();
        }
    }

    //when click quit button
    /*public void quitGame(View view) {
        // Stop the timer
        handler.removeCallbacksAndMessages(null);

        // Show a confirmation dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit Game");
        builder.setMessage("You sure bro?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Close the activity and return to the previous screen
                finish();
            }
        });
        builder.setNegativeButton("No", null);
        builder.show();
    }
*/

    //let users to enter their name when the game has ended
    private void enterName() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Congratulations bruhhh!\n Enter your name here!");
        final EditText nameEditText = new EditText(this);
        builder.setView(nameEditText);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String playerName = nameEditText.getText().toString();
                storeRecord(playerName, score);
                showRecords();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }



    //store player name and scores into shared preferences
    private void storeRecord(String name, int score) {
        SharedPreferences sPrefs = getSharedPreferences("topscorers", MODE_PRIVATE);
        SharedPreferences.Editor editorPrefs = sPrefs.edit();
        editorPrefs.putInt(name, score);
        editorPrefs.apply();
    }

    //direct to other page to show top 25 highest scores
    private void showRecords() {
        Intent intent = new Intent(this, ScoreActivity.class);
        startActivity(intent);
    }



}