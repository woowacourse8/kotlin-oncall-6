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

    fun putWorkerNameInDayList(
        dayList: List<Day>,
        weekdayWorkers: List<Worker>,
        holidayWorkers: List<Worker>
    ): List<Day> {
        var weekdayIndex = 0
        var holidayIndex = 0
        return dayList.map { day ->
            if (!day.isHoliday()) {
                day.worker = weekdayWorkers[weekdayIndex++]
            }
            if (weekdayIndex == weekdayWorkers.lastIndex) weekdayIndex = 0

            if (day.isHoliday()) {
                day.worker = holidayWorkers[holidayIndex++]
            }
            if (holidayIndex == holidayWorkers.lastIndex) holidayIndex = 0
            day
        }
    }

    fun checkSchedule(schedule: List<Day>): List<Day> {
        // 평일인 스케줄
        val weekdaySchedule = schedule.filter {
            !it.isHoliday()
        }

        // 휴일인 스케줄
        val holidaySchedule = schedule.filter {
            it.isHoliday()
        }

        while (isNotTwoConsecutive(schedule)) {
            for (i in 0..<schedule.lastIndex) {
                if (schedule[i].worker == schedule[i + 1].worker) {
                    val worker = schedule[i].worker


                    // 평일이면 다음 평일 근무자랑 교대
                    if (!schedule[i].isHoliday()) {
                        val day = weekdaySchedule.find { it.worker == worker }
                        val dayIndex = weekdaySchedule.indexOf(day)
                        weekdaySchedule[dayIndex].worker = weekdaySchedule[dayIndex + 1].worker
                        weekdaySchedule[dayIndex + 1].worker = worker
                    }

                    // 휴일이면 다음 휴일 근무자랑 교대
                    if (schedule[i].isHoliday()) {
                        val day = holidaySchedule.find { it.worker == worker }
                        val dayIndex = holidaySchedule.indexOf(day)
                        holidaySchedule[dayIndex].worker = holidaySchedule[dayIndex + 1].worker
                        holidaySchedule[dayIndex + 1].worker = worker
                    }
                }
            }
            return schedule
        }
        return schedule
    }

    private fun isNotTwoConsecutive(schedule: List<Day>): Boolean {
        for (i in 0..<schedule.lastIndex) {
            schedule.find {
                schedule[i].worker == schedule[i + 1].worker
            } ?: false
        }
        return true
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
