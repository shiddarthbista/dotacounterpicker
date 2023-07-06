package bista.shiddarth.dotacounterpicker.controller

import bista.shiddarth.dotacounterpicker.model.GlyphUser
import bista.shiddarth.dotacounterpicker.service.CounterService
import bista.shiddarth.dotacounterpicker.service.MatchupService
import kotlinx.coroutines.delay
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CounterController(
    @Autowired private val counterService: CounterService,
    @Autowired private val matchupService: MatchupService
) {

    @GetMapping("/counters/{heroName}")
    fun getCounters(@PathVariable heroName: String) =
        counterService.getTopFiveCounters(heroName)

    @GetMapping("/{heroName1}/versus/{heroName2}")
    fun getWinner(@PathVariable heroName1: String, @PathVariable heroName2: String) =
        matchupService.getWinner(heroName1, heroName2)

    @CrossOrigin(origins = ["http://localhost:3000"])
    @PostMapping("test/{matchId}")
    suspend fun getGlyphUsers(@PathVariable matchId:String): List<GlyphUser> {
        val listOfGlyphUsers = mutableListOf<GlyphUser>()
        val glyphUser1 =  GlyphUser(7228511099,"mYktm | DusT", "76561198313214610", 6,48, 3, 32)
        val glyphUser2 =  GlyphUser(7228511099,"Shake", "76561198391119846", 9,58, 2, 54)
        val glyphUser3 =  GlyphUser(7228511099,"mYktm | DusT", "76561198313214610", 13,8, 3, 32)
        val glyphUser4 =  GlyphUser(7228511099,"Shake", "76561198391119846", 14,17, 2, 54)
        val glyphUser5 =  GlyphUser(7228511099,"Larry LaLonde", "76561198052770692", 19,7, 3, 129)
        val glyphUser6 =  GlyphUser(7228511099,"mYktm | DusT", "76561198313214610", 25,1, 3, 32)
        val glyphUser7 =  GlyphUser(7228511099,"mYktm | DusT", "76561198313214610", 29,28, 3, 32)
        val glyphUser8 =  GlyphUser(7228511099,"Shake", "76561198391119846", 42,44, 2, 54)
        val glyphUser9 =  GlyphUser(7228511099,"Inspector Vivek", "76561198128030555", 45,41, 3, 31)

        listOfGlyphUsers.add(glyphUser1)
        listOfGlyphUsers.add(glyphUser2)
        listOfGlyphUsers.add(glyphUser3)
        listOfGlyphUsers.add(glyphUser4)
        listOfGlyphUsers.add(glyphUser5)
        listOfGlyphUsers.add(glyphUser6)
        listOfGlyphUsers.add(glyphUser7)
        listOfGlyphUsers.add(glyphUser8)
        listOfGlyphUsers.add(glyphUser9)

        delay(5000);

        return listOfGlyphUsers
    }

}