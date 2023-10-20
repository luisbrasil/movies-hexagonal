package com.hexagonal.application.exception


import org.springframework.beans.TypeMismatchException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ExceptionHandler : ResponseEntityExceptionHandler() {

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        val responses = ex.bindingResult.fieldErrors.map {
            ExceptionResponse(status.toString(), it.field.plus(it.defaultMessage))
        }
        return ResponseEntity(responses, status)
    }

    override fun handleMissingServletRequestParameter(
        ex: MissingServletRequestParameterException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        return ResponseEntity(
            ExceptionResponse(
                status.toString(),
                "O parâmetro ${ex.parameterName} é obrigatório"
            ),
            status
        )
    }

    override fun handleTypeMismatch(
        ex: TypeMismatchException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        return ResponseEntity(
            ExceptionResponse(
                status.toString(),
                "O parâmetro ${(ex as MethodArgumentTypeMismatchException).name} requer valores do tipo ${ex.requiredType}"
            ),
            status
        )
    }

    @ExceptionHandler(Throwable::class)
    fun handleOtherExceptions(ex: Throwable, req: WebRequest): ResponseEntity<Any> {
        var statusCode = HttpStatus.INTERNAL_SERVER_ERROR
        var mensagem = "Ocorreu um erro interno, tente novamente mais tarde"

        if (ex is CustomException) {
            statusCode = ex.statusCode ?: HttpStatus.INTERNAL_SERVER_ERROR
            mensagem = ex.message ?: mensagem
        } else {
            logger.error("Exceção não esperada: ", ex)
        }

        ex.printStackTrace()

        return ResponseEntity.status(statusCode.value()).body(
            ExceptionResponse(statusCode.name, mensagem)
        )
    }
}