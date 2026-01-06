package oncall.model

import java.time.DayOfWeek

data class Day(
    val day: Int,
    val dayOfWeek: DayOfWeek,
    val isLegalHoliday: Boolean,
    val worker: Worker
) {

}
