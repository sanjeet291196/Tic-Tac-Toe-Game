package sanjit.tictactoe;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

public class SplashGameBoard extends GameBoard {
    Paint paintX, paintY, paintZ;
    float text;

    public SplashGameBoard(Context context) {
        super(context);
        init();
    }

    public SplashGameBoard(Context context, AttributeSet attrib) {
        super(context, attrib);
        init();
    }

    private void init() {
        paintX = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintY = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintZ = new Paint(Paint.ANTI_ALIAS_FLAG);

        paintX.setStyle(Paint.Style.FILL_AND_STROKE);
        paintY.setStyle(Paint.Style.FILL_AND_STROKE);
        paintZ.setStyle(Paint.Style.FILL_AND_STROKE);

        paintX.setColor(getResources().getColor(R.color.Move));
        paintY.setColor(getResources().getColor(R.color.Win));

        paintX.setTextSize(100);
        paintY.setTextSize(100);

        paintZ.setStrokeWidth(20);
        paintZ.setColor(getResources().getColor(R.color.colorPrimary));

        text = paintX.measureText("X");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int d = getWidth();
        float d1 = ((float) (d / 3.0));
        canvas.drawText("X", d1 / 2 - text / 2, d1 / 2 + text / 2, paintX);
        canvas.drawText("X", 3 * d1 / 2 - text / 2, d1 / 2 + text / 2, paintX);
        canvas.drawText("X", 5 * d1 / 2 - text / 2, 3 * d1 / 2 + text / 2, paintX);
        canvas.drawText("O", 5 * d1 / 2 - text / 2, 5 * d1 / 2 + text / 2, paintX);
        canvas.drawText("O", 5 * d1 / 2 - text / 2, d1 / 2 + text / 2, paintY);
        canvas.drawText("O", 3 * d1 / 2 - text / 2, 3 * d1 / 2 + text / 2, paintY);
        canvas.drawText("O", d1 / 2 - text / 2, 5 * d1 / 2 + text / 2, paintY);

        super.onDraw(canvas);
        canvas.drawLine(d, 0, 0, d, paintZ);
    }
}
