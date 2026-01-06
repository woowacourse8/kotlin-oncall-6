package oncall.service

import oncall.Util
import oncall.model.Day
import oncall.model.DayOfWeek

class Service {
    // 월과 시작 요일을 입력하면 List<Day> 를 반환한다.
    fun getListOfDay(month: Int, startDayOfWeek: DayOfWeek): List<Day> {
        val totalDays = getLastDayOfMonth(month)
        val dayList = mutableListOf<Day>()
        val dayOfWeekList = DayOfWeek.getListOfDaysSize(totalDays, startDayOfWeek)

        for (i in 1..totalDays) {
            dayList.add(Day(month, i, dayOfWeekList[i - 1], false, null))
        }
        return dayList
    }

    private fun getLastDayOfMonth(month: Int): Int {
        if (month == 2)
            return 28

        if (month % 2 == 1)
            return 31

        if (month % 2 == 0)
            return 30

        throw IllegalArgumentException(Util.ERROR_MESSAGE)
    }
}
