package com.example.myquizapp

object Constants {

    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESRIONS: String = "total_questions"
    const val CORRECT_ANSWER: String = "correct_answers"

    fun getQuestions(): List<Question> {
        var questionsList = ArrayList<Question>()

        // Question 1
        val q1 = Question(
            1,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_argentina,
            "Australia",
            "Argentina",
            "Brazil",
            "India",
            1
        )
        questionsList.add(q1)

        // Question 2
        val q2 = Question(
            2,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_india,
            "Brazil",
            "India",
            "Australia",
            "Argentina",
            1
        )
        questionsList.add(q2)

        // Question 3
        val q3 = Question(
            2,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_new_zealand,
            "New Zealand",
            "England",
            "Pakistan",
            "Sri Lanka",
            0
        )
        questionsList.add(q3)

        return questionsList
    }

}