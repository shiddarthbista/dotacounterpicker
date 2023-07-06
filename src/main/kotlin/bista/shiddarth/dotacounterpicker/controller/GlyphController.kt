package bista.shiddarth.dotacounterpicker.controller

import bista.shiddarth.dotacounterpicker.service.GlyphService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class GlyphController (@Autowired private val glyphService:GlyphService ){

    @CrossOrigin(origins = ["https://shiddarthbista.github.io/"])
    @PostMapping("/glyph/{matchId}")
    fun getCounters(@PathVariable matchId: String) =
        glyphService.responseFromGlyphServer(matchId)
}