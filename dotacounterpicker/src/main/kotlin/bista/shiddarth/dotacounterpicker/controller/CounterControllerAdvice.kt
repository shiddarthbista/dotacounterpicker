package bista.shiddarth.dotacounterpicker.controller

import bista.shiddarth.dotacounterpicker.exception.ErrorResponse
import bista.shiddarth.dotacounterpicker.exception.InvalidHeroNameException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class CounterControllerAdvice {

    @ExceptionHandler(InvalidHeroNameException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleInvalidHeroNameException(e: InvalidHeroNameException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse(errorCode = 4084, errorMessage = e.message))
    }
}