package xyz.dichvuso.ailatrieuphu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Activity_Score extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        List<Score> image_details = getData();
        final ListView listView = (ListView) findViewById(R.id.listView);

        listView.setAdapter(new Score_list_Apdapter(this, image_details));

    }


    List<Score> getData()
    {

        DbHelper database = new DbHelper (this);
       List<Score> list = database.get_list_Score();

/*
        List<Score> list = new ArrayList<Score>();
        Score score = new Score();

        score.setID(1);
        score.setMoney(220000);
        score.setName("The Boss");
        score.setAnh("female");

        list.add(score);
        Score score1 = new Score();
        score1.setID(2);
        score1.setMoney(120000000);
        score1.setName("Unknown");
        score1.setAnh("unknown");
        list.add(score1);


        Score score2 = new Score();
        score2.setID(3);
        score2.setMoney(80000000);
        score2.setName("Unknown");
        score2.setAnh("male");
        list.add(score2);
*/

        return  list;
    }
}
