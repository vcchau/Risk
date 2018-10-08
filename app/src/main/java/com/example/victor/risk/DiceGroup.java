package com.example.victor.risk;


import java.util.ArrayList;

public class DiceGroup {

    ArrayList<Die> myDice;
    int[] scoreArray = new int[5];

    public DiceGroup() {
        this.myDice = new ArrayList<Die>();
    }

    public void addDice(Die die) {
        myDice.add(die);
    }

    //Set all dice to disabled and set image
    public void disableDice() {
        for (Die d : myDice) {
            d.setClickable(false);
            if (d instanceof WhiteDie) {
                d.setImageResource(R.drawable.white_die_1_disabled);
            }
            else if (d instanceof RedDie) {
                d.setImageResource(R.drawable.red_die_1_disabled);
            }
        }
    }

    public void enableDice() {
        for (Die d : myDice) {
            d.setClickable(true);
            if (d instanceof WhiteDie) {
                d.setImageResource(R.drawable.white_die_1);
            }
            else if (d instanceof RedDie) {
                d.setImageResource(R.drawable.red_die_1);
            }
        }
    }



}
