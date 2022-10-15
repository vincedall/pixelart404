package com.github.robuspixelart404

class Questions() {
    val questions: List<String> = listOf(
        "Random question 1",
        "What is the number?",
        "What is the letter?"
    )
    val count = questions.size
        get() = field
    val answers: List<List<String>> = listOf(
        listOf("random answer 1", "random answer 2", "random answer 4"),
        listOf("random answer", "123", "hello"),
        listOf("random answer", "hello 123", "b")
    )
    val correctAnswers: Map<Int, Int> = mapOf(
        Pair<Int, Int>(0, 0),
        Pair<Int, Int>(1, 1),
        Pair<Int, Int>(2, 2)
    )
    var currentQuestion: Int = 0
    var isZero: Boolean = true

    fun getQuestion(): String{
        if (isZero) {
            isZero = false
            return questions[0]
        }else if (currentQuestion.equals(questions.size - 1)) {
            currentQuestion = 0
            return questions[0]
        }else
            return questions[++currentQuestion]
    }
}