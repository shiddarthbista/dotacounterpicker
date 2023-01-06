package bista.shiddarth.dotacounterpicker.service

import bista.shiddarth.dotacounterpicker.exception.InvalidHeroNameException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.web.reactive.function.client.WebClient

class CounterServiceTest {

    private val counterService = CounterService(openDotaClient = WebClient.create())

    @Nested
    @DisplayName("Hero names to HeroId map")
    inner class HeroIdMap {
        @Test
        fun `get hero id from map given you have the hero name`() {
            val validHeroName = "axe"
            val validNameWithSpace = "beastm aster"
            val alternateCasing = "sNAp"
            assertThat(counterService.getHeroIdFromHeroName(validHeroName)).isEqualTo(2)
            assertThat(counterService.getHeroIdFromHeroName(validNameWithSpace)).isEqualTo(38)
            assertThat(counterService.getHeroIdFromHeroName(alternateCasing)).isEqualTo(128)
        }

        @Test
        fun `when hero name is not in map throw invalidHeroException`() {
            val invalidHeroName = "Volley"
            val exception = Assertions.assertThrows(InvalidHeroNameException::class.java) {
                counterService.getHeroIdFromHeroName(invalidHeroName)
            }
            assertThat(exception.message).isEqualTo("Cannot find such hero in the Archronicus")
        }
    }
}