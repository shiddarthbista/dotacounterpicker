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

}