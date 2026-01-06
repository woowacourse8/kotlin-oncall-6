package oncall.model

data class Worker(
    val name: String
) {
    init {
        require(name.length <= 5)
    }
}
