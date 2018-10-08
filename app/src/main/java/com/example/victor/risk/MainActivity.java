package com.example.victor.risk;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private EditText set_Attack;
    private EditText set_Defense;
    private int numAttackers;
    private int numDefenders;
    private int maxSoldiers;
    private DiceGroup myDice;
    private TextView attacker_status;
    private TextView defender_status;
    private ProgressBar attacker_bar;
    private ProgressBar defender_bar;
    private WhiteDie whiteDieOne;
    private WhiteDie whiteDieTwo;
    private RedDie redDieOne;
    private RedDie redDieTwo;
    private RedDie redDieThree;
    int[] scoreArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreArray = new int[5];

        set_Attack = findViewById(R.id.set_attack);
        set_Defense = findViewById(R.id.set_defense);
        set_Attack.setText("0");
        set_Defense.setText("0");

        //Find all the die
        this.whiteDieOne = findViewById(R.id.white_die_1);
        this.whiteDieTwo = findViewById(R.id.white_die_2);
        this.redDieOne = findViewById(R.id.red_die_1);
        this.redDieTwo = findViewById(R.id.red_die_2);
        this.redDieThree = findViewById(R.id.red_die_3);

        //Add to DiceGroup
        myDice = new DiceGroup();
        myDice.addDice(whiteDieOne);
        myDice.addDice(whiteDieTwo);
        myDice.addDice(redDieOne);
        myDice.addDice(redDieTwo);
        myDice.addDice(redDieThree);

        //Disable all dice
        myDice.disableDice();

        //Set the progress bars
        this.attacker_bar = findViewById(R.id.attacker_bar);
        this.defender_bar = findViewById(R.id.defender_bar);

        this.attacker_status = findViewById(R.id.attacking_status);
        this.defender_status = findViewById(R.id.defending_status);

        //Only one instance of startButton
        final Button startButton = findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Start button click; set attackers and defenders to starting values
                numAttackers = Integer.parseInt(set_Attack.getText().toString());
                numDefenders = Integer.parseInt(set_Defense.getText().toString());

                maxSoldiers = (numAttackers > numDefenders) ? numAttackers : numDefenders;

                //Only update if valid number given for soldiers; print error message otherwise
                if (numAttackers > 0 && numDefenders > 0) {
                    //Only set the max once
                    attacker_bar.setMax(maxSoldiers);
                    defender_bar.setMax(maxSoldiers);

                    //Enable all dice
                    if (numAttackers >= 3 && numDefenders >= 2) {
                        myDice.enableDice();
                    }
                    else {
                        //Only one or two attackers;

                        redDieOne.setClickable(true);
                        redDieOne.setImageResource(R.drawable.red_die_1);
                        if (numAttackers == 2) {
                            redDieTwo.setClickable(true);
                            redDieTwo.setImageResource(R.drawable.red_die_1);
                        }
                        whiteDieOne.setClickable(true);
                        whiteDieOne.setImageResource(R.drawable.white_die_1);
                        if (numDefenders >= 2) {
                            whiteDieTwo.setClickable(true);
                            whiteDieTwo.setImageResource(R.drawable.white_die_1);
                        }
                    }

                    updateProgress();
                }
                else {
                    Toast.makeText(MainActivity.this, "Please enter numbers greater than 0 to start a game!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        Toast.makeText(MainActivity.this, "You are the attacker! Enter the number of soldiers to begin!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRestart() {
        super.onRestart();
        Toast.makeText(MainActivity.this, "Welcome back!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_quit:
                System.exit(1);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Updates the dice and score
    public void updateProgress() {
        //End of game
        if (this.numAttackers <= 0) {
            this.numAttackers = 0;
            Toast.makeText(MainActivity.this, "You lost! Press start to start a new game.", Toast.LENGTH_SHORT).show();
            myDice.disableDice();
        }
        else if (this.numDefenders <= 0) {
            this.numDefenders = 0;
            Toast.makeText(MainActivity.this, "You won! Press start to start a new game.", Toast.LENGTH_SHORT).show();
            myDice.disableDice();
        }
        this.attacker_status.setText("Attacking soldiers: " + this.numAttackers);
        this.defender_status.setText("Defending soldiers: " + this.numDefenders);

        if (numDefenders == 1) {
            whiteDieTwo.setClickable(false);
            whiteDieTwo.setImageResource(R.drawable.white_die_1_disabled);
        }
        if (numAttackers <= 2) {
            redDieThree.setClickable(false);
            redDieThree.setImageResource(R.drawable.red_die_1_disabled);
            if (numAttackers == 1) {
                redDieTwo.setClickable(false);
                redDieTwo.setImageResource(R.drawable.red_die_1_disabled);
            }
        }


        this.attacker_bar.setProgress(numAttackers);
        this.defender_bar.setProgress(numDefenders);
    }

    //Rolls all of the die
    public void rollDice(View view) {
        int w1 = 0;
        int w2 = 0;
        int r1 = 0;
        int r2 = 0;
        int r3 = 0;
        if (whiteDieOne.isClickable()) {
            w1 = this.whiteDieOne.roll();
        }
        if (whiteDieTwo.isClickable()) {
            w2 = this.whiteDieTwo.roll();
        }
        if (redDieOne.isClickable()) {
            r1 = this.redDieOne.roll();
        }
        if (redDieTwo.isClickable()) {
            r2 = this.redDieTwo.roll();
        }
        if (redDieThree.isClickable()) {
            r3 = this.redDieThree.roll();
        }
        int[] white = {w1, w2};
        int[] red = {r1, r2, r3};
        calculateScore(white, red);
    }

    //Calculates and updates the score
    public void calculateScore(int[] white, int[] red) {
        Arrays.sort(white);
        Arrays.sort(red);
        int tempRed = this.numAttackers;
        int tempWhite = this.numDefenders;
        //First round
        if (red[2] > white[1] && red[2] != 0 && white[1] != 0) { //Attacker wins
            this.numDefenders -= 1;
        }
        else {  //Defender wins
            this.numAttackers -= 1;
        }

        //Second round
        if (red[1] > white[0] && red[1] != 0 && white[0] != 0) { //Attacker wins
            this.numDefenders -= 1;
        }
        else { //Defender wins
            this.numAttackers -= 1;
        }

        // Attacker lost 2 soldiers
        if ((tempRed - this.numAttackers) == 2) {
            Toast.makeText(MainActivity.this, "You lost 2 soldiers!", Toast.LENGTH_SHORT).show();
        }
        else if ((tempWhite - this.numDefenders) == 2) {
            Toast.makeText(MainActivity.this, "The defender lost 2 soldiers!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(MainActivity.this, "You both lost 1 soldier!", Toast.LENGTH_SHORT).show();
        }
        updateProgress();
    }

}
