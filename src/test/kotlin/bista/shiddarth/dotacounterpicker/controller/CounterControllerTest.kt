package bista.shiddarth.dotacounterpicker.controller

import bista.shiddarth.dotacounterpicker.service.CounterService
import bista.shiddarth.dotacounterpicker.service.MatchupService
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import reactor.core.publisher.Mono

internal class CounterControllerTest {

    private val counterService = mockk<CounterService>()
    private val matchUpService = mockk<MatchupService>()

    private val testController = CounterController(counterService,matchUpService)

    @Nested
    inner class GetCounters {

        @Test
        fun `5 counters are returned`() {
            val expected = Mono.just(listOf("Beast", "Drow", "Bat Rider", "Tusk", "Bane"))
            every { counterService.getTopFiveCounters(any()) } returns expected

            val result = testController.getCounters("Axe")
            assertThat(result).isEqualTo(expected)
        }
    }

}