package sanjit.tictactoe;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Main_Menu extends AppCompatActivity {
    Button HVHButton, HVAButton, AVAButton, ExitButton;
    private View mContentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__menu);

        mContentView = findViewById(R.id.fullscreen_content);
        hide();

        Intent intent = new Intent(Main_Menu.this, SplashScreen.class);
        startActivity(intent);

        HVHButton = (Button) findViewById(R.id.human_vs_human_button);
        AVAButton = (Button) findViewById(R.id.ai_vs_ai_button);
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

        AVAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Game.PlayerX = Game.player.AI;
                Game.PlayerO = Game.player.AI;
                loadGame();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        hide();
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

}
