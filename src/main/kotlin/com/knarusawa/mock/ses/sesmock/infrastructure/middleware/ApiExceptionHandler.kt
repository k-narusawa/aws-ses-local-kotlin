package com.knarusawa.mock.ses.sesmock.infrastructure.middleware

import com.knarusawa.mock.ses.sesmock.infrastructure.dto.response.ApiErrorResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.boot.logging.LogLevel
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ApiExceptionHandler {
    private val log = logger()

    @ExceptionHandler(Exception::class)
    fun handleException(
            ex: Exception,
            request: HttpServletRequest
    ): ResponseEntity<ApiErrorResponse> {
        log.warn("message: ${ex.message}, cause: ${ex.cause}, ex: $ex")
        ex.printStackTrace()
        return ResponseEntity(
                ApiErrorResponse.of(
                        exception = ex,
                        logLevel = LogLevel.ERROR,
                        errorMessage = ex.message ?: "An unknown error occurred.",
                ),
                org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
        )
    }
}