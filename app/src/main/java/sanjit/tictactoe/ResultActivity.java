package sanjit.tictactoe;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class ResultActivity extends AppCompatActivity {
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        AdView adView1 = (AdView) findViewById(R.id.result_ad1);
        AdRequest adRequest1 = new AdRequest.Builder()
//                .addTestDevice("B57E80B764791677F26544661D06015F")
                .build();
        adView1.loadAd(adRequest1);

        AdView adView2 = (AdView) findViewById(R.id.result_ad2);
        AdRequest adRequest2 = new AdRequest.Builder()
//                .addTestDevice("B57E80B764791677F26544661D06015F")
                .build();
        adView2.loadAd(adRequest2);

        AdView adView3 = (AdView) findViewById(R.id.result_ad3);
        AdRequest adRequest3 = new AdRequest.Builder()
//                .addTestDevice("B57E80B764791677F26544661D06015F")
                .build();
        adView3.loadAd(adRequest3);

        ImageView resultImageView = (ImageView) findViewById(R.id.result_Image);
        resultImageView.setImageBitmap(Game.screenShot);


        title = (TextView) findViewById(R.id.result_title);
        title.setText(Game.Title);

        TextView resultTextView = (TextView) findViewById(R.id.result_text);
        resultTextView.setText(Game.msg);

        TextView positiveTextView = (TextView) findViewById(R.id.postiveButton);
        positiveTextView.setText("Play Again");
        positiveTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Game.nextAction = 1;
                finish();
            }
        });

        TextView neutralTextView = (TextView) findViewById(R.id.neutralButton);
        neutralTextView.setText("Main Menu");
        neutralTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Game.nextAction = 0;
                finish();
            }
        });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        title.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    @Override
    protected void onResume() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        title.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        super.onResume();
    }
}
