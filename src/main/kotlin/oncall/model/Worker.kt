package oncall.model

import oncall.Util

data class Worker(
    val name: String
) {
    init {
        require(name.length <= 5) { Util.ERROR_MESSAGE }
    }
}
