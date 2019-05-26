package com.example.natali.android1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

public class TemperatureView extends View {

    private Paint paint;
    private Paint paintDegreesSymbol;
    private String temperature;
    private String degreesSymbol;
    int fontSize = 160;
    float width;

    public TemperatureView(Context context) {
        super(context);
        init();
    }

    public TemperatureView(Context context, AttributeSet attributes) {
        super(context, attributes);
        init();
    }

    public TemperatureView(Context context, AttributeSet attributes, int defStyleAttr) {
        super(context, attributes, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextSize(fontSize);
        paintDegreesSymbol = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintDegreesSymbol.setStyle(Paint.Style.FILL);
        paintDegreesSymbol.setTextSize(90);
        degreesSymbol = getResources().getString(R.string.degrees);
    }

    public void setColor(int color) {
        paint.setColor(color);
        paintDegreesSymbol.setColor(color);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setTypeface(Typeface.create(Typeface.SERIF, Typeface.NORMAL));
        canvas.drawText(temperature, 16, 160, paint);
        width = paint.measureText(temperature);
        canvas.drawText(degreesSymbol, width + 40, 140, paintDegreesSymbol);
    }

    public void setTemperature(String temp) {
        this.temperature = temp;
    }
}
