package bista.shiddarth.dotacounterpicker.service

import bista.shiddarth.dotacounterpicker.model.GlyphUser
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux

@Service
class GlyphService(val glyphClient: WebClient) {

    private val log = LoggerFactory.getLogger(this::class.java)

    fun responseFromGlyphServer(matchId: String): Flux<GlyphUser> = glyphClient.post()
        .uri(matchId)
        .retrieve()
        .bodyToFlux(GlyphUser::class.java)
}