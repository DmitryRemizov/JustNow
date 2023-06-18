fun main(args: Array<String>) {
    var secondsAgo: Int
    while (true) {
        print("Введите время не активности пользователя в секундах или 0 для выхода: ")
        secondsAgo = readln().toInt()
        if (secondsAgo != 0)
            println(agoToText(secondsAgo) + "\n")
        else {
            println("Программа завершена.")
            break
        }
    }
}

fun agoToText(seconds: Int): String {
    val textToOut = "Был(а) " + when {
        seconds <= 60 -> "Только что"
        seconds > 60 && seconds <= 60 * 60 -> minutesOrHoursText(seconds)
        seconds > 60 * 60 && seconds <= 24 * 60 * 60 -> minutesOrHoursText(seconds, false)
        seconds > 24 * 60 * 60 && seconds <= 48 * 60 * 60 -> "Сегодня"
        seconds > 48 * 60 * 60 && seconds <= 72 * 60 * 60 -> "Вчера"
        else -> "Давно"
    }
    return textToOut
}

fun minutesOrHoursText(seconds: Int, minutes: Boolean = true): String {
    val minutesOrHours = if (minutes) (seconds / 60).toInt() else (seconds / (60 * 60)).toInt()
    val comparisonNumber = minutesOrHours % 100
    val text = when {
        (comparisonNumber >= 5 && comparisonNumber <= 20) || ((comparisonNumber % 10 != 1)
                && (comparisonNumber % 10 == 0 || comparisonNumber % 10 >= 5)) -> if (minutes) " минут" else " часов"
        (comparisonNumber % 10 > 1 && comparisonNumber % 10 <= 4) -> if (minutes) " минуты" else " часа"
        else -> if (minutes) " минуту" else " час"
    }
    return (if (minutes) "" else "в сети ") + minutesOrHours.toString() + text + " назад"
}