package oncall.view

import oncall.model.Day

class OutputView {
    fun printSchedule(dayList: List<Day>) {
        dayList.forEach { day ->
            var holiday = ""
            if (day.dayOfWeek.isWeekDay && day.isLegalHoliday) holiday = "(휴일)"
            println("${day.month}월 ${day.day}일 ${day.dayOfWeek.label}${holiday} ${day.worker?.name}")
        }
    }
}
