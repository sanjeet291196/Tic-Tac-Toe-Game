package sanjit.tictactoe;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class Main_Menu extends AppCompatActivity {
    Button HVHButton,
            HVAButton,
    //            AVAButton,
    ExitButton;
    private View mContentView;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__menu);

        mContentView = findViewById(R.id.fullscreen_content);
        hide();

        Intent intent = new Intent(Main_Menu.this, SplashScreen.class);
        startActivity(intent);

        HVHButton = (Button) findViewById(R.id.human_vs_human_button);
//        AVAButton = (Button) findViewById(R.id.ai_vs_ai_button);
        HVAButton = (Button) findViewById(R.id.human_vs_ai_button);
        ExitButton = (Button) findViewById(R.id.exit_button);

        ExitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment exitDialog = new ExitDialog();
                exitDialog.show(getFragmentManager(), "Exit Dialog");
            }
        });

        HVHButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Game.PlayerX = Game.player.HUMAN;
                Game.PlayerO = Game.player.HUMAN;
                loadGame();
            }
        });

        HVAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChooseSymbolDialog fragment = new ChooseSymbolDialog();
                fragment.show(getFragmentManager(), "Choose Symbol");
            }
        });

/*
        AVAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Game.PlayerX = Game.player.AI;
                Game.PlayerO = Game.player.AI;
                loadGame();
            }
        });
*/

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId((getString(R.string.interstitial_ad_unit_id)));

    }

    @Override
    protected void onResume() {
        super.onResume();
        hide();
        loadInterstitial();
        showInterstitial();
    }

    @SuppressLint("InlinedApi")
    private void hide() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    public void loadGame() {
        Game.scoreX = 0;
        Game.scoreO = 0;
        Game.totalGames = 0;
        Intent intent = new Intent(Main_Menu.this, GamePlay.class);
        startActivity(intent);
    }

    private void showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and reload the ad.
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
            loadInterstitial();
        }
    }

    private void loadInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
//                .addTestDevice("B57E80B764791677F26544661D06015F")
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

    public void share(View view) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Try this tic tac toe game app. Play with your friends. And I bet you cannot defeat the AI. Try now, http://bit.ly/tictactoegameapp").putExtra(Intent.EXTRA_TITLE, " Try Tic Tac Toe Game");
        startActivity(Intent.createChooser(shareIntent, "Share Using"));
    }
}
