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
        Util.retryUntilValid {
            inputView.readMonthAndStartDayOfWeek()
        }
    }
}
