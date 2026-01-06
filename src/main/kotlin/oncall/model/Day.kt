package oncall.model

data class Day(
    val month: Int,
    val day: Int,
    val dayOfWeek: DayOfWeek,
    var isLegalHoliday: Boolean,
    val worker: Worker?
) {
    init {
        require(month in 1..12)
        require(day in 1..31)
        isLegalHoliday = LegalHoliday.isLegalHoliday(month, day)
    }

    private enum class LegalHoliday(val month: Int, val day: Int) {
        NEW_YEARS_DAY(1, 1),
        MARCH_FIRST(3, 1),
        CHILDRENS_DAY(5, 5),
        MEMORIAL_DAY(6, 6),
        NATIONAL_LIBERATION_DAY(8, 15),
        FOUNDATION_DAY(10, 3),
        KOREANS_DAY(10, 9),
        CHRISTMAS_DAY(12, 25);

        companion object {
            fun isLegalHoliday(month: Int, day: Int): Boolean {
                entries.find {
                    it.month == month && it.day == day
                } ?: return false
                return true
            }
        }
    }
}
