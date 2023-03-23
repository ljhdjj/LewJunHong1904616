package my.edu.utar.ljh1904616;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

public class HighlightButton extends android.support.v7.widget.AppCompatButton {

    public boolean isHighlighted;

    public HighlightButton(Context context) {
        super(context);
        isHighlighted = false;
        setBackgroundColor(Color.GRAY);
        setTextColor(Color.WHITE);
    }

    public HighlightButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        isHighlighted = false;
        setBackgroundColor(Color.GRAY);
        setTextColor(Color.WHITE);
    }

    public void highlight() {
        isHighlighted = true;
        setBackgroundColor(Color.YELLOW);
    }

    public void unhighlight() {
        isHighlighted = false;
        setBackgroundColor(Color.GRAY);
        setTextColor(Color.WHITE);
    }

}
