package bista.shiddarth.dotacounterpicker.openDotaClient

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class OpenDotaConfig {

    @Bean
    fun openDotaClient(): WebClient {
        return WebClient.create("https://api.opendota.com/api/")
    }

    @Bean
    fun glyphClient():WebClient {
        return WebClient.create("https://go-glyph-v2-f53b68856ba5.herokuapp.com/api/glyph/")
    }

}