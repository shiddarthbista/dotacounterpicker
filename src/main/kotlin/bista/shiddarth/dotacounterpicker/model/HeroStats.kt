package bista.shiddarth.dotacounterpicker.model

import com.fasterxml.jackson.annotation.JsonProperty

data class HeroStats(
    @JsonProperty("hero_id")
    val heroId: Int,
    @JsonProperty("games_played")
    val gamesPlayed: Int,
    val wins: Int
)