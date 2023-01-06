package bista.shiddarth.dotacounterpicker.controller

import bista.shiddarth.dotacounterpicker.service.CounterService
import bista.shiddarth.dotacounterpicker.service.MatchupService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CounterController(
    private val counterService: CounterService,
    private val matchupService: MatchupService
) {

    @GetMapping("/counters/{heroName}")
    fun getCounters(@PathVariable heroName: String) =
        counterService.getTopFiveCounters(heroName)

    @GetMapping("/{heroName1}/versus/{heroName2}")
    fun getWinner(@PathVariable heroName1: String, @PathVariable heroName2: String) =
        matchupService.getWinner(heroName1, heroName2)
}