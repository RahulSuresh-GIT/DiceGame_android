package com.rahul.scarnes_dice;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

import static android.R.attr.id;

public class MainActivity extends AppCompatActivity {


  int userScore=0,compScore=0,userTurn=0,compTurn=0,state=0,temp=0;
    ImageView dice;
    TextView mScore,cScore,tScore;
    Button hold,roll;
    int[] cards = {R.drawable.dice1, R.drawable.dice2, R.drawable.dice3, R.drawable.dice4, R.drawable.dice5, R.drawable.dice6};
    Random r = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void winFuction(View view)                                 // TO FIND THE WINNER OF THE GAME
    {
        if (userScore>= 100) {
            hold.setVisibility(view.INVISIBLE);
            roll.setVisibility(view.INVISIBLE);

            Toast.makeText(MainActivity.this, "YOU WON!", Toast.LENGTH_LONG).show();
            mScore.setText(String.valueOf(userScore));
            cScore.setText(String.valueOf(compScore));


        }
        else if(compScore>=100)
         {
            hold.setVisibility(view.INVISIBLE);
            roll.setVisibility(view.INVISIBLE);
            Toast.makeText(MainActivity.this, "PLAYER 2 WON!", Toast.LENGTH_LONG).show();
            mScore.setText(String.valueOf(userScore));
            cScore.setText(String.valueOf(compScore));

        }
    }
   public void rollFunction (View view)                                       // ROLL BUTTON - ONCLICK
       {
           winFuction(null);
           dice = (ImageView) findViewById(R.id.imageView);
       mScore = (TextView) findViewById(R.id.my_Score);
       cScore = (TextView) findViewById(R.id.comp_Score);
       tScore = (TextView) findViewById(R.id.turnScore);
           hold=(Button)findViewById(R.id.hold);
           roll=(Button)findViewById(R.id.roll);

       int n = r.nextInt(6);
       if (state == 0) {
          hold.setVisibility(view.VISIBLE);
           temp = n + 1;

           if (temp == 1) {
               userTurn = temp=0;
               mScore.setText(String.valueOf(userScore));
              // hold.setVisibility(view.INVISIBLE);
               state = 1;
               Toast.makeText(MainActivity.this, "Player 2 turn!", Toast.LENGTH_SHORT).show();

           }
           userTurn=temp+userTurn;

           tScore.setText(String.valueOf(userTurn));

       }
        else {
           temp = n + 1;
           compScore = compScore + userTurn;

           if (temp == 1) {
               state  = temp=compTurn=0;
               cScore.setText(String.valueOf(compScore));
               hold.setVisibility(view.VISIBLE) ;
               Toast.makeText(MainActivity.this, "Your turn!", Toast.LENGTH_SHORT).show();
           }
           compTurn=compTurn+temp;
           tScore.setText(String.valueOf(compTurn));

       }
           winFuction(null);
       dice.setImageResource(cards[n]);
   }

    public void resetFunction(View view)                                           // RESET BUTTON - ONCLICK
    {
        Toast.makeText(MainActivity.this,"Game Resetted!", Toast.LENGTH_SHORT).show();
        hold.setVisibility(view.VISIBLE) ;
        roll.setVisibility(view.VISIBLE) ;

        userScore=compScore=state=userTurn=compTurn=0;
        dice.setImageResource(R.drawable.play);

        dice=(ImageView) findViewById(R.id.imageView);
        mScore=(TextView)findViewById(R.id.my_Score);
        cScore=(TextView)findViewById(R.id.comp_Score);
        tScore.setText(String.valueOf(userTurn));
        mScore.setText(String.valueOf(userScore));
        cScore.setText(String.valueOf(compScore));



    }

    public void holdFunction(View view)                                              // HOLD BUTTON - ONCLICK
    {
        winFuction(null);
          if (state==0)
        {
            userScore = userScore+ userTurn;
            mScore.setText(String.valueOf(userScore));
            userTurn=0;
            tScore.setText(String.valueOf(userTurn));
            state=1;
            Toast.makeText(MainActivity.this,"Control moved to Player 2!", Toast.LENGTH_SHORT).show();


        }
        else if (state==1)
        {
            compScore =  compScore+compTurn;
            cScore.setText(String.valueOf(compScore));
            compTurn=0;
            tScore.setText(String.valueOf(compTurn));
            state = 0;
            Toast.makeText(MainActivity.this, "Control moved to you!", Toast.LENGTH_SHORT).show();
        }


    }


}