package com.knarusawa.mock.ses.sesmock.adapter.middleware

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class RestApiExceptionHandler {
    @ExceptionHandler(Exception::class)
    fun handleException(
            ex: Exception,
            request: HttpServletRequest
    ): ResponseEntity<ApiErrorResponse> {
        return ResponseEntity(
                ApiErrorResponse.of(
                        exception = ex,
                        errorCode = ApiErrorResponse.ErrorCode.INTERNAL_SERVER_ERROR.name,
                        errorMessage = ApiErrorResponse.ErrorCode.INTERNAL_SERVER_ERROR.message,
                ),
                org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
        )
    }

    data class ApiErrorResponse(
            val errorCode: String,
            val errorMessage: String,
    ) {
        companion object {
            fun of(
                    exception: Exception,
                    errorCode: String,
                    errorMessage: String,
            ): ApiErrorResponse {
                return ApiErrorResponse(
                        errorCode = errorCode,
                        errorMessage = errorMessage,
                )
            }
        }

        enum class ErrorCode(val message: String) {
            INTERNAL_SERVER_ERROR("An unknown error occurred."),
        }
    }
}