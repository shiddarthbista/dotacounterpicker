package bista.shiddarth.dotacounterpicker.controller

import bista.shiddarth.dotacounterpicker.service.CounterService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CounterController(private val counterService: CounterService) {


    @GetMapping("/counters/{heroName}")
    fun getCounters(@PathVariable heroName:String) =
        counterService.getTopFiveCounters(heroName)
}