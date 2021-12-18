package com.example.myquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {

    private var tvUsername: TextView? = null
    private var tvScore: TextView? = null
    private var finishBtn: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        tvUsername = findViewById(R.id.username)
        tvScore = findViewById(R.id.tvScore)

        val score = intent.getStringExtra(Constants.CORRECT_ANSWER)
        val totalQuestions = intent.getStringExtra(Constants.TOTAL_QUESRIONS)
        var user = intent.getStringExtra(Constants.USER_NAME)

        tvUsername?.text = user.toString()
        tvScore?.text = "$score / $totalQuestions"

        finishBtn = findViewById(R.id.btnFinish)
        finishBtn?.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)
            finish()
        }
    }
}