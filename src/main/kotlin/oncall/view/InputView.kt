package oncall.view

import camp.nextstep.edu.missionutils.Console
import oncall.Util
import oncall.model.DayOfWeek
import oncall.model.Worker

class InputView {
    fun readMonthAndStartDayOfWeek(): Pair<Int, DayOfWeek> {
        println("비상 근무를 배정할 월과 시작 요일을 입력하세요>")
        val input = getInput()
        val data = input.split(",")
        require(data.size == 2) { Util.ERROR_MESSAGE }
        val month = data[0].trim().toIntOrNull() ?: throw IllegalArgumentException(Util.ERROR_MESSAGE)
        require(month in 1..12) { Util.ERROR_MESSAGE }
        val startDayOfWeek = DayOfWeek.findByName(data[1].trim())
        return Pair(month, startDayOfWeek)
    }

    fun readWeekdayWorkers(): List<Worker> {
        println("평일 비상 근무 순번대로 사원 닉네임을 입력하세요>")
        return checkWorkersInput()
    }

    fun readHolidayWorkers(): List<Worker> {
        println("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요>")
        return checkWorkersInput()
    }

    private fun checkWorkersInput(): List<Worker> {
        val input = getInput()
        val data = input.split(",")
        require(data.size in 5..35) { Util.ERROR_MESSAGE }
        require(data.size == data.distinct().size) { Util.ERROR_MESSAGE }
        return data.map { Worker(it.trim()) }
    }

    private fun getInput() = Console.readLine() ?: throw IllegalArgumentException(Util.ERROR_MESSAGE)
}
