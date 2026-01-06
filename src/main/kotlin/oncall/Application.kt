package oncall

import oncall.model.DayOfWeek

fun main() {
    val list = DayOfWeek.getListOfDaysSize(8, DayOfWeek.WEDNESDAY)

    println(list)
}
