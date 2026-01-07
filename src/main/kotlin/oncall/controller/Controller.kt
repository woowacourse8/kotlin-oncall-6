package oncall.controller

import oncall.Util
import oncall.service.Service
import oncall.view.*
import kotlin.system.exitProcess

class Controller(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val service: Service
) {
    fun run() {
        // 1. 월과 요일 입력 받기
        val (month, startDayOfWeek) = Util.retryUntilValid {
            inputView.readMonthAndStartDayOfWeek()
        }
        val dayList = service.getListOfDay(month, startDayOfWeek)

        // 2. 비상 근무 순번 입력받기
        val (weekdayWorkers, holidayWorkers) = Util.retryUntilValid {
            val weekdayWorkers = inputView.readWeekdayWorkers()
            val holidayWorkers = inputView.readHolidayWorkers()
            Pair(weekdayWorkers, holidayWorkers)
        }

        val schedule = service.putWorkerNameInDayList(dayList, weekdayWorkers, holidayWorkers)

        // 근무표 출력하기
        outputView.printSchedule(schedule)
    }
}
