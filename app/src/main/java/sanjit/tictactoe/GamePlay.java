package sanjit.tictactoe;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class GamePlay extends AppCompatActivity {
    TextView score0, scoreX, scoreLabelO, scoreLabelX;
    TextView GameButtons[][];
    char boardValues[][];
    int bestMoveX;
    int bestMoveY;
    private View mContentView;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);
        mContentView = findViewById(R.id.fullscreen_content);

        initLabels();
        initButtons();
        initBoardValues();
        randomizePlayer();
    }

    private void initBoardValues() {
        boardValues = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardValues[i][j] = '-';
            }
        }
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

    @Override
    protected void onResume() {
        super.onResume();
        hide();
    }

    @SuppressLint("SetTextI18n")
    private void initLabels() {
        score0 = (TextView) findViewById(R.id.Score2);
        scoreX = (TextView) findViewById(R.id.Score1);
        scoreLabelO = (TextView) findViewById(R.id.scoreLabel2);
        scoreLabelX = (TextView) findViewById(R.id.scoreLabel1);
        score0.setText(Game.scoreO + "/" + Game.totalGames);
        scoreX.setText(Game.scoreX + "/" + Game.totalGames);

        if (Game.PlayerO == Game.player.HUMAN) {
            scoreLabelO.setText(R.string.human);
        } else {
            scoreLabelO.setText(R.string.ai);
        }
        scoreLabelO.setText(scoreLabelO.getText() + "(O)");

        if (Game.PlayerX == Game.player.HUMAN) {
            scoreLabelX.setText(R.string.human);
        } else {
            scoreLabelX.setText(R.string.ai);
        }
        scoreLabelX.setText(scoreLabelX.getText() + "(X)");
    }

    private void initButtons() {
        GameButtons = new TextView[3][3];
        GameButtons[0][0] = (TextView) findViewById(R.id.game11);
        GameButtons[0][1] = (TextView) findViewById(R.id.game12);
        GameButtons[0][2] = (TextView) findViewById(R.id.game13);

        GameButtons[1][0] = (TextView) findViewById(R.id.game21);
        GameButtons[1][1] = (TextView) findViewById(R.id.game22);
        GameButtons[1][2] = (TextView) findViewById(R.id.game23);

        GameButtons[2][0] = (TextView) findViewById(R.id.game31);
        GameButtons[2][1] = (TextView) findViewById(R.id.game32);
        GameButtons[2][2] = (TextView) findViewById(R.id.game33);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                final int i1 = i;
                final int j1 = j;
                GameButtons[i][j].setText("");
                GameButtons[i][j].setEnabled(true);
                GameButtons[i][j].setClickable(true);
                GameButtons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        play(i1, j1);
                    }
                });
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private void play(int i1, int j1) {
        GameButtons[i1][j1].setText(Game.currentPlayer + "");
        boardValues[i1][j1] = Game.currentPlayer;
        GameButtons[i1][j1].setEnabled(false);
        togglePlayer();
        nextMove();
    }

    private void nextMove() {
        if (isWin(boardValues)) {
            Game.Title = "Game Over!";
            MessageBox messageBox = new MessageBox();
            messageBox.setCancelable(false);
            messageBox.show(getFragmentManager(), "Win Box");
        } else {
            if (isDraw(boardValues)) {
                Game.totalGames++;
                Game.Title = "Draw Game!";
                Game.msg = "Board is full and since no one won.The Game is Tied.";
                MessageBox messageBox = new MessageBox();
                messageBox.setCancelable(false);
                messageBox.show(getFragmentManager(), "Draw Box");
            } else {
                switch (Game.currentPlayer) {
                    case 'X':
                        if (Game.PlayerX == Game.player.AI) {
                            AIPlay();
                        }
                        break;
                    case 'O':
                        if (Game.PlayerO == Game.player.AI) {
                            AIPlay();
                        }
                        break;
                }
            }
        }
        initLabels();
    }

    private void randomizePlayer() {
        if ((Math.round(Math.random() * 100) % 2) == 1) {
            Game.currentPlayer = 'X';
        } else {
            Game.currentPlayer = 'O';
        }
        String msg = "";
        switch (Game.currentPlayer) {
            case 'X':
                if (Game.PlayerX == Game.player.HUMAN && Game.PlayerO == Game.player.AI) {
                    msg = "Your Turn first!";
                } else if (Game.PlayerX == Game.player.AI && Game.PlayerO == Game.player.HUMAN) {
                    msg = "AI Starts Game!";
                } else {
                    msg = "Player X Starts!";
                }
                break;
            case 'O':

                if (Game.PlayerO == Game.player.HUMAN && Game.PlayerX == Game.player.AI) {
                    msg = "Your Turn first!";
                } else if (Game.PlayerO == Game.player.AI && Game.PlayerX == Game.player.HUMAN) {
                    msg = "AI Starts Game!";
                } else {
                    Game.msg = "Player O Starts!";
                }
                break;
        }

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        nextMove();
    }

    private void togglePlayer() {
        Game.currentPlayer = Game.currentPlayer == 'X' ? 'O' : 'X';
    }

    private boolean isDraw(char values[][]) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (values[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isWin(char Values[][]) {
        for (int i = 0; i < 3; i++) {
            if (Values[i][0] == Values[i][1] && Values[i][0] == Values[i][2]) {
                if (Values[i][0] != '-') {
                    setwin(i, 0);
                    return true;
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            if (Values[0][i] == Values[1][i] && Values[0][i] == Values[2][i]) {
                if (Values[0][i] != '-') {
                    setwin(0, i);
                    return true;
                }
            }
        }
        if (Values[0][0] == Values[1][1] && Values[0][0] == Values[2][2]) {
            if (Values[0][0] != '-') {
                setwin(0, 0);
                return true;
            }
        }
        if (Values[0][2] == Values[1][1] && Values[0][2] == Values[2][0]) {
            if (Values[0][2] != '-') {
                setwin(0, 2);
                return true;
            }
        }

        return false;
    }

    private void setwin(int i, int j) {
        Game.totalGames++;
        switch (boardValues[i][j]) {
            case 'X':
                if (Game.PlayerX == Game.player.HUMAN && Game.PlayerO == Game.player.AI) {
                    Game.msg = "Congratulations! You Win!";
                } else if (Game.PlayerX == Game.player.AI && Game.PlayerO == Game.player.HUMAN) {
                    Game.msg = "You Lost! Better luck next time.";
                } else {
                    Game.msg = "Player X Wins!";
                }
                Game.scoreX++;
                break;
            case 'O':

                if (Game.PlayerO == Game.player.HUMAN && Game.PlayerX == Game.player.AI) {
                    Game.msg = "Congratulations! You Win!";
                } else if (Game.PlayerO == Game.player.AI && Game.PlayerX == Game.player.HUMAN) {
                    Game.msg = "You Lost! Better luck next time.";
                } else {
                    Game.msg = "Player O Wins!";
                }
                Game.scoreO++;
                break;
        }
    }

    private boolean checkWin(char Values[][]) {
        for (int i = 0; i < 3; i++) {
            if (Values[i][0] == Values[i][1] && Values[i][0] == Values[i][2]) {
                if (Values[i][0] != '-') {
                    return true;
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            if (Values[0][i] == Values[1][i] && Values[0][i] == Values[2][i]) {
                if (Values[0][i] != '-') {
                    return true;
                }
            }
        }
        if (Values[0][0] == Values[1][1] && Values[0][0] == Values[2][2]) {
            if (Values[0][0] != '-') {
                return true;
            }
        }
        if (Values[0][2] == Values[1][1] && Values[0][2] == Values[2][0]) {
            if (Values[0][2] != '-') {
                return true;
            }
        }

        return false;
    }

    private void AIPlay() {
        if (firstMove()) {
            bestMoveX = ((int) (Math.random() * 100)) % 3;
            bestMoveY = ((int) (Math.random() * 100)) % 3;
        } else {
            int scores[][] = new int[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (boardValues[i][j] == '-') {
                        scores[i][j] = bestMove(i, j, boardValues, Game.currentPlayer, 0);
                        boardValues[i][j] = '-';
                    } else {
                        scores[i][j] = Integer.MIN_VALUE;
                    }
                }
            }
            bestMoveX = 0;
            bestMoveY = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (scores[i][j] > scores[bestMoveX][bestMoveY]) {
                        bestMoveX = i;
                        bestMoveY = j;
                    }
                }
            }
        }
        play(bestMoveX, bestMoveY);
    }

    private boolean firstMove() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (boardValues[i][j] != '-') {
                    return false;
                }
            }
        }
        return true;
    }

    private int bestMove(int i, int j, char[][] Arr, char Player, int level) {
        char tempArr[][] = Arr.clone();
        tempArr[i][j] = Player;
        if (checkWin(tempArr)) {
            if (Player == Game.currentPlayer) {
                return (10 - level);
            } else {
                return (level - 10);
            }
        }
        if (isDraw(tempArr)) {
            return 0;
        }
        if (level == 10) {
            if (Player == Game.currentPlayer)
                return Integer.MIN_VALUE;
            else
                return Integer.MAX_VALUE;
        }
        int scores[][] = new int[3][3];
        for (int i1 = 0; i1 < 3; i1++) {
            for (int j1 = 0; j1 < 3; j1++) {
                if (Player == Game.currentPlayer) {
                    scores[i1][j1] = Integer.MAX_VALUE;
                } else {
                    scores[i1][j1] = Integer.MIN_VALUE;
                }
                if (tempArr[i1][j1] == '-') {
                    scores[i1][j1] = bestMove(i1, j1, tempArr, ((Player == 'X') ? 'O' : 'X'), level + 1);
                    tempArr[i1][j1] = '-';
                }
            }
        }
        if (Player == Game.currentPlayer) {
            return Min(scores);
        } else {
            return Max(scores);
        }
    }

    private int Max(int[][] scores) {
        int m = scores[0][0];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (scores[i][j] > m)
                    m = scores[i][j];
            }
        }
        return m;
    }

    private int Min(int[][] scores) {
        int m = scores[0][0];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (scores[i][j] < m)
                    m = scores[i][j];
            }
        }
        return m;
    }


    @Override
    public void recreate() {
        //super.recreate();
        initLabels();
        initButtons();
        initBoardValues();
        randomizePlayer();
        hide();
    }
}
