package oncall.model

import oncall.Util

enum class DayOfWeek(val label: String, val isWeekDay: Boolean) {
    MONDAY("월", true),
    TUESDAY("화", true),
    WEDNESDAY("수", true),
    THURSDAY("목", true),
    FRIDAY("금", true),
    SATURDAY("토", false),
    SUNDAY("일", false);

    companion object {
        fun findByName(input: String): DayOfWeek {
            return entries.find {
                it.label == input
            } ?: throw IllegalArgumentException(Util.ERROR_MESSAGE)
        }

        fun getListOfDaysSize(totalDays: Int, startDayOfWeek: DayOfWeek): List<DayOfWeek> {
            val startIndex = entries.find {
                it == startDayOfWeek
            }?.ordinal ?: throw IllegalArgumentException("[ERROR] 요일 입력이 잘못 되었습니다.")
            val remainingDayOfWeeks = DayOfWeek.entries.drop(startIndex)

            var checkCount = 0
            val dayOfWeekList = mutableListOf<DayOfWeek>()

            for (dayOfWeek in remainingDayOfWeeks) {
                dayOfWeekList.add(dayOfWeek)
                checkCount++
            }

            while (checkCount < totalDays) {
                for (dayOfWeek in DayOfWeek.entries) {
                    dayOfWeekList.add(dayOfWeek)
                    checkCount++
                    if (checkCount >= totalDays) break
                }
            }
            return dayOfWeekList
        }
    }
}
