package oncall.service

import oncall.Util
import oncall.model.Day
import oncall.model.DayOfWeek
import oncall.model.Worker

class Service {
    fun getListOfDay(month: Int, startDayOfWeek: DayOfWeek): List<Day> {
        val totalDays = getLastDayOfMonth(month)
        val dayList = mutableListOf<Day>()
        val dayOfWeekList = DayOfWeek.getListOfDaysSize(totalDays, startDayOfWeek)

        for (i in 1..totalDays) {
            dayList.add(Day(month, i, dayOfWeekList[i - 1], false, null))
        }
        return dayList
    }

    fun putWorkerNameInDayList(dayList: List<Day>, weekdayWorkers: List<Worker>, holidayWorkers: List<Worker>): List<Day> {
        var weekdayIndex = 0
        var holidayIndex = 0
        return dayList.map { day ->
            if (day.dayOfWeek.isWeekDay && !day.isLegalHoliday) {
                day.worker = weekdayWorkers[weekdayIndex++]
            }
            if (weekdayIndex == weekdayWorkers.lastIndex) weekdayIndex = 0

            if (!day.dayOfWeek.isWeekDay || day.isLegalHoliday) {
                day.worker = holidayWorkers[holidayIndex++]
            }
            if (holidayIndex == holidayWorkers.lastIndex) holidayIndex = 0
            day
        }
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
