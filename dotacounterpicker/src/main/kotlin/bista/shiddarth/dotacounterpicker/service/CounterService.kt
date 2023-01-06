package bista.shiddarth.dotacounterpicker.service

import bista.shiddarth.dotacounterpicker.exception.InvalidHeroNameException
import bista.shiddarth.dotacounterpicker.model.HeroMap
import bista.shiddarth.dotacounterpicker.model.HeroStats
import bista.shiddarth.dotacounterpicker.model.MatchupWinner
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class CounterService(val openDotaClient: WebClient) {

    private val log = LoggerFactory.getLogger(this::class.java)

    fun getTopFiveCounters(heroName: String): Mono<List<String>> {
        log.info("Received request to get counters for $heroName")
        val heroId = getHeroIdFromHeroName(heroName)
        log.info("Hero id for $heroName is $heroId")
        val heroStatsList = getTopFiveCounterHeroStats(heroId)
        val heroIdOfCounters = heroStatsList.map { heroStatsMutableList ->
            heroStatsMutableList.map { it.heroId }
        }
        val heroNameOfCounters = heroIdOfCounters.map { heroNameList ->
            heroNameList.map { heroId ->
                getHeroNameFromHeroId(heroId)
            }
        }
        return heroNameOfCounters
    }

    fun getHeroIdFromHeroName(heroName: String): Int {
        val heroNameBasic = heroName.lowercase().filter { !it.isWhitespace() }
        try {
            return HeroMap.heroIdMap.getValue(heroNameBasic)
        } catch (e: NoSuchElementException) {
            throw InvalidHeroNameException("$heroName does not exist in the Archronicus.")
        }
    }

    fun getTopFiveCounterHeroStats(heroId: Int): Mono<MutableList<HeroStats>> {
        val response = responseFromMatchupsEndpoint(heroId)

        return response
            .filter { it.gamesPlayed > 10 }
            .sort { t1, t2 ->
                val t1WinRate = t1.wins.toDouble() / t1.gamesPlayed
                val t2WinRate = t2.wins.toDouble() / t2.gamesPlayed
                t2WinRate.compareTo(t1WinRate)
            }
            .take(5)
            .collectList()
    }

    private fun responseFromMatchupsEndpoint(heroId: Int): Flux<HeroStats> {
        return openDotaClient.get()
            .uri("heroes/$heroId/matchups")
            .retrieve()
            .bodyToFlux(HeroStats::class.java)
    }

    fun getWinner(heroName1: String, heroName2: String): Mono<MatchupWinner> {
        val heroId1 = getHeroIdFromHeroName(heroName1)
        val heroId2 = getHeroIdFromHeroName(heroName2)

        val response = responseFromMatchupsEndpoint(heroId1)
        return response.filter { it.heroId == heroId2 }
            .map {heroStat->
                var winRate = (heroStat.wins.toDouble() / heroStat.gamesPlayed.toDouble()) * 100
                val heroIdWinner = if (winRate > 50) heroId2 else heroId1
                if (heroIdWinner == heroId1){
                    winRate = 100 - winRate
                }
                val winner = getHeroNameFromHeroId(heroIdWinner)
                MatchupWinner(winner, String.format("%.2f", winRate).toDouble())
            }
            .single()

    }

    private fun getHeroNameFromHeroId(heroId: Int) =
        HeroMap.heroIdMap.entries.first { it.value == heroId }.key


}
