package oncall.model

import oncall.Util

class Calender() {

    fun getLastDayOfMonth(month: Int): Int {
        if (month == 2)
            return 28

        if (month % 2 == 1)
            return 31

        if (month % 2 == 0)
            return 30

        throw IllegalArgumentException(Util.ERROR_MESSAGE)
    }
}
