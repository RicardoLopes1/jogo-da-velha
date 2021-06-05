package com.rlopes.jogodavelha

import android.annotation.SuppressLint
import android.graphics.Color
import android.media.AsyncPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun btnClick(view: View){
        val btnClicked = view as Button
        var cellID = 0

        when(btnClicked.id){
            R.id.btn1 -> cellID = 1
            R.id.btn2 -> cellID = 2
            R.id.btn3 -> cellID = 3
            R.id.btn4 -> cellID = 4
            R.id.btn5 -> cellID = 5
            R.id.btn6 -> cellID = 6
            R.id.btn7 -> cellID = 7
            R.id.btn8 -> cellID = 8
            R.id.btn9 -> cellID = 9
        }

        playGame(cellID, btnClicked)
    }

    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()
    var activePlayer = 1
    var gameOn = true

    @SuppressLint("SetTextI18n")
    fun playGame(cellID: Int, btnClicked: Button){
        val btnPlayer: Button = findViewById(R.id.btn_player)
        //btnPlayer.isEnabled = false

        if(gameOn){
            if(activePlayer == 1){
                btnClicked.text = "X"
                btnClicked.textSize = 40.0F
                btnClicked.setBackgroundColor(Color.RED)
                player1.add(cellID)
                activePlayer = 2
                btnPlayer.text = "Your turn: Player O"
            }else{
                btnClicked.text = "O"
                btnClicked.textSize = 40.0F
                btnClicked.setBackgroundColor(Color.BLUE)
                player2.add(cellID)
                activePlayer = 1
                btnPlayer.text = "Your turn: Player X"
            }
            btnClicked.isEnabled = false
            gameOn = checkWinner(btnPlayer)
        }

    }


    fun checkWinner(btnPlayer: Button): Boolean {
        var gameOn = true
        var winner = 0

        // lines
        if(player1.contains(1) && player1.contains(2) && player1.contains(3) ||
            player1.contains(4) && player1.contains(5) && player1.contains(6) ||
            player1.contains(7) && player1.contains(8) && player1.contains(9) ){
            winner = 1
        }
        if(player2.contains(1) && player2.contains(2) && player2.contains(3) ||
            player2.contains(4) && player2.contains(5) && player2.contains(6) ||
            player2.contains(7) && player2.contains(8) && player2.contains(9) ){
            winner = 2
        }
        // columns
        if(player1.contains(1) && player1.contains(4) && player1.contains(7) ||
            player1.contains(2) && player1.contains(5) && player1.contains(8) ||
            player1.contains(3) && player1.contains(6) && player1.contains(9) ){
            winner = 1
        }
        if(player2.contains(1) && player2.contains(4) && player2.contains(7) ||
            player2.contains(2) && player2.contains(5) && player2.contains(8) ||
            player2.contains(3) && player2.contains(6) && player2.contains(9) ){
            winner = 2
        }
        // cross
        if(player1.contains(1) && player1.contains(5) && player1.contains(9) ||
            player1.contains(3) && player1.contains(5) && player1.contains(7) ){
            winner = 1
        }
        if(player2.contains(1) && player2.contains(5) && player2.contains(9) ||
            player2.contains(3) && player2.contains(5) && player2.contains(7) ){
            winner = 2
        }
        if(winner != 0){
            if(winner == 1){
                //jogador 1
                btnPlayer.text = "Player X won!"
                btnPlayer.setBackgroundColor(Color.RED)
                gameOn = false
            }else{
                btnPlayer.text = "Player O won!"
                btnPlayer.setBackgroundColor(Color.BLUE)
                gameOn = false
            }
        }
        return gameOn
    }
}