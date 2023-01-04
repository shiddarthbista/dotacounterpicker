package bista.shiddarth.dotacounterpicker.controller

import bista.shiddarth.dotacounterpicker.service.CounterService
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class CounterControllerTest {

    @Nested
    inner class GetCounters {

        private val counterService = mockk<CounterService>()
        private val testController = CounterController(counterService)

        @Test
        fun `5 counters are returned`() {
            every { counterService.getTopFiveCounters("Axe") } returns listOf(
                "Beast","Drow","Bat Rider","Tusk", "Bane"
            )

            val result = testController.getCounters("Axe")
            assertThat(result).isEqualTo(listOf(
                "Beast","Drow","Bat Rider","Tusk", "Bane"
            ))
        }
    }

}