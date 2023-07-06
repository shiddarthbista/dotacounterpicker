package bista.shiddarth.dotacounterpicker.controller

import bista.shiddarth.dotacounterpicker.exception.ErrorResponse
import bista.shiddarth.dotacounterpicker.exception.InvalidHeroNameException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class CounterControllerAdvice {

    @ExceptionHandler(InvalidHeroNameException::class)
    fun handleInvalidHeroNameException(e: InvalidHeroNameException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse(errorCode = 4084, errorMessage = e.message))
    }
}