package bista.shiddarth.dotacounterpicker.model

import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class HeroStatsList(val heroList: List<HeroStats>)

data class HeroStats(
    @JsonProperty("hero_id")
    val heroId: Int,
    @JsonProperty("games_played")
    val gamesPlayed: Int,
    val wins: Int
)