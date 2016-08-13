package sanjit.tictactoe;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

public class ChooseSymbolDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.choose_symbol_dialog_title)
                .setItems(new String[]{"X", "O"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            Game.PlayerX = Game.player.HUMAN;
                            Game.PlayerO = Game.player.AI;
                        } else {
                            Game.PlayerX = Game.player.AI;
                            Game.PlayerO = Game.player.HUMAN;
                        }
                        Game.scoreX = 0;
                        Game.scoreO = 0;
                        Game.totalGames = 0;
                        Intent intent = new Intent(getActivity(), GamePlay.class);
                        startActivity(intent);
                    }
                })
                .setNeutralButton(R.string.cancel, null);

        return builder.create();
    }
}
