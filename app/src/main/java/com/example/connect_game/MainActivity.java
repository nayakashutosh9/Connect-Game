package com.example.connect_game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int activePlayer=0;
    boolean gameActive=true;
    int[] gameState={2,2,2,2,2,2,2,2,2};
    int[][] winningPos={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    Button playAgainButton;
    TextView winnerText;
    public void dropIn(View view){
        ImageView counter=(ImageView) view;
        int tappedCounter=Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter]==2 && gameActive==true) {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1500);
            if (activePlayer == 0) {
                activePlayer = 1;
                counter.setImageResource(R.drawable.yelc);
            } else {
                activePlayer = 0;
                counter.setImageResource(R.drawable.redc);
            }
            counter.animate().translationYBy(1500).rotation(3600).setDuration(500);
            for (int i = 0; i < 8; i++) {
                if (gameState[winningPos[i][0]] == gameState[winningPos[i][1]] && gameState[winningPos[i][1]] == gameState[winningPos[i][2]] && gameState[winningPos[i][0]] != 2) {
                    gameActive=false;
                    String winner="";
                    if (activePlayer == 1) {
                        winner="Yellow";
                    } else {
                        winner="Red";
                    }
                    Toast.makeText(this, winner+" has won!!", Toast.LENGTH_SHORT).show();
                    winnerText.setText(winner + " has won!!!");
                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerText.setVisibility(View.VISIBLE);
                }
            }
        }
    }
    public void playAgain(View view){
        playAgainButton.setVisibility(View.INVISIBLE);
        winnerText.setVisibility(View.INVISIBLE);
        GridLayout gridLayout=(GridLayout) findViewById(R.id.gridLayout);
        for(int i=0;i<gridLayout.getChildCount();i++){
            ImageView counter=(ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        activePlayer=0;
        gameActive=true;
        for(int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playAgainButton=(Button) findViewById(R.id.playAgainButton);
        winnerText=(TextView) findViewById(R.id.textView);
    }
}
