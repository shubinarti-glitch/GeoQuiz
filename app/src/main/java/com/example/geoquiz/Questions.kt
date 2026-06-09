package com.example.geoquiz

/**
 * Вопрос викторины: текст + правильный ответ (true/false).
 */
data class Question(
    val text: String,
    val answer: Boolean
)

/**
 * Шесть географических вопросов (русская локализация UI).
 */
val questions: List<Question> = listOf(
    Question("Канберра — столица Австралии.", true),
    Question("Тихий океан больше Атлантического океана.", true),
    Question("Суэцкий канал соединяет Красное море и Индийский океан.", false),
    Question("Исток реки Нил находится в Египте.", false),
    Question("Амазонка — самая длинная река в Америке.", true),
    Question("Байкал — старейшее и глубочайшее пресноводное озеро в мире.", true)
)
