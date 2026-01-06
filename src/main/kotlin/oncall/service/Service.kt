package oncall.service

import oncall.model.*

class Service {
    val calender = Calender()

    // 월과 시작 요일을 입력하면 List<Day> 를 반환한다.
    fun getListOfDay(month: Int, startDayOfWeek: DayOfWeek): List<Day> {
        val totalDays = calender.getLastDayOfMonth(month)
        val dayList = mutableListOf<Day>()
        val dayOfWeekList = DayOfWeek.getListOfDaysSize(totalDays, startDayOfWeek)

        for (i in 1 .. totalDays) {
            dayList.add(Day(month, i, dayOfWeekList[i - 1], false, null))
        }
        return dayList
    }
}
