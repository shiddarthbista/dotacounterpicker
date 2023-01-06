package bista.shiddarth.dotacounterpicker.service

import bista.shiddarth.dotacounterpicker.model.HeroStats
import bista.shiddarth.dotacounterpicker.utils.ConverterUtils
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
        val heroId = ConverterUtils.getHeroIdFromHeroName(heroName)
        log.info("Hero id for $heroName is $heroId")
        val heroStatsList = getTopFiveCounterHeroStats(heroId)
        val heroIdOfCounters = heroStatsList.map { heroStatsMutableList ->
            heroStatsMutableList.map { it.heroId }
        }
        return heroIdOfCounters.map { heroNameList ->
            heroNameList.map { heroId ->
                ConverterUtils.getHeroNameFromHeroId(heroId)
            }
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

    fun responseFromMatchupsEndpoint(heroId: Int): Flux<HeroStats> = openDotaClient.get()
        .uri("heroes/$heroId/matchups")
        .retrieve()
        .bodyToFlux(HeroStats::class.java)

}
