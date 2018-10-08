package com.example.victor.risk;

import android.content.Context;
import android.util.AttributeSet;

public class RedDie extends Die {

    int value;

    public RedDie(Context context) {
        super(context);
    }

    public RedDie(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RedDie(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public int roll() {
        double r = Math.random();
        value = (int) (6 * r) + 1;
        //Set face of the die
        switch (value) {
            case 1:
                setImageResource(R.drawable.red_die_1);
                break;
            case 2:
                setImageResource(R.drawable.red_die_2);
                break;
            case 3:
                setImageResource(R.drawable.red_die_3);
                break;
            case 4:
                setImageResource(R.drawable.red_die_4);
                break;
            case 5:
                setImageResource(R.drawable.red_die_5);
                break;
            default:
                setImageResource(R.drawable.red_die_6);
                break;
        }
        return value;

    }
}
