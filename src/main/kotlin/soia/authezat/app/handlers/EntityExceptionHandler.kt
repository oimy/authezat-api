package soia.authezat.app.handlers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.ErrorResponse
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.sql.SQLIntegrityConstraintViolationException

@RestControllerAdvice
class EntityExceptionHandler {

    @ExceptionHandler(SQLIntegrityConstraintViolationException::class)
    fun handleNotFound(e: Exception): ResponseEntity<ErrorResponse> =
        ResponseEntity.status(HttpStatus.BAD_REQUEST).build()

}