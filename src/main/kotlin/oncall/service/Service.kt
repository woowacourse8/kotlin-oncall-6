package oncall.service

import oncall.Util
import oncall.model.Day
import oncall.model.DayOfWeek
import oncall.model.Worker
import java.util.LinkedList

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

    fun createSchedule(
        dayList: List<Day>,
        weekdayWorkers: List<Worker>,
        holidayWorkers: List<Worker>
    ): List<Day> {
        val weekdayQueue = LinkedList(weekdayWorkers)
        val holidayQueue = LinkedList(holidayWorkers)
        var previousWorker: Worker? = null

        return dayList.map { day ->
            val worker = assignWorker(day, weekdayQueue, holidayQueue, previousWorker)
            day.worker = worker
            previousWorker = worker
            day
        }
    }

    private fun assignWorker(
        day: Day,
        weekdayQueue: LinkedList<Worker>,
        holidayQueue: LinkedList<Worker>,
        previousWorker: Worker?
    ): Worker {
        val currentQueue = if (day.isHoliday()) holidayQueue else weekdayQueue

        var candidate = currentQueue.poll()

        if (candidate == previousWorker) {
            val nextWorker = currentQueue.poll()
            currentQueue.addFirst(candidate)
            candidate = nextWorker
        }

        currentQueue.addLast(candidate)
        return candidate!!

    }

    private fun getLastDayOfMonth(month: Int): Int {
        return when (month) {
            2 -> 28
            4, 6, 9, 11 -> 30
            1, 3, 5, 7, 8, 10, 12 -> 31
            else -> throw IllegalArgumentException(Util.ERROR_MESSAGE)
        }
    }
}
