package sanjit.tictactoe;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class GameBoard extends View {

    Paint paint;

    public GameBoard(Context context) {
        super(context);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    public GameBoard(Context context, AttributeSet attrib) {
        super(context, attrib);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int dimension = Math.min(heightMeasureSpec, widthMeasureSpec);
        super.onMeasure(dimension, dimension);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int d = getWidth();
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(getResources().getColor(R.color.board_line_color));
        for (float i = 1; i < 3; i++) {
            canvas.drawLine(i * d / 3, 0, i * d / 3, d, paint);
            canvas.drawLine(0, i * d / 3, d, i * d / 3, paint);
        }
    }
}
