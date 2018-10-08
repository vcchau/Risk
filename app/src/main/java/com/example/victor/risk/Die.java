package com.example.victor.risk;

import android.content.Context;
import android.util.AttributeSet;

abstract public class Die extends android.support.v7.widget.AppCompatImageButton {

    int value;

    public Die(Context context) {
        super(context);
    }

    public Die(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Die(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public int roll() {
        return 0;
    };
}
