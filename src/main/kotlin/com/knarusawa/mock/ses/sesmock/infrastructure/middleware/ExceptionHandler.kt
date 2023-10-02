package com.knarusawa.mock.ses.sesmock.infrastructure.middleware

import org.springframework.http.HttpStatus
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class ExceptionHandler {
  @ExceptionHandler(Exception::class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  fun exceptionHandler(e: Exception, model: Model): String {
    model.addAttribute("errorMessage", e.message ?: "An unknown error occurred.")
    return "error"
  }
}