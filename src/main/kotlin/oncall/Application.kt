package oncall

import oncall.controller.Controller
import oncall.service.Service
import oncall.view.InputView
import oncall.view.OutputView
import kotlin.system.exitProcess

fun main() {
    val inputView = InputView()
    val outputView = OutputView()
    val service = Service()
    val controller = Controller(inputView, outputView, service)
    controller.run()
}
