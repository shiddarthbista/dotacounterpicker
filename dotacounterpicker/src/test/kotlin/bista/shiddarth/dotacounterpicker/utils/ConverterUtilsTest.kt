package bista.shiddarth.dotacounterpicker.utils

import bista.shiddarth.dotacounterpicker.exception.InvalidHeroNameException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

private const val AXE = "Axe"
private const val TECHIES = "Techies"

internal class ConverterUtilsTest {

    @Nested
    inner class GetHeroIdFromHeroName() {
        @Test
        fun `return heroID given heroName when calling getHeroIdFromHeroName`(){
            assertThat(ConverterUtils.getHeroIdFromHeroName(AXE)).isEqualTo(2)
            assertThat(ConverterUtils.getHeroIdFromHeroName(TECHIES)).isEqualTo(105)
        }

        @Test
        fun `when hero name is not in map throw invalidHeroException`(){
            val invalidHeroName = "Volley"
            val exception = Assertions.assertThrows(InvalidHeroNameException::class.java) {
                ConverterUtils.getHeroIdFromHeroName(invalidHeroName)
            }
            assertThat(exception.message).isEqualTo("Volley does not exist in the Archronicus.")
        }
    }

    @Nested
    inner class GetHeroNameFromHeroId() {
        @Test
        fun `return heroName given heroName when calling getHeroNameFromHeroId`(){
            assertThat(ConverterUtils.getHeroNameFromHeroId(2)).isEqualTo(AXE)
            assertThat(ConverterUtils.getHeroNameFromHeroId(105)).isEqualTo(TECHIES)
        }
    }
}

