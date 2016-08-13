package sanjit.tictactoe;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class GameBoardButtonLayout extends LinearLayout {
    public GameBoardButtonLayout(Context context) {
        super(context);
    }

    public GameBoardButtonLayout(Context context, AttributeSet attribs) {
        super(context, attribs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int dimension = Math.min(heightMeasureSpec, widthMeasureSpec);
        super.onMeasure(dimension, dimension);
    }
}
