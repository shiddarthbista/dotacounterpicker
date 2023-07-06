package bista.shiddarth.dotacounterpicker.model

import com.fasterxml.jackson.annotation.JsonProperty

data class GlyphUser(
    @JsonProperty("MatchID")
    val matchID: Long,
    @JsonProperty("Username")
    val username:String,
    @JsonProperty("UserSteamID")
    val userSteamId: String,
    @JsonProperty("Minute")
    val minute:Int,
    @JsonProperty("Second")
    val second: Int,
    @JsonProperty("Team")
    val team : Int,
    @JsonProperty("HeroID")
    val heroId: Int)