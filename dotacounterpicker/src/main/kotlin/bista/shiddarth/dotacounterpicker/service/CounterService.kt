package bista.shiddarth.dotacounterpicker.service

import bista.shiddarth.dotacounterpicker.exception.InvalidHeroNameException
import bista.shiddarth.dotacounterpicker.model.HeroMap
import bista.shiddarth.dotacounterpicker.model.HeroStats
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class CounterService(val openDotaClient: WebClient) {

    fun getTopFiveCounters(heroName: String) {

        getHeroIdFromHeroName(heroName)

    }
    fun getHeroIdFromHeroName(heroName: String): Int {
        val heroNameBasic = heroName.lowercase().filter { !it.isWhitespace() }
        try {
            return HeroMap.heroIdMap.getValue(heroNameBasic)
        } catch (e: NoSuchElementException){
            throw InvalidHeroNameException("Cannot find such hero in the Archronicus")
        }

    }

    fun getTopFiveCounterHeroStats(heroName: String): Mono<MutableList<HeroStats>> {
        val response = openDotaClient.get()
            .uri("heroes/$heroName/matchups")
            .retrieve()
            .bodyToFlux(HeroStats::class.java)


        return response
            .sort { t1, t2 ->
                val t1WinRate = t1.wins.toDouble() / t1.gamesPlayed
                val t2WinRate = t2.wins.toDouble() / t2.gamesPlayed
                t2WinRate.compareTo(t1WinRate)
            }
            .take(5)
            .collectList()

    }


}
