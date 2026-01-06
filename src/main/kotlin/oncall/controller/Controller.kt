package oncall.controller

import oncall.Util
import oncall.service.Service
import oncall.view.*

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

        // 근무표 출력하기
        outputView.printSchedule(dayList)
    }
}
