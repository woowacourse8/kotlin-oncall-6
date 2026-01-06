package oncall

import oncall.model.DayOfWeek
import oncall.service.Service

fun main() {
    val list = DayOfWeek.getListOfDaysSize(8, DayOfWeek.WEDNESDAY)
    val service = Service()

    println(service.getListOfDay(5, DayOfWeek.MONDAY))
}
