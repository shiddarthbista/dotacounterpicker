package bista.shiddarth.dotacounterpicker.service

import bista.shiddarth.dotacounterpicker.model.MatchupWinner
import bista.shiddarth.dotacounterpicker.utils.ConverterUtils
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class MatchupService(private val counterService: CounterService) {
    private val log = LoggerFactory.getLogger(this::class.java)

    fun getWinner(heroName1: String, heroName2: String): Mono<MatchupWinner> {
        log.info("$heroName1 versus $heroName2")
        val heroId1 = ConverterUtils.getHeroIdFromHeroName(heroName1)
        val heroId2 = ConverterUtils.getHeroIdFromHeroName(heroName2)

        val response = counterService.responseFromMatchupsEndpoint(heroId1)
        return response.filter { it.heroId == heroId2 }
            .map { heroStat ->
                var winRate = (heroStat.wins.toDouble() / heroStat.gamesPlayed.toDouble()) * 100
                val heroIdWinner = if (winRate > 50) heroId2 else heroId1
                if (heroIdWinner == heroId1) {
                    winRate = 100 - winRate
                }
                val winner = ConverterUtils.getHeroNameFromHeroId(heroIdWinner)
                log.info("$winner has a win rate of ${String.format("%.2f", winRate)} percent")
                MatchupWinner(winner, String.format("%.2f", winRate).toDouble())
            }
            .single()
    }
}
