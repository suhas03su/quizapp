package com.example.myquizapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity() {


    private var progressBar: ProgressBar? = null
    private var tvProgress: TextView? = null
    private var tvOptionOne: TextView? = null
    private var tvOptionTwo: TextView? = null
    private var tvOptionThree: TextView? = null
    private var tvOptionFour: TextView? = null
    private var tvQuestion: TextView? = null
    private var ivCountryImage: ImageView? = null
    private var btnSubmit: Button? = null
    private val questions = Constants.getQuestions()
    private var currentPosition: Int = 0
    private var selectedAnswer: Int = -1

    private var username: String? = null

    private var score: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        this.username = intent.getStringExtra(Constants.USER_NAME)

        progressBar = findViewById(R.id.progressBar)
        tvProgress = findViewById(R.id.tvProgress)
        tvOptionOne = findViewById(R.id.tvFirstOption)
        tvOptionTwo = findViewById(R.id.tvSecondOption)
        tvOptionThree = findViewById(R.id.tvThirdOption)
        tvOptionFour = findViewById(R.id.tvFourthOption)
        tvQuestion = findViewById(R.id.tvQuestion)
        ivCountryImage = findViewById(R.id.ivImage)
        btnSubmit = findViewById(R.id.btnSubmit)
        progressBar?.max = this.questions.size

        tvOptionOne?.setOnClickListener {
            this.selectedAnswer = 0
            this.setTextViewsToDefault()
            this.modifyView()
        }

        tvOptionTwo?.setOnClickListener {
            this.selectedAnswer = 1
            this.setTextViewsToDefault()
            this.modifyView()
        }

        tvOptionThree?.setOnClickListener {
            this.selectedAnswer = 2
            this.setTextViewsToDefault()
            this.modifyView()
        }

        tvOptionFour?.setOnClickListener {
            this.selectedAnswer = 3
            this.setTextViewsToDefault()
            this.modifyView()
        }

        btnSubmit?.setOnClickListener {
            if(btnSubmit?.text.toString() == "NEXT") {
                btnSubmit?.text = "SUBMIT"
                this.showQuestion()
            } else {
                val question: Question = this.questions[currentPosition]
                if (this.selectedAnswer == -1) {
                    Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show()
                } else if (question.correctAnswer == this.selectedAnswer) {
                    this.setBackgroundToSelectedAnswer("#00FF00", selectedAnswer)
                    this.score += 1
                } else {
                    this.setBackgroundToSelectedAnswer("#FF0000", selectedAnswer)
                    this.setBackgroundToSelectedAnswer("#00FF00", question.correctAnswer)
                }
                this.currentPosition += 1
                btnSubmit?.text = "NEXT"
            }
        }

        this.showQuestion()
    }

    @SuppressLint("ResourceAsColor")
    private fun setBackgroundToSelectedAnswer(color: String, index: Int) {
        if(index == 0) {
            tvOptionOne?.setBackgroundColor(Color.parseColor(color))
        } else if (index == 1) {
            tvOptionTwo?.setBackgroundColor(Color.parseColor(color))
        } else if (index == 2) {
            tvOptionThree?.setBackgroundColor(Color.parseColor(color))
        } else if (index == 3) {
            tvOptionFour?.setBackgroundColor(Color.parseColor(color))
        }
    }

    private fun setTextViewsToDefault() {

        tvOptionOne?.setTextColor(Color.parseColor("#7A8089"))
        tvOptionOne?.typeface = Typeface.DEFAULT
        tvOptionOne?.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_color)

        tvOptionTwo?.setTextColor(Color.parseColor("#7A8089"))
        tvOptionTwo?.typeface = Typeface.DEFAULT
        tvOptionTwo?.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_color)

        tvOptionThree?.setTextColor(Color.parseColor("#7A8089"))
        tvOptionThree?.typeface = Typeface.DEFAULT
        tvOptionThree?.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_color)

        tvOptionFour?.setTextColor(Color.parseColor("#7A8089"))
        tvOptionFour?.typeface = Typeface.DEFAULT
        tvOptionFour?.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_color)

    }

    private fun modifyView() {
        val index = this.selectedAnswer
        if(index == 0) {
            tvOptionOne?.setTextColor(Color.parseColor("#363A43"))
            tvOptionOne?.setTypeface(tvOptionOne?.typeface, Typeface.BOLD)
            tvOptionOne?.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
        } else if (index == 1) {
            tvOptionTwo?.setTextColor(Color.parseColor("#363A43"))
            tvOptionTwo?.setTypeface(tvOptionTwo?.typeface, Typeface.BOLD)
            tvOptionTwo?.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
        } else if (index == 2) {
            tvOptionThree?.setTextColor(Color.parseColor("#363A43"))
            tvOptionThree?.setTypeface(tvOptionThree?.typeface, Typeface.BOLD)
            tvOptionThree?.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
        } else if (index == 3) {
            tvOptionFour?.setTextColor(Color.parseColor("#363A43"))
            tvOptionFour?.setTypeface(tvOptionFour?.typeface, Typeface.BOLD)
            tvOptionFour?.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
        }
    }

    private fun showQuestion() {
        if (this.currentPosition == this.questions.size) {
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra(Constants.USER_NAME, this.username)
            intent.putExtra(Constants.CORRECT_ANSWER, this.score.toString())
            intent.putExtra(Constants.TOTAL_QUESRIONS, this.questions.size.toString())
            this.startActivity(intent)
            finish()
        } else {
            val question: Question = this.questions[this.currentPosition]
            this.setTextViewsToDefault()
            progressBar?.progress = this.currentPosition
            tvProgress?.text = "$currentPosition / ${progressBar?.max}"

            tvQuestion?.text = question.name
            tvOptionOne?.text = question.optionOne
            tvOptionTwo?.text = question.optionTwo
            tvOptionThree?.text = question.optionThree
            tvOptionFour?.text = question.optionFour
            ivCountryImage?.setImageResource(question.image)
        }
    }

}