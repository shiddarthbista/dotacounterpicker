package bista.shiddarth.dotacounterpicker.utils

import bista.shiddarth.dotacounterpicker.exception.InvalidHeroNameException
import bista.shiddarth.dotacounterpicker.model.HeroMap

class ConverterUtils {
    companion object {

        fun getHeroIdFromHeroName(heroName: String): Int {
            val heroNameBasic = heroName.lowercase().filter { !it.isWhitespace() }
            try {
                return HeroMap.heroIdMap.getValue(heroNameBasic)
            } catch (e: NoSuchElementException) {
                throw InvalidHeroNameException("$heroName does not exist in the Archronicus.")
            }
        }

        fun getHeroNameFromHeroId(heroId: Int) = HeroMap.heroIdMap.entries.first { it.value == heroId }.key

    }
}