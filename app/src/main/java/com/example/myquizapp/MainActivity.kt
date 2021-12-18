package com.example.myquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonStart: Button = findViewById(R.id.btnStart)
        buttonStart.setOnClickListener {
            val etText: EditText = findViewById(R.id.et_text)
            if(!etText.text.isEmpty()) {
                // INTENT (Navigating to a different screen)
                val intent = Intent(this, QuizQuestionsActivity::class.java)
                intent.putExtra(Constants.USER_NAME, etText.text.toString())
                this.startActivity(intent) // Starts the next activity
                finish() // Close the current Activity
            } else {
                Toast.makeText(this, "Please Enter Your Name", Toast.LENGTH_SHORT).show()
            }
        }
    }


}