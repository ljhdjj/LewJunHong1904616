package my.edu.utar.ljh1904616;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
/*
public class ScoreActivity extends AppCompatActivity {

    private SharedPreferences mPrefs;
    private ListView mListView;
    private ArrayAdapter<String> mAdapter;
    private ArrayList<Score> mScoresList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        mPrefs = getSharedPreferences("high_scores", MODE_PRIVATE);
        mListView = findViewById(R.id.high_scores_list);
        mScoresList = new ArrayList<Score>();
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        mListView.setAdapter(mAdapter);

        // Read scores from shared preferences and add them to the list
        Map<String, ?> allEntries = mPrefs.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            String key = entry.getKey();
            String name;
            int underscoreIndex = key.indexOf("_");
            if (underscoreIndex != -1) {
                name = key.substring(0, underscoreIndex);
            } else {
                name = "Unknown";
            }
            Integer scoreValue = (Integer) entry.getValue();
            Score score = new Score(name, scoreValue);
            mScoresList.add(score);
        }

        // Sort the list in descending order
        Collections.sort(mScoresList, Collections.reverseOrder());

        // Add the top 25 scores to the adapter
        for (int i = 0; i < 25 && i < mScoresList.size(); i++) {
            Score score = mScoresList.get(i);
            //mAdapter.add(score.toString());
            mAdapter.add(score.getName() + " - " + score.getScore());
        }
    }



}
*/

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Button scoreQuitBtn = findViewById(R.id.score_quit_button);
        scoreQuitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScoreActivity.this, Menu.class);
                startActivity(intent);
            }
        });

        // Get the high scores from shared preferences
        SharedPreferences sharedPrefs = getSharedPreferences("topscorers", MODE_PRIVATE);
        Map<String, ?> scoresMap = sharedPrefs.getAll();

        // Convert the scores map to a list of Score objects
        List<Score> scoresList = new ArrayList<>();
        for (Map.Entry<String, ?> entry : scoresMap.entrySet()) {
            String name = entry.getKey();
            int score = Integer.parseInt(entry.getValue().toString());
            Score s = new Score(name, score);
            scoresList.add(s);
        }

        // Sort the scores list in descending order by score
        Collections.sort(scoresList);

        // Display the top 25 scores in a ListView
        ListView listView = findViewById(R.id.scorers_list);
        ArrayAdapter<Score> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, scoresList.subList(0, Math.min(scoresList.size(), 25)));
        listView.setAdapter(adapter);
    }
}
