package oncall.view

import camp.nextstep.edu.missionutils.Console
import oncall.Util
import oncall.model.DayOfWeek

class InputView {
    fun readMonthAndStartDayOfWeek(): Pair<Int, DayOfWeek> {
        println("비상 근무를 배정할 월과 시작 요일을 입력하세요>")
        val input = getInput()
        val data = input.split(",")
        require(data.size == 2)
        val month = data[0].trim().toIntOrNull() ?: throw IllegalArgumentException(Util.ERROR_MESSAGE)
        val startDayOfWeek = DayOfWeek.findByName(data[1].trim())
        return Pair(month, startDayOfWeek)
    }

    private fun getInput() = Console.readLine() ?: throw IllegalArgumentException(Util.ERROR_MESSAGE)
}
