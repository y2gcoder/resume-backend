package app.resume.adapter.webapi

import app.resume.domain.member.DuplicateEmailException
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.Instant

@ControllerAdvice
class ApiControllerAdvice : ResponseEntityExceptionHandler() {

    //RFC - 9457 의 problemDetail 사용
    @ExceptionHandler(Exception::class)
    fun handleException(exception: Exception): ProblemDetail {
        return getProblemDetail(HttpStatus.INTERNAL_SERVER_ERROR, exception)
    }

    @ExceptionHandler(value = [DuplicateEmailException::class])
    fun emailExceptionHandler(exception: Exception): ProblemDetail {
        return getProblemDetail(HttpStatus.CONFLICT, exception)
    }

    private fun getProblemDetail(
        httpStatus: HttpStatus,
        exception: Exception
    ): ProblemDetail {
        val problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, exception.message)

        problemDetail.setProperty("timestamp", Instant.now())
        problemDetail.setProperty("exception", exception.javaClass.simpleName)

        return problemDetail
    }
}