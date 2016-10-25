package sanjit.tictactoe;

import android.graphics.Bitmap;

class Game {
    static player PlayerX;
    static player PlayerO;
    static char currentPlayer;
    static int scoreX;
    static int scoreO;
    static int totalGames;
    static String msg;
    static String Title;
    static int nextAction;
    static Bitmap screenShot;

    enum player {HUMAN, AI}
}
